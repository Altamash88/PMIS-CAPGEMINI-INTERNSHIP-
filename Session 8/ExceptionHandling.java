class ExcpetionHandling{
    public static void main(String[] args) {

        try{
            int c =10/0;
            System.out.println(c);
            int[] nums = {2,3,5};
            System.out.println(nums[4]);
            
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Invalid Array index");
        }
        catch(ArithmeticException r){
            System.out.println("cannot divide by 0");
        }


    }
}