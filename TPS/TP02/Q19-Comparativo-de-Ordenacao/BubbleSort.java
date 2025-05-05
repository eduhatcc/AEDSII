public class BubbleSort extends Main {

	public static void bubbleSort(int[] array, Estatisticas stats) {
		for (int i = 0; i < array.length; i++) {
			int menor = i;

			for (int j = i+1; j < array.length; j++) {
				stats.comparacoes++;
				if (array[j] < array[menor]) {
					swap(array, j, menor, stats);
					menor = j;
				}
			}
		}
	}
}
