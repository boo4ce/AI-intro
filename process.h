#include"bot.h"

class Process {
private:
    Bot bot;
    Maze maze;
    bool running;
    bool end;
public:
    Process(Bot bot, Maze maze);
    ~Process();

public:
    void start(int algoName);
    void stop();
    void resume();
    void reset();
    bool getGoal();
};