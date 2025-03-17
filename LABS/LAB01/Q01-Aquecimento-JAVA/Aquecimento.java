import java.util.Scanner;

public class Aquecimento {

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

	public static boolean verificaFim(String str) {
		return verificaFim(str, "FIM");
	}

	public static int qtMaiusculo(String str) {
		int qt = 0;
		for (int i=0; i < str.length(); i++) {
			if (str.charAt(i) >= 65 && str.charAt(i) <= 90) {
				qt++;
			}
		}

		return qt;
	}

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
