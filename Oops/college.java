package Oops;
public class College{
    public static void main(String[] args){

        Student s1 = new Student();
        Student s2 = new Student();

        s1.name = "Anish";
        s2.name = "Harry Kane";
        s1.age = 22;
        s2.age=25;
        s1.rollNumber = 20;
        s2.rollNumber = 21;
        s1.college = "TU Darmstadt";
        s2.college = "Uni of Bonn";

        s1.markAttendance();
        s2.markAttendance();

        s1.printStudentData();
        s2.printStudentData();

    }


}

class Student{
    String name;
    int age;
    int rollNumber;
    String college;

    void markAttendance(){
        System.out.println("Attendance marked by " + name);

    }

    void printStudentData(){
        System.out.println(name + "\n"+age+"\n"+rollNumber+"\n"+college);
    }


}
