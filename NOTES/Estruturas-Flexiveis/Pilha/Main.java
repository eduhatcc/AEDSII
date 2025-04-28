public class Main {
	public static void main(String[] args) {
		Pilha pilha = new Pilha();

		pilha.inserir(10);
		pilha.inserir(5);
		pilha.inserir(35);

		pilha.mostrar();

		System.out.println("Soma iterativa: " + pilha.somar());
		System.out.println("Soma recursiva: " + pilha.somarRecursivo());

		try {
			int removido = pilha.remover();
			System.out.println("Elemento removido: " + removido);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		pilha.mostrar();
	}
}
