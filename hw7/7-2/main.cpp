#include <iostream>
#include "tree.h"

using namespace std;

int main()
{
    Tree *tree = createTree();
    add(tree, 2);
    add(tree, 3);
    add(tree, 5);
    add(tree, 1);
    add(tree, 9);
    add(tree, 4);
    print(tree);
    remove(tree, 3);
    remove(tree, 2);
    cout << "\n";
    print(tree);

    deleteTree(tree);
    return 0;
}
