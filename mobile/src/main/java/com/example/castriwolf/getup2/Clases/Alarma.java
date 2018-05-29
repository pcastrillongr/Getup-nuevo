package com.example.castriwolf.getup2.Clases;

import java.time.LocalTime;
import java.util.ArrayList;

public class Alarma {

    private int id_alarma;
    private String lugarSalida;
    private String lugarLlegada;
    private int horaSalida;
    private int minutoSalida;
    private int minutoLlegada;
    private int horaLlegada;
    private ArrayList<Actividad> actividades;


    public Alarma(int id_alarma, String lugarSalida, String lugarLlegada, int horaSalida, int minutoSalida, int horaLlegada, int minutoLlegada) {
        this.id_alarma = id_alarma;
        this.lugarSalida = lugarSalida;
        this.lugarLlegada = lugarLlegada;
        this.horaSalida = horaSalida;
        this.minutoSalida = minutoSalida;
        this.horaLlegada = horaLlegada;
        this.minutoLlegada = minutoLlegada;
        actividades = new ArrayList<Actividad>();
    }

    public int getMinutoLlegada() {return minutoLlegada;}

    public void setMinutoLlegada(int minutoLlegada) {this.minutoLlegada = minutoLlegada;}

    public void setHoraLlegada(int horaLlegada) {this.horaLlegada = horaLlegada;}

    public int getId_alarma() {return id_alarma;}

    public void setId_alarma(int id_alarma) {this.id_alarma = id_alarma;}

    public void setHoraSalida(int horaSalida) {this.horaSalida = horaSalida;}

    public int getMinutoSalida() {return minutoSalida;}

    public void setMinutoSalida(int minutoSalida) {this.minutoSalida = minutoSalida;}

    public String getLugarSalida() {
        return lugarSalida;
    }

    public String getLugarLlegada() {
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

    public void setLugarSalida(String lugarSalida) {
        this.lugarSalida = lugarSalida;
    }

    public void setLugarLlegada(String lugarLlegada) {
        this.lugarLlegada = lugarLlegada;
    }

    public void setActividades(ArrayList<Actividad> actividades) {
        this.actividades = actividades;
    }

    public void crearAlarma(int id_alarma, String lugarSalida, String lugarLlegada, LocalTime horaLlegada) {


    }
}
