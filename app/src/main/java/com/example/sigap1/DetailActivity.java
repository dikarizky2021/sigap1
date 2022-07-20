package com.example.sigap1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dua);

        ImageButton btnBack= findViewById(R.id.detail_kembali);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perintah Intent Explicit pindah halaman ke activity_detail
                //
                 finish();
                startActivity(new Intent(DetailActivity.this,DashboardActivity.class));
            }
        });

        TextView btnBackDua= findViewById(R.id.detail_kembali_dua);

        btnBackDua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perintah Intent Explicit pindah halaman ke activity_detail
                //
                finish();
                startActivity(new Intent(DetailActivity.this,DashboardActivity.class));
            }
        });

        Bundle extras = getIntent().getExtras();
        String id= extras.getString("id");



    }
}