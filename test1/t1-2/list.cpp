#include <iostream>
#include "list.h"

struct List
{
    ListElement *head;
};

struct ListElement
{
    int value;
    ListElement *next;
};

List *createList()
{
    List *newList = new List;
    newList->head = nullptr;
    return newList;
}

void addElementTo(List *list, int value, int position)
{    
    if (position == 0)
    {
        ListElement *newElement = new ListElement;
        newElement->value = value;
        newElement->next = list->head;
        list->head = newElement;
        return;
    }

    ListElement *newElement = new ListElement;
    ListElement *temp = list->head;
    newElement->value = value;

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

    while (temp != nullptr)
    {
        std::cout << temp->value << " ";
        temp = temp->next;
    }
}

int peekListElement(List *list, int position)
{
    ListElement *temp = list->head;
    for (int i = 0; i < position; i++)
    {
        temp = temp->next;
    }

    return temp->value;
}

void deleteElementFrom(List *list, int position)
{
    if (position == 0)
    {
        ListElement *temp = list->head;
        list->head = temp->next;
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

    delete deleteThis;
}

void deleteList(List *&list)
{
    while (!isEmpty(list))
        deleteElementFrom(list, 0);

    delete list;
}

void clearList(List *list)
{
    while(!isEmpty(list))
        deleteElementFrom(list, 0);
}
