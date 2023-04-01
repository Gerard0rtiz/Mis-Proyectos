package gestorempleadosfordtrucks;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Gestor de empleados de la empresa Ford Trucks para crear una pestaña HTML por
 * cada empleado que se encuentre en el archivo .csv que se cargue en el
 * programa
 *
 * @author gerard
 */
public class GestorEmpleadosFordTrucks {

    /*Variables globales*/
    public static int NUM_EMPLEADOS_PRED = 50;
    public static EmpleadosFT[] empleados = new EmpleadosFT[NUM_EMPLEADOS_PRED];
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean salir = false;
        do {
            menu();
            try {
                int opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1:
                        loadAndGenerateHTML();
                        break;
                    case 2:
                        salir = true;
                        break;
                    default:
                        salir = true;
                        break;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Error en el valor introducido, detalles: " + ex);
            } catch (Exception ex) {
                System.out.println("Error general, detalles: " + ex);
            }
        } while (!salir);
    }

    /*función para introducir valores tipo String*/
    public static String lecturaDeString(String mensaje) {
        System.out.println(mensaje);
        return sc.nextLine();
    }

    /*función para mostrar el menu*/
    public static void menu() {
        System.out.println("1) Cargar archivo .csv y generar el HTML de cada elemento");
        System.out.println("2) Salir del programa");
    }

    /*función para requerir la ruta del archivo y generar los distintos HTMLs*/
    public static void loadAndGenerateHTML() throws FileNotFoundException {
        String path = lecturaDeString("Introduce la ruta del archivo .csv que quieres cargar y generar su HTML");
        empleados = GestionArchivosIntr.cargarEmpleados(path);
        String pathHtml = "";
        for (int i = 0; i < NUM_EMPLEADOS_PRED; i++) {
            if (empleados[i] != null) {
                pathHtml = "C:\\Users\\gerard\\Downloads\\testHTML\\" + empleados[i].nombre + ".html";
                buildHTML(pathHtml, empleados[i]);
            }
        }
        System.out.println("Archivos .html generados correctamente");
    }

    /*función para generar el archivo HTML del array*/
    public static void buildHTML(String path, EmpleadosFT empleado) throws FileNotFoundException {
        GestionArchivosIntr.generarHTML(path, empleado);
    }
}
