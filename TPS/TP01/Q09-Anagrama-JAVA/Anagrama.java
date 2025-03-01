import java.util.Scanner;

public class Anagrama {

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

	public static boolean ehAnagrama(String str) {
		boolean eh = true;

		for (int i=0; i < str.length()/2; i++) {
			for (int j=i*2; j < str.length(); j++) {
				if (str.charAt(i) == str.charAt(j)) {
					j = str.length();
				}
				else if (j >= str.length() -1) {
					eh = false;
				}
			}
		}

		return eh;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();

		while (!verificaFim(str, "FIM")) {
			if (ehAnagrama(str)) {
				System.out.println("SIM");
			}
			else {
				System.out.println("NAO");
			}
			
			str = sc.nextLine();
		}

		sc.close();
	}
}
