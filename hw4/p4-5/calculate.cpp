#include <iostream>
#include "calculate.h"
#include "stackChar.h"

using namespace ::std;

int const maxLength = 100;

int getMaxLength()
{
    return maxLength;
}

char *inputExpression(int length)
{
    char *expression = new char[length];
    for (int i = 0; i < length; i++)
        expression[i] = 0;

    cin >> expression;

    return expression;
}

int getValue(char a)
{
    return a - '0';
}

char toChar(int a)
{
    return a + '0';
}

char miniCalculate(char a, char b, char sign)
{
    a = getValue(a); // '1' -> 1
    b = getValue(b);

    switch (sign)
    {
    case '*':
        return toChar(a * b);
        break;
    case '+':
        return toChar(a + b);
        break;
    case '/':
        return toChar(a / b);
        break;
    case '-':
        return toChar(a - b);
        break;
    default:
        break;
    }

    return -1;
}

int calculate(char *expression, int length)
{
    Stack *values = createStack();

    for (int i = 0; i < length && expression[i] != 0; i++)
    {
        if (expression[i] >= '0' && expression[i] <= '9')
        {
            push(values, expression[i]);
        }
        if (expression[i] == '*' || expression[i] == '/' || expression[i] == '+' || expression[i] == '-')
        {
            char b = pop(values);
            char a = pop(values);
            push(values, miniCalculate(a, b, expression[i]));
        }
    }
    char result = pop(values);
    deleteStack(values);

    return getValue(result);
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

        else
        {
            if (isEmpty(signs) || peek(signs) == '(')
                push(signs, expression[i]);
            else
                if (expression[i] == '*' || expression[i] == '/')
                {
                    if (peek(signs) == '+' || peek(signs) == '-')
                        push(signs, expression[i]);
                    if (peek(signs) == '/' || peek(signs) == '*')
                    {
                        char topSign = peek(signs);
                        while (topSign != '+' && topSign != '-' && topSign != '(')
                        {
                            newExpression[newLength] = pop(signs);
                            newLength++;
                            topSign = peek(signs);
                        }
                    }
                }
                if (expression[i] == '+' || expression[i] == '-')
                {
                    while (peek(signs) != '(')
                    {
                        newExpression[newLength] = pop(signs);
                        newLength++;
                    }
                }




        }
        /*
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
            while (peek(signs) == '+' || peek(signs) == '-')
            {
                newExpression[newLength] = pop(signs);
                newLength ++;
            }
            push(signs, expression[i]);
        }
        */

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
