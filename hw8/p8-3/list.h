#pragma once

#include "stringg.h"

struct List;

List *createList();

void addElementTo(List *list, String *string, int position);
void deleteElementFrom(List *list, int position);
void deleteList(List *&list);

String *peekListElement(List *list, int position);
int sizeList(List *list);
bool isEmpty(List *list);
void printList(List *list);

bool isHere(List *list, String *string);
void increaseValue(List *list, String *string);

int getSumValues(List *list);
