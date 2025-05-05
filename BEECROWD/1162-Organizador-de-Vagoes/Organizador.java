import java.util.Scanner;

public class Organizador {

	public static void swap(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	public static int bubblesort(int[] array) {
		int swaps = 0;

		for (int i = 0; i < array.length; i++) {
			int menor = i;

			for (int j = i+1; j < array.length; j++) {
				if (array[j] < array[menor]) {
					swap(array, i, j);
					swaps++;
					menor = j;
				}
			}
		}

		return swaps;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		for (int i = 0; i < n; i++) {
			int qt = sc.nextInt();
			int[] array = new int[qt];
			// ler entradas para o array
			for (int j = 0; j < qt; j++) {
				array[j] = sc.nextInt();
			}

			int swaps = bubblesort(array);

			System.out.println("Optimal train swapping takes " + swaps + " swaps.");
		}

		sc.close();
	}
}
