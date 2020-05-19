/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author David
 */
public class arbolB {

    public nodoArbolB raiz;
    public int minimo;
    String encabezado;
    public int cantidad;

    // Constructor (Initializes tree as empty) 
    public arbolB(int minimo) {

	this.raiz = null;
	this.minimo = minimo;
	this.cantidad = 0;
    }

    // function to traverse the tree 
    public void atravesar() {
	if (this.raiz != null) {
	    this.raiz.atravesar();
	}

    }

    public void imprimir() {
	if (this.raiz != null) {
	    String a = "digraph G { \nnode [shape = record,height=.1];\n";
	    String b = "";
	    b += this.raiz.imprimir(b, 0);
	    System.out.println(a + b);
	}

    }
    libro[] libros;
    int amount = 0;

    public libro[] listaLibros() {
	libros = null;
	if (raiz != null) {
	    System.out.println(cantidad);
	    libros = new libro[cantidad];
	    amount = 0;
	    recLlenar(this.raiz);

	}

	return libros;
    }

    void recLlenar(nodoArbolB nodo) {
	if (nodo != null) {
	    for (int j = 0; j < nodo.cantidad; j++) {

		libros[amount] = nodo.libros[j];
		amount++;

	    }

	    for (int j = 0; j < nodo.cantidad + 1; j++) {

		if (nodo.hijos[j] != null) {
		    recLlenar(nodo.hijos[j]);
		}

	    }

	}
    }

    public void impresion() {

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
	    raiz = new nodoArbolB(minimo, true);
	    raiz.libros[0] = nuevoLibro;
	    raiz.cantidad = 1;
	} else {
	    if (raiz.cantidad == 2 * minimo - 1) {
		nodoArbolB temp = new nodoArbolB(minimo, false);
		temp.hijos[0] = raiz;
		temp.partir(0, raiz);

		int i = 0;
		if (temp.libros[0].isbn < nuevoLibro.isbn) {
		    i++;
		}

		temp.hijos[i].insertarMedioLleno(nuevoLibro);

		raiz = temp;
	    } else {
		raiz.insertarMedioLleno(nuevoLibro);
	    }

	}
    }
    public boolean estaVacio(){
	if (raiz == null) {
	 
	    return true;
	}else{
	      return false;
	}
    }

    public void borrar(int isbn) {
	if (raiz == null) {
	    System.out.println("El arbol esta vacio");
	    return;
	}
	cantidad--;
	raiz.borrar(isbn);
	if (raiz.cantidad == 0) {
	    nodoArbolB temp = raiz;
	    if (raiz.esHoja == true) {
		raiz = null;
	    } else {
		raiz = raiz.hijos[0];
	    }
	}
    }

    File escribir(String contenido) {
	String ruta = "salida.txt";

	File file = new File(ruta);

	try {

	    // Si el archivo no existe es creado
	    if (!file.exists()) {

		file.createNewFile();
	    } else {
		if (file.delete()) {
		    file.createNewFile();
		    System.out.println("El fichero ha sido borrado satisfactoriamente");
		} else {
		    System.out.println("El fichero no puede ser borrado");
		}
	    }

	    FileWriter fw = new FileWriter(file);
	    BufferedWriter bw = new BufferedWriter(fw);
	    bw.write(contenido);
	    bw.close();

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return file;
    }

    public String graficar() {

	String encabezado = "digraph G { \nnode [shape = record,height=.1];\n";
	String salida = "";
	String fin = "}";
	idTemp = 0;
	asignarID(raiz);
	encabezado = encabezado + recGraf(this.raiz) + fin;

	System.out.println(encabezado);
	File nuevo = escribir(encabezado);

	try {

	    String dotPath = "dot";

	    String entrada = nuevo.getAbsolutePath();
	    salida = System.getProperty("user.home") + "\\desktop\\salida" + "\\grafoB.jpg";

	    String parametro = "-Tjpg";
	    String op = "-o";

	    String cmd = dotPath + " " + parametro + " " + entrada + " " + op + " " + salida;
	    /* String[] cmd = new String[5];
	    cmd[0] = dotPath;
	    cmd[1] = parametro;
	    cmd[2] = entrada;
	    cmd[3] = op;
	    cmd[4] = salida;*	    System.out.println(cmd);*/

	    Runtime.getRuntime().exec(cmd);
	} catch (Exception ex) {
	    ex.printStackTrace();
	} finally {
	}

	return salida;
    }

    int idTemp;

    void asignarID(nodoArbolB n) {
	n.id = idTemp;
	idTemp++;
	for (int j = 0; j < n.cantidad + 1; j++) {

	    if (n.hijos[j] != null) {
		asignarID(n.hijos[j]);
	    }

	}
    }

    public String recGraf(nodoArbolB n) {
	String nodos = "";

	nodos += "nodo" + n.id + "[label = \"<f0> ";
	for (int i = 0; i < n.cantidad; i++) {

	    nodos += "|" +"Titulo: "+n.libros[i].titulo +" \\n ISBN: "+ Integer.toString(n.libros[i].isbn) + "| <f" + (i + 1) + ">";

	}
	nodos += "\"];\n";

	for (int j = 0; j < n.cantidad + 1; j++) {

	    if (n.hijos[j] != null) {
		nodos = nodos + recGraf(n.hijos[j]) + "\"nodo" + n.id + "\":f" + j + " -> \"nodo" + n.hijos[j].id + "\"\n";
	    }

	}

	return nodos;
    }

}
