#include <iostream>

class Enemy
{
    std::string name;
    int health;

public:

    Enemy(std::string name, int health): name(std::move(name)), health(health) {}
    void takeDamage(int damage) { this->health = std::max(0, this->health - damage); }
    int getHealth() { return this->health; }
    std::string getName() { return this->name; }
};

class MageAttack
{
public:
    int damageOnAttack = 10;
};

class RogueAttack
{
public:
    int damageOnAttack = 15;
};

class WarriorAttack
{
public:
    int damageOnAttack = 25;
};

class Character
{
public:
    static void mageAttack(Enemy enemy)
    {
        std::cout << "Mage attack!" << std::endl;
        enemy.takeDamage(MageAttack().damageOnAttack);
        std::cout << enemy.getName() << " STRENGTH LEVEL IS: " << enemy.getHealth() << std::endl;
    }
    static void rogueAttack(Enemy enemy)
    {
        std::cout << "Rogue attack!" << std::endl;
        enemy.takeDamage(RogueAttack().damageOnAttack);
        std::cout << enemy.getName() << " STRENGTH LEVEL IS: " << enemy.getHealth() << std::endl;
    }
    static void warriorAttack(Enemy enemy)
    {
        std::cout << "Warrior attack!" << std::endl;
        enemy.takeDamage(WarriorAttack().damageOnAttack);
        std::cout << enemy.getName() << " STRENGTH LEVEL IS: " << enemy.getHealth() << std::endl;
    }
};

int main()
{

    Enemy blackKiller("BLACK KILLER", 100);
    Enemy brownBiter("BROWN BITER", 0);

    Character::warriorAttack(blackKiller);
    Character::mageAttack(brownBiter);
    Character::rogueAttack(blackKiller);

    return 0;
}
