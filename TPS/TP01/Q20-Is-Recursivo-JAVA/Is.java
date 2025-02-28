import java.util.Scanner;

public class Is {

	public static boolean verificaFim(String str, String chave, int i) {
		boolean fim = true;

		if (i < chave.length()) {
			if (str.length() == chave.length()) {
				if (str.charAt(i) != chave.charAt(i)) {
					fim = false;
				}
				else {
					fim = verificaFim(str, chave, i+1);
				}
			}
			else {
				fim = false;
			}
		}

		return fim;
	}

	public static boolean isVogal(String str, int i, int j) {
		boolean vogal = true;

		// verifica se faz parte das vogais com base na tabela ASCII
		if (i < j) {
			if (str.charAt(i) !=97 && str.charAt(i) != 101 && str.charAt(i) != 105 && str.charAt(i) != 111 && str.charAt(i) != 117 && str.charAt(i) != 65 && str.charAt(i) != 69 && str.charAt(i) != 73 && str.charAt(i) != 79 && str.charAt(i) != 85) {
				vogal = false;
				i = str.length();
			}
			else {
				vogal = isVogal(str, i+1, j);
			}
		}

		return vogal;

	}

	public static boolean isConsoante(String str, int i, int j) {
		boolean consoante = true;

		// verifica se faz parte das vogais com base na tabela ASCII
		if (i < j) {
			if (str.charAt(i) < 66 || str.charAt(i) == 69 || str.charAt(i) == 73 || str.charAt(i) == 79 || str.charAt(i) == 85 || str.charAt(i) > 90 && str.charAt(i) < 97 || str.charAt(i) == 97 || str.charAt(i) == 101 || str.charAt(i) == 105 || str.charAt(i) == 111 || str.charAt(i) == 117) {
				consoante = false;
				i = str.length();
			}
			else {
				consoante = isConsoante(str, i+1, j);
			}	
		}

		return consoante;
	}

	public static boolean isInt(String str, int i, int j) {
		boolean ehInt = true;
		
		// verifica se faz parte dos números inteiros com base na tabela ASCII
		if (i < j) {
			if (str.charAt(i) < 48 || str.charAt(i) > 57) {
				ehInt = false;
				i = str.length();
			}
			else {
				ehInt = isInt(str, i+1, j);
			}
		}

		return ehInt;
	}

	public static boolean isReal(String str, int cont, int i, int j) {
		boolean ehReal = true;

		if (i < j) {

			// faz a comparação de acordo com a tabela ASCII
			if (str.charAt(i) >= 44 && str.charAt(i) <= 57) {
				if (str.charAt(i) == 44 || str.charAt(i) == 46) {

					//se possui ponto ou vírgula irá contabilizar no contador
					cont++;
				}

				//se possuir mais de um ponto ou vírgula não será um conjunto de números reais
				if (cont <= 1) {
					ehReal = isReal(str, cont, i+1, j);
				}	
				else {
					ehReal = false;
				}
			}	
			else {
				ehReal = false;
			}
		}

		// verifica se o contador está zerado, e, se o ehReal for verdadeiro ele não entrará na condição
		if (cont == 0 && !ehReal) {

			//se o contador estiver zerado ele verifica se possui apenas números inteiros na string
			if (!isInt(str, 0, (str.length() -1))) {
				//se não possuir apenas números inteiros, então será falso para número reais
				ehReal = false;
			}
		}

		return ehReal;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		
		while (!verificaFim(str, "FIM", 0)) {
			System.out.printf(isVogal(str, 0, str.length()) ? "SIM " : "NAO ");
			System.out.printf(isConsoante(str, 0, str.length()) ? "SIM " : "NAO ");
			System.out.printf(isInt(str, 0, (str.length())) ? "SIM " : "NAO ");
			System.out.printf(isReal(str, 0, 0, (str.length())) ? "SIM%n" : "NAO%n");

			str = sc.nextLine();
		}

		sc.close();
	}
}
