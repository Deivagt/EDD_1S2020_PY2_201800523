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
public class nodoArbolB {

    libro[] libros;
    int minimo;
    nodoArbolB[] hijos;
    int cantidad;
    boolean esHoja;

    public nodoArbolB(int minimo, boolean esHoja) {
	this.minimo = minimo;
	this.esHoja = esHoja;
	this.libros = new libro[2 * minimo - 1];
	this.hijos = new nodoArbolB[2 * minimo];
	this.cantidad = 0;
    }

    public void atravesar() {
	int i;
	for (i = 0; i < this.cantidad; i++) {
	    if (esHoja == false) {
		hijos[i].atravesar();
	    }
	    System.out.println(i + " " + libros[i].isbn + " " + libros[i].autor + " ");
	}
	System.out.println("	fin nodo");
	if (esHoja == false) {
	    hijos[i].atravesar();
	}
    }

    public int obtenerId(int isbn) {
	int id = 0;
	while (id < cantidad && libros[id].isbn < isbn) {
	    id++;
	}
	System.out.println("#####"+id);
	return id;
    }

    public nodoArbolB buscar(int isbn) {
	int i = 0;
	while (i < cantidad && isbn > libros[i].isbn) {
	    System.out.println(libros[i].isbn);
	    i++;
	}
	if (libros[i] != null) {
	    if (isbn == libros[i].isbn) {
		return this;
	    }
	}

	// If the key is not found here and this is a leaf node 
	if (esHoja == true) {
	    return null;
	}

	// Go to the appropriate child 
	return hijos[i].buscar(isbn);

    }

    public void partir(int i, nodoArbolB temp) {

	nodoArbolB temp1 = new nodoArbolB(temp.minimo, temp.esHoja);
	temp1.cantidad = minimo - 1;
	for (int j = 0; j < minimo - 1; j++) {
	    temp1.libros[j] = temp.libros[j + minimo];

	}

	if (temp.esHoja == false) {
	    for (int j = 0; j < minimo; j++) {
		temp1.hijos[j] = temp.hijos[j + minimo];
	    }
	}

	temp.cantidad = minimo - 1;
	for (int j = cantidad; j >= i + 1; j--) {
	    hijos[j + 1] = hijos[j];
	}
	hijos[i + 1] = temp1;

	for (int j = cantidad - 1; j >= i; j--) {
	    libros[j + 1] = libros[j];
	}
	libros[i] = temp.libros[minimo - 1];
	cantidad = cantidad + 1;
    }

    void insertarMedioLleno(libro nuevoLibro) {
	int i = cantidad - 1;
	if (esHoja == true) {
	    while (i >= 0 && libros[i].isbn > nuevoLibro.isbn) {
		libros[i + 1] = libros[i];
		i--;
	    }

	    libros[i + 1] = nuevoLibro;
	    cantidad = cantidad + 1;
	} else {
	    while (i >= 0 && libros[i].isbn > nuevoLibro.isbn) {
		i--;
	    }
	    if (hijos[i + 1].cantidad == 2 * minimo - 1) {
		partir(i + 1, hijos[i + 1]);

		if (libros[i + 1].isbn < nuevoLibro.isbn) {
		    i++;
		}
	    }
	    hijos[i + 1].insertarMedioLleno(nuevoLibro);
	}
    }

    void borrar(int isbn) {
	int id = obtenerId(isbn);

	if (id < cantidad && libros[id].isbn == isbn) {

	    if (esHoja == true) {
		quitarDeHoja(id);
	    } else {
		quitarDeNoHoja(id);
	    }

	} else {
	    if (esHoja == true) {
		System.out.println("El libro" + isbn + "No existe en el arbol");
	    }

	    boolean control;
	    if (id == cantidad) {
		control = true;
	    } else {
		control = false;
	    }

	    if (hijos[id].cantidad < minimo) {
		llenar(id);
	    }

	    if (control == true && id > cantidad) {
		hijos[id - 1].borrar(isbn);
	    } else {
		hijos[id].borrar(isbn);
	    }

	}
    }

    void quitarDeHoja(int id) {
	for (int i = id + 1; i < cantidad; ++i) {
	    libros[i - 1] = libros[i];
	}
	cantidad--;
    }

    void quitarDeNoHoja(int id) {
	libro temp = libros[id];

	if (hijos[id].cantidad >= minimo) {
	    libro previo = obtenerPrevio(id);
	    libros[id] = previo;
	    hijos[id].borrar(previo.isbn);
	} else if (hijos[id + 1].cantidad >= minimo) {
	    libro siguiente = obtenerSiguiente(id);
	    libros[id] = siguiente;
	    hijos[id + 1].borrar(siguiente.isbn);
	} else {
	    unir(id);
	    hijos[id].borrar(temp.isbn);
	}
    }

    libro obtenerPrevio(int id) {
	nodoArbolB temp = hijos[id];
	while (!(temp.esHoja == true)) {
	    temp = temp.hijos[temp.cantidad];
	}

	return temp.libros[temp.cantidad - 1];
    }

    libro obtenerSiguiente(int id) {
	nodoArbolB temp = hijos[id];
	while (!(temp.esHoja == true)) {
	    temp = temp.hijos[0];
	}
	return temp.libros[0];
    }

    void llenar(int id) {
	if (id != 0 && hijos[id - 1].cantidad >= minimo) {
	    tomarDeAnterior(id);
	} else if (id != cantidad && hijos[id + 1].cantidad >= minimo) {
	    tomarDeSiguiente(id);
	} else {
	    if (id != cantidad) {
		unir(id);
	    } else {
		unir(id - 1);
	    }
	}
    }

    void tomarDeAnterior(int id) {
	nodoArbolB hijo = hijos[id];
	nodoArbolB hermano = hijos[id - 1];

	for (int i = hijo.cantidad - 1; i >= 0; --i) {
	    hijo.libros[i + 1] = hijo.libros[i];
	}

	if (hijo.esHoja == false) {
	    for (int i = hijo.cantidad; i >= 0; --i) {
		hijo.hijos[i + 1] = hijo.hijos[i];
	    }
	}

	hijo.libros[0] = libros[id - 1];

	if (hijo.esHoja == false) {
	    hijo.hijos[0] = hermano.hijos[hermano.cantidad];

	}

	libros[id - 1] = hermano.libros[hermano.cantidad - 1];

	hijo.cantidad += 1;
	hermano.cantidad -= 1;

    }

    void tomarDeSiguiente(int id) {
	nodoArbolB hijo = hijos[id];
	nodoArbolB hermano = hijos[id + 1];

	hijo.libros[hijo.cantidad] = libros[id];

	if (hijo.esHoja == false) {
	    hijo.hijos[hijo.cantidad + 1] = hermano.hijos[0];
	}

	libros[id] = hermano.libros[0];

	for (int i = 1; i < hermano.cantidad; ++i) {
	    hermano.libros[i - 1] = hermano.libros[i];
	}

	if (hermano.esHoja == false) {
	    for (int i = 1; i <= hermano.cantidad; ++i) {
		hermano.hijos[i - 1] = hermano.hijos[i];
	    }
	}

	hijo.cantidad += 1;
	hermano.cantidad -= 1;
    }

    void unir(int id) {
	nodoArbolB hijo = hijos[id];
	nodoArbolB hermano = hijos[id + 1];

	hijo.libros[minimo - 1] = libros[id];

	for (int i = 0; i < hermano.cantidad; ++i) {
	    hijo.libros[i + minimo] = hermano.libros[i];
	}

	if (hijo.esHoja == false) {
	    for (int i = 0; i < hermano.cantidad; ++i) {
		hijo.hijos[i + minimo] = hermano.hijos[i];
	    }
	}
	
	   for(int i = id +1;i <cantidad;++i){
	       libros[i-1]=libros[i];
	   }
	   
	   for(int i = id + 2; i <= cantidad;++i){
	       hijos[i-1]=hijos[i];
	   }
	   
	   hijo.cantidad += hermano.cantidad +1;
	   cantidad--;
	   
    }

}
