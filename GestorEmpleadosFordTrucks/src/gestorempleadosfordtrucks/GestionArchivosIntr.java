package gestorempleadosfordtrucks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase para gestionar el archivo introducido y mostrarlo en formato html
 * @author gerard
 */
public class GestionArchivosIntr {
    /*Variables globales*/
    public static int NUM_EMPLEADOS_PRED = 50;
    public static int contador = 0;

    /*función para introducir en array empleados los objetos tipo empleado que se encuentren en el archivo*/
    public static EmpleadosFT[] cargarEmpleados(String path) throws FileNotFoundException {
        EmpleadosFT[] empleados = new EmpleadosFT[NUM_EMPLEADOS_PRED];
        File archivo = new File(path);
        Scanner sc = new Scanner(archivo);
        contador = 0;
        while (sc.hasNext()) {
            String linea = sc.nextLine();
            String[] valoresEmpleados = linea.split(";");
            EmpleadosFT empleado = new EmpleadosFT(valoresEmpleados[0], valoresEmpleados[1], valoresEmpleados[2],
                    valoresEmpleados[3], valoresEmpleados[4]);
            empleados[contador] = empleado;
            contador++;
        }
        return empleados;
    }

    /*función para crear el archivo HTML*/
    public static void generarHTML(String path, EmpleadosFT empleados) throws FileNotFoundException {
        File archivo = new File(path);
        PrintStream escribe = new PrintStream(archivo);
        escribe.println("<html>");
        escribe.println(empleados.stringHTML());
        escribe.println("</html>");
        escribe.close();
    }
}
