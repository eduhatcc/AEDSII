import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.*;

public class Main {
    // Função para comparar duas datas
    // Retorna 0 se forem iguais, -1 se dateA < dateB e 1 se dateA > dateB
    public static int datecmp(LocalDate dateA, LocalDate dateB) {
        return dateA.compareTo(dateB);
    }

    // Função de comparação para o quicksort
    // Retorna -1 se dateA < dateB, 0 se forem iguais e 1 se dateA > dateB
    public static int validacao(Show dateA, Show dateB) {
        int resp = 0;

        LocalDate localDateA = dateA.getDateAdded() != null ? dateA.getDateAdded().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : LocalDate.MIN;
        LocalDate localDateB = dateB.getDateAdded() != null ? dateB.getDateAdded().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : LocalDate.MIN;
                                                                                                                    
        int date = datecmp(localDateA, localDateB);

        if (date != 0) {
            resp = date;
            comparacoes++;
        }
        else {
            resp = dateA.getTitle().compareToIgnoreCase(dateB.getTitle());
            comparacoes += 2;
        }

        return resp;
    }

    // Método recursivo para realizar o QuickSort
    public static void quicksort(Celula esq, Celula dir) {
        if (esq != null && dir != null && esq != dir && esq.getAnt() != dir) {
            Show pivo = dir.getElemento();
            Celula i = esq.getAnt();
            Celula j = esq;

            while (j != dir) {
                comparacoes++; 
                if (validacao(j.getElemento(), pivo) < 0) {
                    i = (i == null) ? esq : i.getProx();

                    Show tmp = i.getElemento();
                    i.setElemento(j.getElemento());
                    j.setElemento(tmp);
                    movimentacoes += 3;
                }
                j = j.getProx();
            }

            i = (i == null) ? esq : i.getProx();
            comparacoes++;

            Show tmp = i.getElemento();
            i.setElemento(dir.getElemento());
            dir.setElemento(tmp);
            movimentacoes += 3;

            if (i != null && i.getAnt() != null) {
                comparacoes++;
                quicksort(esq, i.getAnt());
            }
            if (i != null && i.getProx() != null) {
                comparacoes++;
                quicksort(i.getProx(), dir);
            }
        }
    }

    // Método para realizar o QuickSort na lista
    public static void quicksort(Lista l) {
        quicksort(l.getPrimeiro().getProx(), l.getUltimo().getAnt());
    }

    // Main
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

        // Inicia lista e lê IDs iniciais até "FIM"
        Lista lista = new Lista();
        String line = sc.nextLine();
        while (!line.equals("FIM")) {
            int cont = Show.converteStr(line) - 1;
            if (cont >= 0 && cont < shows.length) {
                lista.inserir(shows[cont].clone());
            }
            line = sc.nextLine();
        }

        long start = System.nanoTime();
        quicksort(lista);
        long end = System.nanoTime();
        double tempo = (end - start) / 1e6; // em milissegundos

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(log))) {
            bw.write(String.format("%s\t%d\t%d\t%.2f\n", matricula, comparacoes, movimentacoes, tempo));
        }
        catch(Exception e) {}

        lista.mostrar();

        sc.close();
    }

    public static String log = "874201_quicksort3.txt";
    public static int matricula = 874201;
    public static int comparacoes = 0;
    public static int movimentacoes = 0;
}


class Lista {
    private Celula primeiro;
    private Celula ultimo;
    private int n;

    public Lista() {
        primeiro = new Celula();
        ultimo = new Celula();
        primeiro.setProx(ultimo);
        ultimo.setAnt(primeiro);
        n = 0;
    }

    public Celula getPrimeiro() {
        return primeiro;
    }

    public Celula getUltimo() {
        return ultimo;
    }

    public void inserir(Show s) {
        Celula tmp = new Celula(s);
        Celula p = primeiro.getProx();

	    tmp.setProx(p);
	    tmp.setAnt(primeiro);
        primeiro.setProx(tmp);
        p.setAnt(tmp);

        n++;
    }

    public Show remover() {
        if (n == 0) return null;
        
        Celula tmp = primeiro.getProx();
	    Show rem = tmp.getElemento();
        Celula p = tmp.getProx();

        primeiro.setProx(p);
        p.setAnt(primeiro);

        n--;

        return rem;
    }

    public int tamanho() {
	    return n;
    }

    public void mostrar() {
        for (Celula i = primeiro.getProx(); i != ultimo; i = i.getProx()) {
	        i.getElemento().imprimir();
        }
    }
}

class Celula {
	private Show elemento;
	private Celula prox;
    private Celula ant;

	public Celula() {
		setElemento(null);
		setProx(null);
        setAnt(null);
	}

	public Celula(Show s) {
		setElemento(s);
		setProx(null);
        setAnt(null);
	}

	public void setElemento(Show s) {
		this.elemento = s;
	}
	public Show getElemento() {
		return this.elemento;
	}

	public void setProx(Celula prox) {
		this.prox = prox;
	}
	public Celula getProx() {
		return this.prox;
	}

    public void setAnt(Celula ant) {
        this.ant = ant;
    }
    public Celula getAnt() {
        return this.ant;
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
