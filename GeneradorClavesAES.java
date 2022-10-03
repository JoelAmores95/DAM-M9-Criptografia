package criptografia;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class GeneradorClavesAES {

	public static void main(String[] args) throws UnsupportedEncodingException {
		
		// Respuestas preguntas:
		// 1. Si se introduce una longitud equivocada, no genera clave, lanza excepción
		// 3. Las claves generadas son distintas cada vez
		
		// Longitud 128
		System.out.println("Clave AES 128");
		SecretKey sKey = keygenKeyGeneration(128);
		byte bytes128[]= sKey.getEncoded();
		imprimirKey(bytes128);
		System.out.println();
		
		
		// Longitud 192
		System.out.println("Clave AES 192");
		SecretKey sKey2 = keygenKeyGeneration(192);
		byte bytes192[]= sKey2.getEncoded();
		imprimirKey(bytes192);
		System.out.println();

		// Longitud 256
		System.out.println("Clave AES 256");
		SecretKey sKey3 = keygenKeyGeneration(256);
		byte bytes256[]= sKey3.getEncoded();
		imprimirKey(bytes256);
		System.out.println();

	}
	/**
	 * Generador claves AES
	 * @param keySize
	 * @return 
	 */
	public static SecretKey keygenKeyGeneration(int keySize) {
		SecretKey sKey = null;
		if((keySize == 128) || (keySize == 192) || (keySize == 256)) {
			try {
				KeyGenerator kgen = KeyGenerator.getInstance("AES");
				kgen.init(keySize);
				sKey = kgen.generateKey();
			} catch (NoSuchAlgorithmException ex) {
				System.err.println("Generador no disponible.");
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
