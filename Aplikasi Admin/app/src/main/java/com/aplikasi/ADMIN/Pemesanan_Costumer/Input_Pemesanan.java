package com.aplikasi.ADMIN.Pemesanan_Costumer;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Input_Pemesanan extends AppCompatActivity {
    TextView nama;
    EditText tgl, jns, byk,ket;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormat;
    Button kirim, batal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input__pemesanan);

        nama = findViewById(R.id.nama_user);
        tgl = findViewById(R.id.tgl_pesanan);
        byk = findViewById(R.id.byk_pesan);
        jns = findViewById(R.id.jenis_pesan);
        ket = findViewById(R.id.ket);
        kirim = findViewById(R.id.pesanan_costumer);
        batal = findViewById(R.id.batal_pemesanan);
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Input_Pemesanan.this, MENU_COSTUMER.class);
                startActivity(intent);
            }
        });

        nama.setText(getIntent().getStringExtra("id_user"));

        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kirim_pemesanan();
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
    private void kirim_pemesanan() {
        final String nama_user      = nama.getText().toString();
        final String tanggal        = tgl.getText().toString();
        final String byk_pesanan    = byk.getText().toString();
        final String jns_pesanan    = jns.getText().toString();
        final String keterangan     = ket.getText().toString();

        String url = "https://berkah-andalas06.000webhostapp.com/pemesanan_costumer/Input_Pemesanan_Costumer.php";

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
                                androidx.appcompat.app.AlertDialog.Builder builder=new androidx.appcompat.app.AlertDialog.Builder(Input_Pemesanan.this);
                                builder.setTitle("Sukses");
                                builder.setMessage("Data Sukses Terikirim");
                                builder.setPositiveButton("oke", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                        finish();

                                    }
                                });
                                androidx.appcompat.app.AlertDialog dialog=builder.create();
                                dialog.show();
                            }else {
                                Toast.makeText(Input_Pemesanan.this, "Data Gagal Terkirim" , Toast.LENGTH_LONG).show();

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Input_Pemesanan.this, " Jaringan Tidak Ada \n Mohon Periksa Kembali Jaringan Anda" , Toast.LENGTH_LONG).show();


                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap();
                // jika id kosong maka simpan, jika id ada nilainya maka updat

                params.put("nama_user", nama_user);
                params.put("tanggal", tanggal);
                params.put("banyak_pesanan", byk_pesanan);
                params.put("jenis_pesanan", jns_pesanan);
                params.put("keterangan", keterangan);



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
        if (item.getItemId()==R.id.logout) {
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
        }
                return super.onContextItemSelected(item);
        }

    }







