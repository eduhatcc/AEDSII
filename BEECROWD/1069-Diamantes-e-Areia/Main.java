import java.util.Scanner;

public class Main {
	
	public static int diamantes(String input) {
		int qt = 0,
		    esq = 0,
		    dir = 0;

		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '<') {
				esq++;
			}
			if (input.charAt(i) == '>') {
				dir++;
			}
		}

		while (esq > 0 && dir > 0) {
			qt++;
			esq--; dir--;
		}

		return qt;
	}

	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		sc.nextLine();
		for (int i = 0; i < n; i++) {
			String input = sc.nextLine();

			System.out.println(diamantes(input));
		}

		sc.close();
	}
}
