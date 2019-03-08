#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void extract_chars(char* line, char* alpha, char* nums, char* puncs, char* wspace);
void write_output(FILE* out, int line, char* a, char* d, char* p, char* w);

int main(int argc, char* argv[]) {
    FILE* in;
    FILE* out;
    char* line;
    char* alpha;
    char* nums;
    char* puncs;
    char* wspace;
    int MAX_LEN = 1000;

    if (argc != 3) {
        printf("Usage: %s input file output file \n", argv[0]);
        exit(EXIT_FAILURE);
    }

    in = fopen(argv[1], "r");
    if (in == NULL) {
        printf("Unable to read to file %s\n", argv[1]);
        exit(EXIT_FAILURE);
    }

    out = fopen(argv[2], "w");
    if (out == NULL){
        printf("Unable to write to file %s\n", argv[2]);
        exit(EXIT_FAILURE);
    }

    line = calloc(MAX_LEN, sizeof(char));
    alpha = calloc(MAX_LEN, sizeof(char));
    nums = calloc(MAX_LEN, sizeof(char));
    puncs = calloc(MAX_LEN, sizeof(char));
    wspace = calloc(MAX_LEN, sizeof(char));

    int lineCount = 1;
    while (fgets(line, MAX_LEN, in) != NULL) {
        extract_chars(line, alpha, nums, puncs, wspace);
        write_output(out, lineCount++, alpha, nums, puncs, wspace);
    }

    free(line);
    line = NULL;
    free(alpha);
    alpha = NULL;
    free(nums);
    nums = NULL;
    free(puncs);
    puncs = NULL;
    free(wspace);
    wspace = NULL;

    return (EXIT_SUCCESS);
}

void extract_chars(char* s, char* a, char* d, char* p, char* w) {
    int string_i, alpha_i, digit_i, punc_i, whitespace_i;
    string_i = alpha_i = digit_i = punc_i = whitespace_i = 0;

    while (s[string_i] != '\0') {
        if (isalpha(s[string_i]))
            a[alpha_i++] = s[string_i];
        if (isdigit(s[string_i])) 
            d[digit_i++] = s[string_i];
        if (ispunct(s[string_i]))
            p[punc_i++] = s[string_i];
        if (isspace(s[string_i])) 
            w[whitespace_i++] = s[string_i];
        string_i++;
    }
    a[alpha_i] = '\0';
    d[digit_i] = '\0';
    p[punc_i] = '\0';
    w[whitespace_i] = '\0';
}

void write_output(FILE* out, int line, char* a, char* d, char* p, char* w) {
    fprintf(out, "line %d contains: \n", line);
    int len = 0;
    if ((len = strlen(a)) == 1) {
        fprintf(out, "1 alphabetic character: %s\n", a);
    } else {
        fprintf(out, "%d alphanumeric characters: %s\n", len, a);
    }

    if ((len = strlen(d)) == 1) {
        fprintf(out, "1 numeric character: %s\n", d);
    } else {
        fprintf(out, "%d numeric characters: %s\n", len, d);
    }

    if ((len = strlen(p)) == 1) {
        fprintf(out, "1 punctuation character: %s\n", p);
    } else {
        fprintf(out, "%d punctuation characters: %s\n", len, p);
    }

    if ((len = strlen(w)) == 1) {
        fprintf(out, "1 whitespace character: %s\n", w);
    } else {
        fprintf(out, "%d whitespace characters: %s\n", len, w);
    }
}
