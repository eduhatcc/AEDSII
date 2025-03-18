#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#define MAX 100

void combinador(char str[], char str1[], char str2[]) {
	int i=0,
	    j=0,
	    k=0;
	bool fim = false;

	while (!fim) {
		if (str1[i] != '\0') {
			str[k] = str1[i];
			k++; 
			i++;
		}
		if (str2[j] != '\0') {
			str[k] = str2[j];
			k++;
			j++;
		}
		if (str1[i] == '\0' && str2[j] == '\0') {
			str[k] = '\0';
			fim = true;
		}
	}
}

int main() {
	char str1[MAX];
	char str2[MAX];
	char str[MAX*2];

	while (scanf("%s %s", str1, str2) == 2) {
		combinador(str, str1, str2);
		
		printf("%s\n", str);
	}

	return 0;
}
