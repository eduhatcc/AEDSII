import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TesteOrdenacao {

    /*
     * Classe para armazenar estatísticas de comparação e movimentação
     */
    public static class Estatisticas {
        public long comparacoes = 0;
        public long movimentacoes = 0;
    }

    /*
     * Função para trocar dois elementos em um array
     */
    public static void swap(int[] array, int i, int j, Estatisticas stats) {
        int tmp = array[i];
        array[i] = array[j];  
        array[j] = tmp;
        stats.movimentacoes += 3;
    }

    /*
     * Selection Sort
     */
    public static void selectionSort(int[] array, Estatisticas stats) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < n; j++) {
                stats.comparacoes++;
                if (array[j] < array[menor]) {
                    menor = j;
                }
            }
            swap(array, i, menor, stats);
        }
    }

    /*
     * Insertion Sort
     */
    public static void insertionSort(int[] array, Estatisticas stats) {
        for (int i = 1; i < array.length; i++) {
            int chave = array[i];
            stats.movimentacoes++;
            int j = i - 1;
            boolean parar = false;

            while (j >= 0 && !parar) {
                stats.comparacoes++;
                if (array[j] > chave) {
                    array[j + 1] = array[j];
                    stats.movimentacoes++;
                    j--;
                }
                else {
                    parar = true;
                }
            }
            array[j + 1] = chave;
            stats.movimentacoes++;
        }
    }

    /*
     * Bubble Sort
     */
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

    /*
	 * FUNÇÃO RECURSIVA
	 */
	private static void quicksort(int[] array, int esq, int dir, Estatisticas stats) {
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

    /*
     * Função para testar o algoritmo de ordenação
     */
    public static void testarAlgoritmo(String nome, int[] original, FileWriter writer) throws IOException {
        int[] array = original.clone();
        Estatisticas stats = new Estatisticas();
        long inicio = System.currentTimeMillis();
        
        switch (nome) {
            case "selection": selectionSort(array, stats); break;
            case "insertion": insertionSort(array, stats); break;
            case "bubble": bubbleSort(array, stats); break;
            case "quick": quicksort(array, stats); break;
        }
        
        long fim = System.currentTimeMillis();
        long tempo = fim - inicio;
        writer.write(String.format("%s, %d, %d, %d, %d\n",
        nome, array.length, tempo, stats.comparacoes, stats.movimentacoes));
    }
    
    /*
     * Função para gerar um array de inteiros aleatórios
     */
    public static int[] gerarArray(int tamanho) {
        Random rand = new Random();
        int[] array = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            array[i] = rand.nextInt(1000000);
        }
        return array;
    }

    /*
     * Função principal para executar os testes e gerar o arquivo CSV
     */
    public static void main(String[] args) {
        String[] algoritmos = {"selection", "insertion", "bubble", "quick"};
        int[] tamanhos = {100, 1000, 10000, 100000};

        try (FileWriter writer = new FileWriter("resultados.csv")) {
            writer.write("Algoritmo, Tamanho, Tempo(ms), Comparações, Movimentações\n");

            for (int tam : tamanhos) {
                int[] original = gerarArray(tam);

                for (String nome : algoritmos) {
                    System.out.println("Executando " + nome + " com vetor de tamanho " + tam);
                    testarAlgoritmo(nome, original, writer);
                }
            }
            System.out.println("Arquivo resultados.csv gerado com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

