import java.util.Scanner;

public class Arvore {
	No raiz;

	public Arvore() {
		raiz = null;
	}

	public void inserir(int x) {
		raiz = inserir(x, raiz);
	}

	/* 
	 * MÉTODO ABAIXO INSERIDO NA CLASSE NÓ
	 *
	public No inserir(int x, No i) throws new Exception {
		if (i == null) i = new No(x);
		else if (x < i.elemento) i.esq = inserir(x, i.esq);
		else if (x > i.elemento) i.dir = inserir(x, i.dir);
		else throw new Exception("Erro!");

		return i;
	}
	*/

	public void inserirPai(int x) throws new Exception {
		if (i == null) i = new No(x);
		else if (x < raiz.elemento) inserirPai(x, raiz.esq, raiz);
		else if (x > raiz.elemento) inserirPai(x, raiz.dir, raiz);
		else throw new Exception("Erro!");
	}

	/*
	 * MÉTODO ABAIXO É PARA SER INSERIDO NA CLASSE NÓ
	 */
	public void inserirPai(int x, No i, No pai) throws new Exception {
		if (i = null) {
			if (x < pai.elemento) pai.esq = new No(x);
			else pai.dir = new No(x);
		}
		else if (x < i.elemento) i.esq = inserirPai(x, i.esq, i);
		else if (x > i.elemento) i.dir = inserirPai(x, i.dir, i);
		else throw new Exception("Erro!");
	}

	public boolean pesquisar(int x) {
		return pesquisar(x, raiz);
	}

	/*
	 * MÉTODO ABAIXO É PARA SER INSERIDO NA CLASSE NÓ
	 */
	public boolean pesquisar(int x, No i) {
		boolean find = false;

		if (i != null) {
			if (x == i.elemento) find = true;
			else if (x < i.elemento) find = pesquisar(x, i.esq);
			else if (x > i.elemento) find = pesquisar(x, i.dir);
		}

		return find;
	}

	/* Caminhar Central Recursivo
	 * ( Imprime em ordem decrescente )
	 *
	 */
	public caminharCentral(No i) {
		if (i != null) {
			caminharCentral(i.esq);
			System.out.print(i.elemento + " ");
			caminharCentral(i.dir);
		}
	}

	/* 
	 * Caminhar Central Base
	 */
	public void caminharCentral() {
		caminharCentral(raiz);
	}

	/*
	 * Caminhar Pré-Ordem Recursivo
	 * ( Imprime primeiros os pais para depois imprimir os filhos )
	 */
	public void caminharPre(No i) {
		if (i != null) {
			System.out.print(i.elemento + "");
			caminharPre(i.esq);
			caminharPre(i.dir);
		}
	}

	/*
	 * Caminhar Pré-Ordem Base
	 */
	public void caminharPre() {
		caminharPre(raiz);
	}

	/*
	 * Caminhar Pós-Ordem Recursivo
	 * ( Imprime primeiro os filhos para depois imprimir os pais )
	 */
	public void caminharPos(No i) {
		if (i != null) {
			caminharPos(i.esq);
			caminharPos(i.dir);
			System.out.print(i.elemento + "");
		}
	}

	/* 
	 * Caminhar Pós-Ordem Base
	 */
	public void caminharPos() {
		caminharPos(raiz;);
	}

	public void remover(int x) {

	}
}
