#include <stdio.h>
#include <stdlib.h>

void swap(int array[], int i, int j) {
	int tmp = array[i];
	array[i] = array[j];
	array[j] = tmp;
}

void ordenaArray(int array[], int N) {
	for (int i = 0; i < N; i++) {
		int menor = i;
		for (int j = i+1; j < N; j++) {
			if (array[j] < menor) {
				menor = j;
			}
		} 
		swap(array, i, menor);
	}
}

int pesquisaBinaria(int array[], int esq, int dir, int Q, int src, int i) {
	int resp = 0,
	    meio = (esq+dir) / 2;

	if (esq <= dir && i < Q) {
		if (array[meio] == src) {
			resp = meio+1;
		}
		else if (array[meio] < src){
			resp = pesquisaBinaria(array, meio+1, dir, Q, src, i++);
		}
		else {
			resp = pesquisaBinaria(array, esq, meio-1, Q, src, i++);
		}
	}

	return resp;
}

int main() {
	int N = 0,
	    Q = 0,
	    i = 0,
	    resp = 0,
	    caso = 1;
	int src[65];
	int array[65];

	scanf("%d", &N);
	scanf("%d", &Q);

	while (N > 0 && Q > 0) {
		while (i < N) {
			scanf("%d", &array[i]);
			i++;
		}

		ordenaArray(array, N);

		i = 0;
		while (i < Q) {
			scanf("%d", &src[i]);
			i++;
		}

		i = 0;

		printf("CASE# %d:\n", caso);
		while (i < Q) {
			resp = pesquisaBinaria(array, 0, N, Q, src[i], 0);

			if (resp == 0) {
				printf("%d not found\n", src[i]);
			}
			else {
				printf("%d found at %d\n", src[i], resp);
			}

			i++;
		}
		i = 0; caso++;
		scanf("%d", &N);
		scanf("%d", &Q);
	}

	return 0;
}
