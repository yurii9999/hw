#include <iostream>

using namespace std;

bool isLetter(char c)
{
    return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
}

bool isDigit(char c)
{
    return c >= '0' && c <= '9';
}

int main()
{
    char c = 0;
    enum {start, fail, afterFirstLetter} state = start;

    cout << "\nEnter string: ";
    cin.get(c);
    while (c != '\n')
    {
        switch (state)
        {
        case start:
            if (isLetter(c))
                state = afterFirstLetter;
            else
                state = fail;
            break;
        case afterFirstLetter:
            if (isLetter(c) || isDigit(c) || c == '_')
                state = afterFirstLetter;
            else
                state = fail;
        default:
            break;
        }
        cin.get(c);
    }

    cout << "Result : ";
    if (state == afterFirstLetter)
        cout << "true";
    else
        cout << "false";

    return 0;
}
