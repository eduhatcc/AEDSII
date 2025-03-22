import java.util.Scanner;

public class TimeDuendes {


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		sc.nextLine();
		
		String nome[] = new String[n];
		int idade[] = new int[n];

		for (int i=0; i < n; i++) {
			nome[i] = sc.next();
			idade[i] = sc.nextInt();	
		}

		String lider[] = new String[n/2];
		String entregador[] = new String[n/2];
		String piloto = new String[n/2];

		int maior = idade[0];
		int idadeTemp = 0;
		String nomeTemp = "";

		
		for (int i=0; i < n/2; i++) {
			for (int j=1; j < n; i++) {
				if (idade[j] > maior) {
					maior = idade[j];
				}	
			}
			lider[i] = nome[maior];


		}

		int cont = 0;

		for (int i=1; i <= n; i++) {
			if (i % 3 == 0) {
				cont++;
				System.out.printf("%nTime %d%n", cont);
			}
			

		}

		sc.close();
	}
}
