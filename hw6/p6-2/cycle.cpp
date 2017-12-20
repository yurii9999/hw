#include <iostream>
#include "cycle.h"

struct Element
{
    int value;
    Element *next;
};

struct Cycle
{
    Element *main;
};

Cycle *createCycle()
{
    Cycle *newCycle = new Cycle;
    newCycle->main = nullptr;

    return newCycle;
}

void addElement(Cycle *cycle, int value, int pos)
{
    Element *newElement = new Element;
    newElement->value = value;

    if (pos == -1)
    {

        if (cycle->main == nullptr)
            cycle->main = newElement;


        else
        {
            Element *temp = cycle->main->next;
            while (temp->next != cycle->main)
            {
                temp = temp->next;
            }
            temp->next = newElement;
        }

        newElement->next = cycle->main;
    }
}

int getCycleSize(Cycle *cycle)
{
    Element *temp = cycle->main;
    int result = 1;
    while (temp->next != cycle->main)
    {
        temp = temp->next;
        result++;
    }

    return result;
}

void printCycle(Cycle *cycle)
{
    Element *temp = cycle->main;

    while (temp->next != cycle->main)
    {
        std::cout << "\nValue: " << temp->value << "\tNext: " << temp->next->value;
        temp = temp->next;
    }
    std::cout << "\nValue: " << temp->value << "\tNext: " << temp->next->value;
}

void changeMain(Cycle *cycle)
{
    cycle->main = cycle->main->next;
}

void deleteElement(Cycle *cycle, int pos)
{
    if (pos == 0)
    {
        Element *temp = cycle->main;
        while (temp->next != cycle->main)
            temp = temp->next;

        temp->next = cycle->main->next;
        delete cycle->main;
        cycle->main = temp->next;
    }
}

int getMain(Cycle *cycle)
{
    return cycle->main->value;
}

void deleteCycle(Cycle *&cycle)
{
    while(getCycleSize(cycle) != 1)
        deleteElement(cycle, 0);

    delete cycle->main;
    delete cycle;
}
