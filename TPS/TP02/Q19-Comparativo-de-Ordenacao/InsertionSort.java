public class InsertionSort extends Main {

	public static void insertionSort(int[] array, Estatisticas stats) {
		for (int i = 1; i < array.length; i++) {
			int tmp = array[i]; stats.movimentacoes++;
			int j = i-1;

			while ((j >= 0) && (array[j] > tmp)) {
				stats.comparacoes++;
				array[j+1] = array[j];
				stats.movimentacoes++;
				j--;
			}
			array[j+1] = tmp; stats.movimentacoes++;
		}
	}
}
