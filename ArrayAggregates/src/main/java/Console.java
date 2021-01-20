import java.util.Scanner;

public class Console {
    public static double readDouble(String msg, double min, double max) {
        boolean isInRange = false;
        double output = Double.NaN;
        while (!isInRange) {
            output = readDouble(msg);
            isInRange = output >= min && output <= max;
        }
        return output;
    }

    public static double readDouble(String msg) {
        Scanner input = new Scanner(System.in);
        boolean isValid = false;
        double parsedDouble = Double.NaN;

        while (!isValid) {
            print(msg);
            String userInput = input.nextLine();

            try {
                parsedDouble = Double.parseDouble(userInput);
                isValid = true;
            } catch (NumberFormatException ex) {
            }
        }
        return parsedDouble;
    }

    public static int readInt(String msg, int min, int max) {
        boolean isInRange = false;
        int output = Integer.MIN_VALUE;
        while (!isInRange) {
            output = readInt(msg);
            isInRange = output >= min && output <= max;
        }
        return output;
    }

    public static int readInt(String msg) {
        Scanner input = new Scanner(System.in);
        boolean isValid = false;
        int parsedInt = Integer.MIN_VALUE;

        while (!isValid) {
            print(msg);
            String userInput = input.nextLine();

            try {
                parsedInt = Integer.parseInt(userInput);
                isValid = true;
            } catch (NumberFormatException ex) {

            }
        }

        return parsedInt;
    }

    public static void print(String output) {
        System.out.println(output);
    }
}
