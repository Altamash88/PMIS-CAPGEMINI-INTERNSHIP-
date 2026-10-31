package Oops;
//Compile Time Polymorphism
class Calculator {
    int add(int a, int b) {
        return a + b;
    }

    // Same name, three parameters
    int add(int a, int b, int c) {
        return a + b + c;
    }

    // Same name, different types
    double add(double a, double b) {
        return a + b;
    }
}

// Run Time polymorphism.

class Animal {
    void makeSound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Dog barks: Woof woof!");
    }
}

class Cat extends Animal {
    @Override
    void makeSound() {
        System.out.println("Cat meows: Meow meow!");
    }
}

public class Main {
    public static void main(String[] args) {
        // Parent reference pointing to child objects
        Animal pet1 = new Dog();
        Animal pet2 = new Cat();

        pet1.makeSound(); // Prints: Dog barks: Woof woof!
        pet2.makeSound(); // Prints: Cat meows: Meow meow!
    }
}