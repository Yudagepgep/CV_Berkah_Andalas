package com.aplikasi.ADMIN.Bahan;

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import androidx.appcompat.app.AppCompatActivity;

public class Data_Bahan extends AppCompatActivity {
    public static Context context;
    public static ArrayList<help_bahan> arrayListBahan ;
    static ListView listView;
    private static adapter_bahan adapter;
    private static ProgressDialog mProgressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_bahan);




        context=this;
        listView = findViewById(R.id.list_bahan);
        arrayListBahan = new ArrayList<>();
        adapter = new adapter_bahan(Data_Bahan.this, arrayListBahan);
        Tampil_Bahan();

    }

    private static void Tampil_Bahan() {

        mProgressDialog = new ProgressDialog( context);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setProgress(0);
        mProgressDialog.setProgressNumberFormat(null);
        mProgressDialog.setProgressPercentFormat(null);
        mProgressDialog.show();

        String URL_PRODUCTS = "https://berkah-andalas06.000webhostapp.com/Bahan/Tampil_Bahan.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        arrayListBahan.clear();
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Hasil");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject data = jsonArray.getJSONObject(i);
                                arrayListBahan.add(new help_bahan(

                                        data.getString("id_bahan"),
                                        data.getString("nama_bahan"),
                                        data.getString("tinggi_bahan"),
                                        data.getString("lebar_bahan"),
                                        data.getString("harga_bahan"),
                                        data.getString("satuan")


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
    public static void DeletBahan(final String id_bahan) {
        StringRequest request = new StringRequest(Request.Method.POST, "https://berkah-andalas06.000webhostapp.com/Bahan/Hapus_Bahan.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.equalsIgnoreCase("Berhasil Terhapus")) {
                            Toast.makeText(context, "Data Berhasil DiHapus", Toast.LENGTH_SHORT).show();
                            Tampil_Bahan();


                        } else {
                            Toast.makeText(context, "Data Gagal DiHapus", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap();
                params.put("id_bahan", id_bahan);
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
        menuInflater.inflate(R.menu.tambah_bahan, menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                final ArrayList<help_bahan> filterdataList=filter(arrayListBahan,s);
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
        }else if (item.getItemId() == R.id.tmb_data_bahan){
            startActivity(new Intent(this, Tambah_Bahan.class));
        }

        return true;
    }

    private ArrayList<help_bahan> filter(ArrayList<help_bahan>hi, String s){
        s=s.toLowerCase();
        final ArrayList<help_bahan>filterdataList=new ArrayList<>();
        for (help_bahan data:hi){
            final String text=data.getNama_bahan().toLowerCase();
            if (text.startsWith(s)){
                filterdataList.add(data);
            }
        }
        return filterdataList;
    }




}
