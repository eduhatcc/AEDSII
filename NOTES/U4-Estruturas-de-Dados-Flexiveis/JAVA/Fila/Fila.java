public class Fila {
	// ATRIBUTOS PRIVADOS
	private Celula primeiro;
	private Celula ultimo;

	/*
	 * CONSTRUTOR INICIAL
	 */
	public Fila() {
		primeiro = new Celula(); // Célula cabeça 
		ultimo = primeiro;
	}

	/*
	 * SET AND GET PRIMEIRO
	 */
	public void setPrimeiro(Celula primeiro) {
		this.primeiro = primeiro;
	}
	public Celula getPrimeiro() {
		return this.primeiro;
	}

	/*
	 * SET AND GET ULTIMO
	 */
	public void setUltimo(Celula ultimo) {
		this.ultimo = ultimo;
	}
	public Celula getUltimo() {
		return this.ultimo;
	}

	/*
	 * MÉTODO INSERIR
	 */
	public void inserir(int x) {
		ultimo.getProx() = new Celula(x);
		ultimo.setUltimo(ultimo.getProx());
	}

	/*
	 * MÉTODO REMOVER LOGICAMENTE
	 */
	public int removerLog() throws Exceptions {
		if (primeiro == ultimo) 
			throw new Exception("Erro! Lista Vazia!");

		Celula tmp = getPrimeiro();
		primeiro.setPrimeiro(getPrimeiro().getProx());

		int elemento = getPrimeiro().getElemento();
		tmp = tmp.getProx() = setProx(null);

		return removido;
	}

	/*
	 * MÉTODO REMOVER FISICAMENTE
	 */
	public int removerFis() throws Exceptions {
		if (primeiro == ultimo)
			throw new Exception("Erro! Lista Vazia!");

		Celula tmp = getPrimeiro().getProx();
		primeiro.getProx() = primeiro.getProx().getProx();

		int removido = tmp.getElemento();
		tmp = tmp.getProx() = setProx(null);

		return removido;
	}

	/*
	 * MÉTODO MOSTRAR
	 */
	public void mostrar() {
		Celula i = i.getPrimeiro();

		System.out.print("[ ");
		while (i != null) {
			System.out.print(i.getElemento() + " ");
			i = i.getProx();
		}
		System.out.println("]");
	}
}
