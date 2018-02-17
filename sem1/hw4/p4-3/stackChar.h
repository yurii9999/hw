#pragma once

struct Stack;

Stack *createStack();
void deleteStack(Stack *stack);

void push(Stack *stack, char value);
char pop(Stack *stack);
char peek(Stack *stack);

bool isEmpty(Stack *stack);
int sizeStack(Stack *stack);

void printStack(Stack *stack);
