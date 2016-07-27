import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Main().solve(); 
    }
    int r, c;
    char[][] map;
    int[][][] steps;
    Queue<Node> queue;
    static class Node {
        int x, y, state;
        Node(int x, int y, int state) {this.x = x; this.y = y; this.state = state;}
    }
    int getIndex(char ch) {
        ch = Character.toLowerCase(ch);
        switch (ch) {
            case 'b': 
                return 1;
            case 'y':
                return 2;
            case 'r':
                return 3;
            case 'g':
                return 4;
        }
        throw new RuntimeException("Invalid ch:" + ch);
    }
    void solve() {
        Scanner cin = new Scanner(System.in);
        while (cin.hasNextInt()) {
            r = cin.nextInt(); c = cin.nextInt();
            if (r == 0 || c == 0) {continue;}
            map = new char[r][];
            steps = new int[r][c][1 << 5];
            queue = new LinkedList<Node>();
            for (int i = 0; i < r; i++) {
                map[i] = cin.next().toCharArray();
                for (int j = 0; j < c; j++) {
                    Arrays.fill(steps[i][j], -1);
                    if (map[i][j] == '*') {
                        Node initNode = new Node(i, j, 1);
                        queue.offer(initNode);
                        steps[i][j][1] = 0;
                    }
                }
            }
            int ans = bfs();
            if (ans == -1) {
                System.out.println("The poor student is trapped!");
            } else {
                System.out.println(String.format("Escape possible in %d steps.", ans));
            }
        }
    }
    int bfs() {
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int x = node.x, y = node.y, state = node.state;
            if (map[x][y] == 'X') {return steps[x][y][state];}
            int[][] dirs = new int[][] {
                {1, 0}, {-1, 0}, {0, 1}, {0, -1}
            };
            for (int[] delta : dirs) {
                int nx = x + delta[0], ny = y + delta[1];
                if (nx < 0 || nx >= r || ny < 0 || ny >= c || map[nx][ny] == '#') {continue;}
                if (Character.isUpperCase(map[nx][ny]) && map[nx][ny] != 'X' && (state & (1 << getIndex(map[nx][ny]))) == 0) {continue;}
                int newState = node.state;
                if (Character.isLowerCase(map[nx][ny])) {
                    newState = node.state | (1 << getIndex(map[nx][ny])); 
                }
                if (steps[nx][ny][newState] != -1) {continue;}
                steps[nx][ny][newState] = steps[x][y][state] + 1;
                Node newNode = new Node(nx, ny, newState);
                queue.offer(newNode);
            }
        }
        return -1;
    }
}
