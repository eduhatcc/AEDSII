public class ToAB {
	public No toAB(Celula p1, CelulaDupla p2) {
		Arvore arvore = new Arvore();
		p1 = p1.prox;
		p2 = p2.prox;
		if (p1 != null && p2 != null) {
			arvore.inserir(p1.elemento);
			arvore.inserir(p2.elemento);
			toAB(p1, p2);
		}
		else if (p1 != null) {
			arvore.inserir(p1.elemento);
			toAB(p1, p2);
		}
		else if (p2 != null) {
			arvore.inserir(p2.elemento);
			toAB(p1, p2);
		}

		return arvore.raiz;
	}
}
