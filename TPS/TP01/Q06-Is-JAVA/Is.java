import java.util.Scanner;

public class Is {

	public static boolean verificaFim(String str, String chave) {
		boolean fim = false;

		if (str.length() == chave.length()) {
			fim = true;

			for (int i=0; i < chave.length(); i++) {
				if (str.charAt(i) != chave.charAt(i)) {
					fim = false;
					i = chave.length();
				}
			}
		}

		return fim;
	}

	public static boolean isVogal(String str) {
		boolean vogal = true;
		str = str.toUpperCase();

		//repetição para verificar se faz parte das vogais com base na tabela ASCII
		for (int i=0; i < str.length(); i++) {
			if (str.charAt(i) != 65 &&  str.charAt(i) != 69 && str.charAt(i) != 73 && str.charAt(i) != 79 && str.charAt(i) != 85) {
				vogal = false;
				i = str.length();
			}
		}

		return vogal;

	}

	public static boolean isConsoante(String str) {
		boolean consoante = true;
		str = str.toUpperCase();

		//repetição para verificar se faz parte das vogais com base na tabela ASCII
		for (int i=0; i < str.length(); i++) {
			if (str.charAt(i) < 66 || str.charAt(i) == 69 || str.charAt(i) == 73 || str.charAt(i) == 79 || str.charAt(i) == 85 || str.charAt(i) > 90) {
				consoante = false;
				i = str.length();
			}	
		}

		return consoante;
	}

	public static boolean isInt(String str) {
		boolean ehInt = true;
		
		//repetição para verificar se faz parte dos números inteiros com base na tabela ASCII
		for (int i=0; i < str.length(); i++) {
			if (str.charAt(i) < 48 || str.charAt(i) > 57) {
				ehInt = false;
				i = str.length();
			}
		}

		return ehInt;
	}

	public static boolean isReal(String str) {
		boolean ehReal = false;

		for (int i=0; i < str.length(); i++) {
			if (str.charAt(i) < 48 && str.charAt(i) > 57) {
				i = str.length();
			}
			else if (str.charAt(i) == 46 || str.charAt(i) == 44) {
			       ehReal = true;	
			}
		}

		return ehReal;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		
		while (!verificaFim(str, "FIM")) {
			System.out.printf(isVogal(str) ? "SIM " : "NAO ");
			System.out.printf(isConsoante(str) ? "SIM " : "NAO ");
			System.out.printf(isInt(str) ? "SIM " : "NAO ");
			System.out.printf(isReal(str) ? "SIM %n" : "NAO %n");

			str = sc.nextLine();
		}

		sc.close();
	}
}
