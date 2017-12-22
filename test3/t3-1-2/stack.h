#pragma once

struct Stack;

Stack *createStack();
void deleteStack(Stack *&stack);

void push(Stack *stack, int value);
int pop(Stack *stack);
int peek(Stack *stack);

bool isEmpty(Stack *stack);
int sizeStack(Stack *stack);

void printStack(Stack *stack);
void clearStack(Stack *stack);
