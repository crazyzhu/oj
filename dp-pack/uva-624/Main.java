import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Main().solve();
    }
    int V, N;
    int[] COSTS;
    void solve() {
        Scanner cin = new Scanner(System.in);
        while (cin.hasNextInt()) {
            V = cin.nextInt(); N = cin.nextInt();
            COSTS = new int[N];
            for (int i = 0; i < N; i++) {
                COSTS[i] = cin.nextInt();
            }
            dp();
        }
    }
    void dp() {
        int[] status = new int[V + 1], choice = new int[V + 1];
        for (int i = 0; i < N; i++) {
            zeroOnePack(status, choice, COSTS[i]);
        }
        int sum = status[V];
        System.out.println(output(sum, choice) + "sum:" + sum);
    }
    void zeroOnePack(int[] status, int[] choice, int cost) {
        for (int v = V; v >= cost; v--) {
            if (status[v - cost] + cost > status[v]) {
                choice[v] = cost;
                status[v] = status[v - cost] + cost;
            }
        }
    }
    String output(int sum, int[] choice) {
        if (sum == 0) {
            return "";
        } else {
            return output(sum - choice[sum], choice) + choice[sum] + " ";
        }
    }
    
}
