#include <iostream>
#include <fstream>

#include "list.h"

using namespace std;

void getLists(List *lowerA, List *another, List *biggerB, int a, int b)
{
    int currentValue = 0;
    ifstream in("input.txt");

    while (in.peek() != EOF)
    {
        in >> currentValue;
        if (currentValue < a)
            addElementTo(lowerA, currentValue, 0);
        else
        {
            if (currentValue > b)
                addElementTo(biggerB, currentValue, 0);
            else
                addElementTo(another, currentValue, 0);
        }
    }

    in.close();
}

void putList(List *list, ostream &out)
{
    int lastPosition = sizeList(list) - 1;

    while(lastPosition >= 0)
    {
        out << peekListElement(list, lastPosition);
        out << ' ';
        lastPosition--;
    }
}

void putLists(List *lowerA, List *another, List *biggerB)
{
    int lastPosition1 = sizeList(lowerA) - 1;
    int lastPosition2 = sizeList(another) - 1;
    int lastPosition3 = sizeList(biggerB) - 1;

    ofstream out("output.txt");

    putList(lowerA, out);
    out << '\n';
    putList(another, out);
    out << '\n';
    putList(biggerB, out);

    out.close();
}

int main()
{
    int a = 0;
    cout << "Enter a: ";
    cin >> a;

    int b = 0;
    cout << "Enter b: ";
    cin >> b;

    List* biggerB = createList();
    List* lowerA = createList();
    List* another = createList();

    getLists(lowerA, another, biggerB, a, b);

    putLists(lowerA, another, biggerB);

    deleteList(lowerA);
    deleteList(another);
    deleteList(biggerB);

    return 0;
}
