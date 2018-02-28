#include <iostream>
#include "list.h"

using namespace std;

List *getNewList(List *list)
{
    int maxSize = 0;
    int from = 0;
    int size= sizeList(list);
    int nowSize = 1;
    int currentValue = 0;

    int previosValue = peekListElement(list, 0);
    for (int i = 1; i < size; i++)
    {
        currentValue = peekListElement(list, i);
        if (currentValue > previosValue)
            nowSize++;
        else
            nowSize = 1;

        if (nowSize > maxSize)
        {
            maxSize = nowSize;
            from = i - nowSize + 1;
        }
        previosValue = currentValue;
    }

    List *newList = createList();
    for (int i = 0; i < maxSize; i++)
    {
        int element = peekListElement(list, from + i);
        addElementTo(newList, element, i);
    }

    return newList;
}

int main()
{
    List *list = createList();

    cout << "\nEnter numbers, to end enter -1: ";
    int currentValue = 0;
    int size = 0;

    cin >> currentValue;
    while (currentValue != -1)
    {
        addElementTo(list, currentValue, size);
        size++;
        cin >> currentValue;
    }
    cout << "\nList: ";
    printList(list);
    List *newList = getNewList(list);
    cout << "\nNew list: ";
    printList(newList);

    deleteList(list);
    deleteList(newList);
    return 0;
}
