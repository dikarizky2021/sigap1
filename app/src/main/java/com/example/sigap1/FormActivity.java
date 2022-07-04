package com.example.sigap1;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.sigap1.databinding.ActivityFormBinding;

public class FormActivity extends AppCompatActivity {

    private String[] jenisItem = {"Jenis Pelanggaran 1","Jenis Pelanggaran 2","Jenis Pelanggaran 3"};

    private String[] detailItem = {"Jenis Detail Pelanggaran 1","Jenis Detail Pelanggaran 2","Jenis Detail Pelanggaran 3"};

    private String[] tindakanItem = {"Tindakan 1","Tindakan 2","Tindakan 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);


        ImageButton btnBack= findViewById(R.id.form_kembali);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perintah Intent Explicit pindah halaman ke activity_detail
                //
                finish();
                startActivity(new Intent(FormActivity.this,DashboardActivity.class));
            }
        });

        TextView btnBackDua= findViewById(R.id.form_kembali_dua);

        btnBackDua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perintah Intent Explicit pindah halaman ke activity_detail
                //
                finish();
                startActivity(new Intent(FormActivity.this,DashboardActivity.class));
            }
        });

        Button btnSimpan= findViewById(R.id.btn_simpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perintah Intent Explicit pindah halaman ke activity_detail
                //
                finish();
                startActivity(new Intent(FormActivity.this,DashboardActivity.class));
            }
        });

        Button btnBatal= findViewById(R.id.btn_batal);

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perintah Intent Explicit pindah halaman ke activity_detail
                //
                finish();
                startActivity(new Intent(FormActivity.this,DashboardActivity.class));
            }
        });

        final Spinner ListJenis = findViewById(R.id.jenis_pelanggaran);

        //Inisialiasi Array Adapter dengan memasukkan String Array
        final ArrayAdapter<String> adapterJenis = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,jenisItem);

        //Memasukan Adapter pada Spinner
        ListJenis.setAdapter(adapterJenis);

        final Spinner ListDetail = findViewById(R.id.detail_pelanggaran);

        //Inisialiasi Array Adapter dengan memasukkan String Array
        final ArrayAdapter<String> adapterDetail = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,detailItem);

        //Memasukan Adapter pada Spinner
        ListDetail.setAdapter(adapterDetail);

        final Spinner ListTindakan = findViewById(R.id.tindakan_petugas);

        //Inisialiasi Array Adapter dengan memasukkan String Array
        final ArrayAdapter<String> adapterTindakan = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,tindakanItem);

        //Memasukan Adapter pada Spinner
        ListTindakan.setAdapter(adapterTindakan);

    }
}