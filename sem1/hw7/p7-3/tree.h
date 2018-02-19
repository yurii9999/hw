#pragma once

struct Tree;

Tree* createTree();
void readTree(Tree *tree, char *fileName);
void print(Tree *tree);
void deleteTree(Tree *tree);
int solveTree(Tree *tree);
