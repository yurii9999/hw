#include <iostream>
#include "calculate.h"
#include "stackChar.h"

using namespace ::std;

int const maxLength = 100;

int getMaxLength()
{
    return maxLength;
}

char *convert(char *expression, int length)
{
    Stack *signs = createStack();
    char *newExpression = new char[length];

    for (int i = 0; i < length; i++)
        newExpression[i] = 0;

    int newLength = 0;

    for (int i = 0; i < length; i++)
    {
        if (expression[i] >= '0' && expression[i] <= '9')
        {
            newExpression[newLength] = expression[i];
            newLength ++;
        }

        if (expression[i] == '*' || expression[i] == '/')
        {
            while (peek(signs) == '*' || peek(signs) == '/')
            {
                newExpression[newLength] = pop(signs);
                newLength ++;
            }
            push(signs, expression[i]);
        }
        if (expression[i] == '+' || expression[i] == '-')
        {
            while (peek(signs) == '+' || peek(signs) == '-' || peek(signs) == '+' || peek(signs) == '-')
            {
                newExpression[newLength] = pop(signs);
                newLength ++;
            }
            push(signs, expression[i]);
        }

        if (expression[i] == '(')
        {
            push(signs, expression[i]);
        }
        if (expression[i] == ')')
        {
            while (peek(signs) != '(')
            {
                newExpression[newLength] = pop(signs);
                newLength ++;
            }

            pop(signs);
        }
    }

    while (!isEmpty(signs))
    {
        newExpression[newLength] = pop(signs);
        newLength ++;
    }
    deleteStack(signs);
    return newExpression;
}

char *inputExpression(int length)
{
    char *expression = new char[length];
    cin >> expression;

    return expression;
}
