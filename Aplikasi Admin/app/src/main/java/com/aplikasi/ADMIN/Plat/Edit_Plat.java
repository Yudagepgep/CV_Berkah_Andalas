package com.aplikasi.ADMIN.Plat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Edit_Plat extends AppCompatActivity {
    EditText id,n_plat,h_plat,u_cetak;
    Button kembali,update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_plat);

        id= findViewById(R.id.edt_plt_id_plat);
        n_plat= findViewById(R.id.edt_plt_nama_plat);
        h_plat= findViewById(R.id.edt_plt_harga_plat);
        u_cetak= findViewById(R.id.edt_plt_upah_cetak);
        kembali = findViewById(R.id.kembali_plat);

        id.setText(getIntent().getStringExtra("id_plat"));
        n_plat.setText(getIntent().getStringExtra("nama"));
        h_plat.setText(getIntent().getStringExtra("harga"));
        u_cetak.setText(getIntent().getStringExtra("upah"));
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Edit_Plat.this,Data_Plat.class);
                startActivity(intent);
            }
        });

        update = findViewById(R.id.update_plat);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update_plat();
            }
        });

    }

    private void update_plat() {
        final String plat_id = id.getText().toString();
        final String nama_plat = n_plat.getText().toString();
        final String harga_plat = h_plat.getText().toString();
        final String upah_cetak = u_cetak.getText().toString();

        String url = "https://berkah-andalas06.000webhostapp.com/Plat/Edit_Plat.php";

        StringRequest respon = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("Status");
                            if (status.equalsIgnoreCase("Data terupdate")) {
                                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(Edit_Plat.this);
                                builder.setTitle("Sukses");
                                builder.setMessage("Data Sukses Update");
                                builder.setPositiveButton("oke", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        startActivity(new Intent(Edit_Plat.this, Data_Plat.class));
                                        finish();

                                    }
                                });
                                androidx.appcompat.app.AlertDialog dialog = builder.create();
                                dialog.show();
                            }else {
                                Toast.makeText(Edit_Plat.this, "Data Gagal Update" , Toast.LENGTH_LONG).show();

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Edit_Plat.this, " Jaringan Tidak Ada \n Mohon Periksa Kembali Jaringan Anda" , Toast.LENGTH_LONG).show();

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap();
                // jika id kosong maka simpan, jika id ada nilainya maka updat
                params.put("id_plat", plat_id);
                params.put("nama_plat", nama_plat);
                params.put("harga_plat", harga_plat);
                params.put("upah_cetak", upah_cetak);




                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(respon);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                new AlertDialog.Builder(this)
                        .setMessage("Apakah Anda Ingin Keluar?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                finish();

                            }
                        })
                        .setNegativeButton("Tidak", null)
                        .show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


}
