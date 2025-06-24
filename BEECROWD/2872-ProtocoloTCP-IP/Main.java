import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String word[] = new String[100];
		int i = 0;
		word[i] = sc.nextLine();

		while (!word[i].equals("0")) {
			while (!word[i].equals("0")) {
				i++;
				word[i] = sc.nextLine();
				
			}

			for (int j = 1; j < i; j++) {
				int menor = j;
				for (int k = j+1; k < i; k++) {
					if (word[k].compareToIgnoreCase(word[menor]) < 0) {
						menor = k;
					}
				}

				String tmp = word[j];
				word[j] = word[menor];
				word[menor] = tmp;
			}

			for (int j = 1; j < i; j++) {
				System.out.println(word[j]);
			}
			System.out.println();

			i = 0;
			word[i] = sc.nextLine();
		}

		sc.close();
	}
}
