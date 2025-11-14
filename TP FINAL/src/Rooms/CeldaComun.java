package Rooms;


import Interfaces.Cuarentena;
import Prisoners.CrimenCometido;
import Prisoners.Prisionero;
import Prisoners.Seguridad;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CeldaComun extends Celda {

    private boolean hasTv;
    private ArrayList<Prisionero> presos;


    public String agregarPreso(String nombre, String apellido, String dni, int edad, LocalDate fechaNacimiento, LocalDate fechaIngreso, Celda celdaAsignada, CrimenCometido crimenCometido, Seguridad seguridad , int condenaEnmeses){

        for(Prisionero p : presos){
            if(p.getDni().equals(dni)){
                return "Este prisionero ya esta asignado a esta celda";
            }
        }

        if(presos.size()==getCapacidad() || getFlag()){
            return "Esta celda esta llena, no se ha asignado al prisionero";
        }else{
            presos.add(new Prisionero(nombre, apellido, dni, edad, fechaNacimiento, fechaIngreso, celdaAsignada, crimenCometido, seguridad, condenaEnmeses));
            if(presos.size()==getCapacidad()) {
                setLleno();
                return "Se ha asignado al prisionero correctamente, la celda ahora esta llena";
            }else{
                return "Se ha asignado al prisionero correctamente, todavia quedan " + (getCapacidad()- presos.size()) + " espacios";
            }
        }

    }

    public String removerPresoDNI(String DNI){
        if(presos.isEmpty()) {
            return "La celda esta vacia, no se encuentra el prisionero";
        }else{
            for(Prisionero p : presos) {
                if(p.getDni().equals(DNI)){
                    presos.remove(p);
                    return "Se ha removido el prisionero correctamente";
                }
            }
        }
        return "No se encuentra al prisionero, intente denuevo";
    }

    public Prisionero getPrisioneroPorDNI(String DNI){

        for(Prisionero p : presos) {
            if(p.getDni().equals(DNI)){
                return p;
            }
        }

        return null;
    }

    public String mostrarPresoIndex(int index){
        return presos.get(index).toString();
    }

    //deserializar
    public static CeldaComun fromJSON(JSONObject obj) {
        int numero = obj.getInt("numeroDeCelda");
        int capacidad = obj.getInt("capacidad");
        CeldaComun celda = new CeldaComun(numero, capacidad);

        celda.setUltimaInspeccion(LocalDateTime.parse(obj.getString("ultimaInspeccion")));
        celda.setLleno(); // si en el JSON viene lleno, ajustamos

        celda.hasTv = obj.getBoolean("hasTv"); // directamente

        JSONArray arrPresos = obj.getJSONArray("presos");
        for(int i = 0; i < arrPresos.length(); i++){
            Prisionero p = Prisionero.fromJSON(arrPresos.getJSONObject(i));
            celda.agregarPreso(p.getNombre(), p.getApellido(), p.getDni(), p.getEdad(),
                    p.getFechaNacimiento(), p.getFechaIngreso(), celda, p.getCrimenCometido(),
                    p.getSeguridad(), p.getCondenaEnmeses());
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
        obj.put("tipo", "CeldaComun");
        obj.put("hasTv", hasTv);

        JSONArray arrPresos = new JSONArray();
        for (Prisionero p : presos) {
            arrPresos.put(p.toJSON());
        }
        obj.put("presos", arrPresos);

        return obj;
    }

    /// Getter setter
    public boolean getTv(){return hasTv;}
    public void asignarTv(){this.hasTv = true;}
    /// Getter setter


    public CeldaComun(int numeroDeCelda, int capacidad) {
        super(numeroDeCelda, capacidad);
        this.hasTv = false;
        this.presos = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Celda Comun: {" + super.toString() + ", hasTv=" + hasTv + ", presos=" + presos + '}';
    }
}