#include <iostream>

int jan22task1()
{
    int time;
    std::cin >> time;
    const int hours = time / 3600;
    time %= 3600;
    const int minutes = time / 60;
    time %= 60;
    const int seconds = time;
    std::cout << hours << ":" << minutes << ":" << seconds << std::endl;

    return 0;
}