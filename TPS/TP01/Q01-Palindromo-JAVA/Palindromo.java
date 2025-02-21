import java.util.Scanner;

public class Palindromo {
	
	public static boolean verificaFim(String str, String chave) {
 		boolean fim = false;

		//compara se o tamanho da string é igual ao tamanho da chave
		if(str.length() == chave.length()) {
			fim = true;
			
			//repetição para verificar se a string é igual a chave
			for(int i=0; i < chave.length(); i++) {
				if(str.charAt(i) != chave.charAt(i)) {
					fim = false;
					i = chave.length();
				} //fim if interno
			} //fim for
		} //fim if externo

		return fim;
	}	

	public static boolean ehPalindromo(String str) {
        	boolean palindromo = true;
        	int i = 0,
            	    j = str.length() - 1;

        	while (i < j) {
                	if (str.charAt(i) != str.charAt(j)) {
                        	palindromo = false;
                        	i = j;
                	}
               	 i++; j--;
		}

		return palindromo;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String str;
		str = sc.nextLine().trim();

		while (!verificaFim(str, "FIM")) {
			if(ehPalindromo(str)) {
				System.out.println("SIM");
			}
			else {
				System.out.println("NAO");
			}
			str = sc.nextLine().trim();
		}
	}
}
