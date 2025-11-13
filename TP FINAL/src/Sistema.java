import Excepciones.AccionInvalidaEx;
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
            Comun nuevo = UtilesMain.agregarGComun();
            registroGuardias.agregar(nuevo.getDni(), nuevo);
            System.out.println("Guardia comun agregado correctamente");
        }catch (Exception e){
            return "Error al agregar guardia comun" + e.getMessage();
        }
        return "Guardia comun agregado con exito";
    }

    public void agregarGuardiaArmado (){


        try{
            Armado nuevo = UtilesMain.guardiaArm();
            registroGuardias.agregar(nuevo.getDni(), nuevo);
            System.out.println("Guardia comun agregado con exito");
        }catch (Exception e){
            System.out.println("Error al agregar guardia armado" + e.getMessage());
        }
    }

    public void agregarGuardiaCapacitadoTaser (){

        try{
            CapacitadoTaser nuevo = UtilesMain.agregarCT();
            registroGuardias.agregar(nuevo.getDni(), nuevo);
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
            try {
                g.cambiarTurno(nuevo);
            }catch (AccionInvalidaEx e){
                System.out.println(e.getMessage());
            }

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
