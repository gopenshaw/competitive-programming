#include<stdio.h>
#include<string.h>

int main() {
    char input[1000];
    while (scanf("%s", input) == 1) {
        for (int i = 0; i < strlen(input); i++) {
            printf("%c", input[i] - 7);
        }

        printf("\n");
    }

    return 0;
}

