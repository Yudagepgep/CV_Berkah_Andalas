package com.aplikasi.ADMIN;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.aplikasi.ADMIN.ANTRIAN.Data_Antrian;
import com.aplikasi.ADMIN.LAPORAN.Laporan_Pesanan;
import com.aplikasi.ADMIN.Pemesanan_Costumer.MENU_COSTUMER;
import com.aplikasi.ADMIN.input.INPUT_PESANAN;

public class MENU extends AppCompatActivity {
    CardView data_input, detail_laporan, data_antrian;
    ViewFlipper flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        data_input = findViewById(R.id.input_data);
        detail_laporan = findViewById(R.id.laporan_detail);
        data_antrian = findViewById(R.id.antrian);


        detail_laporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MENU.this, Laporan_Pesanan.class);
                startActivity(intent);
            }
        });

        data_antrian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MENU.this, Data_Antrian.class);
                startActivity(intent);
            }
        });


        data_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MENU.this, INPUT_PESANAN.class);
                startActivity(intent);
            }
        });

        int images[] = {R.drawable.cv1, R.drawable.cv2, R.drawable.cv3, R.drawable.cv3,};

        flipper = findViewById(R.id.flipper);

        //for (int i=0; i< images.length; i++){
        //flipperImages(images[i]);
        //}
        for (int image : images) {
            flipperImages(image);
        }


    }


    public void flipperImages(int images) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(images);

        flipper.addView(imageView);
        flipper.setFlipInterval(4000);
        flipper.setAutoStart(true);

        flipper.setInAnimation(this, android.R.anim.slide_in_left);
        flipper.setOutAnimation(this, android.R.anim.slide_out_right);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_costumer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       if (item.getItemId() == R.id.tb_costumer) {
            startActivity(new Intent(this, MENU_COSTUMER.class));
        }else if (item.getItemId() == R.id.logout) {
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


