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
public class listaSimpleHash {

    nodoLS primero;
    nodoLS ultimo;

    public listaSimpleHash() {
	this.primero = new nodoLS();
	this.ultimo = new nodoLS();

	this.primero.setSiguiente(ultimo);
	this.primero.setContenido(new usuario());
	this.ultimo.setContenido(new usuario());
    }

    public boolean estaVacio() {
	if (primero.getSiguiente() == ultimo) {
	    return true;
	} else {
	    return false;
	}
    }

    public void insertar(usuario contenido) {

	nodoLS temp = primero;

	while (temp.getSiguiente() != ultimo) {
	    temp = temp.getSiguiente();
	}

	nodoLS nuevoUsuario = new nodoLS();
	nuevoUsuario.setContenido(contenido);

	nuevoUsuario.setSiguiente(ultimo);
	temp.setSiguiente(nuevoUsuario);

    }

    public boolean eliminar(int carnet) {
	nodoLS temp = primero;

	while (temp != ultimo) {

	    if (temp.getSiguiente().getContenido().getCarnet() == carnet) {
		break;
	    }

	    temp = temp.getSiguiente();
	}
	if (temp != ultimo) {
	    nodoLS temp1 = temp.getSiguiente().getSiguiente();

	    temp.getSiguiente().setSiguiente(null);
	    temp.setSiguiente(temp1);
	    return true;
	}
	return false;

    }

    public nodoLS buscar(int carnet) {
	nodoLS temp = primero;

	while (temp != ultimo) {
	    if (temp.getContenido().getCarnet() == carnet) {
		return temp;
	    }

	    temp = temp.getSiguiente();
	}
	return null;
    }

    public void imprimir() {
	nodoLS temp = primero.getSiguiente();

	while (temp != ultimo) {
	    System.out.println(temp.getContenido().getCarnet() + temp.getContenido().getNombre() + temp.getContenido().getApellido() + temp.getContenido().getCarrera() + temp.getContenido().getPass());
	    temp = temp.getSiguiente();
	}
    }

    public String graficar(int i) {
	nodoLS temp = primero.getSiguiente();
	String nodos = "";
	int contador = 0;

	while (temp != ultimo) {
	    nodos = nodos + "Casilla" + i + "nodo" + contador + "[label = \"" + temp.getContenido().getNombre() + " " + temp.getContenido().getCarnet() + " \\n " + temp.getContenido().getPass() + "\"];\n";
	    if (temp.siguiente != ultimo) {
		nodos = nodos + "Casilla" + i + "nodo" + contador + "->Casilla" + i + "nodo" + (contador + 1) + "\n";
	    }
	    contador++;
	    temp = temp.getSiguiente();
	}
	return nodos;
    }

    public String rankeo(int i) {
	nodoLS temp = primero.getSiguiente();
	String rankeo = "";
	int contador = 0;

	while (temp != ultimo) {
	    rankeo = rankeo + "Casilla" + i + "nodo" + contador + ";\n";
	    contador++;
	    temp = temp.getSiguiente();

	}
	return rankeo;
    }

}
