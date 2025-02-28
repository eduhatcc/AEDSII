#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <locale.h>
#include <wchar.h>
#include <string.h>

bool ehPalindromo(char str[], int i, int j) {
	bool palindromo = false;

	if (i >= j) {
		palindromo = true;
	}
	else if (str[i] == str[j]) {
		palindromo = ehPalindromo(str, i+1, j-1);
		}

	return palindromo;
}

int main() {
	setlocale(LC_ALL, "");
	char str[100];
	fgets(str, sizeof(str), stdin);
	str[strcspn(str, "\n")] = '\0';
	
	while (strcmp(str, "FIM")) {
		int TAM = strlen(str);
		if (ehPalindromo(str, 0, TAM - 1)) {
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
