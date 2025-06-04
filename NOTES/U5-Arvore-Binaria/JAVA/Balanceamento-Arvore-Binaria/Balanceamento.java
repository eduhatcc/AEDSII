class Balanceamento {

	void balancear() {
		if (raiz.esq != null && raiz.dir != null) { // casos [2,3,1] e [2,1,3]
		}
		else if (raiz.dir != null) { // casos [1,2,3] ou [1,3,2]
			if (raiz.dir.dir != null) { // caso [1,2,3]
			}
			else { // caso [1,3,2]
			}
		}
		else { // if (raiz.esq != null)
			if (raiz.esq.dir != null) { // caso [3,1,2]
			}
			else { // caso [3,2,1]
			}
		}

	}

	/*
	 * IMPLEMENTADO CORRETAMENTE
	 */
	No rotacionarEsq(No no) {
		No noDir = no.dir;
		No noDirEsq = noDir.esq;

		noDir.esq = no;
		no.dir = noDirEsq;

		return noDir;
	}

	/*
	 * IMPLEMENTADO CORRETAMENTE
	 */
	No rotacionarDir(No no) {
		No noEsq = no.esq;
		No noEsqDir = noEsq.dir;

		noEsq.dir = no;
		no.esq = noEsqDir;

		return noEsq;
	}

	/*
	 * MÉTODO QUE ROTACIONA ÁRVORE EM FORMATO DE COTUVELO / FORMATO BOOMERANG QUE ESTÁ DESBALANCEADO PARA A **DIREITA**
	 */
	No rotacionarDirEsq(No no) {
		rotacionarDir(no.dir);
		return rotacionarEsq(no);
	}

	/*
	 * MÉTODO QUE ROTACIONA ÁRVORE EM FORMATO DE COTUVELO / FORMATO BOOMERANG QUE ESTÁ DESBALANCEADO PARA A **ESQUERDA**
	 */
	No rotacionarEsqDir(No no) {
		rotacionarEsq(no.esq);
		return rotacionarDir(no);
	}

	void metodo() {
		no = rotacionarEsq(no);
		no = rotacionarDir(no);
	}
}
