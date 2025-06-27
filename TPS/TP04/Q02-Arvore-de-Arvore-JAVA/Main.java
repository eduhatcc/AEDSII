import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

// Classe Show (mantida como no código original)
class Show {
    private String show_id;
    private String type;
    private String title;
    private String director;
    private String cast;
    private String country;
    private Date date_added;
    private int release_year;
    private String rating;
    private String duration;
    private String listed_in;

    public Show() {}

    public Show(String show_id, String type, String title, String director, String cast,
                String country, Date date_added, int release_year, String rating,
                String duration, String listed_in) {
        setShow_id(show_id);
        setType(type);
        setTitle(title);
        setDirector(director);
        setCast(cast);
        setCountry(country);
        setDate_added(date_added);
        setRelease_year(release_year);
        setRating(rating);
        setDuration(duration);
        setListed_in(listed_in);
    }

    public String getShow_id() { return show_id; }
    public void setShow_id(String show_id) { this.show_id = show_id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }
    public String getCast() { return cast; }
    public void setCast(String cast) { this.cast = cast; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public Date getDate_added() { return date_added; }
    public void setDate_added(Date date_added) { this.date_added = date_added; }
    public int getRelease_year() { return release_year; }
    public void setRelease_year(int release_year) { this.release_year = release_year; }
    public String getRating() { return rating; }
    public void setRating(String rating) { this.rating = rating; }
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
    public String getListed_in() { return listed_in; }
    public void setListed_in(String listed_in) { this.listed_in = listed_in; }

    public void clone(Show outroShow) {
        setShow_id(outroShow.getShow_id());
        setType(outroShow.getType());
        setTitle(outroShow.getTitle());
        setDirector(outroShow.getDirector());
        setCast(outroShow.getCast());
        setCountry(outroShow.getCountry());
        setDate_added(outroShow.getDate_added());
        setRelease_year(outroShow.getRelease_year());
        setRating(outroShow.getRating());
        setDuration(outroShow.getDuration());
        setListed_in(outroShow.getListed_in());
    }

    public void ler(String linha) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM d,yyyy", Locale.ENGLISH);
        String[] campos = dividirLinhaCSV(linha);

        setShow_id(campos[0]);
        setType(campos[1]);
        setTitle(campos[2]);
        setDirector(campos[3]);
        setCast(campos[4]);
        setCountry(campos[5]);

        if (!campos[6].isEmpty()) {
            try {
                setDate_added(sdf.parse(campos[6]));
            } catch (Exception e) {
                setDate_added(null);
            }
        } else {
            setDate_added(null);
        }

        if (!campos[7].isEmpty()) {
            setRelease_year(Integer.parseInt(campos[7]));
        } else {
            setRelease_year(0);
        }

        setRating(campos[8]);
        setDuration(campos[9]);
        setListed_in(campos.length > 10 ? campos[10].replace("\"", "") : "NaN");
    }

    public void imprimir() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd,yyyy", Locale.ENGLISH);
        String dataFormatada = (date_added != null) ? sdf.format(date_added) : "NaN";
        String diretorFormatado = (director != null && !director.isEmpty()) ? director : "NaN";
        String castFormatado;

        if (cast != null && !cast.isEmpty()) {
            String[] nomes = cast.split(", ");
            java.util.Arrays.sort(nomes);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < nomes.length; i++) {
                sb.append(nomes[i]);
                if (i < nomes.length - 1) sb.append(", ");
            }
            castFormatado = sb.toString();
        } else {
            castFormatado = "NaN";
        }

        System.out.println("=> " + show_id + " ## " + title + " ## " + type + " ## " +
                diretorFormatado + " ## [" + castFormatado + "] ## " + country + " ## " +
                dataFormatada + " ## " + release_year + " ## " +
                rating + " ## " + duration + " ## [" + listed_in + "] ##");
    }

    public static String[] dividirLinhaCSV(String linha) {
        ArrayList<String> campos = new ArrayList<>();
        StringBuilder atual = new StringBuilder();
        boolean dentroDeAspas = false;

        for (int i = 0; i < linha.length(); i++) {
            char c = linha.charAt(i);

            if (c == '"') {
                if (dentroDeAspas && i + 1 < linha.length() && linha.charAt(i + 1) == '"') {
                    atual.append('"');
                    i++;
                } else {
                    dentroDeAspas = !dentroDeAspas;
                }
            } else if (c == ',' && !dentroDeAspas) {
                campos.add(atual.toString().trim());
                atual.setLength(0);
            } else {
                atual.append(c);
            }
        }

        campos.add(atual.toString().trim());
        return campos.toArray(new String[0]);
    }

    public static Show[] lerCSV(String nomeArq) throws Exception {
        ArrayList<Show> lista = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(nomeArq));
        String linha;
        br.readLine(); // cabeçalho

        while ((linha = br.readLine()) != null && !linha.equals("FIM")) {
            if (linha.trim().isEmpty()) continue;
            Show s = new Show();
            s.ler(linha);
            lista.add(s);
        }

        br.close();
        return lista.toArray(new Show[0]);
    }
}

// --- CLASSE DO NÓ DA ÁRVORE INTERNA (BinaryTree02) ---
class NoInterno {
    public String title; // Chave da árvore interna: título
    public NoInterno esq, dir;

    public NoInterno(String title) {
        this.title = title;
        this.esq = this.dir = null;
    }
}

// --- CLASSE DA ÁRVORE INTERNA (BinaryTree02) ---
class ArvoreInterna {
    public NoInterno raiz;
    public int comparacoes; // Contador de comparacoes para esta arvore

    public ArvoreInterna() {
        this.raiz = null;
        this.comparacoes = 0;
    }

    // Construtor para inserir o primeiro elemento ao criar a arvore
    public ArvoreInterna(String title) {
        this.raiz = new NoInterno(title);
        this.comparacoes = 0;
    }

    public void inserir(String title) {
        raiz = inserir(raiz, title);
    }

    private NoInterno inserir(NoInterno no, String title) {
        if (no == null) {
            no = new NoInterno(title);
            comparacoes++; // Uma comparacao para chegar no null e criar o no
        } else if (title.compareTo(no.title) < 0) {
            comparacoes++; // Comparacao para ir para a esquerda
            no.esq = inserir(no.esq, title);
        } else if (title.compareTo(no.title) > 0) {
            comparacoes += 2; // Duas comparacoes para ir para a direita (primeira < e segunda >)
            no.dir = inserir(no.dir, title);
        } else {
            comparacoes += 2; // Duas comparacoes se for igual (primeira < e segunda ==)
            // Elemento ja existe, nao faz nada
        }
        return no;
    }

    public boolean pesquisar(String title) {
        comparacoes = 0; // Zera comparacoes para cada nova busca na arvore interna
        return pesquisar(raiz, title);
    }

    private boolean pesquisar(NoInterno no, String title) {
        boolean resp;
        if (no == null) {
            resp = false;
            comparacoes++; // Uma comparacao para verificar se e null
        } else {
            comparacoes++; // Uma comparacao para o equals
            if (no.title.equals(title)) {
                resp = true;
            } else {
                comparacoes++; // Uma comparacao para o compareTo
                if (title.compareTo(no.title) < 0) {
                    System.out.print("esq "); // Saida de depuracao
                    resp = pesquisar(no.esq, title);
                } else {
                    System.out.print("dir "); // Saida de depuracao
                    resp = pesquisar(no.dir, title);
                }
            }
        }
        return resp;
    }
}

// --- CLASSE DO NÓ DA ÁRVORE EXTERNA (BinaryTree01) ---
class NoExterno {
    public int yearModulo; // Chave da arvore externa: releaseYear % 15
    public NoExterno esq, dir;
    public ArvoreInterna arvoreInterna; // Ponteiro para a arvore interna

    public NoExterno(int yearModulo) {
        this.yearModulo = yearModulo;
        this.esq = this.dir = null;
        this.arvoreInterna = null; // Inicialmente nula, criada na primeira insercao de titulo
    }
}

// --- CLASSE DA ÁRVORE EXTERNA (BinaryTree01) ---
class ArvoreExterna {
    public NoExterno raiz;
    public int comparacoes; // Contador de comparacoes total (arvore externa + internas)

    public ArvoreExterna() {
        this.raiz = null;
        this.comparacoes = 0;
    }

    // Metodo para inserir apenas o ano modulo 15 (para pre-popular a arvore externa)
    public void inserirAno(int yearModulo) {
        this.raiz = inserirAno(raiz, yearModulo);
    }

    private NoExterno inserirAno(NoExterno no, int yearModulo) {
        if (no == null) {
            no = new NoExterno(yearModulo);
            comparacoes++;
        } else if (yearModulo < no.yearModulo) {
            comparacoes++;
            no.esq = inserirAno(no.esq, yearModulo);
        } else if (yearModulo > no.yearModulo) {
            comparacoes += 2;
            no.dir = inserirAno(no.dir, yearModulo);
        } else {
            comparacoes += 2; // Ano ja existe, nao faz nada
        }
        return no;
    }

    // Metodo principal de insercao de Show na arvore de arvores
    public void inserir(Show novoShow) {
        int yearModulo = novoShow.getRelease_year() % 15;
        this.raiz = inserir(raiz, yearModulo, novoShow.getTitle());
    }

    private NoExterno inserir(NoExterno no, int yearModulo, String title) {
        if (no == null) {
            no = new NoExterno(yearModulo);
            no.arvoreInterna = new ArvoreInterna(title);
            comparacoes++;
            comparacoes += no.arvoreInterna.comparacoes; 
        } else if (yearModulo == no.yearModulo) {
            comparacoes++; 
            if (no.arvoreInterna == null) {
                no.arvoreInterna = new ArvoreInterna(title);
            } else {
                no.arvoreInterna.inserir(title);
            }
            comparacoes += no.arvoreInterna.comparacoes; 
        } else if (yearModulo < no.yearModulo) {
            comparacoes += 2; 
            no.esq = inserir(no.esq, yearModulo, title);
        } else { // yearModulo > no.yearModulo
            comparacoes += 3; 
            no.dir = inserir(no.dir, yearModulo, title);
        }
        return no;
    }
    
    // Metodo de pesquisa que retorna o caminho e SIM/NAO
    public String pesquisar(String title) {
        this.comparacoes = 0; // Zera o contador total de comparacoes para uma nova busca
        return pesquisar(raiz, title);
    }

    private String pesquisar(NoExterno no, String title) {
        String resultado = "";
        
        if (no == null) {
            resultado = " NAO"; // Nao encontrado em nenhum lugar
        } else {
            // Primeiro, verifique se o titulo esta na arvore interna deste no externo
            boolean encontradoNaInterna = false;
            if (no.arvoreInterna != null) {
                int comparacoesAnteriorInterna = no.arvoreInterna.comparacoes; // Guarda para calcular a diferenca
                encontradoNaInterna = no.arvoreInterna.pesquisar(title);
                this.comparacoes += no.arvoreInterna.comparacoes - comparacoesAnteriorInterna; // Soma apenas as novas comparacoes
            }
            
            if (encontradoNaInterna) {
                resultado = " SIM";
            } else {
                // Se nao encontrou na arvore interna atual, continue a busca na arvore externa
                // E imprima o caminho da arvore externa antes de descer
                
                // Tenta na sub-arvore esquerda da externa
                System.out.print("ESQ "); // Imprime o movimento na arvore externa
                resultado = pesquisar(no.esq, title); // Chama recursivamente para a esquerda
                
                // Se nao encontrou na sub-arvore esquerda, tenta na sub-arvore direita da externa
                if (resultado.endsWith(" NAO")) { // Verifica se a busca na esquerda falhou
                    System.out.print("DIR "); // Imprime o movimento na arvore externa
                    resultado = pesquisar(no.dir, title); // Chama recursivamente para a direita
                }
            }
        }
        return resultado;
    }
}


public class Main {
    public static void main(String[] args) throws Exception {
        String caminhoArquivo = "../tmp/disneyplus.csv";

        // Preencher a lista de shows a partir do CSV
        Show[] shows = Show.lerCSV(caminhoArquivo);
        
        // Instancia a ArvoreExterna (arvore de arvores)
        ArvoreExterna arvoreDeArvores = new ArvoreExterna();

        // Pre-popular a arvore externa com os anos (modulo 15)
        // Isso simula a estrutura fixa do primeiro codigo
        int[] anosIniciais = {7, 3, 11, 1, 5, 9, 13, 0, 2, 4, 6, 8, 10, 12, 14};
        for (int ano : anosIniciais) {
            arvoreDeArvores.inserirAno(ano);
        }

        Scanner sc = new Scanner(System.in);
        String entrada = sc.nextLine();

        // Insere os shows na estrutura de Arvore de Arvores
        while (!entrada.equals("FIM")) {
            for (int i = 0; i < shows.length; i++) {
                if (shows[i].getShow_id().equals(entrada)) {
                    arvoreDeArvores.inserir(shows[i]);
                    break; 
                }
            }
            entrada = sc.nextLine();
        }

        // Inicio da medicao de tempo e contagem de comparacoes para a pesquisa
        long inicio = System.nanoTime();
        
        // Pesquisa os nomes na Arvore de Arvores
        entrada = sc.nextLine();

        while (!entrada.equals("FIM")) {
            System.out.print("raiz "); // Sempre comeca da raiz
            String resultadoPesquisa = arvoreDeArvores.pesquisar(entrada);
            System.out.println(resultadoPesquisa);
            entrada = sc.nextLine();
        }

        long fim = System.nanoTime();
        long tempoExecucao = fim - inicio; // tempo total em nanosegundos

        // O total de comparacoes ja esta acumulado em arvoreDeArvores.comparacoes
        criarLog("874201", tempoExecucao, arvoreDeArvores.comparacoes);

        sc.close();
    }

    public static void criarLog(String matricula, long tempoExecucao, int comparacoes) throws Exception {
        String nomeArquivo = matricula + "_arvoreArvore.txt"; // Alterado para refletir a estrutura
        PrintWriter pw = new PrintWriter(new FileWriter(nomeArquivo));
        pw.println(matricula + "\t" + tempoExecucao + "\t" + comparacoes);
        pw.close();
    }
}
