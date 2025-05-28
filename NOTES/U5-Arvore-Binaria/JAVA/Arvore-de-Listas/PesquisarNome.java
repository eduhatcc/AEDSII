public class PesquisarCPF {
	/*
	 * MÉTODO PARA SER INSERIDO NA CLASSE LISTA
	 */
	public Contato pesquisar(String nome, Celula i) {
		Contato c;

		if (i != null) {
			if (i.getContato().getNome().equals(nome)) {
				c = i.getContato();
			}
			else {
				pesquisar(nome, i.prox);
			}
		}

		return c;
	}

	/* 
	 * MÉTODO PARA SER INSERIDO NA CLASSE LISTA
	 */
	public Contato pesquisar(String nome) {
		return pesquisar(nome, p.prox());
	}

	/* 
	 * MÉTODO PARA SER INSERIDO NA CLASSE NÓ
	 */
	public Contato pesquisar(String nome, No i) {
		Contato c;

		if (i != null) {
			if (nome.charAt(i) > i.getLetra()) {
				pesquisar(nome, i.dir);
			}
			else if (nome.charAt(i) < i.getLetra()) {
				pesquisar(nome, i.esq);
			}
			else {
				c = i.getLista().pesquisar(nome);
			}
		}

		return c;
	}

	/*
	 * MÉTODO PARA SER INSERIDO NA CLASSE ARVORE
	 */
	public Contato pesquisar(String nome) {
		return pesquisar(nome, raiz);
	}
}
