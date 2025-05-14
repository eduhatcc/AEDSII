import java.util.*;
import java.io.*;

class Date {
	private int dia;
	private int mes;
	private int ano;

	public Date() {}

	public Date(int dia, int mes, int ano) {
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
	}

	public Date(String line) {
		StringBuilder str = new StringBuilder();
		int[] info = new int[3];
		int cont = 0;

		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);

			if (c != '/') {
				str.append(c);
			}
			else {
				info[cont++] = Integer.parseInt(str.toString());
				str.setLength(0);
			}
		}
		info[cont] = Integer.parseInt(str.toString());

		this.dia = info[0];
		this.mes = info[1];
		this.ano = info[2];
	}

	public String toString() {
		return String.format("%d/%02d/%04d", dia, mes, ano);
	}
}

class Pessoa {
	private int id;
	private String nome;
	private int idade;
	private String[] cores;
	private Date nascimento;
	private String palavra;

	public Pessoa() {}

	public Pessoa(int id, String nome, int idade, String[] cores, String nascimento, String palavra) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.cores = cores.clone();
		Date tmpNascimento = new Date(nascimento);
		this.nascimento = tmpNascimento;
		this.palavra = palavra;
	}

	public int getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public int getIdade() {
		return this.idade;
	}

	public String[] getCores() {
		return this.cores;
	}

	public String getPalavra() {
		return this.palavra;
	}

	public void imprimir() {
		System.out.printf("%d %s %d %s %s %s%n%n", id, nome, idade, Arrays.toString(cores), nascimento.toString(), palavra);
	}
}

public class Main4 {
	public static void swap(Pessoa[] p, int i, int j) {
		Pessoa tmp = p[i];
		p[i] = p[j];
		p[j] = tmp;
	}

	public static void selectionSort(Pessoa[] p, int index) {
		int menor = 0;
		for (int i = 0; i < index; i++) {
			menor = i;
			for (int j = i+1; j < index; j++) {
				if (p[j].getIdade() < p[menor].getIdade()) menor = j;
				else if(p[j].getIdade() == p[menor].getIdade() && p[j].getId() < p[menor].getId()) menor = j;
			}
			swap(p, i, menor);
		}
	} 

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Pessoa[] p = new Pessoa[50];
		int index = 0;

		String line = sc.nextLine();
		while (!line.equals("FIM")) {
			StringBuilder str = new StringBuilder();
			String[] campos = new String[6];
			int cont = 0;
			boolean aspas = false;

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
			String nascimento = campos[4];
			String palavra = campos[5];

			p[index++] = new Pessoa(id, nome, idade, cores, nascimento, palavra);

			line = sc.nextLine();
		}

		selectionSort(p, index);

		for (int i = 0; i < index; i++) {
			p[i].imprimir();
		}

		sc.close();
	}
}
