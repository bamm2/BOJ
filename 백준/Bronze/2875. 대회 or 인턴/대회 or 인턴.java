import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");

        int girlCount = Integer.parseInt(st.nextToken());
        int boyCount = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int makeTeam = Math.min(girlCount / 2, boyCount);
        int remain = girlCount - makeTeam * 2 + boyCount - makeTeam;

        if (k > remain) {
            k -= remain;
            while (k > 0) {
                k -= 3;
                makeTeam--;
            }
        }
        System.out.println(makeTeam);

        br.close();

    }
}