package Guards;

import Excepciones.AccionInvalidaEx;
import Rooms.Celda;
import org.json.JSONObject;

import java.time.LocalDate;

public class Comun extends Guardia {
    private boolean tieneGasPimienta;

    public Comun(String nombre, String apellido, String dni, int edad, LocalDate fechaNacimiento, int legajo, Turno turno, int numCelda, boolean enServicio, Rango rango, boolean tieneGasPimienta) {
        super(nombre, apellido, dni, edad, fechaNacimiento, legajo, turno, numCelda, enServicio, rango);
        this.tieneGasPimienta = tieneGasPimienta;
    }

    //getters
    public boolean isTieneGasPimienta() {
        return tieneGasPimienta;
    }

    //Metodos
    public boolean asignarGasPimienta (){
        if (!tieneGasPimienta){
            this.tieneGasPimienta = true;
            System.out.println("Gas pimienta asignado a " + getNombre() + getApellido());
        } else{
            System.out.println("El guardia ya tiene gas pimienta");
        }
        return tieneGasPimienta;
    }

    public void patrullar (){
        if (isEnServicio()){
            System.out.println("El guardia "+ getNombre() + "esta patrullando la celda " + getNumCelda());
        } else{
            System.out.println("No puede patrullar, esta en descanso");
        }
    }

    //deserializacion
    public static Comun fromJSON(JSONObject obj){
        return  new Comun(
          obj.getString("nombre"),
          obj.getString("apellido"),
          obj.getString("dni"),
          obj.getInt("edad"),
          LocalDate.parse(obj.getString("fechaNacimiento")),
          obj.getInt("legajo"),
          Turno.valueOf(obj.getString("turno")),
          obj.getInt("numCelda"),
          obj.getBoolean("enServicio"),
          Rango.valueOf(obj.getString("rango")),
          obj.getBoolean("tieneGasPimienta")
        );
    }

    //serializacion
    @Override
    public JSONObject toJSON() {
        JSONObject obj = super.toJSON();
        obj.put("tipo", "Comun");
        obj.put("tieneGasPimienta", isTieneGasPimienta());
        return obj;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nTiene gas pimienta: " + tieneGasPimienta +
                "\n----------------------\n";
    }
}
