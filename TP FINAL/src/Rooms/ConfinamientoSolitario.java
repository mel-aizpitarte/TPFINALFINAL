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

    public String terminarAislamiento(){
        if(isLleno()) {
            this.diasDeAislamiento = 0;
            this.prisonero = null;
            setLleno();
            return "Se ha removido al prisionero correctamente";
        }else{
            return "La celda de confinamiento solitario ya esta vacia";
        }
    }

    public Prisionero getPrisonero() {
        if(this.prisonero==null){
            return null;
        }
        return prisonero;
    }

    public String agregarPrisonero(Prisionero prisonero, int diasDeAislamiento) throws AccionInvalidaEx {
        if(!isLleno()) {
            this.prisonero = prisonero;
            setLleno();
            extenderDiasDeAislamiento(diasDeAislamiento);
            return "Prisonero asignado a celda de confinamiento solitario";
        }else{
            throw new AccionInvalidaEx("La celda de confinamiento solitario ya esta ocupada");
        }
    }

    public ConfinamientoSolitario(int numeroDeCelda, int capacidad) {
        super(numeroDeCelda, capacidad);
        this.diasDeAislamiento = 0;
    }

    @Override
    public String toString() {
        if(prisonero!=null) {
            return "ConfinamientoSolitario{" + super.toString() +
                    ", Dias de Aislamiento=" + diasDeAislamiento +
                    ", Prisonero=" + prisonero.toString() +
                    '}';
        }else{
            return "Confinamiento Solitario:{" + super.toString() + '}';
        }
    }
}
