import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Main().solve();
    }
    double[] probs;
    int[] vols;
    double limit;
    int n;
    void solve() {
        Scanner cin = new Scanner(System.in);
        for (int t = cin.nextInt(); t > 0; t--) {
            this.limit = 1 - cin.nextDouble();
            this.n = cin.nextInt();
            this.probs = new double[n];
            this.vols = new int[n];
            for (int i = 0; i < n; i++) {
                this.vols[i] = cin.nextInt();
                this.probs[i] = 1 - cin.nextDouble();
            }
            dp();
        }
    }
    void dp() {
        int sumVol = 0;
        for (int i = 0; i < n; i++) {
            sumVol += this.vols[i];
        }
        double[] status = new double[sumVol + 1];
        status[0] = 1D;
        for (int i = 0; i < n; i++) {
            zeroOnePack(status, this.vols[i], this.probs[i]);
        }
        for (int v = sumVol; v >= 0; v--) {
            if (status[v] > this.limit) {
                System.out.println(v);
                return;
            }
        }
    }
    void zeroOnePack(double[] status, int v, double prob) {
        for (int iv = status.length - 1; iv >= v; iv--) {
            status[iv] = Math.max(status[iv], status[iv - v] * prob);
        }
    }
}
