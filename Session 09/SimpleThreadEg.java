class ThreadDemo1 extends Thread{
    public void run(){
        System.out.println("Thread is Running.....");
    }
}

public class SimpleThreadEg {
    public static void main(String[] args) {
        ThreadDemo1 td1 = new ThreadDemo1();
                
    }
}
