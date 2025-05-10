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

	public static int pesquisaBinaria(int[] array, int esq, int dir, int Q, int src, int i) {
		int meio = (esq+dir) /2,
		    resp = 0;

		if (esq <= dir && i < Q) {
			if (array[meio] == src) {
				resp = meio+1;
			}
			else if (array[meio] < src) {
				resp = pesquisaBinaria(array, meio+1, dir, Q, src, i++);
			}
			else {
				resp = pesquisaBinaria(array, esq, meio-1, Q, src, i++);
			}
		}

		return resp;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int Q = sc.nextInt();
		int i = 0,
		    caso = 1,
		    resp = 0;
		int[] array = new int[65];
		int[] src = new int[65];
		
		while (N > 0 && Q > 0) {
			while (i < N) {
				array[i] = sc.nextInt();
				i++;
			}

			ordenaArray(array, N);
			i = 0;

			while (i < Q) {
				src[i] = sc.nextInt();
				i++;
			}

			i = 0;
			System.out.println("CASE# " + caso + ":");
			while (i < Q) {
				resp = pesquisaBinaria(array, 0, N, Q, src[i], 0);

				if (resp == 0) {
					System.out.println(src[i] + " not found");
				}
				else {
					System.out.println(src[i] + " found at " + resp);
				}
				i++;
			}
			caso++; i = 0;
			N = sc.nextInt();
			Q = sc.nextInt();
		}

		sc.close();
	}
}
