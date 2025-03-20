import java.util.Scanner;

public class NoelsLabels {

	public static void lerEntradas() {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		sc.nextLine();
		String idioma[] = new String[n];
		String mensagem[] = new String[n];
		String temp;

		// ler entradas de idiomas e mensagem
		for (int i = 0; i < n; i++) {
			temp = sc.nextLine();
			idioma[i] = temp;
			temp = sc.nextLine();
			mensagem[i] = temp;
		}

		int pessoas = sc.nextInt();
		sc.nextLine();

		String nome[] = new String[pessoas];
		String idiomaPessoa[] = new String[pessoas];

		// ler entradas de nomes e idiomas da pessoa
		for (int i = 0; i < pessoas; i++) {
			nome[i] = sc.nextLine();
			idiomaPessoa[i] = sc.nextLine();
		}

		// procurar idioma da pessoa e lançar a mensagem
		for (int i = 0; i < pessoas; i++) { // roda em função de pessoas
			for (int j = 0; j < n; j++) { // roda em função de n
				if (idiomaPessoa[i].equals(idioma[j])) { // verificação de os idiomas são iguais
					System.out.printf("%n%s%n", nome[i]);
					System.out.printf("%s%n", mensagem[j]);
					System.out.println();
					j = n;
				}
			}
		}

		sc.close();
	}

	public static void main(String[] args) {
		lerEntradas();
	}
}
