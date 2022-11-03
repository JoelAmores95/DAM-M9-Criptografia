package criptografia;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class A4FraseMasContrasena {
	
	public static final String FRASE = "Frase a encriptar, con simbolos raros ñç¨^";

	public static void main(String[]args) {

		Scanner teclado = new Scanner(System.in);

		// Contraseña
		System.out.println("Contraseña: ");
		String contrasena = teclado.nextLine();
		teclado.close();
		
		// Muestra los bytes y la frase desencriptada
		solucionEjercicio(contrasena, FRASE);
		
	}

	/**
	 * Generador claves AES
	 * @param keySize
	 * @return 
	 */
	public static SecretKey passwordKeyGeneration(String password) {
		SecretKey sKey = null;
		try {
			byte[] data = password.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hash= md.digest(data);
			byte[] key = Arrays.copyOf(hash, 256/8);
			sKey = new SecretKeySpec(key, "AES");
		} catch (Exception ex) {
			System.err.println("Error generant la clau: " + ex);
		}
		return sKey;
	}

	/**
	 * Encripta datos
	 * @param sKey clave AES
	 * @param frase a encriptar
	 * @return frase encriptada en formato de array de bytes
	 */
	public static byte[] encryptData(SecretKey sKey, String frase) {
		byte[] encryptedData = null;
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, sKey);
			encryptedData = cipher.doFinal(frase.getBytes("UTF-8"));
		}
		catch (Exception ex) {
			System.err.println("Error cifrado: "+ ex);
		}
		return encryptedData;
	}

	/**
	 * Desencripta datos
	 * @param sKey clave AES
	 * @param datosEncriptados, array de bytes encriptados
	 * @return frase desencriptada, totalmente legible
	 */
	public static String decryptData(SecretKey sKey, byte[] datosEncriptados) {
		String frase  = null;
		byte[] decryptedData = null;
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, sKey);
			decryptedData = cipher.doFinal(datosEncriptados);
			frase = new String(decryptedData,StandardCharsets.UTF_8);
		}
		catch (Exception ex) {
			System.err.println("Error cifrado: "+ ex);
		}
		return frase;
	}

	/**
	 * Solución al ejercicio.
	 * Muestra en pantalla los bytes codificados
	 * @param password, usada para crear la clave
	 * @param frase a encriptar
	 */
	public static void solucionEjercicio(String password, String frase) {
		// Crear sKey
		SecretKey sKey = passwordKeyGeneration(password);

		System.out.println("Bytes:");
		byte[]arr = encryptData(sKey, frase);

		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}

		// Desencriptar frase
		String fraseDesencriptada = decryptData(sKey, arr);
		System.out.println("\nFrase desencriptada: " + fraseDesencriptada);
	}
}