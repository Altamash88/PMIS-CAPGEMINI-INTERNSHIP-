package Day_6;

interface Mother {
    void message();
}

interface Father {
    void message();
}

// Using interfaces, we achieve multiple inheritance
class Child implements Mother, Father {
    @Override
    public void message() {
        System.out.println("Loving both mom and dad");
    }
}

public class MutipleInhritance {
    public static void main(String[] args) {
        Child c = new Child();
        c.message();

        Mother m = new Child();
        m.message();

        Father f = new Child();
        f.message();
    }
}



