#include <iostream>
#include <fstream>

int const alphabetSize = 255;

using namespace std;

bool nextWord(char symbol)
{
    if (symbol == ' ')
        return true;
    if (symbol == EOF)
        return true;
    if (symbol == '\n')
        return true;

    return false;
}

void putAllFalse(bool array[alphabetSize])
{
    for (int i = 0; i < alphabetSize; i++)
        array[i] = false;
}

int main()
{
    ifstream in("input.txt");

    bool isUsed[alphabetSize] = {false};
    char currentChar = 0;

    while (in.peek() != EOF)
    {
        in.get(currentChar);
        if (!nextWord(currentChar))
        {
            if (!isUsed[(int) currentChar])
            {
                cout << currentChar;
                isUsed[(int) currentChar] = true;
            }
        }
        else
        {
            putAllFalse(isUsed);
            cout << currentChar;
        }
    }

    in.close();
    return 0;
}
