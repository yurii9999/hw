#include <iostream>
#include <fstream>
#include "stringg.h"
#include "list.h"
#include "hash.h"

int const maxWordLength = 15;

using namespace std;

bool isNextCharBad(istream &in)
{
    if (in.peek() == ' ')
        return true;
    if (in.peek() == '\n')
        return true;
    if (in.peek() == '.')
        return true;
    if (in.peek() == ',')
        return true;
    if (in.peek() == EOF)
        return true;

    return false;
}

String *getWord(istream &in)
{
    char *chars = new char [maxWordLength];
    int length = 0;

    while (!isNextCharBad(in))
    {
        in.get(chars[length]);
        length++;
    }

    String *newString = buildString(chars, length);
    delete[] chars;
    return newString;
}

int main()
{
    List *hashArray[arraySize];
    for (int i = 0; i < arraySize; i++)
        hashArray[i] = createList();

    ifstream in("input.txt");

    while (in.peek() != EOF)
    {
        if (!isNextCharBad(in))
        {
            String *temp = getWord(in);
            if (isIncludedInHA(temp, hashArray))
                increaseValueInHA(temp, hashArray);
            else
                includeStringToHA(temp, hashArray);
            deleteString(temp);
        }
        else
            in.get();
    }

    cout << "1 - printHA\n2 - printAmountWords\n3 - Max size list's id\n4 - Amount empty lists\n5 - Amount different words\n6 - print loadFactor\n7 - print HA list by id\nq - quit\n";

    char toDo = 0;
    while (toDo != 'q')
    {
        cout << "\nEnter toDo: ";
        cin >> toDo;
        switch (toDo) {
        case '1':
            printHashArray(hashArray);
            break;
        case '2':
            cout << "Amount words: " << getAmountWords(hashArray) << endl;
            break;
        case '3':
            cout << "Max size list's id: " << getMaxSizeInHA(hashArray) << endl;
            break;
        case '4':
            cout << "Amount empty lists: " << getAmoutnEmptyListsInHA(hashArray) << endl;
            break;
        case '5':
            cout << "Amount different words: " << getAmountElements(hashArray) << endl;
            break;
        case '6':
            cout << "LoadFactor: " << getLoadFactor(hashArray) << endl;
            break;
        case '7':
            int id;
            cout << "Enter id: ";
            cin >> id;
            printListInHA(hashArray, id);
            break;

        default:
            break;
        }
        cout << "=========================";
    }
    in.close();

    for (int i = 0; i < arraySize; i++)
        deleteList(hashArray[i]);
    return 0;
}
