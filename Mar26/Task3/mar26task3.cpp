#include <iostream>
#include <vector>
#include <string>

class File
{
private:
    std::string name;

public:
    File(std::string name) : name(name) {}

    void display() const
    {
        std::cout << "File: " << name << std::endl;
    }

    std::string getName() const
    {
        return name;
    }
};

class Directory
{
private:
    std::string name;
    std::vector<File *> files; // List of files
    std::vector<Directory *> directories; // List of subdirectories

public:
    Directory(std::string name) : name(name) {}

    void addComponent(File *file)
    {
        files.push_back(file);
    }

    void addComponent(Directory *directory)
    {
        directories.push_back(directory);
    }

    void display() const
    {
        std::cout << "Directory: " << name << std::endl;
        for (const auto &file : files)
        {
            file->display();
        }
        for (const auto &directory : directories)
        {
            directory->display();
        }
    }

    std::string getName() const
    {
        return name;
    }
};

int main()
{
    Directory root("Root");
    File *file1 = new File("File1.txt");
    File *file2 = new File("File2.txt");
    Directory *dir1 = new Directory("Dir1");
    File *file3 = new File("File3.txt");
    File *file4 = new File("File4.txt");
    Directory *dir2 = new Directory("Dir2");

    root.addComponent(file1);
    root.addComponent(file2);
    root.addComponent(dir1);
    dir1->addComponent(file3);
    dir1->addComponent(file4);
    dir1->addComponent(dir2);

    root.display();

    // Clean up memory
    delete file1;
    delete file2;
    delete dir1;
    delete file3;
    delete file4;
    delete dir2;

    return 0;
}