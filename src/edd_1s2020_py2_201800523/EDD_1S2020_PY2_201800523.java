/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_1s2020_py2_201800523;

import estructuras.arbolAvl;
import estructuras.arbolB;
import estructuras.libro;

/**
 *
 * @author David
 */
public class EDD_1S2020_PY2_201800523 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	prueba();
	/*arbolB t = new arbolB(3);

	libro b = new libro();
	b.autor = "paco";
	b.isbn = 10;
	t.insertar(b);

	libro b1 = new libro();
	b1.autor = "josue";
	b1.isbn = 20;
	t.insertar(b1);

	libro b2 = new libro();
	b2.autor = "luis";
	b2.isbn = 5;
	t.insertar(b2);

	libro b3 = new libro();
	b3.autor = "david";
	b3.isbn = 6;
	t.insertar(b3);

	libro b4 = new libro();
	b4.autor = "david";
	b4.isbn = 50;
	t.insertar(b4);

	libro b5 = new libro();
	b5.autor = "david";
	b5.isbn = 3;
	t.insertar(b5);

	System.out.println("Recorrido atravesado uwu: ");
	t.atravesar();
	
	System.out.println("*********************");
	t.borrar(10);
	t.atravesar();*/
    }
    
    static void prueba() {
	arbolAvl a = new arbolAvl();
	
	a.raiz = a.insertar(a.raiz, "abc", 10);
	a.raiz = a.insertar(a.raiz, "bcd",20);
	a.raiz = a.insertar(a.raiz, "efg", 30);
	a.raiz = a.insertar(a.raiz, "hij", 40);
	a.raiz = a.insertar(a.raiz, "klm",50);
	a.raiz = a.insertar(a.raiz,"no", 25);
	
	System.out.println("Reorrido trans del arbol es: ");
	a.preOrden(a.raiz);
	
	a.raiz = a.borrar(a.raiz, "klm");
	System.out.println("");
	System.out.println("Preorden sin 10");
	a.preOrden(a.raiz);
	
	System.out.println(a.verificarBorrar(a.raiz, "bcd", 20));
	
	

    }
}
