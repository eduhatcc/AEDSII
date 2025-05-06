/*
 * DESAFIO: IMPLEMENTAR UM CONSTRUTOR QUE FAÇA A MATRIZ INICIALIZAR COM VALOR 0
 * E FAZER CADA CÉLULA MATRIZ (UP, DOWN, LEFT, RIGHT) APONTAR CORRETAMENTE PARA CADA UMA
 */

class CelulaMatriz {
	int elemento;
	private CelulaMatriz sup;
	private CelulaMatriz inf;
	private CelulaMatriz esq;
	private CelulaMatriz dir;

	public CelulaMatriz() {
		setLinha(null);
		setColuna(null);
	}

	/*
	 * IMPLEMENTADO EM SALA PELO CAPANEMA
	 */
	public CelulaMatriz(int linha, int coluna) {
		setLinha(linha);
		setColuna(coluna);

		inicio = new CelulaMat();
		CelulaMatriz celCol = inicio;
		for (int x = 1; x < col; x++) {
			celCol.getDir() = new CelulaMat();
			celCol.getDir().getEsq() = celCol;
			celCol = celCol.getDir();
		}

		CelulaMatriz celLinha = inicio;
		for (int l = 1; l < linha; l++) {
			CelLinha.getInf() = new CelulaMatriz();
			celLinha.getInf().getSup() = celLinha();
			celLinha = celLinha.getInf();
			celCol = celLinha;

			for (int x = 1; x < col; x++) {
				celCol.getDir() = new CelulaMatriz();
				celCol.getDir().getEsq() = celCol.getSup().getDir();
				celCol.getSup().getDir().getInf() = celCol.getDir();
				celCol = celCol.getDir();
			}
		}
	}
}

public class Matriz {
	// Atributos privados
	private CelulaMatriz inicio;
	private int linha;
	private int coluna;

	// Construtor vazio
	public Matriz() {
		setLinha(3);
		setColuna(3);
	}

	public Matriz(int linha, int coluna) {
		setLinha(linha);
		setColuna(coluna);
		setElementos(linha, coluna);
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}
	public int getLinha() {
		return this.linha;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
	public int getColuna() {
		return this.coluna;
	}

	public setElementos(int linha, int coluna) {
		for (int i = 0; i < linha; i++) {
			for (int j = 0; j < coluna; i++) {

			}
		}
	}
}
