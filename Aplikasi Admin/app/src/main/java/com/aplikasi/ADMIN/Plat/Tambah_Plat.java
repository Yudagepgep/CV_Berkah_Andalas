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

public class Tambah_Plat extends AppCompatActivity {
    EditText plt_id,plt_nama,plt_harga,plt_upah;
    Button kembali, tambah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_plat);

        plt_id = findViewById(R.id.tmb_plt_id_plat);
        plt_nama = findViewById(R.id.tmb_plt_nama_plat);
        plt_harga = findViewById(R.id.tmb_plt_harga_plat);
        plt_upah = findViewById(R.id.tmb_plt_upah_cetak);
        kembali = findViewById(R.id.kembali_plat);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tambah_Plat.this,Data_Plat.class);
                startActivity(intent);
            }
        });

        tambah = findViewById(R.id.tmb_plat);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambah_plat();
            }
        });
    }

    private void tambah_plat() {

        final  String tmb_id= plt_id.getText().toString();
        final  String tmb_nama= plt_nama.getText().toString();
        final  String tmb_harga= plt_harga.getText().toString();
        final  String tmb_upah= plt_upah.getText().toString();

        String url = "https://berkah-andalas06.000webhostapp.com/Plat/Tambah_Plat.php";

        StringRequest respon = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("Status");
                            if (status.equalsIgnoreCase("Berhasil DiTambahkan")) {
                                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(Tambah_Plat.this);
                                builder.setTitle("Sukses");
                                builder.setMessage("Data Sukses Ditambahkan");
                                builder.setPositiveButton("oke", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        startActivity(new Intent(Tambah_Plat.this, Data_Plat.class));
                                        finish();

                                    }
                                });
                                androidx.appcompat.app.AlertDialog dialog = builder.create();
                                dialog.show();
                            }else {
                                Toast.makeText(Tambah_Plat.this, "Data Gagal Ditambahkan" , Toast.LENGTH_LONG).show();

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Tambah_Plat.this, " Jaringan Tidak Ada \n Mohon Periksa Kembali Jaringan Anda" , Toast.LENGTH_LONG).show();


                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap();
                // jika id kosong maka simpan, jika id ada nilainya maka updat
                params.put("id_plat", tmb_id);
                params.put("nama_plat", tmb_nama);
                params.put("harga_plat", tmb_harga);
                params.put("upah_cetak", tmb_upah);




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
