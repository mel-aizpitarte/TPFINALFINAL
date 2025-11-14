package Prisoners;

import Excepciones.AccionInvalidaEx;
import org.json.JSONObject;

import java.time.LocalDate;

public class Visita {
    private int id;
    private LocalDate fecha;
    private boolean estaAutorizado;
    private String dniPresoAvisitar;
    private String nombreVisitante;
    private int legajoGuardia;
    private int cantidadVisitas;

    public Visita(int id, LocalDate fecha, boolean estaAutorizado, String dniPresoAvisitar, String nombreVisitante, int legajoGuardia, int cantidadVisitas) {
        this.id = id;
        this.fecha = fecha;
        this.estaAutorizado = estaAutorizado;
        this.dniPresoAvisitar = dniPresoAvisitar;
        this.nombreVisitante = nombreVisitante;
        this.legajoGuardia = legajoGuardia;
        this.cantidadVisitas = cantidadVisitas;
    }

    //getters
    public int getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public boolean isEstaAutorizado() {
        return estaAutorizado;
    }

    public String getDniPresoAvisitar() {
        return dniPresoAvisitar;
    }

    public String getNombreVisitante() {
        return nombreVisitante;
    }

    public int getLegajoGuardia() {
        return legajoGuardia;
    }

    public int getCantidadVisitas() {
        return cantidadVisitas;
    }

    //Metodos
    //autorizar la visita
    public void autorizar ()throws AccionInvalidaEx{
        if (estaAutorizado){
            throw new AccionInvalidaEx("La visita ya esta autorizada");
        }
        this.estaAutorizado = true;
        System.out.println("Visita de "+ getNombreVisitante()+ "autorizada correctamente");
    }

    //registrar una nueva visita
    public void registrarVisita(Prisionero p)throws AccionInvalidaEx{
        if (!estaAutorizado){
            throw new AccionInvalidaEx("No se puede registrar una visita no autorizada");
        }
        if (p.getVisitasEsteMes() >= p.getLimiteVisitas())
        {
            throw new AccionInvalidaEx("El prisionero ya alcanzo el limite de visitas este mes");
        }
        p.registrarVisita();
        System.out.println("Nueva visita registrada. Total: "+ p.getVisitasEsteMes());
    }

    @Override
    public String toString() {
        return "\nVisita {" +
                "\n  id = " + id +
                "\n  fecha = " + fecha +
                "\n  estaAutorizado = " + estaAutorizado +
                "\n  dniPresoAvisitar = '" + dniPresoAvisitar + '\'' +
                "\n  nombreVisitante = '" + nombreVisitante + '\'' +
                "\n  legajoGuardia = " + legajoGuardia +
                "\n  cantidadVisitas = " + cantidadVisitas +
                "\n}\n";
    }
}
