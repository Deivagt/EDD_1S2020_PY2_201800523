/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

/**
 *
 * @author David
 */
public class nodoLS {
    nodoLS siguiente;
    usuario contenido;

    public nodoLS getSiguiente() {
	return siguiente;
    }

    public void setSiguiente(nodoLS siguiente) {
	this.siguiente = siguiente;
    }

    public usuario getContenido() {
	return contenido;
    }

    public void setContenido(usuario contenido) {
	this.contenido = contenido;
    }
    
    
}
