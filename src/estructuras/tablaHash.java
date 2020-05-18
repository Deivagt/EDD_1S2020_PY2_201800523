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
    
    public void eliminar(int carnet){
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

    int dispersar(int carnet) {
	return carnet % 45;

    }

}
