package Rooms;

import Guards.Rango;
import Guards.Turno;
import Prisoners.Prisionero;

import java.time.LocalDate;
import java.util.ArrayList;

public class CeldaComun extends Celda{

    private boolean hasTv;
    private ArrayList<Prisionero> presos;

    /*
    public void agregarPreso(//Parametros de preso){

    }

    public void eliminarPreso(int index){
    }

    public void eliminarPresoDNI(int DNI){
    }

    public Prisionero getPrisioneroPorDNI(int DNI){

        Prisionero p = null;

        for(int i = 0; i < presos.size(); i++){
            p=presos.get(i);
            if(p.equals(DNI)){
                return p;
            }
        }

        return null;
    }
    */


    public Prisionero getPrisioneroPorIndex(int index){
        return presos.get(index);
    }

    public boolean getTv(){
        return hasTv;
    }

    public void asignarTv(){
        this.hasTv = true;
    }

    public CeldaComun(int numeroDeCelda, int capacidad) {
        super(numeroDeCelda, capacidad);
        this.hasTv = false;
        this.presos = new ArrayList<>();
    }
}
