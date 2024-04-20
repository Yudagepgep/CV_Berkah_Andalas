package com.aplikasi.ADMIN.ANTRIAN;

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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Simpan_Antrian extends AppCompatActivity {

    Spinner id;
    Button  simpan;
    ArrayList<String> data_pesanan = new ArrayList<>();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simpan_antrian);
        context = this;

        id =  findViewById(R.id.smp_id_pesanan);



        id.setSelected(Boolean.parseBoolean(getIntent().getStringExtra("id")));





        simpan = (Button) findViewById(R.id.kirim);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpan_pesanan();
            }
        });
        ID_Pesanan();
    }

    private void ID_Pesanan() {
        String URL_PRODUCTS = "https://berkah-andalas06.000webhostapp.com/Laporan_Antrian/Tampil_ID_Pesanan.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Hasil");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);

                                String id = object.getString("id_pesanan");

                                data_pesanan.add(id);

                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, data_pesanan);
                            id.setAdapter(arrayAdapter);




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

    private void simpan_pesanan() {

        final String id_pesanan = id.getSelectedItem().toString();
        String url="https://berkah-andalas06.000webhostapp.com/Laporan_Antrian/Simpan_Antrian.php";

        StringRequest respon=new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String status=jsonObject.getString("Status");
                            if (status.equals("Data tersimpan"))
                            {
                                AlertDialog.Builder builder=new AlertDialog.Builder(Simpan_Antrian.this);
                                builder.setTitle("Sukses");
                                builder.setMessage("Data Sukses Tersimpan");
                                builder.setPositiveButton("oke", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        startActivity(new Intent(Simpan_Antrian.this,Data_Antrian.class));
                                        finish();

                                    }
                                });
                                AlertDialog dialog=builder.create();
                                dialog.show();

                            }else {
                                Toast.makeText(Simpan_Antrian.this, "Data Gagal Tersimpan" , Toast.LENGTH_LONG).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Simpan_Antrian.this, " Jaringan Tidak Ada \n Mohon Periksa Kembali Jaringan Anda" , Toast.LENGTH_LONG).show();

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap();
                // jika id kosong maka simpan, jika id ada nilainya maka update
                params.put("id_pesanan", id_pesanan);






                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(respon);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                new android.app.AlertDialog.Builder(this)
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

