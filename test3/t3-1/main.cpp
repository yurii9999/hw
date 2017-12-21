#include <iostream>

int const maxSize = 30;

using namespace std;

void inputArray(int array[])
{
    int i = 0;

    cin >> array[0];
    while (array[i] != 0)
    {
        i++;
        cin >> array[i];
    }
}

int getSize(int array[])
{
    int result = 0;
    while (array[result] != 0)
        result++;

    return result;
}

void sort(int array[])
{
    int size = getSize(array);
    for (int i = 0; i < size; i++)
    {
        int j = i;
        while (j > 0 && array[j] < array[j - 1])
        {
            swap(array[j - 1], array[j]);
            j--;
        }
    }
}

void printArray(int array[])
{
    int size = getSize(array);
    int amount = 1;

    for (int i = 0; i < size; i++)
    {
        if (array[i] != array[i + 1])
        {
            cout << endl << array[i] << " amount: " << amount << " ";
            amount = 1;
        }
        else
            amount++;
    }
}

int main()
{
    cout << "maxSize = " << maxSize << "\nInput array's elements: ";
    int array[maxSize] = {0};
    inputArray(array);
    sort(array);
    printArray(array);

    return 0;
}
