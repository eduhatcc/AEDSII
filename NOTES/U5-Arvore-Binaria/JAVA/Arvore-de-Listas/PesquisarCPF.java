public class PesquisarCPF {
	/*
	 * MÉTODO PARA SER INSERIDO NA CLASSE LISTA
	 */
	public Contato pesquisarLista(String CPF, Celula i) {
		Contato c;

		if (i != null) {
			if (i.getContato().getCPF().equals(CPF)) {
				c = i.getContato();
			}
			else {
				pesquisarLista(CPF, i.prox);
			}
		}

		return c;
	}

	/* 
	 * MÉTODO PARA SER INSERIDO NA CLASSE LISTA
	 */
	public Contato pesquisarLista(String CPF) {
		return pesquisar(CPF, p.prox());
	}

	/* 
	 * MÉTODO PARA SER INSERIDO NA CLASSE NÓ
	 */
	public Contato pesquisarCPF(String CPF, No i) {
		Contato c;

		if (i != null) {
			if (c != null) {
				c = pesquisarCPF(CPF, i.dir);
			}
			if (c != null) {
				c = pesquisarCPF(CPF, i.esq);
			}
			c = i.lista.pesquisarLista(CPF);
		}

		return c;
	}

	/*
	 * MÉTODO PARA SER INSERIDO NA CLASSE ARVORE
	 */
	public Contato pesquisarCPF(String CPF) {
		return pesquisar(CPF, raiz);
	}
}
