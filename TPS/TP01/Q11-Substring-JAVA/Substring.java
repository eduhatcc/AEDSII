import java.util.Scanner;

public class Substring {

	/*
	 * FUNÇÃO VERIFICA FIM
	 */
	public static boolean verificaFim(String str, String chave) {
		boolean fim = false;

		if (str.length() == chave.length()) {
			fim = true;

			for (int i=0; i < str.length(); i++) {
				if (str.charAt(i) != chave.charAt(i)) {
					fim = false;
					i = str.length();
				}
			}
		}

		return fim;
	}

	/*
	 * FUNÇÃO VERIFICA FIM BASE
	 */
	public static boolean verificaFim(String str) {
		return verificaFim(str, "FIM");
	}

	/*
	 * FUNÇÃO SUBSTRING MAIS LONGA
	 */
	public static int substringLong(String str) {
		int maior = 0;

		// começa o laço à partir do primeiro caractere da string e vai até o tamanho da string
		for (int i=0; i < str.length(); i++) {
			int cont = 0;
			
			// laço j em função de i e vai até o tamanho da string
			for (int j=i; j < str.length(); j++) {
				boolean rep = false;

				// laço k em função de i e vai até o tamanho j
				for (int k = i; k < j; k++) {

					// se for o caracter se repetir entra no if
					if (str.charAt(k) == str.charAt(j)) {
						rep = true;
						k = j;
					}
				}

				// se for repetido, o laço j termina 
				if (rep) {
					 j = str.length();
				}
				// se não, o contador aumenta e o laço j continua
				else {
					cont++; 

					if (cont > maior) {
						maior = cont;
					}
				}
			}
		}

		return maior;
	}

	/*
	 * FUNÇÃO MAIN
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();

		while (!verificaFim(str)) {
			System.out.println(substringLong(str));
			str = sc.next();
		}

		sc.close();
	}

}
