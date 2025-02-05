#include <iostream>
#include <vector>

class Animal {
    protected:
        std::string name;
        int age;
    public:
        Animal(std::string name, const int age) {
            this->name = std::move(name);
            this->age = age;
        }
        Animal() {name = "Joe", age = 5;}

        virtual ~Animal() = default;

        virtual void makeSound() = 0;
        virtual void uniqueMethod() = 0;
};

class LandAnimal : virtual public Animal {
public:
    LandAnimal(std::string name, const int age) {
        this->name = std::move(name);
        this->age = age;
    }

    ~LandAnimal() override = default;
    LandAnimal() = default;

    void makeSound() override = 0;
    void uniqueMethod() override = 0;
};

class WaterAnimal : virtual public Animal {
public:
    WaterAnimal(std::string name, const int age) {
        this->name = std::move(name);
        this->age = age;
    }

    ~WaterAnimal() override = default;
    WaterAnimal() = default;

    void makeSound() override = 0;
    void uniqueMethod() override = 0;
};

class Lion final : public LandAnimal {
public:
    Lion(std::string name, const int age) : LandAnimal(std::move(name), age) {}
    ~Lion() override = default;
    Lion() = default;

    void makeSound() override {
        std::cout << "Lion sounds..." << std::endl;
    }
    void uniqueMethod() override {
        std::cout << "Lion is walking..." << std::endl;
    }
};

class Dolphin final : public WaterAnimal {
public:
    Dolphin(std::string name, const int age) : WaterAnimal(std::move(name), age) {}
    ~Dolphin() override = default;
    Dolphin() = default;

    void makeSound() override {
        std::cout << "Dolphin sounds..." << std::endl;
    }
    void uniqueMethod() override {
        std::cout << "Dolphin is swimming..." << std::endl;
    }
};

class Amphibian : public LandAnimal, public WaterAnimal {
public:
    Amphibian(std::string name, const int age) : Animal(std::move(name), age) {}
    ~Amphibian() override = default;
    Amphibian() = default;

    void makeSound() override = 0;
    void uniqueMethod() override = 0;
};

class Frog final : public Amphibian {
public:
    Frog(std::string name, const int age) : Amphibian(std::move(name), age) {}
    ~Frog() override = default;
    Frog() = default;

    void makeSound() override {
        std::cout << "Frog sounds..." << std::endl;
    }
    void uniqueMethod() override {
        std::cout << "Frog is walking..." << std::endl;
        std::cout << "Frog is swimming..." << std::endl;
    }
};


int main() {
    std::vector<Animal*> animals;
    animals.push_back(new Lion("Leo", 1337));
    animals.push_back(new Dolphin("Dolphy", 31337));
    animals.push_back(new Frog("Froggo", 42));

    for (const auto animal : animals) {
        animal->makeSound();
        animal->uniqueMethod();
    }
}