/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

import java.math.BigInteger;

import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;


public class usuario {

    int carnet;
    String nombre;
    String apellido;
    String carrera;
    String pass;

    public usuario() {

    }

    public int getCarnet() {
	return carnet;
    }

    public void setCarnet(int carnet) {

	this.carnet = carnet;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getApellido() {
	return apellido;
    }

    public void setApellido(String apellido) {
	this.apellido = apellido;
    }

    public String getCarrera() {
	return carrera;
    }

    public void setCarrera(String carrera) {
	this.carrera = carrera;
    }

    public String getPass() {
	return pass;
    }

    public void setPass(String pass) {

	try {

	    // Static getInstance method is called with hashing MD5 
	    MessageDigest md = MessageDigest.getInstance("MD5");

	    // digest() method is called to calculate message digest 
	    //  of an input digest() return array of byte 
	    byte[] messageDigest = md.digest(pass.getBytes());

	    // Convert byte array into signum representation 
	    BigInteger no = new BigInteger(1, messageDigest);

	    // Convert message digest into hex value 
	    String textoHash = no.toString(16);
	    while (textoHash.length() < 32) {
		textoHash = "0" + textoHash;
	    }
	    this.pass = textoHash;

	} // For specifying wrong message digest algorithms 
	catch (NoSuchAlgorithmException e) {
	    throw new RuntimeException(e);
	}

    }

}
