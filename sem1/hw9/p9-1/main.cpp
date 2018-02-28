#include <iostream>
#include <fstream>
#include "graph.h"

using namespace std;

int *getOrder(int *array, int size)
{
    int *order = new int[size];
    for (int i = 0; i < size; i++)
        order[i] = i;

    for (int i = 0; i < size; i++)
    {
        int j = i;
        while (j > 0 && array[j] < array[j - 1])
        {
            swap(array[j], array[j - 1]);
            swap(order[j], order[j - 1]);
            j--;
        }
    }

    return order;
}

int main()
{
    Graph *graph = readGraph("input.txt");
    cout << "graph:\n";
    printGraph(graph);
    int currentVertex = 0;
    cout << "\nLength from " << currentVertex << ": \n";
    int *lengthFrom = getMinLengthsFrom(graph, currentVertex);
    int size = getSize(graph);
    for (int i = 0; i < size; i++)
        cout << i << ": " << lengthFrom[i] << "\n";

    cout << "\norder: ";
    int *order = getOrder(lengthFrom, size);
    for (int i = 0; i < size; i++)
        cout << order[i] << " ";

    deleteGraph(graph);
    delete[] order;
    delete[] lengthFrom;
    return 0;
}
