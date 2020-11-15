import com.IanFlanagan.MyConfiguration;
import com.rollbar.api.payload.data.Person;

import java.util.Random;

public class RandomTest {

    public static void main(String[] args) {


        int max = 2;
        int min = 0;
        int num = 0;

        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        System.out.println(randomNum);

        switch (num) {

            case 0:
                System.out.println("Ian person config");
            case 1:
                System.out.println("Finbar person config");
            case 2:
                System.out.println("Ivan person config");
            default:
                System.out.println("No valid number will be returned");
                break;

        }

    }
}
