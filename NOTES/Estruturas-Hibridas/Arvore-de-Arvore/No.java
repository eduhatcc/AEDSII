public class No {
	char letra;
	No esq, 
	   dir;
	No2 raiz;

	private int contarPalavras(No2 i, char ultimo) {
		int cont = 0;

		if (i != null) {
			if (i.palavra.charAt(i.palavra.length() -1) == ultimo) {
				cont++;
			}
			cont = contarPalavras(i.esq, ultimo);
			cont = contarPalavras(i.dir, ultimo);
		}

		return cont;
	}

	public int contarPalavras(char ultimo) {
		return contarPalavras(raiz, ultimo);
	}
}
