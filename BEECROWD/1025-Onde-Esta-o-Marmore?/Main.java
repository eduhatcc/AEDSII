import java.util.Scanner;

public class Main {
	public static void swap(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	public static void ordenaArray(int[] array, int N) {
		for (int i = 0; i < N; i++) {
			int menor = i;

			for (int j = i+1; j < N; j++) {
				if (array[j] < array[menor]) {
					menor = j;
				}
			}
			swap(array, i, menor);
		}
	}

	public static int pesquisaBinaria(int[] array, int esq, int dir, int Q) {
		int meio = (esq+dir) /2,
		    resp = 0;

		if (esq <= dir) {
			if (array[meio] == Q) {
				resp = meio+1;
			}
			else if (array[meio] < Q) {
				resp = pesquisaBinaria(array, meio+1, dir, Q);
			}
			else {
				resp = pesquisaBinaria(array, esq, meio-1, Q);
			}
		}

		return resp;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(),
		    Q = sc.nextInt(),
		    i = 0,
		    caso = 1,
		    resp = 0;
		int[] array = new int[65];
		
		while (n > 0 && Q > 0) {
			while (i < n) {
				array[i] = sc.nextInt();
				i++;
			}
			ordenaArray(array, N);
			i = 0;

			while (i < Q) {
				resp = pesquisaBinaria(array, N, Q, caso);

				if (resp == 0) {
					System.out.println(Q + " not found");
				}
				else {
					System.out.println(Q + " found at " + resp);
				}
			}
		}

		sc.close();
	}
}
