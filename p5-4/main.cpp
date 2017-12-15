#include <iostream>
#include <fstream>

using namespace std;

int main()
{
    ifstream in ("test.txt");
    int result = 0;
    cout << "File: test.txt \n";

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
