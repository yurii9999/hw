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
    cin >> expression;

    return expression;
}

int getValue(char a)
{
    return a - 48;
}

char toChar(int a)
{
    return a + 48;
}

char miniCalculate(char a, char b, char sign)
{
    a = getValue(a); // '1' -> 1
    b = getValue(b);
    if (sign == '*')
        return toChar(a * b);
    if (sign == '/')
        return toChar(a / b);
    if (sign == '+')
        return toChar(a + b);
    if (sign == '-')
        return toChar(a - b);
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
