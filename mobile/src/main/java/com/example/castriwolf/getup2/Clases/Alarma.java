package com.example.castriwolf.getup2.Clases;

import java.time.LocalTime;
import java.util.ArrayList;

public class Alarma {

    private int id_alarma;
    private float lugarSalida;
    private float lugarLlegada;
    private String horaSalida;
    private String horaLlegada;
    private ArrayList<Actividad> actividades;

    public Alarma(int id_alarma, String lugarSalida, String lugarLlegada, String horaSalida, String horaLlegada){};

    public Alarma(int id_alarma,float lugarSalida, float lugarLlegada,String horaSalida ,String horaLlegada) {
        this.id_alarma=id_alarma;
        this.lugarSalida = lugarSalida;
        this.lugarLlegada = lugarLlegada;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        actividades = new ArrayList<Actividad>();
    }

    public int getId_alarma() {
        return id_alarma;
    }

    public float getLugarSalida() {
        return lugarSalida;
    }

    public float getLugarLlegada() {
        return lugarLlegada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public ArrayList<Actividad> getActividades() {
        return actividades;
    }

    public void setLugarSalida(float lugarSalida) {
        this.lugarSalida = lugarSalida;
    }

    public void setLugarLlegada(float lugarLlegada) {
        this.lugarLlegada = lugarLlegada;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public void setActividades(ArrayList<Actividad> actividades) {
        this.actividades = actividades;
    }

    public void crearAlarma(int id_alarma,String lugarSalida, String lugarLlegada, LocalTime horaLlegada){


        Alarma alarma = new Alarma();

    }
}
