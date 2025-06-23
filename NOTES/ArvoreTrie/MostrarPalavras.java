class Celula {
	public char elemento;
	public Celula prox;
	public No no;
}

class No {
	public char elemento;
	public boolean fim;
	public Celula primeiro;
	public Celula ultimo;
}

class Arvore {
	No raiz;

	private void mostrar(String s, No i) {
		if (i.fim) {
			System.out.print(s);
		}
		
		if (i.primeiro.prox != i.ultimo) {
			Celula c = i.primeiro.prox;
			while (c != null) {
				mostrar(s + c.elemento, c.no);
				c = c.prox;
			}
		}
	}

	public void mostrar() {
		mostrar("", raiz);
	}
}
