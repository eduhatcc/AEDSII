import java.util.*;
import java.io.*;

class Pessoa {
	private int id;
	private String nome;
	private int idade;
	private String[] cores;
	private String palavra;

	public Pessoa() {}

	public Pessoa(int id, String nome, int idade, String[] cores, String palavra) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.cores = cores.clone();
		this.palavra = palavra;
	}

	public void imprimir() {
		System.out.printf("%d %s %d %s %s%n", id, nome, idade, Arrays.toString(cores), palavra);
	}
}

public class Main2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Pessoa[] p = new Pessoa[50];
		int index = 0;

		String line = sc.nextLine();

		while (!line.equals("FIM")) {
			String[] campos = new String[5];
			int cont = 0;
			boolean aspas = false;
			StringBuilder str = new StringBuilder();

			for (int i = 0; i < line.length(); i++) {
				char c = line.charAt(i);

				if (c == '"') aspas = !aspas;
				else if (c == ',' && !aspas) {
					campos[cont++] = str.toString();
					str.setLength(0);
				}
				else str.append(c);
			}

			campos[cont] = str.toString();

			int id = Integer.parseInt(campos[0]);
			String nome = campos[1];
			int idade = Integer.parseInt(campos[2]);
			String[] cores = campos[3].split(",");
			String palavra = campos[4];

			p[index++] = new Pessoa(id, nome, idade, cores, palavra);

			line = sc.nextLine();
		}

		for (int i = 0; i < index; i++) {
			p[i].imprimir();
		}

		sc.close();
	}
}
