package arboles;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Esta clase contiene la abstracción de un heap el cual se crea vacío o con una
 * raíz. Un heap consiste en un árbol binario completo y balanceado para almacenar
 * valores. En un heap, los hijos siempre son menores que su papá y por lo tanto,
 * la raíz siempre es el elemento mayor en el árbol.
 * @author Equipo 10
 * @version 1.0
 */
public class Heap {
    /**
     * Raíz del heap.
     */
    NodoHeap root;
    
    /**
     * Constructor vacío.
     */
    public Heap(){
    }
    
    /**
     * Constructor que recibe un entero, crea un nodo con él y asigan este nodo
     * como raíz del árbol.
     * @param val Valor del nodo raíz.
     */
    public Heap( int val ){
        root = new NodoHeap( val );
    }
    
    /**
     * Constructor que recibe un nodo el cual se asignará como la raíz del heap.
     * @param root Nodo que se asignará como raíz.
     */
    public Heap( NodoHeap root ){
        this.root = root;
    }

    /**
     * Método que regresa la ráiz del heap.
     * @return La raíz del heap.
     */
    public NodoHeap getRoot() {
        return root;
    }
    
    /**
     * Método que agrega en el heap el nodo recibido como parámetro respetando
     * la condición de estructura del heap.
     * @param nuevo El nodo a agregar.
     */
    public void add( NodoHeap nuevo ) {
        // Si ya existe un nodo con ese valor, lo notifica el usuario y termina
        // la ejecucuión del método.
        if ( getNode( nuevo.valor ) != null ) {
            System.out.println("Ya existe el elemento " + nuevo.valor + " en el heap.");
            return;
        }
        // Busca al nodo en donde se debe insertar el nuevo
        NodoHeap nodoPadre = getLastAvailableFather( root );
        // Si es nulo, el nuevo nodo lo asigna la raíz y termina la ejecución
        // del método.
        if ( nodoPadre == null ){
            root = nuevo;
            root.setPadre( null );
            return;
        }
        // Establece valores al nivel y padre del nuevo nodo
        nuevo.setNivel( nodoPadre.nivel + 1 );
        nuevo.setPadre( nodoPadre );
        // Asigna como hijo el nuevo nodo al correspondiente padre como
        // hijo izquierdo o derecho.
        if ( nodoPadre.izq == null ) {
            nodoPadre.setIzq( nuevo );
        }
        else {
            nodoPadre.setDer( nuevo );
        }
        // Verifica desde el nuevo nodo hasta la raíz que el heap siga teniendo
        // su condición de estructura.
        heapify( nuevo );
    }
    
    /**
     * Método que regresa el nodo en el que se debe agrega un nuevo nodo buscándolo
     * de manera recursiva.
     * @param node Nodo el cual se analiza.
     * @return El nodo donde se debe agregar el nodo.
     */
    public NodoHeap getLastAvailableFather ( NodoHeap node ) {
        // Caso base
        if ( node == null || node.izq == null || node.der == null )
            return node;
        // Recursividad para verificar los subárboles
        NodoHeap izq = getLastAvailableFather(node.izq);
        NodoHeap der = getLastAvailableFather(node.der);
        // Se obtiene la altura, en caso de ser nulo se le asigna uno.
        int altIzq = izq != null ? izq.nivel : 1;
        int altDer = der != null ? der.nivel : 1;
        // Si son iguales, se le da preferencia al lado izquerdo. En caso
        // contrario, regresa el nodo hijo derecho.
        if ( altIzq == altDer ) {
            return izq;
        }
        else {
            return der;
        }
    }
    
    /**
     * Método que verifica que los hijos de un nodo sean menor de manera recursiva
     * para así cumplir la condición de estructura del heap. En caso de no cumplirla,
     * se intercambia el hijo y el padre. Se utiliza en la inserción de elementos
     * en el heap.
     * @param nodo Nodo del cual se analizan sus hijos
     */
    public void heapify ( NodoHeap nodo ) {
        // Caso base
        if ( nodo.padre == null )
            return;
        // Si el valor del nodo actual es mayor que el de su padre, significa
        // que deben de intercambiarse.
        if ( nodo.valor > nodo.padre.valor ) {
            int hijo = nodo == nodo.padre.izq ? 1 : 2;
            int alturaPadre = nodo.padre.nivel;
            NodoHeap aux = nodo.padre;
            NodoHeap auxIzq = nodo.izq;
            NodoHeap auxDer = nodo.der;
            // Si el abuelo del nodo no es nulo, significa que debe actualizarse
            // la relación padre-hijo del abuelo con el nodo. Sino, solo con la raíz.
            if ( nodo.padre.padre != null ) {
                if ( nodo.padre == nodo.padre.padre.izq ) {
                    nodo.padre.padre.izq = nodo;
                }
                else {
                    nodo.padre.padre.der = nodo;
                }
            }
            else{
                NodoHeap auxRoot = root;
                root = nodo;
                if ( nodo == auxRoot.izq ) {
                    root.izq = auxRoot;
                }
                else {
                    root.der = auxRoot;
                }
            }
            // Establece valores al nivel y padre de los nodos a intercambiar.
            nodo.setNivel( alturaPadre );
            aux.setNivel( alturaPadre + 1 );
            nodo.setPadre( aux.padre );
            aux.setPadre( nodo );
            // Se actulizan los hijos del nodo que paso a ser padre del otro.
            if ( hijo == 1 ) {
                nodo.izq = aux;
                nodo.der = aux.der;
            }
            else {
                nodo.der = aux;
                nodo.izq = aux.izq;
            }
            // Actualizar los hijos del nodo que paso a ser hijo del otro.
            aux.izq = auxIzq;
            aux.der = auxDer;
            // Se establece de nuevo la relación padre e hijo de los nodos
            // intercambiados.
            aux.setPadre( nodo );
            // Se actualiza las relaciones padre-hijo a partir del nodo que pasó
            // a ser padre
            updateRelations( nodo );
            // Recursividad para verificar que el heap cumpla con su condición
            // de estructura.
            heapify( nodo );
        }
    }
    
    /**
     * Método que actualiza las relaciones de padre-hijo con los hijos de un
     * nodo de manera recursiva.
     * @param nodo Nodo del cual se quiere actualizar relación son sus hijos.
     */
    public void updateRelations ( NodoHeap nodo ) {
        // Caso base
        if ( nodo == null )
            return;
        // Establece el padre del hijo izquierdo si no es nulo
        if ( nodo.izq != null ) {
            nodo.izq.setPadre( nodo );
        }
        // Establece el padre del hijo derecho si no es nulo
        if ( nodo.der != null ) {
            nodo.der.setPadre( nodo );
        }
        // Recursividad para los subárboles
        updateRelations( nodo.izq );
        updateRelations( nodo.der );
    }
    
    /**
     * Método que elimina la raíz del árbol, y en caso de que esté vacío, lo
     * notifica.
     */
    public boolean deleteRoot(){
        // Si el árbol no está vacío, puede eliminarse la raíz
        if ( !isEmpty() ) {
            // Se obtiene la nueva raíz del último nodo del heap.
            NodoHeap newRoot = getLastNode( root );
            // Se verifica si dicho nodo es la raíz o no
            if ( newRoot != root ) {
                // Se actualiza la relación padre-hijo con el padre de la nueva
                // raíz y la raíz
                //System.out.println(newRoot==null);
                if ( newRoot.padre.izq == newRoot ) {
                    newRoot.padre.setIzq( null );
                }
                else {
                    newRoot.padre.setDer( null );
                }
                // Se establecen los nuevos atributos de la nueva raíz.
                newRoot.setPadre( null );
                newRoot.setNivel(0);
                newRoot.setIzq( root.izq );
                newRoot.setDer( root.der );
                // Se actualiza la raíz
                root = newRoot;
                // Se verifica la estructura del heap a partir de la raíz.
                heapifyReverse( root );
            }
            else {
                // Si la raíz es el único nodo, simplemente se iguala a null
                root = null;
            }
            return true;
        }
        return false;
    }
    
    /**
     * Método que verifica que los hijos de un nodo sean menor de manera recursiva
     * para así cumplir la condición de estructura del heap partiendo de la raíz.
     * En caso de no cumplirla, se intercambia el hijo y el padre. Se usa en la
     * eliminación de la raíz del heap.
     * @param nodo Nodo del cual se analizan sus hijos.
     */
    public void heapifyReverse ( NodoHeap nodo ) {
        // Caso base, se ha llegado a una hoja.
        if ( getMaxChild( nodo ) == null )
            return;
        // Se busca el hijo mayor del nodo.
        NodoHeap hijoMax = getMaxChild( nodo );
        // Si el hijo mayor es mayor que el nodo, hay intercambio.
        if ( hijoMax != null && hijoMax.valor > nodo.valor ) {
            // Se calcula que hijo es el mayor.
            int numHijo = hijoMax == nodo.izq ? 1 : 2;
            // Variables auxiliares.
            int alturaPadre = nodo.nivel;
            NodoHeap auxIzq = nodo.izq;
            NodoHeap auxDer = nodo.der;
            // Se establece el padre del hijo mayor a su abuelo
            hijoMax.setPadre( nodo.padre );
            // Si tiene abuelo, se actualiza la relación padre-hijo del hijo mayor
            // con su abuelo. De lo contrario, el hijo mayor será la nueva raíz.
            if ( nodo.padre != null ) {
                int hijo = nodo == nodo.padre.izq ? 1 : 2;
                if ( hijo == 1 ) {
                    nodo.padre.setIzq( hijoMax );
                }
                else {
                    nodo.padre.setDer( hijoMax );
                }
            }
            else {
                root = hijoMax;
            }
            // Establece el nivel del hijo mayor que pasó a ser padre
            hijoMax.setNivel( alturaPadre );
            // Se modifican los atributos del nodo que pasó a ser hijo.
            nodo.setNivel( alturaPadre + 1 );
            nodo.setPadre( hijoMax );
            nodo.setIzq(hijoMax.izq);
            nodo.setDer(hijoMax.der);
            // Se actualizan los hijos del nodo que pasó a ser padre.
            if ( numHijo == 1 ) {                
                hijoMax.setDer( auxDer );        
                hijoMax.setIzq( nodo );
            }
            else {
                hijoMax.setIzq( auxIzq );   
                hijoMax.setDer( nodo );
            }
            // Se actualizan la relación padre-hijo a partir del nodo que pasó
            // a ser padre.
            updateRelations( hijoMax );
            // Verifica la estructura del heap a partir del nodo que pasó
            // a ser hijo.
            heapifyReverse( nodo );
        }
        else
            updateRelations( nodo );
    }
    
    /**
     * Método que regresa el hijo más grande de un nodo.
     * @param nodo Nodo del cual se analizan sus hijos.
     * @return El nodo hijo más grande del nodo, o null si los dos son nulos.
     */
    public NodoHeap getMaxChild ( NodoHeap nodo ) {
        // Si el hijo izquierdo es nulo, regresa el hijo derecho.
        if ( nodo.izq == null )
            return nodo.der;
        // Si el hijo derecho es nulo, regresa el hijo izquierdo.
        if ( nodo.der == null )
            return nodo.izq;
        // Regresa el mayor de los hermanos.
        return nodo.izq.valor > nodo.der.valor ? nodo.izq : nodo.der;
    }
    
    /**
     * Método que obtiene el último nodo del heap (leyendo de arriba a abajo y
     * de izquierda a derecha) de manera recursiva.
     * @param node Nodo del cual se analiza sus hijos.
     * @return El último nodo del heap.
     */
    public NodoHeap getLastNode ( NodoHeap node ) {
        // Caso base, si no tiene hijos o es nulo.
        if ( node == null || (node.izq == null && node.der == null) )
            return node;
        // Recursividad para revisar los subárboles.
        NodoHeap izq = getLastNode(node.izq);
        NodoHeap der = getLastNode(node.der);
        // Se obtiene la altura, en caso de que sean nulos será uno.
        int altIzq = izq != null ? izq.nivel : 1;
        int altDer = der != null ? der.nivel : 1;
        // Si la altura izquierda es mayor a la derecha, significa que tiene el
        // izquierdo mayor dificultad. De lo contrario, regresa el derecho.
        if ( altIzq > altDer ) {
            return izq;
        }
        else {
            return der != null ? der : izq;
        }
    }
    
    /**
     * Método que verifica si está vacío o no el heap.
     * @return True si el heap está vacío, en caso contrario false.
     */
    public boolean isEmpty () {
        return root == null;
    }
    
    /**
     * Método que verifica la existencia de un nodo que tenga por valor el
     * entero recibido.
     * @param value Valor buscado en el heap.
     * @return True si existe una coincidencia. En caso contrario, false.
     */
    public boolean search( int value ){
        return getNode( value ) != null;
    }	

    /**
     * Método que obtiene el nodo que tenga por valor el entero recibido siguiendo
     * la lógica del recorrido BFS.
     * @param value Valor buscado en el heap.
     * @return Nodo que tenga por valor el parámetro.
     */
    public NodoHeap getNode( int value ){
        NodoHeap r = root;
        Queue<NodoHeap> queue = new LinkedList<>();
        // Si no está vacío, regresa null
        if ( !isEmpty() ){
            // Agrega la raíz a la cola.
            queue.add( r );
            // Mientras la cola no está vacía.
            while ( !queue.isEmpty() ){
                // Se extrae un elemento de la cola.
                r = ( NodoHeap ) queue.poll();
                // Si el valor es igual, regresa el nodo
                if ( r.valor == value ) {
                    return r;
                }
                // Solo si el valor del nodo es mayor que el valor buscado,
                // agrega a sus hijos si es que existen.
                if ( r.valor > value ) {
                    if( r.izq != null )
                        queue.add( r.izq );
                    if( r.der != null )
                        queue.add(r.der);
                }
            }
        }
        return null;
    }	
    
    /**
     * Método que rezalia el recorrido BFS sobre el heap.
     */
    public void breadthFirst(){
        // Si el heap está vacío, notifica al usuario y termina la ejecución del método.
        if ( isEmpty() ) {
            System.out.println("No hay elementos en el heap.");
            return;
        }
        // Realiza el recorrido BFS
        NodoHeap r = root;
        Queue<NodoHeap> queue = new LinkedList<>();
        queue.add( r );
        // Mientras la cola no esté vacía.
        while ( !queue.isEmpty() ){
            // Se extrae un nodo de la cola
            r = ( NodoHeap ) queue.poll();
            // Imprime la información del nodo.
            System.out.println( r.toString() ) ;
            // Si tiene hijos los agrega a la cola.
            if( r.izq != null )
                queue.add( r.izq );
            if( r.der != null )
                queue.add(r.der);
        }
    }

}