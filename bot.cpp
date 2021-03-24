#include"bot.h"

Bot::Bot(int x, int y) :
x(x), y(y) 
{
    for(int i = 0; i < 100; i++)
        for(int j = 0; j < 100; j++) mazeMem[i][j] = 0;
}

Bot::~Bot() {

}

void Bot::findWayByDFS() {
    deque.push_back(ii(this->x, this->y));
    int i;

    while(!deque.empty()) {
        this->tick();
        this->x = deque.back().first;
        this->y = deque.back().second; 

        for(i = 0; i < 4; i++) {
            int next_x = x + verticalMove[i], next_y = y + horizontalMove[i]; 
            if(mazeMem[next_x][next_y] == Maze::WAY) {
                deque.push_back(ii(next_x, next_y));
                break;
            } else if(mazeMem[next_x][next_y] == Maze::GOAL) {
                this->x = next_x; this->y = next_y;
                return;
            }
        }

        if(i == 4) deque.pop_back(); 
    }
}

void Bot::findWayByBFS() {

}

void Bot::findWayByIDS() {

}

void Bot::recognizeWall(int x, int y) {
    this->mazeMem[x][y] = Maze::WALL;
}

void Bot::recognizeWay(int x, int y) {
    this->mazeMem[x][y] = Maze::WAY;
}

void Bot::moveTo(int x, int y) {
    if(this->mazeMem[x][y] != Maze::WALL) {
        this->x = x;
        this->y = y;
    }
}

void Bot::see(int x, int y, short kindOfObject) {
    this->mazeMem[x][y] = kindOfObject;
}
