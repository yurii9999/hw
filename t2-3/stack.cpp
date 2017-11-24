#include <iostream>
#include "stack.h"
#include "list.h"

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

int sizeStack(Stack *stack)
{
    return sizeList(stack->list);
}

void push(Stack *stack, int value)
{
    addElementTo(stack->list, value, 0);
}

int pop(Stack *stack)
{
    int result = peekListElement(stack->list, 0);
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
