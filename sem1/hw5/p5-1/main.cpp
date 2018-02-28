#include <iostream>

using namespace std;

union Value
{
  double asDouble;
  long int asInt;
};

char getSign(Value value)
{
    long int sign = value.asInt & 0x8000000000000000;
    if (sign)
        return '-';

    return '+';
}

double getMantissa(Value value)
{
    long int mantissa = value.asInt & 0x00FFFFFFFFFFFFFF;
    mantissa = mantissa | 0x3FF0000000000000;

    Value result;
    result.asInt = mantissa;

    return result.asDouble;
}

long int getExp(Value value)
{
    long int exp = value.asInt & 0x7FFF000000000000;
    exp = exp >> 52;

    return exp - 1023;
}

int main()
{
    cout << "\nEnter double: ";
    Value a;
    cin >> a.asDouble;

    cout << "\nResult: " << getSign(a) << getMantissa(a) << "*2^" << getExp(a);
    return 0;
}
