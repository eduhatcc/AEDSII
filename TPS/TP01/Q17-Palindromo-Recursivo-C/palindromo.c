#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <locale.h>
#include <wchar.h>
#include <string.h>

bool ehPalindromo(wchar_t str[], int i, int j) {
	bool palindromo = true;

	if (i < j) {
		if (str[i] != str[j]) {
			palindromo = false;
		}
		else {
			palindromo = ehPalindromo(str, i+1, j-1);
		}
	}

	return palindromo;
}

int main() {
	setlocale(LC_ALL, "");
	wchar_t str[100];
	fgetws(str, sizeof(str) / sizeof(wchar_t), stdin);
	str[wcscspn(str, L"\n")] = L'\0';
	
	while (wcscmp(str, L"FIM")) {
		if (ehPalindromo(str, 0, (wcslen(str) - 1))) {
			wprintf(L"SIM\n");
		}
		else {
			wprintf(L"NAO\n");
		}
		fgetws(str, sizeof(str) / sizeof(wchar_t), stdin);
		str[wcscspn(str, L"\n")] = L'\0';
	}

	return 0;
}
