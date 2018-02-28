#include <iostream>
#include <fstream>

int const lengthName = 30 + 1;
int const lengthNumber = 15 + 1;

int const maxSize = 80;

struct NameAndNumber
{
    char name[lengthName];
    char number[lengthNumber];
};

using namespace std;

int getLength(char *string)
{
    int result = 0;

    while(string[result] != '\n')
        result++;

    return result;
}

void inputSring(char string[])
{
    int i = 0;

    cin.get(string[0]);
    while (string[i] != '\n')
    {
        i++;
        cin.get(string[i]);
    }
}

void printString(char *string)
{
    int i = 0;

    while(string[i] != '\n')
    {
        cout << string[i];
        i++;
    }
}

void add(int &size, NameAndNumber book[])
{
    cout << "Enter name: ";
    inputSring(book[size].name);
    cout << "Enter number: ";
    inputSring(book[size].number);
    size++;
}

void printBook(int size, NameAndNumber book[])
{
    for (int i = 0; i < size; i++)
    {
        cout << "Name: ";
        printString(book[i].name);
        cout << endl << "Number: ";
        printString(book[i].number);
        cout << endl;
    }
}

bool isEqual(char string1[], char string2[])
{
    if (getLength(string1) != getLength(string2))
        return false;

    for (int i = 0; i < getLength(string1); i++)
    {
        if (string1[i] != string2[i])
            return false;
    }
    return true;
}

void printName(int size, NameAndNumber book[])
{
    char number[lengthNumber];
    cout << "Enter number: ";
    inputSring(number);

    for (int i = 0; i < size; i++)
    {
        if (isEqual(number, book[i].number))
        {
            cout << "Name: ";
            printString(book[i].name);
            return;
        }
    }
}

void printNumber(int size, NameAndNumber book[])
{
    char name[lengthName];
    cout << "Enter name: ";
    inputSring(name);

    for (int i = 0; i < size; i++)
    {
        if (isEqual(name, book[i].name))
        {
            cout << "Number: ";
            printString(book[i].number);
            return;
        }
    }
}

void updateBase(int size, NameAndNumber book[])
{
    ofstream out("base.txt");
    out << size << '\n';

    for (int i = 0; i < size; i++)
    {
        for (int j = 0; j < getLength(book[i].name); j++)
            out << book[i].name[j];
        out << '\n';

        for (int j = 0; j < getLength(book[i].number); j++)
            out << book[i].number[j];
        out << '\n';

    }
    out.close();
}

void loadBase(int &size, NameAndNumber book[])
{
    ifstream in("base.txt");
    in >> size;

    for (int i = 0; i < size; i++)
    {
        in.get();
        int j = 0;
        while (in.peek() != '\n')
        {
            in.get(book[i].name[j]);
            j++;
        }
        book[i].name[j] = '\n';

        in.get();
        j = 0;
        while (in.peek() != '\n')
        {
            in.get(book[i].number[j]);
            j++;
        }
        book[i].number[j] = '\n';
    }

    in.close();
}

int main()
{
    int size = 0;
    NameAndNumber book[maxSize];

    loadBase(size, book);

    cout << "File: base.txt \n";
    cout << "0 - выйти" << endl << "1 - добавить запись (имя и телефон)" << endl << "2 - найти телефон по имени" << endl << "3 - найти имя по телефону" << endl << "4 - сохранить текущие данные в файл.";
    char c = 0;
    while (c != '0')
    {
        cout << endl << "Enter c: ";
        cin >> c;

        switch (c) {
        case '0':
            break;
        case '1':
            cin.get();
            add(size, book);
            break;
        case '2':
            cin.get();
            printNumber(size, book);
            break;
        case '3':
            cin.get();
            printName(size, book);
            break;
        case '4':
            updateBase(size, book);
            break;
        case '5':
            printBook(size, book);
            break;
        default:
            cout << "incorrect c";
            break;
        }

    }

    return 0;
}
