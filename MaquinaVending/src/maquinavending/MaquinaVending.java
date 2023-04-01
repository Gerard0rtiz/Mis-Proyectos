package maquinavending;

import java.util.Scanner;
import java.text.DecimalFormat;

/**
 * Programa de gestión básica de una máquina expenedora de comida con menú de
 * usuario y menú técnico cada menú con diferentes funciones
 *
 * @author gerard
 */
public class MaquinaVending {

    private static final DecimalFormat df = new DecimalFormat("0.00");
    /**Creamos array como variable global para mostrar y crear nuevos productos mediante los menús*/
    public static Producto[] misproductos = new Producto[9];
    /**Creamos array como variable global para las monedas*/
    public static Monedas[] monedasAceptadas = new Monedas[6];
    /**contador de ventas realizadas */
    public static int contadorVentas = 0;
    /**contador de dinero generado*/
    public static double contadorDinero = 0;
    /**Inicialización del Scanner de manera global para hacer uso en métodos*/
    public static Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String usuario;
        //Variables
        boolean salida = false;
        ProductosPre();
        /**Pregunta si es cliente o técnico*/
        do {
            /** Si el usuario selecciona ser cliente, mostraremos el menú de usuario, si elije ser técnico accederá al 
            *   menú técnico si introduce bien la contraseña
            */
            System.out.println("Menú principal: '(C) cliente'  '(T) técnico'  '(S) salir'");
            usuario = sc.nextLine(); //selección de tipo de usuario
            if (usuario.toUpperCase().equals("C")) { //entrar al menú cliente

                System.out.println("Mostrando menú de usuario...");
                /** Selección del producto con do{}while para permitir al usuario seleccionar un producto, en caso
                 *  de que no haya ninguno, se le volverá a preguntar
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
                        if ((misproductos[selec - 1] == null) || (misproductos[selec - 1].getCantidad() == 0)) { /**comprobación de producto seleccionado*/
                            System.out.println("No hay Stock o ningún producto");
                        } else {
                            while (!finIntrodMonedas) { /**introducción de dinero para pagar el producto seleccionado*/
                                System.out.println("A pagar: " + df.format(misproductos[selec - 1].getPrecio() - saldo) + " euros"
                                        + " Ingresa el dinero " + "(saldo " + df.format(saldo) + ")" + " (0 para cancelar):");
                                dineroIntroducido = sc.nextDouble();
                                sc.nextLine(); //buffer
                                if (dineroIntroducido == 0) { /**cancelación de la compra*/
                                    finIntrodMonedas = true;
                                } else if (dineroIntroducido != 0) {
                                    if (exactMoney(dineroIntroducido)) { /**comprobación de la moneda introducida*/
                                        saldo += dineroIntroducido;
                                        if (saldo >= misproductos[selec - 1].getPrecio()) { /**mostrar mensaje dependiendo de si
                                                                                                        *se debe dar cambio o no*/
                                            finIntrodMonedas = true;
                                            if (saldo > misproductos[selec - 1].getPrecio()) {/**devolución del dinero*/
                                                cambioMonedas = saldo - misproductos[selec - 1].getPrecio();
                                                System.out.println("Se le devolverá: " + df.format(cambioMonedas));
                                                int i = 0;
                                                inicializarMonedasAceptadas();/**inicializar array para comprobación de monedas que se introducen*/
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
            } else if (usuario.toUpperCase().equals("T")) { /**entrar al menú técnico*/

                boolean checkPasswordTec = false;
                int intentos = 3;
                do {
                    System.out.println("Introduzca la contraseña numérica para entrar al menú técnico (tiene " + intentos + " intentos)");
                    int contr = sc.nextInt();
                    sc.nextLine(); //buffer
                    if (contr == 223355) {
                        System.out.println("Contraseña correcta");
                        checkPasswordTec = true;
                    } else {
                        intentos--;
                        System.out.println("Contraseña incorrecta, vuelva a intentarlo");
                        if (intentos < 1) {
                            System.out.println("No tiene más intentos...");
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
                                NewProduct(); /**añadir producto*/
                                break;
                            case 2:
                                AdditionalStock(); /**añadir stock*/
                                break;
                            case 3:
                                deleteProd(); /**borrar producto sin stock*/
                                break;
                            case 4:
                                showProducts(); /**mostrar productos*/
                                DineroYVentas(); /**mostrar dinero y ventas generadas*/
                                break;
                            case 5:
                                salir_tecnico = true; /**ir al menú principal*/
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

//métodos
/**método para generar distintos productos*/
    public static void ProductosPre() {
        misproductos[0] = new Producto("Kinder", 1.10, 3);
        misproductos[2] = new Producto("KitKat", 0.80, 0);
        misproductos[8] = new Producto("Huesitos", 1.00, 5);
    }

    /**método para añadir nuevo producto a la máquina a partir del menu tecnico*/
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

    /**método para añadir stock a un producto ya existente en la máquina*/
    public static void AdditionalStock() {
        int posic = readPos();
        if (posic != 0) {
            System.out.println("¿Cuánto stock quiere añadir?");
            int addStock = sc.nextInt();
            sc.nextLine(); //buffer
            if (misproductos[posic - 1] == null) {
                System.out.println("No hay ningún producto");
            } else {
                misproductos[posic - 1].AddStock(addStock);
                System.out.println("El nuevo stock es: " + misproductos[posic - 1].getCantidad());
            }
        }
    }

    /**método para mostrar los productos*/
    public static void showProducts() {
        /**mostrar "null" del array como "Vacío"*/
        for (int i = 0; i < misproductos.length; i++) {
            if (misproductos[i] == null) {
                System.out.println((i + 1) + "]" + "Vacío");
            } else {
                System.out.println((i + 1) + "]" + misproductos[i].getNombre() + " : " + df.format(misproductos[i].getPrecio()) + " euros" + " : " + misproductos[i].getCantidad() + "un. ");
            }
        }
    }

    /**método para eliminar el producto*/
    public static void deleteProd() {
        int position = readPos();
        if (misproductos[position - 1] != null) {
            if (misproductos[position - 1].getCantidad() == 0) {
                if (position != 0) {
                    System.out.println("¿Está seguro que quiere eliminar éste producto? [S]");
                    String answer = sc.nextLine();
                    if (answer.toUpperCase().equals("S")) {
                        misproductos[position - 1] = null;
                        System.out.println("Producto eliminado con éxito");
                    } else {
                        System.out.println("Opción cancelada");
                    }
                }
            } else {
                System.out.println("No se puede eliminar este producto porque aún hay Stock disponible");
            }
        } else {
            System.out.println("No hay ningún producto");
        }
    }

    /**método para mostrar el título de la máquina de cliente*/
    public static void titleClient() {
        System.out.println("----Máquina expendedora----");
        System.out.println(" ---------CLIENTE---------");
    }

    /**método para mostrar el título de la máquina de técnico*/
    public static void titleTec() {
        System.out.println("----Máquina expendedora----");
        System.out.println(" ---------TÉCNICO---------");
    }

    /**método para mostrar el menú técnico*/
    public static int menuTec() {
        System.out.println("1] Nuevo producto");
        System.out.println("2] Añadir stock");
        System.out.println("3] Eliminar producto");
        System.out.println("4] Información de la máquina");
        System.out.println("5] Salir a menú principal");
        int opt = sc.nextInt();
        return opt;
    }

    /**método para leer entradas del usuario*/
    public static int readPos() {
        System.out.println("Introducir la posición (0 para cancelar):");
        int posit = sc.nextInt();
        sc.nextLine(); //buffer
        return posit;
    }

    /**método para limitar las monedas*/
    public static boolean exactMoney(double dineroIntroducido) {
        if ((dineroIntroducido == 2.0) || (dineroIntroducido == 1.0) || (dineroIntroducido == 0.5)
                || (dineroIntroducido == 0.2) || (dineroIntroducido == 0.1) || (dineroIntroducido == 0.05)) {
            return true;
        } else {
            return false;
        }
    }

    /**método para inicializar array de monedas para gestionar el cambio*/
    public static void inicializarMonedasAceptadas() {
        monedasAceptadas[0] = new Monedas(2.0, 0);
        monedasAceptadas[1] = new Monedas(1.0, 0);
        monedasAceptadas[2] = new Monedas(0.50, 0);
        monedasAceptadas[3] = new Monedas(0.20, 0);
        monedasAceptadas[4] = new Monedas(0.10, 0);
        monedasAceptadas[5] = new Monedas(0.05, 0);
    }

    /**método para mostrar el dinero y las ventas generadas*/
    public static void DineroYVentas() {
        System.out.println("Dinero generado: " + contadorDinero);
        System.out.println("Ventas realizadas: " + contadorVentas);
    }
}