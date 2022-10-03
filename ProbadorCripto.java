package criptografia;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class ProbadorCripto {

	public static void main(String[] args) throws IOException {

		// PRUEBA 1 - De String a byte[] ###################
		
		String str = "Holaaa ñ";
		byte[] bArr = Helper.string2ByteArray(str);
		System.out.println("Prueba 1: " + Arrays.toString(bArr));
		
		// PRUEBA 2 - De byte[] a String ###################
		
		String str2 = Helper.byteArray2String(bArr);
		System.out.println("Prueba 2: " + str2);
		
		// PRUEBA 3 - De byte[] a Raw String ###############
		
//		String str3 = Helper.byteArray2RawString(bArr);
//		System.out.println("Prueba 3: " + str3);
		
		// PRUEBA 4 - Guardo fichero ########################
		String fichero = "ficheroPruebas.txt";
		Helper.writeByteArray(Helper.string2ByteArray(str), fichero);

		// PRUEBA 5 - De fichero a byte[]
		byte[] lectura1 = Helper.readByteArray(fichero);
		System.out.println("Prueba 5.2: " + Helper.byteArray2String(lectura1));
		
		// Pasar String a array[]
		String decodificame = Helper.byteArray2String(lectura1);
		
		System.out.println((decodificame));
		

	}

}
