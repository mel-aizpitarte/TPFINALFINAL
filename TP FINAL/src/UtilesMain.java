import Excepciones.PermisoDenegadoEx;
import Guards.*;

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

    public static Armado guardiaArm ()throws PermisoDenegadoEx {
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
        if(rango==Rango.OFICIAL){
            throw new PermisoDenegadoEx("Rango bajo");
        }
        System.out.println("Arma asignada (PISTOLA/RIFLE/ESCOPETA");
        Arma arma = Arma.valueOf(sc.nextLine().toUpperCase());

        return new Armado(nombre, apellido, dni, edad, fechaNacimiento, legajo, turno, numCeldas, enServicio, rango, arma);
    }

    public static CapacitadoTaser agregarCT () throws Exception {
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



}
