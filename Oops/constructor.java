/* 
package Oops;

public class constructor {
     public static void main(String[] args){

        Student1 s1 = new Student1();
        Student1 s2 = new Student1();

        System.out.println(s1.name);
        System.out.println(s2.name);


    }
}

class Student1{
    String name;
    int age;
    int rollNumber;
    String college;

    // Constructor
    Student1(){
        name = "Anish";

     }

    //Default Constructor
    // Student1(){

    // }


} */


// Parameterized constructor
package Oops;

public class constructor {
     public static void main(String[] args){

        Student1 s1 = new Student1("Anish", 23, 108, "IIT Bombay");
        Student1 s2 = new Student1("Olise", 27, 128, "TU Munich");

        System.out.println(s1.name);
        System.out.println(s1.age);
        
        System.out.println(s2.name);


    }
}

class Student1{
    String name;
    int age;
    int rollNumber;
    String college;

    Student1(String name, int age, int rollNumber, String college){
         this.name = name;
         this.age = age;
         this.rollNumber = rollNumber;
         this.college = college;

    }


}
