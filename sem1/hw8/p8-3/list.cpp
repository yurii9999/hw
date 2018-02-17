#include <iostream>
#include "list.h"

struct ListElement
{
    String *string;
    int value;
    ListElement *next;
};

struct List
{
    ListElement *head;
};


List *createList()
{
    List *newList = new List;
    newList->head = nullptr;
    return newList;
}

void addElementTo(List *list, String *string, int position)
{    
    if (position == 0)
    {
        ListElement *newElement = new ListElement;
        newElement->string = clone(string);
        newElement->next = list->head;
        newElement->value = 1;
        list->head = newElement;
        return;
    }

    ListElement *newElement = new ListElement;
    ListElement *temp = list->head;

    newElement->string = clone(string);
    newElement->value = 1;

    for (int i = 1; i < position; i++)
    {
        temp = temp->next;
    }

    newElement->next = temp->next;
    temp->next = newElement;
}

bool isEmpty(List *list)
{
    return (list->head == nullptr);
}

int sizeList(List *list)
{
    int result = 0;
    ListElement *temp = list->head;
    while (temp != nullptr)
    {
        result++;
        temp = temp->next;
    }
    return result;
}

void printList(List *list)
{

    ListElement *temp = list->head;

    int position = 0;
    while (temp != nullptr)
    {
        std::cout << position << ": string = ";
        printString(temp->string);
        std::cout << " " << "value = " << temp->value << "\n";
        position++;
        temp = temp->next;
    }
}

String *peekListElement(List *list, int position)
{
    ListElement *temp = list->head;
    for (int i = 0; i < position; i++)
    {
        temp = temp->next;
    }

    return temp->string;
}

void deleteElementFrom(List *list, int position)
{
    if (position == 0)
    {
        ListElement *temp = list->head;
        list->head = temp->next;
        deleteString(temp->string);
        delete temp;
        return;
    }

    ListElement *temp = list->head;
    for (int i = 0; i < position - 1; i++)
    {
        temp = temp->next;
    }

    ListElement *deleteThis = temp->next;
    temp->next = temp->next->next;

    deleteString(deleteThis->string);
    delete deleteThis;
}

bool isHere(List *list, String *string)
{
    ListElement *temp = list->head;

    while(temp != nullptr)
    {
        if (areEqual(string, temp->string))
            return true;
        temp = temp->next;
    }

    return false;
}

void increaseValue(List *list, String *string)
{
    ListElement *temp = list->head;

    while(temp != nullptr)
    {
        if (areEqual(string, temp->string))
        {
            temp->value++;
            return;
        }
        temp = temp->next;
    }
}

int getSumValues(List *list)
{
    int result = 0;

    if (isEmpty(list))
        return 0;

    ListElement *temp = list->head;
    while(temp != nullptr)
    {
        result = result + temp->value;
        temp = temp->next;
    }
    return result;
}

void deleteList(List *&list)
{
    while (!isEmpty(list))
        deleteElementFrom(list, 0);
    delete list;
}
