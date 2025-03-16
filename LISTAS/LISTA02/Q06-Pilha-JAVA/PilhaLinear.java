import java.util.Scanner;

public class PilhaLinear {
	int max;
	int array[];
	int n;

	/*
	 * FUNÇÃO CONSTRUTORA
	 */
	public PilhaLinear(int max) {
		this.max = max;
		array = new int[max];
		n = 0;
	}

	/*
	 * FUNÇÃO EMPILHAR
	 */
	public void empilhar(int elem) {
		if (n == max) {
			System.out.printf("%nERRO! Pilha cheia!%n");
		}
		else {
			array[n] = elem;
			n++;
		}
	}

	/*
	 * FUNÇÃO DESEMPILHAR
	 */
	public int desempilhar() throws Exception {
		if (n == 0) 
			throw new Exception("Pilha vazia!");
		
		int resp = array[n-1];
		n--;
		return resp;
	}

	/*
	 * FUNÇÃO MOSTRAR
	 */
	public void mostrar() throws Exception {
		if (n == 0) 
			throw new Exception("Pilha vazia!");

		System.out.printf("%n");
		for (int i=0; i < n; i++) {
			System.out.printf("%d ", array[i]);
		}
		System.out.printf("%n");
	}

	/*
	 * FUNÇÃO PESQUISAR
	 */
	public boolean pesquisar(int elem) throws Exception {
		if (n == 0)
			throw new Exception("Pilha vazia!");

		boolean encontrou = false;
		for (int i=0; i < n; i++) {
			if (array[i] == elem) {
				encontrou = true;
				i = n;
			}
		}

		return encontrou;
	}
	/*
	 * FUNÇÃO MAIN
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int max = 0,
		    opcao = 0,
		    elem = 0;

		System.out.printf("%nDigite o tamanho da sua pilha: ");
		do {
			max = sc.nextInt();
			if (max <= 0) {
				System.out.printf("%nERRO! Digite um número inteiro maior que 0: ");
			}
		} while (max <= 0);

		PilhaLinear pilha = new PilhaLinear(max);

		boolean fim = false;

		while (!fim) {
			System.out.printf("%n!===== MENU ======!%n");
			System.out.println("1 - Empilhar");
			System.out.println("2 - Desempilhar");
			System.out.println("3 - Mostrar Pilha");
			System.out.println("4 - Pesquisar Elemento Pilha");
			System.out.println("0 - Sair");

			do {
				System.out.printf("Escolha sua opção [0 a 4]: ");
				opcao = sc.nextInt();
				if (opcao < 0 || opcao > 4) {
					System.out.printf("%nERRO! Opção inválida!%n");
				}
			} while (opcao < 0 || opcao > 4);

			switch (opcao) {
				case 0: // Sair
					fim = true;
					break;
				case 1: // Empilhar
					System.out.printf("%nDigite o número: ");
					elem = sc.nextInt();
					pilha.empilhar(elem);
					break;
				case 2: // Desempilhar
					try {
						System.out.printf("%nNúmero %d removido!%n", pilha.desempilhar());
					}
					catch (Exception e) {
						System.out.printf("%nErro! %s%n", e.getMessage());
					}
					break;
				case 3: // Mostrar Pilha
					try {
						pilha.mostrar();
					}
					catch (Exception e) {
						System.out.printf("%nErro! %s%n", e.getMessage());
					}
					break;
				case 4: // Pesquisar
					try {
						System.out.printf("%nDigite o número: ");
						elem = sc.nextInt();
						if (pilha.pesquisar(elem)) {
							System.out.printf("Número %d encontrado!", elem);
						}
						else {
							System.out.printf("%nNúmero %d não encontrado!%n", elem);
						}
					}
					catch (Exception e) {
						System.out.printf("%nErro! %s%n", e.getMessage());
					}
					break;
			} // fim switch case
		} // fim while
		sc.close();
	}
}
