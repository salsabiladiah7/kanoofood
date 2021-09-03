package com.mafaa.kanoofood;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    public final static String TAG_USERNAME = "username";
    public final static String TAG_ID = "id";
    public final static String TAG_LEVEL = "level";
    public static final String MY_PREFERENCES = "MyPrefs";
    public static final String STATUS = "status";
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    private static final String TAG = Login.class.getSimpleName();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    final String MESSAGE_NO_INTERNET_ACCESS = "No Internet Connection";
    final String MESSAGE_CANNOT_BE_EMPTY = "Kolom Tidak Boleh Kosong";
    final String MESSAGE_LOGIN = "Logging in ...";
    TextInputEditText teEmail, tePassword;
    TextView tvRegister;
    Button btnLogin;
    ProgressBar progressBar;
    ProgressDialog pDialog;
    int success;
    ConnectivityManager conMgr;
    String tag_json_obj = "json_obj_req";
    SharedPreferences sharedPreferences;
    Boolean status = false;
    String id, username, level, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), MESSAGE_NO_INTERNET_ACCESS,
                        Toast.LENGTH_LONG).show();
            }
        }

        teEmail = findViewById(R.id.email);
        tePassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btn_login);
        progressBar = findViewById(R.id.progress);
        tvRegister = findViewById(R.id.tv_register);


        sharedPreferences = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        status = sharedPreferences.getBoolean(STATUS, false);
        id = sharedPreferences.getString(TAG_ID, null);
        username = sharedPreferences.getString(TAG_USERNAME, null);
        email = sharedPreferences.getString(EMAIL, null);
        password = sharedPreferences.getString(PASSWORD, null);
        level = sharedPreferences.getString(TAG_LEVEL, null);

        if (status) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            intent.putExtra(EMAIL, email);
            intent.putExtra(PASSWORD, password);
            finish();
            startActivity(intent);
        }


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = teEmail.getText().toString();
                final String password = tePassword.getText().toString();

                if (email.trim().length() > 0 && password.trim().length() > 0) {
                    if (conMgr.getActiveNetworkInfo() != null
                            && conMgr.getActiveNetworkInfo().isAvailable()
                            && conMgr.getActiveNetworkInfo().isConnected()) {
                        checkLogin(email, password);
                    } else {
                        Toast.makeText(getApplicationContext(), MESSAGE_NO_INTERNET_ACCESS, Toast.LENGTH_LONG).show();
                    }
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(), MESSAGE_CANNOT_BE_EMPTY, Toast.LENGTH_LONG).show();
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void checkLogin(final String email, final String password) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage(MESSAGE_LOGIN);
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, Urls.URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Login Response: " + response);
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {
                        String level = jObj.getString(TAG_LEVEL);
                        String email = jObj.getString(EMAIL);
                        String password = jObj.getString(PASSWORD);

                        Log.e("Successfully Login!", jObj.toString());

                        Toast.makeText(getApplicationContext(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        // menyimpan login ke session
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean(STATUS, true);
                        editor.putString(EMAIL, email);
                        editor.putString(PASSWORD, password);
                        editor.commit();

                        if (level.equals("admin")) {
                            Intent intent = new Intent(Login.this, AdminActivity.class);
                            intent.putExtra(EMAIL, email);
                            intent.putExtra(PASSWORD, password);
                            finish();
                            startActivity(intent);

                        } else {
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            intent.putExtra(EMAIL, email);
                            intent.putExtra(PASSWORD, password);
                            finish();
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}