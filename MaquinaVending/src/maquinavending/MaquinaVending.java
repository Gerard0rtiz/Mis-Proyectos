package maquinavending;

import java.util.Scanner;
import java.text.DecimalFormat;

/**
 * Programa de gesti�n b�sica de una m�quina expenedora de comida con men� de
 * usuario y men� t�cnico cada men� con diferentes funciones
 *
 * @author gerard
 */
public class MaquinaVending {

    private static final DecimalFormat df = new DecimalFormat("0.00");
    /**Creamos array como variable global para mostrar y crear nuevos productos mediante los men�s*/
    public static Producto[] misproductos = new Producto[9];
    /**Creamos array como variable global para las monedas*/
    public static Monedas[] monedasAceptadas = new Monedas[6];
    /**contador de ventas realizadas */
    public static int contadorVentas = 0;
    /**contador de dinero generado*/
    public static double contadorDinero = 0;
    /**Inicializaci�n del Scanner de manera global para hacer uso en m�todos*/
    public static Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String usuario;
        //Variables
        boolean salida = false;
        ProductosPre();
        /**Pregunta si es cliente o t�cnico*/
        do {
            /** Si el usuario selecciona ser cliente, mostraremos el men� de usuario, si elije ser t�cnico acceder� al 
            *   men� t�cnico si introduce bien la contrase�a
            */
            System.out.println("Men� principal: '(C) cliente'  '(T) t�cnico'  '(S) salir'");
            usuario = sc.nextLine(); //selecci�n de tipo de usuario
            if (usuario.toUpperCase().equals("C")) { //entrar al men� cliente

                System.out.println("Mostrando men� de usuario...");
                /** Selecci�n del producto con do{}while para permitir al usuario seleccionar un producto, en caso
                 *  de que no haya ninguno, se le volver� a preguntar
                 */
                int selec;
                do {
                    titleClient();
                    showProducts();
                    selec = readPos();
                    boolean finIntrodMonedas = false;
                    double saldo = 0;
                    double dineroIntroducido;
                    double cambioMonedas;
                    if (selec != 0) {
                        if ((misproductos[selec - 1] == null) || (misproductos[selec - 1].getCantidad() == 0)) { /**comprobaci�n de producto seleccionado*/
                            System.out.println("No hay Stock o ning�n producto");
                        } else {
                            while (!finIntrodMonedas) { /**introducci�n de dinero para pagar el producto seleccionado*/
                                System.out.println("A pagar: " + df.format(misproductos[selec - 1].getPrecio() - saldo) + " euros"
                                        + " Ingresa el dinero " + "(saldo " + df.format(saldo) + ")" + " (0 para cancelar):");
                                dineroIntroducido = sc.nextDouble();
                                sc.nextLine(); //buffer
                                if (dineroIntroducido == 0) { /**cancelaci�n de la compra*/
                                    finIntrodMonedas = true;
                                } else if (dineroIntroducido != 0) {
                                    if (exactMoney(dineroIntroducido)) { /**comprobaci�n de la moneda introducida*/
                                        saldo += dineroIntroducido;
                                        if (saldo >= misproductos[selec - 1].getPrecio()) { /**mostrar mensaje dependiendo de si
                                                                                                        *se debe dar cambio o no*/
                                            finIntrodMonedas = true;
                                            if (saldo > misproductos[selec - 1].getPrecio()) {/**devoluci�n del dinero*/
                                                cambioMonedas = saldo - misproductos[selec - 1].getPrecio();
                                                System.out.println("Se le devolver�: " + df.format(cambioMonedas));
                                                int i = 0;
                                                inicializarMonedasAceptadas();/**inicializar array para comprobaci�n de monedas que se introducen*/
                                                while (i < monedasAceptadas.length) { /**calcular el las monedas del cambio*/
                                                    if (Math.round(cambioMonedas * 100.0) / 100.0 >= monedasAceptadas[i].value) {
                                                        cambioMonedas -= monedasAceptadas[i].value;
                                                        monedasAceptadas[i].cant++;
                                                    } else {
                                                        i++;
                                                    }
                                                }
                                                for (int j = 0; j < monedasAceptadas.length; j++) { /**mostrar tipo de moneda y cantidad a entregar*/
                                                    System.out.println(monedasAceptadas[j].value + " euros " + "(" + monedasAceptadas[j].cant + ")");
                                                }
                                            } else {
                                                System.out.println("Saldo exacto, muchas gracias");
                                            }
                                        }
                                    } else {
                                        System.out.println("Moneda no permitida");
                                    }
                                }
                            }
                            int cantidad = misproductos[selec - 1].getCantidad();
                            misproductos[selec - 1].setCantidad(cantidad - 1);/**resto de la cantidad del producto comprado*/
                            contadorVentas++;/**aumento de ventas realizadas*/
                            contadorDinero += misproductos[selec - 1].getPrecio();/**aumento de dinero generado*/
                        }
                    }

                } while (selec != 0);
            } else if (usuario.toUpperCase().equals("T")) { /**entrar al men� t�cnico*/

                boolean checkPasswordTec = false;
                int intentos = 3;
                do {
                    System.out.println("Introduzca la contrase�a num�rica para entrar al men� t�cnico (tiene " + intentos + " intentos)");
                    int contr = sc.nextInt();
                    sc.nextLine(); //buffer
                    if (contr == 223355) {
                        System.out.println("Contrase�a correcta");
                        checkPasswordTec = true;
                    } else {
                        intentos--;
                        System.out.println("Contrase�a incorrecta, vuelva a intentarlo");
                        if (intentos < 1) {
                            System.out.println("No tiene m�s intentos...");
                        }
                    }
                } while ((!checkPasswordTec) && (intentos > 0));
                if (intentos != 0) {
                    boolean salir_tecnico = false;
                    do {
                        titleTec();
                        int opc = menuTec(); /**mostrar menu tecnico y leer opcion*/
                        sc.nextLine(); //buffer
                        switch (opc) {
                            case 1:
                                NewProduct(); /**a�adir producto*/
                                break;
                            case 2:
                                AdditionalStock(); /**a�adir stock*/
                                break;
                            case 3:
                                deleteProd(); /**borrar producto sin stock*/
                                break;
                            case 4:
                                showProducts(); /**mostrar productos*/
                                DineroYVentas(); /**mostrar dinero y ventas generadas*/
                                break;
                            case 5:
                                salir_tecnico = true; /**ir al men� principal*/
                                break;
                            default:
                                break;
                        }
                    } while (!salir_tecnico);
                }
            } else if (usuario.toUpperCase().equals("S")) { /**salir del programa*/
                System.out.println("Fin del programa");
                salida = true;
            }
        } while (!salida);

    }

//m�todos
/**m�todo para generar distintos productos*/
    public static void ProductosPre() {
        misproductos[0] = new Producto("Kinder", 1.10, 3);
        misproductos[2] = new Producto("KitKat", 0.80, 0);
        misproductos[8] = new Producto("Huesitos", 1.00, 5);
    }

    /**m�todo para a�adir nuevo producto a la m�quina a partir del menu tecnico*/
    public static void NewProduct() {
        System.out.println("Nuevo producto");
        System.out.println("Nombre:");
        String nombre = sc.nextLine();
        System.out.println("Precio:");
        double precio = sc.nextDouble();
        System.out.println("Stock inicial:");
        int stockIni = sc.nextInt();
        sc.nextLine(); //buffer
        int posi = readPos();
        if (posi != 0) {
            if (misproductos[posi - 1] == null) {
                misproductos[posi - 1] = new Producto(nombre, precio, stockIni);
            } else {
                System.out.println("En esta posicion ya existe un producto");
            }
        } else {
            System.out.println("Proceso cancelado");
        }
    }

    /**m�todo para a�adir stock a un producto ya existente en la m�quina*/
    public static void AdditionalStock() {
        int posic = readPos();
        if (posic != 0) {
            System.out.println("�Cu�nto stock quiere a�adir?");
            int addStock = sc.nextInt();
            sc.nextLine(); //buffer
            if (misproductos[posic - 1] == null) {
                System.out.println("No hay ning�n producto");
            } else {
                misproductos[posic - 1].AddStock(addStock);
                System.out.println("El nuevo stock es: " + misproductos[posic - 1].getCantidad());
            }
        }
    }

    /**m�todo para mostrar los productos*/
    public static void showProducts() {
        /**mostrar "null" del array como "Vac�o"*/
        for (int i = 0; i < misproductos.length; i++) {
            if (misproductos[i] == null) {
                System.out.println((i + 1) + "]" + "Vac�o");
            } else {
                System.out.println((i + 1) + "]" + misproductos[i].getNombre() + " : " + df.format(misproductos[i].getPrecio()) + " euros" + " : " + misproductos[i].getCantidad() + "un. ");
            }
        }
    }

    /**m�todo para eliminar el producto*/
    public static void deleteProd() {
        int position = readPos();
        if (misproductos[position - 1] != null) {
            if (misproductos[position - 1].getCantidad() == 0) {
                if (position != 0) {
                    System.out.println("�Est� seguro que quiere eliminar �ste producto? [S]");
                    String answer = sc.nextLine();
                    if (answer.toUpperCase().equals("S")) {
                        misproductos[position - 1] = null;
                        System.out.println("Producto eliminado con �xito");
                    } else {
                        System.out.println("Opci�n cancelada");
                    }
                }
            } else {
                System.out.println("No se puede eliminar este producto porque a�n hay Stock disponible");
            }
        } else {
            System.out.println("No hay ning�n producto");
        }
    }

    /**m�todo para mostrar el t�tulo de la m�quina de cliente*/
    public static void titleClient() {
        System.out.println("----M�quina expendedora----");
        System.out.println(" ---------CLIENTE---------");
    }

    /**m�todo para mostrar el t�tulo de la m�quina de t�cnico*/
    public static void titleTec() {
        System.out.println("----M�quina expendedora----");
        System.out.println(" ---------T�CNICO---------");
    }

    /**m�todo para mostrar el men� t�cnico*/
    public static int menuTec() {
        System.out.println("1] Nuevo producto");
        System.out.println("2] A�adir stock");
        System.out.println("3] Eliminar producto");
        System.out.println("4] Informaci�n de la m�quina");
        System.out.println("5] Salir a men� principal");
        int opt = sc.nextInt();
        return opt;
    }

    /**m�todo para leer entradas del usuario*/
    public static int readPos() {
        System.out.println("Introducir la posici�n (0 para cancelar):");
        int posit = sc.nextInt();
        sc.nextLine(); //buffer
        return posit;
    }

    /**m�todo para limitar las monedas*/
    public static boolean exactMoney(double dineroIntroducido) {
        if ((dineroIntroducido == 2.0) || (dineroIntroducido == 1.0) || (dineroIntroducido == 0.5)
                || (dineroIntroducido == 0.2) || (dineroIntroducido == 0.1) || (dineroIntroducido == 0.05)) {
            return true;
        } else {
            return false;
        }
    }

    /**m�todo para inicializar array de monedas para gestionar el cambio*/
    public static void inicializarMonedasAceptadas() {
        monedasAceptadas[0] = new Monedas(2.0, 0);
        monedasAceptadas[1] = new Monedas(1.0, 0);
        monedasAceptadas[2] = new Monedas(0.50, 0);
        monedasAceptadas[3] = new Monedas(0.20, 0);
        monedasAceptadas[4] = new Monedas(0.10, 0);
        monedasAceptadas[5] = new Monedas(0.05, 0);
    }

    /**m�todo para mostrar el dinero y las ventas generadas*/
    public static void DineroYVentas() {
        System.out.println("Dinero generado: " + contadorDinero);
        System.out.println("Ventas realizadas: " + contadorVentas);
    }
}