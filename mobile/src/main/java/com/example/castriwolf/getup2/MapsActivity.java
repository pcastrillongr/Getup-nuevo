package com.example.castriwolf.getup2;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.icu.util.TimeZone;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
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

import com.example.castriwolf.getup2.Clases.DirectionsParser;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int LOCATION_REQUEST = 500;
    final CharSequence[] items = {"Evitar autopista", "Evitar peajes"};
    // arraylist to keep the selected items
    final ArrayList seletedItems = new ArrayList();
    public static GoogleMap mMap;
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

    private ImageView coche;
    private ImageView bus;
    private ImageView bici;
    private ImageView andar;
    private ImageView ajuste;

    private Boolean irCoche = false;
    private Boolean irBus = false;
    private Boolean irBici = false;
    private Boolean irAndando = false;
    private Boolean Eautopista = false;
    private Boolean Epeaje = false;

    public Boolean pintado = false;


    Bundle parametros;

    private boolean lunes;
    private boolean martes;
    private boolean miercoles;
    private boolean jueves;
    private boolean viernes;
    private boolean sabado;
    private boolean domingo;
    private int hora;
    private int minuto;
    private int horaRecorrido;
    private int minutosRecorrido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);
        traerBundle();

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
        coche = findViewById(R.id.ivCoche);
        bus = findViewById(R.id.ivBus);
        bici = findViewById(R.id.ivBici);
        andar = findViewById(R.id.ivAndar);
        ajuste = findViewById(R.id.ivAjustes);


        /**
         * Boton de ajustes
         * abre un dialog alert para indicar tipo ajustes de ruta.
         */
        ajuste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = new AlertDialog.Builder(MapsActivity.this)
                        .setTitle("Parametros")
                        .setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    seletedItems.add(indexSelected);
                                } else if (seletedItems.contains(indexSelected)) {
                                    // Else, if the item is already in the array, remove it
                                    seletedItems.remove(Integer.valueOf(indexSelected));
                                }
                            }
                        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                //  Your code when user clicked on OK
                                //  You can write the code  to save the selected item here

                                for(int i =0; 0>seletedItems.size(); i++ ){

                                    if(seletedItems.get(i).equals(items)){

                                    }
                                }

                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                //  Your code when user clicked on Cancel
                                Eautopista=false;
                                Epeaje=false;
                            }
                        }).create();
                dialog.show();

            }});


        /**
         * Boton ubi
         * para encontrar tu ubicacion actual.
         */
        ubi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                new CountDownTimer(4000, 4000) {
                    @Override
                    public void onTick(long l) {

                        miUbicacion();
                        Toast.makeText(getApplicationContext(), "Buscando tu ubicacion", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFinish() {

                        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                        try {
                            List<Address> addresses = geocoder.getFromLocation(latsal, lonsal, 1);
                            if (addresses.size() >= 1) {
                                placeautocompletesalida.setText(addresses.get(0).getAddressLine(0));
                                salida = addresses.get(0).getAddressLine(0);
                                latsal = addresses.get(0).getLatitude();
                                lonsal = addresses.get(0).getLongitude();
                            } else {
                                Toast.makeText(getApplicationContext(), "No pudimos encontrar su ubicacion revise su conexión y permisos", Toast.LENGTH_LONG).show();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }.start();


            }
        });
        /**
         * Boton Next
         * para seguir al siguiente paso.
         */
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!tiempo.getText().toString().equals("")) {
                    recuperarTiempo();
                }
                if (minutosRecorrido > 0 && !placeautocompletesalida.equals("") && !placeautocompletedestino.equals("")) {
                    Intent go = new Intent(getApplicationContext(), Crear_Alarma_Paso3.class);

                    go.putExtra("Lunes", lunes);
                    go.putExtra("Martes", martes);
                    go.putExtra("Miercoles", miercoles);
                    go.putExtra("Jueves", jueves);
                    go.putExtra("Viernes", viernes);
                    go.putExtra("Sabado", sabado);
                    go.putExtra("Domingo", domingo);
                    //Hora de llegada al destino
                    go.putExtra("Hora", hora);
                    go.putExtra("HMinuto", minuto);
                    //Tiempo ruta
                    go.putExtra("HorasRecorrido", horaRecorrido);
                    go.putExtra("MinutosRecorridos", minutosRecorrido);
                    // Lugar de salida y llegada
                    go.putExtra("Lsalida", salida);
                    go.putExtra("Lllegada", destino);
                    //Modo Transporte
                    go.putExtra("Coche", irCoche);
                    go.putExtra("Bus", irBus);
                    go.putExtra("Bici", irBici);
                    go.putExtra("Andar", irAndando);


                    startActivity(go);
                } else {
                    Toast.makeText(getApplicationContext(), "Debes introducir direcciones y calcular ruta.", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Fragment Place 1
        placeautocompletesalida = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete);
        placeautocompletesalida.setOnPlaceSelectedListener(new PlaceSelectionListener() {

            @Override
            public void onPlaceSelected(Place place) {

                salida = place.getName().toString();
                punto1 = place;
                Toast.makeText(getApplicationContext(), salida, Toast.LENGTH_LONG);

            }

            @Override
            public void onError(Status status) {

            }
        });

        //Fragment Place 2
        placeautocompletedestino = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete2);
        placeautocompletedestino.setOnPlaceSelectedListener(new PlaceSelectionListener() {


            @Override
            public void onPlaceSelected(Place place) {

                destino = place.getName().toString();
                punto2 = place;

            }

            @Override
            public void onError(Status status) {

            }
        });


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        /**
         * Boton Calcular
         * Calcular entre dos distancias
         */
        calcular = (Button) findViewById(R.id.calculartiempo);
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (salida.equals("") || destino.equals("") || (irCoche == false && irBus == false && irBici == false && irAndando == false)) {

                    Toast.makeText(getApplicationContext(), "Introduce las dos direcciones y transporte", Toast.LENGTH_LONG).show();

                } else {

                    recorridoJson();
                }
            }


        });

        coche.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

                if (irCoche == false) {
                    irCoche = true;
                    irBus = false;
                    irAndando = false;
                    irBici = false;

                    coche.setImageResource(R.drawable.icons8cocheverde);
                    bus.setImageResource(R.drawable.icons8autobusgris);
                    bici.setImageResource(R.drawable.icons8bicigris);
                    andar.setImageResource(R.drawable.icons8caminargris);
                }

            }
        });

        bus.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

                if (irBus == false) {
                    irCoche = false;
                    irBus = true;
                    irAndando = false;
                    irBici = false;

                    coche.setImageResource(R.drawable.icons8cochegris);
                    bus.setImageResource(R.drawable.icons8autobusverde);
                    bici.setImageResource(R.drawable.icons8bicigris);
                    andar.setImageResource(R.drawable.icons8caminargris);
                }
            }
        });

        bici.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                if (irBici == false) {
                    irCoche = false;
                    irBus = false;
                    irAndando = false;
                    irBici = true;

                    coche.setImageResource(R.drawable.icons8cochegris);
                    bus.setImageResource(R.drawable.icons8autobusgris);
                    bici.setImageResource(R.drawable.icons8biciverde);
                    andar.setImageResource(R.drawable.icons8caminargris);
                }
            }
        });

        andar.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                if (irAndando == false) {
                    irCoche = false;
                    irBus = false;
                    irAndando = true;
                    irBici = false;

                    coche.setImageResource(R.drawable.icons8cochegris);
                    bus.setImageResource(R.drawable.icons8autobusgris);
                    bici.setImageResource(R.drawable.icons8bicigris);
                    andar.setImageResource(R.drawable.icons8caminarverde);
                }

            }
        });

    }


    /**
     * Todos estos metodos funcionan para ubicar
     * tu ubicacion en el mapa.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);
            return;
        }

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
            latsal = 0;
            lonsal = 0;
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
     * --------------------------------------------
     */


    /**
     * Creas los parametros de la busqueda Json
     *
     * @param place1
     * @param place2
     * @return
     */
    private String getRequestUrl(Place place1, Place place2) {
        //origen
        String str_org = "";
        String mode = "";
        String time="";
        if (place1 != null) {
            str_org = "origin=" + place1.getLatLng().latitude + "," + place1.getLatLng().longitude;
        } else {
            str_org = "origin=" + latsal + "," + lonsal;
        }
        //destino
        String str_dest = "destination=" + place2.getLatLng().latitude + "," + place2.getLatLng().longitude;
        //sensor
        String sensor = "sensor=false";
        //Modo transporte
        if (irCoche == true) {
            mode = "mode=driving";
        }
        if (irBus == true) {
           /* mode = "mode=transit";
            //Hora bus
            Calendar calendarNow = null;
            int monthDay=0;
            int month=0;
            int day=0;
            int horaDia=0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                calendarNow = new GregorianCalendar(TimeZone.getTimeZone("Europe/Madrid"));
            }
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
               monthDay =calendarNow.get(Calendar.DAY_OF_MONTH);
            }
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                 month = calendarNow.get(Calendar.MONTH);
            }
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                day = calendarNow.get(Calendar.DAY_OF_MONTH);
            }
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                horaDia = calendarNow.get(Calendar.HOUR_OF_DAY);
            }

            if(hora > horaDia){


            }

            time = "arrival_time=";*/
        }
        if (irBici == true) {
            mode = "mode=bicycling";
        }
        if (irAndando == true) {
            mode = "mode=walking";
        }

        if (irBus == false) {
            //Trafico
            time = "departure_time=now";
        }
        //Build the full param
        String param = str_org + "&" + str_dest + "&" + sensor + "&" + mode + "&" + time;
        //Output format
        String output = "json";
        //Create url to request
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + param;
        return url;
    }

    /**
     * Busqueda Json
     * Busca la informacion entre el punto de
     * salida y llegada.
     */
    private void recorridoJson() {
        String path = "";
        DirectionsParser directionsParser = new DirectionsParser();
        StringBuilder stringBuilder;

        if (salida != "0.0,0.0" && destino != "") {

            tiempo.setText("");
            distancia.setText("");

            String url = getRequestUrl(punto1, punto2);
            TaskRequestDirections taskRequestDirections = new TaskRequestDirections();
            taskRequestDirections.execute(url);
            stringBuilder = DirectionsParser.traerContenidoStringBuilder(url);

            tiempo.setText(directionsParser.parsingTiempo(stringBuilder));
            distancia.setText(directionsParser.parsingKM(stringBuilder));


        }


    }

    /**
     * Trae la informacion del Bundle
     * desde otra activity
     */
    private void traerBundle() {
        parametros = getIntent().getExtras();


        lunes = parametros.getBoolean("Lunes");
        martes = parametros.getBoolean("Martes");
        miercoles = parametros.getBoolean("Miercoles");
        jueves = parametros.getBoolean("Jueves");
        viernes = parametros.getBoolean("Viernes");
        sabado = parametros.getBoolean("Sabado");
        domingo = parametros.getBoolean("Domingo");
        hora = parametros.getInt("Hora");
        minuto = parametros.getInt("HMinuto");
    }

    private void recuperarTiempo() {

        if (tiempo.getText().toString().contains("hours")) {
            String[] tiempos = tiempo.getText().toString().split(" hours ");
            try {
                horaRecorrido = Integer.parseInt(tiempos[0]);
            } catch (NumberFormatException e) {


            }


            String[] tiempos2 = tiempos[1].toString().split(" mins");

            try {
                minutosRecorrido = Integer.parseInt(tiempos2[0].toString());
            } catch (NumberFormatException e) {


            }
        } else {

            String[] tiempos = tiempo.getText().toString().split(" mins");

            try {
                minutosRecorrido = Integer.parseInt(tiempos[0].toString());
            } catch (NumberFormatException e) {

            }
        }


    }

    /**
     * TaskRequestDirections
     * Esta clase sirve para ver la informacion entre
     * direcciones
     */
    public class TaskRequestDirections extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String responseString = "";
            try {
                responseString = requestDirection(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Parse json here
            TaskParser taskParser = new TaskParser();
            taskParser.execute(s);
        }


        private String requestDirection(String reqUrl) throws IOException {
            String responseString = "";
            InputStream inputStream = null;
            HttpURLConnection httpURLConnection = null;
            try {
                URL url = new URL(reqUrl);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();

                //Get the response result
                inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuffer stringBuffer = new StringBuffer();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line);
                }

                responseString = stringBuffer.toString();
                bufferedReader.close();
                inputStreamReader.close();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                httpURLConnection.disconnect();
            }
            return responseString;
        }

    }

    /**
     * TaskParser
     * funciona para pintar las polinias de una ruta
     */
    public class TaskParser extends AsyncTask<String, Void, List<List<HashMap<String, String>>>> {


        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... strings) {
            JSONObject jsonObject = null;
            List<List<HashMap<String, String>>> routes = null;
            try {
                jsonObject = new JSONObject(strings[0]);
                DirectionsParser directionsParser = new DirectionsParser();
                routes = directionsParser.parse(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> lists) {
            //Get list route and display it into the map
            ArrayList points = null;
            PolylineOptions polylineOptions = null;


            if (pintado == true) {
                mMap.clear();
                pintado = false;
            }

            for (List<HashMap<String, String>> path : lists) {
                points = new ArrayList();
                polylineOptions = new PolylineOptions();


                for (HashMap<String, String> point : path) {
                    double lat = Double.parseDouble(point.get("lat"));
                    double lon = Double.parseDouble(point.get("lon"));

                    points.add(new LatLng(lat, lon));
                }

                polylineOptions.addAll(points);
                polylineOptions.width(15);
                polylineOptions.color(Color.BLUE);
                polylineOptions.geodesic(true);
            }

            if (polylineOptions != null) {
                mMap.addPolyline(polylineOptions);
                points.clear();
                polylineOptions.getPoints().clear();
                pintado = true;
            }
            //else {Toast.makeText(getApplicationContext(), "Direcciones no encontradas!", Toast.LENGTH_SHORT).show();}

        }


    }

    public void dialog(){



    }
}

