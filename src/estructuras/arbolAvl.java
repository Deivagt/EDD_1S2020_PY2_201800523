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
public class arbolAvl {

    public nodoArbolAvl raiz;
    public nodoArbolAvl temp;
    boolean control = false;
    String estructura;
    int contador = 0;

    int peso(nodoArbolAvl nodo) {
	if (nodo == null) {
	    return 0;
	}

	return nodo.peso;
    }

    int maximo(int a, int b) {
	if (a > b) {
	    return a;
	} else {
	    return b;
	}
    }

    nodoArbolAvl girarDerecha(nodoArbolAvl nodo) {

	nodoArbolAvl temp = nodo.izquierda;
	nodoArbolAvl temp1 = temp.derecha;

	temp.derecha = nodo;
	nodo.izquierda = temp1;

	nodo.peso = maximo(peso(nodo.izquierda), peso(nodo.derecha) + 1);
	temp.peso = maximo(peso(temp.izquierda), peso(temp.derecha) + 1);

	return temp;

    }

    nodoArbolAvl girarIzquierda(nodoArbolAvl nodo) {

	nodoArbolAvl temp = nodo.derecha;
	nodoArbolAvl temp1 = temp.izquierda;

	temp.izquierda = nodo;
	nodo.derecha = temp1;

	nodo.peso = maximo(peso(nodo.izquierda), peso(nodo.derecha) + 1);
	temp.peso = maximo(peso(temp.izquierda), peso(temp.derecha) + 1);

	return temp;
    }

    int obtenerBalance(nodoArbolAvl nodo) {
	if (nodo == null) {
	    return 0;
	}

	return peso(nodo.izquierda) - peso(nodo.derecha);
    }

    public nodoArbolAvl insertar(nodoArbolAvl nodo, String categoria, int carnetAutor) {
	categoria = categoria.toLowerCase();

	if (nodo == null) {
	    nodo = new nodoArbolAvl(categoria, carnetAutor);
	    nodo.id = contador;
	    contador++;
	    return (nodo);
	}

	if (categoria.compareTo(nodo.categoria) < 0) {
	    nodo.izquierda = insertar(nodo.izquierda, categoria, carnetAutor);
	} else if (categoria.compareTo(nodo.categoria) > 0) {
	    nodo.derecha = insertar(nodo.derecha, categoria, carnetAutor);
	} else if (categoria.compareTo(nodo.categoria) == 0) {
	    nodo.id = contador;
	    contador++;
	    return nodo;
	}

	nodo.peso = 1 + maximo(peso(nodo.izquierda), peso(nodo.derecha));

	int balance = obtenerBalance(nodo);

	if (balance > 1 && categoria.compareTo(nodo.izquierda.categoria) < 0) {
	    return girarDerecha(nodo);
	}

	if (balance < -1 && categoria.compareTo(nodo.derecha.categoria) > 0) {
	    return girarIzquierda(nodo);
	}

	if (balance > 1 && categoria.compareTo(nodo.izquierda.categoria) > 0) {
	    nodo.izquierda = girarIzquierda(nodo.izquierda);
	    return girarDerecha(nodo);
	}

	if (balance < -1 && categoria.compareTo(nodo.derecha.categoria) < 0) {
	    nodo.derecha = girarDerecha(nodo.derecha);
	    return girarIzquierda(nodo);
	}
	nodo.id = contador;
	contador++;
	return nodo;
    }

    public void preOrden(nodoArbolAvl nodo) {
	if (nodo != null) {
	    System.out.print(nodo.categoria + " ");
	    preOrden(nodo.izquierda);
	    preOrden(nodo.derecha);
	}
    }

    public nodoArbolAvl buscar(String categoria) {
	temp = null;
	bsq(raiz, categoria);
	return temp;
    }

    public void bsq(nodoArbolAvl nodo, String categoria) {
	if (nodo != null) {
	    if (nodo.categoria == categoria) {
		temp = nodo;
	    }
	    bsq(nodo.izquierda, categoria);
	    bsq(nodo.derecha, categoria);

	}

    }

    public boolean verificarBorrar(nodoArbolAvl nodo, String categoria, int carnet) {
	control = false;
	vefBor(nodo, categoria, carnet);

	return control;
    }

    public void vefBor(nodoArbolAvl nodo, String categoria, int carnet) {
	categoria = categoria.toLowerCase();
	if (nodo != null) {
	    if (categoria.equals(nodo.categoria) && carnet == nodo.carnetAutor) {
		control = true;
	    }
	    vefBor(nodo.izquierda, categoria, carnet);
	    vefBor(nodo.derecha, categoria, carnet);
	}
    }

    nodoArbolAvl minimo(nodoArbolAvl nodo) {
	nodoArbolAvl actual = nodo;

	/* loop down to find the leftmost leaf */
	while (actual.izquierda != null) {
	    actual = actual.izquierda;
	}

	return actual;
    }

    public nodoArbolAvl borrar(nodoArbolAvl raiz, String categoria) {

	categoria = categoria.toLowerCase();
	if (raiz == null) {
	    return raiz;
	}

	if (categoria.compareTo(raiz.categoria) < 0) {
	    raiz.izquierda = borrar(raiz.izquierda, categoria);
	} else if (categoria.compareTo(raiz.categoria) > 0) {
	    raiz.derecha = borrar(raiz.derecha, categoria);
	} else {
	    if ((raiz.izquierda == null) || (raiz.derecha == null)) {
		nodoArbolAvl temp = null;

		if (temp == raiz.izquierda) {
		    temp = raiz.derecha;
		} else {
		    temp = raiz.izquierda;
		}

		if (temp == null) {
		    temp = raiz;
		    raiz = null;
		} else {
		    raiz = temp;
		}
	    } else {
		nodoArbolAvl temp = minimo(raiz.derecha);

		raiz.categoria = temp.categoria;
		raiz.librosCategoria = temp.librosCategoria;
		raiz.carnetAutor = temp.carnetAutor;

		raiz.derecha = borrar(raiz.derecha, temp.categoria);
	    }
	}

	if (raiz == null) {
	    return raiz;
	}

	raiz.peso = maximo(peso(raiz.izquierda), peso(raiz.derecha)) + 1;

	int balance = obtenerBalance(raiz);

	if (balance > 1 && obtenerBalance(raiz.izquierda) >= 0) {
	    return girarDerecha(raiz);
	}

	if (balance > 1 && obtenerBalance(raiz.izquierda) < 0) {
	    raiz.izquierda = girarIzquierda(raiz.izquierda);
	    return girarDerecha(raiz);
	}

	if (balance < -1 && obtenerBalance(raiz.derecha) <= 0) {
	    return girarIzquierda(raiz);
	}

	if (balance < -1 && obtenerBalance(raiz.derecha) > 0) {
	    raiz.derecha = girarDerecha(raiz.derecha);
	    return girarIzquierda(raiz);
	}

	return raiz;
    }

    void escribir(String contenido) {
	try {
	    String ruta = ".\\salida.txt";

	    File file = new File(ruta);
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
    }

    public void graficar() {

	String encabezado = " digraph G {";

	String fin = "}";
	encabezado = encabezado + recGraf(this.raiz) + fin;

	System.out.println(encabezado);
	escribir(encabezado);

	try {
	    
	    String dotPath = "dot";

	    String entrada = getClass().getProtectionDomain().getCodeSource().getLocation() +"\\salida.txt";
	    String salida = "c:\\grafo1.jpg";

	    String parametro = "-Tjpg";
	    String op = "-o";

	    String[] cmd = new String[5];
	    cmd[0] = dotPath;
	    cmd[1] = parametro;
	    cmd[2] = entrada;
	    cmd[3] = op;
	    cmd[4] = salida;

	    for(int i = 0; i < 5; i ++){
		System.out.println(cmd[i]);
	    }
	    Runtime rt = Runtime.getRuntime();

	    rt.exec(cmd);

	} catch (Exception ex) {
	    ex.printStackTrace();
	} finally {
	}
	
    }

    public String recGraf(nodoArbolAvl n) {
	String nodos;
	if (n.izquierda == null && n.derecha == null) {
	    nodos = "node" + Integer.toString(n.id) + "[label = \"" + n.categoria + "\"];";
	} else {
	    nodos = "node" + Integer.toString(n.id) + "[label = \"" + n.categoria + "\"];";
	}
	if (n.izquierda != null) {
	    nodos = nodos + recGraf(n.izquierda) + "node" + Integer.toString(n.id) + "->node"
		    + Integer.toString(n.izquierda.id) + "\n";
	}
	if (n.derecha != null) {
	    nodos = nodos + recGraf(n.derecha) + "node" + Integer.toString(n.id) + "->node"
		    + Integer.toString(n.derecha.id) + "\n";
	}

	nodos = nodos + "node" + Integer.toString(raiz.id) + " [ label =\"" + raiz.categoria + "\"];\n";
	return nodos;
    }

    /*void arbolBinario::graficarPre() {
	ct = 0;
	string encabezado = " digraph G { rankdir= LR;";

	string fin = "}";
	encabezado = encabezado + recPre(raiz);
	string conecciones = "";
	for (int i = 0; i < ct - 1; i++) {
	    encabezado = encabezado + "node" + to_string(i) + "->node"
		    + to_string(i + 1) + "\n";
	}

	encabezado = encabezado + fin;
	ofstream file;
	file.open("pre.txt", ios::out);
	file << encabezado;
	file.close();

	string s1 = "dot -Tpng pre.txt -o D:/grafos/pre.png";

	system(s1.c_str());
	system("start D:/grafos/pre.png");
    }

    void arbolBinario::graficarIn() {
	ct = 0;
	string encabezado = " digraph G { rankdir= LR;";

	string fin = "}";
	encabezado = encabezado + recIn(raiz);
	string conecciones = "";
	for (int i = 0; i < ct - 1; i++) {
	    encabezado = encabezado + "node" + to_string(i) + "->node"
		    + to_string(i + 1) + ";\n";
	}
	encabezado = encabezado + fin;
	ofstream file;
	file.open("in.txt", ios::out);
	file << encabezado;
	file.close();

	string s1 = "dot -Tpng in.txt -o D:/grafos/in.png";

	system(s1.c_str());
	system("start D:/grafos/in.png");
    }

    void arbolBinario::graficarPos() {
	ct = 0;
	string encabezado = " digraph G { rankdir= LR;";

	string fin = "}";
	encabezado = encabezado + recPos(raiz);
	string conecciones = "";
	for (int i = 0; i < ct - 1; i++) {
	    encabezado = encabezado + "node" + to_string(i) + "->node"
		    + to_string(i + 1) + ";\n";
	}
	encabezado = encabezado + fin;
	ofstream file;
	file.open("pos.txt", ios::out);
	file << encabezado;
	file.close();

	string s1 = "dot -Tpng pos.txt -o D:/grafos/pos.png";

	system(s1.c_str());
	system("start D:/grafos/pos.png");
    }

    string arbolBinario

    ::recPre(nodoArbol 
	 
	 
	* n) {
	if (n == NULL) {
	    return "";
	}

	string nodos;
	nodos = "node" + to_string(ct) + "[label = \"" + n -> nombre + "\"];";
	ct++;

	nodos = nodos + recPre(n -> izq);
	nodos = nodos + recPre(n -> der);

	return nodos;

    }
    string arbolBinario

    ::recIn(nodoArbol 
	 
	 
	* n) {
	if (n == NULL) {
	    return "";
	}

	string nodos;

	nodos = nodos + recIn(n -> izq);
	nodos = nodos + "node" + to_string(ct) + "[label = \"" + n -> nombre + "\"];";
	ct++;
	nodos = nodos + recIn(n -> der);

	return nodos;
    }
    string arbolBinario

    ::recPos(nodoArbol 
	 
	 
	* n) {
	if (n == NULL) {
	    return "";
	}

	string nodos;

	nodos = nodos + recPos(n -> izq);
	nodos = nodos + recPos(n -> der);
	nodos = nodos + "node" + to_string(ct) + "[label = \"" + n -> nombre + "\"];";
	ct++;
	return nodos;
    }
     */
}
