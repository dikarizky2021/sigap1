package com.example.sigap1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class DashboardActivity extends AppCompatActivity {
    RecyclerView rRiwayat;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Riwayat> listRiwayat;
    TextView textViewId;
    ImageView dbLogout;

    void dataDummy(){

        this.listRiwayat= new ArrayList<>();
        //listRiwayat.add(new Riwayat("Pelanggaran2","Trotoar","Malang","22 Februari 2022"));

//
//        listRiwayat.add(new Riwayat("Pelanggaran2","Trotoar","Malang","22 Februari 2022"));
//        listRiwayat.add(new Riwayat("Pelanggaran3","Trotoar","Malang","22 Februari 2022"));
//        listRiwayat.add(new Riwayat("Pelanggaran4","Trotoar","Malang","22 Februari 2022"));
//        listRiwayat.add(new Riwayat("Pelanggaran5","Trotoar","Malang","22 Februari 2022"));
//        listRiwayat.add(new Riwayat("Pelanggaran6","Trotoar","Malang","22 Februari 2022"));
//        listRiwayat.add(new Riwayat("Pelanggaran7","Trotoar","Malang","22 Februari 2022"));
//        listRiwayat.add(new Riwayat("Pelanggaran8","Trotoar","Malang","22 Februari 2022"));
//        listRiwayat.add(new Riwayat("Pelanggaran9","Trotoar","Malang","22 Februari 2022"));
//        listRiwayat.add(new Riwayat("Pelanggaran10","Trotoar","Malang","22 Februari 2022"));
//        listRiwayat.add(new Riwayat("Pelanggaran11","Trotoar","Malang","22 Februari 2022"));
//        listRiwayat.add(new Riwayat("Pelanggaran12","Trotoar","Malang","22 Februari 2022"));



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
      //  dataDummy();
        riwayatList();


      //  data();

        Button btnBuat= findViewById(R.id.buat_laporan);

        btnBuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perintah Intent Explicit pindah halaman ke activity_detail
              //
                //  finish();
              startActivity(new Intent(DashboardActivity.this, FormActivity.class));
            }
        });

        //getting the current user
        User user = SharedPrefManager.getInstance(this).getUser();
        textViewId = (TextView) findViewById(R.id.dbnama_user);
        textViewId.setText("Hallo, "+user.getNama()+"!");

        dbLogout = findViewById(R.id.dblogout);
        dbLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                SharedPrefManager.getInstance(getApplicationContext()).logout();
                startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
            }
        });


    }


    private void riwayatList() {
        //first getting the values
        final String username = SharedPrefManager.getInstance(this).getUser().getUsername();


        //if everything is fine

        class RiwayatList extends AsyncTask<Void, Void, String> {

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
                        this.listRiwayatJson = new ArrayList<>();

                        JSONArray jArray = obj.getJSONArray("riwayat");
                         if (jArray != null) {
                            for (int i=0;i<jArray.length();i++){
                                JSONObject jObj= new JSONObject( jArray.getString(i));
                                this.listRiwayatJson.add(new Riwayat(""+jObj.getString("jenis_pelanggaran"),""+jObj.getString("detail_pelanggaran") , ""+jObj.getString("kecamatan"),""+jObj.getString("waktu"),""+jObj.getString("id")));
                              //  System.out.println(jObj.getString("jenis_pelanggaran")+"-"+jObj.getString("detail_pelanggaran")+"-"+ jObj.getString("detail_lokasi")+"-"+jObj.getString("waktu"));


                            }
                        }

                        rRiwayat = findViewById(R.id.rvRiwayat);
//                        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
//                        rRiwayat.setLayoutManager(manager);
                        rRiwayat.setHasFixedSize(true);
                        adapter = new Adapter(getApplicationContext(),listRiwayatJson);
                        layoutManager = new LinearLayoutManager(getApplicationContext());
                        rRiwayat.setLayoutManager(layoutManager);
                        rRiwayat.setAdapter(adapter);




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

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("username", username);


                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_RIWAYAT, params);
            }
        }

        RiwayatList rl = new RiwayatList();
        rl.execute();
    }

}