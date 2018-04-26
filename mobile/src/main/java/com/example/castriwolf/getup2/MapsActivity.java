package com.example.castriwolf.getup2;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker marcador;
    double lat = 0.0;
    double lon = 0.0;
    private Typeface weatherFont;
    private ImageView home;
    private ImageView trabajo;
    private PlaceAutocompleteFragment placeautocompletesalida;
    private PlaceAutocompleteFragment placeautocompletedestino;
    private String salida;
    private  String destino;
    private Button calcular;
    private TextView distancia;
    private TextView tiempo;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        distancia=(TextView) findViewById(R.id.km);
        tiempo=(TextView) findViewById(R.id.tiempo);

        placeautocompletesalida = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete);
        placeautocompletesalida.setOnPlaceSelectedListener(new PlaceSelectionListener() {

            @Override
            public void onPlaceSelected(Place place) {

                salida=place.getName().toString();
                Log.i("Mensaje",salida);
            }

            @Override
            public void onError(Status status) {

            }
        });
        placeautocompletedestino = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete2);
        placeautocompletedestino.setOnPlaceSelectedListener(new PlaceSelectionListener() {


                                                                @Override
                                                                public void onPlaceSelected(Place place) {

                                                                    destino=place.getName().toString();
                                                                    Log.i("mensaje",destino);

                                                                }

                                                                @Override
                                                                public void onError(Status status) {

                                                                }
                                                            });



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        calcular=(Button)findViewById(R.id.calculartiempo);
        calcular.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            String path = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + salida + "&destinations=" + destino + "&language=fr-FR&key=AIzaSyCw-CaTf79uTrjEzDGt_WGN39ubmJKJIow";


                                            HttpURLConnection con = null;
                                            StringBuilder sb = new StringBuilder();
                                            try {
                                                URL u = new URL(path);
                                                con = (HttpURLConnection) u.openConnection();

                                                con.connect();


                                                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                                                String line;
                                                while ((line = br.readLine()) != null) {
                                                    sb.append(line + "\n");

                                                }
                                                br.close();


                                            } catch (MalformedURLException ex) {
                                                ex.printStackTrace();
                                            } catch (IOException ex) {
                                                ex.printStackTrace();
                                            } finally {
                                                if (con != null) {
                                                    try {
                                                        con.disconnect();
                                                    } catch (Exception ex) {
                                                        ex.printStackTrace();
                                                    }
                                                }
                                            }







                                               try {

                                                   JSONObject jsontiempo = new JSONObject(sb.toString());
                                                        JSONArray array = jsontiempo.getJSONArray("rows");
                                                        JSONObject routes = array.getJSONObject(0);
                                                        JSONArray legs = routes.getJSONArray("elements");
                                                        JSONObject steps = legs.getJSONObject(0);
                                                        JSONObject tiempos = steps.getJSONObject("duration");
                                                   tiempo.setText(tiempos.getString("text"));


                                                   JSONObject jsonRespRouteDistance = new JSONObject(sb.toString());
                                                        JSONArray array2 = jsontiempo.getJSONArray("rows");
                                                        JSONObject routes2 = array.getJSONObject(0);
                                                        JSONArray legs2 = routes.getJSONArray("elements");
                                                        JSONObject steps2 = legs.getJSONObject(0);
                                                        JSONObject distance = steps.getJSONObject("distance");
                                                   distancia.setText(distance.getString("text"));








                                                   String tiempoDistancia = jsontiempo.get("text").toString();

                                                  // tiempo.setText(tiempoDistancia.toString());


                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }


                                        }


                                    });}


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        miUbicacion();

    }


    private void agregarMarcador(double lat, double lon) {

        LatLng cordenadas = new LatLng(lat, lon);
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(cordenadas, 16);
        if (marcador != null){marcador.remove();}

        marcador = mMap.addMarker(new MarkerOptions().position(cordenadas).title("Mi posicion actual").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.animateCamera(miUbicacion);


    }




    private void actualizarUbicacion(Location location) {

        if (location != null) {
            lat = location.getLatitude();
            lon = location.getLongitude();
            agregarMarcador(lat, lon);
        }
    }

    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            actualizarUbicacion(location);

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {


            Toast.makeText(getApplicationContext(), "Gps Activado", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderDisabled(String provider) {
            showAlert();

        }
    };

   private void miUbicacion() {

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            return;
        }
        else {
            LocationManager lctManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location location = lctManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            actualizarUbicacion(location);
            lctManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 15000, 00, locListener);
        }
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getApplicationContext());
        dialog.setTitle("Enable Location")
                .setMessage("Su ubicación esta desactivada.\npor favor active su ubicación usa esta app")
                .setPositiveButton("Configuración de ubicación", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    }
                });
        dialog.show();


    }
}
