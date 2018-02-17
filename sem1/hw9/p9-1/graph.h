#pragma once

struct Graph;

Graph *readGraph(char *fileName);
void printGraph(Graph *graph);
int *getMinLengthsFrom(Graph *graph, int vertex);
int getSize(Graph *graph);
void deleteGraph(Graph *&graph);
