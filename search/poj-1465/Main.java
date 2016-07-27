import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Main().solve();
    }
    int n, m;
    int[] digits;
    class Node {
        int digit;
        Node prev;
        Node(int digit, Node prev) {
            this.digit = digit;
            this.prev = prev;
        }
    }
    void solve() {
        Scanner cin = new Scanner(System.in);
        while (cin.hasNextInt()) {
            n = cin.nextInt(); m = cin.nextInt();
            digits = new int[m];
            for (int i = 0; i < m; i++) {
                digits[i] = cin.nextInt();
            }
            Arrays.sort(digits);
            solveOne();
        }
    }
    void solveOne() {
        if (n == 0) {System.out.println(0); return;}
        Node[] remainderNums = new Node[n];
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < m; i++) {
            if (digits[i] == 0) {continue;}
            int remainder = digits[i] % n;
            if (remainder == 0) {System.out.println(digits[i]); return;}
            if (remainderNums[remainder] == null) {
                remainderNums[remainder] = new Node(digits[i], null);
                queue.offer(remainder);
            }
        }
        while (!queue.isEmpty()) {
            int remainder = queue.poll();
            if (remainder == 0) {output(remainderNums[remainder]); return;}
            for (int i = 0; i < m; i++) {
                int newRemainder = (remainder * 10 + digits[i]) % n;
                if (remainderNums[newRemainder] == null) {
                    remainderNums[newRemainder] = new Node(digits[i], remainderNums[remainder]);
                    queue.offer(newRemainder);
                }
            }
        }
        System.out.println(0);
    }
    void output(Node node) {
        StringBuilder sb = new StringBuilder();
        for (;node != null; node = node.prev) {
            sb.append(node.digit);
        }
        System.out.println(sb.reverse().toString());
    }
}
