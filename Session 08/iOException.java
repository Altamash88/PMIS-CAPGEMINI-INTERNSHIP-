import java.io.FileReader;
import java.io.*;


public class iOException {
    static void readfile() throws IOException{
    FileReader file = new FileReader("Finally.java");
    System.out.println("File Opened");
}
    public static void main(String[] args)  {
        try{
            readfile();
        }catch(IOException e){
            System.out.println("could not find file");
        }
    }
}
