package Utiles;

import Guards.*;
import Prisoners.CrimenCometido;
import Prisoners.Prisionero;
import Prisoners.Seguridad;

import java.time.LocalDate;
import java.util.Scanner;

public class UtilesMain {

    //Clase que usamos para metodos donde nos comunicamos con el usuario
    public static Comun agregarGComun () throws Exception{
            Scanner sc = new Scanner(System.in);

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
            System.out.println("¿Tiene gas pimienta? (true/false): ");
            boolean gasPimienta = sc.nextBoolean();
            sc.nextLine();

            return new Comun(nombre, apellido, dni, edad, fechaNacimiento,legajo ,turno, numCeldas, enServicio, rango, gasPimienta);

    }

    public static Armado agregarGuardiaArm (){
        Scanner sc = new Scanner(System.in);

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

        return new Armado(nombre, apellido, dni, edad, fechaNacimiento, legajo, turno, numCeldas, enServicio, rango, arma);
    }

    public static CapacitadoTaser agregarCT ()throws Exception {
        Scanner sc = new Scanner(System.in);

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

            return new CapacitadoTaser(nombre, apellido, dni, edad, fechaNacimiento, legajo, turno, numCeldas, enServicio, rango, LocalDate.now(), taser);
    }

    public static String pedirDni (){
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese el DNI: ");
        String dni = sc.nextLine();
        sc.nextLine();

        return dni;
    }

    public static Prisionero prisionero (){
        Scanner sc = new Scanner(System.in);

        System.out.println("Nombre:");
        String nombre = sc.nextLine();

        System.out.println("Apellido:");
        String apellido = sc.nextLine();

        System.out.println("DNI:");
        String dni = sc.nextLine();

        System.out.println("Edad:");
        int edad = sc.nextInt();
        sc.nextLine();

        System.out.println("Fecha de nacimiento (AAAA-MM-DD):");
        LocalDate nacimiento = LocalDate.parse(sc.nextLine());

        System.out.println("Crimen cometido (ROBO/HOMICIDIO/NARCOTRAFICO/FRAUDE/SECUESTRO/VIOLENCIA_DOMESTICA/CORRUPCION/AGRESION/TENTATIVA_DE_HOMICIDIO OTRO): ");
        CrimenCometido crimen = CrimenCometido.valueOf(sc.nextLine().toUpperCase());

        System.out.println("Nivel de seguridad (BAJA/MEDIA/ALTA): ");
        Seguridad seguridad = Seguridad.valueOf(sc.nextLine().toUpperCase());

        System.out.println("Condena en meses:");
        int condena = sc.nextInt();
        sc.nextLine();

      return new Prisionero(nombre, apellido, dni, edad, nacimiento, LocalDate.now(), null, crimen, seguridad, condena);
    }

    public static int numCelda (){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el numero de celda a asignar: ");
        int numCeldas = sc.nextInt();
        return numCeldas;
    }

    public static int numMostrar(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el numero de celda a mostrar: ");
        int numCeldas = sc.nextInt();
        return numCeldas;
    }

    public static int capacidad(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la capacidad de la celda: ");
        int capacidad = sc.nextInt();
        return capacidad;
    }

    public static int dias(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la cantidad de dias: ");
        int dias = sc.nextInt();
        return dias;
    }

}
