#include <iostream>
#include <fstream>
#include "haffamn.h"

using namespace std;

int main()
{
    encodeText("input.txt", "output.txt", "keys.txt", "prioritys.txt");
    cout << "done";
    return 0;
}
