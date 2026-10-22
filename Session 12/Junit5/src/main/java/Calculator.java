// Remove the package main.java; line entirely from the top!

public class Calculator {
    public int add(int a, int b){
        return a + b;
    }

    public float divide(float a, float b){
        if(b == 0){
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return a / b;
    }
}
