package Guards;

import Excepciones.PermisoDenegadoEx;
import org.json.JSONObject;

import java.time.LocalDate;

public class Armado extends Guardia {
    private Arma arma;

    public Armado(String nombre, String apellido, String dni, int edad, LocalDate fechaNacimiento, int legajo, Turno turno, int numCelda, boolean enServicio, Rango rango, Arma arma){
        if (rango == Rango.OFICIAL) {
            super(nombre, apellido, dni, edad, fechaNacimiento, legajo, turno, numCelda, enServicio, Rango.COMISARIO_MAYOR);
            this.arma = arma;
        }else{
            super(nombre, apellido, dni, edad, fechaNacimiento, legajo, turno, numCelda, enServicio, rango);
            this.arma = arma;
        }
    }

    public Arma getArma() {
        return arma;
    }

    //Metodos
    //asignar o reasignar arma
    public void asignarArma (Arma nuevaArma)throws PermisoDenegadoEx {
        if (getRango() == Rango.COMISARIO_MAYOR){
            throw new PermisoDenegadoEx("El guardia" + getNombre() + getApellido() + "no tiene rango suficiente para cambiar su arma");
        }
        if (arma == null){
          this.arma = nuevaArma;
          System.out.println("Se asigno " + nuevaArma + "al guardia "+ getNombre() + getApellido());
      } else{
          System.out.println("El guardia ya tiene un arma " + this.arma);
      }
    }

    public void retirarArma (){
        if (arma != null){
            System.out.println("Se retiro " + arma + "del guardia " + getNombre() + getApellido());
        } else{
            System.out.println("El guardia no tenia un arma asignada");
        }
    }

    //deserializacion
    public static Armado fromJSON(JSONObject obj){
        return  new Armado(
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
          Arma.valueOf(obj.getString("arma"))
        );
    }

    //serializacion
    @Override
    public JSONObject toJSON() {
        JSONObject obj = super.toJSON();
        obj.put("tipo", "Armado");
        obj.put("arma" , getArma().toString());
        return obj;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nArma: " + arma +
                "\n----------------------\n";
    }
}
