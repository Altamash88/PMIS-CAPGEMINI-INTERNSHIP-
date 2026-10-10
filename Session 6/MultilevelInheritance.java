package Day_6;

class Device {
    void poweron() {
        System.out.println("Device powered on");
    }
}

class Dabbaphone extends Device {
    void makecall() {
        System.out.println("Calling the number");
    }
}

class Smartphone extends Dabbaphone {
    void browserinternet() {
        System.out.println("Opening browser");
    }
}

public class MultilevelInheritance {
    public static void main(String[] args) {
        Smartphone samsung = new Smartphone();

        samsung.makecall();
        samsung.browserinternet();
        samsung.poweron();
    }
}