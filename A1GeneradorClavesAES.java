package criptografia;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class A1GeneradorClavesAES {

	public static void main(String[] args) throws UnsupportedEncodingException {
		
		// Respuestas preguntas:
		// 1. Si se introduce una longitud equivocada, no genera clave, lanza excepción
		// 3. Las claves generadas son distintas cada vez
		
		// Longitud 128
		System.out.println("Clave AES 128:\n");
		
		// Utiliza el método para generar una clave AES de 128 bits
		SecretKey sKey = keygenKeyGeneration(128);
		imprimirKey(sKey);
		System.out.println();
		
		// Longitud 192
		System.out.println("Clave AES 192:\n");
		SecretKey sKey2 = keygenKeyGeneration(192);
		imprimirKey(sKey2);
		System.out.println();

		// Longitud 256
		System.out.println("Clave AES 256:\n");
		SecretKey sKey3 = keygenKeyGeneration(256);
		imprimirKey(sKey3);
		System.out.println();

	}
	
	/**
	 * Generador claves simétricas AES
	 * @param keySize, Tamaño de la key en bits (128, 192, 256)
	 * @return sKey, clave simétrica en algoritmo AES
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
	 * @param sKey
	 */
	public static void imprimirKey(SecretKey sKey) {
		
		// Paso la sKey a byte[] con .getEncoded()
		byte[] sKeyBytes = sKey.getEncoded();
		
		for (int i = 0; i < sKeyBytes.length; i++) {
			
			// Después del " " va el valor numérico, es opcional
			System.out.println(String.format("%8s", Integer.toBinaryString(sKeyBytes[i] & 0xFF)).replace(' ', '0')+ " ("+ sKeyBytes[i] + ")" );
		}
	}

}
