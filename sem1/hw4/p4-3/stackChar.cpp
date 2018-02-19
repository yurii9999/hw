#include <iostream>
#include "stackChar.h"
#include "listChar.h"

struct Stack
{
    List *list;
};

Stack *createStack()
{
    Stack *stack = new Stack;
    stack->list = createList();
    return stack;
}

void deleteStack(Stack *stack)
{
    deleteList(stack->list);
    delete stack;
}

int sizeStack(Stack *stack)
{
    return sizeList(stack->list);
}

void push(Stack *stack, char value)
{
    addElementTo(stack->list, value, 0);
}

char peek(Stack *stack)
{
    if (isEmpty(stack))
        return -1;
    return peekListElement(stack->list, 0);
}

char pop(Stack *stack)
{
    char result = peekListElement(stack->list, 0);
    deleteElementFrom(stack->list, 0);
    return result;
}

void printStack(Stack *stack)
{
    printList(stack->list);
}

bool isEmpty(Stack *stack)
{
    return(sizeStack(stack) == 0);
}
