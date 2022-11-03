package criptografia;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.NoSuchPaddingException;

public class Generació {

	public static final String CLAU_PUBLICA = "clauPública.key";
	public static final String CLAU_PRIVADA = "clauPRIVADA.key";
	public static final int TAMANYO_CLAVES = 2048; // Tamaños = 512, 1024 (default), 2048 (considerada segura), 4096...
	public static final String ALGORITME = "RSA";
	
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, FileNotFoundException, IOException {
		
		// Esta libreria sirve para crear un par de claves (pública y privada)
		KeyPairGenerator generadorClau = KeyPairGenerator.getInstance(ALGORITME);
		generadorClau.initialize(TAMANYO_CLAVES);
		
		KeyPair claves = generadorClau.generateKeyPair();
		guardarClaves(claves, CLAU_PUBLICA, CLAU_PRIVADA);
				
		System.out.println("Claves generadas correctamente.");
	}	
		
	/**
	 * Guarda en los archivos especificados las claves pública y privada
	 * @param keyPair
	 * @param archClavePublica, nombre del archivo donde se guarda la clave pública
	 * @param archClavePrivada, nombre del archivo donde se guarda la clave privada
	 * @throws IOException
	 */
	public static void guardarClaves(KeyPair keyPair, String archClavePublica, String archClavePrivada) throws IOException {
		
		// Separar las claves
		PrivateKey clavePrivada = keyPair.getPrivate();
		PublicKey clavePublica = keyPair.getPublic();
 
		// Guardar clave pública
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(clavePublica.getEncoded());
		FileOutputStream fos = new FileOutputStream(archClavePublica);
		fos.write(x509EncodedKeySpec.getEncoded());
		fos.close();
 
		// Guardar clave privada
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(clavePrivada.getEncoded());
		fos = new FileOutputStream(archClavePrivada);
		fos.write(pkcs8EncodedKeySpec.getEncoded());
		fos.close();
	}

}
