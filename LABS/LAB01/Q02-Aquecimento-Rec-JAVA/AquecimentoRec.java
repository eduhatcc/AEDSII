import java.util.Scanner;

public class AquecimentoRec {

	/*
	 * FUNÇÃO VERIFICA FIM RECURSIVO
	 */
	public static boolean verificaFim(String str, String chave, int i) {
		boolean fim = true;

		if (i < chave.length()) {
			if (str.length() != chave.length()) {
				fim = false;
			} 
			else if (str.charAt(i) != chave.charAt(i)) {
				fim = false;
			} 
			else {
				fim = verificaFim(str, chave, i+1);
			}
		}

		return fim;
	}

	/*
	 * FUNÇÃO VERIFICA FIM BASE
	 */
	public static boolean verificaFim(String str) {
		return verificaFim(str, "FIM", 0);
	}

	/*
	 * FUNÇÃO QUANTIDADE MAIUSCULO RECURSIVO
	 */
	public static int qtMaiusculo(String str, int i) {
		int qt = 0;
		if (i < str.length()) {
			if (str.charAt(i) >= 65 && str.charAt(i) <= 90) {
				qt++;
			}
			qt += qtMaiusculo(str, i+1);
		}

		return qt;
	}

	/*
	 * FUNÇÃO QUANTIDADE MAIUSCULO BASE
	 */
	public static int qtMaiusculo(String str) {
		return qtMaiusculo(str, 0);
	}

	/*
	 * FUNÇÃO MAIN
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();

		while (!verificaFim(str)) {
			System.out.println(qtMaiusculo(str));
			str = sc.nextLine();
		}

		sc.close();
	}
}
