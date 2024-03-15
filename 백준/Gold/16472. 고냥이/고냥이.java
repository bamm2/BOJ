import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        char[] arr = br.readLine().toCharArray();

        int[] count = new int[26];
        Queue<Character> q = new ArrayDeque<>();

        int ans = -1;
        int choice = 0; // 선택한 알파벳 갯수

        for (int i = 0; i < arr.length; i++) {
            char curr = arr[i]; // 확인할 알파벳
            int alphabetIdx = arr[i] - 'a';
            if (count[alphabetIdx]==0) { // 아직 선택되지 않은 알파벳인 경우
                if (choice + 1 > N) { // 선택시, 선택 가능한 알파벳의 갯수 초과 시
                    while (!q.isEmpty()) { // 알파벳 하나를 온전히 지울 때까지 반복
                        Character poll = q.poll();
                        int pollAlphabetIdx = poll - 'a';
                        count[pollAlphabetIdx]--;
                        if (count[pollAlphabetIdx]==0) break;
                    }
                } else { // 선택 가능
                    choice++;
                }
            }
            count[alphabetIdx]++;
            q.offer(curr);
            ans = Math.max(ans, q.size());
        }

        System.out.println(ans);
        br.close();
    }
}