#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
 * FUNÇÃO INVERSÃO STRING RECURSIVA
 */
void inversaoStringRec(char str[], int i, int j) {
	
	// condição de parada
	if (i < j) {
		char swap = str[i];
		str[i] = str[j];
		str[j] = swap;

		inversaoStringRec(str, i+1, j-1);
	}
}

/*
 * FUNÇÃO INVERSÃO STRING BASE QUE CHAMA A FUNÇÃO RECURSIVA
 */
void inversaoString(char str[]) {
	inversaoStringRec(str, 0, strlen(str) -1);
}

/*
 * FUNÇÃO MAIN
 */
int main() {
	char str[100];
	fgets(str, sizeof(str), stdin);
	str[strcspn(str, "\n")] = '\0';

	while (strcmp(str, "FIM")) {
		inversaoString(str);
		printf("%s\n", str);

		fgets(str, sizeof(str), stdin);
		str[strcspn(str, "\n")] = '\0';
	}

	return 0;
}
