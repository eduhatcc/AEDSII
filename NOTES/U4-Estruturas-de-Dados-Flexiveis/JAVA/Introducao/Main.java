class Celula {
    public int elemento;
    public Celula prox;
    public Celula() {
        setElemento(0);
    }
    public Celula(int elemento) {
        setElemento(elemento);
        setProx(null);
    }
    
    public void setElemento(int elemento) {
        this.elemento = elemento;
    } 
    public int getElemento() {
        return this.elemento;
    }

    public void setProx(Celula prox) {
        this.prox = prox;
    }
    public Celula getProx() {
        return this.prox;
    }
}

public class Main {
    public static void main(String[] args) {
        Celula c1 = new Celula(10);
        Celula c2 = new Celula(20);
        c1.prox = c2;
        
        //Celula cNull = new Celula();
        //System.out.println("Elemento da célula nula: " + cNull.elemento);

        System.out.println("Elemento da primeira célula: " + c1.elemento);
        System.out.println("Elemento da segunda célula: " + c1.prox.elemento);
    }
}
