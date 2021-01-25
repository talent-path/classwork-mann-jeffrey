package com.tp.rpg;

import java.util.Scanner;

public class Console {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void print(String msg) {
        System.out.print(msg);
    }

    public static void printBlack(String msg) {
        System.out.print(ANSI_BLACK + msg + ANSI_RESET);
    }

    public static void printRed(String msg) {
        System.out.print(ANSI_RED + msg + ANSI_RESET);
    }

    public static void printGreen(String msg) {
        System.out.print(ANSI_GREEN + msg + ANSI_RESET);
    }

    public static void printYellow(String msg) {
        System.out.print(ANSI_YELLOW + msg + ANSI_RESET);
    }

    public static void printBlue(String msg) {
        System.out.print(ANSI_BLUE + msg + ANSI_RESET);
    }

    public static void printPurple(String msg) {
        System.out.print(ANSI_PURPLE + msg + ANSI_RESET);
    }

    public static void printCyan(String msg) {
        System.out.print(ANSI_CYAN + msg + ANSI_RESET);
    }

    public static void printWhite(String msg) {
        System.out.print(ANSI_WHITE + msg + ANSI_RESET);
    }

    public static double readDouble(String msg, double min, double max) {
        boolean isValid = false;
        double value = Double.MIN_VALUE;
        while (!isValid) {
            value = readDouble(msg);
            if (value >= min && value <= max) {
                System.out.println("Valid Double!");
                isValid = true;
            } else
                System.out.println("Not correct!!! Try again.");
        }
        return value;
    }

    public static double readDouble(String msg) {
        Scanner scan = new Scanner(System.in);
        boolean isValid = false;
        double parsedDouble = Double.MIN_VALUE;
        while (!isValid) {
            print(msg);
            String userInput = scan.nextLine();
            try {
                parsedDouble = Double.parseDouble(userInput);
                isValid = true;
            } catch (NumberFormatException ex) {
            }
        }
        return parsedDouble;
    }

    public static int readInt(String msg, int min, int max) {
        boolean isValid = false;
        int value = Integer.MIN_VALUE;
        while (!isValid) {
            value = readInt(msg);
            if (value >= min && value <= max) {
                isValid = true;
            } else {
                System.out.println(String.format("Please input number between %d & %d.", min, max));
            }
        }
        return value;
    }

    public static int readInt(String msg) {
        Scanner scan = new Scanner(System.in);
        boolean isValid = false;
        int parsedInt = Integer.MIN_VALUE;
        while (!isValid) {
            print(msg);
            String userInput = scan.nextLine();
            try {
                parsedInt = Integer.parseInt(userInput);
                isValid = true;
            } catch (NumberFormatException ex) {
            }
        }
        return parsedInt;
    }

}
