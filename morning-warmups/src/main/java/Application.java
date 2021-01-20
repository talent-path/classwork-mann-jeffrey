public class Application {
    public static void main(String[] args) {

    }

    public static int middleOfThree(int a, int b, int c) {
        int middleNum = a;

        if (a > b) {
            if (a > c) {
                middleNum = b;
            } else if (a < c) {
                middleNum = c;
            } else {
                middleNum = a;
            }
        } else {
        }

        return middleNum;
    }

    public static int maxOfThree(int a, int b, int c) {
        int max = a;

        if (a > b) {

        }
        throw new UnsupportedOperationException();
    }

    public static void fizzbuzz() {
        for (int i = 1; i < 100; i++) {
            String output = "";

            if (i%3==0) output += "fizz";
            if (i%5==0) output += "buzz";
            if (output.equals("")) output = Integer.toString(i);

            System.out.println(output);
        }
    }
}
