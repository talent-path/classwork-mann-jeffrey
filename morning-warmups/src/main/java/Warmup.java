public class Warmup {
    public static void main(String[] args) {
        System.out.println(maxMirror(new int[]{1, 2, 1, 4})); // expect 3
        System.out.println(maxMirror(new int[]{21, 22, 9, 8, 7, 6, 23, 24, 6, 7, 8, 9, 25, 7, 8, 9})); // expect 4

    }

    public static boolean noTriples(int [] arr) {
//        return true if there are no triples in the array
        int temp = arr[0];
        boolean isDouble = false;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == temp) {
                if (isDouble) {
                    return false;
                }
                isDouble = true;
            } else isDouble = false;
            temp = arr[i];
        }
        return true;
    }

    public static int[] addBigNum(int[] left, int[] right) {
        int biggestArr = Math.max(left.length, right.length);
        int carry = 0;
        int[] output = new int[biggestArr + 1];

        for (int i = 0; i < biggestArr; i++) {
            int sum = left[i] + right[i] + carry;
            carry = sum / 10;
            output[i] = sum % 10;
        }
        if (carry > 0) {
            output[biggestArr] = 1;
        }

        return output;
    }

    public static int[] baddBigNum(int[] left, int[] right) {
        int biggestArr = Math.max(left.length, right.length);
        int temp = Integer.MIN_VALUE;
        int[] sum = new int[biggestArr + 1];
        for (int i = 0; i < biggestArr; i++) {
            if (temp > 9) {
                sum[i] = 1;
            }
            temp = left[i] + right[i];
            sum[i] += temp % 10;
        }
        if (temp > 9) {
            sum[biggestArr] = 1;
        }
        return sum;
    }

    public static int[] multiplyBigNum(int[] left, int[] right) {
        throw new UnsupportedOperationException();
    }

    public static int maxMirror(int[] nums) {
        int mirrorLength = 0;

        for (int i = 0; i < nums.length - mirrorLength; i++) {
            for (int j = nums.length-1; j >= 0; j--) {
                if (nums[i] == nums[j]) {
                    int currentLength = 1;
                    for (int offset = 1; i + offset < nums.length && j - offset >= 0; offset ++) {
                        if (nums[i+offset] == nums[j-offset]) {
                            currentLength++;
                        } else {
                            break;
                        }
                    }
                    if ( currentLength > mirrorLength) {
                        mirrorLength = currentLength;
                    }
                }
            }
        }

        return mirrorLength;
    }
}
