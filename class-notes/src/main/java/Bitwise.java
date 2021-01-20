import java.util.Random;

public class Bitwise {

    public static void main(String[] args) {
        int andMask = 1;

        Random rng = new Random();
        int randNumAnd = rng.nextInt();

        for (int i = 0; i < 32; i++) {
            System.out.println(randNumAnd & andMask);
            andMask <<= 1;
        }

        int orMask = 0b11111111111111111111111111111110;
        int randNumOr = rng.nextInt();

        for (int i = 0; i < 32; i++) {
            System.out.println(randNumOr | orMask);
            orMask <<= 1;
            orMask++;
        }

    }
}
