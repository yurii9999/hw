#include <iostream>
#include "tree.h"

using namespace std;

int main()
{
    Tree *tree = createTree();

    cout << "\nEnter 0 to quit";
    cout << "\nEnter 1 to add element";
    cout << "\nEnter 2 to remove element";
    cout << "\nEnter 3 to find element";
    cout << "\nEnter 4 to print tree";
    cout << "\nEnter 5 to print tree lowest..biggest";
    cout << "\nEnter 6 to print tree biggest..lowest\n";

    int c = 0;

    do
    {
        cout << "Enter c: ";
        cin >> c;

        int element = 0;
        switch (c)
        {
        case 1:
            cout << "Enter element: ";
            cin >> element;
            add(tree, element);
            break;
        case 2:
            cout << "Enter element: ";
            cin >> element;
            remove(tree, element);
            break;
        case 3:
            cout << "Enter element: ";
            cin >> element;
            if (contains(tree, element))
                cout << "it included";
            else
                cout << "it is not included";
            break;
        case 4:
            print(tree);
            cout << "\n";
            break;
        case 5:
            printLowestFirst(tree);
            cout << "\n";
            break;
        case 6:
            printBiggestFirst(tree);
            cout << "\n";
            break;
        default:
            break;
        }
    }
    while (c != 0);

    deleteTree(tree);
    return 0;
}
