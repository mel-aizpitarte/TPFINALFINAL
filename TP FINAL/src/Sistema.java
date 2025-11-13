import Excepciones.AccionInvalidaEx;
import Excepciones.PermisoDenegadoEx;
import Guards.*;
import Prisoners.Prisionero;
import Rooms.Celda;
import Utiles.UtilesMain;

import java.util.ArrayList;
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
    public void agregarGuardiaComun (){

        try{
            Comun nuevo = UtilesMain.agregarGComun();
            registroGuardias.agregar(nuevo.getDni(), nuevo);
            System.out.println("Guardia comun agregado correctamente");
        }catch (Exception e){
            System.out.println("Error al crear el guardia comun: "+ e.getMessage());
        }
    }

    public void agregarGuardiaArmado (){

        try{
            Armado nuevo = UtilesMain.agregarGuardiaArm();
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
    public void agregarPrisionero (){
        try{
            Prisionero p = UtilesMain.prisionero();
            registroPrisioneros.agregar(p.getDni(),p);
            System.out.println("Prisionero agregado exitosamente");
        }catch (Exception e){
            System.out.println("Error al agregar prisionero: " + e.getMessage());
        }
    }

    public void mostrarPrisioneros(){
        if (registroPrisioneros.estaVacio()) {
            System.out.println("No hay prisioneros cargados");
            return;
        }
        registroPrisioneros.mostrar();
    }

    public void asignarCeldaAPris (){

        try{
            String dni =  UtilesMain.pedirDni();
            Prisionero p = registroPrisioneros.obtener(dni);

            if (p == null){
                System.out.println("No se encontro el prisionero con el DNI: "+ dni);
                return;
            }

            int numCelda = UtilesMain.numCelda();
            Celda c = listaCeldas.get(numCelda);

            if (c == null) {
                System.out.println("No se encontró la celda con número: " + numCelda);
                return;
            }

            if (c.isOcupado()) {
                System.out.println("La celda ya está ocupada.");
                return;
            }

            p.asignarCelda(c);
            c. ;//TERMINAR IVAN
            System.out.println("Prisionero" + p.getNombre()+ "asignado a la celda"+ numCelda);

        } catch (AccionInvalidaEx e){
            System.out.println("No se pudo asignar la celda: " + e.getMessage());
        } catch (Exception e){
            System.out.println("Ocurrio un error: "+e.getMessage());
        }

    }

    public void solicitarVis (){
        String dni =  UtilesMain.pedirDni();
        Prisionero p = registroPrisioneros.obtener(dni);

        if (p == null){
            System.out.println("Prisionero no encontrado");
            return;
        }

        try{
            p.solicitarVisita(true);
            p.registrarVisita();
            System.out.println("Visita registrada correctamente. Total de visitas este mes: " + p.getVisitasEsteMes());
        } catch (AccionInvalidaEx e){
            System.out.println("No se pudo registrar la visita: " + e.getMessage());
        }

    }

    public void registrarVis (){
        String dni =  UtilesMain.pedirDni();
        Prisionero p = registroPrisioneros.obtener(dni);

        if (p == null){
            System.out.println("Prisionero no encontrado");
            return;
        }

        try{
            p.registrarVisita();
            System.out.println("Visita registrada");
        }catch (AccionInvalidaEx e){
            System.out.println(e.getMessage());
        }
    }

    public void mostrarVisitasPrisionero (){
      String dni = UtilesMain.pedirDni();
      Prisionero p = registroPrisioneros.obtener(dni);
      if (p == null){
          System.out.println("No se encontro el prisionero con DNI: " + dni);
          return;
      }
        System.out.println("------ Información de Visitas del Prisionero ------");
        System.out.println("Nombre: " + p.getNombre() + " " + p.getApellido());
        System.out.println("DNI: " + dni);
        System.out.println("Visitas realizadas este mes: " + p.getVisitasEsteMes());
        System.out.println("Límite de visitas por mes: " + p.getLimiteVisitas());

        int restantes = p.getLimiteVisitas() - p.getVisitasEsteMes();
        if (restantes > 0){
            System.out.println("Visitas restantes este mes: " + restantes);
        } else {
            System.out.println("El prisionero ya alcanzó el límite de visitas este mes");
        }
    }

    public void mostrarTiempoRestante (){
        String dni =  UtilesMain.pedirDni();
        Prisionero p = registroPrisioneros.obtener(dni);
        if (p == null){
            System.out.println("No se encontro el prisionero con DNI: " + dni);
            return;
        }
        System.out.println(p.cumplirCondena());
    }

    //Celdas metodos
}
