package com.example.sigap1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class holder extends RecyclerView.ViewHolder{
    TextView jenis, detail, lokasi, tanggal;
    String id;

    public holder(@NonNull View itemView) {
        super(itemView);
        jenis= itemView.findViewById(R.id.rw_nama_pelanggar);
        detail = itemView.findViewById(R.id.rw_detail_pelanggaran);
        lokasi = itemView.findViewById(R.id.rw_lokasi_view);
        tanggal = itemView.findViewById(R.id.rw_tgl);
    }
}
public class Adapter extends RecyclerView.Adapter<holder> {
    Context context;
    ArrayList<Riwayat> listRiwayat;

    public Adapter(Context context, ArrayList<Riwayat> listRiwayat) {
        this.context = context;
        this.listRiwayat = listRiwayat;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_riwayat,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        Riwayat riwayat = listRiwayat.get(position);

        holder.jenis.setText(riwayat.getJenis());
        holder.detail.setText(riwayat.getDetail());
        holder.lokasi.setText(riwayat.getLokasi());
        holder.tanggal.setText(riwayat.getTanggal());
        holder.id=riwayat.getId();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mulai activity Detail
                Intent varIntent = new Intent(context, DetailActivity.class);
                // sisipkan data ke intent
                varIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                varIntent.putExtra("id", holder.id);


                // method startActivity cma bisa di pake di activity/fragment
                // jadi harus masuk ke context dulu
               context.startActivity(varIntent);
            }
        });


    }

    @Override
    public int getItemCount() {

        return listRiwayat.size();
    }
}
