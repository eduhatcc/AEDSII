import java.util.Scanner;

public class Ciframento {

	/* 
	 * FUNÇÃO VERIFICAFIM RECURSIVA
	 */
	public static boolean verificaFim(String str, String chave, int i) {
		boolean fim = true;

		// condição de parada
		if (i < chave.length()) {

			//verifica se a str e a chave contém o mesmo tamanho
			if (str.length() == chave.length()) {

				// verifica se os caracteres são diferentes
				if (str.charAt(i) != chave.charAt(i)) {
					// se sim, retorna falso
					fim = false;
				}
				else {
					// se não, chama recursivamente
					fim = verificaFim(str, chave, i+1);
				}
			}

			// se não conter o mesmo tamanho retorna falso
			else {
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
	 * FUNÇÃO TEXTBF RECURSIVA
	 */
	public static String textbf(String str, int i) {
		String cifra = "";

		// condição de parada
		if (i < str.length()) {

			// verifica se a letra ou numero não é um caracter especial
			if(str.charAt(i) < 127) { 
				//concatenar os chars
				cifra += (char) (str.charAt(i) + 3);
				cifra += textbf(str, i+1);
			} 
			else {
				// se for um caracter especial ele printa ele mesmo
				cifra += str.charAt(i);
				cifra += textbf(str, i+1);
			}
		}

		return new String(cifra);
	}

	/* 
	 * FUNÇÃO TEXTBF BASE
	 */
	public static String textbf(String str) {
		return textbf(str, 0);
	}

	/* 
	 * FUNÇÃO MAIN
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String str;
		str = sc.nextLine();

		// chama a função verificaFim base
		while (!verificaFim(str)) {
			System.out.println(textbf(str));
			str = sc.nextLine();	
		}

		sc.close();
	}
}
