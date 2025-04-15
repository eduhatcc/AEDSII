import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class Show {
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
    
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH); 
    private static String arq = "/tmp/disneyplus.csv";
    private static List<String> csv = new ArrayList<>();
    
    public static String log = "matricula_selecao.txt";
    public static int matricula = 874201;
    public static int comparacoes = 0;
    public static int movimentacoes = 0;
    
    public static List<String> getcsv() {
        return csv;
    }

    public Show() {}

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
        String id = (getShowId() != null && !getShowId().isEmpty()) ? getShowId() : "NaN";
        String titulo = (getTitle() != null && !getTitle().isEmpty()) ? getTitle() : "NaN";
        String tipo = (getType() != null && !getType().isEmpty()) ? getType() : "NaN";
        String diretor = (getDirector() != null && !getDirector().isEmpty()) ? getDirector() : "NaN";
        String pais = (getCountry() != null && !getCountry().isEmpty()) ? getCountry() : "NaN";
        String data = (getDateAdded() != null)
            ? new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).format(getDateAdded())
            : "NaN";
        String classificacao = (getRating() != null && !getRating().isEmpty()) ? getRating() : "NaN";
        String duracao = (getDuration() != null && !getDuration().isEmpty()) ? getDuration() : "NaN";

        String[] elenco = getCast() != null ? getCast() : new String[0];
        String elencoStr = (elenco.length == 0) ? "[NaN]" : Arrays.toString(elenco);

        String[] categorias = getListedIn() != null ? getListedIn() : new String[0];
        String categoriasStr = (categorias.length == 0) ? "[NaN]" : Arrays.toString(categorias);

        System.out.println("=> " + id + " ## " + titulo + " ## " + tipo + " ## " +
            diretor + " ## " + elencoStr + " ## " + pais + " ## " +
            data + " ## " + getReleaseYear() + " ## " + classificacao + " ## " +
            duracao + " ## " + categoriasStr + " ##");
    }
    
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
    
    public static void ordenar(String[] array) {
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
        array.add(str.toString());

        String[] coluns = array.toArray(new String[0]);

        setShowId(coluns.length > 0 ? coluns[0] : "");
        setType(coluns.length > 1 && coluns[1].trim().equalsIgnoreCase("movie") ? "Movie" : "TV Show");
        setTitle(coluns.length > 2 ? coluns[2] : "");
        setDirector(coluns.length > 3 ? coluns[3] : "");
        setCast(coluns.length > 4 && !coluns[4].equals("") ? coluns[4].split(", ") : new String[0]);
        if (getCast() != null && getCast().length > 1) ordenar(getCast());
        setCountry(coluns.length > 5 ? coluns[5] : "");
        try {
            setDateAdded(coluns.length > 6 && !coluns[6].equals("") ? dateFormat.parse(coluns[6]) : null);
        } catch (Exception e) {
            setDateAdded(null);
        }
        setReleaseYear(coluns.length > 7 && !coluns[7].equals("") ? Integer.parseInt(coluns[7]) : 0);
        setRating(coluns.length > 8 ? coluns[8] : "");
        setDuration(coluns.length > 9 ? coluns[9] : "");
        setListedIn(coluns.length > 10 && !coluns[10].equals("") ? coluns[10].split(", ") : new String[0]);
        if (getListedIn() != null && getListedIn().length > 1) ordenar(getListedIn());
    }
    
    // Converte a string de entrada no índice do CSV
    // Agora ignora o primeiro caractere ("s")
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

    public static void swap(Show[] shows, int i, int j) {
    Show temp = shows[i];
    shows[i] = shows[j];
    shows[j] = temp;
    movimentacoes += 3;
}

public static void selecaoRec(Show[] shows, int i, int j, int menor, int n) {  
    if (i >= n - 1) {
        return;
    }
    
    if (j < n) {
        comparacoes++;
        if (shows[j].getTitle().compareToIgnoreCase(shows[menor].getTitle()) < 0) {
            menor = j;
        }
        // Continua a varredura interna
        selecaoRec(shows, i, j + 1, menor, n);
    } else {
        // Se terminou a varredura interna, faz a troca
        swap(shows, i, menor);
        // Avança para a próxima posição externa
        selecaoRec(shows, i + 1, i + 2, i + 1, n);
    }
}

// Seleção base
public static void selecao(Show[] shows) {
    // Chama a recursão com i=0, j=1 e o menor inicial sendo 0
    selecaoRec(shows, 0, 1, 0, 300);
}

    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Show[] shows = new Show[1700];
        int cont = 0;

        Show.preencher();
        List<String> lines = Show.getcsv();

        while (!input.equals("FIM")) {
            int index = Show.converteStr(input);

            if (index >= 0 && index < lines.size()) {
                Show show = new Show();
                show.ler(lines.get(index));
                shows[cont++] = show;
            }
            input = sc.nextLine();
        }

        long start = System.nanoTime();
        Show.selecao(shows);
        long end = System.nanoTime();
        double tempo = (end - start) / 1e6; // em milissegundos

        for (int i = 0; i < cont; i++) {
            shows[i].imprimir();    
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(log))) {
            bw.write(String.format("%s\t%d\t%d\t%.2f\n", matricula, comparacoes, movimentacoes, tempo));
        }
        catch(Exception e) {}

        sc.close();
    }
}