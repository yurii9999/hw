#pragma once

struct Tree;

Tree* createTree();

void add(Tree *tree, int value);
void remove(Tree *tree, int value);
bool contains(Tree *tree, int value);
void deleteTree(Tree *&tree);

void print(Tree *tree);
void printLowestFirst(Tree *tree);
void printBiggestFirst(Tree *tree);
