#include <iostream>
#include "arrays.h"
#include "ints.h"

using namespace std;

int main()
{
    int a = 0;
    cout << "Введите число: ";
    cin >> a;
    int length = getIntLength(a);
    int* array = new int[length];
    convertIntToArray(a, length, array);
    sortArray(array, 0, length - 1);
    if (array[0] == 0)
    {
        moveArray(array, length);
    }
    cout << "Минимальное число: ";
    printArrayAsValue(length, array);
    delete [] array;
    return 0;
}
