#include <iostream>

using namespace std;

int getFirstSolutionId(int id, int *solversId)
{
    int temp = id;

    while (temp != solversId[temp])
        temp = solversId[temp];

    return temp;
}

int main()
{
    int amountPeople = 0;
    cout << "\nEnter amountPeople(1-3 are included): ";
    cin >> amountPeople;
    amountPeople++; // 0 - empty

    int *solversId = new int[amountPeople];

    for (int i = 0; i <= 3; i++)
        solversId[i] = i;

    for (int i = 4; i < amountPeople; i++)
    {
        cout << "Enter student's id and solver's id (0 if he hasnt): ";
        int id = 0;
        cin >> id >> solversId[id];
    }

    for (int i = 1; i < amountPeople; i++)
    {
        int firstSolutionId = getFirstSolutionId(i, solversId);
        if (firstSolutionId != 0)
            cout << "\nStudent id: " << i << "\tSolution id: " << firstSolutionId;
        else
            cout << "\nStudent id: " << i << "\thasnt solution";
    }

    delete[] solversId;
    return 0;
}
