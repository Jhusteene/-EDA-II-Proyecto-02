package proyecto2;

import arboles.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase principal del proyecto donde se despliega el menú principal para poder
 * acceder a las funcionalidades solicitadas.
 * @author Equipo 10
 */
public class Main {
    
    /**
     * Método que agrega al heap recibido el o los valores recibidos.
     * @param h Heap en donde se agregar las claves.
     * @param entrada Valor o valores que se desean agregar al heap.
     */
    public static void agregarValoresAlHeap ( Heap h, String entrada ) {
        String valores[] = entrada.split(" "); 
        for ( String valor : valores )
            if ( !valor.equals("") )
                h.add( new NodoHeap( Integer.parseInt( valor ) ) );
    }
    public static NodoAVL agregarValoresAlAVL ( AVL avl, String entrada ) {
        String valores[] = entrada.split(" ");
        for ( String valor : valores )
            if ( !valor.equals("") )
                avl.insertarAVL(avl.root, Integer.parseInt( valor )  );
        return avl.root;
    }

    /**
     * Método principal el cual es el punto de entrada al programa.
     * @param args Argumentos de la línea de comnado.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner( System.in );
        String opcion, subopcion;
        Heap h = new Heap();
        AVL avl = new AVL();
        ArbolExpresionAritmetica aea = null;
        ResultadoAEA resultado;
        int num;
        //        h.add( new NodoHeap( 43 ) );
        //        h.add( new NodoHeap( 8 ) );
        //        h.add( new NodoHeap( 52 ) );
        //        h.add( new NodoHeap( 15 ) );
        //        h.add( new NodoHeap( 4 ) );
        //        h.add( new NodoHeap( 37 ) );
        //        h.add( new NodoHeap( 80 ) );
        //        h.add( new NodoHeap( 2 ) );
        //        h.add( new NodoHeap( 90 ) );
        do {
            System.out.println("Proyecto 333333 Árboles - Equipo 10");
            System.out.println("1 -> AVL");
            System.out.println("2 -> Heap");
            System.out.println("3 -> Árbol de Expresión Aritmética (AEA)");
            System.out.println("4 -> Salir");
            System.out.print(">> Opción: ");
            opcion = sc.nextLine();
            System.out.println("\n------------------------\n");
            switch ( opcion ) {
                case "1":
                    do {
                        System.out.println("\t-- AVL --");
                        System.out.println("1 -> Agregar clave");
                        System.out.println("2 -> Buscar un valor");
                        System.out.println("3 -> Eliminar clave");
                        System.out.println("4 -> Mostrar árbol");
                        System.out.println("5 -> Regresar");
                        System.out.print(">> Opción: ");
                        subopcion = sc.nextLine();
                        System.out.println("\n========================\n");
                        int seleccion;
                        String agregar;
                        switch ( subopcion ) {
                            case "1":
                                System.out.println("Ingresa la clave a agregar.");
                                System.out.println("Si quieres agregar varias sepáralas por espacios.");
                                System.out.println("Clave(s): ");
                                agregar = sc.nextLine();
                                //Ejemplos de entrada
                                //10 100 20 80 40 50 30
                                String valores[] = agregar.split(" ");
                                for ( String valor : valores )
                                    if ( !valor.equals("") )
                                        avl.root = avl.insertarAVL(avl.root, Integer.parseInt( valor )  );
                                break;

                            case "2":
                                System.out.println("Ingrese el valor a buscar.");
                                try {
                                    seleccion = Integer.parseInt( sc.nextLine() );
                                }
                                catch ( InputMismatchException e ) {
                                    System.out.println("No ingresó un número.");
                                    break;
                                }
                                System.out.println("\n========================\n");
                                avl.buscarAVL(avl.root, seleccion);
                                break;
                            case "3":
                                System.out.println("Ingrese la clave a eliminar.");
                                try {
                                    seleccion = Integer.parseInt( sc.nextLine() );
                                }
                                catch ( InputMismatchException e ) {
                                    System.out.println("No ingresó un número.");
                                    break;
                                }
                                System.out.println("\n========================\n");
                                AVL tmp = new AVL();
                                tmp.root= avl.root;
                                
                                if (avl.buscarAVLbool(avl.root, seleccion)!=null) {
                                    avl.root = avl.eliminarAVL(avl.root, seleccion);
                                    System.out.println("Valor "+seleccion+" eliminado del árbol AVL");
                                } else{
                                    System.out.println("El valor "+seleccion+" no fue encontrado dentro del árbol.");
                                }
                                break;

                            case "4":
                                avl.breadthFirst();
                                break;

                            case "5":
                                System.out.println("Regresando...");
                                break;

                            default:
                                System.out.println("Opción inválida.");
                        }
                        System.out.println("\n========================\n"); 
                    } while( !subopcion.equals("5") );
                    break;
                    
                case "2":
                    do {
                        System.out.println("\t-- HEAP --");
                        System.out.println("1 -> Agregar clave (o claves separadas por espacios)");
                        System.out.println("2 -> Eliminar raíz");
                        System.out.println("3 -> Mostrar árbol");
                        System.out.println("4 -> Buscar clave");
                        System.out.println("5 -> Regresar");
                        System.out.print(">> Opción: ");
                        subopcion = sc.nextLine();
                        System.out.println("\n========================\n");
                        switch ( subopcion ) {
                            case "1":
                                System.out.println("Ingresa la clave a agregar.");
                                System.out.println("Si quieres agregar varias sepáralas por espacios.");
                                System.out.println("Clave(s): ");
                                //Ejemplos de entrada
                                //43 8 52 15 4 37 80 2 90
                                //25 26 12 22 38 28
                                agregarValoresAlHeap(h, sc.nextLine());
                                break;

                            case "2":
                                if ( h.deleteRoot() )
                                    System.out.println("Raíz eliminada.");
                                else
                                    System.out.println("No hay elementos en el heap.");
                                break;

                            case "3":
                                h.breadthFirst();
                                break;

                            case "4":
                                System.out.print("Ingresa la clave a buscar: ");
                                try {
                                    num = Integer.parseInt( sc.nextLine() );
                                }
                                catch ( InputMismatchException e ) {
                                    System.out.println("No ingresó un número.");
                                    break;
                                }
                                if ( h.search( num ) )
                                    System.out.println("Sí existe la clave.");
                                else
                                    System.out.println("No existe la clave.");
                                break;

                            case "5":
                                System.out.println("Regresando...");
                                break;

                            default:
                                System.out.println("Opción inválida.");
                        }
                        System.out.println("\n========================\n"); 
                    } while( !subopcion.equals("5") );
                    break;
                    
                case "3":
                    do {
                        System.out.println("\t-- AEA --");
                        System.out.println("1 -> Ingresar expresión");
                        System.out.println("2 -> Mostrar árbol");
                        System.out.println("3 -> Resolver");
                        System.out.println("4 -> Regresar");
                        System.out.print(">> Opción: ");
                        subopcion = sc.nextLine();
                        System.out.println("\n========================\n");
                        switch ( subopcion ) {
                            case "1":
                                System.out.print("Ingrese la expresión a resolver: ");
                                //Ejemplo de entrada
                                //5*3+2*64/9-6
                                //((3+2)/(4*8))+(10-5)
                                //7*1-(2+6)*23
                                aea = new ArbolExpresionAritmetica(sc.nextLine());
                                break;

                            case "2":
                                if ( aea != null )
                                    aea.mostrar();
                                else
                                    System.out.println("No ha ingresado un aexpresión en la opción 1.");
                                break;



                            case "3":
                                if ( aea != null ) {
                                    resultado = aea.resolverExpresion();
                                    if ( !resultado.hayError() )
                                        System.out.println( "El resultado de la expresión es " + resultado.getResultado() );
                                    else
                                        System.out.println(resultado.getMensajerError());
                                }
                                else
                                    System.out.println("No ha ingresado un aexpresión en la opción 1.");
                                break;

                            case "4":
                                System.out.println("Regresando...");
                                break;

                            default:
                                System.out.println("Opción inválida.");
                        }
                        System.out.println("\n========================\n"); 
                    } while( !subopcion.equals("4") );
                    break;
                    
                case "4":
                    System.out.println("Hasta luego.");
                    break;
                
                default:
                    System.out.println("Opción inválida.");
            }
            System.out.println("\n------------------------\n");
        } while( !opcion.equals("4") );
    }
    
}
