public class Fibonacci {
    public static int fibonacci( int n ) {
        if (n==1) return 1;
        if (n==0) return 0;

        int a = 0;
        int b = 1;

        for (int i = 3; i <= n; i++) {
            int next = a + b;
            a = b;
            b = next;
        }

        return b;
    }

    //    memoization!
    static int[] allFoundFibs = new int[1000000000];

    public static int fibRecursive( int n ) {
        if (n == 1) return 0;
        if (n == 2) return 1;

        if (allFoundFibs[n] == 0) allFoundFibs[n] = fibRecursive(n-1) + fibRecursive(n-2);

        return allFoundFibs[n];
    }
}
