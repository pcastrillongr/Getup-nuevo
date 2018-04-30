package com.example.castriwolf.getup2;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.CountDownTimer;
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
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker marcador;
    private Marker marcador2;
    private double latsal = 0.0;
    private double lonsal = 0.0;
    private double latdes = 0.0;
    private double londes = 0.0;
    private Typeface weatherFont;
    private ImageView home;
    private ImageView trabajo;
    private PlaceAutocompleteFragment placeautocompletesalida;
    private PlaceAutocompleteFragment placeautocompletedestino;
    private String salida = "";
    private String destino = "";
    private Button calcular;
    private TextView distancia;
    private TextView tiempo;
    private Place punto1;
    private Place punto2;
    private Polyline line = null;
    private ImageView next;
    private ImageView ubi;

    private boolean lunes;
    private boolean martes;
    private boolean miercoles;
    private boolean jueves;
    private boolean viernes;
    private boolean sabado;
    private boolean domingo;
    private String hora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);

      Bundle parametros = this.getIntent().getExtras();
        lunes=parametros.getBoolean("Lunes");
        martes=parametros.getBoolean("Martes");
        miercoles=parametros.getBoolean("Miercoles");
        jueves=parametros.getBoolean("Jueves");
        viernes=parametros.getBoolean("Viernes");
        sabado=parametros.getBoolean("Sabado");
        domingo=parametros.getBoolean("Domingo");
        hora = parametros.getString("Hora");



        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        distancia = findViewById(R.id.km);
        tiempo = findViewById(R.id.tiempo);
        home = findViewById(R.id.imgCasa);
        home.setVisibility(View.VISIBLE);
        next = findViewById(R.id.next);
        next.setVisibility(View.VISIBLE);
        ubi = findViewById(R.id.ubi);
        ubi.setVisibility(View.VISIBLE);

        ubi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                miUbicacion();
                Toast.makeText(getApplicationContext(), "Buscando tu ubicacion", Toast.LENGTH_SHORT).show();


                new CountDownTimer(4000, 4000) {
                    @Override
                    public void onTick(long l) {


                    }

                    @Override
                    public void onFinish() {

                        Geocoder geocoder= new Geocoder(getApplicationContext(), Locale.getDefault());
                        try {
                            List<Address> addresses = geocoder.getFromLocation(latsal, lonsal, 1);
                            placeautocompletesalida.setText(addresses.get(0).getAddressLine(0));
                            salida=addresses.get(0).getAddressLine(0);
                            latsal=addresses.get(0).getLatitude();
                            lonsal=addresses.get(0).getLongitude();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }.start();


            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent go=new Intent(getApplicationContext(),Crear_Alarma_Paso3.class);

                go.putExtra("Lunes",lunes);
                go.putExtra("Martes",martes);
                go.putExtra("Miercoles",miercoles);
                go.putExtra("Jueves",jueves);
                go.putExtra("Viernes",viernes);
                go.putExtra("Sabado",sabado);
                go.putExtra("Domingo",domingo);

                go.putExtra("Hora", String.valueOf(hora));

                go.putExtra("TiempoRecorrido", String.valueOf(tiempo));





                startActivity(go);
            }
        });
        placeautocompletesalida = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete);
        salida = "Tu ubicación";
        placeautocompletesalida.setOnPlaceSelectedListener(new PlaceSelectionListener() {

            @Override
            public void onPlaceSelected(Place place) {

                salida = place.getName().toString();
                punto1 = place;
                Toast.makeText(getApplicationContext(), salida, Toast.LENGTH_LONG);
                Log.i("Mensaje", salida);
            }

            @Override
            public void onError(Status status) {

            }
        });
        placeautocompletedestino = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete2);
        placeautocompletedestino.setOnPlaceSelectedListener(new PlaceSelectionListener() {


            @Override
            public void onPlaceSelected(Place place) {

                destino = place.getName().toString();
                punto2 = place;
                Log.i("mensaje", destino);

            }

            @Override
            public void onError(Status status) {

            }
        });


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        calcular = (Button) findViewById(R.id.calculartiempo);
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(salida.equals("")||destino.equals(""))
                {

                    Toast.makeText(getApplicationContext(), "Introduce las dos direcciones", Toast.LENGTH_LONG).show();

                }else{

                    recorridoJson();
                }
            }


        });
    }


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


    }


    private void agregarMarcador(double lat, double lon) {

        LatLng cordenadas = new LatLng(lat, lon);
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(cordenadas, 16);
        if (marcador != null) {
            marcador.remove();
        }

        marcador = mMap.addMarker(new MarkerOptions().position(cordenadas).title("Mi posicion actual").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.animateCamera(miUbicacion);


    }


    private void actualizarUbicacion(Location location) {

        if (location != null) {
            latsal = location.getLatitude();
            lonsal = location.getLongitude();
            agregarMarcador(latsal, lonsal);
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
        } else {
            LocationManager lctManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location location = lctManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            actualizarUbicacion(location);
            lctManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 15000, 00, locListener);
            salida = String.valueOf(latsal + "," + lonsal);

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


    /**
     * Busqueda Json
     * Busca la informacion entre el punto de
     * salida y llegada.
     */

    private void recorridoJson() {
        String path="";
        StringBuilder stringBuilder;
        if(salida.equals("Tu ubicación")){
           path ="https://maps.googleapis.com/maps/api/geocode/json?latlng="+salida.toString()+"&key=AIzaSyCw-CaTf79uTrjEzDGt_WGN39ubmJKJIow";

            stringBuilder=traerContenidoStringBuilder(path);
           traerDireccion(stringBuilder);
        }

        if (salida != "0.0,0.0" && destino != "") {
            path = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + salida + "&destinations=" + destino + "&language=sp-SP&key=AIzaSyCw-CaTf79uTrjEzDGt_WGN39ubmJKJIow";
            // AIzaSyCw-CaTf79uTrjEzDGt_WGN39ubmJKJIow


            stringBuilder=traerContenidoStringBuilder(path);
            parsing(stringBuilder);



            /**
             * Si existe una polilinia ya creada la borramos y creamos otra.
             */

            pintarPolilinea();
            agregarMarcadorPolilinea();

        }



    }

    private void traerDireccion(StringBuilder sb) {

        JSONObject jsontiempo = null;
        try {
            JSONArray array = jsontiempo.getJSONArray("rows");
            JSONObject routes = array.getJSONObject(0);
            JSONArray legs = routes.getJSONArray("elements");
            JSONObject steps = legs.getJSONObject(0);
            JSONObject tiempos = steps.getJSONObject("duration");
            tiempo.setText(tiempos.getString("text"));

            jsontiempo = new JSONObject(sb.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * pintaPolilineas
     * funcionar para pintar una ralla entre
     * el punto de salida a la llegada.
     */

    private void pintarPolilinea() {
        if (line != null) {

            line.remove();

        }

        line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(latsal, lonsal),
                        new LatLng(punto2.getLatLng().latitude, punto2.getLatLng().longitude)).width(5).color(Color.RED));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        float minZoomLevel = mMap.getMinZoomLevel();
    }

    /**
     * agregarMarcadorPolilinea
     * agregas un marcador en el punto de salida
     * y el punto de llegada.
     */
    private void agregarMarcadorPolilinea() {


        if(marcador != null){

            marcador.remove();

        }

        if(marcador2 != null){
            marcador2.remove();
        }

       marcador= mMap.addMarker(new MarkerOptions()
                .position(new LatLng(latsal, lonsal))
                .title("Salida"));

       marcador2= mMap.addMarker(new MarkerOptions()
                .position(new LatLng(punto2.getLatLng().latitude, punto2.getLatLng().longitude))
                .title("Destino"));


    }

    private StringBuilder traerContenidoStringBuilder(String path){

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
        return sb;
    }

    private void parsing(StringBuilder sb){
        try {

            JSONObject jsontiempo = new JSONObject(sb.toString());
            JSONArray array = jsontiempo.getJSONArray("rows");
            JSONObject routes = array.getJSONObject(0);
            JSONArray legs = routes.getJSONArray("elements");
            JSONObject steps = legs.getJSONObject(0);
            JSONObject tiempos = steps.getJSONObject("duration");
            tiempo.setText(tiempos.getString("text"));


            JSONObject jsonRespRouteDistance = new JSONObject(sb.toString());
            JSONArray array2 = jsonRespRouteDistance.getJSONArray("rows");
            JSONObject routes2 = array2.getJSONObject(0);
            JSONArray legs2 = routes2.getJSONArray("elements");
            JSONObject steps2 = legs2.getJSONObject(0);
            JSONObject distance = steps2.getJSONObject("distance");
            distancia.setText(distance.getString("text"));


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}

