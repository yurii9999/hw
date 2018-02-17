#pragma once

struct List;
struct ListElement;

List *createList();
void deleteList(List *list);

void addElementTo(List *list, char value, int position); // works only with position == 0
void deleteElementFrom(List *list, int position); // works only with position == 0

char peekListElement(List *list, int position); // works only with position == 0
int sizeList(List *list);
bool isEmpty(List *list);
void printList(List *list);
