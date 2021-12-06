/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

/**
 *
 * @author MarioTeran
 */
public class NodoAVL {
    /**
     * Atributo de la alturo de un nodo 
     */
    int altura;
    /**
     * Atributo asignado al valor que representa un nodo
     */
    int clave;
    /**
     * Atributo del hijo izquiero de un nodo
     */
    NodoAVL izquierdo;
    /**
     * Atributo del hijo derecho de un nodo
     */
    NodoAVL derecho;
    /**
     * Constructuor de un nodo con un valor dado asignado a la clave y con 
     * una altura automática de 1
     * @param valor Reibe como parámetro el valor a asignar como atributo clave
     */
    NodoAVL(int valor){
        clave = valor;
        altura = 1;
    }
    /**
     * Sobreescribe el método de toString para imprimir los hijos del nodo 
     *  con un formato establecido
     * @return Devuelve la cadena de caracteres de los atributos con el 
     * formato preestablecido
     */
    @Override
    public String toString() {
        return clave + ", Izq: " + (izquierdo==null ? "No" : izquierdo.clave) + 
                ", Der: " + (derecho==null ? "No" : derecho.clave)  ;
    }
}
