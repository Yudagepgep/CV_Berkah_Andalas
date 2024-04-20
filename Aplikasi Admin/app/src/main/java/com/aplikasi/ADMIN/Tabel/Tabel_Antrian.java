package com.aplikasi.ADMIN.Tabel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aplikasi.ADMIN.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class Tabel_Antrian extends AppCompatActivity {

    public static Context context;
    public static ArrayList<help_antrian> arrayListAntriant ;
    static ListView listView;
    private static adapter_antrian adapter;
    private static ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabel__antrian);

        context=this;
        listView = findViewById(R.id.list_antrian);
        arrayListAntriant = new ArrayList<>();
        adapter = new adapter_antrian(Tabel_Antrian.this, arrayListAntriant);
        Tampil_Antrian();
    }

    private void Tampil_Antrian() {

        mProgressDialog = new ProgressDialog( context);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setProgress(0);
        mProgressDialog.setProgressNumberFormat(null);
        mProgressDialog.setProgressPercentFormat(null);
        mProgressDialog.show();
        String URL_PRODUCTS = "https://berkah-andalas06.000webhostapp.com/Laporan_Antrian/Data_Antrian.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        arrayListAntriant.clear();
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Hasil");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject antrian = jsonArray.getJSONObject(i);
                                arrayListAntriant.add(new help_antrian(
                                        antrian.getString("id_pesanan"),
                                        antrian.getString("id_user"),
                                        antrian.getString("id_produk"),
                                        antrian.getString("tanggal"),
                                        antrian.getString("quantity"),
                                        antrian.getString("banyak_bahan"),
                                        antrian.getString("laba"),
                                        antrian.getString("total_harga"),
                                        antrian.getString("penanggung_jawab"),
                                        antrian.getString("status")


                                ));


                                listView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                mProgressDialog.dismiss();
                            }




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, " Jaringan Tidak Ada \n Mohon Periksa Kembali Jaringan Anda" , Toast.LENGTH_LONG).show();


                    }
                });

        Volley.newRequestQueue(context).add(stringRequest);
    }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_toolbar, menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                final ArrayList<help_antrian> filterdataList=filter(arrayListAntriant,s);
                adapter.setfilter(filterdataList);
                return true;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected( MenuItem item) {

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

    private ArrayList<help_antrian> filter(ArrayList<help_antrian>hi, String s){
        s=s.toLowerCase();
        final ArrayList<help_antrian>filterdataList=new ArrayList<>();
        for (help_antrian data:hi){
            final String text=data.getUser().toLowerCase();
            if (text.startsWith(s)){
                filterdataList.add(data);
            }
        }
        return filterdataList;
    }
    }

