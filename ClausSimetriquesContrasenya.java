package criptografia;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class ClausSimetriquesContrasenya {

	public static final String MD5 = "MD5";
	public static final String SHA1 = "SHA-1";
	public static final String SHA256 = "SHA-256";

	/* Resposta a les preguntes:
	 * 2) Si executes el programa amb un algoritme HASH en concret i una longitud de clau, 
	 * i utilitzant la mateixa contrasenya, la clau generada és sempre la mateixa? 
	 * 
	 * RESPOSTA: SÍ
	 * 
	 * 
	 * Si canvies una petita part de la contrasenya, o afegeixes caràcters o en treus, 
	 * la clau generada canvia una mica o molt?
	 * RESPOSTA: CANVIA MOLT
	 * 
	 * */



	public static void main(String[] args) {

		// Encriptación 128 bytes
		SecretKey sKey128 = passwordKeyGeneration("Contrasenya", 128, MD5);

		// Guardo los bytes de la key en una array de bytes
		byte[] arrMD5128 = sKey128.getEncoded();

		// Imprimo valor
		System.out.println("\nMD5 128:");
		imprimirKey(arrMD5128);

		// ##########################

		// Encriptación 192 bytes
		SecretKey sKey192 = passwordKeyGeneration("Contrasenya", 192, MD5);
		// Guardo los bytes de la key en una array de bytes
		byte[] arrMD5192 = sKey192.getEncoded();

		// Imprimo valor
		System.out.println("\nMD5 192:");
		imprimirKey(arrMD5192);

		// ##########################

		// Encriptación 256 bytes
		SecretKey sKey256 = passwordKeyGeneration("Contrasenya", 256, MD5);
		// Guardo los bytes de la key en una array de bytes
		byte[] arrMD5256 = sKey256.getEncoded();

		// Imprimo valor
		System.out.println("\nMD5 256:");
		imprimirKey(arrMD5256);

		// ##########################

		// SHA - 1

		// ##########################

		// Encriptación 128 bytes
		SecretKey sKey128SHA = passwordKeyGeneration("Contrasenya", 128, SHA1);

		// Guardo los bytes de la key en una array de bytes
		byte[] arrSHA128 = sKey128SHA.getEncoded();

		// Imprimo valor
		System.out.println("\nSHA-1 128:");
		imprimirKey(arrSHA128);

		// ##########################

		// Encriptación 192 bytes
		SecretKey sKey192SHA = passwordKeyGeneration("Contrasenya", 192, SHA1);
		// Guardo los bytes de la key en una array de bytes
		byte[] arrSHA192 = sKey192SHA.getEncoded();

		// Imprimo valor
		System.out.println("\nSHA-1 192:");
		imprimirKey(arrSHA192);

		// ##########################

		// Encriptación 256 bytes
		SecretKey sKey256SHA = passwordKeyGeneration("Contrasenya", 256, SHA1);
		// Guardo los bytes de la key en una array de bytes
		byte[] arrSHA256 = sKey256SHA.getEncoded();

		// Imprimo valor
		System.out.println("\nSHA-1 256:");
		imprimirKey(arrSHA256);

		// ##########################

		// SHA - 256

		// ##########################

		// Encriptación 128 bytes
		SecretKey sKey128SHA256 = passwordKeyGeneration("Contrasenya", 128, SHA256);

		// Guardo los bytes de la key en una array de bytes
		byte[] arrSHA128A = sKey128SHA256.getEncoded();

		// Imprimo valor
		System.out.println("\nSHA-256 128:");
		imprimirKey(arrSHA128A);

		// ##########################

		// Encriptación 192 bytes
		SecretKey sKey192SHA256 = passwordKeyGeneration("Contrasenya", 192, SHA256);
		// Guardo los bytes de la key en una array de bytes
		byte[] arrSHA192B = sKey192SHA256.getEncoded();

		// Imprimo valor
		System.out.println("\nSHA-256 192:");
		imprimirKey(arrSHA192B);

		// ##########################

		// Encriptación 256 bytes
		SecretKey sKey256SHA256 = passwordKeyGeneration("Contrasenya", 256, SHA256);
		// Guardo los bytes de la key en una array de bytes
		byte[] arrSHA256C = sKey256SHA256.getEncoded();

		// Imprimo valor
		System.out.println("\nSHA-256 256:");
		imprimirKey(arrSHA256C);
	}

	// Método encriptador
	public static SecretKey passwordKeyGeneration(String text, int keySize, String algoHash) {
		SecretKey sKey = null;
		if((keySize == 128) || (keySize == 192) || (keySize == 256)) {
			try {
				byte[] data = text.getBytes("UTF-8");
				MessageDigest md = MessageDigest.getInstance(algoHash);
				byte[] hash= md.digest(data);
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
	public static void imprimirKey(byte[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.println(String.format("%8s", Integer.toBinaryString(array[i] & 0xFF)).replace(' ', '0') + " "+ array[i]);
		}
	}

}
