import Excepciones.AccionInvalidaEx;
import Excepciones.PermisoDenegadoEx;
import Guards.*;
import Prisoners.Prisionero;
import Rooms.Celda;
import Rooms.CeldaComun;
import Rooms.ConfinamientoSolitario;
import Utiles.JSONUtiles;
import Utiles.UtilesMain;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
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
            guardarGuardias();
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
            guardarGuardias();
            System.out.println("Guardia comun agregado con exito");
        } catch (Exception e) {
            System.out.println("Error al agregar guardia armado" + e.getMessage());
        }
    }

    public void agregarGuardiaCapacitadoTaser() {

        try {
            CapacitadoTaser nuevo = UtilesMain.agregarCT();
            registroGuardias.agregar(nuevo.getDni(), nuevo);
            guardarGuardias();
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
            guardarPrisioneros();
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
    public String cargarNuevaCeldaComun() {

        int numeroDeCelda=UtilesMain.numCelda();
        int capacidad=UtilesMain.capacidad();

        for (int i = 0; i < listaCeldas.size(); i++) {
            if (listaCeldas.get(i).getNumeroDeCelda() == numeroDeCelda) {
                return "Esa celda ya existe y esta cargada al sistema";
            }
        }

        listaCeldas.add(new CeldaComun(numeroDeCelda, capacidad));
        guardarCeldas();
        return "Celda cargada correctamente";
    }

    public String cargarNuevaCeldaConfinamientoSolitario() {

        int numeroDeCelda=UtilesMain.numCelda();
        int capacidad=UtilesMain.capacidad();

        for (int i = 0; i < listaCeldas.size(); i++) {
            if (listaCeldas.get(i).getNumeroDeCelda() == numeroDeCelda) {
                return "Esa celda ya existe y esta cargada al sistema";
            }
        }

        listaCeldas.add(new ConfinamientoSolitario(numeroDeCelda, capacidad));
        guardarCeldas();
        return "Celda cargada correctamente";
    }

    public String mostrarCelda(){

        int numCelda=UtilesMain.numCelda();

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

    public String cambiarUltimaInspeccion(){

        int numCelda=UtilesMain.numCelda();

        for (int i = 0; i < listaCeldas.size(); i++) {
            if (listaCeldas.get(i).getNumeroDeCelda() == numCelda) {
                listaCeldas.get(i).setUltimaInspeccion(LocalDateTime.now());
                return "Se ha logrado asentar que hoy se inspecciono la celda";
            }
        }
        return "No se encuentra la celda ingresada";
    }

    public String asignarTV(){

        int numCelda=UtilesMain.numCelda();

        for (int i = 0; i < listaCeldas.size(); i++) {
            if (listaCeldas.get(i).getNumeroDeCelda() == numCelda && listaCeldas.get(i) instanceof CeldaComun){
                if(!((CeldaComun)listaCeldas.get(i)).getTv()){
                    ((CeldaComun)listaCeldas.get(i)).asignarTv();
                    return "Se ha asignado tv a esta celda correctamente";
                }else{
                    return "Esta celda ya tiene tv";
                }

            }
        }
        return "Celda no encontrada, o no es una celda comun, intentar denuevo";
    }

    public String extenderAislamiento(){

        int numCelda=UtilesMain.numCelda();
        int dias=UtilesMain.dias();

        for (int i = 0; i < listaCeldas.size(); i++) {
            if (listaCeldas.get(i).getNumeroDeCelda() == numCelda && listaCeldas.get(i) instanceof ConfinamientoSolitario) {
                ((ConfinamientoSolitario)listaCeldas.get(i)).extenderDiasDeAislamiento(dias);
                return "Se han extendido los dias exitosamente";
            }
        }

        return "No se encontro la celda o no es confinamiento solitario";
    }

    //JSON metodos
    //serializar
    public void guardarGuardias (){
        try{
            JSONArray arrGuardias = new JSONArray();
            for (Guardia g :registroGuardias.getLista().values()){
                arrGuardias.put(g.toJSON());
            }
            JSONUtiles.uploadJSON(arrGuardias, "guardias");
            System.out.println("Guardias guardados correctamente en JSON");
        } catch (Exception e){
            System.out.println("Error al guardar los guardias: " + e.getMessage());
        }
    }

    public void guardarPrisioneros (){
        try{
            JSONArray arrPrisioneros = new JSONArray();
            for (Prisionero p :registroPrisioneros.getLista().values()){
                arrPrisioneros.put(p.toJSON());
            }
            JSONUtiles.uploadJSON(arrPrisioneros, "prisioneros");
            System.out.println("Prisioneros guardados correctamente en JSON");
        } catch (Exception e){
            System.out.println("Error al guardar los prisioneros: " + e.getMessage());
        }
    }

    public void guardarCeldas (){
            try {
                JSONArray arrCeldas = new JSONArray();
                for (Celda c : listaCeldas) {
                    arrCeldas.put(c.toJSON());
                }

                JSONUtiles.uploadJSON(arrCeldas, "celdas");
                System.out.println("Celdas guardadas correctamente en JSON");
            } catch (Exception e) {
                System.out.println("Error al guardar las celdas: " + e.getMessage());
            }

    }

    public void guardarTodo (){
        guardarGuardias();
        guardarPrisioneros();
        guardarCeldas();
    }

    //deserializar
    public void cargarDatos (String archivo){
        String contenido = JSONUtiles.downloadJSON(archivo);
        if (contenido.isEmpty()){
            return;
        }

        JSONObject obj = new JSONObject(contenido);

        //Guardias
        JSONArray arrGuardias = obj.getJSONArray("guardias");
        for (int i = 0; i < arrGuardias.length(); i++){
            JSONObject Gobj = arrGuardias.getJSONObject(i);
            String tipo = Gobj.getString("tipo");

            Guardia g = null;

            switch (tipo){
                case "Comun":
                    g = Comun.fromJSON(Gobj);
                    break;
                case "Armado":
                    g= Armado.fromJSON(Gobj);
                    break;
                case "CapacitadoTaser":
                    g =  CapacitadoTaser.fromJSON(Gobj);
                    break;
                default:
                    System.out.println("Tipo de guardia desconocido: "+ tipo);
            }

            if(g != null){
                registroGuardias.agregar(g.getDni(), g);
            }
        }

        //Prisioneros
        JSONArray arrPrisioneros = obj.getJSONArray("prisioneros");
        for (int i = 0; i < arrPrisioneros.length(); i++){
            JSONObject Pobj = arrPrisioneros.getJSONObject(i);
            Prisionero p = Prisionero.fromJSON(Pobj);
            registroPrisioneros.agregar(p.getDni(), p);
        }

        //celdas
        JSONArray arrCeldas = obj.getJSONArray("celdas");
        for (int i = 0; i < arrCeldas.length(); i++) {
            JSONObject Cobj = arrCeldas.getJSONObject(i);
            String tipo = Cobj.getString("tipo");
            Celda c = null;

            switch (tipo) {
                case "CeldaComun":
                    c = CeldaComun.fromJSON(Cobj);
                    break;
                case "ConfinamientoSolitario": c =
                        ConfinamientoSolitario.fromJSON(Cobj);
                break;
                default: System.out.println("Tipo de celda desconocido: " + tipo);
            }

            if (c != null){
                listaCeldas.add(c);
            }
        }
    }

   //menus
    public void menuGuardia (){
    System.out.println("1. Agregar guardia comun");
    System.out.println("2. Agregar guardia armado");
    System.out.println("3. Agregar guardia capacitado en Taser");
    System.out.println("4. Mostrar todos los guardias");
    System.out.println("5. Mostrar guardias en servicio");
    System.out.println("6. Mostrar guardias fuera de servicio");
    System.out.println("7. Cambiar turno de un guardia");
    System.out.println("8.Asignar o cambiar arma a un guardia armado");
    System.out.println("9.Asignar gas pimienta a un guardia comun");
    System.out.println("10.Asignar/actualizar Taser a un guardia capacitado");
    System.out.println("Elige una opcion: ");
   }

    public void menuPrisioneros (){
        System.out.println("1. Agregar prisionero");
        System.out.println("2. Mostrar todos los prisioneros");
        System.out.println("3. Asignar celda a prisionero");
        System.out.println("4. Solicitar visita");
        System.out.println("5. Registrar visita");
        System.out.println("6. Mostrar visitas del prisionero");
        System.out.println("7. Ver tiempo restante de condena");
        System.out.println("Elige una opcion: ");
    }

    public  void menuCeldas (){
        System.out.println("1. Crear celda comun");
        System.out.println("2.Crear celda de confinamiento solitario");
        System.out.println("3.Mostrar todas las celdas");
        System.out.println("4.Asignar guardia a una celda");
        System.out.println("5.Asignar prisionero a una celda");
        System.out.println("6.Eliminar prisionero de una celda");
        System.out.println("7.Extender dias de aislamiento(confinamiento)");
        System.out.println("8.Terminar aislamiento(liberar celda solitaria)");
        System.out.println("9.Registrar inspeccion de celda");
        System.out.println("10.Mostrar celdas ocupadas");
        System.out.println("11.Mostrar celdas vacias");
        System.out.println("Elige una opcion: ");
    }

}
