import java.util.Scanner;

public class Palindromo {
	
	public static boolean verificaFim(String str, String chave, int i) {
 		boolean fim = true;		

		if (i >= 0) {
		 	fim = false;

			//compara se o tamanho da string é igual ao tamanho da chave
			if (str.length() == chave.length()) {
				fim = true;

				if (str.charAt(i) != chave.charAt(i)) {
					fim = false;
					i = 0;
				}
				else {
					fim = verificaFim(str, chave, i-1);
				}
			}
			else {
				fim = false;
			}
		} 
	
		return fim;
	}	

	public static boolean ehPalindromo(String str, int i, int j) {
        	boolean palindromo = true;

        	if (i < j) {
                	if (str.charAt(i) != str.charAt(j)) {
                        	palindromo = false;
                        	i = j;
                	}
               		else {
				palindromo = ehPalindromo(str, i+1, j-1);
			}
		}

		return palindromo;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String str = sc.nextLine().trim();

		while (!verificaFim(str, "FIM", 2)) {
			if (ehPalindromo(str, 0, (str.length()-1))) {
				System.out.println("SIM");
			}
			else {
				System.out.println("NAO");
			}
			str = sc.nextLine().trim();
		}
	}
}
