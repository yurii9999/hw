#pragma once

#include "stringg.h"
#include "list.h"

int const arraySize = 13;

int getHash(String *string);
bool isIncludedInHA(String *string, List *hashArray[arraySize]);
void includeStringToHA(String *string, List *hashArray[arraySize]);
void increaseValueInHA(String *string, List *hashArray[arraySize]);
void printHashArray(List *hashArray[arraySize]);
int getAmountWords(List *hashArray[arraySize]);
int getMaxSizeInHA(List *hashArray[arraySize]);
void printListInHA(List *hashArray[arraySize], int id);
int getAmoutnEmptyListsInHA(List *hashArray[arraySize]);
int getAmountElements(List *hashArray[arraySize]);
double getLoadFactor(List *hashArray[arraySize]);
