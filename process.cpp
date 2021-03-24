#include"process.h"

Process::Process(Bot bot, Maze maze) :
bot(bot), maze(maze) 
{
    running = false;
    end = false;
}

Process::~Process() {

}

void Process::start(int algoName) {
    switch (algoName)
    {
        case Bot::DFS: 
            bot.findWayByDFS();
            break;
        case Bot::BFS:
            bot.findWayByBFS();
            break;
        case Bot::IDS:
            bot.findWayByIDS();
            break;
        default:
    };
}

void Process::stop() {
    running = false;
}

void Process::resume() {
    running = true;
}

void Process::reset() {
    running = true;
    end = false;
}


bool Process::getGoal() {
    return bot.getCoordinate() == maze.getEndPoint();
}