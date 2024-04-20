package com.aplikasi.ADMIN.Plat;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aplikasi.ADMIN.R;
import com.aplikasi.ADMIN.input.INPUT_PESANAN;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import androidx.appcompat.app.AppCompatActivity;

public class Data_Plat extends AppCompatActivity {

    public static Context context;
    public static ArrayList<help_plat> arrayListPlat ;
    static ListView listView;
    private static adapter_plat adapter;
    private static ProgressDialog mProgressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_plat);

        context=this;
        listView = findViewById(R.id.list_plat);
        arrayListPlat = new ArrayList<>();
        adapter = new adapter_plat(Data_Plat.this, arrayListPlat);
        Tampil_Plat();

    }

    private static void Tampil_Plat() {
        mProgressDialog = new ProgressDialog( context);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setProgress(0);
        mProgressDialog.setProgressNumberFormat(null);
        mProgressDialog.setProgressPercentFormat(null);
        mProgressDialog.show();

        String URL_PRODUCTS = "https://berkah-andalas06.000webhostapp.com/Plat/Tampil_Plat.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        arrayListPlat.clear();
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Hasil");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject data = jsonArray.getJSONObject(i);
                                arrayListPlat.add(new help_plat(

                                        data.getString("id_plat"),
                                        data.getString("nama_plat"),
                                        data.getString("harga_plat"),
                                        data.getString("upah_cetak")


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
    public static void deletPlat(final String id_plat) {
        StringRequest request = new StringRequest(Request.Method.POST, "https://berkah-andalas06.000webhostapp.com/Plat/Hapus_Plat.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.equalsIgnoreCase("Berhasil Terhapus")) {
                            Toast.makeText(context, "Data Berhasil DiHapus", Toast.LENGTH_SHORT).show();
                            Tampil_Plat();


                        } else {
                            Toast.makeText(context, "Data Gagal DiHapus", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, " Jaringan Tidak Ada \n Mohon Periksa Kembali Jaringan Anda" , Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap();
                params.put("id_plat", id_plat);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
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
        menuInflater.inflate(R.menu.tambah_plat, menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                final ArrayList<help_plat> filterdataList=filter(arrayListPlat,s);
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
        }else if (item.getItemId() == R.id.tmb_data_plat){
            startActivity(new Intent(this, Tambah_Plat.class));
        }

        return true;
    }

    private ArrayList<help_plat> filter(ArrayList<help_plat>hi, String s){
        s=s.toLowerCase();
        final ArrayList<help_plat>filterdataList=new ArrayList<>();
        for (help_plat data:hi){
            final String text=data.getNama_plat().toLowerCase();
            if (text.startsWith(s)){
                filterdataList.add(data);
            }
        }
        return filterdataList;
    }
}
