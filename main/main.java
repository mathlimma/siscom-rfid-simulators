package main;
import java.util.Scanner;

import control.Controller;

public class main {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Especifique quais simuladores você deseja utilizar: \n"
				+ "1 - Lower Bound \n"
				+ "2 -  Eom-Lee \n"
				+ "3 - Ambos");
		int protocolsChoosen = in.nextInt();
		System.out.println("Especifique o número de inicial de etiquetas: ");
		int inicialNumberTags = in.nextInt();
		System.out.println("Especifique o número de incremento de etiquetas: ");
		int incrementTagsBy = in.nextInt();
		System.out.println("Especifique o número máximo de etiquetas: ");
		int maxNumberTags = in.nextInt();
		System.out.println("Especifique o número de repetições por quantidade de etiquetas: ");
		int repetitionsEachNumberTags = in.nextInt();
		System.out.println("Especifique o tamanho inicial do quadro: ");
		int inicialFrameSize = in.nextInt();
		
		Controller c = new Controller(inicialNumberTags,incrementTagsBy,maxNumberTags,repetitionsEachNumberTags,inicialFrameSize,protocolsChoosen);
		
		c.runEstimators();
		
		System.out.println("heh");
		
		
		
		
		
		
		

	}

}



