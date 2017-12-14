#include <fstream>
#include <iostream>
#include "haffman.h"

int const alphobetSize = 26 * 2 + 3; // a..z A..Z . , _
int const maxKeyLength = 10;

using namespace ::std;

struct SymbolAndKey
{
    char symbol = 0;
    char key[maxKeyLength] = {0};
};

SymbolAndKey *loadKeyTable(char *keyTable, int &amountSymbols)
{
    ifstream getAmount(keyTable);

    char currentChar = 0;
    while (getAmount.peek() != EOF)
    {
        getAmount.get(currentChar);
        if (currentChar == '-')
            amountSymbols++;
    }
    getAmount.close();

    SymbolAndKey *base = new SymbolAndKey[amountSymbols];

    int i = 0;
    ifstream in(keyTable);
    while (in.peek() != EOF)
    {
        in.get(base[i].symbol);
        in.get(); // '-'

        in >> base[i].key;
        in.get(); // '\n'
        i++;
    }
    in.close();

    return base;
}

bool areEqual(char a[maxKeyLength], char b[maxKeyLength])
{
    for (int i = 0; i < maxKeyLength; i++)
        if (a[i] != b[i])
            return false;

    return true;
}

char getSymbolByKey(SymbolAndKey *base, int baseSize, char key[maxKeyLength])
{
    for (int i = 0; i < baseSize; i++)
        if (areEqual(base[i].key, key))
            return base[i].symbol;

    return 0;
}

void decodeText(char *inputFile, char *outputFile, char *keyTable)
{
    int amountSymbols = 0;
    SymbolAndKey *base = loadKeyTable(keyTable, amountSymbols);

    ifstream in(inputFile);
    ofstream out(outputFile);

    int nowLength = 0;
    char currentKey[maxKeyLength] = {0};

    while(in.peek() != EOF)
    {
        in.get(currentKey[nowLength]);
        nowLength++;

        char symbol = getSymbolByKey(base, amountSymbols, currentKey);
        if (symbol != 0)
        {
            out << symbol;
            nowLength = 0;
            for (int i = 0; i < maxKeyLength; i++)
                currentKey[i] = 0;
        }
    }

    delete[] base;
    in.close();
    out.close();
}
