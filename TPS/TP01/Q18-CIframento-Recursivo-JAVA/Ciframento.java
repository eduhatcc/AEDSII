import java.util.Scanner;

public class Ciframento {

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

	public static String textbf(String str, int i) {
		String cifra = "";

		if (i < str.length()) {
			//verifica se a letra ou numero não é um caracter especial
			if(str.charAt(i) < 127) { 

				//concatenar os chars
				cifra += (char) (str.charAt(i) + 3);
				cifra += textbf(str, i+1);
			} 
			else {
				//se for um caracter especial ele printa ele mesmo
				cifra += str.charAt(i);
				cifra += textbf(str, i+1);
			}
		}

		return new String(cifra);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String str;
		str = sc.nextLine();

		while (!verificaFim(str, "FIM", 0)) {
			System.out.println(textbf(str, 0));
			str = sc.nextLine();	
		}

		sc.close();
	}
}
