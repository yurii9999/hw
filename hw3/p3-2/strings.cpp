#include <iostream>
#include "strings.h"

using namespace std;

void sortString(char* &array, int from, int to)
{
    int temp = 0;
    int i = from, j = to;
    int element = array[ (i + j) / 2];
    while (i < j)
    {
        while (array[i] < element)
        {
            i++;
        }
        while (array[j] > element)
        {
            j--;
        }

        if (i <= j)
        {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }
    }
    if (from < j)
    {
        sortString(array, from, j);
    }
    if (to > i)
    {
        sortString(array, i, to);
    }
}

void printString(char* &array, int length)
{
    for (int i = 0; i < length; i++)
    {
        cout << array[i];
    }
}

void inputString(char* &array, int length)
{
    for (int i = 0; i < length; i++)
    {
        cin >> array[i];
    }
}

bool equalStrings(char* &array1, int length1, char* &array2, int length2)
{
    if (length1 != length2)
    {
        return false;
    }
    else
    {
        int i = 0;
        while (i < length1 && array1[i] == array2[i])
        {
            i++;
        }
        return array1[i] == array2[i];
    }
}
