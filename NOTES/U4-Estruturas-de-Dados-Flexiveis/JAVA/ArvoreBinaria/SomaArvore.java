public class SomaArvore {
	/*
	 * FUNÇÃO PARA REALIZAR A SOMA DOS ELEMENTOS DE UMA ÁRVORE
	 */
	int somaArvore(No i) {
		int soma = 0;

		if (i != null) {
			soma = i.elemento;
			soma += somaArvore(i.esq);
			soma += somaArvore(i.dir);
		}

		return soma;
	}
}
