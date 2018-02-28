#include <iostream>
#include "calculate.h"

using namespace std;

int main()
{
    int length = getMaxLength();

    cout << "Enter expression(without spaces): ";
    char *expression = inputExpression(length);

    char *converted = convert(expression, length);
    cout << "Converted: " << converted;

    cout << "\nResult = " << calculate(converted, length);

    delete[] expression;
    delete[] converted;
    return 0;
}
