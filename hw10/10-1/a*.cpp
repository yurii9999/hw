#include <iostream>
#include <fstream>
#include "a*.h"

using namespace ::std;

int const infinity = 500;
int const wall = 1000;

struct Map
{
    int height;
    int width;
    int **map;
};

int getPrice(Coords a, Map *map)
{
    return map->map[a.x][a.y];
}

Coords cutMin(Map *map, Coords *array, int &size)
{
    int minPrice = getPrice(array[0], map);
    int number = 0;

    for (int i = 1; i < size; i++)
    {
        int currentPrice = getPrice(array[i], map);
        if (currentPrice < minPrice)
        {
            minPrice = currentPrice;
            number = i;
        }
    }

    Coords result = array[number];
    array[number] = array[size - 1];
    size--;

    return result;
}

void deleteMap(Map *&map)
{
    int width = map->width;

    for (int i = 0; i < width; i++)
        delete[] map->map[i];

    delete[] map->map;
    delete map;
}

void printMapDebug(Map *map)
{
    int height = map->height;
    int width = map->width;

    for (int i = 0; i < height; i++)
    {
        for (int j = 0; j < width; j++)
            cout << map->map[j][i] << "\t";
        cout << "\n";
    }
}

Map *readMap(char *fileName)
{
    ifstream in(fileName);
    int width = 0;
    int height = 0;
    in >> width >> height;

    int **map = new int*[width];
    for (int i = 0; i < width; i++)
        map[i] = new int[height];

    for (int i = 0; i < height; i++)
        for (int j = 0; j < width; j++)
            in >> map[j][i];

    in.close();

    Map *newMap = new Map;
    newMap->height = height;
    newMap->width = width;
    newMap->map = map;

    return newMap;
}

int h(Coords a, Coords b)
{
    return abs(a.x - b.x) + abs(a.y - b.y);
}

bool isIncluded(Coords *buffer, int current, Coords a)
{
    for (int i = 0; i < current; i++)
        if (buffer[i].x == a.x && buffer[i].y == a.y)
            return true;

    return false;
}

bool canWalk(Coords a, Map *map)
{
    return map->map[a.x][a.y] != wall;
}

void updateCoords(Coords here, Coords from, Map *map, Coords *newPoints, int &pointreNewPoints, Coords goal)
{
    int x = from.x;
    int y = from.y;

    int x1 = here.x;
    int y1 = here.y;

    if (canWalk(here, map))
    {
        int newPrice = map->map[x][y] + h(here, goal) + 1;
        if (newPrice < map->map[x1][y1])
        {
            map->map[x1][y1] = newPrice;
            newPoints[pointreNewPoints] = here;
            pointreNewPoints++;
        }
    }
}

void updateMap(Map *map, Coords here, Coords goal, Coords *newPoints, int &pointerNewPoints)
{
    int price = getPrice(here, map);
    if (price == wall)
        return;

    int x = here.x;
    int y = here.y;

    updateCoords({x - 1, y}, here, map, newPoints, pointerNewPoints, goal);
    updateCoords({x + 1, y}, here, map, newPoints, pointerNewPoints, goal);
    updateCoords({x, y - 1}, here, map, newPoints, pointerNewPoints, goal);
    updateCoords({x, y + 1}, here, map, newPoints, pointerNewPoints, goal);
}

Coords getMinNear(Coords here, Map *map)
{
    int x = here.x;
    int y = here.y;

    int minPrice = getPrice({x - 1, y}, map);
    Coords result = {x - 1, y};

    int currentPrice = getPrice({x + 1, y}, map);
    if (currentPrice < minPrice)
    {
        result = {x + 1, y};
        minPrice = currentPrice;
    }

    currentPrice = getPrice({x, y - 1}, map);
    if (currentPrice < minPrice)
    {
        result = {x, y - 1};
        minPrice = currentPrice;
    }


    currentPrice = getPrice({x, y + 1}, map);
    if (currentPrice < minPrice)
    {
        result = {x, y + 1};
        minPrice = currentPrice;
    }

    return result;
}

Coords *getWay(Map *map, Coords start, Coords goal)
{
    int height = map->height + 2;
    int width = map->width + 2;
    Map *newMap = new Map;
    newMap->map = new int*[width];
    for (int i = 0; i < width; i++)
        newMap->map[i] = new int[height];

    for (int i = 0; i < width - 2; i++)
        for (int j = 0; j < height - 2; j++)
        {
            if (map->map[i][j] == 1)
                newMap->map[i + 1][j + 1] = wall;
            else
                newMap->map[i + 1][j + 1] = infinity;
        }

    for (int i = 0; i < width; i++)
    {
        newMap->map[i][0] = wall;
        newMap->map[i][height - 1] = wall;
    }
    for (int i = 0; i < height; i++)
    {
        newMap->map[0][i] = wall;
        newMap->map[width - 1][i] = wall;
    }

    start = {start.x + 1, start.y + 1};
    goal = {goal.x + 1, goal.y + 1};
    newMap->map[start.x][start.y] = h(start, goal);
    newMap->height = height;
    newMap->width = width;

//    wall = 1000
//    infinity = 500
//    another = way from start + h
    cout << "\n======================================\n";
    cout << "\nMap:\n";
    printMapDebug(newMap);

    Coords *opened = new Coords[width * height];
    opened[0] = start;
    int sizeOpened = 1;

    bool foundGoal = false;
    int j = 0;
    while (!foundGoal)
    {
        cout << "\nj = " << j;
        j++;
        Coords *newPoints = new Coords[4];
        int sizeNewPoints = 0;
        Coords current = cutMin(newMap, opened, sizeOpened);
        cout << "\nCurrent: x: " << current.x << "\ty: " << current.y;
        updateMap(newMap, current, goal, newPoints, sizeNewPoints);
        for (int i = 0; i < sizeNewPoints; i++)
            opened[sizeOpened + i] = newPoints[i];

        cout << "\nNewPoints: ";
        for (int i = 0; i < sizeNewPoints; i++)
            cout << "\nx: " << newPoints[i].x << "\ty: " << newPoints[i].y;

        sizeOpened = sizeOpened + sizeNewPoints;
        foundGoal = isIncluded(newPoints, sizeNewPoints, goal);
        cout << "\nOpened:";
        for (int i = 0; i < sizeOpened; i++)
            cout << "\nx: " << opened[i].x << "\ty: " << opened[i].y;

        cout << "\nMap:\n";
        printMapDebug(newMap);
        delete[] newPoints;
    }
    delete[] opened;
    cout << "\n=========================================================\n";

    Coords *way = new Coords[j];
    way[0] = goal;
    int sizeWay = 1;
    while (way[sizeWay - 1].x != start.x || way[sizeWay - 1].y != start.y)
    {
        way[sizeWay] = getMinNear(way[sizeWay - 1], newMap);
        sizeWay++;
    }

    Coords *result = new Coords[sizeWay];
    for (int i = 0; i < sizeWay; i++)
    {
        Coords current = way[sizeWay - i - 1];
        result[i] = {current.x - 1, current.y - 1};
    }

    delete[] way;
    deleteMap(newMap);

    return result;
}

void printMapWithWay(Map *map, Coords *way, int length)
{
    int height = map->height;
    int width = map->width;

    for (int i = 0; i < height; i++)
    {
        for (int j = 0; j < width; j++)
            if (isIncluded(way, length, {j, i}))
                cout << "* ";
            else
                if (map->map[j][i] == 1)
                    cout << "1 ";
                else
                    cout << "0 ";
        cout << "\n";
    }
}
