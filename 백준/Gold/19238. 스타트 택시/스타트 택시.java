import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, map[][];
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static boolean[][] customerVisited;

    static class Taxi {
        int r, c, fuel;

        public Taxi(int r, int c, int fuel) {
            this.r = r;
            this.c = c;
            this.fuel = fuel;
        }
    }

    static class Customer {
        int r, c, goalR, goalC, fromTaxiDistance;

        public Customer(int r, int c, int goalR, int goalC, int fromTaxiDistance) {
            this.r = r;
            this.c = c;
            this.goalR = goalR;
            this.goalC = goalC;
            this.fromTaxiDistance = fromTaxiDistance;
        }
    }


    static List<Customer> list = new ArrayList<>();
    static Taxi taxi;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int fuel = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        customerVisited = new boolean[N][N];

        st = new StringTokenizer(br.readLine(), " ");
        taxi = new Taxi(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, fuel);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int sr = Integer.parseInt(st.nextToken()) - 1;
            int sc = Integer.parseInt(st.nextToken()) - 1;
            int gr = Integer.parseInt(st.nextToken()) - 1;
            int gc = Integer.parseInt(st.nextToken()) - 1;
            customerVisited[sr][sc] = true;
            list.add(new Customer(sr, sc, gr, gc, 0));
        }

        System.out.println(solve());

    }

    private static int solve() {
        while (true) {
            if (list.size()==0) break;
            Customer customer = findCustomer();
            if (customer.r==-1) return -1;
            if (!driveDestination(customer)) return -1;
        }

        return taxi.fuel;
    }

    private static Customer findCustomer() { // 현재 택시 위치에서 최단거리 고객 찾기
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[0]==o2[0]) { // r이 같을 경우
                return Integer.compare(o1[1], o2[1]);
            }
            return Integer.compare(o1[0], o2[0]);
        });

        boolean[][] visited = new boolean[N][N];
        visited[taxi.r][taxi.c] = true;
        if (customerVisited[taxi.r][taxi.c]) pq.offer(new int[]{taxi.r, taxi.c});

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{taxi.r, taxi.c});

        int dist = 0; // 택시에서의 거리
        if (pq.isEmpty()) { // 시작 위치에 고객이 존재하지 않는다면
            while (!q.isEmpty()) {
                int size = q.size();
                dist++;
                while (size-- > 0) {
                    int[] curr = q.poll();
                    for (int d = 0; d < 4; d++) {
                        int nr = curr[0] + dir[d][0];
                        int nc = curr[1] + dir[d][1];
                        if (isOut(nr, nc) || visited[nr][nc] || map[nr][nc]==1) continue;
                        visited[nr][nc] = true;
                        q.offer(new int[]{nr, nc});
                        if (customerVisited[nr][nc]) pq.offer(new int[]{nr, nc});
                    }
                }
                if (!pq.isEmpty()) {
                    return searchCustomer(pq.peek()[0], pq.peek()[1], dist);
                }
            }
            return new Customer(-1, -1, -1, -1, -1);
        }
        return searchCustomer(pq.peek()[0], pq.peek()[1], dist);
    }

    private static Customer searchCustomer(int r, int c, int dist) {
        for (int i = 0; i < list.size(); i++) {
            Customer curr = list.get(i);
            if (curr.r==r && curr.c==c) {
                list.remove(curr);
                curr.fromTaxiDistance = dist;
                customerVisited[curr.r][curr.c]=false;
                return curr;
            }
        }
        return new Customer(-1, -1, -1, -1, -1);
    }

    private static boolean driveDestination(Customer customer) { // 승객 목적지까지 데려다주기
        if (taxi.fuel <= customer.fromTaxiDistance) return false;
        taxi.fuel -= customer.fromTaxiDistance;

        int destinationDistance = getDestinationDistance(customer);
        if (destinationDistance==-1 || taxi.fuel < destinationDistance) return false;
        taxi.fuel -= destinationDistance;
        taxi.fuel += (destinationDistance * 2);
        taxi.r=customer.goalR;
        taxi.c=customer.goalC;
        return true;
    }

    private static int getDestinationDistance(Customer customer) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        visited[customer.r][customer.c] = true;
        q.offer(new int[]{customer.r, customer.c});

        int dist = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-->0) {
                int[] curr = q.poll();
                if (curr[0]==customer.goalR && curr[1]==customer.goalC) {
                    return dist;
                }
                for (int d = 0; d < 4; d++) {
                    int nr = curr[0] + dir[d][0];
                    int nc = curr[1] + dir[d][1];
                    if (isOut(nr, nc) || visited[nr][nc] || map[nr][nc]==1) continue;
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                }
            }
            dist++;
        }

        return -1;
    }

    public static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= N;
    }
}