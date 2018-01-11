#include <iostream>

int calculateFibonacci(int number)
{
    int now = 0;
    int next = 1;
    int temp = 0;
    for (int i = 0; i < number; i++)
    {
        temp = now;
        now = next;
        next = temp + now;
    }
    return now;
}


using namespace std;

int main()
{
    int number = 0;
    cout << "Enter niumber: ";
    cin >> number;
    cout << endl << "Result = " << calculateFibonacci(number);
    return 0;
}
