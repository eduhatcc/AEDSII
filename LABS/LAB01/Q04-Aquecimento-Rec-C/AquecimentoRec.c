#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int qtMaiusculoRec(char str[], int i) {
	int qt = 0;

	if (str[i] != '\0') {
		if (str[i] >= 65 && str[i] <= 90) {
			qt++;
		}
		qt += qtMaiusculoRec(str, i+1);
	}

	return qt;
}

int qtMaiusculo(char str[]) {
	return qtMaiusculoRec(str, 0);
}

int main() {
	char str[1000];
	fgets(str, 1000, stdin);
	str[strcspn(str, "\n")] = '\0';

	while (strcmp(str, "FIM") != 0) {
		printf("%d\n", qtMaiusculo(str));

		fgets(str, 1000, stdin);
		str[strcspn(str, "\n")] = '\0';
	}

	return 0;
}
