#include <iostream>
#include "tree.h"

using namespace std;

struct Node
{
    int height;
    int value;
    Node* left;
    Node* right;
};

struct Tree
{
    Node* root;
};

int height(Node *node)
{
    if (node)
        return node->height;
    return 0;
}

int balanceFactor(Node *node)
{
    return height(node->right) - height(node->left);
}

void updateHeight(Node *node)
{
    node->height = max(height(node->left), height(node->right)) + 1;
}

Node *rotateRight(Node *node)
{
    Node *temp = node->left;
    node->left = temp->right;
    temp->right = node;
    updateHeight(temp);
    updateHeight(node);

    return temp;
}

Node *rotateLeft(Node *node)
{
    Node *temp = node->right;
    node->right = temp->left;
    temp->left = node;
    updateHeight(temp);
    updateHeight(node);

    return temp;
}

Node *balance(Node *node)
{
    updateHeight(node);
    if (balanceFactor(node) == 2)
    {
        if (balanceFactor(node->right) < 0)
            node->right = rotateRight(node->right);

        return rotateLeft(node);
    }
    if (balanceFactor(node) == -2)
    {
        if (balanceFactor(node->left) > 0)
            node->left = rotateLeft(node->left);

        return rotateRight(node);
    }

    return node;
}

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
    newNode->height = 1;
    newNode->left = nullptr;
    newNode->right = nullptr;
    return newNode;
}

void addNode(Node *&node, int value)
{
    if (node == nullptr)
    {
        node = createNode(value);
        node = balance(node);
        return;
    }

    if (value == node->value)
        return;

    if (value > node->value)
    {
        addNode(node->right, value);
        node = balance(node);
    }

    else
    {
        addNode(node->left, value);
        node = balance(node);
    }
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
    {
        removeNode(node->left, value);
        node = balance(node);
        return;
    }
    if (value > node->value)
    {
        removeNode(node->right, value);
        node = balance(node);
        return;
    }

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
            node = balance(node);
            return;
        }
        if (node->right == nullptr)
        {
            Node* temp = node;
            node = node->left;
            delete temp;
            node = balance(node);
            return;
        }
        else
        {
            int maxLeftValue = getMax(node->left);
            removeNode(node->left, maxLeftValue);
            node->value = maxLeftValue;
            node = balance(node);
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
