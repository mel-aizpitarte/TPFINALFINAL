package Rooms;

import Excepciones.AccionInvalidaEx;
import Prisoners.Prisionero;

public class ConfinamientoSolitario extends Celda{

    private int diasDeAislamiento;
    private Prisionero prisonero;

    public int getDiasDeAislamiento() {
        return diasDeAislamiento;
    }

    public void extenderDiasDeAislamiento(int diasDeAislamiento) {
        this.diasDeAislamiento = this.diasDeAislamiento + diasDeAislamiento;
    }

    public void terminarAislamiento(){
        this.diasDeAislamiento = 0;
        this.prisonero=null;
    }

    public Prisionero getPrisonero() {
        return prisonero;
    }

    public String agregarPrisonero(Prisionero prisonero) throws AccionInvalidaEx {
        if(!isLleno()) {
            this.prisonero = prisonero;
            return "Prisonero asignado a celda de confinamiento solitario";
        }else{
            throw new AccionInvalidaEx("La celda de confinamiento solitario ya esta ocupada");
        }
    }


    public ConfinamientoSolitario(int numeroDeCelda, int capacidad, Prisionero prisonero, int diasDeAislamiento) {
        super(numeroDeCelda, capacidad);
        this.prisonero = prisonero;
        this.diasDeAislamiento = diasDeAislamiento;
    }
}
