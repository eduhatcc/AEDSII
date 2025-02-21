#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <locale.h>
#include <wchar.h>
#include <string.h>

/*
 * void removeLinha(wchar_t str[]) {
 *	size_t len = wcslen(str);
 *
 *	if (len > 0 && str[len - 1] == L'\n') {
 *		str[len - 1] = L'\0';
 *	}
}*
*/

/*
 * bool verificaFim(wchar_t str[], wchar_t chave[]) {
 *	bool fim = false;
 *
 *	//compara se o tamanho da string é igual ao tamanho da chave
 *	if (wcslen(str) == wcslen(chave)) {
 *		fim = true;
 *
 *		//repetição para verificar se a string é igual a chave
 *		for (int i=0; i < wcslen(chave); i++) {
 *			if(str[i] != chave[i]) {
 * 				fim = false;
 *				i = wcslen(chave);
 * 			}
 *		}
 *	}
 *
 *	return fim;
}*
 */
bool ehPalindromo(wchar_t str[]) {
	bool palindromo = true;
	int i = 0,
	    j = (wcslen(str) - 1);

	//repetição para conferir palíndromos
	while (i < j) {
		if (str[i] != str[j]) {
			palindromo = false;
			i = j;
		}
		i++; j--;
	}

	return palindromo;
}

int main() {
	setlocale(LC_ALL, "");
	wchar_t str[100];
	fgetws(str, sizeof(str), stdin);
	str[wcscspn(str, L"\n")] = L'\0';
	

	while (!wcscmp(str, L"FIM")) {
		if (ehPalindromo(str)) {
			wprintf(L"SIM\n");
		}
		else {
			wprintf(L"NAO\n");
		}
		fgetws(str, sizeof(str), stdin);
		str[wcscspn(str, L"\n")] = L'\0';
	}

	return 0;
}
