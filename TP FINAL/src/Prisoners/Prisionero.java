package Prisoners;

import Excepciones.AccionInvalidaEx;
import Persona.Persona;
import Rooms.Celda;
import org.json.JSONObject;

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

    //deserializacion
    public static Prisionero fromJSON(JSONObject obj) {

        // Datos básicos
        String nombre = obj.getString("nombre");
        String apellido = obj.getString("apellido");
        String dni = obj.getString("dni");
        int edad = obj.getInt("edad");
        LocalDate fechaNacimiento = LocalDate.parse(obj.getString("fechaNacimiento"));



        // Datos del prisionero
        LocalDate fechaIngreso = LocalDate.parse(obj.getString("fechaIngreso"));
        int condena = obj.getInt("condenaEnMeses");
        int visitas = obj.getInt("visitasEsteMes");
        Seguridad nivelSeg = Seguridad.valueOf(obj.getString("seguridad"));
        CrimenCometido crimen = CrimenCometido.valueOf(obj.getString("crimenCometido"));

        int numeroCelda = obj.getInt("celdaAsignada");


        Prisionero p = new Prisionero(
                nombre, apellido, dni, edad, fechaNacimiento,
                fechaIngreso,
                null,
                crimen,
                nivelSeg,
                condena
        );

        p.visitasEsteMes = visitas;
        p.numeroCeldaTemporal = numeroCelda;

        return p;
    }

    public Integer numeroCeldaTemporal = null;

    //serializacion
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();

        json.put("nombre", getNombre());
        json.put("apellido", getApellido());
        json.put("dni", getDni());
        json.put("edad", getEdad());
        json.put("fechaNacimiento", getFechaNacimiento().toString());

        json.put("fechaIngreso", fechaIngreso.toString());
        json.put("condenaEnMeses", condenaEnmeses);
        json.put("visitasEsteMes", visitasEsteMes);
        json.put("limiteVisitas", LimiteVisitas);

        // Objetos
        json.put("crimenCometido", crimenCometido.toString());
        json.put("seguridad", seguridad);
        json.put("celdaAsignada", celdaAsignada);

        return json;
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
