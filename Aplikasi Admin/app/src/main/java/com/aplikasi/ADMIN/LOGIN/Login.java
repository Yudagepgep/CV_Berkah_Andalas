package com.aplikasi.ADMIN.LOGIN;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aplikasi.ADMIN.MENU;
import com.aplikasi.ADMIN.Pemesanan_Costumer.Input_Pemesanan;
import com.aplikasi.ADMIN.Pemesanan_Costumer.MENU_COSTUMER;
import com.aplikasi.ADMIN.Pemesanan_Costumer.Pemesanan;
import com.aplikasi.ADMIN.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    Button btn_register, btn_login;
    EditText email, password;
    ProgressBar loading;

    private String url ="https://berkah-andalas06.000webhostapp.com/Admin/Login.php";
    private String user ="https://berkah-andalas06.000webhostapp.com/User/Login.php";

    String[] menu = {"Pilih", "Admin",  "Costumer"};
    Spinner s11;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);
        email = (EditText) findViewById(R.id.txt_email);
        password = (EditText) findViewById(R.id.txt_password);
        loading = findViewById(R.id.loading);

        s11 = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu);
        s11.setAdapter(adapter);


        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int index = s11.getSelectedItemPosition();
                if (menu[index] == "Admin") {
                    String mEmail = email.getText().toString();
                    String mPassword = password.getText().toString();

                    // mengecek kolom yang kosong
                    if (!mEmail.isEmpty() || !mPassword.isEmpty()) {
                        login_Admin(mEmail, mPassword);
                    } else {
                        email.setError("Mohon Masukan Email");
                        password.setError("Mohon Masukan Password");
                    }
                }else if (menu[index] == "Costumer"){
                    String mEmail = email.getText().toString();
                    String mPassword = password.getText().toString();

                    // mengecek kolom yang kosong
                    if (!mEmail.isEmpty() || !mPassword.isEmpty()) {
                        login_user(mEmail, mPassword);
                    } else {
                        email.setError("Mohon Masukan Email");
                        password.setError("Mohon Masukan Password");
                    }
                }
      }


        });


        btn_register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Login.this, Register.class);
                finish();
                startActivity(intent);
            }
        });

    }

    private void login_user(final String mEmail, final String mPassword) {
        loading.setVisibility(View.VISIBLE);

        StringRequest strReq = new StringRequest(Request.Method.POST, user, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("login");

                    if (success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject object = jsonArray.getJSONObject(i);

                            String id = object.getString("id_user").trim();
                            Intent intent = new Intent(Login.this, MENU_COSTUMER.class);
                            intent.putExtra("id_user",id);
                            startActivity(intent);

                            loading.setVisibility(View.GONE);

                        }
                    }else {
                        Toast.makeText(Login.this, "Login Gagal \n Email Atau Password Anda Salah" , Toast.LENGTH_LONG).show();
                        loading.setVisibility(View.GONE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Login.this, "Login Gagal \n Mohon Buat Akun Baru \n Dengan Email Berbeda \n" , Toast.LENGTH_LONG).show();
                    loading.setVisibility(View.GONE);
                    btn_login.setVisibility(View.VISIBLE);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this, " Jaringan Tidak Ada \n Periksa Kembali Jaringan Anda" , Toast.LENGTH_LONG).show();
                loading.setVisibility(View.GONE);
                btn_login.setVisibility(View.VISIBLE);

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email_user", mEmail);
                params.put("password_user", mPassword);

                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(strReq);

    }


    private void login_Admin(final String mEmail, final String mPassword) {
        loading.setVisibility(View.VISIBLE);

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("login");

                    if (success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject object = jsonArray.getJSONObject(i);



                            Intent intent = new Intent(Login.this, MENU.class);
                            startActivity(intent);

                            loading.setVisibility(View.GONE);

                        }
                   }else {
                        Toast.makeText(Login.this, "Login Gagal \n Email Atau Password Anda Salah" , Toast.LENGTH_LONG).show();
                        loading.setVisibility(View.GONE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Login.this, "Login Gagal \n Mohon Buat Akun Baru \n Dengan Email Berbeda ", Toast.LENGTH_LONG).show();
                    loading.setVisibility(View.GONE);
                    btn_login.setVisibility(View.VISIBLE);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this, " Jaringan Tidak Ada \n Mohon Periksa Kembali Jaringan Anda" , Toast.LENGTH_LONG).show();
                loading.setVisibility(View.GONE);
                btn_login.setVisibility(View.VISIBLE);

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", mEmail);
                params.put("password", mPassword);

                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(strReq);

    }

}
