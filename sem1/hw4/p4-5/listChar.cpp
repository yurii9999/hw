#include <iostream>
#include "listChar.h"

struct List
{
    ListElement *head;
};

struct ListElement
{
    char value;
    ListElement *next;
};

List *createList()
{
    List *newList = new List;
    newList->head = nullptr;
    return newList;
}

void addElementTo(List *list, char value, int position)
{    
    if (position == 0)
    {
        ListElement *temp = new ListElement;
        temp->value = value;
        temp->next = list->head;
        list->head = temp;
    }
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

char peekListElement(List *list, int position)
{
    if (position == 0)
    {
        return list->head->value;
    }
}

void deleteElementFrom(List *list, int position)
{
    if (position == 0)
    {
        ListElement *temp = list->head;
        list->head = temp->next;
        delete temp;
    }
}

void deleteList(List *list)
{
    while (list->head != nullptr)
    {
        deleteElementFrom(list, 0);
    }
    delete list->head;
    delete list;
}
