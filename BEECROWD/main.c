#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {
	int n = 0;
	scanf("%d", &n);

	char nome[20][n];
	int p[n],
	    k[n],
	    m[n];

	for (int i = 0; i < n; i++) {
		scanf("%s", &nome[][i]);
		scanf("%d", &p[i]);
		scanf("%d", &k[i]);
		scanf("%d", &m[i]);
	}

	int maiorPoder = 0;
	for (int i = 0; i < n; i++) {
		int maior = i;
		for (int j = i+1; j < n; j++) {
			if (p[j] > p[maior]) {
				maior = j;
			} // fim if p >
			else if (p[j] == p[maior]) {
				if (k[j] > k[maior]) {
					maior = j;
				} // fim if k >
				else if (k[j] == k[maior]) {
					if (m[j] < m[maior]) {
						maior = j;
					} // fim if m <
				} // fim else if k ==
			} // fim else if p ==
		} // fim for j

		maiorPoder = maior;
	} // fim for i

	  
	printf("%s", nome[][maior]);

	return 0;
}
