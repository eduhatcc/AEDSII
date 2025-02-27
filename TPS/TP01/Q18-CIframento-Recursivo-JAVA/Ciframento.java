import java.util.Scanner;

public class Ciframento {

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

	public static String textbf(String str) {
		String cifra = "";

		for (int i=0; i < str.length(); i++) {

			//verifica se a letra ou numero não é um caracter especial
			if(str.charAt(i) < 127) { 

				//concatenar os chars
				cifra += (char) (str.charAt(i) + 3);
			} 
			else {
				//se for um caracter especial ele printa ele mesmo
				cifra += str.charAt(i);
			}
		}

		return new String(cifra);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String str;
		str = sc.nextLine();

		while (!verificaFim(str, "FIM")) {
			System.out.println(textbf(str));
			str = sc.nextLine();	
		}

		sc.close();
	}
}
