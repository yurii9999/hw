#include <iostream>
#include "stack.h"

using namespace std;

int getSum(int value)
{
    int result = 0;
    value = abs(value);
    while (value / 10 != 0)
    {
        result = result + (value % 10);
        value = value / 10;
    }

    return result + value;
}

Stack *getValues(int *array, int size)
{
    int maxSum = getSum(array[0]);
    Stack *values = createStack();
    push(values, array[0]);

    for (int i = 1; i < size; i++)
    {
        int currentSum = getSum(array[i]);

        if (currentSum > maxSum)
        {
            clearStack(values);
            maxSum = currentSum;
        }

        if (currentSum >= maxSum)
            push(values, array[i]);
    }

    return values;
}


int main()
{
    int size = 0;
    cout << "\nEnter size: ";

    cin >> size;
    int *array = new int[size];
    cout << "\nEnter array: ";
    for (int i = 0; i < size; i++)
        cin >> array[i];

    cout << "\nArray: ";
    for (int i = 0; i < size; i++)
        cout << array[i] << " ";

    Stack *maxSums = getValues(array, size);

    cout << "\nResult: ";
    printStack(maxSums);

    deleteStack(maxSums);
    delete[] array;
    return 0;
}
