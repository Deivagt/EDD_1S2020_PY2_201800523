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
    public nodoArbolAvl temporal;
    boolean control = false;
    String estructura;
    int contador = 0;
    int ct;

    int peso(nodoArbolAvl nodo) {
	if (nodo == null) {
	    return 0;
	}

	return nodo.peso;
    }

    int maximo(int a, int b) {
	return (a > b) ? a : b;
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
	/*categoria = Normalizer.normalize(categoria, Normalizer.Form.NFD);
	categoria = categoria.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");*/

	if (nodo == null) {
	    nodo = new nodoArbolAvl(categoria, carnetAutor);
	    nodo.id = contador;
	    contador++;
	    return (nodo);
	}

	if (categoria.compareToIgnoreCase(nodo.categoria) < 0) {
	    nodo.izquierda = insertar(nodo.izquierda, categoria, carnetAutor);
	} else if (categoria.compareToIgnoreCase(nodo.categoria) > 0) {
	    nodo.derecha = insertar(nodo.derecha, categoria, carnetAutor);
	} else if (categoria.compareTo(nodo.categoria) == 0) {

	    return nodo;
	}

	nodo.peso = 1 + maximo(peso(nodo.izquierda), peso(nodo.derecha));

	int balance = obtenerBalance(nodo);

	if (balance > 1 && categoria.compareToIgnoreCase(nodo.izquierda.categoria) < 0) {
	    return girarDerecha(nodo);
	}

	if (balance < -1 && categoria.compareToIgnoreCase(nodo.derecha.categoria) > 0) {
	    return girarIzquierda(nodo);
	}

	if (balance > 1 && categoria.compareToIgnoreCase(nodo.izquierda.categoria) > 0) {
	    nodo.izquierda = girarIzquierda(nodo.izquierda);
	    return girarDerecha(nodo);
	}

	if (balance < -1 && categoria.compareToIgnoreCase(nodo.derecha.categoria) < 0) {
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
	categoria = categoria.toLowerCase();
	temporal = null;
	bsq(raiz, categoria);
	return temporal;
    }

    public void bsq(nodoArbolAvl nodo, String categoria) {
	if (nodo != null) {

	    if (nodo.categoria.equals(categoria)) {
		temporal = nodo;
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

    public String graficar(int i) {

	String encabezado = "digraph G {";
	String salida = "";
	String fin = "}";
	encabezado = encabezado + recGraf(this.raiz) + fin;

	System.out.println(encabezado);
	File nuevo = escribir(encabezado);

	try {

	    String dotPath = "dot";

	    String entrada = nuevo.getAbsolutePath();
	    salida = System.getProperty("user.home") + "\\desktop\\salida" + "\\grafo" + i + ".jpg";

	    String parametro = "-Tjpg";
	    String op = "-o";

	    String cmd = dotPath + " " + parametro + " " + entrada + " " + op + " " + salida;
	    /* String[] cmd = new String[5];
	    cmd[0] = dotPath;
	    cmd[1] = parametro;
	    cmd[2] = entrada;
	    cmd[3] = op;
	    cmd[4] = salida;*/
	    System.out.println(cmd);

	    Runtime.getRuntime().exec(cmd);

	} catch (Exception ex) {
	    ex.printStackTrace();
	} finally {
	}

	return salida;
    }

    public String recGraf(nodoArbolAvl n) {
	String nodos;
	//Primero declaro el nodo donde estoy
	if (n.izquierda == null && n.derecha == null) {
	    nodos = "node" + Integer.toString(n.id) + "[label = \"" + n.categoria + " " + n.cantidad + "\"];";
	} else {
	    nodos = "node" + Integer.toString(n.id) + "[label = \"" + n.categoria + " " + n.cantidad + "\"];";
	}
	
	//Declaro nuevos nodos y flechas
	if (n.izquierda != null) {
	    nodos = nodos + recGraf(n.izquierda) + "node" + Integer.toString(n.id) + "->node"
		    + Integer.toString(n.izquierda.id) + "\n";
	}
	if (n.derecha != null) {
	    nodos = nodos + recGraf(n.derecha) + "node" + Integer.toString(n.id) + "->node"
		    + Integer.toString(n.derecha.id) + "\n";
	}

	nodos = nodos + "node" + Integer.toString(raiz.id) + " [ label =\"" + raiz.categoria + " " + n.cantidad + "\"];\n";
	return nodos;
    }

    public String graficarPre() {
	ct = 0;
	String encabezado = " digraph G { rankdir= LR;";
	String salida = "";
	String fin = "}";
	encabezado = encabezado + recPre(raiz);
	String conexiones = "";
	for (int i = 0; i < ct - 1; i++) {
	    encabezado = encabezado + "node" + Integer.toString(i) + "->node"
		    + Integer.toString(i + 1) + "\n";
	}

	encabezado = encabezado + fin;
	File nuevo = escribir(encabezado);
	try {

	    String dotPath = "dot";

	    String entrada = nuevo.getAbsolutePath();
	    salida = System.getProperty("user.home") + "\\desktop\\salida" + "\\grafoPre.jpg";

	    String parametro = "-Tjpg";
	    String op = "-o";

	    String cmd = dotPath + " " + parametro + " " + entrada + " " + op + " " + salida;

	    System.out.println(cmd);

	    Runtime.getRuntime().exec(cmd);

	} catch (Exception ex) {
	    ex.printStackTrace();
	} finally {
	}
	return salida;

    }

    String recPre(nodoArbolAvl n) {
	if (n == null) {
	    return "";
	}

	String nodos;
	nodos = "node" + Integer.toString(ct) + "[label = \"" + n.categoria + " " + n.cantidad + "\"];";
	ct++;

	nodos = nodos + recPre(n.izquierda);
	nodos = nodos + recPre(n.derecha);

	return nodos;

    }

    public String graficarIn() {
	ct = 0;
	String encabezado = " digraph G { rankdir= LR;\n";
	String salida = "";
	String fin = "}";
	encabezado = encabezado + recIn(raiz);
	String conexiones = "";
	for (int i = 0; i < ct - 1; i++) {
	    encabezado = encabezado + "node" + Integer.toString(i) + "->node"
		    + Integer.toString(i + 1) + "\n";
	}

	encabezado = encabezado + fin;
	File nuevo = escribir(encabezado);
	try {

	    String dotPath = "dot";

	    String entrada = nuevo.getAbsolutePath();
	    salida = System.getProperty("user.home") + "\\desktop\\salida" + "\\grafoIn.jpg";

	    String parametro = "-Tjpg";
	    String op = "-o";

	    String cmd = dotPath + " " + parametro + " " + entrada + " " + op + " " + salida;

	    System.out.println(cmd);

	    Runtime.getRuntime().exec(cmd);

	} catch (Exception ex) {
	    ex.printStackTrace();
	} finally {
	}
	return salida;

    }

    int cantidad;
    nodoArbolAvl[] categorias;

    public nodoArbolAvl[] listaCategorias() {
	categorias = null;
	if (raiz != null) {

	    recCantidad(raiz);

	    categorias = new nodoArbolAvl[cantidad];
	    cantidad = 0;
	    recLlenar(raiz);

	}

	return categorias;
    }

    void recCantidad(nodoArbolAvl nodo) {
	if (nodo != null) {
	    cantidad++;
	    recCantidad(nodo.izquierda);
	    recCantidad(nodo.derecha);
	}
    }

    void recLlenar(nodoArbolAvl nodo) {
	if (nodo != null) {
	    categorias[cantidad] = nodo;
	    cantidad++;
	    recLlenar(nodo.izquierda);
	    recLlenar(nodo.derecha);
	}
    }

    String recIn(nodoArbolAvl n) {
	if (n == null) {
	    return "";
	}

	String nodos = recIn(n.izquierda);

	nodos = nodos + "node" + Integer.toString(ct) + "[label = \"" + n.categoria + " " + n.cantidad + "\"];";
	ct++;

	nodos = nodos + recIn(n.derecha);

	return nodos;

    }

    public String graficarPos() {
	ct = 0;
	String encabezado = " digraph G { rankdir= LR;\n";
	String salida = "";
	String fin = "}";
	encabezado = encabezado + recPos(raiz);
	String conexiones = "";
	for (int i = 0; i < ct - 1; i++) {
	    encabezado = encabezado + "node" + Integer.toString(i) + "->node"
		    + Integer.toString(i + 1) + "\n";
	}

	encabezado = encabezado + fin;
	File nuevo = escribir(encabezado);
	try {

	    String dotPath = "dot";

	    String entrada = nuevo.getAbsolutePath();
	    salida = System.getProperty("user.home") + "\\desktop\\salida" + "\\grafoPos.jpg";

	    String parametro = "-Tjpg";
	    String op = "-o";

	    String cmd = dotPath + " " + parametro + " " + entrada + " " + op + " " + salida;

	    System.out.println(cmd);

	    Runtime.getRuntime().exec(cmd);

	} catch (Exception ex) {
	    ex.printStackTrace();
	} finally {
	}

	return salida;
    }

    String recPos(nodoArbolAvl n) {
	if (n == null) {
	    return "";
	}

	String nodos = recPos(n.izquierda);
	nodos = nodos + recPos(n.derecha);
	nodos = nodos + "node" + Integer.toString(ct) + "[label = \"" + n.categoria + " " + n.cantidad + "\"];";
	ct++;

	return nodos;

    }
    /*void arbolBinario::graficarIn() {
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
