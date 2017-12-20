#include <iostream>
#include "stack.h"

int const maxLength = 100;
int const maxLength2 = 10;

int const d = 13;
int const q = 23 % d;

using namespace std;

int getLength(char *string)
{
    int result = 0;
    while (string[result] != 0)
        result++;

    return result;
}

int getHash(char *string, int howMuch)
{
    int length = 0;
    if (howMuch == -1)
        length = getLength(string);
    else
        length = howMuch;

    int result = 0;
    for (int i = 0; i < length; i++)
        result = (result * q +(int)string[i]) % d;

    return result % d;
}

bool checkBySymbols(char *string, int from, char *substring)
{
    int length = getLength(substring);
    for (int i = 0; i < length; i++)
        if (substring[i] != string[from + i])
            return false;

    return true;
}

int ab(int a, int b)
{
    int result = 1;
    for (int i = 0; i < b; i++)
        result *= a;

    return result;
}

int getModule(int a, int b)
{
    if (a % b > 0)
        return a % b;

    return b + (a % b);
}


int main()
{
    char string[maxLength] = {0};
    cout << "\nEnter string: ";
    cin >> string;
    int length = getLength(string);

    cout << "\nEnter substring: ";
    char substring[maxLength2] = {0};
    cin >> substring;
    int secondLength = getLength(substring);

    int extraQ = ab(q, secondLength) % d;

    int wantedHash = getHash(substring, -1);
    int currentHash = getHash(string, secondLength);


    Stack *begins = createStack();
    if (currentHash == wantedHash)
        if (checkBySymbols(string, 0, substring))
            push(begins, 0);

    for (int i = 1; i < length - secondLength + 1; i++)
    {
        currentHash = (currentHash * q) % d;
        currentHash = currentHash - string[i - 1] * extraQ % d;
        currentHash = (currentHash + string[i + secondLength - 1]) % d;
        if (currentHash == wantedHash)
            if (checkBySymbols(string, i, substring))
                push(begins, i);
    }

    cout << "\nPositions: ";
    printStack(begins);

    deleteStack(begins);
    return 0;
}
