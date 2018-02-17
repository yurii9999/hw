#include <iostream>
#include "tree.h"

using namespace std;

enum toDo{quit = 0, addE = 1, removeE = 2, findE = 3, print1 = 4, print2 = 5, print3 = 6};

toDo valToDo(int c)
{
    if (c == 0)
        return quit;
    if (c == 1)
        return addE;
    if (c == 2)
        return removeE;
    if (c == 3)
        return findE;
    if (c == 4)
        return print1;
    if (c == 5)
        return print2;
    if (c == 6)
        return print3;
}

int main()
{
    Tree* tree = createTree();

    cout << "\nEnter 0 to quit";
    cout << "\nEnter 1 to add element";
    cout << "\nEnter 2 to remove element";
    cout << "\nEnter 3 to find element";
    cout << "\nEnter 4 to print tree";
    cout << "\nEnter 5 to print tree lowest..biggest";
    cout << "\nEnter 6 to print tree biggest..lowest\n";

    toDo nowAction;
    do
    {
        cout << "Enter c: ";
        int c = 0;
        cin >> c;
        nowAction = valToDo(c);

        int element = 0;
        switch (nowAction)
        {
        case addE:
            cout << "Enter element: ";
            cin >> element;
            add(tree, element);
            break;
        case removeE:
            cout << "Enter element: ";
            cin >> element;
            remove(tree, element);
            break;
        case findE:
            cout << "Enter element: ";
            cin >> element;
            if (contains(tree, element))
                cout << "it included";
            else
                cout << "it is not included";
            break;
        case print1:
            print(tree);
            cout << "\n";
            break;
        case print2:
            printLowestFirst(tree);
            cout << "\n";
            break;
        case print3:
            printBiggestFirst(tree);
            cout << "\n";
            break;
        }
    }
    while (nowAction != quit);

    deleteTree(tree);

    return 0;
}
