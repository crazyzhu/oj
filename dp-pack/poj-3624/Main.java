import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Main().solve();
    }
    int N, M;
    int[] W, D;
    void solve() {
        Scanner cin = new Scanner(System.in);
        N = cin.nextInt(); M = cin.nextInt(); 
        W = new int[N]; D = new int[N];
        for (int i = 0; i < N; i++) {
            W[i] = cin.nextInt(); D[i] = cin.nextInt();
        }
        System.out.println(dp());
    }
    int dp() {
        int[] status = new int[M + 1];
        for (int i = 0; i < N; i++) {
            zeroOnePack(status, W[i], D[i]);
        }
        return status[M];
    }
    void zeroOnePack(int[] status, int w, int d) {
        for (int m = M; m >= w; m--) {
            status[m] = Math.max(status[m], status[m - w] + d);
        }
    }

}
