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
public class tablaHash {

    int tamanio;
    listaSimpleHash[] data;

    public tablaHash() {

	tamanio = 45;
	data = new listaSimpleHash[tamanio];
	for (int i = 0; i < tamanio; i++) {
	    data[i] = new listaSimpleHash();
	}

    }

    public boolean insertar(usuario nuevoUsuario) {
	int ubicacion = dispersar(nuevoUsuario.getCarnet());

	if (data[ubicacion].buscar(nuevoUsuario.getCarnet()) == null) {
	    data[ubicacion].insertar(nuevoUsuario);
	    return true;
	}

	return false;

    }

    public nodoLS buscar(int carnet) {
	for (int i = 0; i < tamanio; i++) {
	    nodoLS busqueda = data[i].buscar(carnet);
	    if (busqueda != null) {
		return busqueda;
	    }
	}
	return null;
    }

    public void eliminar(int carnet) {
	for (int i = 0; i < tamanio; i++) {
	    nodoLS busqueda = data[i].buscar(carnet);
	    if (busqueda != null) {
		data[i].eliminar(carnet);
	    }
	}
    }

    public void imprimir() {
	for (int i = 0; i < tamanio; i++) {
	    System.out.println(" casilla" + i);
	    impresora(i);

	}
    }

    void impresora(int i) {
	data[i].imprimir();
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
	String encabezado = "digraph G {\n";
	String salida = "";
	String fin = "}";
	encabezado = encabezado + graficadora() + fin;

	System.out.println(encabezado);
	System.out.println(encabezado);
	File nuevo = escribir(encabezado);

	try {

	    String dotPath = "dot";

	    String entrada = nuevo.getAbsolutePath();
	    salida = System.getProperty("user.home") + "\\desktop\\salida" + "\\grafoDispersa" + ".jpg";

	    String parametro = "-Tjpg";
	    String op = "-o";

	    String cmd = dotPath + " " + parametro + " " + entrada + " " + op + " " + salida;

	    Runtime.getRuntime().exec(cmd);
	

	} catch (Exception ex) {
	    ex.printStackTrace();
	} finally {
	}
	return salida;
    }

    public String graficadora() {
	String nodos = "";
	String rankear = "";
	for (int i = 0; i < tamanio; i++) {
	    if (data[i].estaVacio() != true) {

		nodos = nodos + "cabecera" + i + "[label = \"" + i + "\"];\n";

		nodos = nodos + "cabecera" + i + "->Casilla" + i + "nodo" + 0 + "\n";
		nodos = nodos + data[i].graficar(i);
		int j = i;

		while (j != 44) {
		    j++;
		    if (data[j].estaVacio() != true) {
			nodos = nodos + "cabecera" + i + "->cabecera" + j + "\n";
			break;
		    }
		}
		rankear = rankear + "{rank = same; \n" + "cabecera" + i + "\n" + data[i].rankeo(i) + "}\n";

	    }

	}
	nodos = nodos + rankear;
	return nodos;
    }

    int dispersar(int carnet) {
	return carnet % 45;

    }

}
