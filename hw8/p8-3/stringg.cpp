#include <iostream>
#include "stringg.h"

using namespace ::std;

int const maxInput = 100;

struct String
{
    int length;
    char *chars;
};


String *createString()
{
    String *newString = new String;
    newString->length = 0;
    newString->chars = nullptr;

    return newString;
}

String *clone(String *string)
{
    return getSubstring(string, 0, string->length - 1);
}

bool isEmpty(String *string)
{
    return string->length == 0;
}

int getLength(String *string)
{
    return string->length;
}

String *concatinate(String *string1, String *string2)
{
    String *newString = createString();

    newString->length = string1->length + string2->length;
    newString->chars = new char[newString->length];

    for (int i = 0; i < string1->length; i++)
        newString->chars[i] = string1->chars[i];

    int nowPosition = string1->length - 1;

    for (int i = 0; i < string2->length; i++)
        newString->chars[nowPosition + i] = string2->chars[i];

    return newString;
}

String *getSubstring(String *string, int from, int to)
{
    String *newString = createString();

    newString->length = to - from + 1;
    newString->chars = new char[newString->length];

    for (int i = 0; i < newString->length; i++)
        newString->chars[i] = string->chars[from + i];

    return newString;
}

void deleteString(String *&string)
{
    delete[] string->chars;
    delete string;
}

void inputString(String *string)
{
    char chars[maxInput];
    char currentChar = 0;
    int length = 0;

    cin.get(currentChar);
    while (currentChar != '\n')
    {
        chars[length] = currentChar;
        length++;
        cin.get(currentChar);
    }

    string->length = length;
    string->chars = new char[length];

    for (int i = 0; i < length; i++)
        string->chars[i] = chars[i];
}

bool areEqual(String *string1, String *string2)
{
    if (string1->length != string2->length)
        return false;

    for (int i = 0; i < string1->length; i++)
        if (string1->chars[i] != string2->chars[i])
            return false;

    return true;
}

char *getCharArray(String *string)
{
    return string->chars;
}

void printString(String *string)
{
    for (int i = 0; i < string->length; i++)
        cout << string->chars[i];
}

String *buildString(char *chars, int length)
{
    String *newString = createString();
    newString->length = length;
    newString->chars = new char[length];

    for(int i = 0; i < length; i++)
        newString->chars[i] = chars[i];

    return newString;
}
