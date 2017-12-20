#pragma once

struct Cycle;

Cycle *createCycle();
void deleteCycle(Cycle*&);

int getMain(Cycle*);
void changeMain(Cycle*);
int getCycleSize(Cycle*);
void printCycle(Cycle*);

void addElement(Cycle*, int, int); // works with * * -1 == add to end
void deleteElement(Cycle*, int); // works with * 0 == delete main

