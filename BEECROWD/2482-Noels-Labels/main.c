#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAXN 200       
#define MAXM 200       
#define MAXLEN 100     

void imprimirLabels(char idiomas[][MAXLEN], char etiquetas[][MAXLEN], char criancas[][MAXLEN], char idiomasCriancas[][MAXLEN], int n, int m) {
	for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
            		if (strcmp(idiomasCriancas[i], idiomas[j]) == 0) {
				printf("%s\n%s\n\n", criancas[i], etiquetas[j]);
			}
		}
	}
}

int main() {
     	int n, m;
	char idiomas[MAXN][MAXLEN];
	char etiquetas[MAXN][MAXLEN];
	char criancas[MAXM][MAXLEN];
	char idiomasCriancas[MAXM][MAXLEN];

	while (scanf("%d", &n) == 1) {
		getchar();  // consome o '\n' que ficou no buffer

		for (int i = 0; i < n; i++) {
			fgets(idiomas[i], MAXLEN, stdin);
			idiomas[i][strcspn(idiomas[i], "\n")] = '\0';

			fgets(etiquetas[i], MAXLEN, stdin);
			etiquetas[i][strcspn(etiquetas[i], "\n")] = '\0';
		}

		scanf("%d", &m);
		getchar();  
		for (int i = 0; i < m; i++) {
			fgets(criancas[i], MAXLEN, stdin);
			criancas[i][strcspn(criancas[i], "\n")] = '\0';

			fgets(idiomasCriancas[i], MAXLEN, stdin);
			idiomasCriancas[i][strcspn(idiomasCriancas[i], "\n")] = '\0';
		}

		imprimirLabels(idiomas, etiquetas, criancas, idiomasCriancas, n, m);
	}

	return 0;
}

