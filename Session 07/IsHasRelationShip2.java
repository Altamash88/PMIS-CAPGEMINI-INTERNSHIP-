class Address {
    String city;
    String state;
    int pincode;

    Address(String city , String state , int pincode){
        this.city=city;
        this.state=state;
        this.pincode = pincode;
    }
    void displayAddress(){
        System.out.println(city + " " + state + " "+ pincode );        
    }

}

class Student{
    String name;
    int rollnumber;
    Address address;
    Student(String name,int rollnumber,Address address){
        this.rollnumber = rollnumber;
        this.address = address;
        this.name = name;
    }
    void displayProfile(){
        System.out.println("Name : "+name +",\nRoll No : "+ rollnumber +",");
        System.out.println("Live at this Address : ");
        address.displayAddress();
    }

}

public class IsHasRelationShip2 {
    public static void main(String[] args) {
        Address add = new Address("Mumbai", "Maharashtra", 400072);
        Student std1 = new Student("Alex", 49,add);
        std1.displayProfile();
    }
}
