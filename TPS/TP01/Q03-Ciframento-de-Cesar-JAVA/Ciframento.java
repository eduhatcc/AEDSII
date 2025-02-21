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

		for (int i=0; i < str.length(); i++){
			cifra += (char) (str.charAt(i) + 3);
		}

		return cifra;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String str;
		str = sc.nextLine().trim();

		while (!verificaFim(str, "FIM")) {
			System.out.println(textbf(str));
			str = sc.nextLine().trim();	
		}

		sc.close();
	}
}
