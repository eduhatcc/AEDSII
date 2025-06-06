public class ArvoreArvore {
	No raiz;

	private int contarPalavras(No i, char primeiro, char ultimo) {
		int cont = 0;
		if (i != null) {
			if (i.letra == primeiro) {
				cont = contarPalavras(i.raiz, ultimo);
			}
			else if (primeiro < i.letra) {
				cont = contarPalavras(i.esq, primeiro, ultimo);
			}
			else if (primeiro > i.letra) {
				cont = contarPalavras(i.dir, primeiro, ultimo);
			}
		}

		return cont;
	}

	public int contarPalavras(char primeiro, char ultimo) {
		return contarPalavras(raiz, primeiro, ultimo);
	}
}
