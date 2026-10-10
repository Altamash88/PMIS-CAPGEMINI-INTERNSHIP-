package Day_5;

// A constructor is a special method in Java that is used to initialize an object.
public class Student {

    String name;
    int age;

    // Constructor
    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public static void main(String[] args) {

        Student student = new Student("BOB", 19);

        System.out.println("Name: " + student.name);
        System.out.println("Age: " + student.age);
    }
}