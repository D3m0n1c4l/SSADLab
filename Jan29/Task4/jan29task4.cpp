class Box {
public:
    unsigned int length, width, height;

    // Default constructor
    Box() {this->length = 0; this->width = 0; this->height = 0;}

    // Conversion constructor
    Box(const unsigned int length, const unsigned int width, const unsigned int height) {
        this->length = length;
        this->width = width;
        this->height = height;
    }

    // Copy constructor
    Box(const Box& box) {
        this->length = box.length;
        this->width = box.width;
        this->height = box.height;
    }

    // Assignment operator
    Box& operator=(const Box& box) {
        this->length = box.length;
        this->width = box.width;
        this->height = box.height;
        return *this;
    }

    // Member functions
    unsigned int getVolume() const {return this->height * this->length * this->width;}
    void scale(const unsigned int scaleValue) {
        this->length *= scaleValue;
        this->width *= scaleValue;
        this->height *= scaleValue;
    }
    bool isBigger(const Box& box) const {
        if (this->length > box.length && this->width > box.width && this->height > box.height) {
            return true;
        }
        return false;
    }
    bool isSmaller(const Box& box) const {
        if (this->length < box.length && this->width < box.width && this->height < box.height) {
            return true;
        }
        return false;
    }

    // Operators
    Box& operator*(const int factor) {
        this->length *= factor;
        this->width *= factor;
        this->height *= factor;
        return *this;
    }

    bool operator==(const Box& box) const {
        if (!this->isBigger(box) && !this->isSmaller(box)) {
            return true;
        }
        return false;
    }
};

class Cube {
    public:
    unsigned int length;
    unsigned int width;
    unsigned int height;

    Cube() {this->length = 0; this->width = 0; this->height = 0;}
    Cube(const unsigned int length, const unsigned int width, const unsigned int height) {
        this->length = length;
        this->width = width;
        this->height = height;
    }
    Cube(const Cube& box) {
        this->length = box.length;
        this->width = box.width;
        this->height = box.height;
    }

    // Operator
    explicit operator Box() const {
        return Box{this->length, this->width, this->height};
    }
};
