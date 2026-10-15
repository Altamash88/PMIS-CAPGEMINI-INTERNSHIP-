abstract class Shape{
    private String color;
    void setColor(String color){
        this.color = color;
    }
    String getColor(){
        return color;
    }
    abstract double calculateArea(); 
}
class Circle extends Shape{
    double radius;
    Circle(double radius){
        this.radius = radius;
    }
    @Override 
    double calculateArea(){
        return (Math.PI)*radius*radius;
        
    }
}
class Rectangle extends Shape{
    double len;
    double brd;

    Rectangle(double len,double brd){
        this.len=len;
        this.brd= brd;
    }

    @Override 
    double calculateArea(){
        return len*brd;
    }
}
public class abstractMethod {
    public static void main(String[] args) {
        Circle c1 = new Circle(5);
        Rectangle r1 = new Rectangle(1,4);
        c1.setColor("red");
        System.out.println("Circle color : "+ c1.getColor());
        System.out.printf("Circle color %.2f%n", c1.calculateArea());
        // System.out.printf("Circle Area: %.2f%n", c1.calculateArea());
        r1.setColor("green");
        System.out.println("Rectangle color : "+r1.getColor()); 
        System.out.printf("Rectangle color %.2f%n ",r1.calculateArea()); 
    }
}
