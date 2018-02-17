#include <iostream>
#include <fstream>

using namespace std;

int const alphabet = 26;

struct LetterAndAmount
{
    char letter = 0;
    int amount = 0;
};

int main()
{
    ifstream in("input.txt");
    if (!in)
    {
        cout << "cant open";
        return 0;
    }

    LetterAndAmount text[alphabet];
    for (int i = 0; i < alphabet; i++)
    {
        text[i].letter = (char) i + 'a';
        text[i].amount = 0;
    }

    char currentChar = 0;

    while (in.peek() != EOF)
    {
        in.get(currentChar);

        if (currentChar >= 'a' && currentChar <= 'z')
            text[currentChar - 'a'].amount++;
    }

    ofstream out("output.txt");
    for (int i = 0; i < alphabet; i++)
        if(text[i].amount != 0)
            out << (char) text[i].letter << "--" << text[i].amount << endl;
    out.close();

    in.close();
    return 0;
}
