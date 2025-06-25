#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <ctype.h>
#include <time.h>

#define MAX_LINE_SIZE 2000
#define MAX_SHOWS 1000
#define MAX_CAPACITY 1368

char **csv_lines = NULL;
int csv_line_count = 0,
    comparacoes = 0,
    movimentacoes = 0;

typedef struct {
    char show_id[50];
    char type[20];
    char title[200];
    char director[200];
    char **cast;
    int castCount;
    char country[100];
    struct tm *dateAdded;
    int releaseYear;
    char rating[20];
    char duration[50];
    char **listedIn;
    int listedInCount;
} Show;

void init_show(Show *show) {
    strcpy(show->show_id, "NaN");
    strcpy(show->type, "NaN");
    strcpy(show->title, "NaN");
    strcpy(show->director, "NaN");
    strcpy(show->country, "NaN");
    show->cast = malloc(sizeof(char *));
    show->cast[0] = strdup("NaN");
    show->castCount = 1;
    show->dateAdded = malloc(sizeof(struct tm));
    memset(show->dateAdded, 0, sizeof(struct tm));
    show->dateAdded->tm_mday = 1;  // Dia 1
    show->dateAdded->tm_mon = 2;   // Março
    show->dateAdded->tm_year = 0;  // 1900
    show->releaseYear = 0;
    strcpy(show->rating, "NaN");
    strcpy(show->duration, "NaN");
    show->listedIn = malloc(sizeof(char *));
    show->listedIn[0] = strdup("NaN");
    show->listedInCount = 1;
}

void read_file(const char *filename) {
    FILE *file = fopen(filename, "r");
    if (file == NULL) {
        fprintf(stderr, "Error opening file: %s\n", filename);
        return;
    }

    // Count number of lines
    char buffer[MAX_LINE_SIZE];
    csv_line_count = 0;
    while (fgets(buffer, MAX_LINE_SIZE, file) != NULL) {
        csv_line_count++;
    }

    // Allocate memory for lines
    csv_lines = (char **)malloc(csv_line_count * sizeof(char *));
    if (csv_lines == NULL) {
        fprintf(stderr, "Memory allocation error\n");
        fclose(file);
        return;
    }

    // Reset file pointer and read lines
    rewind(file);
    for (int i = 0; i < csv_line_count; i++) {
        if (fgets(buffer, MAX_LINE_SIZE, file) != NULL) {
            // Remove newline character
            buffer[strcspn(buffer, "\n")] = 0;
            
            csv_lines[i] = (char *)malloc((strlen(buffer) + 1) * sizeof(char));
            if (csv_lines[i] == NULL) {
                fprintf(stderr, "Memory allocation error\n");
                fclose(file);
                return;
            }
            strcpy(csv_lines[i], buffer);
        }
    }

    fclose(file);
}

// Sort a string array alphabetically
void sort_string_array(char **array, int size) {
    if (array == NULL || size <= 1) return;
    
    for (int i = 0; i < size - 1; i++) {
        for (int j = i + 1; j < size; j++) {
            if (array[i] != NULL && array[j] != NULL && strcmp(array[i], array[j]) > 0) {
                char *temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
    }
}

// Trim leading and trailing whitespace from a string
char* trim(char *str) {
    if (str == NULL) return NULL;
    
    // Trim leading space
    while(isspace((unsigned char)*str)) str++;
    
    if(*str == 0) return str; // All spaces
    
    // Trim trailing space
    char *end = str + strlen(str) - 1;
    while(end > str && isspace((unsigned char)*end)) end--;
    
    // Write new null terminator
    *(end + 1) = 0;
    
    return str;
}

// Replace double double-quotes with single double-quotes
char* fix_double_quotes(char *str) {
    if (str == NULL) return NULL;
    
    char *src = str;
    char *dst = str;
    
    while (*src) {
        if (*src == '"' && *(src + 1) == '"') {
            *dst++ = '"';
            src += 2;
        } else {
            *dst++ = *src++;
        }
    }
    *dst = '\0';
    
    return str;
}

// Parse CSV fields properly handling quotes and escape sequences
char** parse_csv_line(char *line, int *field_count) {
    if (line == NULL || field_count == NULL) return NULL;
    
    // Count the expected number of fields (approximate)
    int expected_fields = 1;
    bool in_quotes = false;
    for (char *p = line; *p; p++) {
        if (*p == '"') {
            in_quotes = !in_quotes;
        } else if (*p == ',' && !in_quotes) {
            expected_fields++;
        }
    }
    
    // Allocate memory for fields
    char **fields = (char **)malloc(expected_fields * sizeof(char *));
    if (fields == NULL) return NULL;
    
    // Initialize field count
    *field_count = 0;
    
    char *p = line;
    char *field_start = p;
    in_quotes = false;
    
    while (*p) {
        if (*p == '"') {
            // Check if it's an escaped quote (double double-quote)
            if (*(p+1) == '"') {
                p += 2; // Skip both quotes
            } else {
                in_quotes = !in_quotes;
                p++;
            }
        } else if (*p == ',' && !in_quotes) {
            // End of field
            *p = '\0';
            
            // Remove surrounding quotes if present
            char *field_value = field_start;
            int field_len = strlen(field_value);
            
            if (field_len >= 2 && *field_value == '"' && *(field_value + field_len - 1) == '"') {
                field_value++;
                *(field_value + field_len - 2) = '\0';
                // Fix escaped quotes (convert "" to ")
                fix_double_quotes(field_value);
            }
            
            fields[(*field_count)++] = strdup(field_value);
            field_start = p + 1;
            p++;
        } else {
            p++;
        }
    }
    
    // Handle the last field
    if (field_start) {
        // Remove surrounding quotes if present
        int field_len = strlen(field_start);
        if (field_len >= 2 && *field_start == '"' && *(field_start + field_len - 1) == '"') {
            field_start++;
            *(field_start + field_len - 2) = '\0';
            // Fix escaped quotes (convert "" to ")
            fix_double_quotes(field_start);
        }
        
        fields[(*field_count)++] = strdup(field_start);
    }
    
    return fields;
}

// Split a string by delimiter, properly handling quoted content
char** split_and_sort(const char *str, int *count) {
    if (str == NULL || count == NULL || strlen(str) == 0) {
        *count = 0;
        return NULL;
    }
    
    // Make a copy of the input string
    char *str_copy = strdup(str);
    if (str_copy == NULL) {
        *count = 0;
        return NULL;
    }
    
    // Fix any double-quoted sections
    fix_double_quotes(str_copy);
    
    // Count commas to estimate the maximum number of items
    int max_items = 1;
    for (char *p = str_copy; *p; p++) {
        if (*p == ',') max_items++;
    }
    
    // Allocate array for items
    char **items = (char **)malloc(max_items * sizeof(char *));
    if (items == NULL) {
        free(str_copy);
        *count = 0;
        return NULL;
    }
    
    // Parse the list
    int item_count = 0;
    char *token = strtok(str_copy, ",");
    
    while (token != NULL) {
        items[item_count++] = strdup(trim(token));
        token = strtok(NULL, ",");
    }
    
    free(str_copy);
    *count = item_count;
    
    // Sort the items
    sort_string_array(items, item_count);
    
    return items;
}

// Parse a CSV line into a Show structure
void read_show(Show *show, char *line) {
    if (show == NULL || line == NULL) return;
    
    // Make a copy of the line to avoid modifying the original
    char *line_copy = strdup(line);
    if (line_copy == NULL) return;
    
    // Parse the CSV line
    int field_count = 0;
    char **fields = parse_csv_line(line_copy, &field_count);
    
    // Process fields if we have enough
    if (fields != NULL && field_count >= 11) {
        // Show ID
        if (fields[0] && strlen(fields[0]) > 0) {
            strcpy(show->show_id, fields[0]);
        }
        
        // Type
        if (fields[1] && strlen(fields[1]) > 0) {
            if (strcasecmp(fields[1], "movie") == 0) {
                strcpy(show->type, "Movie");
            } else {
                strcpy(show->type, "TV Show");
            }
        }
        
        // Title - WITH FIX TO REMOVE QUOTES
        if (fields[2] && strlen(fields[2]) > 0) {
            char *cleaned_title = strdup(fields[2]);
            
            // Remove all quotes from the title
            char *src = cleaned_title;
            char *dst = cleaned_title;
            
            while (*src) {
                if (*src != '"') {
                    *dst++ = *src;
                }
                src++;
            }
            *dst = '\0';
            
            strcpy(show->title, cleaned_title);
            free(cleaned_title);
        }
        
        // Director
        if (fields[3] && strlen(fields[3]) > 0) {
            strcpy(show->director, fields[3]);
        }
        
        // Cast
        if (fields[4] && strlen(fields[4]) > 0) {
            show->cast = split_and_sort(fields[4], &show->castCount);
        } else {
            show->cast = NULL;
            show->castCount = 0;
        }
        
        // Country
        if (fields[5] && strlen(fields[5]) > 0) {
            strcpy(show->country, fields[5]);
        }
        
        // Date added
        if (fields[6] && strlen(fields[6]) > 0) {
            show->dateAdded = malloc(sizeof(struct tm));
            if (show->dateAdded != NULL) {
                memset(show->dateAdded, 0, sizeof(struct tm));
                
                // Date format: "Month day, year"
                char month_str[20] = {0};
                int day = 0, year = 0;
                
                sscanf(fields[6], "%19s %d, %d", month_str, &day, &year);
                
                // Convert month string to number (0-11)
                const char *months[] = {"January", "February", "March", "April", "May", "June", 
                                       "July", "August", "September", "October", "November", "December"};
                int month = 0;
                for (int i = 0; i < 12; i++) {
                    if (strstr(month_str, months[i]) != NULL) {
                        month = i;
                        break;
                    }
                }
                
                show->dateAdded->tm_year = year - 1900;
                show->dateAdded->tm_mon = month;
                show->dateAdded->tm_mday = day;
            }
        } else {
            show->dateAdded = malloc(sizeof(struct tm));
            if (show->dateAdded != NULL) {
                memset(show->dateAdded, 0, sizeof(struct tm));
                show->dateAdded->tm_year = 0;  // 1900
                show->dateAdded->tm_mon = 2;   // Março
                show->dateAdded->tm_mday = 1;  // Dia 1
            }
        }
        
        // Release year
        if (fields[7] && strlen(fields[7]) > 0) {
            show->releaseYear = atoi(fields[7]);
        }
        
        // Rating
        if (fields[8] && strlen(fields[8]) > 0) {
            strcpy(show->rating, fields[8]);
        }
        
        // Duration
        if (fields[9] && strlen(fields[9]) > 0) {
            strcpy(show->duration, fields[9]);
        }
        
        // Listed in
        if (fields[10] && strlen(fields[10]) > 0) {
            show->listedIn = split_and_sort(fields[10], &show->listedInCount);
        } else {
            show->listedIn = NULL;
            show->listedInCount = 0;
        }
        
        // Free fields
        for (int i = 0; i < field_count; i++) {
            if (fields[i] != NULL) {
                free(fields[i]);
            }
        }
        free(fields);
    }
    
    free(line_copy);
}

// Print a Show structure
void print_show(Show *show) {
    if (show == NULL) return;
    
    printf("=> %s ## %s ## %s ## ", 
           show->show_id, 
           show->title, 
           show->type);
    
    // Director
    printf("%s ## ", show->director);
    
    // Cast
    if (show->castCount == 0 || show->cast == NULL) {
        printf("[NaN] ## ");
    } else {
        printf("[");
        for (int i = 0; i < show->castCount; i++) {
            if (show->cast[i] != NULL) {
                printf("%s", show->cast[i]);
                if (i < show->castCount - 1) printf(", ");
            }
        }
        printf("] ## ");
    }
    
    // Country
    printf("%s ## ", show->country);
    
    // Date added
    if (show->dateAdded == NULL) {
        printf(" ## ");
    } else {
        const char *months[] = {"January", "February", "March", "April", "May", "June", 
                               "July", "August", "September", "October", "November", "December"};
        printf("%s %d, %d ## ", 
               months[show->dateAdded->tm_mon],
               show->dateAdded->tm_mday,
               show->dateAdded->tm_year + 1900);
    }
    
    // Release year, rating, duration
    printf("%d ## %s ## %s ## ", 
           show->releaseYear, 
           show->rating, 
           show->duration);
    
    // Listed in
    if (show->listedInCount == 0 || show->listedIn == NULL) {
        printf("[NaN] ##");
    } else {
        printf("[");
        for (int i = 0; i < show->listedInCount; i++) {
            if (show->listedIn[i] != NULL) {
                printf("%s", show->listedIn[i]);
                if (i < show->listedInCount - 1) printf(", ");
            }
        }
        printf("] ##");
    }
    
    printf("\n");
}

// Free memory allocated for a Show structure
void free_show(Show *show) {
    if (show == NULL) return;
    
    // Free cast array
    if (show->cast != NULL) {
        for (int i = 0; i < show->castCount; i++) {
            if (show->cast[i] != NULL) {
                free(show->cast[i]);
            }
        }
        free(show->cast);
        show->cast = NULL;
    }
    
    // Free listedIn array
    if (show->listedIn != NULL) {
        for (int i = 0; i < show->listedInCount; i++) {
            if (show->listedIn[i] != NULL) {
                free(show->listedIn[i]);
            }
        }
        free(show->listedIn);
        show->listedIn = NULL;
    }
    
    // Free date
    if (show->dateAdded != NULL) {
        free(show->dateAdded);
        show->dateAdded = NULL;
    }
}

// Free memory allocated for CSV lines
void free_csv_lines() {
    if (csv_lines == NULL) return;
    
    for (int i = 0; i < csv_line_count; i++) {
        if (csv_lines[i] != NULL) {
            free(csv_lines[i]);
        }
    }
    free(csv_lines);
    csv_lines = NULL;
}

// Check if string is "FIM"
bool is_end(char *str) {
    return (str != NULL && strlen(str) == 3 && str[0] == 'F' && str[1] == 'I' && str[2] == 'M');
}

// Convert string to integer
int convert_str_to_int(char *str) {
    if (str == NULL || strlen(str) == 0) return 0;
    
    int value = 0;
    for (int i = 0; i < strlen(str); i++) {
        if (isdigit(str[i])) {
            value = value * 10 + (str[i] - '0');
        }
    }
    
    return value;
}

char *strdup_lower(const char *src) {
    char *dup = strdup(src);             
    if (!dup) exit(1);
    for (int i = 0; dup[i]; i++) {
        dup[i] = tolower((unsigned char)dup[i]);
    }
    return dup;
}

typedef struct No {
    Show *elemento;
    struct No *esq;
    struct No *dir;
    int altura;
} No;

// Create new no holding Show*
No *new_no_value(Show *s) {
    No *tmp = malloc(sizeof(No));
    tmp->elemento = s;
    tmp->esq = NULL;
    tmp->dir = NULL;
    tmp->altura = 1;
    return tmp;
}

// Height of the tree
int altura(No *no) {
    return no ? no->altura : 0;
}

// Calculate balance factor of a node
int max(int a, int b) {
    return (a > b) ? a : b;
}

No *rotacionar_direita(No *i) {
    No *j = i->esq;
    No *T2 = j->dir;

    // Perform rotation
    j->dir = i;
    i->esq = T2;

    // Update heights
    i->altura = max(altura(i->esq), altura(i->dir)) + 1;
    j->altura = max(altura(j->esq), altura(j->dir)) + 1;

    return j;
}

No *rotacionar_esquerda(No *i) {
    No *j = i->dir;
    No *T2 = j->esq;

    // Perform rotation
    j->esq = i;
    i->dir = T2;

    // Update heights
    i->altura = max(altura(i->esq), altura(i->dir)) + 1;
    j->altura = max(altura(j->esq), altura(j->dir)) + 1;

    return j;
}

int get_balance(No *no) {
    int balance = altura(no->esq) - altura(no->dir);
    return balance;
}

No *inserir_avl(No *i, Show *s) {
    if (i == NULL) {
        i = new_no_value(s); // Create a new node
    }
    else {
        int cmp = strcmp(s->title, i->elemento->title);
        if (cmp < 0) {
            printf(" ==== TESTE INSERIR DIREITA ====\n");
            i->esq = inserir_avl(i->esq, s); // Insert in right subtree
        }
        else if(cmp > 0) {
            printf("===== TESTE INSERIR ESQUERDA =====\n");
            i->dir = inserir_avl(i->dir, s); // Insert in left subtree
        }
        else {
            // Element already exists, do not insert
            return i;
        }

    	i->altura = 1 + max(altura(i->esq), altura(i->dir));
	int balance = get_balance(i);

	if (balance > 1 && strcmp(s->title, i->esq->elemento->title) < 0) {
		i = rotacionar_direita(i); // Right rotation
	}
	else if (balance < -1 && strcmp(s->title, i->dir->elemento->title) > 0) {
		i = rotacionar_esquerda(i); // Left rotation
	}
	// Left Right
	if (balance > 1 && strcmp(s->title, i->esq->elemento->title) > 0) {
		i->esq = rotacionar_esquerda(i->esq); // Left 
		i = rotacionar_direita(i); // Right
	}
	// Right Left
	if (balance < -1 && strcmp(s->title, i->dir->elemento->title) < 0) {
		i->dir = rotacionar_direita(i->dir); // Right
		i = rotacionar_esquerda(i);  // Left
	}

	return i;
    }
}
bool pesquisar_avl(No *i, char *key) {
    bool encontrou = false;
    if (i != NULL) {
        int cmp = strcmp(i->elemento->title, key);
        if (cmp == 0) {
            encontrou = true; // Found
        } 
        else if (cmp < 0) {
            printf("esq ");
            encontrou = pesquisar_avl(i->dir, key); // Search right
        } 
        else {
            printf("dir ");
            encontrou = pesquisar_avl(i->esq, key); // Search left
        }
    }
    return encontrou;
}

int main() {
    // Carrega CSV e descarta header automaticamente em read_file
    read_file("../tmp/disneyplus.csv");

    // Pré-carrega shows (linha 0 é header)
    int data_count = csv_line_count - 1;
    Show *shows = malloc(sizeof(Show) * data_count);
    for (int i = 1; i <= data_count; i++) {
        init_show(&shows[i-1]);
        read_show(&shows[i-1], csv_lines[i]);
    }

    // Inicia lista e lê IDs iniciais até "FIM"
    No *raiz = NULL;
    char buffer[256];
    bool fim = false;
    while (!fim)  {
        fgets(buffer, sizeof(buffer), stdin);
        buffer[strcspn(buffer, "\r\n")] = '\0';
        
        if (is_end(buffer)) {
            fim = true;
        } 
        else {
            int idx = convert_str_to_int(buffer) - 1;
            if (idx >= 0 && idx < data_count) {
                raiz = inserir_avl(raiz, &shows[idx]);
            }
        }
    }

    fim = false;
    while (!fim) {
        fgets(buffer, sizeof(buffer), stdin);
        buffer[strcspn(buffer, "\r\n")] = '\0';
        
        if (is_end(buffer)) {
            fim = true;
        } 
        else {
            printf("=>raiz  ");
            if(pesquisar_avl(raiz, buffer)) {
                printf("SIM\n");
            } else {
                printf("NAO\n");
            }
        }
    }

    free(shows);
    free_csv_lines();
    return 0;
}
