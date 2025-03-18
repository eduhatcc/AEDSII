#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

/*
 * STRUCT
 */
typedef struct {
	int max;
	int *array;
	int n;
} PilhaLinear;

/*
 * CONSTRUTOR 
 */
PilhaLinear* newPilhaLinear(int max) {
	PilhaLinear *pilha = (PilhaLinear*) malloc(sizeof(PilhaLinear));
	pilha->array = (int*) malloc(max * sizeof(int));

	pilha->max = max;
	pilha->n = 0;
	
	return pilha;
}

/*
 * DESALOCAR PILHA
 */
void delPilhaLinear(PilhaLinear* pilha) {
	if (pilha) {
		if (pilha->array) {
			free(pilha->array);
		}
		free(pilha);
	}
}

/*
 * EMPILHAR
 */
void empilhar(PilhaLinear* pilha, int elem) {
	if (pilha->n >= pilha->max) {
		printf("\nErro! Pilha cheia!\n");
	}
	else {
		pilha->array[pilha->n] = elem;
		pilha->n++;
	}
}

/*
 * DESEMPILHAR
 */
int desempilhar(PilhaLinear* pilha) {
	int resp = -1;
	if (pilha->n == 0) {
		printf("\nErro! Pilha vazia!\n");
	}
	else {
		pilha->n--;
		resp = pilha->array[pilha->n];
	}

	return resp;
}

/*
 * MOSTRAR PILHA
 */
void mostrar(PilhaLinear* pilha) {
	if (pilha->n == 0) {
		printf("\nErro! Pilha vazia!\n");
	}
	else {
		printf("\n");
		for (int i=0; i < pilha->n; i++) {
			printf("%d ", pilha->array[i]);
		}
		printf("\n");
	}
}

/*
 * PESQUISAR ELEMENTO
 */
bool pesquisar(PilhaLinear* pilha, int elem) {
	bool encontrou = false;
	if (pilha->n == 0) {
		printf("\nErro! Pilha vazia!\n");
	}
	else {
		for (int i=0; i < pilha->n; i++) {
			if (pilha->array[i] == elem) {
				encontrou = true;
			i = pilha->n;	
			}
		}
	}

	return encontrou;
}


int main() {
	int max;
	printf("\nDigite o tamanho da pilha: ");
	scanf("%d", &max);

	PilhaLinear *pilha = newPilhaLinear(max);

	int opcao = 0,
	    elem = 0;
	bool fim = false;
	while (!fim) {
		printf("\n!===== MENU =====!\n");
		printf("1 - Deletar Pilha\n");
		printf("2 - Empilhar\n");
		printf("3 - Desempilhar\n");
		printf("4 - Mostrar Pilha\n");
		printf("5 - Pesquisar Elemento\n");
		printf("0 - Sair\n");

		printf("\nSua opcao [0 a 5]: ");
		scanf("%d", &opcao);

		switch (opcao) {
			case 0: // Sair
				fim = true;
				break;
			case 1: // Deletar Pilha
				delPilhaLinear(pilha);
				break;
			case 2: // Empilhar
				printf("\nDigite o numero: ");
				scanf("%d", &elem);
				empilhar(pilha, elem);
				break;
			case 3: // Desempilhar
				int num = desempilhar(pilha);
				if (num != -1) {
					printf("\nNumero %d removido!\n", num);
				}
				break;
			case 4: // Mostrar 
				mostrar(pilha);
				break;
			case 5: // Pesquisar Elemento
				printf("\nDigite o numero: ");
				scanf("%d", &elem);
				if (pesquisar(pilha, elem)) {
					printf("\nNumero %d encontrado!\n", elem);
				}
				else {
					printf("\nNumero %d nao encontrado!\n", elem);
				}
				break;
		}
	}

	return 0;
}
