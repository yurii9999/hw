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
    int status = 0;

    char c = 0;
    cin.get(c);

    while(c != '\n')
    {
        switch (status)
        {
        case 0:
            if (isSign(c))
                status = 1;
            else
                if(isDigit(c))
                    status = 2;
                else
                    status = -1;
            break;
        case 1:
            if (isDigit(c))
                status = 2;
            else
                status = -1;
            break;
        case 2:
            if (isDigit(c))
                status = 2;
            else
                if (c == '.')
                    status = 3;
                else
                    status = -1;
            break;
        case 3:
            if (isDigit(c))
                status = 4;
            else
                status = -1;
            break;
        case 4:
            if (isDigit(c))
                status = 4;
            else
                if (c == 'E')
                    status = 5;
                else
                    status = -1;
            break;
        case 5:
            if (isSign(c))
                status = 6;
            else
                if (isDigit(c))
                    status = 7;
                else
                    status = -1;
            break;
        case 6:
            if (isDigit(c))
                status = 7;
            else
                status = -1;
            break;
        case 7:
            if (isDigit(c))
                status = 7;
            else
                status = -1;
            break;
        }

        cin.get(c);
    }

    if (status == 7 || status == 2 || status == 4)
        cout << "double";
    else
        cout << "not double";

    return 0;
}
