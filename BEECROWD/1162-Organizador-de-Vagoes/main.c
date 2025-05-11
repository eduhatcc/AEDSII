#include <stdio.h>
#include <stdlib.h>

void swap(int vagao[], int i, int j) {
	int tmp = vagao[i];
	vagao[i] = vagao[j];
	vagao[j] = tmp;
}

int bubblesort(int vagao[], int l) {
	int swaps = 0;

	for (int i = 0; i < l; i++) {
		for (int j = i+1; j < l; j++) {
			if (vagao[i] > vagao[j]) {
				swap(vagao, i, j);
				swaps++;
			}
		}
	}

	return swaps;
}

int main() {
	int n = 0,
	    l = 0;
	int vagao[50];

	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		scanf("%d", &l);
		for (int j = 0; j < l; j++) {
			scanf("%d", &vagao[j]);
		}

		printf("Optimal train swapping takes %d swaps.\n", bubblesort(vagao, l));
	}

	return 0;
}
