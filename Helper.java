package criptografia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Helper {

	// DE STRING A BYTE[] ##########

	/**
	 * Transforma un String en una cadena de bytes (Codifica)
	 * @param str
	 * @return Array de bytes
	 */

	public static byte[] string2ByteArray(String str) {
		return str.getBytes(StandardCharsets.UTF_8);

	}

	// DE BYTE[] A STRING ##########

	/**
	 * Transforma una cadena de bytes en un String
	 * @param bArr
	 * @return String codificado en charset establecido
	 */

	public static String byteArray2String(byte[] bArr) {

		// Para decodificar, necesito un Charset, en mi caso utf-8
		Charset charset = StandardCharsets.UTF_8;

		return new String(bArr,charset);

	}

	// DE BYTE[] A RAW STRING ##########

	/**
	 * Genera letras random
	 * @param bArr
	 * @return
	 */

	public static String byteArray2RawString(byte[] bArr){
		Random rnd = new Random();
		rnd.nextBytes(bArr);
		return byteArray2String(bArr);
	}

	// DE BYTE[] A FICHERO ##########

	/**
	 * Guarda en un fichero una array de bytes
	 * @param bArr
	 * @param fileName
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */

	public static void writeByteArray (byte [] bArr, String fileName) throws FileNotFoundException, IOException{
//		byte[] bytes = "Texto prueba".getBytes(StandardCharsets.UTF_8);
//		try (FileOutputStream fos = new FileOutputStream("./"+fileName)) {
//			fos.write(bytes);
//		}
		
		PrintWriter pw = new PrintWriter(fileName);
		pw.print(Arrays.toString(bArr));
		pw.close();
	}


	// DE FICHERO A BYTE[] ##########

	/**
	 * Lee un fichero y devuelve una array de bytes
	 * @param fileName
	 * @return
	 * @throws IOException 
	 */

	public static byte[] readByteArray (String fileName) throws IOException{
		byte[]bArr = new byte[20]; 
		FileInputStream fileInputStream = new FileInputStream(fileName);
		
		fileInputStream.read(bArr);
		fileInputStream.close();
		for (int i = 0; i < bArr.length; i++) {
			System.out.print(bArr[i]);
			
		}
		return bArr;
	}
	
}