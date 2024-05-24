import java.util.Random;

public class j45Ramdom {

    public static void main(String args[]) {
        Random rd = new Random();

        for (int i = 0; i < 20; i++) {
            int randomNumber = rd.nextInt(5, 10);
            System.out.println("Random Number : " + randomNumber);
        }

    }

}
