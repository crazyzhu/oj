import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt();
        int[] cost = new int[n];
        for (int i = 0; i < n; i++) {
            cost[i] = in.nextInt();
        }
        System.out.println(solve(n, m, cost));
    }

    static class MonoQueue<T extends Comparable<T>> {
        Deque<T> data = new ArrayDeque<T>(), aux = new ArrayDeque<T>();
        void offer(T e) {
            data.offerLast(e);
            while (!aux.isEmpty() && aux.peekLast().compareTo(e) > 0) {
                aux.pollLast();
            }
            aux.offerLast(e);
        }

        T poll() {
            T e = data.pollFirst();
            if (e == aux.peekFirst()) {aux.pollFirst();}
            return e;
        }

        T min() {
            return aux.peekFirst(); 
        }
    }

    static int solve(int n, int m, int[] cost) {
        int[] f = new int[n];
        MonoQueue<Integer> mq = new MonoQueue<Integer>();
        for (int i = 0; i < n; i++) {
            if (i < m) {
                f[i] = cost[i];
                mq.offer(f[i]);
            } else {
                f[i] = mq.min() + cost[i];
                mq.poll();mq.offer(f[i]);
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = Math.max(0, n - m); i < n; i++) {
            ans = Math.min(f[i], ans);
        }
        return ans;
    }
}
