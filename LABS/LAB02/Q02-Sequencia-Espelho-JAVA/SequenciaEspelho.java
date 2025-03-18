import java.util.Scanner;

public class SequenciaEspelho {

	public static void espelho(int n1, int n2) {
		int k = 0,
		    n = 0,
		    cont = 0,
		    maior = 0,
		    menor = 0;
		int seq[] = new int[1000];
		int temp[] = new int[1000];

		if (n1 > n2) {
			maior = n1;
			menor = n2;	
		}
		else {
			maior = n2;
			menor = n1;
		}

		// ARMAZENA A SEQUÊNCIA E A STRING TEMPORÁRIA CRESCENTEMENTE
		for (int i = menor; i <= maior; i++) {
			seq[k] = i;
			temp[k] = i;
			k++; cont++;
		}

		// REPETIÇÃO PARA CONCATENAR A STRING TEMPORÁRIA DE FORMA INVERSA
		for (int j = cont-1; j >= 0; j--) {
			seq[k] = temp[j];
			k++;
		}

		System.out.printf("%n");
		// REPETIÇÃO PARA ESCREVER A SEQUÊNCIA ESPELHADA
		for (int i = 0; i < k; i++) {
			System.out.printf("%d", seq[i]);
		}
		System.out.printf("%n");
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n1, n2;
		//int seq[] = new int[1000];
		
		while (sc.hasNext()) {
			n1 = sc.nextInt();
			n2 = sc.nextInt();
			espelho(n1, n2);
		}

		sc.close();
	}
}
