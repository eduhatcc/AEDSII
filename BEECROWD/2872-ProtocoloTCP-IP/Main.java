import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String word = new String();
		int i = 0;
		word[i] = sc.nextLine();

		while (word[i] == 1) {

			while (word[i] != 0) {
				word[i] = sc.nextLine();
				i++;
			}
			i--;

			for (int j = 0; j < i; j++) {
				int maior = j;
				for (int k = j+1; k < i; k++) {
					if (word[j].comparetoIgnoreCase(word[maior]) > 0)
				}
			}
		}

		sc.close();
	}
}
