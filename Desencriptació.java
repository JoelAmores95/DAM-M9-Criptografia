package criptografia;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Desencriptació {

	public static final String ARCHIVO_CLAVE_PRIVADA = "clauPRIVADA.key";
	public static final String ARCHIVO_TEXTO_CIFRADO = "ZZZ_missatge_encriptat.txt";
	public static final String ARCHIVO_CLAVE_AES_CIFRADA = "ZZZ_clau_encriptada.txt";
	public static final String CIPHER_PARAMS_RSA = "RSA/ECB/PKCS1Padding";
	public static final String CIPHER_PARAMS_AES = "AES/ECB/PKCS5Padding";

	public static void main(String[] args) throws Exception {

		// Leer clave privada
		PrivateKey clavePrivada = loadPrivateKey(ARCHIVO_CLAVE_PRIVADA);

		// Leer clave AES encriptada
		byte[] claveAESDesencriptada = desencriptarClaveAES(clavePrivada,ARCHIVO_CLAVE_AES_CIFRADA);
		SecretKey sKeyAES = new SecretKeySpec(claveAESDesencriptada, 0, claveAESDesencriptada.length, "AES");

		// Desencriptar texto de archivo
		System.out.println(desencriptarMensaje(sKeyAES, ARCHIVO_TEXTO_CIFRADO));
	}
	/**
	 * Carga una clave privada de un archivo
	 * @param fileName
	 * @return PrivateKey
	 * @throws Exception
	 */
	private static PrivateKey loadPrivateKey(String fileName) throws Exception {

		// Leer archivo
		FileInputStream fis = new FileInputStream(fileName);
		int numBytes = fis.available();
		byte[] bytes = new byte[numBytes];
		fis.read(bytes);
		fis.close();

		// Crear Clave
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		KeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
		PrivateKey clavePrivada = keyFactory.generatePrivate(keySpec);
		return clavePrivada;
	}

	/**
	 * Desencripta una clave AES guardada en fichero haciendo uso de la PrivateKey
	 * @param clavePrivada para desencriptar
	 * @param nombreFicheroAES nombre del archivo que contiene la clave AES
	 * @return
	 */
	public static byte[] desencriptarClaveAES(PrivateKey clavePrivada, String nombreFicheroAES) {

		byte[] bytesFitxerAES = null;
		byte[] bytesDecryptedAES = null;

		File fitxerClauAES = new File(nombreFicheroAES);

		try {
			Cipher rsa = Cipher.getInstance(CIPHER_PARAMS_RSA);
			rsa.init(Cipher.DECRYPT_MODE, clavePrivada);
			bytesFitxerAES = Files.readAllBytes(fitxerClauAES.toPath());
			bytesDecryptedAES = rsa.doFinal(bytesFitxerAES);
		} catch (Exception ex) {
			System.err.println("Error cifrando los datos: " + ex);
		}
		return bytesDecryptedAES;
	}

	/**
	 * Desencripta un mensaje desde un archivo
	 * @param sKey
	 * @param nombreArchivoMensaje
	 * @return
	 * @throws IOException
	 */
	public static String desencriptarMensaje(SecretKey sKey, String nombreArchivoMensaje) throws IOException {

		String mensajeDesencriptado = null;
		File archivoMensaje = new File(nombreArchivoMensaje);

		byte[] bytesMensajeDesencriptado;
		byte[] bytesMensaje = Files.readAllBytes(archivoMensaje.toPath());
		try {
			Cipher aes = Cipher.getInstance(CIPHER_PARAMS_AES);
			aes.init(Cipher.DECRYPT_MODE, sKey);
			bytesMensajeDesencriptado = aes.doFinal(bytesMensaje);
			mensajeDesencriptado = new String(bytesMensajeDesencriptado, StandardCharsets.UTF_8);
		} catch (Exception ex) {
			System.err.println("Error xifrant les dades: " + ex);
		}
		return mensajeDesencriptado;
	}

}
