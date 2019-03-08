#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void stringReverse(char* s);

int main(int argc, char* argv[]) {
    FILE* in;
    FILE* out;
    char words[256]; //arbitrary

    if (argc != 3) {
        printf("Usage: %s <input file> <output file>\n", argv[0]);
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

    stringReverse(words);

    while( fscanf(in, " %s", words) != EOF ){
        stringReverse(words);
        fprintf(out, "%s\n", words);
    }

    fclose(in);
    fclose(out);
    return(EXIT_SUCCESS);
}

void stringReverse(char* s) {
    int i = 0;
    int j = strlen(s) - 1;
    char temp;

    while (i <= j) {
        temp = s[j];
        s[j] = s[i];
        s[i] = temp;
        i++;
        j--;
    }
}