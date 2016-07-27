import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Main().solve();
    }
    int N, V;
    int[] VALS, VOLS;
    void solve() {
        Scanner cin = new Scanner(System.in);
        for(int T = cin.nextInt(); T > 0; T--) {
            N = cin.nextInt(); V = cin.nextInt();
            VALS = new int[N]; VOLS = new int[N];
            for (int i = 0; i < N; i++) {
                VALS[i] = cin.nextInt();
            }
            for (int i = 0; i < N; i++) {
                VOLS[i] = cin.nextInt();
            }
            System.out.println(dp());
        }
    }
    int dp() {
        int[] status = new int[V + 1];
        for (int i = 0; i < N; i++) {
            onePack(status, VALS[i], VOLS[i]);
        }
        return status[V];
    }
    void onePack(int[] status, int val, int vol) {
        for (int v = V; v >= vol; v--) {
            status[v] = Math.max(status[v], status[v - vol] + val);
        }
    }
}
