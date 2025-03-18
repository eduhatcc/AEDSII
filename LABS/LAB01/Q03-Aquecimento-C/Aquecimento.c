#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int qtMaiusculo(char str[]) {
	int qt = 0;
	int i = 0;

	while (str[i] != '\0') {
		if (str[i] >= 65 && str[i] <= 90) {
			qt++;
		}
		i++;
	}

	return qt;
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
