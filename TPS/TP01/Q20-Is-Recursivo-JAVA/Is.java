import java.util.Scanner;

public class Is {

	/* 
	 * FUNÇÃO VERIFICA FIM RECURSIVA
	 */
	public static boolean verificaFim(String str, String chave, int i) {
		boolean fim = true;

		// condição de parada
		if (i < chave.length()) {

			// verifica se o tamanho das strings são iguais
			if (str.length() == chave.length()) {
				
				// verifica se os caracteres são diferentes 
				if (str.charAt(i) != chave.charAt(i)) {
					// se sim, retorna falso
					fim = false;
				}
				else {
					// se não, continua chamando recursivamente
					fim = verificaFim(str, chave, i+1);
				}
			}
			else {
				// se o tamanho das strings forem diferentes retorna falso
				fim = false;
			}
		}

		return fim;
	}

	/*
	 * FUNÇÃO VERIFICA FIM BASE QUE CHAMA A FUNÇÃO RECURSIVA
	 */ 
	public static boolean verificaFim(String str) {
		return verificaFim(str, "FIM", 0);
	}

	/*
	 * FUNÇÃO IS VOGAL RECURSIVA
	 */
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

	/*
	 * FUNÇÃO IS VOGAL BASE QUE CHAMA A FUNÇÃO RECURSIVA
	 */
	public static boolean isVogal(String str) {
		return isVogal(str, 0, str.length());
	}

	/*
	 * FUNÇÃO IS CONSOANTE RECURSIVA
	 */
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

	/*
	 * FUNÇÃO IS CONSOANTE BASE QUE CHAMA A RECURSIVA
	 */
	public static boolean isConsoante(String str) {
		return isConsoante(str, 0, str.length());
	}

	/*
	 * FUNÇÃO IS INT RECURSIVA
	 */
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

	/*
        * FUNÇÃO IS INT BASE QUE CHAMA A FUNÇÃO RECURSIVA
        */
        public static boolean isInt(String str) {
		return isInt(str, 0, str.length());
        }

	/*
	 * FUNÇÃO IS REAL RECURSIVA
	 */
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
			if (!isInt(str)) {
				//se não possuir apenas números inteiros, então será falso para número reais
				ehReal = false;
			}
		}

		return ehReal;
	}

	/*
	 * FUNÇÃO IS REAL BASE QUE CHAMA A FUNÇÃO RECURSIVA
	 */
	public static boolean isReal(String str) {
		return isReal(str, 0, 0, str.length());
	}

	/*
	 * FUNÇÃO MAIN
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		
		while (!verificaFim(str)) {
			System.out.printf(isVogal(str) ? "SIM " : "NAO ");
			System.out.printf(isConsoante(str) ? "SIM " : "NAO ");
			System.out.printf(isInt(str) ? "SIM " : "NAO ");
			System.out.printf(isReal(str) ? "SIM%n" : "NAO%n");

			str = sc.nextLine();
		}

		sc.close();
	}
}
