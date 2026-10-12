import java.nio.channels.Pipe.SourceChannel;

class UserAccount{
    private String username;
    private static int totalUser=0;

    UserAccount(String username){
        this.username=username;
        totalUser++;
    }

    String getUsername(){
        return username;
    }

    static int getTotalUser(){
        return totalUser;
    }
}
public class Account {
    public static void main(String[] args) {
        System.out.println("intial count : "+UserAccount.getTotalUser());
        UserAccount ua1 = new UserAccount("Alex");
        UserAccount ua2 = new UserAccount("BOB");
        UserAccount ua3 = new UserAccount("Max");

        System.out.println("userName : "  + ua1.getUsername());
        System.out.println("userName : " + ua2.getUsername());
        System.out.println("userName : " + ua3.getUsername());
        System.out.println("Total User : "+UserAccount.getTotalUser());
        

    }
}
 