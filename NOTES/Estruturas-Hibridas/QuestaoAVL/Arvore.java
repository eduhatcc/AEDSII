public class Arvore {
	private No raiz;
	
	private boolean isAVL(No i) {
		boolean isAvl = true;

		if (i != null) {
			int alturaEsq = getAltura(i.esq);
			int alturaDir = getAltura(i.dir);

			if ((Math.abs(alturaDir - alturaEsq)) < 2) {
				isAvl = false;
			}

		}

		return isAvl;
	}

	public boolean isAvl() {
		return isAvl(raiz);
	}

	private int getAltura(No i) {
		int altura = 0;

		if (i != null) {
			int alturaEsq = getAltura(i.esq);
			int alturaDir = getAltura(i.dir);
			altura++;
			
			if (alturaEsq > alturaDir) {
				altura = alturaEsq;
			}
			else {
				altura = alturaDir;
			}
		}

		return altura;
	}

	public int getAltura() {
		return getAltura(raiz);
	}
}
