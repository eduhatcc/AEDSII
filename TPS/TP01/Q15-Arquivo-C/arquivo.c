#include <stdio.h>
#include <stdlib.h>

int main () {
    int n;
    char line[256];
    
    scanf("%d", &n);
    
    float in[n];
    float out[n];
    
    FILE *arq = fopen("arq.dat", "w");
    
    for (int i = 0; i < n; i++) {
        scanf(" %f", &in[i]);

        if (i == n-1) {
            fprintf(arq, "%g", in[i]);
        }
	else {
            fprintf(arq, "%g\n", in[i]);
        }
    }
    fclose(arq);
    
    arq = fopen("arq.dat", "r");
    
    for (int i = 0; i < n; i++) {
        fgets(line, sizeof(line), arq);
        sscanf(line, "%f", &out[i]);
    }
    fclose(arq);
    
    for (int i = n-1; i >= 0; i--) {
        if ((int)out[i] == out[i]) {
            printf("%.0f\n", out[i]);
        } else {
            printf("%g\n", out[i]);
        }
    }
    
    return 0;
}
