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

    public Show() {
        setId("NaN");
        setType("NaN");
    }

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
        // Pode ser implementado se necessário
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
    
    public static void main(String[] args) {

        List<Show> shows = new ArrayList<>();
		String arq = "/tmp/disneyplus.csv";

        try {
            BufferedReader br = new BufferedReader(new FileReader(arq));
         
            String line = br.readLine(); // Lê o cabeçalho

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                
                String id = fields[0];
                String type = fields[1];
                String title = fields[2];
                String director = fields[3].isEmpty() ? "NaN" : fields[3];
                
                ArrayList<String> cast = new ArrayList<>();
                if (fields[4].isEmpty()) {
                    cast.add("NaN");
                } else {
                    cast.addAll(Arrays.asList(fields[4].split(", ")));
                }
                
                String country = fields[5].isEmpty() ? "NaN" : fields[5];
                
                Date date_added = null;
                try {
                    String dataLimpa = fields[6].replace("\"", "").trim();
                    date_added = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(dataLimpa);
                } catch (ParseException e) {}
                
                int release_year = Integer.parseInt(fields[7]);
                
                String rating = fields[8].isEmpty() ? "NaN" : fields[8];
                String duration = fields[9].isEmpty() ? "NaN" : fields[9];
                ArrayList<String> listed_in = new ArrayList<>();
                if (fields[10].isEmpty()) {
                    listed_in.add("NaN");
                } else {
                    listed_in.addAll(Arrays.asList(fields[10].split(", ")));
                }
                
                Show show = new Show(id, type, title, director, cast, country, date_added, release_year, rating, duration, listed_in);
                shows.add(show);
            }
            br.close();
        } catch (IOException e) {
			System.out.println("Erro ao ler o arquivo: " + e.getMessage());
		}
        
        Scanner sc = new Scanner(System.in);
        String entrada;
        while (!(entrada = sc.nextLine()).equals("FIM")) {
            Show showEncontrado = buscarShowId(shows, entrada);
            if (showEncontrado != null)
                System.out.println("=> " + formatShow(showEncontrado));
        }
        sc.close();
    }
}
