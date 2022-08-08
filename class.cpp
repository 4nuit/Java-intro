#include <iostream>

class A
{
public:
    virtual void f0()
    {
        std::cout << "f0 from A\n";
    };
};


class B : public A
{
public:
    void f0()
    {
        std::cout << "f0 from B\n";
    };
    void f1()
    {
        std::cout << "f1 from B\n";
    };
};

int main() {
        

    A* ab = new B();
    ab->f0();
    //ab->f1();
    
    delete ab;
    system("pause");
    return 0;
}
