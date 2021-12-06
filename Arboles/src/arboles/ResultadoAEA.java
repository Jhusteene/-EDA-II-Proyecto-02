/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

/**
 * Esta clase contiene el resultado de un aexpresión aritmética y su posible
 * error que pudiera producir.
 * @author Equipo 10
 * @version 1.0
 */
public class ResultadoAEA {
    
    /**
     * Mensaje de error en caso de haber uno.
     */
    String mensajeError;
    
    /**
     * Resultado de la expresión airtmética.
     */
    float resultado;
    
    /**
     * Constructor que recibe un mensaje de error que produjo la resolución de una expresión.
     * @param m Mensaje de error.
     */
    public ResultadoAEA ( String m ) {
        mensajeError = m;
    }
    
    /**
     * Constructor que recibe un número real que es el resultado de la expresión
     * aritmética. 
     * @param r Resultado de la expresión aritmética.
     */
    public ResultadoAEA ( float r ) {
        mensajeError = "";
        resultado = r;
    }
    
    /**
     * Método que obtiene el resultado de la expresión aritmética.
     * @return Resultado de la expresión aritmética.
     */
    public float getResultado () {
        return resultado;
    }
    
    /**
     * Método que obtiene el mensaje de error.
     * @return Mensaje de error.
     */
    public String getMensajerError () {
        return mensajeError;
    }
    
    /**
     * Verifica si hubo un error o no.
     * @return True si hubo un error, false en caso contrario.
     */
    public boolean hayError () {
        return !mensajeError.equals("");
    }
    
}
