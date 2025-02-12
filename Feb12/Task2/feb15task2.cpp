#include <cmath>
#include <iostream>
#include <ostream>

class Shape {
public:
    virtual double area() = 0;
    virtual double perimeter() = 0;

    Shape() {}
    virtual ~Shape() {}
};

class Rectangle final : public Shape {
    double width;
    double height;

public:
    Rectangle() : Shape(), width(0), height(0) {}
    explicit Rectangle(const double width, const double height) : Shape(), width(width), height(height) {}
    ~Rectangle() {};

    double area() override { return this->width * this->height; }
    double perimeter() override { return 2 * (this->width + this->height); }
};

class Circle final : public Shape {
    double radius;
public:
    Circle() : Shape(), radius(0) {}
    explicit Circle(const double radius) : Shape(), radius(radius) {}
    ~Circle() {}

    double area() override { return this->radius * this->radius * M_PI; }
    double perimeter() override { return 2 * M_PI * this->radius; }
};

int main() {
    Rectangle rectangle(5.0, 3.0);
    Circle circle(4.0);

    Shape *recshape = &rectangle;
    Shape *circleshape = &circle;

    const Rectangle *constrecshape = &rectangle;
    const Circle *constcircleshape = &circle;

    // Static casting
    auto staticRectangle = static_cast<Rectangle*>(recshape);
    auto staticCircle = static_cast<Circle*>(circleshape);

    std::cout << "STATIC CASTING" << std::endl;
    std::cout << staticRectangle->area() << std::endl;
    std::cout << staticCircle->area() << std::endl;
    std::cout << staticRectangle->perimeter() << std::endl;
    std::cout << staticCircle->perimeter() << std::endl;

    // Dynamic casting
    auto dynamicRectangle = dynamic_cast<Rectangle*>(recshape);
    auto dynamicCircle = dynamic_cast<Circle*>(circleshape);

    std::cout << "DYNAMIC CASTING" << std::endl;
    std::cout << dynamicRectangle->area() << std::endl;
    std::cout << dynamicCircle->area() << std::endl;
    std::cout << dynamicRectangle->perimeter() << std::endl;
    std::cout << dynamicCircle->perimeter() << std::endl;

    // Const casting
    auto constRectangle = const_cast<Rectangle*>(constrecshape);
    auto constCircle = const_cast<Circle*>(constcircleshape);

    std::cout << "CONST CASTING" << std::endl;
    std::cout << constRectangle->area() << std::endl;
    std::cout << constCircle->area() << std::endl;
    std::cout << constRectangle->perimeter() << std::endl;
    std::cout << constCircle->perimeter() << std::endl;

    int intValue = 42;

    // Reinterpret casting
    unsigned int *newIntValue = reinterpret_cast<unsigned int *>(&intValue);

    std::cout << "REINTERPRET CASTING" << std::endl;
    std::cout << *newIntValue << std::endl;

    return 0;
}