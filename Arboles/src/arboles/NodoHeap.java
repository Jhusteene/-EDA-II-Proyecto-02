package arboles;

import java.util.LinkedList;
import java.util.List;

/**
 * Esta clase contiene la abstracción de un nodo de un heap el cual se crea con
 * o sin valor.
 * @author Equipo 10
 * @version 1.0
 */
public class NodoHeap {
    /**
     * Valor del nodo.
     */
    int valor;
    
    /**
     * Nodo hijo izquierdo del nodo.
     */
    NodoHeap izq;
    
    /**
     * Nodo hijo derecho del nodo.
     */
    NodoHeap der;
    
    /**
     * Nivel del nodo.
     */
    int nivel;
    
    /**
     * Nodo padre del nodo.
     */
    NodoHeap padre;
    
    /**
     * Constructor que recibe un entero y lo asigna como el valor del nodo.
     * @param val Entero que será el valor del nodo.
     */
    public NodoHeap ( int val ) {
        this.valor = val;
    }
    
    /**
     * Constructor vacío.
     */
    public NodoHeap () {
    }

    /**
     * Modifica el hijo izquierdo del nodo.
     * @param hijo Nuevo hijo izquierdo.
     */
    void setIzq(NodoHeap hijo) {
        izq = hijo;
    }

    /**
     * Modifica el hijo derecho del nodo.
     * @param hijo Nuevo hijo derecho.
     */
    void setDer(NodoHeap hijo) {
        der = hijo;
    }
    
    /**
     * Modifica el nivel del nodo.
     * @param nivel 
     */
    void setNivel ( int nivel ) {
        this.nivel = nivel;
    }
    
    /**
     * Modifica el padrel del nodo.
     * @param padre Nuevo padre del nodo.
     */
    void setPadre(NodoHeap padre) {
        this.padre = padre;
    }
    
    /**
     * Regresa una cadena con la información del nodo.
     * @return Una cadena con la información del nodo.
     */
    @Override
    public String toString() {
        return valor + ", izq:" + (izq==null ? "no" : izq.valor) + ", der:" + (der==null ? "no" : der.valor) + ", padre:" + (padre==null ? "no" : padre.valor);
    }
}
