#include <iostream>

using namespace std;

bool isDigit(char c)
{
    return c >= '0' && c <= '9';
}

bool isSign(char c)
{
    return c == '+' || c == '-';
}

int main()
{
//    0 - start
//    1 - first +/-
//    2 - first digits
//    3 - dot
//    4 - digits after dot
//    5 - 'E'
//    6 - +/- after E
//    7 - digits after E
//    -1 - fail
//    good : 2, 4, 7
    enum {start, firstSign, firstDigits, dot, digitsAfterDot, E, signAfterE, digitsAfterE, fail} status = start;

    cout << "\nEnter number: ";
    char c = 0;
    cin.get(c);

    while(c != '\n')
    {
        switch (status)
        {
        case start:
            if (isSign(c))
                status = firstSign;
            else
                if(isDigit(c))
                    status = firstDigits;
                else
                    status = fail;
            break;
        case firstSign:
            if (isDigit(c))
                status = firstDigits;
            else
                status = fail;
            break;
        case firstDigits:
            if (isDigit(c))
                status = firstDigits;
            else
                if (c == '.')
                    status = dot;
                else
                    if (c == 'E')
                        status = E;
                    else
                        status = fail;
            break;
        case dot:
            if (isDigit(c))
                status = digitsAfterDot;
            else
                status = fail;
            break;
        case digitsAfterDot:
            if (isDigit(c))
                status = digitsAfterDot;
            else
                if (c == 'E')
                    status = E;
                else
                    status = fail;
            break;
        case E:
            if (isSign(c))
                status = signAfterE;
            else
                if (isDigit(c))
                    status = digitsAfterE;
                else
                    status = fail;
            break;
        case signAfterE:
            if (isDigit(c))
                status = digitsAfterE;
            else
                status = fail;
            break;
        case 7:
            if (isDigit(c))
                status = digitsAfterE;
            else
                status = fail;
            break;
        }
        cin.get(c);
    }

    if (status == digitsAfterE || status == firstDigits || status == digitsAfterDot)
        cout << "double";
    else
        cout << "not double";

    return 0;
}
