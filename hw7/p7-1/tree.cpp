#include <iostream>
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

Node *createNode(int value)
{
    Node *newNode = new Node;
    newNode->value = value;
    newNode->left = nullptr;
    newNode->right = nullptr;
    return newNode;
}

void addNode(Node *&node, int value)
{
    if (node == nullptr)
    {
        node = createNode(value);
        return;
    }

    if (value == node->value)
        return;

    if (value > node->value)
        addNode(node->right, value);

    else
        addNode(node->left, value);
}

void add(Tree *tree, int value)
{
    addNode(tree->root, value);
}

bool containsNode(Node* node, int value)
{
    if (node == nullptr)
        return false;

    if (node->value == value)
        return true;

    if (value > node->value)
        return containsNode(node->right, value);
    else
        return containsNode(node->left, value);
}

bool contains(Tree* tree, int value)
{
    return containsNode(tree->root, value);
}

int getMax(Node* node)
{
    if (node->right == nullptr)
        return node->value;
    else
        return getMax(node->right);
}

void removeNode(Node*& node, int value)
{
    if (node == nullptr)
        return;

    if (value < node->value)
        return removeNode(node->left, value);
    if (value > node->value)
        return removeNode(node->right, value);

    if (value == node->value)
    {
        if (node->left == nullptr && node->right == nullptr)
        {
            delete node;
            node = nullptr;
            return;
        }

        if (node->left == nullptr)
        {
            Node* temp = node;
            node = node->right;
            delete temp;
            return;
        }
        if (node->right == nullptr)
        {
            Node* temp = node;
            node = node->left;
            delete temp;
            return;
        }
        else
        {
            int maxLeftValue = getMax(node->left);
            removeNode(node->left, maxLeftValue);
            node->value = maxLeftValue;
            return;
        }
    }
}

void remove(Tree* tree, int value)
{
    removeNode(tree->root, value);
}

void printNode(Node* node)
{
    if (node == nullptr)
    {
        cout << "null";
        return;
    }

    cout << "(" << node->value << ", ";
    printNode(node->left);
    cout << " ";
    printNode(node->right);
    cout << ")";
}

void print(Tree* tree)
{
    printNode(tree->root);
}

void print1Node(Node *node)
{
    if (node == nullptr)
        return;

    print1Node(node->left);
    cout << node->value << " ";
    print1Node(node->right);
}

void printLowestFirst(Tree *tree)
{
    print1Node(tree->root);
}

void print2Node(Node *node)
{
    if (node == nullptr)
        return;

    print2Node(node->right);
    cout << node->value << " ";
    print2Node(node->left);
}

void printBiggestFirst(Tree *tree)
{
    print2Node(tree->root);
}

void deleteNode(Node *&node)
 {
     if (node == nullptr)
         return;
     deleteNode(node->left);
     deleteNode(node->right);

     delete node;
 }

void deleteTree(Tree *&tree)
{
    deleteNode(tree->root);
    delete tree;
}
