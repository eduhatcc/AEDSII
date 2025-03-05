import java.util.Scanner;

public class Soma {

	public static boolean verificaFim(String str, String chave, int i) {
		boolean fim = true;

		if (i < chave.length()) {
			if (str.length() == chave.length()) {
				if (str.charAt(i) != chave.charAt(i)) {
					fim = false;
				}
				else {
					fim = verificaFim(str, chave, i+1);
				}
			}
			else { 
				fim = false;
			}
		}
		return fim;
	}

	public static int somaDigitos(String str, int i) {
		int soma = 0;
		
		if (i < str.length()) {
			char n = str.charAt(i);
			soma = (n - '0'); // transforma o char em número, sem considerar a tabela ascii
			soma += somaDigitos(str, i+1);
		}

		return soma;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();

		while (!verificaFim(str, "FIM", 0)) {
			System.out.println(somaDigitos(str, 0));
			str = sc.nextLine();
		}

		sc.close();
	}
}
