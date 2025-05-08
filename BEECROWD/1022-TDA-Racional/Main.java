import java.util.Scanner;

public class Main {

	public static void valorRacional(int N1, int D1, String c, String operador, int N2, int D2) {
		int totalN, 
		    totalD,
		    res = N1*D1;
		char soma = '+';
		char subtracao = '-';
		char multiplicacao = '*';
		char divisao = '/';

		if (operador.charAt(0) == soma) {
			totalN = (N1*D2 + N2*D1);
			totalD = (D1*D2);
			System.out.println(totalN + c + totalD + " = " + (totalN/res) + c + (totalD/res));
		}
		else if (operador.charAt(0) == subtracao) {
			totalN = (N1*D2 - N2*D1);
			totalD = (D1*D2);
			System.out.println(totalN + c + totalD + " = " + (totalN/res) + c + (totalD/res));
		}
		else if (operador.charAt(0) == multiplicacao) {
			totalN = (N1*N2);
			totalD = (D1*D2);
			System.out.println(totalN + c + totalD + " = " + (totalN/res) + c + (totalD/res));
		}
		else if (operador.charAt(0) == divisao) {
			totalN = (N1*D2);
			totalD = (N2*D1);
			System.out.println(totalN + c + totalD + " = " + (totalN/res) + c + (totalD/res));
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int i = 0,
		    N1,
		    D1,
		    N2,
		    D2;
		String c,
		     operador;

		while(i < n) {
			N1 = sc.nextInt();
			c = sc.next();
			D1 = sc.nextInt();

			operador = sc.next();

			N2 = sc.nextInt();
			c = sc.next();
			D2 = sc.nextInt();
			sc.nextLine();

			valorRacional(N1, D1, c, operador, N2, D2);
			i++;
		}

		sc.close();
	}
}
