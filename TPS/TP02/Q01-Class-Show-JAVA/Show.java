import java.util.*;

class Show {
	private String show_id;
	private String title;
	private String director;
	private String cast[];
	private int cast.TAM;
	private String country;
	private Date date_added;
	private int release_year;
	private String duration;
	private String listed_in[];

	public Show() {
		setId("Vazio");
		setTitle("Titulo não informado");
		setDirector("Diretor não informado");
		setCast("Não informado", 0);
		setCoutry("Cidade não informada");
		setDate("Data não informada");
		setReleaseYear("Ano não informado");
		setDuration("Não informado");
		setListed_in("Não informado", 0);
	}

	public Show(String id, String title, String director, String cast[], String coutry,
				String date, String release_year, String duration, String listed_in[]) {
		setId("Vazio");
		setTitle("Titulo não informado");
		setDirector("Diretor não informado");
		setCast("Não informado", 0);
		setCoutry("Cidade não informada");
		setDate("Data não informada");
		setReleaseYear("Ano não informado");
		setDuration("Não informado");
		setListed_in("Não informado", 0);
		int n = 0;
	}
	
	public void setDate()

	public void setCountry(String country) {
		this.country = country;
	}
	
	public void setCast(String cast[]) {
	
	}

	private void setDirector(String director) {
		this.director = director;
	}

	public void setTitle(String title) {
		this.show_id = id;
	}

	public void setId(String id) {
		this.show_id = id;
	}




}
