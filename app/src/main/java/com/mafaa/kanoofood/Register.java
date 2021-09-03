package com.mafaa.kanoofood;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;

public class Register extends AppCompatActivity {

    TextInputEditText teName, teNumber, teEmail, tePassword;
    RadioGroup rgGender;
    RadioButton rbLaki, rbPerempuan, rbGender;
    TextView tvLogin, tvTgl_Lahir;
    Button btnRegister;
    ProgressBar progressBar;
    String date;

    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        teName = findViewById(R.id.nama);
        teNumber = findViewById(R.id.nomor);
        teEmail = findViewById(R.id.email);
        tePassword = findViewById(R.id.password);
        rgGender = findViewById(R.id.gender);
        rbLaki = findViewById(R.id.btn_laki);
        rbPerempuan = findViewById(R.id.btn_perempuan);
        btnRegister = findViewById(R.id.btn_signup);
        progressBar = findViewById(R.id.progress);
        tvTgl_Lahir = findViewById(R.id.tgl_lahir);
        tvLogin = findViewById(R.id.tv_login);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        tvTgl_Lahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Register.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                date = day + "/" + month + "/" + year;
                tvTgl_Lahir.setText(date);
            }
        };

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int idradio = rgGender.getCheckedRadioButtonId();
                rbGender = findViewById(idradio);

                final String nama = teName.getText().toString();
                final String nomor = teNumber.getText().toString();
                final String gender = rbGender.getText().toString();
                final String tgl = tvTgl_Lahir.getText().toString();
                final String email = teEmail.getText().toString();
                final String password = tePassword.getText().toString();

                if (nama.isEmpty() || nomor.isEmpty() || gender.isEmpty() || tgl.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Register.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    class Login extends AsyncTask<Void, Void, String> {
                        final ProgressDialog pdLoading = new ProgressDialog(Register.this);

                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();

                            //this method will be running on UI thread
                            pdLoading.setMessage("\tLoading...");
                            pdLoading.setCancelable(false);
                            pdLoading.show();
                        }

                        @Override
                        protected String doInBackground(Void... voids) {
                            //creating request handler object
                            RequestHandler requestHandler = new RequestHandler();

                            //creating request parameters
                            HashMap<String, String> params = new HashMap<>();
                            params.put("name", nama);
                            params.put("no_hp", nomor);
                            params.put("gender", gender);
                            params.put("tanggal_lahir", tgl);
                            params.put("email", email);
                            params.put("password", password);


                            //returing the response
                            return requestHandler.sendPostRequest(Urls.URL_REGISTER, params);
                        }

                        @Override
                        protected void onPostExecute(String s) {
                            super.onPostExecute(s);
                            pdLoading.dismiss();

                            try {
                                //converting response to json object
                                JSONObject obj = new JSONObject(s);
                                //if no error in response
                                if (!obj.getBoolean("error")) {
                                    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(Register.this, "Exception: " + e, Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    Login login = new Login();
                    login.execute();
                }

            }
        });

    }
}