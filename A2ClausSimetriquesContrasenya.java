package criptografia;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class A2ClausSimetriquesContrasenya {

	public static final String MD5 = "MD5"; // Vulnerable, de 128 bits
	public static final String SHA1 = "SHA-1"; // Vulnerable, de 160 bits
	public static final String SHA256 = "SHA-256"; // Ideal, de 256 bits

	/* Resposta a les preguntes:
	 * 2) Si executes el programa amb un algoritme HASH en concret i una longitud de clau, 
	 * i utilitzant la mateixa contrasenya, la clau generada �s sempre la mateixa? 
	 * 
	 * RESPOSTA: S�, entrando la misma contrase�a siempre se recibe la misma clave
	 * Porque por ejemplo nos da la clave 6, que se puede obtener de 6*1, 2*3, 3*2, 2*1*3, 1*2*3... 
	 * Por eso se dice que no se puede obtener la contrase�a desde la clave.
	 * 
	 * 
	 * Si canvies una petita part de la contrasenya, o afegeixes car�cters o en treus, 
	 * la clau generada canvia una mica o molt?
	 * RESPOSTA: CANVIA MOLT
	 * 
	 * */



	public static void main(String[] args) {

		// Encriptaci�n 128 bytes
		SecretKey sKey128 = passwordKeyGeneration("Contrasenya", 128, MD5);
		System.out.println("\nMD5 128:");
		imprimirKey(sKey128);

		// ##########################

		// Encriptaci�n 192 bytes
		SecretKey sKey192 = passwordKeyGeneration("Contrasenya", 192, MD5);
		System.out.println("\nMD5 192:");
		imprimirKey(sKey192);

		// ##########################

		// Encriptaci�n 256 bytes
		SecretKey sKey256 = passwordKeyGeneration("Contrasenya", 256, MD5);
		System.out.println("\nMD5 256:");
		imprimirKey(sKey256);

		// ##########################

		// SHA - 1

		// ##########################

		// Encriptaci�n 128 bytes
		SecretKey sKey128SHA = passwordKeyGeneration("Contrasenya", 128, SHA1);
		System.out.println("\nSHA-1 128:");
		imprimirKey(sKey128SHA);

		// ##########################

		// Encriptaci�n 192 bytes
		SecretKey sKey192SHA = passwordKeyGeneration("Contrasenya", 192, SHA1);
		System.out.println("\nSHA-1 192:");
		imprimirKey(sKey192SHA);

		// ##########################

		// Encriptaci�n 256 bytes
		SecretKey sKey256SHA = passwordKeyGeneration("Contrasenya", 256, SHA1);
		System.out.println("\nSHA-1 256:");
		imprimirKey(sKey256SHA);

		// ##########################

		// SHA - 256

		// ##########################

		// Encriptaci�n 128 bytes
		SecretKey sKey128SHA256 = passwordKeyGeneration("Contrasenya", 128, SHA256);
		System.out.println("\nSHA-256 128:");
		imprimirKey(sKey128SHA256);

		// ##########################

		// Encriptaci�n 192 bytes
		SecretKey sKey192SHA256 = passwordKeyGeneration("Contrasenya", 192, SHA256);
		System.out.println("\nSHA-256 192:");
		imprimirKey(sKey192SHA256);

		// ##########################

		// Encriptaci�n 256 bytes
		SecretKey sKey256SHA256 = passwordKeyGeneration("Contrasenya", 256, SHA256);
		System.out.println("\nSHA-256 256:");
		imprimirKey(sKey256SHA256);
	}

	/**
	 * Genera una clave sim�trica a trav�s de una contrase�a
	 * @param text
	 * @param keySize
	 * @param algoHash
	 * @return
	 */
	public static SecretKey passwordKeyGeneration(String password, int keySize, String algoritmoHash) {
		
		SecretKey sKey = null;
		
		if((keySize == 128) || (keySize == 192) || (keySize == 256)) {
			try {
				
				// Este objeto crea contrase�as
				MessageDigest md = MessageDigest.getInstance(algoritmoHash);
				
				// Transformo un texto a bytes y los guardo
				byte[] data = password.getBytes("UTF-8");
				byte[] hash = md.digest(data);
				//Del hash obtenido, cojo el tama�o que necesito, siempre se divide entre 8
				byte[] key = Arrays.copyOf(hash, keySize/8);
				
				sKey = new SecretKeySpec(key, "AES");
			} catch (Exception ex) {
				System.err.println("Error generant la clau: " + ex);
			}
		}
		return sKey;
	}

	/**
	 * Imprimir key
	 * @param array
	 */
	public static void imprimirKey(SecretKey sKey) {
		
		// Transformo la sKey a bytes y los guardo en una array
		byte[] sKeyBytes = sKey.getEncoded();
		
		// Imprimo los bytes
		for (int i = 0; i < sKeyBytes.length; i++) {
			System.out.println(String.format("%8s", Integer.toBinaryString(sKeyBytes[i] & 0xFF)).replace(' ', '0') + " ("+ sKeyBytes[i] + ")");
		}
	}

}
