class Pilha {
    private Celula topo; // Atributo privado

    /*
     * CONSTRUTOR INICIAL
     */
    public Pilha() {
        setTopo(null);
    }

    /* SET AND GET TOPO
     */
    public void setTopo(Celula topo) {
        this.topo = topo;
    }
    public Celula getTopo() {
        return this.topo;
    }

    /*
     * MÉTODO INSERIR
     */
    public void inserir(int elemento) {
        Celula inserir = new Celula(elemento);
		inserir.setProx(getTopo());
		setTopo(inserir);
		inserir = null;
    }

    /*
     * MÉTODO REMOVER
     */
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

    /*
     * MÉTODO MOSTRAR
     */
    public void mostrar() {
	Celula i = getTopo();

	System.out.print("[ ");
	while (i != null) {
	    System.out.print(i.getElemento() + " ");
	    i = i.getProx();
	}
	System.out.println("]");
    }

    /*
     * MÉTODO SOMAR ITERATIVO
     */
    public int somar() {
	Celula i = getTopo();
	int soma = 0;

	while (i != null) {
		soma += i.getElemento();
		i = i.getProx();
	}    

	return soma;
    }

    /*
     * MÉTODO SOMAR RECURSIVO
     */
    public int somarRecursivo(Celula i) {
	int soma = 0;

	if (i != null) {
		soma = i.getElemento() + somarRecursivo(i.getProx());
	}

	return soma;
    }

    /*
     * MÉTODO SOMAR RECURSIVO BASE
     */
    public int somarRecursivo() {
	return somarRecursivo(getTopo());
    }

    /*
     * MÉTODO GET MAIOR ITERATIVO
     */
    public int getMaior() {
	Celula i = getTopo();
	int maior = i.getElemento();
	i = getProx();

	while (i != null) {
		if (i.getElemento() > maior) {
			maior = i.getElemento();
		}	
		i = i.getProx();
	}

	return maior;
    }

    /*
     * MÉTODO GET MAIOR RECURSIVO
     */
    public int getMaiorRec(Celula i) throws Exceptions {
	    if (i == null)
		    throw new Exception("Erro! Pilha vazia!");

	    if (i.getProx() == null) {
		    return i.getElemento();
	    }
	    
	    int maiorRestante = getMaiorRec(i.getProx());

	    return i.getElemento() > maiorRestante ? i.getElemento() : maiorRestante;
    }

    /*
     * MÉTODO GET MAIOR RECURSIVO BASE
     */
    public int getMaiorRec() {
	    return getMaiorRec(getTopo());
    }

    /*
     * FALTA IMPLEMENTAR:
     * MÉTODO RECURSIVO PARA MOSTRAR OS ELEMENTOS DA PILHA NA ORDEM QUE SERÃO REMOVIDOS
     * MÉTODO RECURSIVO PARA MOSTRAR OS ELEMENTOS DA PILHA NA ORDEM QUE FORAM INSERIDOS
     * MÉTODO ITERATIVO PARA MOSTRAR OS ELEMENTOS DA PILHA NA ORDEM QUE FORAM INSERIDOS
     */
}
