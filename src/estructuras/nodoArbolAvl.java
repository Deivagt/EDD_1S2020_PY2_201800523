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
public class nodoArbolAvl {
    
   public String categoria;
   public arbolB librosCategoria;
   public int carnetAutor;
    int peso;
    nodoArbolAvl izquierda;
    nodoArbolAvl derecha;
    
    public nodoArbolAvl(String categoria, int carnetAutor){
	this.categoria = categoria;
	this.carnetAutor = carnetAutor;
	this.librosCategoria = new arbolB(3);
    }
    
}
