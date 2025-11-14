package Rooms;

import Excepciones.AccionInvalidaEx;
import Interfaces.Cuarentena;
import Prisoners.Prisionero;
import org.json.JSONObject;

import java.time.LocalDateTime;

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
        if(!isLleno() || getFlag()) {
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

    //deserializar
    public static ConfinamientoSolitario fromJSON(JSONObject obj) {
        int numero = obj.getInt("numeroDeCelda");
        int capacidad = obj.getInt("capacidad");
        ConfinamientoSolitario celda = new ConfinamientoSolitario(numero, capacidad);

        celda.setUltimaInspeccion(LocalDateTime.parse(obj.getString("ultimaInspeccion")));
        celda.setLleno();

        Prisionero p = Prisionero.fromJSON(obj.getJSONObject("prisonero"));
        try {
            celda.agregarPrisonero(p, obj.getInt("diasDeAislamiento"));
        } catch (AccionInvalidaEx e) {
            e.printStackTrace();
        }

        return celda;
    }

    @Override
    public void cuarentena() {
        setFlag();
    }

    //serializar
    public JSONObject toJSON() {
        JSONObject obj = super.toJSON(); // campos comunes de Celda
        obj.put("tipo", "ConfinamientoSolitario");
        obj.put("diasDeAislamiento", diasDeAislamiento);

        if (prisonero != null) {
            obj.put("prisonero", prisonero.toJSON());
        }

        return obj;
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