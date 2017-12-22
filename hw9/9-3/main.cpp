#include <iostream>
#include <fstream>
#include "haffman.h"

using namespace std;

int main()
{
    decodeText("input.txt", "output.txt", "keys.txt");
    cout << "done";
    return 0;
}
