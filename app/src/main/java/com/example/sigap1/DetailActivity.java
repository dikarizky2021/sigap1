package com.example.sigap1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.HashMap;

public class DetailActivity extends AppCompatActivity  {




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

        class RiwayatList extends AsyncTask<Void, Void, String> implements OnMapReadyCallback {

            String id;
            ArrayList<Riwayat> listRiwayatJson;
            private GoogleMap mMap;
            double lat, longi;

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);



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
                        nik.setText(userJson.getString("nik"));

                        TextView nama= findViewById(R.id.detail_nama);
                        nama.setText(userJson.getString("nama"));

                        TextView jenis= findViewById(R.id.isi_jenis_pelanggaran);
                        jenis.setText(userJson.getString("jenis"));

                        TextView detail= findViewById(R.id.detail_detail_pelanggaran);
                        detail.setText(userJson.getString("detail"));

                        TextView lokasi= findViewById(R.id.isi_detail_lokasi);
                        lokasi.setText(userJson.getString("lokasi"));


                      //  URL newurl = new URL(userJson.getString("foto"));
                      //  Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                        ImageView dokumentasi = findViewById(R.id.dokumenatasi_foto);
                        Picasso.with(getApplicationContext()).load(userJson.getString("foto")).into(dokumentasi);
                       // dokumentasi.setImageBitmap(mIcon_val);


                    this.longi=Double.parseDouble(userJson.getString("longi"));
                    this.lat=Double.parseDouble(userJson.getString("lati"));

//  Toast. makeText(getApplicationContext(),"Get Post",Toast. LENGTH_SHORT).show();
                        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                                .findFragmentById(R.id.detail_map);
                        mapFragment.getMapAsync(this);




                    } else {
                        //        Toast.makeText(getApplicationContext(), "Invalid data", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException  e) {
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

            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                // Add a marker in Sydney and move the camera
                LatLng sydney = new LatLng(this.lat, this.longi);
                mMap.addMarker(new MarkerOptions().position(sydney).title("Lokasi pelanggaran"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(this.lat, this.longi), 12.0f));
            }

        }

        RiwayatList rl = new RiwayatList();
        rl.execute();
    }
}