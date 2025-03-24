import java.util.Scanner;

public class Ordenacao {

	public static void ordenar(String str) {
		char newStr[] = new char[255];
		int pos = 1,
		    maior = 0,
		    cont = 0,
		    j = 0;

		for (int i=0; i < str.length(); i++) {
			int cont = 0;
			while (str.charAt(j) != ' ';
				cont++;
				j++;		
			}
			maior = cont
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		sc.nextLine();

		for (int i=0; i < n; i++) {
			String str = sc.nextLine();
			ordenar(str);
		}

		sc.close();
	}
}
