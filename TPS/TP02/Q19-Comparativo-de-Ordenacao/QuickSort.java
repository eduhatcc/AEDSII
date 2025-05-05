public class QuickSort extends Main {

	/*
	 * FUNÇÃO RECURSIVA
	 */
	public static void quicksort(int[] array, int esq, int dir, Estatisticas stats) {
		int i = esq,
		    j = dir,
		    meio = array[(esq + dir) / 2];

		while (i <= j) {
			while (array[i] < meio) {
				i++; stats.comparacoes++;
			}
			
			while (array[j] > meio) {
				j--; stats.comparacoes++;
			}

			if (i <= j) {
				swap(array, i, j, stats);
				i++; j--;
			}
		}

		if (esq < j) quicksort(array, esq, j, stats);
		if (i < dir) quicksort(array, i, dir, stats);
	}

	/*
	 * FUNÇÃO BASE
	 */
	public static void quicksort(int[] array, Estatisticas stats) {
		quicksort(array, 0, (array.length -1), stats);
	}
}
