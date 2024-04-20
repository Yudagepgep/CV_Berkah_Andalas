package com.aplikasi.ADMIN.Pemesanan_Costumer;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
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
import androidx.appcompat.app.AppCompatActivity;

public class Data_Pemesanan_Costumer extends AppCompatActivity {

    EditText txtvalue;
    Button btnfetch;
    ListView listview;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_pemesanan_costumer);
        txtvalue = (EditText) findViewById(R.id.editText);
        btnfetch = (Button) findViewById(R.id.buttonfetch);
        listview = (ListView) findViewById(R.id.listView_costumer);

        btnfetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
    }

    private void getData() {

        String value = txtvalue.getText().toString().trim();

        mProgressDialog = new ProgressDialog(Data_Pemesanan_Costumer.this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setProgress(0);
        mProgressDialog.setProgressNumberFormat(null);
        mProgressDialog.setProgressPercentFormat(null);
        mProgressDialog.show();

        if (value.equals("")) {
            Toast.makeText(this, "Mohon Masukan NAMA Terlebih Dahulu", Toast.LENGTH_LONG).show();
            return;
        }

        String url = config.MATCHDATA_URL + txtvalue.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                showJSON(response);
                mProgressDialog.dismiss();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Data_Pemesanan_Costumer.this, " Jaringan Tidak Ada \n Mohon Periksa Kembali Jaringan Anda" , Toast.LENGTH_LONG).show();

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void showJSON(String response) {
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(config.JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String tgl = jo.getString(config.KEY_TANGGAL);
                String cstm = jo.getString(config.KEY_COSTUMER);
                String produk = jo.getString(config.KEY_PRODUK);
                String bahan = jo.getString(config.KEY_BAHAN);
                String plat = jo.getString(config.KEY_PLAT);
                String quan = jo.getString(config.KEY_QUANTITY);
                String lb = jo.getString(config.KEY_LEBAR_BAHAN);
                String tb = jo.getString(config.KEY_TINGGI_BAHAN);
                String lc = jo.getString(config.KEY_LEBAR_CETAK);
                String tc = jo.getString(config.KEY_TINGGI_CETAK);
                String bh = jo.getString(config.KEY_BANYAK_BAHAN);
                String st = jo.getString(config.KEY_SATUAN);
                String th = jo.getString(config.KEY_TOTAL_HARGA);
                String stus = jo.getString(config.KEY_STATUS);
                String id = jo.getString(config.KEY_ID);


                final HashMap<String, String> employees = new HashMap<>();
                employees.put(config.KEY_TANGGAL, tgl);
                employees.put(config.KEY_COSTUMER, cstm);
                employees.put(config.KEY_PRODUK, produk);
                employees.put(config.KEY_BAHAN, bahan);
                employees.put(config.KEY_PLAT, plat);
                employees.put(config.KEY_QUANTITY, quan);
                employees.put(config.KEY_LEBAR_BAHAN, lb);
                employees.put(config.KEY_TINGGI_BAHAN, tb);
                employees.put(config.KEY_LEBAR_CETAK, lc);
                employees.put(config.KEY_TINGGI_CETAK, tc);
                employees.put(config.KEY_BANYAK_BAHAN, bh);
                employees.put(config.KEY_SATUAN, st);
                employees.put(config.KEY_TOTAL_HARGA, th);
                employees.put(config.KEY_STATUS, stus);

                employees.put(config.KEY_ID, id);

                list.add(employees);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListAdapter adapter = new SimpleAdapter(
                Data_Pemesanan_Costumer.this, list, R.layout.data_pemesanan_costumer_list,
                new String[]{config.KEY_TANGGAL, config.KEY_COSTUMER, config.KEY_PRODUK, config.KEY_BAHAN,config.KEY_PLAT,config.KEY_QUANTITY,
                        config.KEY_LEBAR_BAHAN,config.KEY_TINGGI_BAHAN,config.KEY_LEBAR_CETAK,config.KEY_TINGGI_CETAK, config.KEY_BANYAK_BAHAN, config.KEY_SATUAN, config.KEY_TOTAL_HARGA, config.KEY_STATUS, config.KEY_ID},
                new int[]{R.id.tanggal_pemesanan, R.id.costumer_pemesanan, R.id.produk_pemesanan,R.id.bahan_pemesanan,R.id.plat_pemesanan, R.id.quantity_pemesanan,  R.id.txt_lb_bahan,
                        R.id.txt_tg_bahan,R.id.txt_lb_cetak,R.id.txt_tg_cetak,  R.id.banyak_bahan_pemesanan, R.id.satuan_pemesanan, R.id.total_harga_pemesanan,R.id.status_pemesanan});

        listview.setAdapter(adapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         if (item.getItemId() == R.id.logout) {
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
        }
        return true;

    }

    }



