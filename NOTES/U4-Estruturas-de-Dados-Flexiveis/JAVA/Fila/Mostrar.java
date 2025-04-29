public class Mostrar {
    /*
     * Apenas anotações, o código não é executável
     */
    void mostrar() {
        Celula i = primeiro;
        while (i.prox != null) {
            System.out.println(i.elemento);
            i = i.prox;
        }
    }
}
