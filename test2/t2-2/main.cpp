#include <iostream>
#include <fstream>
#include "list.h"

using namespace std;

bool isSymmetric(List *list)
{
    int size = sizeList(list);
    int maxAccess = size - 1;
    for (int i = 0; i < size / 2; i++)
    {
        int elementA = peekListElement(list, i);
        int elementB = peekListElement(list, maxAccess - i);
        if (elementA != elementB)
            return false;
    }

    return true;
}

int main()
{
    ifstream in("input.txt");
    if (!in)
    {
        cout << "cant open";
        in.close();
        return 0;
    }

    List *list = createList();
    int currentValue = 0;
    int size = 0;

    while (in.peek() != EOF)
    {
        in >> currentValue;
        addElementTo(list, currentValue, size);
        size++;
    }
    in.close();

    cout << "\nList: ";
    printList(list);
    cout << "\nIs symmetric?";

    if (isSymmetric(list))
        cout << "\nYes";
    else
        cout << "\nNo";

    deleteList(list);
    return 0;
}
