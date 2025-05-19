import java.util.Scanner;

public class No {
	int elemento;
	No esq;
	No dir;

	public No () {
		this.elemento = 0;
		this.esq = null;
		this.dir = null;
	}

	public No inserir(int x, No i) throws new Exception {
		if (i == null) i = new No(x);
		else if (x < i.elemento) i.esq = inserir(x, i.esq);
		else if (x > i.elemento) i.dir = inserir(x, i.dir);
		else throw new Exception("Erro!");

		return i;
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
}
