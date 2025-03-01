import java.util.Scanner;

public class Palindromo {
	
	/* 
	 * FUNÇÃO VERIFICA FIM RECURSIVO
	 */
	public static boolean verificaFim(String str, String chave, int i) {
 		boolean fim = true;		

		if (i >= 0) {
		 	fim = false;

			// compara se o tamanho da string é igual ao tamanho da chave
			if (str.length() == chave.length()) {
				fim = true;

				// verifica se o caracter é diferente
				if (str.charAt(i) != chave.charAt(i)) {
					
					// se for diferente, retorna falso e acaba o loop
					fim = false;
					i = 0;
				}
				else {
					// se for igual, continua chamando a função recursivamente
					fim = verificaFim(str, chave, i-1);
				}
			}
			else {
				// se for tamanho diferente retorna falso e acaba o loop
				fim = false;
			}
		} 
	
		return fim;
	}	

	/*
	 * FUNÇÃO VERIFICA FIM BASE QUE CHAMA A FUNÇÃO RECURSIVA
	 */
	public static boolean verificaFim(String str) {
		return verificaFim(str, "FIM", chave, 2);
	}

	/* 
	 * FUNÇÃO É PALINDROMO RECURSIVO
	 */
	public static boolean ehPalindromo(String str, int i, int j) {
        	boolean palindromo = true;

		// condição de parada
        	if (i < j) {

			// verificação de palindromo
                	if (str.charAt(i) != str.charAt(j)) {
                        	
				// se os caracteres forem diferentes retorna falso e acaba o loop
				palindromo = false;
                	}
               		else {
				// se forem iguais, continua chamando a função recursivamente
				palindromo = ehPalindromo(str, i+1, j-1);
			}
		}

		return palindromo;
	}

	/* 
	 * FUNÇÃO É PALINDROMO BASE QUE CHAMA A FUNÇÃO RECURSIVA
	 */
	public static boolean ehPalindromo(String str) {
		return ehPalindromo(str, 0, str.length() -1);
	}

	/* 
	 * FUNÇÃO MAIN
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String str = sc.nextLine().trim();

		while (!verificaFim(str)) {
			if (ehPalindromo(str)) {
				System.out.println("SIM");
			}
			else {
				System.out.println("NAO");
			}
			str = sc.nextLine().trim();
		}
	}
}
