#include <iostream>
#include "stack.h"

int const maxLength = 100000;
int const maxLength2 = 1000;

int const hashMod = 13;
int const letterCount = 23 % hashMod;

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
        result = (result * letterCount +(int)string[i]) % hashMod;

    return result % hashMod;
}

bool checkBySymbols(char *string, int from, char *substring)
{
    int length = getLength(substring);
    for (int i = 0; i < length; i++)
        if (substring[i] != string[from + i])
            return false;

    return true;
}

int aDegreeB(int a, int b)
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
    int length = 0;
    char c = 0;

    cin.get(c);
    while (c != '\n')
    {
        string[length] = c;
        length++;
        cin.get(c);
    }

    cout << "Enter substring: ";
    char substring[maxLength2] = {0};
    int secondLength = 0;

    cin.get(c);
    while (c != '\n')
    {
        substring[secondLength] = c;
        secondLength++;
        cin.get(c);
    }


    int extraQ = aDegreeB(letterCount, secondLength) % hashMod;

    int wantedHash = getHash(substring, -1);
    int currentHash = getHash(string, secondLength);


    Stack *begins = createStack();
    if (currentHash == wantedHash)
        if (checkBySymbols(string, 0, substring))
            push(begins, 0);

    for (int i = 1; i < length - secondLength + 1; i++)
    {
        currentHash = (currentHash * letterCount) % hashMod;
        currentHash = currentHash - string[i - 1] * extraQ % hashMod;
        currentHash = (currentHash + string[i + secondLength - 1]) % hashMod;
        if (currentHash == wantedHash)
            if (checkBySymbols(string, i, substring))
                push(begins, i);
    }

    cout << "\nPositions: ";
    printStack(begins);

    deleteStack(begins);
    return 0;
}
