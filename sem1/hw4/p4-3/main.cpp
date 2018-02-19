#include <iostream>
#include "calculate.h"

using namespace std;

int main()
{
    int length = getMaxLength();

    cout << "Input expression(without spaces): ";
    char *expression = inputExpression(length);

    char *newExpression = convert(expression, length);
    cout << newExpression;

    delete[] newExpression;
    delete[] expression;
    return 0;
}
