package Guards;

import Persona.Persona;

import java.time.LocalDate;

public abstract class Guardia extends Persona {
    private int legajo;
    private Turno turno;
    private int numCelda;
    private boolean enServicio;
    private Rango rango;

    public Guardia(String nombre, String apellido, String dni, int edad, LocalDate fechaNacimiento, int legajo, Turno turno, int numCelda, boolean enServicio, Rango rango) {
        super(nombre, apellido, dni, edad, fechaNacimiento);
        this.legajo = legajo;
        this.turno = turno;
        this.numCelda = numCelda;
        this.enServicio = enServicio;
        this.rango = rango;
    }

    //getters
    public int getLegajo() {
        return legajo;
    }

    public Turno getTurno() {
        return turno;
    }

    public int getNumCelda() {
        return numCelda;
    }

    public boolean isEnServicio() {
        return enServicio;
    }

    public Rango getRango() {
        return rango;
    }

    public void setEnServicio(boolean enServicio) {
        this.enServicio = enServicio;
    }

    //si esta en servicio le doy descanso
    public abstract boolean darDescanso ();

    public void iniciarServicio (){
        if (!enServicio){
            enServicio = true;
            System.out.println("El guardia "+ getNombre() + "volvio al servicio");
        } else{
            System.out.println("El guardia "+ getNombre()+ "ya esta en servicio");
        }
    }

    public void mostrarInfoDetallada (){
        System.out.println("------ Informacion del Guardia ------");
        System.out.println("Nombre: " + getNombre());
        System.out.println("Apellido: " + getApellido());
        System.out.println("DNI: " + getDni());
        System.out.println("Legajo: " + getLegajo());
        System.out.println("Rango: " + getRango());
    }

    public void cambiarTurno (Turno nuevoTurno){
        if (!enServicio){
            this.turno = nuevoTurno;
            System.out.println("Turno cambiado a " + nuevoTurno);
        } else {
            System.out.println("No se puede cambiar turno mientras esta en servicio");
        }
    }

    @Override
    public String toString() {
        return "Guardia{" +
                "legajo=" + legajo +
                ", turno=" + turno +
                ", numCelda=" + numCelda +
                ", enServicio=" + enServicio +
                ", rango=" + rango +
                "} " + super.toString();
    }
}


