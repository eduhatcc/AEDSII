class CelulaDupla {
	private int elemento;
	private CelulaDupla prox,
		    ant;

	public CelulaDupla() {
		inicio = fim = prox = ant = null;
	}

	public CelulaDupla(int elemento) {
		this.elemento = elemento;
		this.prox = this.ant = null;
	}

	public void setElemento(int elemento) {
		this.elemento = elemento;
	}

	public void setProx(CelulaDupla prox) {
		this.prox = prox;
	}

	public void setAnt(CelulaDupla ant) {
		this.ant = ant;
	}

	public int getElemento() {
		return this.elemento;
	}

	public CelulaDupla getProx() {
		return this.prox;
	}

	public CelulaDupla getAnt() {
		return this.ant;
	}
}

class Celula {
	private int elemento;
	private Celula prox;

	public Celula() {
		this.prox = null;
	}

	public Celula(int elemento) {
		this.elemento = elemento;
		this.prox = null;
	}

	public void setElemento(int elemento) {
		this.elemento = elemento;
	}

	public void setProx(Celula prox) {
		this.prox = prox;
	}

	public int getElemento() {
		return this.elemento;
	}

	public Celula getProx() {
		return this.prox;
	}
}

class No {
	public int elemento;
	public No esq,
	   dir;

	public No() {
		this.esq = this.dir = null;
	}

	public No(int elemento) {
		this.elemento = elemento;
		this.esq = this.dir = null;
	}

	/*
	 * FALTA IMPLEMENTAR GETTERS AND SETTERS
	 */
}

public class Doidona {
	int[] tabela;
	CelulaDupla inicio, 
		    fim;
	Celula topo;
	No raiz;

	public void hash(int x) {
		return x % tabela.length();
	}

	public void inserirListaDupla(int x) {
		ultimo.prox = new CelulaDupla(x);
		ultimo = ultimo.prox;
		ultimo.prox = null;
	}

	public void inserirPilha(int x) {
		Celula tmp = new Celula(x);
		topo.prox = topo;
		topo = tmp;
	}

	/*
	 * FALTA IMPLEMENTAR O RESTANTE DA FUNÇÃO
	 */
}


