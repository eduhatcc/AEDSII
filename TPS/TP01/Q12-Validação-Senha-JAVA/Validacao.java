import java.util.Scanner;

public class Validacao {

	/*
	 * FUNÇÃO VERIFICA FIM
	 */
	public static boolean verificaFim(String str, String chave) {
		boolean fim = false;

		if (str.length() == chave.length()) {
			fim = true;

			for (int i=0; i < str.length(); i++) {
				if (str.charAt(i) != chave.charAt(i)) {
					fim = false;
					i = str.length();
				}
			}
		}

		return fim;
	}

	/*
	 * FUNÇÃO VERIFICA FIM BASE
	 */
	public static boolean verificaFim(String str) {
		return verificaFim(str, "FIM");
	}

	/*
	 * FUNÇÃO PARA VERIFICAR SENHA VÁLIDA
	 */
	public static boolean senhaValida(String str) {
		boolean valida = false;
		int upper = 0,
		    lower = 0,
		    num = 0,
		    esp = 0;

		if (str.length() >= 8) {
			for (int i=0; i < str.length(); i++) {
				
				// verifica se o contador de todos estão zerados
				if (esp == 0 || upper == 0 || num == 0 || lower == 0) {

					// verificação do caracter maíusculo
					if (str.charAt(i) >= 65 && str.charAt(i) <= 90) {
						upper++;
					}
						// verificação do caracter minúsculo
						else if(str.charAt(i) >= 97 && str.charAt(i) <= 122) {
							lower++;
						}
							// verificação do caracter numérico
							else if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
								num++;
							}
								// verificação do caracter especial
								else if ((str.charAt(i) >= 33 && str.charAt(i) <= 47) || (str.charAt(i) >= 58 && str.charAt(i) <= 64) || (str.charAt(i) >= 91 && str.charAt(i) <= 96) || (str.charAt(i) >= 123 && str.charAt(i) <= 126)) {
									esp++;
								}
				}
				// se os contadores não estiverem zerados, significa que todos os caracteres foram encontrados
			}
		}

		// verificação se passou em todos os requisitos para ser uma senha válida
		if (upper > 0 && lower > 0 && num > 0 && esp > 0) {
			valida = true;
		}

		return valida;
	}

	/*
	 * FUNÇÃO MAIN
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String str = sc.nextLine().trim();

		while (!verificaFim(str)) {
			System.out.println((senhaValida(str)) ? "SIM" : "NAO");
			str = sc.nextLine().trim();
		}
		       	

		sc.close();
	}

}
