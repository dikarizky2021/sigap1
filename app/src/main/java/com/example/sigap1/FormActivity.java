package com.example.sigap1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Context;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.provider.Settings;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.app.AlertDialog;

import android.location.LocationManager;
import android.content.DialogInterface;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

//
//public class FormActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
//        GoogleApiClient.OnConnectionFailedListener, LocationListener {
public class FormActivity extends AppCompatActivity implements OnMapReadyCallback {

    private String[] jenisItem = {"Ketertiban Umum dan Ketentraman Masyarakat"};

    private String[] detailItem = {
            "Menggunakan bahu jalan/ trotoar \n tidak sesuai dengan fungsinya",
            "Berdagang di atas badan jalan/ trotoar/ \n bawah flyover/ halte/ jembatan penyeberangan orang/ \n tempat-tempat untuk kepentingan umum lainnya",
            "Melakukan tindakan premanisme, pemungutan uang, \n mengelola/menjual lapak/tempat untuk berdagang di pasar,\n dan di jalan-jalan yang mengakibatkan keresahan, kesemerautan, tidak tertibnya lingkungan dan mengganggu lalu lintas",
            "Menempatkan benda-benda untuk melakukan \n sesuatu usaha di jalan/ di pinggir rel kereta api/ \n jalur hijau/ di bawah flyover/ taman dan tempat-tempat umum",
            "Menjajakan barang dagangan/ membagikan selebaran/ \n melakukan usaha-usaha tertentu dengan mengharapkan imbalan \n di jalan/ jalur hijau/ taman dan tempat-tempat umum" };

    private String[] tindakanItem = {"Pembinaan", "Penyidikan", "Pelaporan"};

    EditText editTextNIK, editTextNama, editTextDetailLokasi;
   Spinner ListJenis, ListTindakan, ListDetail;
    String vNIK,vNama,vDetailLokasi,vJenis, vDetail,vTindakan, vLat, vLong, vKec, vKel, vFot, vUsername;
    Bitmap imageAbsen;
    ImageView photoButton;
    double lat, longi;

    private GoogleMap mMap;
//    GoogleApiClient mGoogleApiClient;
//    private LocationRequest mLocationRequest;
//    private Marker mCurrLocationMarker;
//    private Location mLastLocation;

//    private int locationRequestCode = 1000;
//    private double wayLatitude = 0.0, wayLongitude = 0.0;
//    FusedLocationProviderClient mFusedLocation;
    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    String latitude, longitude;

    //--------------------------------

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageAbsen = imageBitmap;

            if (checkAWS(imageAbsen)) {
                this.photoButton.setImageBitmap(imageAbsen);
            }

        }
    }

    //------------------------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        editTextNIK = (EditText) findViewById(R.id.NIK);
        editTextNama = (EditText) findViewById(R.id.Nama);
        editTextDetailLokasi = (EditText) findViewById(R.id.detail_lokasi);


        ImageButton btnBack = findViewById(R.id.form_kembali);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perintah Intent Explicit pindah halaman ke activity_detail
                //
                finish();
                startActivity(new Intent(FormActivity.this, DashboardActivity.class));
            }
        });

        TextView btnBackDua = findViewById(R.id.form_kembali_dua);
        btnBackDua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perintah Intent Explicit pindah halaman ke activity_detail
                //
                finish();
                startActivity(new Intent(FormActivity.this, DashboardActivity.class));
            }
        });

        Button btnSimpan = findViewById(R.id.btn_simpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perintah Intent Explicit pindah halaman ke activity_detail
                simpanData();
//                finish();
//                startActivity(new Intent(FormActivity.this, DashboardActivity.class));
            }
        });

        Button btnBatal = findViewById(R.id.btn_batal);
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perintah Intent Explicit pindah halaman ke activity_detail
                //
                finish();
                startActivity(new Intent(FormActivity.this, DashboardActivity.class));
            }
        });

        ListJenis = findViewById(R.id.jenis_pelanggaran);
        //Inisialiasi Array Adapter dengan memasukkan String Array
       // final ArrayAdapter<String> adapterJenis = new ArrayAdapter<>(this,
        //        android.R.layout.simple_spinner_dropdown_item, jenisItem);
        final ArrayAdapter<String> adapterJenis = new ArrayAdapter<>(this,
                        R.layout.textview_layout, jenisItem);
        adapterJenis.setDropDownViewResource(R.layout.textview_layout);
        ListJenis.setAdapter(adapterJenis);

        ListDetail = findViewById(R.id.detail_pelanggaran);
        //Inisialiasi Array Adapter dengan memasukkan String Array
       // final ArrayAdapter<String> adapterDetail = new ArrayAdapter<>(this,
              //  android.R.layout.simple_spinner_dropdown_item, detailItem);
        final ArrayAdapter<String> adapterDetail = new ArrayAdapter<>(this,
                R.layout.textview_layout, detailItem);
        adapterJenis.setDropDownViewResource(R.layout.textview_layout);
        //Memasukan Adapter pada Spinner
        ListDetail.setAdapter(adapterDetail);

        ListTindakan = findViewById(R.id.tindakan_petugas);
        //Inisialiasi Array Adapter dengan memasukkan String Array
        final ArrayAdapter<String> adapterTindakan = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, tindakanItem);
        //Memasukan Adapter pada Spinner
        ListTindakan.setAdapter(adapterTindakan);

        //-------------------------------------
        this.photoButton = (ImageView) this.findViewById(R.id.imageFoto);
        this.photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

        //---------------------------------------------------------
//        this.mFusedLocation = LocationServices.getFusedLocationProviderClient(this);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
//                    locationRequestCode);
//            Toast.makeText(getApplicationContext(),"toast granted",Toast.LENGTH_LONG).show();
//            return;
//        } else {
//
//            Toast.makeText(getApplicationContext(),"toast else granted",Toast.LENGTH_LONG).show();
//        }
//
//
//        this.mFusedLocation.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
//            @Override
//            public void onSuccess(Location location) {
//                if (location != null) {
//                    // Do it all with location
//                    Log.d("My Current location", "Lat : " + location.getLatitude() + " Long : " + location.getLongitude());
//                    // Display in Toast
//                    System.out.println("Lat : " + location.getLatitude());
//                    Toast.makeText(getApplicationContext(),
//                            "Lat : " + location.getLatitude() + " Long : " + location.getLongitude(),
//                            Toast.LENGTH_LONG).show();
//
//
//                } else {
//                    Toast.makeText(getApplicationContext(), "lokasi gagal", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
        //-------------------
//
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            OnGPS();
        } else {
            getLocation();
        }


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentMap);
        mapFragment.getMapAsync(this);


    }

    public void simpanData(){

        vNIK=editTextNIK.getText().toString();
        vNama=editTextNama.getText().toString();
        vJenis=ListJenis.getSelectedItem().toString();
        vDetail=ListDetail.getSelectedItem().toString();
        vTindakan=ListTindakan.getSelectedItem().toString();
        vDetailLokasi=editTextDetailLokasi.getText().toString();
        vLat=latitude;
        vLong=longitude;
        vKec="Mojokerto";
        vKel="Mojokerto";
        User user = SharedPrefManager.getInstance(this).getUser();
        vUsername=user.getUsername();
        vFot="0";

//        if (TextUtils.isEmpty(vNIK)) {
//            editTextNIK.setError("Masukkan NIK terlebih dahulu");
//            editTextNIK.requestFocus();
//            return;
//        }

        if (TextUtils.isEmpty(vNama)) {
            editTextNama.setError("Masukkan Nama Pelanggar terlebih dahulu");
            editTextNama.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(vDetailLokasi)) {
            editTextDetailLokasi.setError("Masukkan Detail Lokasi terlebih dahulu");
            editTextDetailLokasi.requestFocus();
            return;
        }

        //---------------------
        BitmapFactory.Options options0 = new BitmapFactory.Options();
        options0.inSampleSize = 2;
        // options.inJustDecodeBounds = true;
        options0.inScaled = false;
        options0.inDither = false;
        options0.inPreferredConfig = Bitmap.Config.ARGB_8888;

        ImageView imgPreview = (ImageView) findViewById(R.id.imageFoto);
        //Bitmap bmp = BitmapFactory.decodeResource(findViewById(R.id.imageFoto).getResources(),R.id.imageFoto);
        Bitmap bmp = ((BitmapDrawable) imgPreview.getDrawable()).getBitmap();
        ByteArrayOutputStream baos0 = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos0);
        byte[] imageBytes0 = baos0.toByteArray();
      //  image.setImageBitmap(bmp);
        this.vFot= Base64.encodeToString(imageBytes0, Base64.DEFAULT);

        //----------------------

//        String tes=""+vFot+" "+vUsername+" "+vDetailLokasi+" "+vDetail+" "+vLat;
//        Toast.makeText(getApplicationContext(),tes,Toast.LENGTH_LONG).show();
//        System.out.println(tes);

        //if it passes all the validations

        class RegisterUser extends AsyncTask<Void, Void, String> {

           // private ProgressBar progressBar;

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("jenis", vJenis);
                params.put("detail", vDetail);
                params.put("nik", vNIK);
                params.put("nama", vNama);
                params.put("tindakan", vTindakan);
                params.put("longi", vLong);
                params.put("lati", vLat);
                params.put("lokasi", vDetailLokasi);
                params.put("username", vUsername);
                params.put("kecamatan", vKec);
                params.put("kelurahan", vKel);
                params.put("foto", vFot);

                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_LAPOR, params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //displaying the progress bar while user registers on the server
//                progressBar = (ProgressBar) findViewById(R.id.progressBar);
//                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //hiding the progressbar after completion
//                progressBar.setVisibility(View.GONE);

                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();

                       //getting the user from the response
//                        JSONObject userJson = obj.getString("user");

                        //creating a new user object
//                        User user = new User(
//
//                                userJson.getString("username"),
//                                userJson.getString("nama")
//                        );
//
//                        //storing the user in shared preferences
//                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                        //starting the profile activity
                        finish();
                        startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Some error occurred", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        //executing the async task
        RegisterUser ru = new RegisterUser();
        ru.execute();
    }



    public boolean checkAWS(Bitmap image){
        // cek aws
        if(image!=null) {
            return true;
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(lat, longi);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Lokasi saat ini"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
////
    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
////
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
                FormActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                FormActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                lat = locationGPS.getLatitude();
                longi = locationGPS.getLongitude();
                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);
                Toast.makeText(getApplicationContext(),   "Latitude: " + latitude + "\n" + "Longitude: " + longitude,Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }
//
//    private void buildGoogleApiClient() {
//        mGoogleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
//        mGoogleApiClient.connect();
//    }
//
//    @Override
//    public void onConnected(@Nullable Bundle bundle) {
//        LocationRequest mLocationRequest = new LocationRequest();
//        mLocationRequest.setInterval(1000);
//        mLocationRequest.setFastestInterval(1000);
//        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
//        if (ContextCompat.checkSelfPermission(this,
//                android.Manifest.permission.ACCESS_FINE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED) {
//            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
//        }
//    }
//
//    @Override
//    public void onConnectionSuspended(int i) {
//
//    }
//
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//    }
//
//    @Override
//    public void onLocationChanged(Location location) {
//        mLastLocation = location;
//        if (mCurrLocationMarker != null) {
//            mCurrLocationMarker.remove();
//        }
//        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//
//        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latLng.latitude, latLng.longitude)).zoom(16).build();
//
//        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//
//        //menghentikan pembaruan lokasi
//        if (mGoogleApiClient != null) {
//            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
//        }
//    }
//
//    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
//    public boolean checkLocationPermission(){
//        if (ContextCompat.checkSelfPermission(this,
//                android.Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {
//                ActivityCompat.requestPermissions(this,
//                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
//                        MY_PERMISSIONS_REQUEST_LOCATION);
//            } else {
//                ActivityCompat.requestPermissions(this,
//                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
//                        MY_PERMISSIONS_REQUEST_LOCATION);
//            }
//            return false;
//        } else {
//            return true;
//        }
//    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case MY_PERMISSIONS_REQUEST_LOCATION: {
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    // Izin diberikan.
//                    if (ContextCompat.checkSelfPermission(this,
//                            Manifest.permission.ACCESS_FINE_LOCATION)
//                            == PackageManager.PERMISSION_GRANTED) {
//
//                        if (mGoogleApiClient == null) {
//                            buildGoogleApiClient();
//                        }
//                        mMap.setMyLocationEnabled(true);
//                    }
//
//                } else {
//
//                    // Izin ditolak.
//                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
//                }
//                return;
//            }
//        }
//    }


}