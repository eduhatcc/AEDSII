public class Remover {
    /*
     * Apenas anotações, o código não é executável
    */ 
    void remover() throws Exception {
        if (primeiro == ultimo)
            throw new Exception("Lista vazia");
        
        Celula tmp = primeiro.prox;
        primeiro.prox = primeiro.prox.prox;    
        int elemento = tmp.elemento;
        tmp.prox = tmp = null;

        return elemento;
    }
}
