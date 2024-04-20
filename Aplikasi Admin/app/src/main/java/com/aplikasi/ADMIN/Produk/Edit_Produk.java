package com.aplikasi.ADMIN.Produk;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aplikasi.ADMIN.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import androidx.appcompat.app.AppCompatActivity;

public class Edit_Produk extends AppCompatActivity {
    Spinner bahan, plat;
    EditText id, nama, tinggi, lebar;
    Button kembali_produk, update_produk;
    ArrayList<String> id_bahan = new ArrayList<>();
    ArrayList<String> id_plat = new ArrayList<>();
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_produk);

        id = findViewById(R.id.edt_id_produk);
        nama = findViewById(R.id.edt_nama_produk);
        tinggi = findViewById(R.id.edt_tinggi_cetak);
        lebar = findViewById(R.id.edt_Lebar_Cetak);
        bahan = findViewById(R.id.id_bahan);
        plat = findViewById(R.id.id_plat);

        context = this;
        id.setText(getIntent().getStringExtra("id_produk"));
        nama.setText(getIntent().getStringExtra("nama"));
        tinggi.setText(getIntent().getStringExtra("tinggi"));
        lebar.setText(getIntent().getStringExtra("lebar"));
        bahan.setSelected(Boolean.parseBoolean(getIntent().getStringExtra("bahan")));
        plat.setSelected(Boolean.parseBoolean(getIntent().getStringExtra("plat")));

        kembali_produk = (Button) findViewById(R.id.kembali_produk);
        kembali_produk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Edit_Produk.this, Data_Produk.class);
                startActivity(intent);
            }
        });
        update_produk = (Button) findViewById(R.id.update_produk);

        update_produk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpan_update_produk();
            }
        });

        id_bahan();
        id_plat();

    }


    private void id_bahan() {
        String URL_PRODUCTS = "https://berkah-andalas06.000webhostapp.com/Bahan/id_bahan.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Hasil");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);

                                String id = object.getString("id_bahan");

                                id_bahan.add(id);

                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, id_bahan);
                            bahan.setAdapter(arrayAdapter);




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        Volley.newRequestQueue(context).add(stringRequest);
    }


    private void id_plat() {

        String URL_PRODUCTS = "https://berkah-andalas06.000webhostapp.com/Plat/id_plat.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Hasil");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);

                                String id = object.getString("id_plat");

                                id_plat.add(id);

                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, id_plat);
                            plat.setAdapter(arrayAdapter);




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        Volley.newRequestQueue(context).add(stringRequest);

    }
    private void simpan_update_produk() {

        final String produk_id = id.getText().toString();
        final String nama_produk = nama.getText().toString();
        final String tinggi_cetak = tinggi.getText().toString();
        final String lebar_cetak = lebar.getText().toString();
        final String plat_id = plat.getSelectedItem().toString();
        final String bahan_id = bahan.getSelectedItem().toString();


        String url = "https://berkah-andalas06.000webhostapp.com/Produk/Edit_Produk.php";

        StringRequest respon = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("Status");
                            if (status.equalsIgnoreCase("Data terupdate ")) {
                                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(Edit_Produk.this);
                                builder.setTitle("Sukses");
                                builder.setMessage("Data Sukses Update");
                                builder.setPositiveButton("oke", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        startActivity(new Intent(Edit_Produk.this, Data_Produk.class));
                                        finish();

                                    }
                                });
                                androidx.appcompat.app.AlertDialog dialog = builder.create();
                                dialog.show();
                            }else {
                                Toast.makeText(Edit_Produk.this, "Data Gagal Update" , Toast.LENGTH_LONG).show();

                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Edit_Produk.this, " Jaringan Tidak Ada \n Mohon Periksa Kembali Jaringan Anda" , Toast.LENGTH_LONG).show();

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap();
                // jika id kosong maka simpan, jika id ada nilainya maka updat
                params.put("id_produk", produk_id);
                params.put("nama_produk", nama_produk);
                params.put("tinggi_cetak", tinggi_cetak);
                params.put("lebar_cetak", lebar_cetak);
                params.put("id_bahan", bahan_id);
                params.put("id_plat", plat_id);



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
