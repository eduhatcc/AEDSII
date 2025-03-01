import java.util.Scanner;

public class Contagem {

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
	 * FUNÇÃO CONTAGEM DE PALAVRAS
	 */
	public static int contagemPalavras(String str) {
		int qt = 1;
		char comp = ' ';

		for (int i=0; i < str.length(); i++) {
			// comparação de char
			if (str.charAt(i) == comp) {
				qt++;
			}
		}

		return qt;
	}

	/*
	 * FUNÇÃO MAIN
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String str = sc.nextLine().trim();

		while (!verificaFim(str)) {
			if (str.charAt(0) != ' ') {
				System.out.println(contagemPalavras(str));
			}
			else {
				System.out.println("Digite uma palavra válida! Tente novamente.");
			}
			str = sc.nextLine().trim();
		}
	
		sc.close();
	}

}
