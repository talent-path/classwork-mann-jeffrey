import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Warmup {
    public static void main(String[] args) {
//        System.out.println(canPlaceFlowers(new int[] {1,0,0,0,1}, 1));
//        System.out.println(canPlaceFlowers(new int[] {1,0,0,0,1}, 2));
//        System.out.println(canPlaceFlowers(new int[] {1,0,0,0,0,0,1}, 2));
        System.out.println(canPlaceFlowers(new int[]{0}, 1));
        System.out.println(canPlaceFlowers(new int[]{0, 0, 0, 0, 1}, 2));
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

        for (int i = 1; i < numDigits + 1; i++) {
            int currentDigit = (toFlip % (int) Math.pow(10, i)) / (int) Math.pow(10, i - 1);
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
            ;
        }

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

    public static boolean isPerfect(int n) {
        int sum = 0;
        for (int i = 1; i < n; i++) {
            if (n % i == 0) {
                sum += i;
            }
        }
        return sum == n;
    }

    public static boolean isPerfectMemoized(int n, Map<Integer, Integer> sums, Map<Integer, List<Integer>> factors) {
        return sumFactors(n, sums, factors) == 2 * n;
    }

    public static int sumFactors(int n, Map<Integer, Integer> sums, Map<Integer, List<Integer>> factors) {
        if (!sums.containsKey(n)) {
            int sum = 0;
            for (int factor : factorsOf(n, factors)) {
                sum += factor;
            }
            sums.put(n, sum);
            return sum;
        }
        return sums.get(n);
    }

    public static List<Integer> factorsOf(int n, Map<Integer, List<Integer>> factors) {
        if (!factors.containsKey(n)) {
            List<Integer> newFactors = new ArrayList<>();
            for (int factor = 1; factor <= n; factor++) {
                if (isFactor(n, factor)) {
                    newFactors.add(factor);
                }
            }
            factors.put(n, newFactors);
            return newFactors;
        }
        return factors.get(n);
    }

    public static boolean isFactor(int n, int potential) {
        return n % potential == 0;
    }

    public static boolean isValidSudoku(char[][] board) {
        boolean valid = true;
        for (int row = 0; valid && row < board.length; row++) {
            for (int col = 0; valid && col < board[row].length; col++) {
                if (board[row][col] != '.') {
                    int boxTop = row / 3 * 3;
                    int boxLeft = col / 3 * 3;

                    for (int i = 0; valid && i < 9; i++) {
                        int boxR = boxTop + i / 3;
                        int boxC = boxLeft + i % 3;

//                        if ((row != boxR || col != boxC) && board[row][col] == board[boxR][boxC])
                    }
                }
            }
        }
        return valid;
    }

    public static void solveSudoku(char[][] board) {
        boolean done = false;
        //1. loop through all squares
        for (int row = 0; !done && row < 9; row++) {
            for (int col = 0; !done && col < 9; col++) {
                //2. if '.' try each nuber one at a time
                if (board[row][col] == '.') {
                    for (char toTry = '1'; !done && toTry <= '9'; toTry++) {
                        board[row][col] = toTry;
                        if (isValidSudoku(board)) {
                            //3. recursively call solve sudoku
                            solveSudoku(board);
                            done = checkSolved(board);
                        }
                    }
                    if (!done) {
                        board[row][col] = '.';

                        done = true;
                    }
                }
            }
        }
    }

    public static boolean checkSolved(char[][] board) {
        boolean solved = true;
        for (int row = 0; solved && row < board.length; row++) {
            for (int col = 0; solved && col < board[row].length; col++) {
                if (board[row][col] == '.') solved = false;
            }
        }
        return solved;
    }

    public static int rangeSumBST(TreeNode root, int low, int high) {
        int sum = 0;
        if (root.val >= low && root.val <= high) {
            sum += root.val;
        }
        if (root.left != null && root.val >= low) sum += rangeSumBST(root.left, low, high);
        if (root.right != null && root.val <= high) sum += rangeSumBST(root.right, low, high);
        return sum;
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null) return null;


        ListNode prev = null;
        ListNode current = head;
        ListNode next = null;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;

        return head;
    }

    public int search(int[] nums, int target) {
        // iterative
//         int min = 0;
//         int max = nums.length - 1;

//         while (min <= max) {
//             int mid = (min + max) / 2;
//             if (nums[mid] == target) {
//                 return mid;
//             } else if (nums[mid] < target) {
//                 min = mid + 1;
//             } else {
//                 max = mid -1;
//             }
//         }

//         return -1;
        return binarySearch(nums, target, 0, nums.length - 1);
    }

    private int binarySearch(int[] nums, int target, int min, int max) {
        //recursive
        if (min > max) return -1;
        int mid = (min + max) / 2;

        if (nums[mid] == target) return mid;
        else if (nums[mid] < target) return binarySearch(nums, target, mid + 1, max);
        else return binarySearch(nums, target, min, mid - 1);
    }

    public static boolean hasCycle(ListNode head) {
//        Set<ListNode> seen = new HashSet<>();
//        boolean cycle = false;
//
//        ListNode temp = head;
//        while (temp != null && !cycle) {
//            cycle = !seen.add(temp);
//            temp = temp.next;
//        }
//
//        return cycle;

        ListNode one = head;
        ListNode two = head.next;

        while (two != null && two.next != null) {
            if (one == two) {
                return true;
            }
            two = two.next.next;
            one = one.next;
        }
        return false;
    }

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prev = head;
        ListNode curr = head.next;

        head = curr;

        while (true) {
            ListNode next = curr.next;
            curr.next = prev;

            if (next == null || next.next == null) {
                prev.next = next;
                break;
            }

            prev.next = next.next;

            prev = next;
            curr = prev.next;
        }
        return head;
    }

    public static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    public static int minDays(int n) {
        if (n == 1) return 1;

        int minDays = 0;

        while (n > 0) {
            int minOranges = 1 + Math.min((n % 2) + minDays(n / 2), (n % 3) + minDays(n / 3));
            n = n - minOranges;
            minDays++;
        }

        return minDays;
    }

    public static boolean validTicTacToe(String[] board) {
        boolean isValid = false;
        boolean won = false;

        int countX = 0;
        int countO = 0;

        int totalX = 0;
        int totalO = 0;

        for (String s : board) {
            // if all empty squares
            if (!s.contains("X") && !s.contains("O")) isValid = true;
            for (char c : s.toCharArray()) {
                if (c == 'X') countX++;
                if (c == 'O') countO++;
            }

            for (int i = 0; i < 3; i++) {
                if (s.charAt(i) == 'X') won = true;
                else if (s.charAt(i) == 'O') won = true;
                else {
                    won = false;
                    break;
                }
            }

            if (countX == 3) won = true;
            if (countO == 3) won = true;

            totalX += countX;
            totalO += countO;
            countX = 0;
            countO = 0;
        }
        isValid = true;
        // if more xs or os than squares
        if (totalX + totalO > 9) isValid = false;
            // if x or o have taken too many moves
        else if (totalO > totalX || totalX > totalO + 1) isValid = false;

        if (won) {
            if (totalX != totalO - 1) isValid = false;
            else if (totalO != totalX) isValid = false;
            else isValid = true;
        }
        return isValid;
    }

    public static int countNodes(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        int count = 0;
        TreeNode current = getLeftMost(root);

        while (current != null) {
            count++;
            current = getNextNode(current, root);
        }

        return count;
    }

    private static TreeNode getLeftMost(TreeNode n) {
        TreeNode temp = n;
        while (temp.left != null) {
            temp = temp.left;
        }
        return temp;
    }

    private static TreeNode getNextNode(TreeNode n, TreeNode root) {
        TreeNode parent = getParent(n, root);
        if (n.right != null) return getLeftMost(n.right);
        else {
            while (parent != null && parent.right.equals(n)) {
                n = parent;
            }
            return parent;
        }

    }

    private static TreeNode getParent(TreeNode n, TreeNode root) {
        if (root == null || root.left == null || root.right == null) return null;
        else if (root.left.equals(n) || root.right.equals(n)) {
            return root;
        } else {
            if (root.val < n.val) {
                return getParent(n, root.right);
            } else {
                return getParent(n, root.left);
            }
        }
    }

    public static int islandPerimeter(int[][] grid) {
        int perimeter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                if (grid[i][j] == 1) {
                    perimeter += 4;

                    if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                        perimeter -= 1;
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                        perimeter -= 1;
                    }
                    if (i + 1 <= grid.length - 1 && grid[i + 1][j] == 1) {
                        perimeter -= 1;
                    }
                    if (j + 1 <= grid[i].length - 1 && grid[i][j + 1] == 1) {
                        perimeter -= 1;
                    }
                }
            }
        }

        return perimeter;
    }

    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n == 0) return true;
        boolean planted = false;
        while (n > 0) {
            planted = false;
            for (int i = 0; i < flowerbed.length; i++) {
                if (flowerbed[i] == 0) {
                    if (i - 1 >= 0 && i + 1 <= flowerbed.length - 1) {
                        if (flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0) {
                            planted = true;
                            flowerbed[i] = 1;
                            break;
                        }
                    } else if (i < flowerbed.length - 1 && flowerbed[i + 1] == 0) {
                        planted = true;
                        flowerbed[i] = 1;
                        break;
                    } else if (i > 0 && flowerbed[i - 1] == 0) {
                        planted = true;
                        flowerbed[i] = 1;
                        break;
                    } else if (i + 1 > flowerbed.length - 1 && i - 1 < 0) {
                        planted = true;
                        flowerbed[i] = 1;
                        break;
                    }
                }
            }
            n--;
        }
        return planted;
    }

    public static boolean canPlaceFlowersBetter(int[] flowerbed, int n) {
        int openSpots = 0;
        for (int i = 0; i < flowerbed.length && openSpots < n; i++) {
            if (flowerbed[i] == 1) {
                i++;
            } else if ((i == 0
                    || flowerbed[i - 1] == 0)
                    && (i + 1 == flowerbed.length
                    || flowerbed[i + 1] == 0)) {
                i++;
                openSpots++;
            }

        }
        return openSpots >= n;
    }
}
