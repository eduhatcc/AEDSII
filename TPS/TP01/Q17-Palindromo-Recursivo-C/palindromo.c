#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <locale.h>
#include <string.h>

/* 
 * FUNÇÃO É PALINDROMO RECURSIVA
 */
bool ehPalindromo(char str[], int i, int j) {
	bool palindromo = false;

	// condição de parada
	if (i >= j) {
		palindromo = true;
	}

	// verifica se é palindromo.
	else if (str[i] == str[j]) {
		palindromo = ehPalindromo(str, i+1, j-1);
	}

	return palindromo;
}

/* 
 * FUNÇÃO É PALINDROMO BASE
 */
bool ehPalindromo(char str[]) {
	int TAM = strlen(str);

	//chama a função ehPalindromo recursiva
	return ehPalindromo(str, 0, TAM-1);
}

/* 
 * FUNÇÃO MAIN
 */
int main() {
	setlocale(LC_ALL, "");
	char str[100];
	fgets(str, sizeof(str), stdin);
	str[strcspn(str, "\n")] = '\0';
	
	while (strcmp(str, "FIM")) {

		//chama a função ehPalindromo base
		if (ehPalindromo(str)) {
			printf("SIM\n");
		}
		else {
			printf("NAO\n");
		}
		fgets(str, sizeof(str), stdin);
		str[strcspn(str, "\n")] = '\0';
	}

	return 0;
}
