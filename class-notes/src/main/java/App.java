import java.util.Scanner;

public class App {
    //    :)
    public static void main(String[] args) {
//        PRIMITIVE TYPES

//        variables "declared" w/ *type* and *name*

        int x = 5; // integer (no decimal)
        long bigNum; // 64-bit integer
        short smallNum; // 16-bit integer
        byte verySmallNum; // 8-bit (1 byte) integer

        x = 1000000000;
        x = x + 1000000000;
        x = x + 1000000000;

        System.out.println(x); // ***overflow error: most significant bit (sign bit) will flip

        char singleCharacter; // one letter/number/symbol UTF-16 (16-bit)

        double d; //double precision (64-bit) floating point number
        float f; //single precision (32-bit) floating point number

        //floats are really *two integers*
        // like scientific notation...

//        mantissa * 10 ^ exp           1.234 * 10 ^ -3 = 0.001234
//                                      1234 * 10 ^ -6 = 0.001234

//        **computer doesn't use base 10**

//        mantissa * 2 ^ exp
//        in scientific notation
//        1/3 = 0.3333... * 10 ^ 1

//        check https://0.30000000000000004.com/ for base two representation issues

        boolean trueOrFalse; // logic values (true or false)

//        NON-PRIMITIVE / REFERENCE TYPES

        Object somethingComplicated; // represents an "object"
                                    // not terribly useful on its own
        String name; // a collection of char data
                    // lots and lots of useful built-in methods

        //variables are "initialized" with an "assignment" (=)

        //left side of an assignment must be a variable
        //right side of an assignment must be an "expression" that fits in that variable

        x = 5; // change x to 5
        // 5 = x; // invalid (L error?)
        // x = true; // invalid (assignment error)

        //variables can be initialized when they are declared (and ideally should be)

        int y = 23;
        String otherName = "Bob";

        long largeFilePos = 55L; // <-- the L makes this a "long literal" otherwise would be interpreted as an int

        float length = 2.3f; // <-- "float literal"

        long binaryDefinition = 0b0001000101010100110L; // the "0b" means this is a binary representation

        int hexColorCode = 0xFFFFFF; // the "0x" means this is a hexadecimal

//        OPERATORS

        int a = 5;
        int b = 2;

        int sum = a + b; //addition
        int difference = a - b; //subtraction
        int product = a * b; //multiplication
        int quotient = a / b; //division
        int remainder = a % b; //modulo

//        int bad = 1 / 0;

        //a++; <-- increment
        //a--; <-- decrement

//        GETTING USER INPUT

        //1. prompt the user
        //2. read the user input (always string type)
        //3. "parse" the string into an integer so we can do math on it

        //1.
        System.out.print("Please enter a: ");

        //2.
        Scanner input = new Scanner(System.in);
        String userStringA = input.nextLine();

        //3.
        int otherA = Integer.parseInt(userStringA);

        System.out.print("Please enter b: ");
        String userStringB = input.nextLine();
        int otherB = Integer.parseInt(userStringB);

        System.out.println("a + b = " + (otherA+otherB));
        System.out.println("a - b = " + (otherA-otherB));
        System.out.println("a * b = " + (otherA*otherB));
        if (otherB != 0) {
            System.out.println("a / b = " + (otherA / otherB));
            System.out.println("a % b = " + (otherA % otherB));
        } else {
            System.out.println("Cannot compute division by zero");
        }

//        BOOLEAN OPERATORS
//        take bools as input and output

//        ! = not operator - invert provided value
//        unary operator - operation with only one operand

        boolean inClass = false;
        boolean absent = !inClass;

//        && = and operator - both operands must be true for output to be true otherwise false
        boolean payingAttention = true;
        boolean goodStudent = payingAttention && inClass;

//        || = or operator - both operands must be false for output to be false otherwise true
        boolean badStudent = !inClass || !payingAttention;

//        !(a && b) == !a || !b;    <--DeMorgan's Law
//        !(a || b) == !a & !b;     <--     ""

//        Bitwise equivalents

        int num = 57;
        int flag = 1;

        if ((num & flag) == 1) {
            //number is odd
        }

        int mask = 0b11111111111111110000000000000000;
        System.out.println(999999999 & mask);
        // 111011100110101100100111111111 & 11111111111111110000000000000000 = 11101110011010110000000000000000
        System.out.println(999999999 | mask);
        // 111011100110101100100111111111 | 11111111111111110000000000000000 = 11101110011010110000000000000000
        System.out.println(999999999 ^ mask);

//        comparison operators
//        numeric input / boolean output

//        == equality operator - returns true if operand on left is the same as right
//        when used on OBJECTS, however, this will return true if objects are in the same memory location
//        as a result, you only want to use them on primitives
//        use .equals() to compare Objects

//        != returns true if


//        LOOPS

//        while loop
//        keep repeating while boolean expression is true
//        typically we use while loops when we don't know how many times we need it to loop

//        this is an infinite loop
        while (true) {
            break;  // this exits loop no matter what, regardless of condition at the top
                    // can be a "code smell"
                    // causes a jump in execution that isn't really reflected by brackets
        }

//        echo the user input as long as they type even numbers
//        otherwise go forever
        while (true) {
            int toCheck = Console.readInt("please enter a number");
            if (toCheck % 2 != 0) {
                continue;
            }
            System.out.println(toCheck);;
        }

//        int userChoice = 0;
//        do {
//            // print our main menu
//            // we'll pretend this works
//            // and option 5 to quit
//            userChoice = Console.readInt("please enter selection", 1, 5);
//
//            // do something with choices 1-4 if that's what they enter
//        } while (userChoice != 5);
//
//        System.out.println(userChoice);


//        for loop
//        typically used when number of iterations is "known"
//        3 parts:
//        1) initialization
//        2) "while" condition
//        3) increment value

//        for (int i = 0; i < 10; i++) {
//            System.out.println(i);
//        }
//
//        for (int i = -1; i < -10; i--) {
//            System.out.println(i);
//        }

//        "this loop is so bad it's crying"
//        boolean _ = true;
//        for ( ; _ ; ) {
//
//        }
//        equivalent to -> while(_){ }

//          ARRAYS

//        most basic collection of data
//        arrays are indexed at 0
//        the first piece of data is found at 0 not 1
//        the max index is the size of the array - 1 (minus one)

//        declaring an array variable
//        type of each element, followed by empty square brackets
//        String[] classNames;

//        initializing an array
//        use new keyword to allocate memory
//        now in square brackets we provide the size of our array

//        classNames = new String[14];

//        String[] temp = new String[14];

//        first index = 0
//        index must stay less than length (bc max index is length-1)
//        since we copy each element we increase by one
//        for (int i = 0; i < temp.length; i++) {
//            temp[i] = classNames[i];
//        }
//
//        classNames = new String[15];
//        for (int i = 0; i < classNames.length; i++) {
//            classNames[i] = temp[i];
//        }

//        quick initialization
//        int[] ages = {0, 2, 14, 34, 99};

    }
}
