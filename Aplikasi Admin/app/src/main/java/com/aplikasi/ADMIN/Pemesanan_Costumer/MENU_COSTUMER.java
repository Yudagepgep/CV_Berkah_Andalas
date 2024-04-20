package com.aplikasi.ADMIN.Pemesanan_Costumer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.aplikasi.ADMIN.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MENU_COSTUMER extends AppCompatActivity {
    CardView data_input, detail_pemesanan;
    ViewFlipper flipper;
    TextView id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_costumer);
        data_input = findViewById(R.id.input_data);
        detail_pemesanan = findViewById(R.id.laporan_detail);
        id = findViewById(R.id.user_id);

        id.setText(getIntent().getStringExtra("id_user"));

        detail_pemesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MENU_COSTUMER.this, Data_Pemesanan_Costumer.class);
                startActivity(intent);
            }
        });


        data_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id_user = id.getText().toString().trim();
                Intent intent = new Intent(MENU_COSTUMER.this, Input_Pemesanan.class);
                intent.putExtra("id_user",id_user);
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
