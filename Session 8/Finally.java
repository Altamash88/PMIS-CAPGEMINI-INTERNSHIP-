public class Finally {
    public static void main(String[] args){
        try{
            int res = 10/5;
            System.out.println(res);

        }catch(ArithmeticException e){
            System.out.println(e);
        }
        finally{
            System.out.println("Error occured but finally works");
        }
    }
}
