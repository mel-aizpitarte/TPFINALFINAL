package Prisoners;

import Excepciones.AccionInvalidaEx;
import Persona.Persona;
import Rooms.Celda;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class Prisionero extends Persona {
    private LocalDate fechaIngreso;
    private Celda celdaAsignada;
    private CrimenCometido crimenCometido;
    private Seguridad seguridad;
    private int condenaEnmeses; //duracion total de la condena en meses
    private int visitasEsteMes = 0;
    private final int LimiteVisitas = 5;

    public Prisionero(String nombre, String apellido, String dni, int edad, LocalDate fechaNacimiento, LocalDate fechaIngreso, Celda celdaAsignada, CrimenCometido crimenCometido, Seguridad seguridad , int condenaEnmeses) {
        super(nombre, apellido, dni, edad, fechaNacimiento);
        this.fechaIngreso = fechaIngreso;
        this.celdaAsignada = celdaAsignada;
        this.crimenCometido = crimenCometido;
        this.seguridad = seguridad;
        this.condenaEnmeses = condenaEnmeses;
    }

    //getters
    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public int getCondenaEnmeses() {
        return condenaEnmeses;
    }

    public Celda getCeldaAsignada() {
        return celdaAsignada;
    }

    public CrimenCometido getCrimenCometido() {
        return crimenCometido;
    }

    public Seguridad getSeguridad() {
        return seguridad;
    }

    public int getVisitasEsteMes() {
        return visitasEsteMes;
    }

    public int getLimiteVisitas() {
        return LimiteVisitas;
    }

    //Metodos
    //asignar o reasignar celda
    public void asignarCelda (Celda nueva)throws AccionInvalidaEx {
       if (nueva == null){
          throw new AccionInvalidaEx("La celda asignada no puede ser nula");
       }
        this.celdaAsignada = nueva;
        System.out.println("EL prisionero" + getNombre() + "ha sido reasignado a la celda " + nueva.getNumeroDeCelda());
    }

    //cambiar nivel de seguridad
    public void cambiarSeguridad (Seguridad nueva)throws AccionInvalidaEx {
        if (nueva == null){
            throw new AccionInvalidaEx("El nivel de seguridad no puede ser nulo");
        }
        this.seguridad = nueva;
        System.out.println("Nivel de seguridad del prisionero "+ getNombre() + "actualizado a "+ nueva);
    }

    //liberar prisionero
    public void liberar ()throws AccionInvalidaEx {
        if (celdaAsignada == null){
            throw new AccionInvalidaEx("El prisionero no tiene una celda asignada para liberar");
        }
        Celda celdaLiberada = celdaAsignada;
        this.celdaAsignada = null;
        System.out.println("El prisionero "+ getNombre()+ "ha sido liberada de la celda "+ celdaLiberada.getNumeroDeCelda());
    }

    public void solicitarVisita (boolean autorizado)throws AccionInvalidaEx {
        if (celdaAsignada == null){
            throw new AccionInvalidaEx("El prisionero no tiene una celda asignada y no puede recibir visitas");
        }
        if (!autorizado) {
            throw new AccionInvalidaEx("La visita NO está autorizada para este prisionero");
        }
        if (visitasEsteMes >= LimiteVisitas){
            throw new AccionInvalidaEx("El prisionero ya alcanzo el limite de visitas este mes");
        }
        visitasEsteMes++;
    }

    //marca si prisionero finalizo su condena o cuanto le queda
    public String cumplirCondena (){

        //calculamos la fecha en que termina la condena
        //partimos de la fecha de ingreso y le sumamos los meses de la condena.
       LocalDate fechaFin = fechaIngreso.plusMonths(condenaEnmeses);

       // Obtenemos la fecha de hoy para comparar.
       LocalDate hoy = LocalDate.now();

       //Si la fecha actual es posterior a la fecha de fin,
        // significa que ya cumplió la condena.
       if (hoy.isAfter(fechaFin)){
           return "El prisionero "+ getNombre() + "ya cumplio su condena";
       } else{
           // Period.between(hoy, fechaFin) devuelve la diferencia en años/meses/días.
           Period tiempoRestante = Period.between(hoy, fechaFin);
           return "Al prisionero " + getNombre() + "le quedan "+
                   tiempoRestante.getMonths() + "meses y "+
                   tiempoRestante.getDays() + "dias de condena";
       }
    }

    public void registrarVisita()throws AccionInvalidaEx{
        if(visitasEsteMes >= getLimiteVisitas()){
            throw new AccionInvalidaEx("El prisionero ya alcanzó el límite de visitas este mes");
        }
        this.visitasEsteMes++;
    }

    @Override
    public String toString() {
        return "Prisionero{" +
                "fechaIngreso=" + fechaIngreso +
                ", celdaAsignada=" + celdaAsignada +
                ", crimenCometido=" + crimenCometido +
                ", seguridad=" + seguridad +
                ", condenaEnmeses=" + condenaEnmeses +
                ", visitasEsteMes=" + visitasEsteMes +
                ", LimiteVisitas=" + LimiteVisitas +
                "} " + super.toString();
    }
}
