import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

class Show {
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

	public String[] getCast() {
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

	public String[] getListedIn() {
		return this.listed_in;
	}
}

public class Main {
	public static void main(String[] args) {
		

		try (BufferedReader bw = new BufferedWriter(new FileWriter(path))){
			bw.write
			
		} catch (Exception e) {
			System.out.println("Error:");
		}

		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();

		while (!sc.equals("FIM")) {
			int n = Integer.parseInt(input);
			shows.get(n - 1).imprimir();
            input = sc.nextLine();
		}
		sc.close();
	}
}