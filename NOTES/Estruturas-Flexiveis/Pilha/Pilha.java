class Pilha {
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
		inserir.setProx(getTopo());
		setTopo(inserir);
		inserir = null;
    }

    public int remover() throws Exception {
	if (getTopo() == null)
		throw new Exception("Erro!");

	int elemento = topo.getElemento();
	Celula tmp = getTopo();
	topo = topo.getProx();
	tmp.setProx(null);
	tmp = null;

	return elemento;
    }

    public void mostrar() {
	Celula i = getTopo();

	System.out.print("[ ");
	while (i != null) {
	    System.out.print(i.getElemento() + " ");
	    i = i.getProx();
	}
	System.out.println("]");
    }

    public int somar() {
	Celula i = getTopo();
	int soma = 0;

	while (i != null) {
		soma += i.getElemento();
		i = i.getProx();
	}    

	return soma;
    }


    public int somarRecursivo(Celula i) {
	int soma = 0;

	if (i != null) {
		soma = i.getElemento() + somarRecursivo(i.getProx());
	}

	return soma;
    }

    /*
     * FUNÇÃO BASE
     */
    public int somarRecursivo() {
	return somarRecursivo(getTopo());
    }
}
