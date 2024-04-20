package com.aplikasi.ADMIN.Tabel;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aplikasi.ADMIN.Produk.Edit_Produk;
import com.aplikasi.ADMIN.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Edit_Data_Antrian extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormat;
    Spinner prdk;
    Spinner user;
    EditText tgl, quantity,
             jawab, lb_ctk, tg_ctk, lb_ar, tg_ar, lb,st;
    Button back, simpan;

    ArrayList<String> data_user = new ArrayList<>();
    ArrayList<String> produk = new ArrayList<>();

    Context context;
    int bahan;
    private int position;
    private JSONArray result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__data__antrian);



        context = this;
        lb_ctk = (EditText) findViewById(R.id.lbr_ctk);
        tg_ctk = (EditText) findViewById(R.id.tg_ctk);
        lb_ar = (EditText) findViewById(R.id.lbr_area);
        tg_ar = (EditText) findViewById(R.id.tg_area);
        user = findViewById(R.id.id_user);
        prdk = findViewById(R.id.id_produk);
        tgl = (EditText) findViewById(R.id.tanggal);
        quantity = (EditText) findViewById(R.id.quantity);
        jawab = findViewById(R.id.input_jwb);
        lb = findViewById(R.id.laba);
        st = findViewById(R.id.input_sts);

        prdk.setOnItemSelectedListener(this);
        user.setSelected(Boolean.parseBoolean(getIntent().getStringExtra("id_user")));
        prdk.setSelected(Boolean.parseBoolean(getIntent().getStringExtra("id_produk")));
        tgl.setText(getIntent().getStringExtra("tanggal"));
        quantity.setText(getIntent().getStringExtra("quantity"));
        lb.setText(getIntent().getStringExtra("laba"));
        jawab.setText(getIntent().getStringExtra("jawab"));
        st.setText(getIntent().getStringExtra("status"));



        back = (Button) findViewById(R.id.kembali_update);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Edit_Data_Antrian.this, Tabel_Antrian.class);
                startActivity(intent);
            }
        });
        simpan = (Button) findViewById(R.id.simpan_update);

        pesanan();
        showdata();

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpan_pesan();
            }
        });

        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        tgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });
    }

    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayofMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayofMonth);
                tgl.setText(dateFormat.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();


    }


    private void pesanan() {

        String URL_PRODUCTS = "https://berkah-andalas06.000webhostapp.com/User/nama_user.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Hasil");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);

                                String id = object.getString("id_user");

                                data_user.add(id);

                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, data_user);
                            user.setAdapter(arrayAdapter);




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


    private void showdata() {

        String URL_PRODUCTS = "https://berkah-andalas06.000webhostapp.com/Produk/Tampil_Produk.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            result = j.getJSONArray("Hasil");

                            //Calling method getStudents to get the students from the JSON Array
                            getProduk(result);

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
    private void getProduk(JSONArray j) {
        for(int i=0;i<j.length();i++){
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                produk.add(json.getString("id_produk"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        prdk.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, produk));

    }

    private String getTinggi_bahan(int position){
        String tinggi_bahan="";
        try {
            //Getting object of given index
            JSONObject json = result.getJSONObject(position);

            //Fetching name from that object
            tinggi_bahan = json.getString("tinggi_bahan");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return tinggi_bahan;
    }
    private String getLebar_bahan(int position){
        String lebar_bahan="";
        try {
            //Getting object of given index
            JSONObject json = result.getJSONObject(position);

            //Fetching name from that object
            lebar_bahan = json.getString("lebar_bahan");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return lebar_bahan;
    }
    private String getTinggi_cetak(int position){
        String tinggi_cetak="";
        try {
            //Getting object of given index
            JSONObject json = result.getJSONObject(position);

            //Fetching name from that object
            tinggi_cetak = json.getString("tinggi_cetak");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return tinggi_cetak;
    }
    private String getLebar_cetak(int position){
        String lebar_cetak="";
        try {
            //Getting object of given index
            JSONObject json = result.getJSONObject(position);

            //Fetching name from that object
            lebar_cetak = json.getString("lebar_cetak");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return lebar_cetak;
    }


    private void simpan_pesan() {

        int Lebar_Cetakan = Integer.parseInt(lb_ar.getText().toString());
        int Tinggi_Cetakan = Integer.parseInt(tg_ar.getText().toString());
        int Lebar_Bahan_Baku = Integer.parseInt(lb_ctk.getText().toString());
        int Tinggi_Bahan_Baku = Integer.parseInt(tg_ctk.getText().toString());
        int fil1;
        int fil2;
        int fil3;
        int fil4;
        int k2;
        int kl2;
        int b2;
        int b3;
        int b4;
        int hasil3;
        int kl1;
        int bagi3;
        int k;
        int hasil4;

        //Ini Adalah filter Pertama
        fil1 = (Tinggi_Bahan_Baku / Tinggi_Cetakan) * (Lebar_Bahan_Baku / Lebar_Cetakan);

        //Ini Adalah Filter Kedua
        fil2 = (Tinggi_Bahan_Baku / Lebar_Cetakan) * (Tinggi_Cetakan / Lebar_Bahan_Baku);

        //Ini Adalah Filter Ketiga
        fil3 = (Lebar_Bahan_Baku / Lebar_Cetakan);
        k = (Tinggi_Bahan_Baku - Tinggi_Cetakan);
        b2 = (k / Lebar_Cetakan);
        bagi3 = (Lebar_Bahan_Baku / Tinggi_Cetakan);
        kl1 = (b2 * bagi3);
        hasil3 = (fil3 + kl1);

        //ini Adalah Filter Kempat
        fil4 = (Lebar_Bahan_Baku / Tinggi_Cetakan);
        k2 = (Tinggi_Bahan_Baku - Lebar_Cetakan);
        b3 = (k2 / Tinggi_Cetakan);
        b4 = (Lebar_Bahan_Baku / Lebar_Cetakan);
        kl2 = (b3 * b4);
        hasil4 = (fil4 + kl2);

        //Untuk Mencari Nilai Terbesar Dari Semua Filter Untuk Banyak Bahan
        if (fil1 >= fil2 && fil1 >= hasil3 && fil1 >= hasil4) {
            bahan = fil1;
        } else if (fil2 >= fil1 && fil2 >= hasil3 && fil2 >= hasil4) {
            bahan = fil2;
        } else if (hasil3 >= fil1 && hasil3 >= fil2 && hasil3 >= hasil4) {
            bahan = hasil3;
        } else if (hasil4 >= fil1 && hasil4 >= fil2 && hasil4 >= hasil3) {
            bahan = hasil4;
        } else {
            System.out.println("error");
        }
        final String banyak_bahan = String.valueOf(bahan);

        final String id_user = user.getSelectedItem().toString();
        final String id_produk = prdk.getSelectedItem().toString();
        final String tanggal = tgl.getText().toString();
        final String quan = quantity.getText().toString();
        final String penanggung_jawab = jawab.getText().toString();
        final String laba = lb.getText().toString();
        final String stus = st.getText().toString();

        String url = "https://berkah-andalas06.000webhostapp.com/Laporan_Antrian/Update_Antrian.php";

        StringRequest respon = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("Status");
                            if (status.equalsIgnoreCase("Data tersimpan")) {
                                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(Edit_Data_Antrian.this);
                                builder.setTitle("Sukses");
                                builder.setMessage("Data Sukses Update");
                                builder.setPositiveButton("oke", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        startActivity(new Intent(Edit_Data_Antrian.this, Tabel_Antrian.class));
                                        finish();

                                    }
                                });
                                androidx.appcompat.app.AlertDialog dialog = builder.create();
                                dialog.show();
                            }else {
                                Toast.makeText(Edit_Data_Antrian.this, "Data Gagal Update" , Toast.LENGTH_LONG).show();

                            }


                            } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Edit_Data_Antrian.this, " Jaringan Tidak Ada \n Mohon Periksa Kembali Jaringan Anda" , Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap();
                // jika id kosong maka simpan, jika id ada nilainya maka updat
                params.put("id_pesanan", getIntent().getStringExtra("id"));
                params.put("id_user", id_user);
                params.put("id_produk", id_produk);
                params.put("tanggal", tanggal);
                params.put("quantity", quan);
                params.put("banyak_bahan", banyak_bahan);
                params.put("laba", laba);
                params.put("penanggung_jawab", penanggung_jawab);
                params.put("status", stus);


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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        lb_ctk.setText(getLebar_bahan(position));
        tg_ctk.setText(getTinggi_bahan(position));
        lb_ar.setText(getLebar_cetak(position));
        tg_ar.setText(getTinggi_cetak(position));

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

        lb_ctk.setText("");
        tg_ctk.setText("");
        lb_ar.setText("");
        tg_ar.setText("");

    }
}

