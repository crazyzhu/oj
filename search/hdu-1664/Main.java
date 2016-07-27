import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Main().solve();
    }

    int n;
    class Node {
        StringBuilder s; int r;
        Node(StringBuilder s, int r) {this.s = s; this.r = r;}
        public String toString() {return s.toString();}
    }
    void solve() {
        Scanner cin = new Scanner(System.in);
        while ((n = cin.nextInt()) > 0) {
            String find1Ans = find1();    
            if (find1Ans != null) {
                System.out.println(find1Ans);
            } else {
                System.out.println(find2());
            }
        }
    }
    String find1() {
        String ans = null;
        for (int i = 1; i <= 9; i++) {
            boolean[] flags = new boolean[n];
            Arrays.fill(flags, false);
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            int r = i % n;
            while (!flags[r] && r > 0) {
                flags[r] = true;
                sb.append(i);
                r = (r * 10 + i) % n;
            }
            if (r == 0 && (ans == null || ans.length() > sb.length())) {
                ans = sb.toString();
            }
        }
        return ans;
    }
    String find2() {
        String ans = null;
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j <= 9; j++) {
                Queue<Integer> queue = new LinkedList<Integer>();
                String[] rmdValues = new String[n];
                if (i > 0) {
                    queue.offer(i);
                    rmdValues[i] = "" + (char)('0' + i);
                }
                queue.offer(j);
                rmdValues[j] = "" + (char)('0' + j);
                while (!queue.isEmpty()) {
                    int rmd = queue.poll();    
                    String value = rmdValues[rmd];
                    if (ans != null &&
                            (value.length() > ans.length() 
                             || (value.length() == ans.length() && value.compareTo(ans) > 0))) {
                        break;
                    }
                    if (rmd == 0) {ans = value; break;}
                    int[] dirs = new int[] {i, j};
                    for (int k : dirs) {
                        int nrmd = (rmd * 10 + k) % n;
                        if (rmdValues[nrmd] != null) {continue;}
                        rmdValues[nrmd] = value + (char)('0' + k);
                        queue.add(nrmd);
                    }
                }
            }
        }
        return ans;
    }
}
