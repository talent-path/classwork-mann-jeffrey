public class Aggregate {
    public static int min( int[] arr ) {
//        find minimum number
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    public static int max( int[] arr ) {
//        find maximum number
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static int sum( int[] arr) {
//        find sum of all numbers
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        return sum;
    }

    public static double sum( double[] arr) {
//        find sum of all numbers
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        return sum;
    }

    public static double average( int[] arr ) {
//        find average (sum / number of items)
        return (double)sum(arr) / (double)arr.length;
    }

    public static double average( double[] arr ) {
//        find average (sum / number of items)
        return sum(arr) / arr.length;
    }

    public static double sampleStandardDeviation( int[] arr ) {
//        find average of data
        double avg = average(arr);
//        double[] devArr = new double[arr.length];
        double sumOfSquares = 0;

        for (int i = 0; i < arr.length; i++) {
//        subtract average from each entry
            double d = arr[i] - avg;
//        square the difference
            sumOfSquares += d*d;
        }
//        find square root of sum over size minus one
        return Math.sqrt(sumOfSquares / (arr.length - 1));
    }

    public static double populationStandardDeviation( int[] arr ) {
        double avg = average(arr);
//        double[] devArr = new double[arr.length];
        double sumOfSquares = 0;

        for (int i = 0; i < arr.length; i++) {
            double d = arr[i] - avg;
            sumOfSquares += d*d;
        }

        return Math.sqrt(sumOfSquares / arr.length);
    }
}
