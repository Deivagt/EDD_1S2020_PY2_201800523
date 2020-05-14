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
public class arbolAvl {

    public nodoArbolAvl raiz;
    public nodoArbolAvl temp;
    boolean control = false;
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
	    return (new nodoArbolAvl(categoria, carnetAutor));
	}

	if (categoria.compareTo(nodo.categoria) < 0) {
	    nodo.izquierda = insertar(nodo.izquierda, categoria, carnetAutor);
	} else if (categoria.compareTo(nodo.categoria) > 0) {
	    nodo.derecha = insertar(nodo.derecha, categoria, carnetAutor);
	} else if (categoria.compareTo(nodo.categoria) == 0) {
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
	return nodo;
    }

    public void preOrden(nodoArbolAvl nodo) {
	if (nodo != null) {
	    System.out.print(nodo.categoria + " ");
	    preOrden(nodo.izquierda);
	    preOrden(nodo.derecha);
	}
    }
    
    public nodoArbolAvl buscar(String categoria){
	temp = null;
	bsq(raiz, categoria);
	return temp;
    }
      public void bsq(nodoArbolAvl nodo, String categoria) {
	if (nodo != null) {
	  if(nodo.categoria == categoria){
	      temp = nodo;
	  }
	    bsq(nodo.izquierda,categoria);
	    bsq(nodo.derecha, categoria);
	    
	}
	
    }

    public boolean verificarBorrar(nodoArbolAvl nodo, String categoria, int carnet){
	control = false;
	vefBor(nodo, categoria, carnet);
	
	return  control;
    }
    public void vefBor(nodoArbolAvl nodo, String categoria, int carnet) {
	categoria = categoria.toLowerCase();
	if (nodo != null) {
	  if(categoria.equals(nodo.categoria) && carnet == nodo.carnetAutor){
	      control = true;
	  }
	    vefBor(nodo.izquierda,categoria,carnet);
	    vefBor(nodo.derecha,categoria,carnet);
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

}
