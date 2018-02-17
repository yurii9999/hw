#include <iostream>
#include "cycle.h"

using namespace std;

void changeMainManyTimes(Cycle *cycle, int n)
{
    for (int i = 0; i < n; i++)
        changeMain(cycle);
}

int main()
{
    Cycle *cycle = createCycle();
    int n = 0;
    int m = 0;
    cout << "Enter n, m: ";
    cin >> n >> m;

    for (int i = 0; i < n; i++)
        addElement(cycle, i, -1);

    while (getCycleSize(cycle) != 1)
    {
        changeMainManyTimes(cycle, m - 1);
        deleteElement(cycle, 0);
    }

    cout << "Safe position: " << getMain(cycle);

    deleteCycle(cycle);
    return 0;
}
