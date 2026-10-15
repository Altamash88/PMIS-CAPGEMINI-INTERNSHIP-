public class BankException {
    public static void main(String[] args) {
        int bal = 10000;
        int withdraw = 1200;
        try{
            if(withdraw>bal){
                throw new IllegalArgumentException("balance insufficient");
            }
            withdraw -= bal;
            System.out.println("Withdraw is successfull");
            System.out.println("balance is :  " + bal);
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        System.out.println("program successfull");
    }
}
