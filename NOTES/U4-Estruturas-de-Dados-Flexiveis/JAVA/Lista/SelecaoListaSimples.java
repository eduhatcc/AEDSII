public class SelecaoListaSimples {
	
	// Maneira que eu estava implementando
	public void selecao() {
		int maior = getPrimeiro().getElemento();
		for (Celula i = getPrimeiro().getProx(); i != null; i = i.getProx()) {
			if (i.getElemento() > maior.getElemento()) {
				swap(i, maior);
				maior = i;
			}
		}
	}

	// Maneira que o Capanema fez no quadro
	public void swap(Celula i, Celula maior) {
		int x = i.getElemento();
		i.getElemento() = maior.getElemento();
		maior.getElemento() = x;
	}
	//
	public void selecao() {
		for (Celula i = getPrimeiro(); i.getProx() != null; i = i.getProx()) {
			Celula maior = i;
			
			for (Celula j = i.getProx(); j.getProx() != null; j = j.getProx()) {
				if (j.getElemento() > maior.getElemento()) {
					maior = j;
				}
			}
			swap(i, maior);
		}
	}
}
