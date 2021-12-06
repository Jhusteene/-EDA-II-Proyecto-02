package arboles;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Esta clase contiene los métodos de estructuración para un árbol del tipo AVL
 * el cual trabaja y opera a través de su raíz cambiante a lo largo de las 
 * diferentes ejecuciones posibles, el objetivo de un árbol AVL es mantenerse 
 * siempre balanceado, proceso que se obtiene a través de las rotaciones que
 * llega a realizar tras la inserción o eliminación de valores
 * @author Equipo 10
 * @version 1.0
 */
public class AVL {
    /**
     * Identificador raíz del árbol AVL
     */
    public NodoAVL root;

    public AVL() {
        this.root = null;
    }
    
    /**
     * El método altura obtiene el atributo altura de un nodo dado, asimismo 
     * esta se inicializa en cero al tratarse de un nodo vacío
     * @param Nodo Nodo del que se desea obtener la altura
     * @return Regrese la altura del nodo pasado como parámetro
     */
    int altura (NodoAVL Nodo){
        if (Nodo == null) {
            return 0;
        }
        return Nodo.altura;
    }
    /**
     * Método para obtener el valor mayor entre dos números enteros dados
     * @param a Primer entero a comparar
     * @param b Segundo entero a comparar
     * @return el mayor de ambos por medio de un operador ternario de su 
     * comparación aritmética
     */
     int max (int a, int b){
         return(a>b) ? a:b;
     }
     /**
      * Método booleano que compueba si el árbol está vacío 
      * @return devuelve un valor true si la raíz es igual a null y false
      * de no serlo
      */
     public boolean isEmpty () {
        return root == null;
    }
     /**
      * Método que presenta la lógica para desarrollar rotación derecha simple 
      * o doble dependiendo de la implementación para árobles del tipo AVL, 
      * la rotación derecha se presenta cuando el árbol se encuentra 
      * desbalanceado por la iaquierda, es decir, tiene una sobre carga 
      * por el lado izquierdo obtenida a través de la diferencia de las alturas
      * @param nodo Recibe el nodo a rotar por derecha
      * @return Regresa el nodo pasado como parámetro con una rotación o 
      * reasignación derecha simple
      */
     NodoAVL rotacionDerecha(NodoAVL nodo){
         //Instancia los valores a emplear para realizar la reasignación
         NodoAVL tmp1 = nodo.izquierdo;
         NodoAVL tmp2 = tmp1.derecho;
         //Sustituye los valores deacuerdo a la rotación derecha simple
         tmp1.derecho = nodo;
         nodo.izquierdo = tmp2;
         //Se reasigna el valor de las alturas
         nodo.altura = max(altura(nodo.izquierdo), altura(nodo.derecho)) + 1;
         tmp1.altura = max(altura(tmp1.izquierdo), altura(tmp1.derecho)) + 1;
         //Regresa el nuevo nodo rotado
         return tmp1;
     }
     /**
      * Método que presenta la lógica para desarrollar rotación izquierda simple 
      * o doble dependiendo de la implementación para árobles del tipo AVL, 
      * la rotación izquierda se presenta cuando el árbol se encuentra 
      * desbalanceado por la derecha, es decir, tiene una sobre carga 
      * por el lado derecho obtenida a través de la diferencia de las alturas 
      * @param nodo Recibe el nodo a rotar por izquierda
      * @return Regresa el nodo pasado como parámetro con una rotación o 
      * reasignación izquierda simple
      */
     NodoAVL rotacionIzquierda(NodoAVL nodo){
         //Instancia los valores a emplear para realizar la reasignación
         NodoAVL tmp1 = nodo.derecho;
         NodoAVL tmp2 = tmp1.izquierdo;
         //Sustituye los valores deacuerdo a la rotación izquierda simple
         tmp1.izquierdo = nodo;
         nodo.derecho = tmp2;
         //Se reasigna el valor de las alturas
         nodo.altura = max(altura(nodo.izquierdo), altura(nodo.derecho)) + 1;
         tmp1.altura = max(altura(tmp1.izquierdo), altura(tmp1.derecho)) + 1;
         //Regresa el nuevo nodo rotado
         return tmp1;
     }
     /**
      * Método que obtiene el equilibrio o balance a partir de un nodo dado, 
      * este valor corresponde a la resta de la altura de su nodo hijo 
      * izquierdo menos la altura su nodo hijo derecho
      * @param nodo Recibe el nodo al cual obtener su balance
      * @return Devuelve como parámetro el número entero equibalente al 
      * equilibrio o balance
      */
     int equilibrio (NodoAVL nodo){
         if (nodo == null) {
             return 0;
         }
         return altura(nodo.izquierdo) - altura(nodo.derecho);
     }
     /**
      * Método par instar un nuevo valor a un árbol AVL por medio de 
      * recursividad a través del recorrido entre los valores asignados a las 
      * claves hasta llegar al caso donde el nodo actual sea nulo y se agregue 
      * un nuevo nodo con el valor entero pasado como parámetro, realizando la
      * comprobación si se requiere relizar rotaciones simples o dobles y 
      * reasignando el valor de la altura del nodo a uno más la altura máxima 
      * entre las alturas del nodo izquierdo i derecho
      * @param nodo Recibe como parámetro el nodoAVL al cual insertar el 
      * nuevo valor 
      * @param valor Recibe el número entero a agregar 
      * @return Regresa el nodoAVL con el valor dado ya insertado
      */
     public NodoAVL insertarAVL (NodoAVL nodo, int valor){
         //Caso base
         if (nodo == null) {
             return (new NodoAVL(valor));
         }
         //Validaciones de los valores dentro del árbol
         if (valor < nodo.clave) 
             nodo.izquierdo = insertarAVL(nodo.izquierdo, valor);
         else if (valor > nodo.clave)
             nodo.derecho = insertarAVL(nodo.derecho,  valor);
         else
             return nodo;
         //Comprobacion del balance tras reasignar la altura
         nodo.altura = 1 + max(altura(nodo.izquierdo), altura(nodo.derecho));
         
         int eq = equilibrio(nodo);
         //Rotación derecha simple
         if (eq > 1 && valor < nodo.izquierdo.clave) {
             return rotacionDerecha(nodo);
         }
         //Rotación izquierda simple
         if (eq < -1 && valor > nodo.derecho.clave) {
             return rotacionIzquierda(nodo);
         }
         //Rotación derecha doble
         if (eq > 1 && valor > nodo.izquierdo.clave) {
             nodo.izquierdo = rotacionIzquierda(nodo.izquierdo);
             return rotacionDerecha(nodo);
         }
         //Rotación izquierda doble
         if (eq < -1 && valor < nodo.derecho.clave) {
             nodo.derecho = rotacionDerecha(nodo.derecho);
             return rotacionIzquierda(nodo);
         }
         return nodo;
     }
     /**
      * Método que obtiene el nodo hijo mínimo de un nodo dado, al trataerse 
      * de un árbol binario ed búsqueda, este corresponde a el nodo más 
      * izquierdo de sus hijos
      * @param nododo Recibe el nodo sobre el cuál obtener el nodo mínimo
      * @return Devuelve el nodo mínimo obtenido
      */
     NodoAVL valorMinimo(NodoAVL nodo){
         NodoAVL tmp1 = nodo;
         //Obtener el valor mínimo de un árbol binario es el maayor
         //recorrido izquierdo posible
         while (tmp1.izquierdo != null) {
             tmp1 = tmp1.izquierdo;
         }
         return tmp1;
     }
     /**
      * Método para eliminar algún valor dado dentro de una secuencia de 
      * nodosAVL por medio de una recursividad que llega e un caso base de 
      * sustitución del valor por un nodo temporal nulo, en los diferentes casos
      * dependiendo de la cantidad de hijos no nulos, posterioremtne se reasigna
      * el valor de la altura del nodo en turno y se validan las rotaciones 
      * necesarias para manetener el nodo AVL balanceado
      * @param raiz Recibe el parámetro del nodo a realizar la eliminación
      * @param valor Recibe el valor a eliminar dentro del nodo
      * @return devuelve el nodo con el valor dado ya eliminado
      */
     public NodoAVL eliminarAVL(NodoAVL raiz, int valor){
//         if (buscarAVLbool(raiz, valor)==false) {
//             System.out.println("El valor no se encuentra dentro de la lista ");
//         }
//         else 
         //Caso base
         if (raiz == null) {
             return raiz;
         }
         //Validaciones de los valores dentro del árbol
         if (valor < raiz.clave) {
             raiz.izquierdo = eliminarAVL(raiz.izquierdo, valor);
         }
         else if (valor > raiz.clave) {
             raiz.derecho = eliminarAVL(raiz.derecho, valor);
         }
         //Proceso de eliminación en los diferentes casos
         else {
             if((raiz.izquierdo == null)||(raiz.derecho == null)){
                 NodoAVL tmp1 = null;
                 if (tmp1 == raiz.izquierdo) {
                     tmp1 = raiz.derecho;
                 } else{
                     tmp1 = raiz.izquierdo;
                 }
                 if (tmp1 == null) {
                     tmp1 = raiz;
                     raiz = null;
                 } else {
                     raiz = tmp1;
                 }
             } else {
                 NodoAVL tmp1 = valorMinimo(raiz.derecho);
                 raiz.clave = tmp1.clave;
                 raiz.derecho = eliminarAVL(raiz.derecho, tmp1.clave);
             }
         }
         //Comprobacion del balance tras reasignar la altura
         if (raiz == null) {
             return raiz;
         }
         raiz.altura = max(altura(raiz.izquierdo), altura(raiz.derecho)) + 1;
         
         int eq = equilibrio(raiz);
         //Rotación derecha simple
         if (eq > 1 && equilibrio(raiz.izquierdo) >= 0) {
             return rotacionDerecha(raiz);
         }
         //Rotación izquierda simple
         if (eq > 1 && equilibrio(raiz.izquierdo) < 0) {
             raiz.izquierdo = rotacionIzquierda(raiz.izquierdo);
             return rotacionDerecha(raiz);
         }
         //Rotación derecha doble
         if (eq < -1 && equilibrio(raiz.derecho) <= 0) {
             return rotacionIzquierda(raiz);
         }
         //Rotación izquierda simple
         if (eq < -1 && equilibrio(raiz.derecho) > 0) {
             raiz.derecho = rotacionDerecha(raiz.derecho);
             return rotacionIzquierda(raiz);
         }
         return raiz;
     }
     /**
      * Método para buscar un valor dentro de un árbol AVL por medio de 
      * recursividad y las diferentes posibilidades de un nodo binario de 
      * búsqueda para avanzar secancialmentehasta encontrar o no un valor 
      * equivalente al dado, imprimiendo en pantalla en caso de haber sido 
      * encontrado o no
      * @param nodo Recibe el nodo a partir del cual se realizará la búsqueda
      * @param elem Recibe el valor entero a buscar dentro del nodo dado y 
      * sus hijos
      * @return Devuelve el nodo AVL para poder realizar la recursividad, 
      * en caso de encontrarlo devuelve el mismo nodo y de no hacerlo 
      * devuelve el valor null
      */
     public NodoAVL buscarAVL (NodoAVL nodo, int elem){
         //Validación sobre si está o no vacío
        if (nodo==null) {
             System.out.println("El árbol AVl se encuentra vacío");
             return nodo;
         }
        //Caso encontrado
        if(elem == nodo.clave){
            System.out.println("El valor " + nodo.clave + " sí se enceuntra dentro del árbol");
            return nodo;
        }
        //Continua el recorrido a la izquierda
        else if(elem < nodo.clave && nodo.izquierdo != null){
            nodo = buscarAVL(nodo.izquierdo, elem);
        }
        //Continua el recorrido a la derecha
        else if(elem > nodo.clave && nodo.derecho != null){
            nodo = buscarAVL(nodo.derecho, elem);
        }
        //Caso no encontrado
        else{
            System.out.println("El valor " + elem + " no se enceuntra dentro del árbol");
            return null;
        }
        //Regresa el nodo en caso de encontrarse
        return nodo;
     }
     /**
      * Método que realiza las mismas soperaciones que el de búsqueda anterior 
      * pero sin realizar las impresiones en pantalla
      * @param nodo Recibe el nodo a partir del cual se realizará la búsqueda
      * @param elem Recibe el valor entero a buscar dentro del nodo dado y 
      * sus hijos
      * @return Devuelve el nodo AVL para poder realizar la recursividad, 
      * en caso de encontrarlo devuelve el mismo nodo y de no hacerlo 
      * devuelve el valor null
      */
     public NodoAVL buscarAVLbool (NodoAVL nodo, int elem){
         if (nodo==null) {
             System.out.println("vacíoooo");
             return nodo;
         }
        if(elem == nodo.clave){
            return nodo;
        }
        //Se borran las impresiones en pantalla
        else if(elem < nodo.clave && nodo.izquierdo != null){
            nodo = buscarAVLbool(nodo.izquierdo, elem);
        }

        else if(elem > nodo.clave && nodo.derecho != null){
            nodo = buscarAVLbool(nodo.derecho, elem);
        }
    
        else{
            return null;
        }
        
        return nodo;
     }
     /**
      * Método que no devuelve ni recive parámetro alguno, se emplea para 
      * realizar la impresiónen pantalla del árbol por medio del recorrido BFS
      */
     public void breadthFirst(){
        // Si el heap está vacío, notifica al usuario y termina la ejecución del método.
        if ( isEmpty() ) {
            System.out.println("No hay elementos en el árbol AVL.");
            return;
        }
        // Realiza el recorrido BFS
        NodoAVL r = root;
        Queue<NodoAVL> queue = new LinkedList<>();
        queue.add( r );
        // Mientras la cola no esté vacía.
        while ( !queue.isEmpty() ){
            // Se extrae un nodo de la cola
            r = ( NodoAVL ) queue.poll();
            // Imprime la información del nodo.
            System.out.println( r.toString() ) ;
            // Si tiene hijos los agrega a la cola.
            if( r.izquierdo != null )
                queue.add( r.izquierdo);
            if( r.derecho != null )
                queue.add(r.derecho);
        }
    }
     
}
