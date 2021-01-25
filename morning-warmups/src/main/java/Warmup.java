import java.util.*;

public class Warmup {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("sss"));
        System.out.println(lengthOfLongestSubstring("sas"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }

    public static boolean noTriples(int[] arr) {
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
            for (int j = nums.length - 1; j >= 0; j--) {
                if (nums[i] == nums[j]) {
                    int currentLength = 1;
                    for (int offset = 1; i + offset < nums.length && j - offset >= 0; offset++) {
                        if (nums[i + offset] == nums[j - offset]) {
                            currentLength++;
                        } else {
                            break;
                        }
                    }
                    if (currentLength > mirrorLength) {
                        mirrorLength = currentLength;
                    }
                }
            }
        }

        return mirrorLength;
    }

    public static Map<String, List<String>> groupByFirstTwoLetters(String[] arr) {
        return groupByFirstNLetters(arr, 2);
    }

    public static Map<String, List<String>> groupByFirstNLetters(String[] arr, int n) {
        Map<String, List<String>> output = new HashMap<String, List<String>>();
        for (String name : arr) {
            String firstN = name.length() > n ? name.substring(0, n) : name;
            firstN = firstN.toLowerCase(Locale.ROOT);
            if (output.containsKey(firstN)) {
                List<String> existingList = output.get(firstN);
                existingList.add(name);
            } else {
                List<String> newList = new ArrayList<>();
                newList.add(name);
                output.put(firstN, newList);
            }
        }

        return output;
    }

    public static long collatzRule(long n) throws Exception {
        if (n < 1) throw new Exception("n must be greater than 1!");
        if (n % 2 == 0) {
            n = n / 2;
        } else {
            n = 3 * n + 1;
        }
        return n;
    }

    public static long collatzRuleRecursive(long n) throws Exception {

        if (n < 1) throw new Exception("n must be greater than 1!");
        if (n % 2 == 0) {
            n = n / 2;
        } else {
            n = 3 * n + 1;
        }
        return n;
    }

    public static int collatzSequence(long n) {
        int iter = 0;
        while (n > 1) {
            try {
                n = collatzRule(n);
            } catch (Exception e) {
                System.out.println(e.toString());
                System.exit(1);
            }
            iter++;
        }
        return iter;
    }

    public static int collatzSequenceMemoized(long n, Map<Long, Integer> memo) {
        int iter = 0;
        if (memo.containsKey(n)) {
            iter = memo.get(n);
        } else {
            while (n > 1) {
                try {
                    n = collatzRule(n);
                } catch (Exception e) {
                    System.out.println(e.toString());
                    System.exit(1);
                }
                iter++;
            }
            memo.put(n, iter);
        }
        return iter;
    }

    public static long longestCollatzSequence() {
        int length = -1;
        long longestSequence = -1;
        Map<Long, Integer> memo = new HashMap<>();
        for (long i = 1; i < 100000000; i++) {
            int currentLength = collatzSequenceMemoized(i, memo);
            if (currentLength > length) {
                length = currentLength;
                longestSequence = i;
                System.out.println(longestSequence + ": " + length);
            }
        }
        return longestSequence;
    }

    public static int flipDigits(int toFlip) {
        int numDigits = (int) (Math.log10(toFlip) + 1);
        int output = 0;

        for (int i = 1; i < numDigits+1; i++) {
            int currentDigit = (toFlip % (int) Math.pow(10, i)) / (int) Math.pow(10, i-1);
            output += currentDigit * (int) Math.pow(10, numDigits - i);
        }

        return output;
    }

    public static int davidsFlipDigits(int toFlip) {
        int flipped = 0;

        while (toFlip != 0) {
            int onesPlace = toFlip % 10;
            toFlip /= 10;

            flipped *= 10;
            flipped += onesPlace;
;        }

        return flipped;
    }

    public static int lengthOfLongestSubstring(String s) {
        int longest = 0;

        for (int i = 1; i < s.length(); i++) {
            int subLength = s.length();
            for (int j = 0; j < subLength; j++) {
                if (i != j) {
                    if (s.charAt(i) == s.charAt(j)) {
                        subLength--;
                    } else {
                        longest = subLength;
                    }
                }
            }
        }

        return longest;
    }
}
