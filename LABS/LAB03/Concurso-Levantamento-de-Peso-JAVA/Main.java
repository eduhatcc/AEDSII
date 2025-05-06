import java.util.Scanner;

public class Main {

	public static void swap(String[] nome, int[] peso, int i, int j) {
		String tmpNome = nome[i];
		nome[i] = nome[j];
		nome[j] = tmpNome;

		int tmpPeso = peso[i];
		peso[i] = peso[j];
		peso[j] = tmpPeso;
	}

	public static void ordenar(String[] nome, int[] peso, int n) {
		for (int i = 0; i < n; i++) {
			int menor = i;

			for (int j = i+1; j < n; j++) {
				if (peso[j] > peso[menor]) {
					menor = j;
				}
				else if (peso[j] == peso[menor]) {
					if (nome[j].compareToIgnoreCase(nome[menor]) < 0) {
						menor = j;
					}
				}
			}
			swap(nome, peso, i, menor);
		}
	}

	public static void mostrar(String[] nome, int[] peso, int n) {
		for (int i = 0; i < n; i++) {
			System.out.println(nome[i] + " " + peso[i]);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = 0;

		do {
			n = sc.nextInt();
		} while (n <= 0 || n > 100);

		sc.nextLine();

		String[] nome = new String[n];
		int[] peso = new int[n];

		for (int i = 0; i < n; i++) {
			do {
				nome[i] = sc.next();
			} while (nome[i].length() > 50);

			do {
				peso[i] = sc.nextInt();
			} while (peso[i] < 1 || peso[i] > 500);
			sc.nextLine();
		}

		ordenar(nome, peso, n);

		mostrar(nome, peso, n);

		sc.close();
	}
}
