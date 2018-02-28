#include <iostream>
#include "stringg.h"

using namespace std;

int main()
{
    String *string = createString();
    cout << "\nInput string: ";
    inputString(string);
    cout << "String: ";
    printString(string);
    cout << "\tLength: " << getLength(string);

    cout << "Hello World!" << endl;

    deleteString(string);
    return 0;
}
