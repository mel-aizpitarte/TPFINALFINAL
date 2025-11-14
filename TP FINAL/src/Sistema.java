import Excepciones.AccionInvalidaEx;
import Excepciones.PermisoDenegadoEx;
import Guards.*;
import Prisoners.Prisionero;
import Rooms.Celda;
import Rooms.CeldaComun;
import Rooms.ConfinamientoSolitario;
import Utiles.UtilesMain;

import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
    private Manager<Guardia> registroGuardias;
    private Manager<Prisionero> registroPrisioneros;
    private ArrayList<Celda> listaCeldas;

    public Sistema() {
        this.registroGuardias = new Manager<>();
        this.registroPrisioneros = new Manager<>();
        this.listaCeldas = new ArrayList<>();
    }

    //Guardias metodos
    //Metodos para agregar los distintos tipos de guardia
    public String agregarGuardiaComun() {

        try {
            Comun nuevo = UtilesMain.agregarGComun();
            registroGuardias.agregar(nuevo.getDni(), nuevo);
            System.out.println("Guardia comun agregado correctamente");
        } catch (Exception e) {
            return "Error al agregar guardia comun" + e.getMessage();
        }
        return "Guardia comun agregado con exito";
    }

    public void agregarGuardiaArmado() {


        try {
            Armado nuevo = UtilesMain.agregarGuardiaArm();
            registroGuardias.agregar(nuevo.getDni(), nuevo);
            System.out.println("Guardia comun agregado con exito");
        } catch (Exception e) {
            System.out.println("Error al agregar guardia armado" + e.getMessage());
        }
    }

    public void agregarGuardiaCapacitadoTaser() {

        try {
            CapacitadoTaser nuevo = UtilesMain.agregarCT();
            registroGuardias.agregar(nuevo.getDni(), nuevo);
            System.out.println("Guardia comun agregado con exito");
        } catch (Exception e) {
            System.out.println("Error al agregar guardia capacitado taser" + e.getMessage());
        }
    }

    public void mostrarGuardiasEnServicio() {
        for (Guardia g : registroGuardias.getLista().values()) {
            if (g.isEnServicio()) {
                System.out.println(g);
            }
        }
    }

    public void mostrarGuardiasFueraDeServicio() {
        for (Guardia g : registroGuardias.getLista().values()) {
            if (!g.isEnServicio()) {
                System.out.println(g);
            }
        }
    }

    public void cambiarTurnoGuardia() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el DNI del guardia: ");
        String dni = sc.nextLine();

        Guardia g = registroGuardias.obtener(dni);

        if (g != null) {

            System.out.println("Ingrese el nuevo turno (MANIANA/TARDE/NOCHE: ");
            Turno nuevo = Turno.valueOf(sc.nextLine().toUpperCase());
            try {
                g.cambiarTurno(nuevo);
            } catch (AccionInvalidaEx e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("Guardia no encontrado");
        }
    }

    public void asignarOcambiarArma() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el DNI del guardia: ");
        String dni = sc.nextLine();

        Guardia g = registroGuardias.obtener(dni);

        if (g instanceof Armado armado) {
            System.out.println("Ingrese el arma (PISTOLA/RIFLE/ESCOPETA");
            Arma nueva = Arma.valueOf(sc.nextLine().toUpperCase());

            try {
                armado.asignarArma(nueva);
            } catch (PermisoDenegadoEx e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Ese guardia no es del tipo ARMADO");
        }
    }

    public void asignarGasPimienta() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el DNI del guardia: ");
        String dni = sc.nextLine();

        Guardia g = registroGuardias.obtener(dni);
        if (g instanceof Comun comun) {
            comun.asignarGasPimienta();
        } else {
            System.out.println("Ese guardia no es del tipo COMUN");
        }
    }

    public void asignarOactualizarTaser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el DNI del guardia: ");
        String dni = sc.nextLine();

        Guardia g = registroGuardias.obtener(dni);
        if (g instanceof CapacitadoTaser ct) {
            if (ct.verificarCapacitacionVencida()) {
                ct.actualizarCapacitacion();
            } else {
                ct.asignarTaser();
            }
        } else {
            System.out.println("Ese guardia no es del tipo CAPACITADO EN TASER");
        }
    }

    //mostrar todos los guardias
    public void mostrarGuardias() {
        registroGuardias.mostrar();
    }


    //Prisioneros metodos
    public void agregarPrisionero() {
        try {
            Prisionero p = UtilesMain.prisionero();
            registroPrisioneros.agregar(p.getDni(), p);
            System.out.println("Prisionero agregado exitosamente");
        } catch (Exception e) {
            System.out.println("Error al agregar prisionero: " + e.getMessage());
        }
    }

    public void mostrarPrisioneros() {
        if (registroPrisioneros.estaVacio()) {
            System.out.println("No hay prisioneros cargados");
            return;
        }
        registroPrisioneros.mostrar();
    }

    /// Para asignar un prisionero a una celda y viseversa le pide al usuario los dias de confinamiento por parametro y el dni por metodo en UtilesMain

    public String asignarConfinamientoSolitarioAPris(int diasDeConfinamiento) {
        try {

            String dni = UtilesMain.pedirDni();
            Prisionero p = registroPrisioneros.obtener(dni);

            if (p == null) {
                throw new AccionInvalidaEx("No se encontro el prisionero con el DNI: " + dni);
            }

            int numCelda = UtilesMain.numCelda();
            CeldaComun c = null;
            for (int i = 0; i < numCelda; i++) {
                if (numCelda == listaCeldas.get(i).getNumeroDeCelda()) {
                    c = (CeldaComun) listaCeldas.get(i);
                }
            }

            if (c == null) {
                throw new AccionInvalidaEx("No se encontró la celda con número: " + numCelda);
            } else if (c.isLleno()) {
                throw new AccionInvalidaEx("La celda está llena");
            } else {
                if (c.getPrisioneroPorDNI(dni) == null) {
                    registroPrisioneros.obtener(dni).asignarCelda(c);
                    for (int i = 0; i < listaCeldas.size(); i++) {
                        if (listaCeldas.get(i).getNumeroDeCelda() == c.getNumeroDeCelda()) {
                            ((ConfinamientoSolitario) listaCeldas.get(i)).agregarPrisonero(p, diasDeConfinamiento);
                        }
                    }
                }
            }

            return "Prisionero" + p.getNombre() + "asignado a la celda" + numCelda;

        } catch (AccionInvalidaEx e) {
            return "No se pudo asignar la celda: " + e.getMessage();
        } catch (Exception e) {
            return "Ocurrio un error: " + e.getMessage();
        }
    }

    /// Para asignar prisionero a celda y viseversa, se le pide al usuario por metodo en UtilesMain el DNI

    public String asignarCeldaAPris() {

        try {

            String dni = UtilesMain.pedirDni();
            Prisionero p = registroPrisioneros.obtener(dni);

            if (p == null) {
                throw new AccionInvalidaEx("No se encontro el prisionero con el DNI: " + dni);
            }

            int numCelda = UtilesMain.numCelda();
            CeldaComun c = null;
            for (int i = 0; i < numCelda; i++) {
                if (numCelda == listaCeldas.get(i).getNumeroDeCelda()) {
                    c = (CeldaComun) listaCeldas.get(i);
                }
            }

            if (c == null) {
                throw new AccionInvalidaEx("No se encontró la celda con número: " + numCelda);
            } else if (c.isLleno()) {
                throw new AccionInvalidaEx("La celda está llena");
            } else {
                if (c.getPrisioneroPorDNI(dni) == null) {
                    registroPrisioneros.obtener(dni).asignarCelda(c);
                    for (int i = 0; i < listaCeldas.size(); i++) {
                        if (listaCeldas.get(i).getNumeroDeCelda() == c.getNumeroDeCelda()) {
                            ((CeldaComun) listaCeldas.get(i)).agregarPreso(p.getNombre(), p.getApellido(), p.getDni(), p.getEdad(), p.getFechaNacimiento(), p.getFechaIngreso(), c, p.getCrimenCometido(), p.getSeguridad(), p.getCondenaEnmeses());
                        }
                    }
                }
            }

            return "Prisionero" + p.getNombre() + "asignado a la celda" + numCelda;

        } catch (AccionInvalidaEx e) {
            return "No se pudo asignar la celda: " + e.getMessage();
        } catch (Exception e) {
            return "Ocurrio un error: " + e.getMessage();
        }

    }

    public void solicitarVis() {

        String dni = UtilesMain.pedirDni();
        Prisionero p = registroPrisioneros.obtener(dni);

        if (p == null) {
            System.out.println("Prisionero no encontrado");
            return;
        }

        try {
            p.solicitarVisita(true);
            p.registrarVisita();
            System.out.println("Visita registrada correctamente. Total de visitas este mes: " + p.getVisitasEsteMes());
        } catch (AccionInvalidaEx e) {
            System.out.println("No se pudo registrar la visita: " + e.getMessage());
        }

    }

    public void registrarVis() {
        String dni = UtilesMain.pedirDni();
        Prisionero p = registroPrisioneros.obtener(dni);

        if (p == null) {
            System.out.println("Prisionero no encontrado");
            return;
        }

        try {
            p.registrarVisita();
            System.out.println("Visita registrada");
        } catch (AccionInvalidaEx e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarVisitasPrisionero() {
        String dni = UtilesMain.pedirDni();
        Prisionero p = registroPrisioneros.obtener(dni);
        if (p == null) {
            System.out.println("No se encontro el prisionero con DNI: " + dni);
            return;
        }
        System.out.println("------ Información de Visitas del Prisionero ------");
        System.out.println("Nombre: " + p.getNombre() + " " + p.getApellido());
        System.out.println("DNI: " + dni);
        System.out.println("Visitas realizadas este mes: " + p.getVisitasEsteMes());
        System.out.println("Límite de visitas por mes: " + p.getLimiteVisitas());

        int restantes = p.getLimiteVisitas() - p.getVisitasEsteMes();
        if (restantes > 0) {
            System.out.println("Visitas restantes este mes: " + restantes);
        } else {
            System.out.println("El prisionero ya alcanzó el límite de visitas este mes");
        }
    }

    public String mostrarTiempoRestante() {
        String dni = UtilesMain.pedirDni();
        Prisionero p = registroPrisioneros.obtener(dni);
        if (p == null) {
            return "No se encontro el prisionero con DNI: " + dni;
        }
        return p.cumplirCondena();
    }

    /// Celdas metodos
    public String cargarNuevaCeldaComun(int numeroDeCelda, int capacidad) {

        for (int i = 0; i < listaCeldas.size(); i++) {
            if (listaCeldas.get(i).getNumeroDeCelda() == numeroDeCelda) {
                return "Esa celda ya existe y esta cargada al sistema";
            }
        }

        listaCeldas.add(new CeldaComun(numeroDeCelda, capacidad));
        return "Celda cargada correctamente";
    }

    public String cargarNuevaCeldaConfinamientoSolitario(int numeroDeCelda, int capacidad) {
        for (int i = 0; i < listaCeldas.size(); i++) {
            if (listaCeldas.get(i).getNumeroDeCelda() == numeroDeCelda) {
                return "Esa celda ya existe y esta cargada al sistema";
            }
        }

        listaCeldas.add(new ConfinamientoSolitario(numeroDeCelda, capacidad));
        return "Celda cargada correctamente";
    }



    public String mostrarCelda(int numCelda){
        if(!listaCeldas.isEmpty()){
            for (Celda listaCelda : listaCeldas) {
                if (listaCelda.getNumeroDeCelda() == numCelda) {
                    if (listaCelda instanceof ConfinamientoSolitario) {
                        return ((ConfinamientoSolitario) listaCelda).toString();
                    } else {
                        return ((CeldaComun) listaCelda).toString();
                    }
                }
            }
            return "No se encontro la celda";
        }else{
            return "No hay celdas registradas";
        }
    }


}
