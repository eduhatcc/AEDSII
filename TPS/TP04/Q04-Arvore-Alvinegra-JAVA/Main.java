import java.io.*;
import java.text.*;
import java.util.*;

public class Main {
    public static String log = "874201_alvinegra.txt";
    public static int matricula = 874201;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Carrega todas as linhas do CSV (incluindo header)
        Show.preencher();
        List<String> rawCsv = Show.getcsv();
        
        // Separa header e dados
        List<String> csvData = rawCsv.subList(1, rawCsv.size());
        
        // Pre-carrega todos os shows para facilitar clones
        Show[] shows = new Show[csvData.size()];
        for (int i = 0; i < csvData.size(); i++) {
            shows[i] = new Show();
            shows[i].ler(csvData.get(i));
        }

        // Inicia a árvore e lê IDs iniciais até "FIM"
        Alvinegra alvinegra = new Alvinegra();
        String line = sc.nextLine();
        while (!line.equals("FIM")) {
            int cont = Show.converteStr(line) - 1;
            if (cont >= 0 && cont < shows.length) {
                alvinegra.inserir(shows[cont].clone());
            }
            line = sc.nextLine();
        }

        // Processa operações
	line = sc.nextLine();
	long start = System.nanoTime();
	while(!line.equals("FIM"))  {
	    	if (alvinegra.pesquisar(line)) {
			System.out.println("SIM");
		}
		else {
			System.out.println("NAO");
		}
		line = sc.nextLine();
	}
	long end = System.nanoTime();
        double tempo = (end - start) / 1e6; // em milissegundos

	try (BufferedWriter bw = new BufferedWriter(new FileWriter(log))) {
            bw.write(String.format("%s\t%d\t%d\t%.2f\n", matricula, alvinegra.comparacoes, tempo));
        }
        catch(Exception e) {}

        sc.close();
    }
}

class No {
	private Show elemento;
	private No esq;
	private No dir;
	private boolean cor;

	public No(Show elemento) {
		this.elemento = elemento;
		this.esq = this.dir = null;
		this.cor = false;
	}

	public No(Show elemento, boolean cor) {
		this(elemento, null, null, cor);
	}

	public No(Show elemento, No esq, No dir, boolean cor) {
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
		this.cor = cor;
	}

	public void setElemento(Show elemento) {
		this.elemento = elemento;
	}

	public void setEsq(No esq) {
		this.esq = esq;
	}

	public void setDir(No dir) {
		this.dir = dir;
	}

	public void setCor(boolean cor) {
		this.cor = cor;
	}

	public No getEsq() {
		return this.esq;
	}

	public No getDir() {
		return this.dir;
	}

	public Show getElemento() {
		return this.elemento;
	}

	public String getTitle() {
		return this.elemento.getTitle();
	}

	public boolean getCor() {
		return this.cor;
	}
}

class Alvinegra {
    private No raiz;
    public static int comparacoes;

    public Alvinegra() {
	    this.raiz = null;
	    comparacoes = 0;
    }

    private No inserir(No i, Show s) {
	    if (i == null) {
		    comparacoes++;
		    i = new No(s);
	    }
	    else if (s.getTitle().compareToIgnoreCase(i.getElemento().getTitle()) < 0) {
		    comparacoes += 2;
		    i.setEsq(inserir(i.getEsq(), s));
	    }
	    else if (s.getTitle().compareToIgnoreCase(i.getElemento().getTitle()) > 0) {
		    comparacoes += 3;
		    i.setDir(inserir(i.getDir(), s));
	    }

	    return i;
    }

    private void inserir(Show s, No bisavo, No avo, No pai, No i) {
	    if (i == null) {
		    if (s.getTitle().compareToIgnoreCase(pai.getElemento().getTitle()) < 0) {
			    comparacoes++;
			    pai.setEsq(new No(s, true));
			    i = pai.getEsq();
		    }
		    else {
			    pai.setDir(new No(s, true));
			    i = pai.getDir();
		    }

		    if (pai.getCor() == true) {
			    comparacoes++;
			    balancear(bisavo, avo, pai, i);
		    }
	    }
	    else {
		    if (i.getEsq() != null && i.getDir() != null && i.getEsq().getCor() == true && i.getDir().getCor() == true) {
			    comparacoes++;

			    i.setCor(true);
			    i.getEsq().setCor(false);
			    i.getDir().setCor(false);

			    if (i == raiz) {
				    comparacoes++;
				    i.setCor(false);
			    }
			    else if (pai.getCor() == true) {
				    comparacoes += 2;
				    balancear(bisavo, avo, pai, i);
			    }
		    }

		    if (s.getTitle().compareToIgnoreCase(i.getElemento().getTitle()) < 0) {
			    comparacoes++;
			    inserir(s, avo, pai, i, i.getEsq());
		    }
		    else if (s.getTitle().compareToIgnoreCase(i.getElemento().getTitle()) > 0) {
			    comparacoes += 2;
			    inserir(s, avo, pai, i, i.getDir());
		    }
	    }
    }

    public void inserir(Show s) {
	    // Se a árvore estiver vazia
	    if (raiz == null) {
		    comparacoes++;
		    raiz = new No(s);
	    }
	    /*
	    // Senão, se a árvore tiver um elemento inserido
	    else if (raiz.getEsq() == null && raiz.getDir() == null) {
		    comparacoes += 2;
		    if (s.getTitle().compareToIgnoreCase(raiz.getElemento().getTitle()) < 0) {
			    comparacoes++;
			    raiz.setEsq(new No(s));
		    }
		    else {
			    raiz.setDir(new No(s));
		    }
	    }
	    // Senão, se a árvore tiver dois elementos (raiz e dir)
	    else if (raiz.getEsq() == null) {
		    comparacoes++;
		    if (s.getTitle().compareToIgnoreCase(raiz.getElemento().getTitle()) < 0) {
			    comparacoes++;
			    raiz.setEsq(new No(s));
		    }
		    else if (s.getTitle().compareToIgnoreCase(raiz.getDir().getElemento().getTitle()) < 0) {
			    comparacoes += 2;
			    raiz.setEsq(new No(s));
		    }
		    else {
			    raiz.setEsq(new No(s));
			    raiz.setElemento(raiz.getDir().getElemento());
			    raiz.getDir().setElemento(s);
		    }
		    raiz.getEsq().setCor(false);
		    raiz.getDir().setCor(false);
	    }
	    */
	    else {
		    inserir(s, null, null, null, raiz);
	    }
	    raiz.setCor(false);
    }

    private No rotacaoDir(No no) {
	    No noEsq = no.getEsq();
	    No noEsqDir = noEsq.getDir();

	    noEsq.setDir(no);
	    no.setEsq(noEsqDir);

	    return noEsq;
    }

    private No rotacaoEsq(No no) {
	    No noDir = no.getDir();
	    No noDirEsq = noDir.getEsq();

	    noDir.setEsq(no);
	    no.setDir(noDirEsq);

	    return noDir;
    }

    private No rotacaoDirEsq(No no) {
	    no.setDir(rotacaoDir(no.getDir()));
	    return rotacaoEsq(no);
    }

    private No rotacaoEsqDir(No no) {
	    no.setEsq(rotacaoEsq(no.getEsq()));
	    return rotacaoDir(no);
    }

    private void balancear(No bisavo, No avo, No pai, No i) {
	    if (pai.getCor() == true) {
		    comparacoes++;
		    if (pai.getElemento().getTitle().compareToIgnoreCase(avo.getElemento().getTitle()) > 0) {
			    comparacoes++;
			    if (i.getElemento().getTitle().compareToIgnoreCase(pai.getElemento().getTitle()) > 0) {
				    comparacoes++;
				    avo = rotacaoEsq(avo);
			    }
			    else {
				    avo = rotacaoDirEsq(avo);
			    }
		    }
		    else {
			    if (i.getElemento().getTitle().compareToIgnoreCase(pai.getElemento().getTitle()) < 0) {
				    comparacoes++;
				    avo = rotacaoDir(avo);
			    }
			    else {
				    avo = rotacaoEsqDir(avo);
			    }
		    }

		    if (bisavo == null) {
			    comparacoes++;
			    raiz = avo;
		    }
		    else if (avo.getElemento().getTitle().compareToIgnoreCase(bisavo.getElemento().getTitle()) < 0) {
			    comparacoes += 2;
			    bisavo.setEsq(avo);
		    }
		    else {
			    bisavo.setDir(avo);
		    }

		    avo.setCor(false);
		    avo.getEsq().setCor(true);
		    avo.getDir().setCor(true);
	    }
    }

    private boolean pesquisar(No i, String line) {
	    boolean encontrou = false;
	    comparacoes++;
	    if (i != null) {
		    if (i.getTitle().equals(line)) {
			    comparacoes++;
			    encontrou = true;
		    }
		    else if (line.compareToIgnoreCase(i.getTitle()) < 0) {
			    comparacoes += 2;
			    System.out.print("esq ");
			    encontrou = pesquisar(i.getEsq(), line);
		    }
		    else {
			    comparacoes += 2;
			    System.out.print("dir ");
			    encontrou = pesquisar(i.getDir(), line);
		    }
	    }

	    return encontrou;
    }

    public boolean pesquisar(String line) {
	    System.out.print("=>raiz  ");
	    return pesquisar(raiz, line);
    }
}

class Show {
    // Atributos da classe Show
    private String show_id;
    private String type;
    private String title;                                
    private String director;
    private String cast[];
    private String country;
    private Date date_added;
    private int release_year;
    private String rating;
    private String duration;
    private String listed_in[];
    
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH); // Formato da data
    private static String arq = "../tmp/disneyplus.csv"; // Caminho do arquivo CSV
    private static List<String> csv = new ArrayList<>(); // Lista para armazenar as linhas do CSV
    
    // Método para obter o caminho do arquivo CSV
    public static List<String> getcsv() {
        return csv;
    }

    // Construtor vazio
    public Show() {}

    // Construtor com parâmetros completos
    public Show(String show_id, String type, String title, String director, 
           String[] cast, String country, Date date_added, int release_year, 
           String rating, String duration, String[] listed_in) {
        setShowId(show_id);
        setType(type);
        setTitle(title);
        setDirector(director);
        setCast(cast);
        setCountry(country);
        setDateAdded(date_added);
        setReleaseYear(release_year);
        setRating(rating);
        setDuration(duration);
        setListedIn(listed_in);
    }

    public void setShowId(String show_id) {
        this.show_id = show_id;
    }
    public String getShowId() {
        return show_id;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    
    public void setDirector(String director) {          
        this.director = director;
    }
    public String getDirector() {              
        return director;
    }
    
    public void setCast(String[] cast) {
        this.cast = cast;
    }
    public String[] getCast() {
        return cast;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    public String getCountry() {
        return country;
    }
    
    public void setDateAdded(Date date_added) {
        this.date_added = date_added;
    }
    public Date getDateAdded() {
        return date_added;
    }
    
    public void setReleaseYear(int release_year) {
        this.release_year = release_year;
    }
    public int getReleaseYear() {
        return release_year;
    }
    
    public void setRating(String rating) {
        this.rating = rating;
    }
    public String getRating() {
        return rating;
    }
    
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getDuration() {
        return duration;
    }
    
    public void setListedIn(String[] listed_in) {
        this.listed_in = listed_in;
    }
    public String[] getListedIn() {
        return listed_in;
    }
    
    public Show clone() {
        Show clone = new Show();
        clone.show_id = getShowId();
        clone.type = getType();
        clone.title = getTitle();
        clone.director = getDirector();
        clone.cast = (getCast() != null ? getCast().clone() : new String[0]);
        clone.country = getCountry();
        clone.date_added = getDateAdded();
        clone.release_year = getReleaseYear();
        clone.rating = getRating();     
        clone.duration = getDuration();
        clone.listed_in = (getListedIn() != null ? getListedIn().clone() : new String[0]);
        
        return clone;
    }

    // Método imprimir utilizando os getters e tratando valores nulos ou vazios
    public void imprimir() {
        String dateAdded = (getDateAdded() != null)
        ? new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).format(getDateAdded())
        : "March 1, 1900";
        
        String[] casts = getCast();
        String castStr = Arrays.toString(casts);

        String[] listedIn = getListedIn();
        String listedInStr = Arrays.toString(listedIn);

        System.out.println("=> " + getShowId() + " ## " + getTitle() + " ## " + getType() + " ## " +
            getDirector() + " ## " + castStr + " ## " + getCountry() + " ## " +
            dateAdded + " ## " + getReleaseYear() + " ## " + getRating() + " ## " +
            getDuration() + " ## " + listedInStr + " ##");
    }
    
    // Método para preencher a lista csv com os dados do arquivo CSV
    public static void preencher() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(arq));
            String line;
            while((line = br.readLine()) != null) {
                csv.add(line);
            }
            br.close();
        } 
        catch(IOException e) {
            System.err.println("Erro ao carregar o arquivo: " + e.getMessage());
        }
    }
    
    // Método para trocar dois elementos no array de shows
    public static void swap(Show[] shows, int i, int j) {
        Show temp = shows[i];
        shows[i] = shows[j];
        shows[j] = temp;
    }

    // Método para ordenar o array de shows 
    public void ordenar(String[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i].compareTo(array[j]) > 0) {               
                    String temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
    
    // Método para ler uma linha do CSV e preencher os atributos do show
    public void ler(String line) {
        List<String> array = new ArrayList<>();
        boolean aspas = false;
        StringBuilder str = new StringBuilder();

        // alterna o valor de aspas para lidar com campos entre aspas
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            
            if (c == '"') {
                aspas = !aspas;
            } 
            else if (c == ',' && !aspas) {
                array.add(str.toString());
                str.setLength(0);
            } 
            else {
                str.append(c);
            }
        }
        array.add(str.toString()); // Adiciona o último campo

        String[] coluns = array.toArray(new String[0]); // Converte para array

        setShowId(coluns.length > 0 && !coluns[0].isEmpty() ? coluns[0] : "NaN");
        setType(coluns.length > 1 && coluns[1].trim().equalsIgnoreCase("movie") ? "Movie" : "TV Show");
        setTitle(coluns.length > 2 && !coluns[2].isEmpty() ? coluns[2] : "NaN");
        setDirector(coluns.length > 3 && !coluns[3].isEmpty() ? coluns[3] : "NaN");
        setCast(coluns.length > 4 && !coluns[4].isEmpty() ? coluns[4].split(", ") : new String[]{"NaN"});
        if (getCast().length > 1) ordenar(getCast());
        setCountry(coluns.length > 5 && !coluns[5].isEmpty() ? coluns[5] : "NaN");
        try {
            setDateAdded(coluns.length > 6 && !coluns[6].isEmpty() ? dateFormat.parse(coluns[6]) : null);
        } catch (Exception e) {
            setDateAdded(null);
        }
        setReleaseYear(coluns.length > 7 && !coluns[7].isEmpty() ? Integer.parseInt(coluns[7]) : 0);
        setRating(coluns.length > 8 && !coluns[8].isEmpty() ? coluns[8] : "NaN");
        setDuration(coluns.length > 9 && !coluns[9].isEmpty() ? coluns[9] : "NaN");
        setListedIn(coluns.length > 10 && !coluns[10].isEmpty() ? coluns[10].split(", ") : new String[]{"NaN"});
        if (getListedIn().length > 1) ordenar(getListedIn());
    }
    
    // Converte a string de entrada no índice do CSV
    public static int converteStr(String input) {
        int valor = 0;
        int multiplicador = 1;
        for (int i = input.length() - 1; i >= 1; i--) {
            int n = input.charAt(i) - '0';
            valor += n * multiplicador;
            multiplicador *= 10;
        }
        return valor;
    }
}
