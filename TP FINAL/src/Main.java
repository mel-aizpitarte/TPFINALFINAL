import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

       Scanner sc = new Scanner(System.in);

       Sistema sistema = new Sistema();

       int opcion;

       do{
           System.out.println("============================================");
           System.out.println("       SISTEMA DE CONTROL DE PRISIÓN        ");
           System.out.println("============================================");

           System.out.println("-----------------Guardias-------------------");
           System.out.println("1. Agregar guardia comun");
           System.out.println("2. Agregar guardia armado");
           System.out.println("3. Agregar guardia capacitado en Taser");
           System.out.println("4. Mostrar todos los guardias");
           System.out.println("5. Dar descanso a un guardia");
           System.out.println("6. Mostrar guardias en servicio");
           System.out.println("7. Mostrar guardias fuera de servicio");
           System.out.println("8. Cambiar turno de un guardia");
           System.out.println("9.Asignar o cambiar arma a un guardia armado");
           System.out.println("10.Asignar gas pimienta a un guardia comun");
           System.out.println("11.Asignar/actualizar Taser a un guardia capacitado");

           System.out.println("-----------------Prisioneros-------------------");

           System.out.println("-----------------Celdas-------------------");
           System.out.println("13. Crear celda comun");
           System.out.println("14.Crear celda de confinamiento solitario");
           System.out.println("15.Mostrar todas las celdas");
           System.out.println("16.Asignar guardia a una celda");
           System.out.println("17.Asignar prisionero a una celda");
           System.out.println("18.Eliminar prisionero de una celda");
           System.out.println("19.Extender dias de aislamiento(confinamiento)");
           System.out.println("20.Terminar aislamiento(liberar celda solitaria)");
           System.out.println("21.Registrar inspeccion de celda");
           System.out.println("22.Mostrar celdas ocupadas");
           System.out.println("23.Mostrar celdas vacias");


           System.out.println("-----------------Archivos-------------------");
           System.out.println("Cargar datos en JSON");
           System.out.println("Guardar datos en JSON");
           System.out.println("0. Salir");
           System.out.println("Seleccione una opcion: ");

           opcion = sc.nextInt();
           sc.nextLine();

           switch (opcion){
               case 1:
                   sistema.agregarGuardiaComun();
                   break;
               case 2:
                   sistema.agregarGuardiaArmado();
                   break;
               case 3:
                   sistema.agregarGuardiaCapacitadoTaser();
                   break;
               case 4:
                   System.out.println("\n-----LISTA GUARDIAS-----");
                   sistema.mostrarGuardias();
                   break;
               case 5:
                   sistema.darDescansoGuardia();
                   break;
               case 6:
                   System.out.println("\n------GUARDIAS EN SERVICIO------");
                   sistema.mostrarGuardiasEnServicio();
                   break;
               case 7:
                   System.out.println("\n-----GUARDIAS FUERA DE SERVICIO-----");
                   sistema.mostrarGuardiasFueraDeServicio();
                   break;
               case 8:
                   sistema.cambiarTurnoGuardia();
                   break;
               case 9:
                   sistema.asignarOcambiarArma();
                   break;
               case 10:
                   sistema.asignarGasPimienta();
                   break;
               case 11:
                   sistema.asignarOactualizarTaser();
                   break;

               case 0:
                   System.out.println("Saliendo del sistema....");
                   break;
               default:
                   System.out.println("Opcion no valida. Intente nuevamente");
                   break;
           }

           System.out.println("\nPresione ENTER para continuar");
           sc.nextLine();

       }while(opcion != 0);


    }
}