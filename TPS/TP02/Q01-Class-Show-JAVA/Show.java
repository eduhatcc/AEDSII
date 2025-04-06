import java.util.*;

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
		setTitle("NaN");
		setDirector("NaN");
		setCast("NaN";
		setCountry("NaN");
		setDate("NaN");
		setReleaseYear("NaN");
		setRating("NaN");
		setDuration("NaN");
		setListedIn("NaN");
	}

	public Show(String id, String type, String title, String director, String cast[], String country,
				Date date, int release_year, String rating, String duration, String listed_in[]) {
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
		int n = 0;
	}
	
	public void setListedIn(String listed_in[]) {

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

	public void setDate(Date date) {

	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public void setCast(String cast) {
	
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
