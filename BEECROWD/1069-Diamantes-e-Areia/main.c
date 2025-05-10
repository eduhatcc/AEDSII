#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int diamantes(char str[]) {
	int diamantes = 0,
	    esq = 0,
	    dir = 0,
	    i = 0;

	while (i < strlen(str)) {
		if (str[i] == '<') esq++;
		else if(str[i] == '>') dir++;
		i++;
	}

	while (esq > 0 && dir > 0) {
		diamantes++; esq--; dir--;
	}

	return diamantes;
}

int main() {
	int n = 0;
	scanf("%d", &n);
	getchar();
		
	char str[1000];
	for (int i = 0; i < n; i++) {
		fgets(str, sizeof(str), stdin);
		str[strlen(str) -1] = '\0';

		printf("%d\n", diamantes(str));
	}

	return 0;
}
