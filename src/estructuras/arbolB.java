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
public class arbolB {

    public nodoArbolB raiz;
    public int grado;

    // Constructor (Initializes tree as empty) 
    public arbolB(int grado) {
	this.raiz = null;
	this.grado = grado;
    }

    // function to traverse the tree 
    public void atravesar() {
	if (this.raiz != null) {
	    this.raiz.atravesar();
	}
	//System.out.println();
    }

    // function to search a key in this tree 
    public nodoArbolB buscar(int id) {
	if (this.raiz == null) {
	    return null;
	} else {
	    return this.raiz.buscar(id);
	}
    }

     public void insertar(libro nuevoLibro) {
	if (raiz == null) {
	    raiz = new nodoArbolB(grado, true);
	    raiz.libros[0] = nuevoLibro;
	    raiz.cantidad = 1;
	} else {
	    if (raiz.cantidad == 2 * grado - 1) {
		nodoArbolB temp = new nodoArbolB(grado, false);
		temp.hijos[0] = raiz;
		temp.partir(0, raiz);
		
		int i = 0;
		if(temp.libros[0].isbn < nuevoLibro.isbn){
		    i++;
		}
		raiz = temp;
		temp.hijos[i].insertarMedioLleno(nuevoLibro);
	    }else{
		raiz.insertarMedioLleno(nuevoLibro);
	    }

	}
    }

     public void borrar(int isbn){
	if(raiz == null){
	    System.out.println("la raiz esta vacia");
	}
	
	raiz.borrar(isbn);
	if(raiz.cantidad == 0){
	    nodoArbolB temp = raiz;
	    if(raiz.esHoja == true){
		raiz = null;
	    }
	    else{
		raiz = raiz.hijos[0];
	    }
	}
    }
}
