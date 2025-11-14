package Guards;

import org.json.JSONObject;

import java.time.LocalDate;

public class CapacitadoTaser extends Guardia {
    private LocalDate fechaCapacitacion;
    private boolean tieneTaser;

    public CapacitadoTaser(String nombre, String apellido, String dni, int edad, LocalDate fechaNacimiento, int legajo, Turno turno, int numCelda, boolean enServicio, Rango rango, LocalDate fechaCapacitacion, boolean tieneTaser) {
        super(nombre, apellido, dni, edad, fechaNacimiento, legajo, turno, numCelda, enServicio, rango);
        this.fechaCapacitacion = fechaCapacitacion;
        this.tieneTaser = tieneTaser;
    }

    //getters
    public LocalDate getFechaCapacitacion() {
        return fechaCapacitacion;
    }

    //Metodos
    public boolean isTieneTaser() {
        return tieneTaser;
    }

    public boolean asignarTaser (){
        if(!isTieneTaser()){
            this.tieneTaser = true;
            System.out.println("Taser asignado al guardia " + getNombre() + getApellido());
        } else{
            System.out.println("El guardia ya tiene taser");
        }
        return tieneTaser;
    }

    //Para saber si la capacitacion fue hace mas de 1 año
    public boolean verificarCapacitacionVencida (){
        LocalDate hoy = LocalDate.now();
        return fechaCapacitacion.plusYears(1).isBefore(hoy);
    }

    public void actualizarCapacitacion (){
        fechaCapacitacion = LocalDate.now();
        System.out.println("Capacitacion actualizada para " + getNombre() + getApellido());
    }

    //deserializacion
    public static CapacitadoTaser fromJSON(JSONObject obj){
        return  new CapacitadoTaser(
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
                LocalDate.parse(obj.getString("fechaCapacitacion")),
                obj.getBoolean("tieneTaser")
        );
    }

    //serializacion
    @Override
    public JSONObject toJSON() {
        JSONObject obj = super.toJSON();
        obj.put("tipo", "CapacitadoTaser");
        obj.put("fechaCapacitacion", getFechaCapacitacion().toString());
        obj.put("tieneTaser", isTieneTaser());
        return obj;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nFecha capacitación: " + fechaCapacitacion +
                "\nTiene taser: " + tieneTaser +
                "\n----------------------\n";
    }
}
