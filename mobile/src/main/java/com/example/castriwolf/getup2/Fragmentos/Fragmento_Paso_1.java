package com.example.castriwolf.getup2.Fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.castriwolf.getup2.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

public class Fragmento_Paso_1 extends Fragment {

    private static EditText salida;
    private static EditText llegada;
    private Button boton;
    private android.app.Fragment fragment;
    private GoogleMap googleMap;
    private MapView mapView;
    private Bundle mBundle;

    private static final String ARG_SECTION_NUMBER = "section_number";

    public Fragmento_Paso_1() {
    }

    public static Fragmento_Paso_1 newInstance(int sectionNumber) {

        Fragmento_Paso_1 fragment = new Fragmento_Paso_1();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString("salida", String.valueOf(salida));
        args.putString("llegada",String.valueOf(llegada));
        fragment.setArguments(args);
        return fragment;
    }
}
