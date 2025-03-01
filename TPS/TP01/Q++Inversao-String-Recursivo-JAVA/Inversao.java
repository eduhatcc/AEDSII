import java.util.Scanner;

public class Inversao {
	
	public static boolean verificaFim(String str, String chave, int i) {
		boolean fim = true;
		
		if (i < str.length()) {
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

	public static String inversaoString(String str, int i) {
		String newStr = "";

		//concatena a string inversa na nova string
		if (i >= 0) {
			newStr += str.charAt(i);
			newStr += inversaoString(str, i-1);
		}

		return newStr;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine().trim();

		while (!verificaFim(str, "FIM", 0)) {

			System.out.println(inversaoString(str, (str.length()-1)));
			str = sc.nextLine().trim();
		}

		sc.close();
	}

}

