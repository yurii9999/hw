#pragma once

struct List;
struct ListElement;

List *createList();

void addElementTo(List *list, int value, int position);
void deleteElementFrom(List *list, int position);
void deleteList(List *&list);

int peekListElement(List *list, int position);
int sizeList(List *list);
bool isEmpty(List *list);
void printList(List *list);
