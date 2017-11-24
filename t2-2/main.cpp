#include <iostream>
#include <fstream>

using namespace std;

struct Data
{
    char d[2];
    char m[2];
    char y[4];
};

bool getData(istream &in, Data &data)
{
    for (int i = 0; i < 2; i++)
    {
        if (in.peek() < '0' || in.peek() > '9')
            return false;
        in.get(data.d[i]);
    }
    if (in.peek() != '.')
        return false;
    in.get();

    for (int i = 0; i < 2; i++)
    {
        if (in.peek() < '0' || in.peek() > '9')
            return false;
        in.get(data.m[i]);
    }
    if (in.peek() != '.')
        return false;

    for (int i = 0; i < 4; i++)
    {
        if (in.peek() < '0' || in.peek() > '9')
            return false;
        in.get(data.y[i]);
    }
    if (in.peek() != ' ')
        return false;

    return true;
}

bool isLower(Data a, Data b)
{
    for (int i = 0; i < 4; i++)
        if ((a.y[i]) < (b.y[i]))
            return true;
    for (int i = 0; i < 2; i++)
        if (a.m[i] < b.m[i])
            return true;
    for (int i = 0; i < 2; i++)
        if (a.d[i] < b.d[i])
            return true;
    return false;
}

void rememberThisData(Data &a, Data b)
{
    for (int i = 0; i < 4; i++)
        a.y[i] = b.y[i];

    for (int i = 0; i < 2; i++)
        a.m[i] = b.m[i];

    for (int i = 0; i < 2; i++)
        a.d[i] = b.d[i];
}

void printData(Data data)
{
    for (int i = 0; i < 4; i++)
        cout << data.y[i];
    cout << ".";
    for (int i = 0; i < 2; i++)
        cout << data.m[i];
    cout << ".";
    for (int i = 0; i < 2; i++)
        cout << data.d[i];
}

int main()
{
    Data currentData;
    Data lowest;
    lowest.y[0] = '9' + 1;

    ifstream in("input.txt");
    char currentChar = 0;

    while (in.peek() != EOF)
    {
        in.get(currentChar);

        if (currentChar == ' ')
        {
            if (getData(in, currentData))
            {
                if(isLower(currentData, lowest))
                    rememberThisData(lowest, currentData);
            }
        }

    }

    in.close();
    cout << "lowest Data: ";
    printData(lowest);
    return 0;
}
