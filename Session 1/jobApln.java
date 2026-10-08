package Day1;

import java.util.Scanner;

public class jobApln {

    static class criteria {
        void verify(boolean python, boolean french) {

            if (python == true && french == false) {
                System.out.println("you need to learn french");
            } else if (python == false && french == true) {
                System.out.println("you need to learn python");
            } else if (python == false && french == false) {
                System.out.println("you cannot apply");
            } else {
                System.out.println("you can apply");
            }
        }
    }
        public static void main() {
            criteria c = new criteria();
            Scanner sc = new Scanner(System.in);
            boolean p = sc.nextBoolean();
            boolean f = sc.nextBoolean();
            c.verify(p,f);

        }
}