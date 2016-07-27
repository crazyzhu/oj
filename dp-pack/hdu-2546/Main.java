import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Main().solve();    
    }
    int N, M;
    int[] COSTS;
    void solve() {
        Scanner cin = new Scanner(System.in);
        while ((N = cin.nextInt()) != 0) {
            COSTS = new int[N];
            for (int i = 0; i < N; i++) {
                COSTS[i] = cin.nextInt();
            }
            M = cin.nextInt();
            System.out.println(dp());
        }
    }
    int dp() {
        if (M < 5) {return M;}
        Arrays.sort(COSTS);
        // solve v = m - 5, n = n - 1 problem
        int[] status = new int[M - 5 + 1];
        for (int i = 0; i < N - 1; i++) {
            zeroOnePack(status, COSTS[i], COSTS[i]);
        }
        return M - status[M - 5] - COSTS[N - 1];
    }
    void zeroOnePack(int[] status, int c, int v) {
        for (int m = M - 5; m >= c; m--) {
            status[m] = Math.max(status[m], status[m - c] + v);
        }
    }


}
