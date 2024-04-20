package com.aplikasi.ADMIN.LOGIN;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aplikasi.ADMIN.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    ProgressBar loading;
    Button btn_register, login;
    EditText email,password,c_password,nama,instan,hp,lev;

    private String url = "https://berkah-andalas06.000webhostapp.com/User/Register.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loading = findViewById(R.id.loading);
        login = findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);
        nama = findViewById(R.id.nama);
        instan = findViewById(R.id.instansi);
        hp = findViewById(R.id.handphone);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.pass);
        c_password = (EditText) findViewById(R.id.confirm_pass);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                finish();
                startActivity(intent);
            }
        });


        btn_register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Register();

            }
        });

    }

    private void Register() {
        loading.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
        final String nm_lengkap = this.nama.getText().toString().trim();
        final String organisasi = this.instan.getText().toString().trim();
        final String hand = this.hp.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String password = this.password.getText().toString().trim();

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                    // Check for error node in json
                    if (success.equals("1")) {


                        Toast.makeText(Register.this,"Register Berhasil", Toast.LENGTH_LONG).show();


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Register.this,"Register Gagal", Toast.LENGTH_LONG).show();
                    loading.setVisibility(View.GONE);
                    btn_register.setVisibility(View.VISIBLE);

                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Register.this,"Jaringan Tidak Ada \n Periksa Kembali Jaringan Anda", Toast.LENGTH_LONG).show();
                loading.setVisibility(View.GONE);
                btn_register.setVisibility(View.VISIBLE);
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("nama_lengkap", nm_lengkap);
                params.put("instansi_organisasi", organisasi);
                params.put("email_user", email);
                params.put("password_user", password);
                params.put("no_handphone", hand);

                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(strReq);

    }

}
