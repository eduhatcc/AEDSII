import java.util.Scanner;

public class Anagrama {

	public static boolean ehAnagrama(String str) {
		boolean eh = false;

		for (int i=0; i < str.length(); i++) {
			for (int j=i; j < str.length(); j++) {
				if (str.charAt(i) == str.charAt(j)) {
					eh = true;
					j = str.length();
				}
				else if (j == (str.length() -1) {
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
