package arboles;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * Esta clase contiene la abstracción de un árbol de expresión aritmética el cual se crea
 * a partir de una expresión aritmética dada como la siguiente: ((3+2)/(4*8))+(10-5)
 * Se puede obtener la notación postfija del árbol para poder resolver la expresión
 * mediante dos pilas, una de operadores y otra de operandos.
 * @author Equipo 10
 * @version 1.0
 */

public class ArbolExpresionAritmetica {
    
    /**
     * Raíz del árbol.
     */
    private NodoAEA raiz;
    
    /**
     * Jerarquía de operaciones.
     */
    private static Set<List<String>> jerarquia;
    
    /**
     * Constructor que recibe una expresión para construir su árbol correspondiente.
     * @param expresion Expresión de la cual se construye el árbol.
     */
    public ArbolExpresionAritmetica( String expresion ) {
        raiz = new NodoAEA();
        inicializarJerarquia();
        construirArbol(expresion.replaceAll(" ", ""), raiz);
    }

    /**
     * Método que inicializa la jerarquería de operaciones aritmética si no
     * ha sido inicializada.
     */
    public final void inicializarJerarquia () {
        // Se inicializa la jerarquía en caso de que no se haya inicializado.
        if ( jerarquia == null ) {
            jerarquia = new LinkedHashSet<>();
            jerarquia.add( new LinkedList<>( Arrays.asList( "+", "-" ) ) );
            jerarquia.add( new LinkedList<>( Arrays.asList( "*", "/" ) ) );
        }
    }

    /**
     * Método que evalua si una cadena es un número real.
     * @param num Cadena a evaluar.
     * @return Regresa true si la cadena pasada como parámetro es un número
     * real. En caso contrario regresa false.
     */
    public boolean esNumero ( String num ) {
        try {
            float n = Float.parseFloat( num );
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Método que evalua si la cadena pasada es un paréntesis y de qué tipo.
     * @param cadena Cadena a evaluar.
     * @return Regresa 1 si la cadena es un paréntesis que abre, 2 si es un
     * paréntesis que cierra, o en su defecto 0.
     */
    public int cualParentesis ( String cadena ) {
        return cadena.equals( "(" ) ? 1 : ( cadena.equals( ")" ) ? -1 : 0 );
    }

    /**
     * Método que realiza la operación indicada con dos números dados.
     * @param operador Operador que indica la operación a realizar.
     * @param num1 Primer operando de la operación.
     * @param num2 Segundo operando de la operación.
     * @return Regresa el resultado de la operación indicada. En caso de alguna
     * inválidez, regresa una cadena vacía.
     */
    public String hacerOperacion ( String operador, String num1, String num2 ) throws ArithmeticException {
        float n1, n2;
        // Se convierten las cadenas a números reales.
        try {
            n1 = Float.parseFloat( num1 );
            n2 = Float.parseFloat( num2 );
        } catch (InputMismatchException e) {
            System.out.println("Solo se aceptan números. Expresión inválida.");
            return "";
        }
        // Dependiendo del operador dado, se hace la oepración coreespondiente.
        switch ( operador ) {
            case "+":
                return String.valueOf( n1 + n2 );

            case "-":
                return String.valueOf( n1 - n2 );

            case "*":
                return String.valueOf( n1 * n2 );

            case "/":
                if ( n2 == 0 ) {
                    throw new ArithmeticException();
                }
                return String.valueOf( n1 / n2 );
        }
        return "";
    }

    /**
     * Método que construye el árbol dada una expresión recibida y el nodo
     * a partir del cual construirá sus dos hijos o sus dos subárboles.
     * Este método funciona de manera recursiva, por lo que el nodo será hijo
     * del anterior (o la raíz en un inicio) y la expresión será una subcadena
     * de la expresión original.
     * @param expresion Expresión a analizar.
     * @param nodo Nodo actual de la construcción del árbol.
     */
    public final void construirArbol ( String expresion, NodoAEA nodo ) {
        int pos, posOperador, i, controlParentesis;
        // Se recorre la jeraquía nivel por nivel
        for (List<String> nivel : jerarquia ) {
            pos = -1;
            // Se recorren todos los operadores del nivel
            for (String operador : nivel) {
                posOperador = -1;
                controlParentesis = 0;
                // Se recorre la expresión caracter por caracter
                for ( i = 0; i < expresion.length(); i++ ) {
                    // Se lleva un control de los paréntesis
                    // Es cero si el número de paréntesis abiertos es igual al de los cerrados (está balanceado el contador)
                    // Es negativo si hay más cerrados y es positivo si hay más abiertos
                    controlParentesis += cualParentesis(String.valueOf(expresion.charAt(i)));
                    // Si el contador está balanceado y el operador actual es igual al caracter igual,
                    // se considera como la última posición hasta el momento.
                    if ( controlParentesis == 0 && operador.equals(String.valueOf(expresion.charAt(i))) ) {
                        posOperador = i;
                    }
                }
                /*
                // Se intentó realizar la búsqueda de un operador que no estuviera encerrado por
                // paréntesis. Sin embargo, no se logró concretar una regex (expresión regular)
                // que pudiera detectar únicamente a dichos operadores.
                posOperador = -1;
                Pattern pattern = Pattern.compile("(?!\\([^\\)]*)\\" + operador + "(?![^\\(]*\\))");
                Matcher matcher = pattern.matcher(expresion);
                while (matcher.find())
                    posOperador = matcher.start();
                */
                if ( posOperador > pos ) {
                    pos = posOperador;
                }
            }
            // Si hubo un operador en la expresión no encerrado entre paréntesis en este nivel de la jerarquía
            if ( pos != -1 ) {
                // Se asigan el operador al valor del nodo
                nodo.setValor( String.valueOf( expresion.charAt(pos) ) );
                // Se crean sus nodos hijos
                nodo.izq = new NodoAEA();
                nodo.der = new NodoAEA();
                // Se emplea la recursividad diviendo la expresión en dos de acuerdo al operador encontrado
                construirArbol( expresion.substring( 0, pos ), nodo.getHijoIzquierdo() );
                construirArbol( expresion.substring( pos + 1 ), nodo.getHijoDerecho() );
                return;
            }
        }
        // Si la expresión empieza y termina con paréntesis, se usa la recursividad
        // para volver a analizar lo de adentro del programa.
        if ( expresion.startsWith("(") && expresion.endsWith(")") ) {
            construirArbol( expresion.substring( 1, expresion.length() - 1 ), nodo );
            return;
        }
        // Si la expresión está vacía, se le asigna cero como valor al nodo
        // En caso contrario, se le asigna la expresión al nodo
        if ( expresion.equals("") )
            nodo.setValor( "0" );
        else
            nodo.setValor( expresion );
    }

    /**
     * Método que resuelve la expresión ya convertida en el árbol con ayuda de
     * dos pilas, una para operandos y otra para operadores.
     * @return Regresa el resultado de la expresión guardada en el árbol.
     */
    public ResultadoAEA resolverExpresion ()  {
        // Se recorre el árbol de manera postfija y se guarda cada nodo
        // visitado en una cola.
        Queue<String> cola = new LinkedList<>();
        recorrerNotacionPostFija( raiz, cola );
        // Pilas para operandos y operadores
        Stack<String> operandos = new Stack<>();
        Stack<String> operadores = new Stack<>();
        if ( !cola.isEmpty() ) {
            String elemento;
            String n1, n2;
            // Se extraen los elementos de la cola para analizarlos
            do {
                // Se extrae un elemento
                elemento = cola.poll();
                // Si el elemento es un número, lo inserta en la pila de operandos
                // En caso contrario lo inserta en la pila de operadores
                if ( esNumero( elemento ) ) {
                    operandos.push( elemento );
                } else {
                    operadores.push( elemento );
                }
                // Si la pila de operadores no está vacía, significa que se debe
                // de realizar una operación.
                if ( !operadores.isEmpty() ) {
                    // Se extraen los últimos dos operandos insertados.
                    try {
                        n2 = operandos.pop() ;
                        n1 = operandos.pop() ;
                    }
                    catch (Exception e) {
                        return new ResultadoAEA("Expresión inválida. Operandos incompletos.");
                    }
                    // Se hace la operación correspondiente con los operandos
                    try {
                        operandos.push( hacerOperacion( operadores.pop(), n1, n2 ) );
                    }
                    catch (ArithmeticException e) {
                        return new ResultadoAEA("No se puede realizar la división entre cero. Expresión inválida.");
                    }
                }
            } while ( !cola.isEmpty() );
            // Regresa el resultado final
            return new ResultadoAEA(Float.parseFloat( operandos.pop() ));
        }
        return new ResultadoAEA("Expresión inválida.");
    }

    /**
     * Método que guarda, mediante la notación postifija, los valores de cada
     * nodo del árbol en una cola dada.
     * @param nodo Nodo actual
     * @param cola Cola donde se almacenaran los valores de los nodos visitados.
     */
    public void recorrerNotacionPostFija( NodoAEA nodo, Queue<String> cola ){
        if ( nodo == null )
            return;
        recorrerNotacionPostFija( nodo.izq, cola );
        recorrerNotacionPostFija( nodo.der, cola );
        cola.add( nodo.getValor() );
    }
    
    /**
     * Método que muestra el árbol en su notación postfija.
     * @param nodo Nodo actual
     * @param cola Cola donde se almacenaran los valores de los nodos visitados.
     */
    public void mostrar(){
        recorrerNotacionPostFija( raiz );
        System.out.println("");
    }
    
    public void recorrerNotacionPostFija( NodoAEA nodo ){
        if ( nodo == null )
            return;
        recorrerNotacionPostFija( nodo.izq );
        recorrerNotacionPostFija( nodo.der );
        System.out.print( nodo.getValor() + " " );
    }
}