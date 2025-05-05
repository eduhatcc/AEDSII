public class SelectionSort extends Main {

	public static void selectionSort(int[] array, Estatisticas stats) {
		for (int i = 0; i < array.length; i++) {
			int menor = array[i];
			
			for (int j = i+1; j < array.length; j++) {
				stats.comparacoes++;
				if (array[j] < menor) {
					menor = array[j];
				}
			}
			swap(array, i, menor, stats);
		}
	}
}
