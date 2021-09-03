package com.mafaa.kanoofood.admin_ui.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.mafaa.kanoofood.R;
import com.mafaa.kanoofood.Urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserFragment extends Fragment {

    private RecyclerView recyclerView;
    private UsersAdapater usersAdapater;
    private List<Users> usersList;

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_fragment_user, container, false);

        recyclerView = view.findViewById(R.id.RecycleList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(UserFragment.newInstance().getContext()));

        usersList = new ArrayList<>();
        LoadAllUsers();
        return view;
    }

    private void userRecycleList(View view) {
        recyclerView = view.findViewById(R.id.RecycleList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(UserFragment.newInstance().getContext()));

        usersList = new ArrayList<>();
        LoadAllUsers();
    }

    private void LoadAllUsers() {
        JsonArrayRequest request = new JsonArrayRequest(Urls.URL_USER, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray array) {
                for (int i = 0; i < array.length(); i++) {
                    try {
                        JSONObject object = array.getJSONObject(i);
                        String name = object.getString("name").trim();
                        String level = object.getString("level").trim();
                        String gender = object.getString("gender").trim();
                        String tgl = object.getString("tanggal_lahir").trim();
                        String no_hp = object.getString("no_hp").trim();
                        String email = object.getString("email").trim();

                        Users users = new Users();
                        users.setName(name);
                        users.setLevel(level);
                        users.setGender(gender);
                        users.setTgl(tgl);
                        users.setNo_hp(no_hp);
                        users.setEmail(email);
                        usersList.add(users);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                usersAdapater = new UsersAdapater(UserFragment.newInstance().getContext(), usersList);
                recyclerView.setAdapter(usersAdapater);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserFragment.newInstance().getContext(), error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(UserFragment.newInstance().getContext());
        requestQueue.add(request);
    }

}