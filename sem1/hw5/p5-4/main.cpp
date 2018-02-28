#include <iostream>
#include <fstream>

using namespace std;

int main()
{
    char *fileName = "input.txt";
    ifstream in (fileName);
    int result = 0;
    cout << "File: " << fileName << "\n";

    while(in.peek() != EOF)
    {
        while (in.peek() == ' ' || in.peek() == '\t')
            in.get();

        if (in.peek() != '\n')
            result++;

        while (in.peek() != '\n' && in.peek() != EOF)
            in.get();

        if (in.peek() == '\n')
            in.get();
    }

    cout << "result: " << result;

    in.close();
    return 0;
}
