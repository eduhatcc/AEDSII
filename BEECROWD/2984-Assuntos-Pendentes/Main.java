import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String line = sc.nextLine();

		int esq = 0,
		    dir = 0;

		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == '(') {
				esq++;
			}
			else if (line.charAt(i) == ')') {
				dir++;
			}
		}

		if (esq == dir) {
			System.out.println("Partiu RU!");
		}
		else {
			int diferença = 0;
			if (esq > dir) {
				diferença = esq - dir;	
			}
			else {
				diferença = dir - esq;
			}
			System.out.println("Ainda temos " + diferença + " assunto(s) pendente(s)!");
		}

		sc.close();
	}
}
