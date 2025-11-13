package Rooms;
import Guards.Guardia;
import Guards.Rango;
import Guards.Turno;
import Prisoners.Prisionero;


import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDateTime;


public abstract class Celda implements Cuarentena{

    private boolean flag;
    private boolean ocupado;
    private boolean lleno;
    private int numeroDeCelda;
    private int capacidad;
    private Guardia guardiaAsignado;
    private LocalDateTime ultimaInspeccion;



    public boolean isOcupado() {
        return ocupado;
    }

    public void ChangeOcupado() {
        if(this.ocupado) {
            this.ocupado = false;
        }else {
            this.ocupado = true;
        }
    }

    public boolean isLleno() {
        return lleno;
    }

    public void ChangeLleno() {
        if(this.lleno) {
            this.lleno = false;
        }else {
            this.lleno = true;
        }
    }

    public String getUltimaInspeccion() {
        return ultimaInspeccion.toString();
    }

    public void setUltimaInspeccion(LocalDateTime ultimaInspeccion) {
        this.ultimaInspeccion = ultimaInspeccion;
    }

    public void asignarGuardia(String nombre, String apellido, String dni, int edad, LocalDate fechaNacimiento, int legajo, Turno turno, int numPabellon, boolean enServicio, Rango rango){
        guardiaAsignado=new Guardia(nombre, apellido, dni, edad, fechaNacimiento, legajo, turno, numPabellon, enServicio, rango);
    }


    public Celda(int numeroDeCelda, int capacidad) {
        this.flag=false;
        this.numeroDeCelda = numeroDeCelda;
        this.capacidad = capacidad;
    }

    @Override
    public void cuarentena() {
        if(this.flag) {
            this.flag=false;
        }else  {
            this.flag=true;
        }
    }

    /*Cuando prisionero este realizado, hace falta hacer un constructor para meter directamente prisioneros a la celda */
}