package Day_6;

class Animal{
    void eat(){
        System.out.println("This Animal eat food");
    }
}

class Dog extends Animal{
    void bark(){
        System.out.println("The dog barks");
    }
}

public class Inheritance {
    public static void main(String args[]){
        Dog mydog = new Dog();
        mydog.eat();
        mydog.bark();
    } 
    
}


// class Device {
// }

// // Child class - 1
// class dabbaPhone extends Device {
//     void makeCall() {
//         System.out.println("Calling the number......");
//     }
// }

// // Child class - 2
// class SmartPhone extends dabbaPhone {
//     void browseInternet() {
//         System.out.println("Opening Browser....");
//     }
// }

// public class Inheritance {
//     public static void main(String[] args) {
//         SmartPhone samsung = new SmartPhone();

//         samsung.browseInternet();
//         samsung.makeCall();
//         samsung.poweron();
//     }
// }

// Hierarchical Inheritance

// Common Parent
// class Shape {
//     String color = "Blue";
// }

// // Child - 1
// class Circle extends Shape {
//     void drawCircle() {
//         System.out.println("Drawing a " + color + " circle.");
//     }
// }

// // Child - 2
// class Rectangle extends Shape {
//     void drawRectangle() {
//         System.out.println("Drawing a " + color + " rectangle.");
//     }
// }

// public class Inheritance {
//     public static void main(String[] args) {
//         Circle c = new Circle();
//         Rectangle r = new Rectangle();

//         c.drawCircle();
//         r.drawRectangle();
//     }
// }