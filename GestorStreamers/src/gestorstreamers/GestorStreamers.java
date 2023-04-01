package gestorstreamers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Programa que gestiona una serie de streamers de unas plataformas en concreto
 *
 * @author gerard
 */
public class GestorStreamers {
//Variables globales
    public static Scanner sc = new Scanner(System.in);
    public static final int NUM_MAX_STREAMERS = 20;
    public static Streamer[] listaStreamers = new Streamer[NUM_MAX_STREAMERS];
    public static int numStreamers = 0;
    
    public static void main(String[] args) {
        //Variables
        boolean salir = false;

        do {
            menu();

            try {
                int opcion = Integer.parseInt(sc.nextLine());
                switch(opcion) {
                    case 1:
                        addStreamer();
                        break;
                    case 2:
                        modifyStreamer();
                        break;
                    case 3:
                        deleteStreamer();
                        break;
                    case 4:
                        mostrarInfoStreamer();
                        break;
                    case 5:
                        loadStreamers();
                        break;
                    case 6:
                        saveListStreamers();
                        break;
                    case 7:
                        buildHTML();
                        break;
                    case 8:
                        salir = true;
                        break;
                    default:
                        salir = true;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Error de valor introducido. Detalles: " + ex);
            }catch(Exception ex){
                System.out.println("Error general. Detalls: " + ex);
                
            }
        } while (!salir);

    }
    
    /*método para la lectura de teclado tipo String*/
    public static String lecturaDeString (String mensaje){
        System.out.println(mensaje);
        return sc.nextLine();
    }
    
    /*método para la lectura de teclado tipo Int*/
    public static int lecturaDeInteger (String mensaje){
        System.out.println(mensaje);
        return Integer.parseInt(sc.nextLine());
    }
    
    /*método para buscar streamers del array*/
    public static int buscarStreamer(String nickname){
        boolean encontrado = false;
        int contadorBucle = 0;
        while((!encontrado) && (contadorBucle < numStreamers)){
            
            if(nickname.equals(listaStreamers[contadorBucle].nickname)){
                encontrado = true;
            } else{
                contadorBucle++;
            }
        }
        if(!encontrado){
            return -1;
        } else{
            return contadorBucle;
        }
    }
    
    /*método para añadir un nuevo streamer al array*/
    public static void addStreamer() {
        if(numStreamers < NUM_MAX_STREAMERS){
            if (listaStreamers[numStreamers] == null) {
                boolean correcto = false;
                String nombre = lecturaDeString("Nickname del streamer?");
                String URL = lecturaDeString("Introduce la URL de su imagen");
                int horas = lecturaDeInteger("Cuantas horas emitidas tiene?");
                int seguidores = lecturaDeInteger("Cuantos seguidores tiene?");

                do {
                    String plataforma = lecturaDeString("En que plataforma emite? (YouTube, Twitch o default)");
                    if ((plataforma.equalsIgnoreCase("youtube")) || (plataforma.equalsIgnoreCase("twitch")) || (plataforma.equalsIgnoreCase("default"))) {
                        listaStreamers[numStreamers] = new Streamer(nombre, URL, horas, seguidores, plataforma);
                        correcto = true;
                        numStreamers++;
                    }
                } while (!correcto);
            }
        } else{
            System.out.println("La lista de streamers ya esta completa");
        }
    }
    
    //método para mostrar el menu del gestor
    public static void menu(){
        System.out.println("---GESTOR DE STREAMERS---");
        System.out.println("1) Nuevo streamer");
        System.out.println("2) Modificar streamer");
        System.out.println("3) Eliminar streamer");
        System.out.println("4) Ver informacion del streamer");
        System.out.println("5) Cargar lista de streamers de un fichero");
        System.out.println("6) Guardar lista de streamers en fichero .txt");
        System.out.println("7) Crear informe HTML de streamers");
        System.out.println("8) Salir");
    }
    
    /*método para modificar un streamer mediante su nickname*/
    public static void modifyStreamer() {
        String introNickname = lecturaDeString("Introduce el nickname del streamer que quieres modificar:");
        int posStreamer = buscarStreamer(introNickname);
        if (posStreamer != -1) {
            boolean correcto = false;
            String nombre = lecturaDeString("Nickname del streamer?");
            String URL = lecturaDeString("Introduce la URL de su imagen");
            int horas = lecturaDeInteger("Cuantas horas emitidas tiene?");
            int seguidores = lecturaDeInteger("Cuantos seguidores tiene?");
            do {
                String plataforma = lecturaDeString("En que plataforma emite (YouTube, Twitch o default)");
                if ((plataforma.equalsIgnoreCase("youtube")) || (plataforma.equalsIgnoreCase("twitch")) || (plataforma.equalsIgnoreCase("default"))) {
                    listaStreamers[posStreamer] = new Streamer(nombre, URL, horas, seguidores, plataforma);
                    correcto = true;
                }
            } while (!correcto);
        } else{
            System.out.println("Este nickname no esta registrado");
        }
    }
    
    /*método para eliminar un streamer mediante su nickname*/
    public static void deleteStreamer(){
        String introNickname = lecturaDeString("Introduce el nickname del streamer que quieres modificar:");
        int posStreamer = buscarStreamer(introNickname);
        if (posStreamer != -1) {
            //bucle para mover elementos del array, una posicion atrás, así eliminamos el Streamer
            for(int i = posStreamer; i < numStreamers; i++){
                if(i+1 != numStreamers){
                    listaStreamers[i] = listaStreamers[i+1];
                } else{//si la posición es la última del array, se eliminará a mano igualandolo a null
                    listaStreamers[i] = null;
                }
            }
            numStreamers--;
        } else{
            System.out.println("Este nickname no esta registrado");
        }
    }
    
    /*método para consultar la información del streamer*/
    public static void mostrarInfoStreamer(){
        String introNickname = lecturaDeString("Introduce el nickname del streamer que quieres modificar:");
        int posStreamer = buscarStreamer(introNickname);
        if (posStreamer != -1) {
            System.out.println(listaStreamers[posStreamer].printInfoStreamer());
        } else{
            System.out.println("Este nickname no esta registrado");
        }
    }
    
    /*método para guardar en un archivo .txt todos los streamers*/
    public static void saveListStreamers() throws FileNotFoundException{
        String path = lecturaDeString("Introduce la ruta del archivo");
        OperacionArchivos.guardarTXT(path, listaStreamers, numStreamers);
    }
    
    /*método para crear un informe HTML con todos los streamers*/
    public static void buildHTML() throws FileNotFoundException{
        String path = lecturaDeString("Introduce la ruta del archivo");
        OperacionArchivos.generarHTML(path, listaStreamers, numStreamers);
    }
    
    /*método para cargar una lista de streamers*/
    public static void loadStreamers() throws FileNotFoundException{
        String path = lecturaDeString("Introduce la ruta del archivo");
        String eleccion = lecturaDeString("Esta seguro que quiere sobrescribir la lista actual [S/N]");
        if(eleccion.equalsIgnoreCase("S")) {
            listaStreamers = OperacionArchivos.cargarListaStreamers(path);
            numStreamers = 0;
            for(int i = 0; i < NUM_MAX_STREAMERS; i++){//bucle para reiniciar la cantidad de streamers en la lista
                if(listaStreamers[i] != null) numStreamers++;
            }
            for(int i = 0; i < numStreamers; i++){
                System.out.println(listaStreamers[i].printInfoStreamer());
            }
        }else System.out.println("Opcion cancelada");
    }
}