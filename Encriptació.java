package criptografia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Encriptació {

	public static final String CLAU_PUBLICA = "clauPública.key";
	public static final String TEXTO_ORIGINAL = "Texto original de prueba 1, 2, 3 ñç*^^$%, lee caracteres raros";

	public static final int TAMANYO_AES = 128;

	public static final String CIPHER_PARAMS_RSA = "RSA/ECB/PKCS1Padding";
	public static final String CIPHER_PARAMS_AES = "AES/ECB/PKCS5Padding";

	public static final String ARCHIVO_CLAVE_AES_CIFRADA = "ZZZ_clau_encriptada.txt";
	public static final String ARCHIVO_TEXTO_CIFRADO = "ZZZ_missatge_encriptat.txt";

	public static void main(String[] args) throws Exception {

		Scanner teclado = new Scanner(System.in);

		// Pedir nombre archivo de la clave pública
		System.out.println("Nombre del archivo: ");
		//	String nombreArchivo = teclado.nextLine();
		String nombreArchivo = CLAU_PUBLICA;

		// Cargo la publicKey de archivo
		PublicKey publicKey = leerPublicKey(nombreArchivo);
		System.out.println("Clave cargada correctamente.");

		// Texto a encriptar
		System.out.println("Escribe el texto que quieres encriptar: ");
		// String texto = teclado.nextLine();
		String texto = TEXTO_ORIGINAL;

		// Crear clave AES
		Key keyAES = keygenKeyGeneration(TAMANYO_AES);

		// Obtener la clase para encriptar/desencriptar
		Cipher aes = Cipher.getInstance(CIPHER_PARAMS_AES);

		// Se encripta el texto con la keyAES
		aes.init(Cipher.ENCRYPT_MODE, keyAES);
		byte[] textoEncriptado = aes.doFinal(texto.getBytes("UTF-8"));

		// Guardar el archivo del texto encriptado
		saveBytesToFile(textoEncriptado, ARCHIVO_TEXTO_CIFRADO);
		System.out.println("Archivo de texto encriptado guardado correctamente.");

		// Encriptar clave simétrica AES 
		Cipher rsa = Cipher.getInstance("RSA");
		rsa.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] claveAESEncriptada = rsa.doFinal(keyAES.getEncoded());

		// Guardar el archivo del texto encriptado
		saveBytesToFile(claveAESEncriptada, ARCHIVO_CLAVE_AES_CIFRADA);
		System.out.println("Archivo de clave AES encriptada guardado correctamente.");
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
	 * Lee una PublicKey de un archivo
	 * @param nombreArchivo
	 * @return PublicKey
	 * @throws Exception
	 */
	private static PublicKey leerPublicKey(String nombreArchivo) throws Exception {

		// Leer archivo
		File fis = new File(nombreArchivo);
		byte[] bytes;
		bytes = Files.readAllBytes(fis.toPath());

		// Crear PublicKey
		KeySpec keySpec = new X509EncodedKeySpec(bytes);
		PublicKey keyFromBytes = KeyFactory.getInstance("RSA").generatePublic(keySpec);
		return keyFromBytes;
	}

	/**
	 * Guarda una array de bytes en un archivo
	 * @param arr
	 * @param fileName
	 * @throws Exception
	 */
	private static void saveBytesToFile(byte[] arr, String fileName) throws Exception {
		FileOutputStream fos = new FileOutputStream(fileName);
		fos.write(arr);
		fos.close();
	}

}
