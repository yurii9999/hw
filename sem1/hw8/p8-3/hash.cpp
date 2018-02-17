#include <iostream>
#include "hash.h"

using namespace ::std;

int getHash(String *string)
{
    char *chars = getCharArray(string);
    int length = getLength(string);
    int result = 0;

    for (int i = 0; i < length; i++)
        result = (result + chars[i]) % arraySize;

    return result;
}

bool isIncludedInHA(String *string, List *hashArray[arraySize])
{
    return isHere(hashArray[getHash(string)], string);
}

void includeStringToHA(String *string, List *hashArray[arraySize])
{
    int hash = getHash(string);
    addElementTo(hashArray[hash], string, 0);
}

void increaseValueInHA(String *string, List *hashArray[arraySize])
{
    int hash = getHash(string);
    increaseValue(hashArray[hash], string);
}

void printHashArray(List *hashArray[arraySize])
{
    for (int i = 0; i < arraySize; i++)
    {
        cout << "\t\tList[" << i << "]:\n";
        printList(hashArray[i]);
    }
}

int getAmountWords(List *hashArray[arraySize])
{
    int result = 0;
    for (int i = 0; i < arraySize; i++)
        result = result + getSumValues(hashArray[i]);

    return result;
}

int getMaxSizeInHA(List *hashArray[arraySize])
{
    int maxSize = -1;

    for (int i = 0; i < arraySize; i++)
    {
        int size = sizeList(hashArray[i]);
        if (size > maxSize)
            maxSize = size;
    }
    return maxSize;
}

void printListInHA(List *hashArray[arraySize], int id)
{
    printList(hashArray[id]);
}

int getAmoutnEmptyListsInHA(List *hashArray[arraySize])
{
    int result = 0;
    for (int i = 0; i < arraySize; i++)
        if (isEmpty(hashArray[i]))
            result++;

    return result;
}

int getAmountElements(List *hashArray[arraySize])
{
    int result = 0;
    for (int i = 0; i < arraySize; i++)
        result = result + sizeList(hashArray[i]);

    return result;
}

double getLoadFactor(List *hashArray[arraySize])
{
    return (double) getAmountElements(hashArray) / arraySize;
}
