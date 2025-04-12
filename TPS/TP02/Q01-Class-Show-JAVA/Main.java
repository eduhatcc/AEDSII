import java.util.*;
import java.io.*;
import java.text.*;

class Show {
    private String show_id;
    private String type;
    private String title;
    private String director;
    private ArrayList<String> cast;
    private String country;
    private Date date_added;
    private int release_year;
    private String rating;
    private String duration;
    private ArrayList<String> listed_in;

    public Show() {}

    public Show(String id, String type, String title, String director, ArrayList<String> cast, String country,
                Date date, int release_year, String rating, String duration, ArrayList<String> listed_in) {
        setId(id);
        setType(type);
        setTitle(title);
        setDirector(director);
        setCast(cast);
        setCountry(country);
        setDate(date);
        setReleaseYear(release_year);
        setRating(rating);
        setDuration(duration);
        setListedIn(listed_in);
    }
    
    public void imprimir() {
        System.out.println("=> " +
            this.show_id + " ## " +
            this.title + " ## " +
            this.type + " ## " +
            (this.director.isEmpty() ? "NaN" : this.director) + " ## " +
            (this.cast.length == 0 ? "[NaN]" : Arrays.toString(this.cast)) + " ## " +
            (this.country.isEmpty() ? "NaN" : this.country) + " ## " +
            (this.date_added != null ? new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).format(this.date_added) : "NaN") + " ## " +
            this.release_year + " ## " +
            this.rating + " ## " +
            this.duration + " ## " +
            (this.listed_in.length == 0 ? "[NaN]" : Arrays.toString(this.listed_in)) + " ##"
        );
    }

    public void setListedIn(ArrayList<String> listed_in) {
        this.listed_in = listed_in;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setReleaseYear(int release_year) {
        this.release_year = release_year;
    }

    public void setDate(Date date_added) {
        this.date_added = date_added;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    private void setDirector(String director) {
        this.director = director;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(String id) {
        this.show_id = id;
    }

    public String getShowId() {
        return this.show_id;
    }

    public String getType() {
        return this.type;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDirector() {
        return this.director;
    }

    public ArrayList<String> getCast() {
        return this.cast;
    }

    public String getCountry() {
        return this.country;
    }

    public Date getDate() {
        return this.date_added;
    }

    public int getReleaseYear() {
        return this.release_year;
    }

    public String getRating() {
        return this.rating;
    }

    public String getDuration() {
        return this.duration;
    }

    public ArrayList<String> getListedIn() {
        return this.listed_in;
    }
}

public class Main {
    private static final String arq = "/tmp/disneyplus.csv";
	private static final List<String> CsvLines = new ArrayList<>();
    static List<Show> shows = new ArrayList<>();

    // Método para buscar um Show pelo ID na lista
    public static Show buscarShowId(List<Show> shows, String id) {
        for (Show show : shows) {
            if (show.getShowId().equals(id)) {
                return show;
            }
        }
        return null;
    }
    
    // Método para formatar a saída de um Show
    public static String formatShow(Show show) {
        StringBuilder sb = new StringBuilder();
        sb.append(show.getShowId()).append(" ## ");			
        sb.append(show.getTitle()).append(" ## ");
        sb.append(show.getType()).append(" ## ");
        sb.append((show.getDirector() == null || show.getDirector().isEmpty()) ? "NaN" : show.getDirector()).append(" ## ");
        // Lista de atores
        sb.append((show.getCast() == null || show.getCast().isEmpty()) ? "NaN" : show.getCast()).append(" ## ");
        sb.append((show.getCountry() == null || show.getCountry().isEmpty()) ? "NaN" : show.getCountry()).append(" ## ");
        if (show.getDate() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
            sb.append(sdf.format(show.getDate()));
        } else {
            sb.append("NaN");
        }
        sb.append(" ## ");
        sb.append(show.getReleaseYear()).append(" ## ");
        sb.append((show.getRating() == null || show.getRating().isEmpty()) ? "NaN" : show.getRating()).append(" ## ");
        sb.append((show.getDuration() == null || show.getDuration().isEmpty()) ? "NaN" : show.getDuration()).append(" ## ");
        sb.append((show.getListedIn() == null || show.getListedIn().isEmpty()) ? "NaN" : show.getListedIn()).append(" ## ");
        return sb.toString();
    }
    
    public static List<String> parseCSVLine(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder str = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '\"') {
                inQuotes = !inQuotes; // alterna estado
            } 
            else if (c == ',' && !inQuotes) {
                fields.add(str.toString().trim());
                fields.setLength(0); 
            } 
            else {
                str.append(c);
            }
        }
        fields.add(str.toString().trim()); // último campo
        return fields;
    }

    public static List<String> getCsvLines() {
        return CsvLines;
    }

    public static int converteStr(String input) {
        int valor = 0;
        int multiplicador = 1;
        for (int i = input.length() - 1; i > 0; i--) {
            int numero = input.charAt(i) - '0';
            valor += numero * multiplicador;
            multiplicador *= 10;
        }
        return valor;
    }

    public static void preencher() {
        try {
            
            BufferedReader br = new BufferedReader(new FileReader(arq));
            String line;
    
            while((line = br.readLine()) != null) {
                CsvLines.add(line);
            } 
            br.close();
        } catch(IOException e) {
            System.err.println("Erro ao carregar o arquivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
	    String input = sc.nextLine();
	    Show[] shows = new Show[1500];
	    int cont = 0;

	    Show.preencher();
	    List<String> line = Show.getCsvLines();

        while(input.equals("FIM")) {
            int index = converteStr(input);
            if(index >=0 && index <lines.size()) {
                Show show = new Show();
                show.preencher(line.get(index));
                shows[cont++] = show;
            }

            input = sc.nextLine();
        }

        for(int i=0; i < cont; i++) {
            shows[i].imprimir();	
        }

        sc.close();
    }
}
