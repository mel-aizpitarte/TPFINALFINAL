package Guards;

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

    @Override
    public String toString() {
        return "Guards.CapacitadoTaser{" +
                "fechaCapacitacion=" + fechaCapacitacion +
                ", tieneTaser=" + tieneTaser +
                "} " + super.toString();
    }
}
