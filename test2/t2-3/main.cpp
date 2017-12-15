#include <iostream>
#include <fstream>

using namespace std;

int main()
{
    ifstream in("input.txt");
    if (!in)
    {
        in.close();
        cout << "cant open";
        return 0;
    }

    bool isComment = false;
    char currentChar = 0;

    while (in.peek() != EOF)
    {
        in.get(currentChar);

        if (isComment)
        {
            if (currentChar != '\n' && currentChar != EOF)
                cout << currentChar;
            else
            {
                isComment = false;
                cout << "\n";
            }
        }
        else
        {
            if (currentChar == ';')
            {
                cout << ";";
                isComment = true;
            }
        }
    }

    in.close();
    return 0;
}
