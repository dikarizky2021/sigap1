package com.example.sigap1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class holder extends RecyclerView.ViewHolder{
    TextView txtnama, txtnik;

    public holder(@NonNull View itemView) {
        super(itemView);
        txtnama = itemView.findViewById(R.id.nama_pelanggar);
        txtnik = itemView.findViewById(R.id.NIK);
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

        holder.txtnama.setText(riwayat.getNama());
        holder.txtnik.setText(riwayat.getNik());


    }

    @Override
    public int getItemCount() {

        return listRiwayat.size();
    }
}
