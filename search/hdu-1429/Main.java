import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt(), m = in.nextInt(), t = in.nextInt();
            char[][] data = new char[n][];
            for (int i = 0; i < n; i++) {
                data[i] = in.next().toCharArray();
            }
            System.out.println(solve(n, m, t, data));
        }
    }

    static class Node {
        int x, y;
        int state;
        Node(int x, int y, int state) {
            this.x = x;
            this.y = y;
            this.state = state;
        }
    }


    static int solve(int n, int m, int t, char[][] data) {
        int[][][] steps = new int[n][m][1 << 12];
        Queue<Node> q = new LinkedList<Node>();
        int initX = -1, initY = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(steps[i][j], -1);
                if (data[i][j] == '@') {
                    initX = i; initY = j;
                }
            }
        }
        steps[initX][initY][0] = 0;
        Node initNode = new Node(initX, initY, 0);
        q.offer(initNode);
        while (!q.isEmpty()) {
            Node node = q.poll();
            if (steps[node.x][node.y][node.state] >= t) {return -1;}
            if (data[node.x][node.y] == '^') {return steps[node.x][node.y][node.state];}
            int[][] dirs = new int[][] {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1}
            };
            for (int[] xy : dirs) {
                int x = node.x + xy[0];
                int y = node.y + xy[1];

                if (x < 0 || x >= n || y < 0 || y >= m || data[x][y] == '*') {continue;}
                if (data[x][y] >= 'A' && data[x][y] <= 'J' && (node.state & (1 << (data[x][y] - 'A' + 1))) == 0) {continue;}

                int newState = node.state;
                if (data[x][y] >= 'a' && data[x][y] <= 'j') {
                    newState = node.state | (1 << (data[x][y] - 'a' + 1));
                }
                if (steps[x][y][newState] != -1) {continue;}

                steps[x][y][newState] = steps[node.x][node.y][node.state] + 1;

                Node newNode = new Node(x, y, newState);
                q.offer(newNode);
            }
        }
        return -1;

    }
}
