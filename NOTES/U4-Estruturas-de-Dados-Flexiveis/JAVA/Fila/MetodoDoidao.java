public class MetodoDoidao {
    /*
     * Apenas anotações, o código não é executável
     */

    // O METÓDO DOIDÃO NÃO FAZ NADA. APENAS REESCREVA A LISTA.
    void metodoDoidao() {
        Celula fim = ultimo;
        while (fim != ultimo) {
            ultimo.prox = new Celular(primeiro.prox.elemento);
            Celula tmp = primeiro;
            primeiro = primeiro.prox;
            tmp = tmp.prox = null;
            ultimo = ultimo.prox;
        }
        fim = null;
}
