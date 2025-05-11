#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

bool balanco(char str[]) {
	int esq = 0,
	    dir = 0;
	bool valido = false;

	for (int i = 0; i < strlen(str); i++) {
		if (str[i] == '(') esq++;
		else if (str[i] == ')') dir++;
	}

	if (esq == dir) valido = true;

	return valido;
}

int main() {
	char str[100];

	while (fgets(str, sizeof(str), stdin)) {
		str[strlen(str) -1] = '\0';
		
		if (balanco(str)) printf("correct\n");
		else printf("incorrect\n");
	}

	return 0;
}
