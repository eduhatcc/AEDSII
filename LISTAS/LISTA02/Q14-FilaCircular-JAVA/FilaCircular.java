import java.util.Scanner;

public class FilaCircular {
	// atributos
	int max;
	int array[];
	int n;
	int p;
	int u;

	/*
	 * CONSTRUTOR
	 */
	public FilaCircular(int max) {
		this.max = (max+1);
		array = new int[this.max];
		n = 0;
		p = 0;
		u = 0;
	}
	/*
	 * FUNÇÃO ENFILEIRAR
	 */
	public void enfileirar(int elem) throws Exception {
		if ((n+1) % array.length == p)
			throw new Exception("Fila cheia!");
		if (n == p) {
			array[0] = elem;
			n++;
		}
		else {
			array[n] = elem;
			n = (n+1) % array.length;
			u = (u+1) % array.length;
		}
	}

	/*
	 *FUNÇÃO DESENFILEIRAR
	 */
	public int desenfileirar() throws Exception {
		if (n == p) 
			throw new Exception("Fila vazia!");
		
		int resp = array[n-1];
		n--;
		return resp;
	}

	/*
	 * FUNÇÃO MOSTRAR FILA
	 */
	public void mostrar() throws Exception {
		if (n == p) 
			throw new Exception("Fila vazia!");

		System.out.printf("%n");
		for (int i=0; i < n; i++) {
			System.out.printf("%d ", array[i]);
		}
		System.out.printf("%n");
	}

	public void mostrarInvertido() throws Exception {
		if (n == p) {
			throw new Exception("Fila vazia!");
		}

		System.out.println();
		for (int i = u; i >= p; i--) {
			System.out.printf("%d ", array[i]);
		}
		System.out.println();
	}

	/*
	 * FUNÇÃO PESQUISAR
	 */
	public boolean pesquisar(int elem) throws Exception {
		if (n == p)
			throw new Exception("Fila vazia!");

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

		System.out.printf("Digite o tamanho da fila: ");
		do {
			max = sc.nextInt();
			if (max < 0)
				System.out.printf("%nERRO! Digite um número inteiro maior que 0: ");
		} while (max < 0);

		FilaCircular fila = new FilaCircular(max);

		boolean fim = false;
		while (!fim) {
			System.out.printf("%n!===== MENU =====!%n");
			System.out.println("1 - Enfileirar");
			System.out.println("2 - Desenfileirar");
			System.out.println("3 - Mostrar");
			System.out.println("4 - Mostrar invertido");
			System.out.println("5 - Pesquisar Elemento");
			System.out.println("0 - Sair");
			
			System.out.printf("Sua escolha [0 a 5]: ");
			do {
				opcao = sc.nextInt();
				if (opcao < 0 || opcao > 4) 
					System.out.printf("%nErro! Opção inválida! Tente novamente: ");
			} while (opcao < 0 || opcao > 4);

			switch (opcao) {
				case 0: //sair
					fim = true;
					break;
				case 1: 
					try {
						System.out.printf("%nDigite um número: ");
						elem = sc.nextInt();
						fila.enfileirar(elem);
					}
					catch (Exception e) {
						System.out.printf("%nErro! %s%n", e.getMessage());
					}
					break;
				case 2: // Desenfileirar
					try {
						System.out.printf("%nNúmero %d removido!%n", fila.desenfileirar());
					}
					catch (Exception e) {
						System.out.printf("%nErro! %s%n", e.getMessage());
					}
					break;
				case 3: // Mostrar
					try {
						fila.mostrar();
					}
					catch (Exception e) {
						System.out.printf("%nErro! %s%n", e.getMessage());
					}
					break;
				case 4: // Mostrar invertido
					try {
						fila.mostrarInvertido();
					}
					catch (Exception e) {
						System.out.printf("%nErro! %s%n", e.getMessage());
					}
					break;
				case 5: // Pesquisar Elemento
					try {
						System.out.printf("%nDigite um número: ");
						elem = sc.nextInt();
						if (fila.pesquisar(elem)) {
							System.out.printf("%nNúmero %d encontrado!%n", elem);
						}
						else {
							System.out.printf("%nNúmero %d não encontrado!%n", elem);
						}
					}
					catch (Exception e) {
						System.out.printf("%nErro! %s%n", e.getMessage());
					}
					break;
				default: // caso base
					System.out.printf("%nErro! Algo inesperado aconteceu!%n");
					break;
			} // fim switch case
		} // fim while

		sc.close();
	}
}
