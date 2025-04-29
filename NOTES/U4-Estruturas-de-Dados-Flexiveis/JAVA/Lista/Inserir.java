public class Inserir {
    /*
     * Apenas anotações, o código não é executável
     */
    public void inserirMeio() {
        Celula i = primeiro;
        for (int j = 0; j < pos; j++) {
            i = i.prox;
        }
        Celula tmp = new Celula(x);
        tmp.prox = i.prox;
        i.prox = tmp;
        tmp = i = null;
    }

    public void inserir() throws Exception {
        int tamanho = tamanho(); // tem que implemtar a função tamanho
        if (pos < 0 || pos > tamanho) 
            throw new Exception("Erro!");
        else if (po == 0)
            inserirInicio();
        else if (pos == tamanho)
            inserirFim();
        else
            inserirMeio();
    }
}