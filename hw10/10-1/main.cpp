#include <iostream>
#include <fstream>
#include "a*.h"

using namespace std;

int getLength(Coords *way, Coords end)
{
    int i = 0;
    while(end.x != way[i].x || end.y != way[i].y)
        i++;

    return i + 1;
}

int main()
{
    Map *map = readMap("input.txt");
    Coords start = {0, 0};
    Coords goal = {0, 0};
    cout << "Enter start x, y: ";
    cin >> start.x >> start.y;
    cout << "Enter goal x, y: ";
    cin >> goal.x >> goal.y;

    Coords *way = getWay(map, start, goal);
    int length = getLength(way, goal);
    cout << "\nMap:\n";
    printMapWithWay(map, way, length);

    delete[] way;
    deleteMap(map);
    return 0;
}
