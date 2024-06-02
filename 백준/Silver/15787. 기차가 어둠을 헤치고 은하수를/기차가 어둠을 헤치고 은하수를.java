import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static boolean[][] trains;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        trains = new boolean[N][20];

        int commandNumber, trainNumber, seatNumber;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            commandNumber = Integer.parseInt(st.nextToken());
            trainNumber = Integer.parseInt(st.nextToken()) - 1;
            if (commandNumber==1 || commandNumber==2) seatNumber = Integer.parseInt(st.nextToken())-1;
            else seatNumber = -1;
            command(commandNumber, trainNumber, seatNumber);
        }

        HashSet<String> hs = new HashSet<>();
        for (int i = 0; i < N; i++) {
            hs.add(convert(i));
        }

        System.out.println(hs.size());
        br.close();
    }

    private static void command(int commandNumber, int trainNumber, int seatNumber) {
        switch (commandNumber) {
            case 1:
                take(trainNumber, seatNumber);
                break;
            case 2:
                takeDown(trainNumber, seatNumber);
                break;
            case 3:
                backOneStep(trainNumber);
                break;
            case 4:
                goOneStep(trainNumber);
                break;
        }
    }

    private static void take(int trainNumber, int seatNumber) {
        trains[trainNumber][seatNumber] = true;
    }

    private static void takeDown(int trainNumber, int seatNumber) {
        trains[trainNumber][seatNumber] = false;
    }

    private static void backOneStep(int trainNumber) {
        for (int i = 19; i > 0; i--) {
            trains[trainNumber][i] = trains[trainNumber][i - 1];
        }
        trains[trainNumber][0] = false;
    }

    private static void goOneStep(int trainNumber) {
        for (int i = 0; i < 19; i++) {
            trains[trainNumber][i] = trains[trainNumber][i + 1];
        }
        trains[trainNumber][19] = false;
    }

    private static String convert(int trainNumber) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            if (trains[trainNumber][i]) str.append("1");
            else str.append("0");
        }
        return str.toString();
    }

}