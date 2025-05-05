import java.util.*;

public class Main {
	
	public static void swap(int[] array, int i, int j, Estatisticas stats) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
		stats.movimentacoes += 3;
	}

	public static void testarAlgoritmo(String nome, int[] original) {
		int[] array = original.clone();
		Estatisticas stats = new Estatisticas();
		long inicio = System.currentTimeMillis();

		switch (nome) {
			case "selection": SelectionSort.selectionSort(array, stats);
					  break;
			case "insertion": InsertionSort.insertionSort(array, stats);
					  break;
			case "bubble": BubbleSort.bubbleSort(array, stats);
				       break;
			case "quick": QuickSort.quicksort(array, stats);
				      break;
		}

		long fim = System.currentTimeMillis();
		System.out.println("Algortimo: "+ nome);
		System.out.println("Tempo :" + (fim - inicio) + " ms");
		System.out.println("Comparações: " + stats.comparacoes);
		System.out.println("Movimentações: " + stats.movimentacoes);
		System.out.println("--------------------------\n\n");
	}

	public static int[] gerarArray(int tam) {
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

			testarAlgoritmo("selection", original);
			testarAlgoritmo("insertion", original);
			testarAlgoritmo("bubble", original);
			testarAlgoritmo("quick", original);
		}
	}
}
