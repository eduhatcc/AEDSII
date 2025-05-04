public class SelectionSort {



	public static void testarAlgoritmo(String nome, int[] original) {
		int[] array = original.clone();
		Estatisticas stats = new Estatisticas();
		long inicio = System.currentTimeMillis();

		switch (nome) {
			case "selection": selectionSort(array, stats);
					  break;
			case "insertion": insertionSort(array, stats);
					  break;
			case "bubble": bubbleSort(array, stats);
				       break;
			case "quick": quicksort(array, stats);
				      break;
		}

		long fim = System.currentTimeMillis();
		System.out.println("Algortimo: "+ nome);
		System.out.println("Tempo :" + (fim - inicio) + " ms");
		System.out.println("Comparações: " + stats.comparacoes);
		System.out.println("Movimentações: " + stats.movimentacoes);
	}

	public static int[] gerarVetor(int tam) {
		Random rand = new Random();
		int[] array = new int[tam];

		for (int i = 0; i < tam; i++) {
			array[i] = rand.nextInt(1000000);
		}

		return array;
	}

	public static void main(String[] args) {
		int[] tamanho = {100, 1000, 10000, 100000};

		for (int tam : tamanho) {
			System.out.println("Tamanho do array: " + tam);
			int[] original = gerarArray(tam);

			testarAlgoritmo("selectin", original);
			testarAlgoritmo("insertion", original);
			testarAlgoritmo("bubble", original);
			testarAlgoritmo("quick", original);
		}
	}
}
