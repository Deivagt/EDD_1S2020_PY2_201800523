/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_1s2020_py2_201800523;

import estructuras.*;

/**
 *
 * @author David
 */
public class EDD_1S2020_PY2_201800523 {


    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

	//prueba();
	Login t = new Login();
	t.setVisible(true);

    }

    static void prueba() {
	arbolAvl a = new arbolAvl();

	a.raiz = a.insertar(a.raiz, "abc", 10);
	a.raiz = a.insertar(a.raiz, "bcd", 20);
	a.raiz = a.insertar(a.raiz, "efg", 30);
	a.raiz = a.insertar(a.raiz, "hij", 40);
	a.raiz = a.insertar(a.raiz, "klm", 50);
	//a.raiz = a.insertar(a.raiz, "no", 25);

	System.out.println("Reorrido trans del arbol es: ");
	a.preOrden(a.raiz);

	a.raiz = a.borrar(a.raiz, "klm");
	System.out.println("");
	System.out.println("Preorden sin 10");
	a.preOrden(a.raiz);

	System.out.println(a.verificarBorrar(a.raiz, "bcd", 20));

    }

    static void pruebaLS() {
	listaSimpleHash nuevo = new listaSimpleHash();

	usuario n1 = new usuario();
	n1.setCarnet(25);
	nuevo.insertar(n1);

	usuario n2 = new usuario();
	n2.setCarnet(30);
	nuevo.insertar(n2);

	usuario n3 = new usuario();
	n3.setCarnet(20);
	nuevo.insertar(n3);

	nuevo.imprimir();
	int k = 20;
	nuevo.eliminar(k);
	nuevo.eliminar(25);
	nuevo.eliminar(30);
	nuevo.eliminar(100);

	//nuevo.eliminar(30);
	System.out.println("Borrar:");
	nuevo.imprimir();

    }

    static void pruebab() {
	//pruebaHash();

	/*arbolB t = new arbolB(3);

	libro b = new libro();
	b.autor = "paco";
	b.isbn = 0;
	t.insertar(b);

	libro b1 = new libro();
	b1.autor = "josue";
	b1.isbn = 1;
	t.insertar(b1);

	libro b2 = new libro();
	b2.autor = "luis";
	b2.isbn = 2;
	t.insertar(b2);

	libro b3 = new libro();
	b3.autor = "david3";
	b3.isbn = 3;
	t.insertar(b3);

	libro b4 = new libro();
	b4.autor = "david4";
	b4.isbn = 4;
	t.insertar(b4);

	libro b5 = new libro();
	b5.autor = "david5";
	b5.isbn = 5;
	t.insertar(b5);
	
	/*libro b6 = new libro();
	b6.autor = "david6";
	b6.isbn = 6;
	t.insertar(b6);
	
	libro b7 = new libro();
	b7.autor = "david7";
	b7.isbn = 7;
	t.insertar(b7);
	
	libro b8 = new libro();
	b8.autor = "david8";
	b8.isbn = 8;
	t.insertar(b8);
	
	libro b9 = new libro();
	b9.autor = "david9";
	b9.isbn = 9;
	t.insertar(b9);
	
	libro b10 = new libro();
	b10.autor = "david10";
	b10.isbn = 10;
	t.insertar(b10);
	
	libro b11 = new libro();
	b11.autor = "david11";
	b11.isbn = 11;
	t.insertar(b11);

	libro b12 = new libro();
	b12.autor = "david12";
	b12.isbn = 12;
	t.insertar(b12);
	
	libro b13 = new libro();
	b13.autor = "david13";
	b13.isbn = 13;
	t.insertar(b13);
	
	libro b14 = new libro();
	b14.autor = "david14";
	b14.isbn = 14;
	t.insertar(b14);
	
	libro b15 = new libro();
	b15.autor = "david14";
	b15.isbn = 15;
	t.insertar(b15);
	
	libro b16 = new libro();
	b16.autor = "david14";
	b16.isbn = 16;
	t.insertar(b16);
	
	libro b17 = new libro();
	b17.autor = "david14";
	b17.isbn = 17;
	t.insertar(b17);
	
	//t.imprimir();
	System.out.println(t.raiz.libros[0].isbn);
	System.out.println("Recorrido atravesado uwu: ");
	t.atravesar();
	
	/*System.out.println("*********************");
	t.borrar(0);
	
	System.out.println(t.raiz.libros[0].isbn);
	t.atravesar();*/
    }

    static void pruebaHash() {
	tablaHash nuevo = new tablaHash();

	usuario n1 = new usuario();
	n1.setCarnet(44);
	nuevo.insertar(n1);

	usuario n2 = new usuario();
	n2.setCarnet(89);
	nuevo.insertar(n2);

	usuario n3 = new usuario();
	n3.setCarnet(47);
	nuevo.insertar(n3);

	nodoLS bus = nuevo.buscar(44);
	bus.getContenido().setCarnet(10);

	bus = nuevo.buscar(10);
	if (bus != null) {
	    System.out.println(bus.getContenido().getCarnet());
	}
    }
}
