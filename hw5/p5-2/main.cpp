#include <iostream>

using namespace std;

void printValue(short int value)
{
    int bit = 0x8000;

    for (int i = 0; i < 16; i++)
    {
        if (value & bit)
            cout << "1";
        else
            cout << "0";

        bit = bit >> 1;
    }

}

bool threeOnesAndMore(short int a, short int b, short int c, short int bit)
{
    if ((c & bit) && (b & bit))
        return true;
    if ((c & bit) && (a & bit))
        return true;
    if ((a & bit) && (b & bit))
        return true;

    return false;
}

short int calculateSum(int a, int b)
{
    short int bit = 0x0001;
    short int result = 0x0000;
    short int extra = 0x0000;

    for (int i = 0; i < 16; i ++)
    {
        if ((extra & bit) && (a & bit) && (b & bit))
        {
            result = result | bit;
            extra = bit << 1;
        }
        else
        {
            if (threeOnesAndMore(extra, a, b, bit))
            {
                    extra = bit << 1;
            }
            else
            {
                if ((extra | a | b) & bit)
                {
                    result = result | bit;
                    extra = 0x0000;
                }
            }
        }

        bit = bit << 1;
    }

    return result;
}

int main()
{
    short int a = 0;
    short int b = 0;
    cout << "Enter a: ";
    cin >> a;
    cout << "Enter b: ";
    cin >> b;

    cout << endl << "a = ";
    printValue(a);
    cout << endl << "b = ";
    printValue(b);
    short int sum = calculateSum(a , b);
    cout << endl << "a + b = " << sum << " = ";
    printValue(sum);
    return 0;
}
