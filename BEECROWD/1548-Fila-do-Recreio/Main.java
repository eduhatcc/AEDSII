import java.util.Scanner;

public class Main {

	public static void swap(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	public static void selectionSort(int reordenado[], int[] p, int m) {
		int maior = 0;
		for(int i = 0; i < m; i++) {
			maior = i;
			for (int j = i+1; j < m; j++) {
				if (reordenado[j] > reordenado[maior]) {
					maior = j;
				}
			}
			swap(reordenado, i, maior);
		}
	}

	public static int[] reordenar(int[] p, int m) {
		int[] reordenado = new int[m];
		for (int i = 0; i < m; i++) {
			reordenado[i] = p[i]; // Copia o array p
		}

		selectionSort(reordenado, p, m);

		return reordenado;
	}

	public static int qtNaoTrocou(int[] p1, int[] p2, int m) {
		int qt = 0;

		for (int i = 0; i < m; i++) {
			if (p1[i] == p2[i]) qt++;
		}

		return qt;
	} 

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = 0,
		    m = 0;
		int[] p1 = new int[1000];
		int[] p2 = new int[1000];

		n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			m = sc.nextInt();
			for (int j = 0; j < m; j++) {
				p1[j] = sc.nextInt();
			}

			p2 = reordenar(p1, m);

			System.out.println(qtNaoTrocou(p1, p2, m));
		}

		sc.close();
	}
}
