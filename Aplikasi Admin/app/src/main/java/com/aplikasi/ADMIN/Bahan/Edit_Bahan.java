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
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import androidx.appcompat.app.AppCompatActivity;

public class Edit_Bahan extends AppCompatActivity {
EditText id,n_bhn,t_bhn,l_bhn,h_bhn,st;
Button kembali,update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_bahan);

        id = findViewById(R.id.edt_bhn_id_bahan);
        n_bhn = findViewById(R.id.edt_bhn_nama_bahan);
        t_bhn = findViewById(R.id.edt_bhn_tinggi_bahan);
        l_bhn = findViewById(R.id.edt_bhn_lebar_bahan);
        h_bhn = findViewById(R.id.edt_bhn_harga_bahan);
        st = findViewById(R.id.edt_bhn_satuan);

        id.setText(getIntent().getStringExtra("id_bahan"));
        n_bhn.setText(getIntent().getStringExtra("nama"));
        t_bhn.setText(getIntent().getStringExtra("tinggi"));
        l_bhn.setText(getIntent().getStringExtra("lebar"));
        h_bhn.setText(getIntent().getStringExtra("harga"));
        st.setText(getIntent().getStringExtra("satuan"));

        kembali = findViewById(R.id.kembali_bahan);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Edit_Bahan.this,Data_Bahan.class);
                startActivity(intent);
            }
        });

        update = findViewById(R.id.update_bahan);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update_bahan();
            }
        });


    }

    private void update_bahan() {
        final String bahan_id = id.getText().toString();
        final String nama_bahan = n_bhn.getText().toString();
        final String tinggi_bahan = t_bhn.getText().toString();
        final String lebar_bahan = l_bhn.getText().toString();
        final String harga_bahan = h_bhn.getText().toString();
        final String satuan = st.getText().toString();



        String url = "https://berkah-andalas06.000webhostapp.com/Bahan/Edit_bahan.php";

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
                                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(Edit_Bahan.this);
                                builder.setTitle("Sukses");
                                builder.setMessage("Data Sukses Update");
                                builder.setPositiveButton("oke", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        startActivity(new Intent(Edit_Bahan.this, Data_Bahan.class));
                                        finish();

                                    }
                                });
                                androidx.appcompat.app.AlertDialog dialog = builder.create();
                                dialog.show();
                            }else {
                                Toast.makeText(Edit_Bahan.this, "Data Gagal Update" , Toast.LENGTH_LONG).show();

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Edit_Bahan.this, " Jaringan Tidak Ada \n Mohon Periksa Kembali Jaringan Anda" , Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap();
                // jika id kosong maka simpan, jika id ada nilainya maka updat
                params.put("id_bahan", bahan_id);
                params.put("nama_bahan", nama_bahan);
                params.put("tinggi_bahan", tinggi_bahan);
                params.put("lebar_bahan", lebar_bahan);
                params.put("harga_bahan", harga_bahan);
                params.put("satuan", satuan);



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
