class Celula {
	private int elemento;
	private Celula prox;

	public Celula() {
		setElemento(0);
	}

	public Celula(int elemento) {
		setElemento(elemento);
		setProx(null);
	}

	public void setElemento(int elemento) {
		this.elemento = elemento;
	}
	public int getElemento() {
		return this.elemento;
	}

	public void setProx(Celula prox) {
		this.prox = prox;
	}
	public Celula getProx() {
		return this.prox;
	}
}
