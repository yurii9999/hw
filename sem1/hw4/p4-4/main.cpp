#include <iostream>
#include "calculate.h"

using namespace std;

int main()
{
    int length = getMaxLength();
    cout << "Input expression: ";
    char *expression = inputExpression(length);
    cout << "result = " << calculate(expression, length);

    delete[] expression;
    return 0;
}
