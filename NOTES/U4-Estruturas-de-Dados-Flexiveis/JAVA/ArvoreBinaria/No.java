import java.util.Scanner;

public class No {
	int elemento;
	No esq;
	No dir;

	public No () {
		this.elemento = 0;
		this.esq = null;
		this.dir = null;
	}

	public No inserir(int x, No i) throws new Exception {
		if (i == null) i = new No(x);
		else if (x < i.elemento) i.esq = inserir(x, i.esq);
		else if (x > i.elemento) i.dir = inserir(x, i.dir);
		else throw new Exception("Erro!");

		return i;
	} 

}
