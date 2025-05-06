#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
	char nome[50];
	int peso;
} Levantamento;

void swap(Levantamento array[], int i, int j) {
	Levantamento tmp = array[i];
	array[i] = array[j];
	array[j] = tmp;

	/*
	char tmpNome[50];
	tmpNome = array[i].nome;
	array[i].nome = array[j].nome;
	array[j].nome = tmpNome;

	int tmpPeso = array[i].peso;
	array[i].peso = array[j].peso;
	array[j].peso = tmpPeso;
	*/
}

void ordenar(Levantamento array[], int n) {
	for (int i = 0; i < n; i++) {
		int maior = i;

		for (int j = i+1; j < n; j++) {
			if (array[j].peso > array[maior].peso) {
				maior = j;
			}
			else if (array[j].peso == array[maior].peso) {
				if (strcmp(array[j].nome, array[maior].nome) < 0) {
					maior = j;
				}
			}
		}
		swap(array, i, maior);
	}
}

void mostrar(Levantamento array[], int n) {
	for (int i = 0; i < n; i++) {
		printf("%s %d\n", array[i].nome, array[i].peso);
	}
}

int main () {
	int n = 0;
	do {
		scanf("%d", &n);
	} while (n < 1 || n > 100);

	Levantamento array[n];

	int i = 0;
	while (scanf("%49s %d", array[i].nome, &array[i].peso) == 2) i++;

	ordenar(array, n);
	
	mostrar(array, n);

	return 0;
}
