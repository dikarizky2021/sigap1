package com.example.sigap1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class DetailActivity extends AppCompatActivity {


    ArrayList<Riwayat> listRiwayat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dua);

        riwayatList();

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
                finish();
                startActivity(new Intent(DetailActivity.this,DashboardActivity.class));
            }
        });



    }



    private void riwayatList() {

        //if everything is fine

        class RiwayatList extends AsyncTask<Void, Void, String>  {

            String id;
            ArrayList<Riwayat> listRiwayatJson;

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                //  Toast. makeText(getApplicationContext(),"Get Post",Toast. LENGTH_SHORT).show();


                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        // Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        JSONObject userJson = obj.getJSONObject("user");

                       TextView tanggal= findViewById(R.id.waktu_pelanggaran);
                       tanggal.setText(userJson.getString("waktu"));

                        TextView nik= findViewById(R.id.detail_nik);
                        tanggal.setText(userJson.getString("nik"));

                        TextView nama= findViewById(R.id.detail_nama);
                        tanggal.setText(userJson.getString("nama"));

                        TextView jenis= findViewById(R.id.isi_jenis_pelanggaran);
                        tanggal.setText(userJson.getString("jenis"));

                        TextView detail= findViewById(R.id.detail_detail_pelanggaran);
                        tanggal.setText(userJson.getString("detail"));

                        TextView lokasi= findViewById(R.id.isi_detail_lokasi);
                        tanggal.setText(userJson.getString("lokasi"));



                    userJson.getString("longi");
                    userJson.getString("lati");


                    userJson.getString("foto");






                    } else {
                        //        Toast.makeText(getApplicationContext(), "Invalid data", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    //    Toast. makeText(getApplicationContext(),e.toString(),Toast. LENGTH_SHORT).show();

                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();


                Bundle extras = getIntent().getExtras();
                this.id= extras.getString("id");

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("id", this.id);


                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_DETAIL, params);
            }
        }

        RiwayatList rl = new RiwayatList();
        rl.execute();
    }
}