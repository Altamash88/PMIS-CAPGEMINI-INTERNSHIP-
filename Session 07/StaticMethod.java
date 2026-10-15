class StaticMethodStudent {
    String name;
    static String collegeName = "IIT Bombay";

    StaticMethodStudent(String name) {
        this.name = name;
    }

    void displayStudent() {
        System.out.println("College name : " + collegeName);
        System.out.println("Student name : " + name);
    }
}

public class StaticMethod {
    public static void main(String[] args) {
        StaticMethodStudent s1 = new StaticMethodStudent("BOB");
        s1.displayStudent();
    }
}
