import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(), m = in.nextInt();
        int[] v = new int[m], p = new int[m], f = new int[N+1];
        for (int i = 0; i < m; i++) {
            v[i] = in.nextInt();
            p[i] = in.nextInt();
            zeroOnePack(f, v[i], v[i] * p[i]);
        }
        int ans = -1;
        for (int i = 0; i <= N; i++) {
            ans = Math.max(ans, f[i]);
        }
        System.out.println(ans);
    }
    static void zeroOnePack(int[] f, int cost, int weight) {
        for (int n = f.length - 1; n >= cost; n--) {
            f[n] = Math.max(f[n], f[n - cost] + weight);
        }
    }
}
