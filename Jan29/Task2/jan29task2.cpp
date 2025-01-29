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
};