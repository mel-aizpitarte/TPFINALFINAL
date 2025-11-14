import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Sistema sistema = new Sistema();

       try{
           sistema.cargarDatos("archivos.json");
       } catch (Exception e) {
           System.out.println("Error" + e.getMessage());
       }


       int opcion;

       do{
           System.out.println("============================================");
           System.out.println("       SISTEMA DE CONTROL DE PRISIÓN        ");
           System.out.println("============================================");

           System.out.println("1. Configuracion de Guardias");
           System.out.println("2. Configuracion de Prisioneros");
           System.out.println("3.Configuracion de Celdas");
           System.out.println("0. Salir");
           System.out.println("Seleccione una opcion: ");

           opcion = sc.nextInt();
           sc.nextLine();

           switch (opcion){
               case 1:
                   System.out.println("----------Configuracion de Guardias----------");
                   sistema.menuGuardia();
                   int opcion1 = sc.nextInt();
                   sc.nextLine();

                   switch (opcion1){
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
                           System.out.println("----------- Lista de Guardias -----------");
                           sistema.mostrarGuardias();
                           break;
                       case 5:
                           sistema.mostrarGuardiasEnServicio();
                           break;
                       case 6:
                           sistema.mostrarGuardiasFueraDeServicio();
                           break;
                       case 7:
                           sistema.cambiarTurnoGuardia();
                           break;
                       case 8:
                           sistema.asignarOcambiarArma();
                           break;
                       case 9:
                           sistema.asignarGasPimienta();
                           break;
                       case 10:
                           sistema.asignarOactualizarTaser();
                           break;
                   }
                   break;
               case 2:
                   System.out.println("----------Configuracion de Prisioneros----------");
                   sistema.menuPrisioneros();

                   int opcion2 = sc.nextInt();
                   sc.nextLine();

                   switch (opcion2){
                       case 1:
                           sistema.agregarPrisionero();
                           break;
                       case 2:
                           sistema.mostrarPrisioneros();
                           break;
                       case 3:
                           sistema.asignarCeldaAPris(); //FALTA TERMINARLO
                           break;
                       case 4:
                           sistema.solicitarVis();
                           break;
                       case 5:
                           sistema.registrarVis();
                           break;
                       case 6:
                           sistema.mostrarVisitasPrisionero();
                           break;
                       case 7:
                           sistema.mostrarTiempoRestante();
                           break;
                   }
                   break;
               case 3:
                   System.out.println("----------Configuracion de Celdas----------");
                   sistema.menuCeldas();

                   int opcion3 = sc.nextInt();
                   sc.nextLine();

                   switch (opcion3){
                       case 1:
                           break;
                   }
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

       try{
           sistema.guardarTodo();
       }catch (Exception e){
           System.out.println("Error "+ e.getMessage());
       }

    }


}