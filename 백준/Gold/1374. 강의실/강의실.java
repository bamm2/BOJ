import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Room {
        int start, end;

        public Room(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Room[] rooms = new Room[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            st.nextToken();
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            rooms[i] = new Room(start, end);
        }

        Arrays.sort(rooms, (o1, o2) -> o1.start!=o2.start ? Integer.compare(o1.start, o2.start):Integer.compare(o1.end, o2.end));

        PriorityQueue<Room> pq = new PriorityQueue<>(
                (o1, o2) -> o1.end!=o2.end ? Integer.compare(o1.end, o2.end)
                        :Integer.compare(o1.start, o2.start));

        int ans = 0;
        for (Room room : rooms) {
            if (pq.isEmpty()) pq.offer(room);
            else {
                if (room.start >= pq.peek().end) {
                    while (!pq.isEmpty() && room.start >= pq.peek().end) {
                        pq.poll();
                    }
                }
                pq.offer(room);
            }
            ans = Math.max(ans, pq.size());
        }

        System.out.println(ans);
        br.close();
    }
}