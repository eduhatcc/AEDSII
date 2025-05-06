import java.util.Scanner;

class Levantamento {
	private String nome;
	private int peso;
	private Levantamento prox;

	public Levantamento() {
		setNome(null);
		setPeso(0);
	}

	public Levantamento(String nome, int peso) {
		setNome(nome);
		setPeso(peso);
		setProx(null);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return this.nome;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}
	public int getPeso() {
		return this.peso;
	}

	public void setProx(Levantamento prox) {
		this.prox = prox;
	}
	public Levantamento getProx() {
		return this.prox;
	}
} 

class ListaFlexivel {
	private Levantamento inicio;
	private Levantamento ultimo;

	public ListaFlexivel() {
		this.inicio = new Levantamento();
		ultimo = inicio;
	}

	public void inserirInicio(String nome, int peso) {
		Levantamento tmp = new Levantamento(nome, peso);
		tmp.setProx(inicio.getProx());
		inicio.setProx(tmp);

		if (inicio == ultimo) {
			ultimo = tmp;
		}
		tmp = null;
	}

	public void inserirFim(String nome, int peso) {
		Levantamento novo = new Levantamento(nome, peso);
		ultimo.setProx(novo);
		ultimo = novo;
	}

	public void swap(Levantamento i, Levantamento j) {
		// Armazena temporáriamente
		String tmpNome = i.getNome();
		int tmpPeso = i.getPeso();
		// Set para i
		i.setNome(j.getNome());
		i.setPeso(j.getPeso());
		// Set para j
		j.setNome(tmpNome);
		j.setPeso(tmpPeso);
	}

	public void ordenar() {
		for (Levantamento i = inicio.getProx(); i != null; i = i.getProx()) {
			Levantamento maior = i;

			for (Levantamento j = i.getProx(); j != null; j = j.getProx()) {
				if (j.getPeso() > maior.getPeso()) {
					maior = j;
				}
				else if (j.getPeso() == maior.getPeso()){
					if (j.getNome().compareToIgnoreCase(maior.getNome()) < 0) {
						maior = j;
					}
				}
			}
			swap(i, maior);
		}
	}

	public void mostrar() {
		Levantamento i = inicio.getProx();

		while (i != null) {
			System.out.println(i.getNome() + " " + i.getPeso());
			i = i.getProx();
		}
	}
}

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = 0;
		do {
			n = sc.nextInt();
		} while (n <= 0 || n > 100);

		sc.nextLine();

		ListaFlexivel lista = new ListaFlexivel();
		String nome;
		do {
			nome = sc.next();
		} while (nome.length() >= 50);

		int peso;
		do {
			peso = sc.nextInt();
		} while (peso < 1 || peso > 500);

		sc.nextLine();
		lista.inserirInicio(nome, peso);

		for (int i = 1; i < n; i++) {
			do {
				nome = sc.next();
			} while (nome.length() >= 50);

			do {
				peso = sc.nextInt();
			} while (peso < 1 || peso > 500);

			sc.nextLine();
			lista.inserirFim(nome, peso);
		}

		lista.ordenar();

		lista.mostrar();

		sc.close();
	}
}
