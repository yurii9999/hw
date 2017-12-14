#include <iostream>
#include <fstream>
#include "graph.h"

using namespace ::std;

int const infinite = 1000;

struct Graph
{
    int amountVertex;
    int **length;
};

Graph *readGraph(char *fileName)
{
    Graph *graph = new Graph;

    ifstream in(fileName);
    int amountVertex = 0;
    int amountEdge = 0;
    in >> amountVertex >> amountEdge;

    graph->length = new int*[amountVertex];
    for (int i = 0; i < amountVertex; i++)
        graph->length[i] = new int[amountVertex];


    graph->amountVertex = amountVertex;
    for (int i = 0; i < amountVertex; i++)
        for (int j = 0; j < amountVertex; j++)
            graph->length[i][j] = 0;

    int a = 0;
    int b = 0;
    for (int i = 0; i < amountEdge; i++)
    {
        in >> a >> b >> graph->length[a][b];
        graph->length[b][a] = graph->length[a][b];
    }

    in.close();
    return graph;
}

int getSpecialMinsIndex(int *array, bool *isOut, int size)
{
    int min = infinite;
    int result = 0;
    for (int i = 0; i < size; i++)
        if (array[i] < min && array[i] != 0 && !isOut[i])
        {
            min = array[i];
            result = i;
        }
    return result;
}
bool isAllOut(bool *array, int size)
{
    for (int i = 0; i < size; i++)
        if (array[i] == false)
            return false;

    return true;
}
void updateLengths(int *lengthsFromA, int *lengthsFromB, int size, int differenceAB)
{
    for (int i = 0; i < size; i++)
    if (lengthsFromA[i] > lengthsFromB[i] + differenceAB)
        lengthsFromA[i] = lengthsFromB[i] + differenceAB;
}

void printLengthsFrom(int *array, int size)
{
    for (int i = 0; i < size; i++)
        cout << i << ":" << array[i] << "\t";
}

int *getMinLengthsFrom(Graph *graph, int vertex)
{
    int size = graph->amountVertex;

    int *lengthFrom = new int[size];
    for (int i = 0; i < size; i++)
    {
        if (graph->length[vertex][i])
            lengthFrom[i] = graph->length[vertex][i];
        else
            lengthFrom[i] = infinite;
    }
    lengthFrom[vertex] = 0;
    bool *isOut = new bool[size];
    for (int i = 0; i < size; i++)
        isOut[i] = false;
    isOut[vertex] = true;


    while (!isAllOut(isOut, size))
    {
        int currentVertex = getSpecialMinsIndex(lengthFrom, isOut, size);
        int *lengthFromCurrent = new int[size];
        isOut[currentVertex] = true;

        for (int i = 0; i < size; i++)
        {
            if (graph->length[currentVertex][i] != 0)
                lengthFromCurrent[i] = graph->length[currentVertex][i];
            else
                lengthFromCurrent[i] = infinite;
        }

        updateLengths(lengthFrom, lengthFromCurrent, size, lengthFrom[currentVertex]);
        delete[] lengthFromCurrent;
    }

    delete[] isOut;

    return lengthFrom;
}

int getSize(Graph *graph)
{
    return graph->amountVertex;
}

void printGraph(Graph *graph)
{
    int size = graph->amountVertex;
    for (int i = 0; i < size; i++)
    {
        for (int j = 0; j < size; j++)
            cout << "\t" << graph->length[i][j];
        cout << endl;
    }
}

void deleteGraph(Graph *&graph)
{
    int size = graph->amountVertex;
    for (int i = 0; i < size; i++)
        delete[] graph->length[i];

    delete[] graph->length;
    delete graph;
}
