package com.example.sigap1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {
    RecyclerView rRiwayat;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Riwayat> listRiwayat;

    void dataDummy(){
        listRiwayat = new ArrayList<>();
        listRiwayat.add(new Riwayat("Roy Achmad", "35250606099970004"));
        listRiwayat.add(new Riwayat("Dika", "35250602304382934"));
        listRiwayat.add(new Riwayat("Rizky", "984729847298892472"));
        listRiwayat.add(new Riwayat("aku","32431313123"));
        listRiwayat.add(new Riwayat("Achmad", "35250602304382934"));
        listRiwayat.add(new Riwayat("Aziz", "984729847298892472"));

    }
    void data(){
        rRiwayat = findViewById(R.id.rvRiwayat);
        adapter = new Adapter(this,listRiwayat);
        layoutManager = new LinearLayoutManager(this);
        rRiwayat.setLayoutManager(layoutManager);
        rRiwayat.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        dataDummy();data();

        Button btnBuat= findViewById(R.id.buat_laporan);

        btnBuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perintah Intent Explicit pindah halaman ke activity_detail
              startActivity(new Intent(DashboardActivity.this, FormActivity.class));
            }
        });
    }
}