package arboles;

/**
 * Esta clase contiene la abstracción de un nodo, con un operador u operando
 * com valor, que pertenece a un árbol de expresión aritmética. 
 * @author Equipo 10
 * @version 1.0
 */
public class NodoAEA {
    
    /**
     * Nodos hijos, tanto izquierdo como derecho.
     */
    public NodoAEA izq, der;
    
    /**
     * Valor del nodo (operador u operando).
     */
    public String valor;
    
    /**
     * Constructor que inicializa el valor, hijo izquiero e hijo derecho del nodo
     * en sus correspondientes atributos.
     * @param valor Valor que se le asignará al nodo.
     * @param izq Hijo izquierdo del nodo.
     * @param der Hijo derecho del nodo.
     */
    public NodoAEA ( String valor, NodoAEA izq, NodoAEA der  ) {
        this.valor = valor;
        this.izq = izq;
        this.der = der;
    }

    /**
     * Constructor que inicializa el valor del nodo en atributo correspondiente.
     * @param valor Valor que se le asignará al nodo.
     */
    public NodoAEA ( String valor ) {
        this.valor = valor;
    }

    /**
     * Constructor vacío de la clase.
     */
    public NodoAEA () {

    }

    /**
     * Asigna un nodo al hijo izquierdo.
     * @param izq Nodo a asignar
     */
    public void setHijoIzquierdo ( NodoAEA izq ) {
        this.izq = izq;
    }

    /**
     * Asigna un nodo al hijo derecho.
     * @param der Nodo a asignar
     */
    public void setHijoDer ( NodoAEA der ) {
        this.der = der;
    }

    /**
     * Regresa el hijo izquierdo del nodo.
     * @return El hijo izquierdo del nodo.
     */
    public NodoAEA getHijoIzquierdo ( ) {
        return izq;
    }

    /**
     * Regresa el hijo derecho del nodo.
     * @return El hijo derecho del nodo.
     */
    public NodoAEA getHijoDerecho ( ) {
        return der;
    }

    /**
     * Regresa el valor del nodo.
     * @return El valor del nodo.
     */
    public String getValor () {
        return valor;
    }

    /**
     * Asigna un valor al nodo.
     * @param valor Valor a asignar.
     */
    public void setValor ( String valor ) {
        this.valor = valor;
    }
    
}
