public class MultiDimArrays {

    public static void main(String[] args) {
//        1-d arrays
        int[] oneD = new int[10];

//        2-d arrays
        int[][] twoD = new int[10][];
        for (int i = 0; i < twoD.length; i++) {
            twoD[i] = new int[10];
        }
//        this produces a 10x10 array
//        OR an array size 10 filled with arrays size 10

        int[][] quick = {{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                {1, 2, 3, 4, 8, 9, 10},
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                {8, 9, 10},
                {1, 2, 3, 4, 5, 10},
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                {1, 2, 3, 4, 8, 9, 10}};

//        for (int row = 0; row < quick.length; row++) {
//            for (int col = 0; col < quick[row].length; col++) {
//                System.out.print(quick[row][col] + " ");
//            }
//            System.out.println();
//        }

        int[][] square = {{1, 2, 3, 111},
                {1, 2, 3, 222},
                {1, 2, 3, 333},
                {1, 2, 3, 444},};
//        for (int i = 0; i < square.length * square.length; i++) {
//            int row = i / square.length;
//            int col = i % square.length;
//            System.out.println(square[row][col] + "");
//        }

        System.out.println(middleOfThree(1,2,3));
        System.out.println(middleOfThree(1,3,2));
        System.out.println(middleOfThree(2,1,3));
        System.out.println(middleOfThree(2,3,1));
        System.out.println(middleOfThree(3,1,2));
        System.out.println(middleOfThree(3,2,1));
    }

    public static int computeIndex(int row, int col, int rowWidth) {
        return rowWidth * row + col;
    }

    public static int middleOfThree(int a, int b, int c) {
        int middleNum = a;

        if (a > b) {
            if (b > c) {
//                a>b>c
                middleNum = b;
            } else if (a > c) {
//                a>c>b
                middleNum = c;
            } else {
                middleNum = a;
            }
        } else {
            if (a > c) {
//                c<a<b
                middleNum = a;
            } else if (b > c) {
//                a<c<b
                middleNum = c;
            } else {
//
                middleNum = b;
            }
        }
        return middleNum;
    }
}
