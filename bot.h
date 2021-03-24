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
    short verticalMove[4] = {0, 0, -1, 1};
    short horizontalMove[4] = {-1, 1, 0, 0};
    
public:
    Bot(int x, int y);
    ~Bot();

    void moveTo(int x, int y);

public:
    void findWayByDFS();
    void findWayByBFS();
    void findWayByIDS();
    inline ii getCoordinate() {return ii(this->x, this->y);};
    inline void setFirstCoordinate(int x, int y) { this-> x = x; this->y = y; };
    void recognizeWall(int x, int y);
    void recognizeWay(int x, int y); 
    void see(int x, int y, short kindOfObject);
    inline void tick() {this->mazeMem[x][y] = Bot::TRACK; };
};