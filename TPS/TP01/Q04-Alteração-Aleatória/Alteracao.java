import java.util.Scanner;
import java.util.Random;

public class Alteracao {

	public static boolean verificaFim(String str, String chave) {
		boolean fim = false;

		if (str.length() == chave.length()) {
			fim = true;

			for (int i=0; i < chave.length(); i++) {
				if(str.charAt(i) != chave.charAt(i)) {
					fim = false;
					i = chave.length();
				}
			}
		}
		return fim;
	}

	public static String alterarLetra(String str, Random gerador) {
		StringBuilder newStr = new StringBuilder(str);

		char rand0 = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));
		char rand1 = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));		
		/*
		 * while (rand0 == rand1) {
		 *	rand1 = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));
		}*
		*/
		
		//faz a alteracao da letra sorteada para a string
		for (int i=0; i < str.length(); i++) {
			
			//verifica se a primeira letra sorteada é igual a letra da string
			if (newStr.charAt(i) == rand0) {

				//altera a letra da string para a segunda letra sorteada
				newStr.setCharAt(i, rand1);
			}
		}

		return newStr.toString();
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random gerador = new Random();
		gerador.setSeed(4);

		String str = sc.nextLine();

		while (!verificaFim(str, "FIM")) {
			System.out.println(alterarLetra(str, gerador));

			str = sc.nextLine();
		}
		
		sc.close();
	}
}
