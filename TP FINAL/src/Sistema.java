import Excepciones.PermisoDenegadoEx;
import Guards.*;
import Prisoners.Prisionero;
import Rooms.Celda;

import java.security.Guard;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Sistema {
    private Manager<Guardia> registroGuardias;
    private Manager<Prisionero> registroPrisioneros;
    private List<Celda> listaCeldas;

    public Sistema() {
        this.registroGuardias = new Manager<>();
        this.registroPrisioneros = new Manager<>();
        this.listaCeldas = new ArrayList<>();
    }

    //Guardias metodos
    //Metodos para agregar los distintos tipos de guardia
    public String agregarGuardiaComun (){

        try{
            UtilesMain.agregarGComun();
        }catch (Exception e){
            return "Error al agregar guardia comun" + e.getMessage();
        }
        return "Guardia comun agregado con exito";
    }

    public void agregarGuardiaArmado (){
        Scanner sc = new Scanner(System.in);

        try{
            System.out.println("Nombre: ");
            String nombre = sc.nextLine();
            System.out.println("Apellido: ");
            String apellido = sc.nextLine();
            System.out.println("DNI: ");
            String dni = sc.nextLine();
            System.out.println("Edad: ");
            int edad = sc.nextInt();
            sc.nextLine();
            System.out.println("Legajo: ");
            int legajo = sc.nextInt();
            sc.nextLine();
            System.out.println("Fecha de nacimiento (yyyy/MM/dd): ");
            LocalDate fechaNacimiento = LocalDate.parse(sc.nextLine());
            System.out.println("Turno (MANIANA/TARDE/NOCHE: ");
            Turno turno = Turno.valueOf(sc.nextLine().toUpperCase());
            System.out.println("Numero de celda asignada: ");
            int numCeldas = sc.nextInt();
            sc.nextLine();
            System.out.println("¿Esta en servicio? (true/false): ");
            boolean enServicio = sc.nextBoolean();
            sc.nextLine();
            System.out.println("Rango (OFICIAL/COMISARIO_GENERAL/COMISARIO_MAYOR/JEFE_DE_SEGURIDAD/DIRECTOR_GENERAL");
            Rango rango = Rango.valueOf(sc.nextLine().toUpperCase());
            System.out.println("Arma asignada (PISTOLA/RIFLE/ESCOPETA");
            Arma arma = Arma.valueOf(sc.nextLine().toUpperCase());

            Guardia g = new Armado(nombre, apellido, dni, edad, fechaNacimiento, legajo, turno, numCeldas, enServicio, rango, arma);
            registroGuardias.agregar(dni, g);
            System.out.println("Guardia comun agregado con exito");

        }catch (Exception e){
            System.out.println("Error al agregar guardia armado" + e.getMessage());
        }
    }

    public void agregarGuardiaCapacitadoTaser (){
        Scanner sc = new Scanner(System.in);

        try{
            System.out.println("Nombre: ");
            String nombre = sc.nextLine();
            System.out.println("Apellido: ");
            String apellido = sc.nextLine();
            System.out.println("DNI: ");
            String dni = sc.nextLine();
            System.out.println("Edad: ");
            int edad = sc.nextInt();
            sc.nextLine();
            System.out.println("Legajo: ");
            int legajo = sc.nextInt();
            sc.nextLine();
            System.out.println("Fecha de nacimiento (yyyy/MM/dd): ");
            LocalDate fechaNacimiento = LocalDate.parse(sc.nextLine());
            System.out.println("Turno (MANIANA/TARDE/NOCHE: ");
            Turno turno = Turno.valueOf(sc.nextLine().toUpperCase());
            System.out.println("Numero de celda asignada: ");
            int numCeldas = sc.nextInt();
            sc.nextLine();
            System.out.println("¿Esta en servicio? (true/false): ");
            boolean enServicio = sc.nextBoolean();
            sc.nextLine();
            System.out.println("Rango (OFICIAL/COMISARIO_GENERAL/COMISARIO_MAYOR/JEFE_DE_SEGURIDAD/DIRECTOR_GENERAL");
            Rango rango = Rango.valueOf(sc.nextLine().toUpperCase());
            System.out.println("¿Tiene Taser? (true/false): ");
            boolean taser = sc.nextBoolean();
            sc.nextLine();

            Guardia g = new CapacitadoTaser(nombre, apellido, dni, edad, fechaNacimiento, legajo, turno, numCeldas, enServicio, rango, LocalDate.now(), taser);
            registroGuardias.agregar(dni, g);
            System.out.println("Guardia comun agregado con exito");

        }catch (Exception e){
            System.out.println("Error al agregar guardia capacitado taser" + e.getMessage());
        }
    }

    public void darDescansoGuardia (){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el DNI del guardia: ");
        String dni = sc.nextLine();

        Guardia g = registroGuardias.obtener(dni);
        if (g != null){
            g.darDescanso();
        } else {
            System.out.println("No se encontro un guardia con ese DNI");
        }
    }

    public void mostrarGuardiasEnServicio (){
        for (Guardia g : registroGuardias.getLista().values()){
            if (g.isEnServicio()){
                System.out.println(g);
            }
        }
    }

    public void mostrarGuardiasFueraDeServicio (){
        for (Guardia g : registroGuardias.getLista().values()){
            if (!g.isEnServicio()){
                System.out.println(g);
            }
        }
    }

    public void cambiarTurnoGuardia (){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el DNI del guardia: ");
        String dni = sc.nextLine();

        Guardia g = registroGuardias.obtener(dni);

        if (g != null){
            System.out.println("Ingrese el nuevo turno (MANIANA/TARDE/NOCHE: ");
            Turno nuevo = Turno.valueOf(sc.nextLine().toUpperCase());
            g.cambiarTurno(nuevo);

        } else {
            System.out.println("Guardia no encontrado");
        }
    }

    public void asignarOcambiarArma (){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el DNI del guardia: ");
        String dni = sc.nextLine();

        Guardia g = registroGuardias.obtener(dni);

        if (g instanceof  Armado armado){
            System.out.println("Ingrese el arma (PISTOLA/RIFLE/ESCOPETA");
            Arma nueva = Arma.valueOf(sc.nextLine().toUpperCase());

            try{
                armado.asignarArma(nueva);
            } catch (PermisoDenegadoEx e){
                System.out.println("Error: " +  e.getMessage());
            }
        } else {
            System.out.println("Ese guardia no es del tipo ARMADO");
        }
    }

    public void asignarGasPimienta (){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el DNI del guardia: ");
        String dni = sc.nextLine();

        Guardia g = registroGuardias.obtener(dni);
        if (g instanceof Comun comun){
            comun.asignarGasPimienta();
        } else {
            System.out.println("Ese guardia no es del tipo COMUN");
        }
    }

    public void asignarOactualizarTaser (){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el DNI del guardia: ");
        String dni = sc.nextLine();

        Guardia g = registroGuardias.obtener(dni);
        if (g instanceof CapacitadoTaser ct){
            if (ct.verificarCapacitacionVencida()){
                ct.actualizarCapacitacion();
            } else {
                ct.asignarTaser();
            }
        } else {
            System.out.println("Ese guardia no es del tipo CAPACITADO EN TASER");
        }
    }

    //mostrar todos los guardias
    public void mostrarGuardias (){
            registroGuardias.mostrar();
    }


    //Prisioneros metodos


    //Celdas metodos
}
