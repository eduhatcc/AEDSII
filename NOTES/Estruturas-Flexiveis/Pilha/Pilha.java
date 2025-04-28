public class Pilha {
    private Celula topo;
    public Pilha() {
        setTopo(null);
    }

    public void setTopo(Celula topo) {
        this.topo = topo;
    }
    public Celula getTopo() {
        return this.topo;
    }

    public void inserir(int elemento) {
        Celula inserir = new Celula(elemento);
	inserir.prox = getTopo();
	inserir = null;
    }

    public int remover() throws Exception {
	if (getTopo() == null)
		throw new Exception("Erro!");

	int elemento = topo.getElemento();
	Celula tmp = getTopo();
	topo = topo.prox;
	tmp.setProx(null);
	tmp = null;

	return elemento;
    }

    public void mostrar() {
	    Celula i = getTopo();

	    System.out.print("[");
	    while (i != null) {
		    System.out.print(i.getElemento() + " ");
		    i = i.getProx();
	    }
	    System.out.println("]");
    }

    /*
     * IMPLEMENTAR O MÉTODO SOMAR
     * Slide u04 Estruturas de dados básicas flexíveis
     */
    public int somar() {
	    
    }
}
