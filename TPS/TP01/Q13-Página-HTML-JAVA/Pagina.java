import java.util.*;
import java.net.*;
import java.io.*;

public class Pagina {

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
	 * FUNÇÃO DE OCORRÊNCIAS DA PÁGINA
	 */
	public static void ocorrenciasPagina(String str, String html) {
		int a = 0,
		    e = 0,
		    i = 0,
		    o = 0,
		    u = 0,
		    a0 = 0,
		    e0 = 0,
		    i0 = 0,
		    o0 = 0,
		    u0 = 0,
		    a1 = 0,
		    e1 = 0,
		    i1 = 0,
		    o1 = 0,
		    u1 = 0,
		    a2 = 0,
		    o2 = 0,
		    a3 = 0,
		    e3 = 0,
		    i3 = 0,
		    o3 = 0,
		    u3 = 0,
		    consoante = 0,
		    br = 0,
		    table = 0,
		    nomePagina = 0;

		for (int j=0; j < html.length(); j++) {
			if (html.charAt(j) == 'a') {
				a++;
			} 
			else if (html.charAt(j) == 'e') {
				e++;
			}
			else if (html.charAt(j) == 'i') {
				i++;
			}
			else if (html.charAt(j) == 'o') {
				o++;
                        }
                        else if (html.charAt(j) == 'u') {
                                u++;
                        }
			else if (html.charAt(j) == 'á') {
				a0++;
			} 
			else if (html.charAt(j) == 'é') {
				e0++;
			} 
			else if (html.charAt(j) == 'í') {
				i0++;
			} 
			else if (html.charAt(j) == 'ó') {
				o0++;
			} 
			else if (html.charAt(j) == 'ú') {
				u0++;
			} 
			else if (html.charAt(j) == 'à') {
				a1++;
			} 
			else if (html.charAt(j) == 'è') {
				e1++;
			} 
			else if (html.charAt(j) == 'ì') {
				i1++;
			} 
			else if (html.charAt(j) == 'ò') {
				o1++;
			} 
			else if (html.charAt(j) == 'ù') {
				u1++;
			} 
			else if (html.charAt(j) == 'ã') {
				a2++;
			} 
			else if (html.charAt(j) == 'õ') {
				o2++;
			} 
			else if (html.charAt(j) == 'â') {
				a3++;
			} 
			else if (html.charAt(j) == 'ê') {
				e3++;
			} 
			else if (html.charAt(j) == 'î') {
				i3++;
			} 
			else if (html.charAt(j) == 'ô') {
				o3++;
			}
			else if (html.charAt(j) == 'û') {
				u3++;
			} 
			else if ((html.charAt(j) >= 'a' && html.charAt(j) <= 'z' || html.charAt(j) >= 'A' && html.charAt(j) <= 'Z') && html.charAt(j) != 'a' && html.charAt(j) != 'e' && html.charAt(j) != 'i' && html.charAt(j) != 'o' && html.charAt(j) != 'u' && html.charAt(j) != 'A' && html.charAt(j) != 'E' && html.charAt(j) != 'I' && html.charAt(j) != 'O' && html.charAt(j) != 'U') {
				consoante++;
			}
			else if (html.charAt(j) == '<') {
				if (html.charAt(j+1) == 'b' && html.charAt(j+2) == 'r' && html.charAt(j+3) == '>') {
					br++;
					consoante -= 2;
				}
				else if (html.charAt(j+1) == 't' && html.charAt(j+2) == 'a' && html.charAt(j+3) == 'b' && html.charAt(j+4) == 'l' && html.charAt(j+5) == 'e' && html.charAt(j+6) == '>') {
					table++;
					consoante -= 3;
					a--;
					e--;
				}
			}
		}

		System.out.printf("a(%d) e(%d) i(%d) o(%d) u(%d) á(%d) é(%d) í(%d) ó(%d) ú(%d) à(%d) è(%d) ì(%d) ò(%d) ù(%d) ã(%d) õ(%d) â(%d) ê(%d) î(%d) ô(%d) û(%d) consoante(%d) <br>(%d) <table>(%d) %s%n", a, e, i, o, u, a0, e0, i0, o0, u0, a1, e1, i1, o1, u1, a2, o2, a3, e3, i3, o3, u3, consoante, br, table, str);
         
	}

	/*
	 * FUNÇÃO OBTER HTML
	 */
	public static String getHtml(String endereco) {
		URL url;
		InputStream is = null;
		BufferedReader br;
		String html = "",
		       line;

		try {
			url = new URL(endereco);
			is = url.openStream();
			br = new BufferedReader(new InputStreamReader(is));

			while ((line = br.readLine()) != null) {
				html += line + "\n";
			}
		}
		catch (MalformedURLException mue) {
			mue.printStackTrace();
		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		}

		if (is != null) {
			try {
				is.close();
			} 
			catch (IOException ioe) {}
		}

		return html;
	}

	/*
	 * FUNÇÃO MAIN
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine().trim();

		while (!verificaFim(str)) {
			String endereco = sc.nextLine().trim();
			String html = getHtml(endereco);
			ocorrenciasPagina(str, html);
			str = sc.nextLine().trim();
		}

		sc.close();
	}
}
