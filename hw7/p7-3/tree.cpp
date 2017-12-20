#include <iostream>
#include <fstream>
#include "tree.h"

using namespace std;

struct Node
{
    int value;
    Node* left;
    Node* right;
};

struct Tree
{
    Node* root;
};

Tree *createTree()
{
    Tree* newTree = new Tree;
    newTree->root = nullptr;
    return newTree;
}

Node *createNode()
{
    Node *newNode = new Node;
    newNode->left = nullptr;
    newNode->right = nullptr;
    newNode->value = 0;

    return newNode;
}

bool isValue(Node* node)
{
    return ((node->left == nullptr) && (node->right == nullptr));
}

void printNode(Node* node)
{
    if (node == nullptr)
    {
        cout << "null";
        return;
    }
    if (isValue(node))
        cout << "(" << node->value << ", ";
    else
        cout << "(" << (char) node->value << ", ";
    printNode(node->left);
    cout << " ";
    printNode(node->right);
    cout << ")";
}

void print(Tree* tree)
{
    printNode(tree->root);
}

void deleteNode(Node *node)
{
    if (node == nullptr)
        return;
    deleteNode(node->left);
    deleteNode(node->right);
    delete node;
}
int solveNode(Node* node)
{
    if (isValue(node))
        return node->value;

    switch (node->value)
    {
    case (int) '*':
        return (solveNode(node->left) * solveNode(node->right));
        break;
    case (int) '/':
        return (solveNode(node->left) / solveNode(node->right));
        break;
    case (int) '+':
        return (solveNode(node->left) + solveNode(node->right));
        break;
    case (int) '-':
        return (solveNode(node->left) - solveNode(node->right));
        break;
    }

    return -1;
}

int solveTree(Tree *tree)
{
    return solveNode(tree->root);
}

void deleteTree(Tree *tree)
{
    deleteNode(tree->root);
    delete tree;
}

void readNode(Node *&node, istream &in)
{
    in.get(); // (

    node = createNode();
    char c = 0;
    in.get(c);
    node->value = (int) c;

    while (in.peek() == ' ') // vse probels
        in.get();

    if (in.peek() == '(')
        readNode(node->left, in);

    else
    {
        node->left = createNode();
        in >> node->left->value;
    }
    while (in.peek() == ' ') // vse probels
        in.get();

    if (in.peek() == '(')
        readNode(node->right, in);
    else
    {
        node->right = createNode();
        in >> node->right->value;
    }
    in.get(); // )
}

void readTree(Tree *tree, char *fileName)
{
    ifstream in(fileName);
    readNode(tree->root, in);
    in.close();
}
