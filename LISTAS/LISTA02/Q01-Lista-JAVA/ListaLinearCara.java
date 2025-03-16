import java.util.Scanner;

public class ListaLinearCara {
	// atributos da classe
	int max;
	int array[];
	int n;

	/*
	 * FUNÇÃO CONSTRUTORA
	 */
	public ListaLinearCara(int max) {
		this.max = max;
		array = new int[max];
		n = 0;
	}

	/*
	 * FUNÇÃO II - INSERIR INICIO
	 */
	public void inserirInicio(int elem) {
		if (n == max) {
			System.out.println("ERRO! Lista cheia!");
		}
		else {
			for (int i = n; i > 0; i--) {
				array[i] = array[i-1];
			}
			array[0] = elem;
			n++;
		}
	}
	
	/*
	 * FUNÇÃO IF - INSERIR FIM
	 */
	public void inserirFim(int elem) {
		if (n == max) {
			System.out.println("ERRO! Lista cheia!");
		}
		else {
			array[n++] = elem;
		}
	}

	/*
	 * FUNÇÃO INSERIR NA POSIÇÃO DESEJADA
	 */
	public void inserir(int elem, int pos) {
		if (n == max ) {
			System.out.println("ERRO! Lista cheia!");
		}
		else if (pos > n) {
			System.out.println("ERRO! Posição inexistente");
		}
		else {
			for (int i = n; i > pos; i--) {
				array[i] = array[i-1];
			}
			array[pos] = elem;
			n++;
		}
	}

	/*
	 * FUNÇÃO RI - REMOVER INICIO
	 */
	public int removerInicio() {
		int resp = array[0];
		for (int i = 0; i < n; i++) {
			array[i] = array[i+1];
		}	
		n--;

		return resp;
	}

	/*
	 * FUNÇÃO RF - REMOVER FIM
	 */
	public int removerFim() {
		int resp = array[n];
		n--;

		return resp;
	}

	/*
	 * FUNÇÃO REMOVER NA POSIÇÃO DESEJADA
	 */
	public int remover(int pos) {
		int resp = 0;
		if (pos < 0 || pos > n) {
			System.out.println("ERRO! Posição inválida!");
		}
		else {
			resp = array[pos];
			for (int i = pos; i < n; i++) {
				array[i] = array[i+1];
			}
			n--;
		}
		return resp;
	}

	/*
	 * FUNÇÃO MOSTRAR OS ELEMENTOS DA LISTA
	 */
	public void mostrar() {
		System.out.printf("%n");
		for (int i = 0; i < n; i++) {
			System.out.printf(" %d", array[i]);
		}
		System.out.printf("%n");
	}

	/*
	 * FUNÇÃO PESQUISAR ELEMENTO DA LISTA
	 */
	public boolean pesquisar(int elem) {
		boolean encontrou = false;

		for (int i = 0; i < n; i++) {
			if (array[i] == elem) {
				encontrou = true;
				i = n;
			}
		}

		return encontrou;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TAM = 0;
		do {
			System.out.printf("Digite o tamanho desejado para a lista: ");
			TAM = sc.nextInt();

			if (TAM <= 0) {
				System.out.println("Erro! Digite um número inteiro maior que 0");
			}
		} while (TAM <= 0);

		ListaLinear lista = new ListaLinearCara(TAM);

		int elem = 0,
		    pos = 0,
		    num = 0,
		    opcao = 0;
		boolean fim = false;

		while (!fim) {
			System.out.printf("%n!===== MENU =====!%n");
			System.out.println("1 - Inserir Início");
			System.out.println("2 - Inserir Fim");
			System.out.println("3 - Inserir Posição Desejada");
			System.out.println("4 - Remover Início");
			System.out.println("5 - Remover Fim");
			System.out.println("6 - Remover Posição Desejada");
			System.out.println("7 - Mostrar Lista");
			System.out.println("8 - Pesquisar Número");
			System.out.println("0 - Sair");

			do {
				System.out.printf("Sua escolha [0 a 8]: "); 
				opcao = sc.nextInt();
				if (opcao < 0 || opcao > 8) {
					System.out.println("Erro! Tente novamente");
				}
			} while (opcao < 0 || opcao > 8);

			switch (opcao) {
				case 0: // Sair
					fim = true;
					break;
				case 1: // II - Inserir Início
					System.out.printf("%nDigite o numero: ");
					elem = sc.nextInt();
					lista.inserirInicio(elem);
					break;
				case 2: // IF - Inserir Fim
					System.out.printf("%nDigite o numero: ");
					elem = sc.nextInt();
					lista.inserirFim(elem); 
					break;
				case 3: // I - Inserir Posição Desejada
					System.out.printf("%nDigite o número e a posição desejada [posição de: 1 a %d]", TAM);
					elem = sc.nextInt();
					do {
						pos = sc.nextInt();
						if (pos < 0 || pos > TAM) {
							System.out.printf("%nErro! Posição incompatível! Digite um numero maior que 0 e menor que %d: ");
						}
					} while (pos < 0 || pos > TAM);
					lista.inserir(elem, pos-1);
					break;
				case 4: // RI - Remover Início
					num = lista.removerInicio();
					System.out.printf("%nNúmero %d removido!%n", num);	
					break;
				case 5:  // RF - Remover Fim
					num = lista.removerFim();
					System.out.printf("%nNúmero %d removido!%n", num);
					break;
				case 6: // R - Remover Posição Desejada
					System.out.printf("%nDigite a posição desejada para remover [1 a %d]: ", TAM);
					do {
						pos = sc.nextInt();
						if (pos < 0 || pos > TAM) {
							System.out.printf("%nErro! Posição incompatível! Digite um numero maior que 0 e menor que %d: ", TAM);
						}
					} while (pos < 0 || pos > TAM);
					num = lista.remover(pos-1);
					System.out.printf("%nNúmero %d removido!%n", num);					
					break;
				case 7:  // Mostrar Lista
					lista.mostrar();
					break;
				case 8:  // Pesquisar Número 
					System.out.printf("%nDigite o número a ser pesquisado: ");
					elem = sc.nextInt();
					if (lista.pesquisar(elem)) {
						System.out.printf("%nNúmero %d encontrado!%n", elem);
					}
					else {
						System.out.printf("%nNúmero %d não encontrado!%n", elem);
					}
					break;
				default : // Caso base para erro 
					System.out.println("ERRO! Tente novamente!");
			}
		}

		sc.close();
	}
}
