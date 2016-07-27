import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Main().solve();
    }
    int T, N, C, M;
    int[] digits;
    class Node {
        Node prev; int digit, len;
        Node(int digit, int len, Node prev) {this.digit = digit; this.len = len; this.prev = prev;}
        public String toString() {
            if (this.digit == -1) {
                return "give me the bomb please";
            } else {
                Node current = this;
                StringBuilder sb = new StringBuilder(); 
                for (;current != null; current = current.prev) {
                    sb.append(toChar(current.digit));
                } 
                return sb.reverse().toString();
            }
        }
    }
    int toInt(char ch) {
        if (Character.isUpperCase(ch)) {
            return ch - 'A' + 10;
        } else if (Character.isDigit(ch)) {
            return ch - '0';
        } else {
            throw new RuntimeException("invalid ch:" + ch);
        }
    }
    char toChar(int i) {
        if (i < 10) {
            return (char)('0' + i);
        } else if (i < 16) {
            return (char)('A' + i - 10);
        } else {
            throw new RuntimeException("invalid i:" + i);
        }
    }

    void solve() {
        Scanner cin = new Scanner(System.in);
        T = cin.nextInt();
        for (; T > 0; T--) {
            N = cin.nextInt(); C = cin.nextInt(); M = cin.nextInt();
            digits = new int[M];
            for (int i = 0; i < M; i++) {
                digits[i] = toInt(cin.next().charAt(0));
            }
            Arrays.sort(digits);
            System.out.println(bfs());
        }
    }
    Node bfs() {
        if (N == 0) {
            if (digits[0] == 0) {
                return new Node(0, 1, null);
            } else {
                return new Node(-1, 1, null);
            }
        }
        Node[] rmdNodes = new Node[N];
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < M; i++) {
            if (digits[i] == 0) {continue;}
            int rmd = digits[i] % N;
            if (rmd == 0) {return new Node(digits[i], 1, null);}
            if (rmdNodes[rmd] == null) {
                rmdNodes[rmd] = new Node(digits[i], 1, null);
                queue.offer(rmd);
            }
        }
        while (!queue.isEmpty()) {
            int rmd = queue.poll();
            if (rmdNodes[rmd].len > 500) {break;}
            if (rmd == 0) {return rmdNodes[rmd];}
            for (int i = 0; i < M; i++) {
                int newRmd = (rmd * C + digits[i]) % N;
                if (rmdNodes[newRmd] == null) {
                    rmdNodes[newRmd] = new Node(digits[i], rmdNodes[rmd].len + 1, rmdNodes[rmd]);
                    queue.offer(newRmd);
                }
            }
        }
        return new Node(-1, 1, null);
    }
}
