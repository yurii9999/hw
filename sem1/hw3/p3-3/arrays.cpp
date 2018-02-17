#include "arrays.h"

using namespace std;

void printArrayAsValue(int length, int* &array)
{
    for (int i = 0; i < length; i++)
    {
        cout << array[i];
    }
}

void convertIntToArray(int value, int length, int* &array)
{
    for (int i = 0; i < length; i++)
    {
        array[i] = value % 10;
        value /= 10;
    }
}

void sortArray(int* &array, int from, int to)
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
        sortArray(array, from, j);
    }
    if (to > i)
    {
        sortArray(array, i, to);
    }
}

void moveArray(int* &array, int length)
{
    int i = 0;
    while (array[i] == 0 && i < length)
    {
        i++;
    }

    if (i < length)
    {
        int temp = array[i];
        array[i] = array[0];
        array[0] = temp;
    }
}
