import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Main().solve();   
    }
    int N, M;
    int[] COSTS;
    void solve() {
        Scanner cin = new Scanner(System.in);
        for (int T = cin.nextInt(); T > 0; T--) {
            N = cin.nextInt();
            COSTS = new int[N];
            for (int i = 0; i < N; i++) {
                COSTS[i] = cin.nextInt();
            }
            dp();
        }
    }
    void dp() {
        int costSum = 0;
        for (int c : COSTS) {
            costSum += c;
        }
        M = costSum / 2;
        int[] status = new int[M + 1];
        for (int c : COSTS) {
            zeroOnePack(status, c);
        }
        System.out.println(costSum - 2 * status[M]);
    }
    void zeroOnePack(int[] status, int c) {
        for (int m = M; m >= c; m--) {
            status[m] = Math.max(status[m], status[m - c] + c);
        }
    }
}

