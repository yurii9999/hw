#pragma once

struct String;

String *createString();
String *clone(String *string);
void deleteString(String *&string);

String *getSubstring(String *string, int from, int to);
void inputString(String *string);
void printString(String *string);
int getLength(String *string);
bool isEmpty(String *string);
bool areEqual(String *string1, String *string2);
char *getCharArray(String *string);


