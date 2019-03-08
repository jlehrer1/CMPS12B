#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include "Dictionary.h"

#define MAX_STRING_LEN = 256

typedef struct NodeObj {
    struct NodeObj* next;
    char* key;
    char* value;
} NodeObj;

typedef NodeObj* Node; //aliasing NodeObj* to Node

//Creating the Dictionary type
typedef struct DictionaryObj {
    Node head;
    int numItems;
} DictionaryObj;

//newNode() constructor for a new node
Node newNode(char key[], char value[]) {
    Node N = malloc(sizeof(NodeObj));
    assert (N != NULL);
    N->key = key;
    N->value = value;
    N->next = NULL;
    return (N);
}

//freeNode() deconstructor for the Node type
void freeNode(Node* pN) {
    if ((pN != NULL) and (*pN != NULL)) {
        free(*pN);
        *pN = NULL;
    }
}

Node findKey(Dictionary D, char* key) {
    Node N = D->head;
    while (N != NULL) {
        if (strcmp(N->key, key) == 0) {
            return N;
        }
        N = N->next;
    }
    freeNode(&N);
    return N;
}

//Public methods
Dictionary newDictionary(void) {
    Dictionary D = malloc(sizeof(NodeObj));
    assert(D != NULL);
    D->head = NULL;
    D->numItems = 0;
    return D;
};

void freeDictionary(Dictionary* pD) {
    if (pD != NULL && *pD != NULL) {
        free(*pD);
        *pD = NULL;
    }
}

int isEmpty(Dictionary D) {
    if (D == NULL) {
        fprintf(stderr, "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    return (D->numItems == 0);
}

int size(Dictionary D) {
    if (D == NULL) {
        fprintf(stderr, "Dictionary Error: calling size() on a NULL Dictionary reference");
        exit(EXIT_FAILURE);
    }
    return D->numItems;
}

char* lookup(Dictionary D, char* k) {
    if (D == NULL) {
        fprintf(stderr, "Dictionary Error: calling lookup() on a NULL Dictionary reference");
        exit(EXIT_FAILURE);
    }
    Node searched = findKey(D, k);
    if (searched != NULL)
        return searched->value;
    else
        return NULL;
    freeNode(&searched);
}

void insert(Dictionary D, char* k, char* v) {
    Node N;
    if (D == NULL) {
        fprintf(stderr, "Dictionary Error: calling insert() on a NULL Dictionary reference");
        exit(EXIT_FAILURE);
    }
    if (findKey(D, k) != NULL) {
        printf("Error: Cannot insert duplicate keys");
        exit(EXIT_FAILURE);
    } else {
        N = D->head;
        if (N == NULL) {
            D->head = newNode(k, v); //why doesn't using Node N here work? 
            D->head->next = NULL;
        } else {
            while (N->next != NULL) {
                N = N->next;
            }
            Node new = newNode(k, v);
            N->next = new;
        }
        D->numItems++;
    }
}

void delete(Dictionary D, char* k) {
    if (D == NULL) {
        fprintf(stderr, "Dictionary Error: calling delete() on a NULL Dictionary reference");
        exit(EXIT_FAILURE);
    }
    Node temp = D->head;
    Node prev = NULL;
    if (findKey(D, k) == NULL) {
        printf("Error: key not found");
        exit(EXIT_FAILURE);
    } else {
        if (temp != NULL && (strcmp(temp->key, k) == 0)) {
            D->head = temp->next;
            D->numItems--;
            return;
        } else {
            while (temp != NULL && (strcmp(temp->key, k) != 0)) {
                prev = temp;
                temp = temp->next;
            }
            prev->next = temp->next;
            D->numItems--;
        }
        freeNode(&temp);
    }
}

void makeEmpty(Dictionary D) {
    if (D == NULL) {
        fprintf(stderr, "Dictionary Error: calling makeEmpty() on a NULL Dictionary reference");
        exit(EXIT_FAILURE);
    }
    D->head = NULL;
    D->numItems = 0;
}

void printDictionary(FILE* out, Dictionary D) {
    Node N;
    if (D == NULL) {
        fprintf(stderr, "Dictionary Error: printDictionary() called on NULL reference");
        exit (EXIT_FAILURE);
    }
    for (N = D->head; N != NULL; N = N->next) fprintf(out, "%s %s\n", N->key, N->value);
    freeNode(&N);
}
