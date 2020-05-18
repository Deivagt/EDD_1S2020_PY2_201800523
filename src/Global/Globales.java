/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Global;

import estructuras.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author David
 */
public class Globales {

    public static tablaHash tablaDispersion = new tablaHash();
    public static arbolB arbolLibros = new arbolB(3);
    public static arbolAvl arbolCategorias = new arbolAvl();
    public static nodoLS  usuarioActivo = null;
  
    public static String hashear(String pass) {

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
	    return textoHash;

	} // For specifying wrong message digest algorithms 
	catch (NoSuchAlgorithmException e) {
	    throw new RuntimeException(e);
	}

    }
	
}
