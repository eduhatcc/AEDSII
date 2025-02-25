import java.util.Scanner;

public class Inversao {
	
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

	public static String inversaoString(String str) {
		String newStr = "";
		int i = str.length() - 1;

		//concatena a string inversa na nova string
		while (i >= 0) {
			newStr += str.charAt(i);
			i--;
		}

		return newStr;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine().trim();

		while (!verificaFim(str, "FIM")) {

			System.out.println(inversaoString(str));
			str = sc.nextLine().trim();
		}

		sc.close();
	}

}

