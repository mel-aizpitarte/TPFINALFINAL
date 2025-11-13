package Rooms;
import Guards.*;
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
    private Prisionero prisionero;
    private LocalDateTime ultimaInspeccion;


    /// Getters and setters
    public int getNumeroDeCelda() {return numeroDeCelda;}

    public int getCapacidad() {return capacidad;}

    public boolean isOcupado() {
        return ocupado;
    }

    public boolean isLleno() {
        return lleno;
    }

    public String getUltimaInspeccion() {
        return ultimaInspeccion.toString();
    }

    public void setUltimaInspeccion(LocalDateTime ultimaInspeccion) {
        this.ultimaInspeccion = ultimaInspeccion;
    }
    ///  Getters and setters


    public void ChangeLleno() {
        this.lleno = !this.lleno;
    }

    /// Asignar guardias
    public void asignarGuardiaComun(String nombre, String apellido, String dni, int edad, LocalDate fechaNacimiento, int legajo, Turno turno, int numCelda, boolean enServicio, Rango rango, boolean tieneGasPimienta){
        this.guardiaAsignado=new Comun(nombre, apellido, dni, edad, fechaNacimiento, legajo, turno, numCelda, enServicio, rango, tieneGasPimienta);
    }

    public void asignarGuardiaTaser(String nombre, String apellido, String dni, int edad, LocalDate fechaNacimiento, int legajo, Turno turno, int numCelda, boolean enServicio, Rango rango, LocalDate fechaCapacitacion, boolean tieneTaser){
        this.guardiaAsignado=new CapacitadoTaser(nombre, apellido, dni, edad, fechaNacimiento, legajo, turno, numCelda, enServicio, rango, fechaCapacitacion, tieneTaser);
    }

    public void asignarGuardiaArmado(String nombre, String apellido, String dni, int edad, LocalDate fechaNacimiento, int legajo, Turno turno, int numCelda, boolean enServicio, Rango rango, Arma arma){
        this.guardiaAsignado=new Armado(nombre, apellido, dni, edad, fechaNacimiento, legajo, turno, numCelda, enServicio, rango, arma);
    }
    /// Asignar guardias


    /// Falta asignar prisionero


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

}