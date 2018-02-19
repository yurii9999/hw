#pragma once

struct Map;
struct Coords
{
    int x;
    int y;
};

Map *readMap(char *fileName);
Coords *getWay(Map *map, Coords start, Coords goal);
void printMapWithWay(Map *map,Coords *way, int length);
void deleteMap(Map *&map);
