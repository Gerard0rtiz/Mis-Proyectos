package gestorstreamers;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Clase para realizar la carga, el guardado y para la generación de HTML de streamers
 * @author gerard
 */
public class OperacionArchivos {
    public static final int NUM_MAX_STREAMERS = 20;
    public static void guardarTXT(String path, Streamer[] streamers, int numStreamers) throws FileNotFoundException{
        File archivo = new File(path);
        PrintStream escribe = new PrintStream(archivo);
        for(int i = 0; i < numStreamers; i++){
            escribe.println(streamers[i].printInfoStreamer());
        }
        escribe.close();
    }
    
    public static void generarHTML(String path, Streamer[] streamers, int numStreamers) throws FileNotFoundException{
        File archivo = new File(path);
        PrintStream escribe = new PrintStream(archivo);
        escribe.println("<html>");
        for(int i = 0; i < numStreamers; i++){
            escribe.println(streamers[i].toStringHTML());
        }
        escribe.println("</html>");
        escribe.close();
    }
    
    /*método para cargar lista de streamers*/
    public static Streamer[] cargarListaStreamers (String path) throws FileNotFoundException{
        Streamer[] listaStreamers = new Streamer[NUM_MAX_STREAMERS];
        File archivo = new File(path);
        Scanner sc = new Scanner(archivo);
        int contador = 0;
        while(sc.hasNext()){
            String linea = sc.nextLine();
            String[] valoresStreamer = linea.split(";");
            Streamer streamer = new Streamer(valoresStreamer[0], valoresStreamer[1], Integer.parseInt(valoresStreamer[2]), 
                                            Integer.parseInt(valoresStreamer[3]), valoresStreamer[4]);
            listaStreamers[contador]  = streamer;
            contador++;
        }
        return listaStreamers;
    }
    
}
