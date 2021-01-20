import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean isValid = true;

        System.out.println("You're in a Haunted House: There are 3 doors. ");
        System.out.println("Door 1: written on the first door are the words: \"Very Scary\" ");
        System.out.println("Door 2: written on the second door are the words: \"Kinda Scary\" ");
        System.out.println("Door 3: written on the third door are the words: \"Not-So Scary\" ");

        while (isValid == true) {
            try {
                System.out.println("Choose a door, which one will you choose?");
                int choice = scan.nextInt();
                if (choice == 1) {
                    System.out.println("very scary thing");
                    isValid = false;
                } else if (choice == 2) {
                    System.out.println("less scary thing");
                    isValid = false;
                } else if (choice == 3) {
                    System.out.println("not scary thing");
                    isValid = false;
                } else {
                    throw new InputMismatchException("please enter a valid option (1, 2, 3)");
                }
            } catch (InputMismatchException e) {
                scan = new Scanner(System.in);
                System.out.println("That's not a viable option");
            }
        }
    }
}
