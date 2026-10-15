class Bank{
    final void calculateInterset(){
        System.out.println("Base Intrest Calculation");
    }
}

class Branch extends Bank{
    void calculateInterset1(){
        System.out.println("Custom Interst");
    }
}


public class FinalKeyWord {

    public static void main(String[] args) {
        Branch b1 = new Branch();
        b1.calculateInterset1();
    }
}