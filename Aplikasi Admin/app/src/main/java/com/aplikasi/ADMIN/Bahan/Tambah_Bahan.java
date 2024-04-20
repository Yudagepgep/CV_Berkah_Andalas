package com.aplikasi.ADMIN.Bahan;

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
import com.aplikasi.ADMIN.input.INPUT_PESANAN;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Tambah_Bahan extends AppCompatActivity {
    EditText tb_id,tb_nama,tb_tinngi,tb_lebar,tb_harga,tb_satuan;
    Button tmb,kmb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_bahan);

        tb_id = findViewById(R.id.tmb_id_bhn);
        tb_nama = findViewById(R.id.tmb_nama_bhn);
        tb_tinngi = findViewById(R.id.tmb_tinggi_bhn);
        tb_lebar = findViewById(R.id.tmb_lebar_bhn);
        tb_harga = findViewById(R.id.tmb_harga_bhn);
        tb_satuan = findViewById(R.id.tmb_satuan_bhn);

        tmb=findViewById(R.id.tmb_bahan);
        kmb= findViewById(R.id.kembali_bahan);
        kmb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tambah_Bahan.this,Data_Bahan.class);
                startActivity(intent);
            }
        });

        tmb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambah_bahan();
            }
        });
    }

    private void tambah_bahan() {
        final  String tmb_id= tb_id.getText().toString();
        final  String tmb_nama= tb_nama.getText().toString();
        final  String tmb_tinggi= tb_tinngi.getText().toString();
        final  String tmb_lebar= tb_lebar.getText().toString();
        final  String tmb_harga= tb_harga.getText().toString();
        final  String tmb_satuan= tb_satuan.getText().toString();

        String url = "https://berkah-andalas06.000webhostapp.com/Bahan/Tambah_Bahan.php";

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
                                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(Tambah_Bahan.this);
                                builder.setTitle("Sukses");
                                builder.setMessage("Data Sukses Ditambahkan");
                                builder.setPositiveButton("oke", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        startActivity(new Intent(Tambah_Bahan.this, Data_Bahan.class));
                                        finish();

                                    }
                                });
                                androidx.appcompat.app.AlertDialog dialog = builder.create();
                                dialog.show();
                            }else {
                                Toast.makeText(Tambah_Bahan.this, "Data Gagal Ditambahkan" , Toast.LENGTH_LONG).show();

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Tambah_Bahan.this, " Jaringan Tidak Ada \n Mohon Periksa Kembali Jaringan Anda" , Toast.LENGTH_LONG).show();

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap();
                // jika id kosong maka simpan, jika id ada nilainya maka updat
                params.put("id_bahan", tmb_id);
                params.put("nama_bahan", tmb_nama);
                params.put("tinggi_bahan", tmb_tinggi);
                params.put("lebar_bahan", tmb_lebar);
                params.put("harga_bahan",tmb_harga);
                params.put("satuan", tmb_satuan);



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
