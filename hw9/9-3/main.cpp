#include <iostream>
#include <fstream>
#include "haffman.h"

using namespace std;

int main()
{
    decodeText("output.txt", "input.txt", "keys.txt");
    cout << "done";
    return 0;
}
