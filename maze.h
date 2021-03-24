#pragma once

#include<utility>

typedef std::pair<int, int> ii;

class Maze {
public:
    static const short WALL = 2;
    static const short WAY = 3;
    static const short GOAL = 4;

private:
    int start_x, start_y, end_x, end_y; // coordinate of start or end point
    int rows, columns;

public:
    Maze(int row, int column, int start_x, int start_y, int end_x, int end_y);
    ~Maze();

public:
    inline void setRow(int row) { this->rows = row; };
    inline void setColumn(int column) { this->columns = column; };
    inline int getRow() {return this->rows; };
    inline int getColumn() {return this->columns; };
    inline ii getStartPoint() { return ii(this->start_x, this->start_y); };
    inline ii getEndPoint() { return ii(this->end_x, this->end_y); };
};