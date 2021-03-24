#pragma once

#include<queue>
#include<stack>
#include<utility>
#include"maze.h"

typedef std::pair<int, int> ii;

class Bot {
public:
    static short const DFS = 1;
    static short const BFS = 2;
    static short const IDS = 3;
    static short const TRACK = -1;

private:
    int x, y; //coordinate
    std::deque<ii> deque;
    short mazeMem[100][100];

    // direct
    const short verticalMove[4] = {0, 0, -1, 1};
    const short horizontalMove[4] = {-1, 1, 0, 0};
    
public:
    Bot(int x, int y);
    ~Bot();

    const void moveTo(int x, int y);

public:
    const void findWayByDFS();
    const void findWayByBFS();
    const void findWayByIDS();
    const inline ii getCoordinate() const {return ii(this->x, this->y);};
    const inline void setFirstCoordinate (int x, int y) { this-> x = x; this->y = y; };
    const void recognizeWall(int x, int y);
    const void recognizeWay(int x, int y); 
    const void see(int x, int y, short kindOfObject);
    const inline void tick() {this->mazeMem[x][y] = Bot::TRACK; };
};