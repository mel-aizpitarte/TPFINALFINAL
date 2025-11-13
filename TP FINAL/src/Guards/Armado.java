package Guards;

import Excepciones.PermisoDenegadoEx;

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

    @Override
    public String toString() {
        return "Guards.Armado{" +
                "arma=" + arma +
                "} " + super.toString();
    }
}
