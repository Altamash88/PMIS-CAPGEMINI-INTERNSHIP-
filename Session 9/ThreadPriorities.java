class ThreadPrioritiesEx extends Thread{
    public void run(){
        System.out.println("Priority");
    }
}

public class ThreadPriorities {
    static String name="Alex";
    public static void main(String[] args) {
        ThreadPrioritiesEx th1 = new ThreadPrioritiesEx();
        ThreadPrioritiesEx th2 = new ThreadPrioritiesEx();
        ThreadPrioritiesEx th3 = new ThreadPrioritiesEx();
        System.out.println("priority of thread th1"+ th1.getPriority());
        System.out.println("priority of thread th2"+ th2.getPriority());
        System.out.println("priority of thread th3"+ th3.getPriority());


        th1.setPriority(6);
        th2.setPriority(10);
        th3.setPriority(3);

        System.out.println("priority of thread th1"+ th1.getPriority());
        System.out.println("priority of thread th2"+ th2.getPriority());
        System.out.println("priority of thread th3"+ th3.getPriority());
        System.out.println(name);
    }
}
