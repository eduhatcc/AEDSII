public class Altura {
	/*
	 * FUNÇÃO PARA RETORNAR A ALTURA DA ÁRVORE
	 */
	int alturaArvore(No i) {
		int altura = 0;

		if (i != null) {
			int alturaEsq = alturaArvore(i.esq);
			int alturaDir = alturaArvore(i.dir);
			altura = 1 + (alturaEsq > alturaDir ? alturaEsq : AlturaDir);
		}

		return altura;
	}
}
