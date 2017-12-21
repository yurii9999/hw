#include <iostream>
#include <fstream>

using namespace std;

bool getArray(char *fileName,int **&array, int &m, int &n)
{
    ifstream in(fileName);
    if (!in)
        return false;

    in >> m >> n;

    array = new int*[m];
    for (int i = 0; i < m; i++)
        array[i] = new int[n];

    for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++)
            in >> array[i][j];

    in.close();
    return true;
}

void sort(int **array, int m, int collum)
{

    for (int i = 0; i < m; i++)
    {
        int j = i;
        while (j > 0 && array[j][collum] > array[j - 1][collum])
        {
            swap(array[j], array[j - 1]);
            j--;
        }
    }
}

void printArray(int **array, int m, int n)
{
    for (int i = 0; i < m; i++)
    {
        for (int j = 0; j < n; j++)
            cout << "\t" << array[i][j];
        cout << "\n";
    }
}

bool isMin(int *array, int size, int pos)
{
    int min = array[pos];

    for (int i = 0; i < size; i++)
    {
        if (array[i] <= min)
            if (i != pos)
                return false;
    }
    return true;
}

void printAllSPoints(int **array, int m, int n)
{
    for (int i = 0; i < n; i++)
    {
        sort(array, m, i);
        if (array[0][i] > array[1][i])
            if (isMin(array[0], n, i))
                cout << "\nGood element: " << array[0][i];
    }
}

void deleteArray(int **&array, int m)
{
    for (int i = 0; i < m; i++)
        delete[] array[i];

    delete[] array;
}

int main()
{
    int n = 0;
    int m = 0;
    int **array = nullptr;
    if (!getArray("input.txt", array, m, n))
    {
        cout << "cant open";
        return 0;
    }
    printAllSPoints(array, m, n);

    deleteArray(array, m);
    return 0;
}
