#include <iostream>
#include "ints.h"

int getIntLength(int value)
{
    int length = 0;
    while (value != 0)
    {
        value /= 10;
        length++;
    }
    return length;
}
