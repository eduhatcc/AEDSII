#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
 * FUNÇÃO SOMA DIGITOS RECURSIVA
 */
int somaDigitos(char str[], int i) {
	int soma = 0;

	if (i >= 0) {
		soma = str[i] - '0';
		soma += somaDigitos(str, i-1);
	}

	return soma;
}

/*
 * FUNÇÃO SOMA DIGITOS BASE QUE CHAMA A FUNÇÃO RECURSIVA
 */

int somaDigitos(char str[]) {
	return somaDigitos(str, strlen(str) -1);
}

/*
 * FUNÇÃO MAIN
 */
int main() {
	char str[100];
	fgets(str, sizeof(str), stdin);
	str[strcspn(str, "\n")] = '\0';
	
	while (strcmp(str, "FIM")) {
		printf("%d\n", somaDigitos(str));
		fgets(str, sizeof(str), stdin);
		str[strcspn(str, "\n")] = '\0';
	}

	return 0;
}
