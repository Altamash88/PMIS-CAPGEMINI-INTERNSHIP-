class Process1 extends Thread{
    public void run(){
        for (int i = 1; i <=10; i++) {
            System.out.println("process1 : "+i);
        }
    }
}
class Process2 extends Thread{
    public void run(){
        for (int i = 1; i <=10; i++) {
            System.out.println("process 2: "+i);
        }
    }
}
public class ThreadProgm {
    public static void main(String[] args) {
        Process1 p1 = new Process1();
        Process2 p2 =new Process2();
        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p2);
        t1.start();
        t2.start();
    }
}
