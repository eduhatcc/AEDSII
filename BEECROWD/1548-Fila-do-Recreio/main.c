#include <stdio.h>
#include <stdlib.h>

int qtNaoTrocou(int p1[], int p2[], int m) {
	int qt = 0;

	for (int i = 0; i < m; i++) {
		if (p1[i] == p2[i]) qt++;
	}

	return qt;
}

void swap(int array[], int i, int j) {
	int tmp = array[i];
	array[i] = array[j];
	array[j] = tmp;
}

void selectionSort(int p[], int m) {
	int maior = 0;
	for (int i = 0; i < m; i++) {
		maior = i;
		for (int j = i+1; j < m; j++) {
			if (p[j] > p[maior]) maior = j;
		}
		swap(p, i, maior);
	}
} 

void copiarArray(int p1[], int p2[], int m) {
	for (int i = 0; i < m; i++) {
		p2[i] = p1[i];
	}
}

void preencherArray(int array[], int m) {
	for (int i = 0; i < m; i++) {
		scanf("%d", &array[i]);
	}
}

int main() {
	int n = 0,
	    m = 0;
	int p1[1000];
	int p2[1000];

	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		scanf("%d", &m); 
		
		preencherArray(p1, m);

		copiarArray(p1, p2, m);

		selectionSort(p2, m);

		printf("%d\n", qtNaoTrocou(p1, p2, m));
	}

	return 0;
}
