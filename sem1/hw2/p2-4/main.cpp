#include <iostream>

using namespace std;

void printFareysValues(int n)
{
    int nowUp = 1;
    int nowDown = n;

    int previousUp = 0;
    int previousDown = 1;

    while (nowUp / nowDown < 1)
    {
        int nextUp = (int) (n + previousDown) / nowDown * nowUp - previousUp;
        int nextDown = ((int) ((n + previousDown) / nowDown)) * nowDown - previousDown;
        cout << nowUp << " / " << nowDown << endl;
        previousUp = nowUp;
        previousDown = nowDown;
        nowUp = nextUp;
        nowDown = nextDown;
    }
}

int main()
{
    int n = 0;
    cout << "Введите n : ";
    cin >> n;
    printFareysValues(n);
    return 0;
}
