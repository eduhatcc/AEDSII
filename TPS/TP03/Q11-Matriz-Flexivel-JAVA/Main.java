import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int casos = sc.nextInt();

        for (int c = 0; c < casos; c++) {
            int lin = sc.nextInt();
            int col = sc.nextInt();
            int[][] valores = new int[lin][col];
            for (int i = 0; i < lin; i++) {
                for (int j = 0; j < col; j++) {
                    valores[i][j] = sc.nextInt();
                }
            }

            // cria a primeira matriz
            MatrizFlexivel matriz1 = new MatrizFlexivel(lin, col, valores);
            matriz1.mostrarDiagonalPrincipal();
            matriz1.mostrarDiagonalSecundaria();

            // Leitura da segunda matriz
            lin = sc.nextInt();
            col = sc.nextInt();
            valores = new int[lin][col];
            for (int i = 0; i < lin; i++) {
                for (int j = 0; j < col; j++) {
                    valores[i][j] = sc.nextInt();
                }
            }
            MatrizFlexivel matriz2 = new MatrizFlexivel(lin, col, valores);

            // Soma e multiplicação
            MatrizFlexivel soma = matriz1.soma(matriz2);
            if (soma != null) {
                soma.imprimir();
            }
            MatrizFlexivel prod = matriz1.multiplicacao(matriz2);
            if (prod != null) {
                prod.imprimir();
            }
        }

        sc.close();
    }
}

class Celula {
    Celula up, down, left, dir;
    int valor;

    public Celula() {
        this.up = null;
        this.down = null;
        this.left = null;
        this.dir = null;
        this.valor = 0;
    }

    public Celula(Celula up, Celula down, Celula left, Celula dir, int valor) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.dir = dir;
        this.valor = valor;
    }

    public Celula(int valor) {
        this.valor = valor;
    }
}

class MatrizFlexivel {
    Celula inicio;
    int col;
    int lin;

    public MatrizFlexivel() {
        this.col = 0;
        this.lin = 0;
        this.inicio = null;
    }

    public MatrizFlexivel(int lin, int col, int[][] valores) {
        this.col = col;
        this.lin = lin;

        this.inicio = new Celula(valores[0][0]);
        Celula anterior = null;

        for (int i = 0; i < lin; i++) {
            Celula celLin = (i == 0) ? this.inicio : new Celula(valores[i][0]);
            celLin.up = anterior;
            if (anterior != null) {
                anterior.down = celLin;
            }

            Celula aux = celLin;
            for (int j = 1; j < col; j++) {
                Celula tmp = new Celula(valores[i][j]);
                tmp.left = aux;
                aux.dir = tmp;

                if (anterior != null) {
                    tmp.up = aux.up.dir;
                    aux.up.dir.down = tmp;
                }

                aux = tmp;
            }
            anterior = celLin;
        }
    }

    public MatrizFlexivel soma(MatrizFlexivel m) {
        if (this.lin != m.lin || this.col != m.col) return null;

        int[][] resultado = new int[lin][col];
        Celula linha1 = this.inicio;
        Celula linha2 = m.inicio;

        for (int i = 0; i < lin; i++) {
            Celula coluna1 = linha1;
            Celula coluna2 = linha2;
            for (int j = 0; j < col; j++) {
                resultado[i][j] = coluna1.valor + coluna2.valor;
                coluna1 = coluna1.dir;
                coluna2 = coluna2.dir;
            }
            linha1 = linha1.down;
            linha2 = linha2.down;
        }

        return new MatrizFlexivel(lin, col, resultado);
    }

    public MatrizFlexivel multiplicacao(MatrizFlexivel m) {
        if (m.lin != this.col) return null;

        int[][] resultado = new int[this.lin][m.col];
        int i = 0;
        for (Celula atual = this.inicio; atual != null; atual = atual.down, i++) {
            int j = 0;
            for (Celula colM = m.inicio; colM != null; colM = colM.dir, j++) {
                int soma = 0;
                Celula pAux = atual;
                for (Celula linM = colM; linM != null; linM = linM.down) {
                    soma += pAux.valor * linM.valor;
                    pAux = pAux.dir;
                }
                resultado[i][j] = soma;
            }
        }

        return new MatrizFlexivel(this.lin, m.col, resultado);
    }

    void mostrarDiagonalPrincipal() {
        Celula atual = this.inicio;
        while (atual != null && atual.down != null) {
            System.out.print(atual.valor + " ");
            atual = atual.down.dir;
        }
        System.out.println(atual.valor + " ");
    }

    void mostrarDiagonalSecundaria() {
        Celula atual = this.inicio;
        // vai até a última coluna da primeira linha
        while (atual.dir != null) {
            atual = atual.dir;
        }
        // desce pela diagonal secundária
        while (atual != null && atual.down != null) {
            System.out.print(atual.valor + " ");
            atual = atual.down.left;
        }
        System.out.println(atual.valor + " ");
    }

    void imprimir() {
        for (Celula linha = this.inicio; linha != null; linha = linha.down) {
            for (Celula coluna = linha; coluna != null; coluna = coluna.dir) {
                System.out.print(coluna.valor + " ");
            }
            System.out.println();
        }
    }
}
