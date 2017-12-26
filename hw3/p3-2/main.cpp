#include <iostream>
#include "strings.h"

using namespace std;

int main()
{
    int length1 = 0;
    cout << "Введите длину строки1: ";
    cin >> length1;
    char* string1 = new char [length1];
    cout << "Введите строку1: ";
    inputString(string1, length1);

    int length2 = 0;
    cout << "Введите длину строки2: ";
    cin >> length2;
    char* string2 = new char [length2];
    cout << "Введите строку2: ";
    inputString(string2, length2);

    sortString(string1, 0, length1 - 1);
    sortString(string2, 0, length2 - 1);

    if (equalStrings(string1, length1, string2, length2))
    {
        cout << "Можно получить";
    }
    else
    {
        cout << "Нельзя";
    }

    delete [] string1;
    delete [] string2;

    return 0;
}
