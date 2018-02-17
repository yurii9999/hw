#include <iostream>
#include <fstream>
#include "tree.h"

using namespace std;

int main()
{
    Tree *tree = createTree();
    readTree(tree, "input.txt");
    print(tree);
    cout << endl << "Result: " << solveTree(tree);

    deleteTree(tree);
    return 0;
}
