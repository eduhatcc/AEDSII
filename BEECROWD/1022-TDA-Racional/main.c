#include <stdio.h>
#include <stdlib.h>

void TDARacional(int N1, int D1, int N2, int D2, char c, char operador) {
	int totalN,
	    totalD,
	    dividir = N1*D1;

	if (operador == '+') {
		totalN = (N1*D2 + N2*D1);
		totalD = (D1*D2);
		printf("%d%c%d = %d%c%d\n", totalN, c, totalD, (totalN/dividir), c, (totalD/dividir));
	}
	else if (operador == '-') {
		totalN = (N1*D2 - N2*D1);
		totalD = (D1*D2);
		printf("%d%c%d = %d%c%d\n", totalN, c, totalD, (totalN/dividir), c, (totalD/dividir));
	
	}
	else if (operador == '*') {
		totalN = (N1*N2);
		totalD = (D1*D2);
		printf("%d%c%d = %d%c%d\n", totalN, c, totalD, (totalN/dividir), c, (totalD/dividir));

	}
	else if (operador == '/') {
		totalN = (N1*D2);
		totalD = (N2*D1);
		printf("%d%c%d = %d%c%d\n", totalN, c, totalD, (totalN/dividir), c, (totalD/dividir));
	}
}

int main() {
	int N = 0,
	    N1 = 0,
	    N2 = 0,
	    D1 = 0,
	    D2 = 0;
	char operador,
	     fracao;

	scanf("%d", &N);

	for (int i = 0; i < N; i++) {
		scanf("%d", &N1);
		scanf(" %c", &fracao);
		scanf("%d", &D1);

		scanf(" %c", &operador);

		scanf("%d", &N2);
		scanf(" %c", &fracao);
		scanf("%d", &D2);

		TDARacional(N1, D1, N2, D2, fracao, operador);
	}

	return 0;
}
