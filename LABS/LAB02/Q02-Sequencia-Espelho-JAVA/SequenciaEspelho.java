import java.util.Scanner;

public class SequenciaEspelho {

	public static void espelho(int inicio, int fim) {
		String seq = "";

		for (int i = inicio; i <= fim; i++) {
			seq += i;		
		}

		System.out.print(seq);

		for (int i = seq.length()-1; i >= 0; i--) {
			System.out.print(seq.charAt(i));
		}

		System.out.println();
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int inicio, fim;
		//int seq[] = new int[1000];
		
		while (sc.hasNext()) {
			inicio = sc.nextInt();
			fim = sc.nextInt();
			espelho(inicio, fim);
		}

		sc.close();
	}
}
