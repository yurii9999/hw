#include <iostream>
#include <fstream>
#include "haffamn.h"

int const alphobetSize = 255;
int const maxKeyLength = 20;

using namespace ::std;

struct Node
{
    Node *zero;
    Node *one;

    int priority;
    char symbol;
};

struct symbolAndKey
{
    char symbol = 0;
    char key[maxKeyLength] = {0};
};

void sortByPriority(Node *symbols, int size)
{
    for (int i = 1; i < size; i++)
    {
        int j = i;
        while (j > 0 && symbols[j - 1].priority < symbols[j].priority)
        {
            swap(symbols[j - 1], symbols[j]);
            j--;
        }
    }
}

void sort(int size, int* &array)
{
    for (int i = 1; i < size; i++)
    {
        int j = i;
        while (j > 0 && array[j - 1] > array[j])
        {
            int temp = array[j - 1];
            array[j - 1] = array[j];
            array[j] = temp;
            j--;
        }
    }
}

Node createNode(char symbol, int priority)
{
    Node newNode;
    newNode.symbol = symbol;
    newNode.priority = priority;
    newNode.one = nullptr;
    newNode.zero = nullptr;

    return newNode;
}

Node *rewriteTableToNodes(int *priorityTable, int amountNonZeros)
{
    Node *symbols = new Node[amountNonZeros];

    int j = 0;
    for (int i = 0; i < alphobetSize; i++)
        if (priorityTable[i] != 0)
        {
            symbols[j] = createNode((char) i, priorityTable[i]);
            j++;
        }

    return symbols;
}

int *getPriorityTable(char *fileName)
{
    ifstream in(fileName);
    char currentChar = 0;
    int *priorityTable = new int[alphobetSize];

    for (int i = 0; i < alphobetSize; i++)
        priorityTable[i] = 0;

    while (in.peek() != EOF)
    {
        in.get(currentChar);
        priorityTable[(int)currentChar]++;
    }

    in.close();
    return priorityTable;
}

int amountZeros(int *array, int size)
{
    int result = 0;
    for (int i = 0; i < size; i++)
        if (array[i] == 0)
            result++;

    return result;
}

Node *cloneNode(Node a)
{
    Node *newNode = new Node;
    newNode->symbol = a.symbol;
    newNode->priority = a.priority;
    newNode->one = a.one;
    newNode->zero = a.zero;

    return newNode;
}

Node concatinate(Node a, Node b)
{
    Node newNode;
    newNode.one = cloneNode(a);
    newNode.zero = cloneNode(b);
    newNode.symbol = 0;
    newNode.priority = a.priority + b.priority;

    return newNode;
}

Node *buildTree(Node *symbols, int size)
{
    sortByPriority(symbols, size);
    Node *temp = new Node[size];

    for (int i = 0; i < size; i++)
    {
        temp[i].one = nullptr;
        temp[i].zero = nullptr;
        temp[i].priority = symbols[i].priority;
        temp[i].symbol = symbols[i].symbol;
    }

    while (size != 1)
    {
        Node tempNode = concatinate(temp[size - 1], temp[size - 2]);
        swap(tempNode, temp[size - 2]);
        size--;
        sortByPriority(temp, size);
    }

    Node *result = cloneNode(temp[0]);
    delete[] temp;

    return result;
}

void loadBase(symbolAndKey *base, char symbol, char key[maxKeyLength])
{
    int i = 0;
    while (base[i].symbol != 0)
        i++;

    base[i].symbol = symbol;

    for (int j = 0; j < maxKeyLength; j++)
        base[i].key[j] = key[j];
}

void parseKeys(symbolAndKey *base, Node *node, char key[maxKeyLength],int  nowLength)
{
    if (node->symbol == 0)
    {
        key[nowLength] = '1';
        for (int i = nowLength + 1; i < maxKeyLength; i++)
            key[i] = 0;
        parseKeys(base, node->one, key, nowLength + 1);

        key[nowLength] = '0';
        for (int i = nowLength + 1; i < maxKeyLength; i++)
            key[i] = 0;
        parseKeys(base, node->zero, key, nowLength + 1);
    }
    else
    {
        loadBase(base, node->symbol, key);
    }
}

char *getKey(symbolAndKey *base, char symbol)
{
    int i = 0;
    while (base[i].symbol != symbol)
        i++;

    return base[i].key;
}

void deleteNode(Node *node)
{
    if (node->one != nullptr)
        deleteNode(node->one);
    if (node->zero != nullptr)
        deleteNode(node->zero);

    delete node;
}

void encodeText(char *inputFile, char *outputFile, char *tableKey, char *tablePriority)
{
    ifstream in(inputFile);
    if (!in)
    {
        cout << "cant open";
        in.close();
        return;
    }

    int *priorityTable = getPriorityTable(inputFile);
    int amountNonZeros = alphobetSize - amountZeros(priorityTable, alphobetSize);

    Node *symbols = rewriteTableToNodes(priorityTable, amountNonZeros);
    Node *root = buildTree(symbols, amountNonZeros);
    char toCallFunc[maxKeyLength] = {0};
    symbolAndKey *keyTable = new symbolAndKey[amountNonZeros];
    parseKeys(keyTable, root, toCallFunc, 0);

    //encode:
    ofstream out(outputFile);

    char currentChar = 0;
    while (in.peek() != EOF)
    {
        in.get(currentChar);
        out << getKey(keyTable, currentChar);
    }

    in.close();
    out.close();

    //saveKeys:
    ofstream outKeys(tableKey);

    for (int i = 0; i < amountNonZeros; i++)
        outKeys << keyTable[i].symbol << "-" << keyTable[i].key << endl;

    outKeys.close();

    //savePrioritys:
    ofstream outPriority(tablePriority);
    for (int i = 0; i < amountNonZeros; i++)
        outPriority << symbols[i].symbol << "-" << symbols[i].priority << endl;

    outPriority.close();

    delete[] priorityTable;
    delete[] symbols;
    deleteNode(root);
    delete[] keyTable;
}
