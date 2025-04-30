/*
 * DESAFIO: IMPLEMENTAR UM CONSTRUTOR QUE FAÇA A MATRIZ INICIALIZAR COM VALOR 0
 * E FAZER CADA CÉLULA MATRIZ (UP, DOWN, LEFT, RIGHT) APONTAR CORRETAMENTE PARA CADA UMA
 */

class CelulaMatriz {
	private CelulaMatriz up;
	private CelulaMatriz down;
	private CelulaMatriz left
	private CelulaMatriz right;

	public CelulaMatriz() {
		setCelulaMatriz(0)
	}
}

public class Matriz {
	// Atributos privados
	private Celula inicio;
	private int linha;
	private int coluna;
	private Celula up;
	private Celula down;
	private Celula left;
	private Celula right;

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
