package com.example.castriwolf.getup2.Clases;

import java.time.LocalTime;
import java.util.ArrayList;

public class Alarma {

    private int id_alarma;
    private float lugarSalida;
    private float lugarLlegada;
    private int horaSalida;
    private int horaLlegada;
    private ArrayList<Actividad> actividades;

    public Alarma(){};

    public Alarma(int id_alarma,float lugarSalida, float lugarLlegada,int horaSalida ,int horaLlegada) {
        this.id_alarma=id_alarma;
        this.lugarSalida = lugarSalida;
        this.lugarLlegada = lugarLlegada;
        this.lugarSalida = lugarSalida;
        this.horaLlegada = horaLlegada;
        actividades = new ArrayList<Actividad>();
    }

    public float getLugarSalida() {
        return lugarSalida;
    }

    public float getLugarLlegada() {
        return lugarLlegada;
    }

    public int getHoraSalida() {
        return horaSalida;
    }

    public int getHoraLlegada() {
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

    public void setHoraSalida(int horaSalida) {
        this.horaSalida = horaSalida;
    }

    public void setHoraLlegada(int horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public void setActividades(ArrayList<Actividad> actividades) {
        this.actividades = actividades;
    }

    public void crearAlarma(int id_alarma,String lugarSalida, String lugarLlegada, LocalTime horaLlegada){


        Alarma alarma = new Alarma();

    }
}
