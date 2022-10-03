package criptografia;

import java.io.IOException;
import java.util.Scanner;

public class CifradoCesar {

	public static void main(String[] args) throws IOException {
		
		// 1 - Introducir un texto 

		String texto = pedirTexto();

		// 2 - Introducir el c�digo (cuantas letras se desplaza)

		int codigo = pedirCodificacion();

		// 3 - Introducir la operaci�n a realizar: cifrar o descifrar
		char opcion;
		
		do {
			System.out.print("(C) cifrar o (D) descifrar?: ");
			opcion = (char) System.in.read();
		} while (Character.toUpperCase(opcion) != 'C' && Character.toUpperCase(opcion) != 'D');                   
		if (Character.toUpperCase(opcion) == 'C') {
			System.out.println("Texto cifrado: " + cifradoCesar(texto, codigo));
		} else {
			System.out.println("Texto descifrado: " + descifradoCesar(texto, codigo));
		}
	
	}

	/**
	 * Cifrar el texto en c�digo C�sar
	 * @param palabra
	 * @param key
	 * @return String cifrada en c�digo C�sar
	 */
	public static String cifradoCesar(String palabra,int key){
		String result="";
		int aux;
		for(int i=0;i<palabra.length();i++)
		{
			aux=(int) palabra.charAt(i);
			aux=aux+key;

			aux -= 48;
			aux = aux%75;
			aux += 48;
			result += (char) aux;
		}
		return(result);

	}
	
	/**
	 * Descifrar el texto en c�digo C�sar
	 * @param palabra
	 * @param key
	 * @return String descifrada
	 */
	public static String descifradoCesar(String palabra, int key)
	{
		String result="";
		int aux;
		for(int i=0;i<palabra.length();i++){
			aux=(int) palabra.charAt(i);
			aux=aux-key;
			aux-=48;
			aux=aux%75;
			aux+=48;
			result+=(char) aux;
		}
		return result;
	}

	/**
	 * Pedir un texto
	 * @return String texto
	 */
	public static String pedirTexto() {
		Scanner sc = new Scanner(System.in);
		String texto;
		
		// Seguir� pidiendo hasta que se entre un texto
		do {
			System.out.print("Introduce un texto: ");
			texto = sc.nextLine();
		} while (texto.isEmpty());
		
		return texto;
	}
	
	/**
	 * Pide el n�mero de letras que se desplaza
	 * @return int numero de desplazamiento (codificaci�n)
	 */
	public static int pedirCodificacion() {
		Scanner sc = new Scanner(System.in);
		int codigo;
		do {
			System.out.print("Introduce el c�digo: ");
			codigo = sc.nextInt();
			
		} while (codigo < 1);
		sc.nextLine();
		return codigo;
	}

}