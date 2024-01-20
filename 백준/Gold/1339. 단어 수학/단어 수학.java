import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    static int N, ans;
    static String[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new String[N];

        Map<Character, Integer> hm = new HashMap<>();

        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
            int pos = 1;
            for (int j = arr[i].length() - 1; j >= 0; j--) {
                char c = arr[i].charAt(j);
                hm.put(c, hm.getOrDefault(c, 0) + pos);
                pos *= 10;
            }
        }

        List<Character> keyList = new ArrayList<>(hm.keySet());
        keyList.sort((o1, o2) -> Integer.compare(hm.get(o2), hm.get(o1)));

        int[] score = new int[26];
        int value = 9;
        for (int i = 0; i < keyList.size(); i++) {
            score[keyList.get(i) - 'A'] = value--;
        }

        ans = 0;
        for (int i = 0; i < N; i++) {
            String cur = arr[i];
            int num = 0;
            for (int j = 0; j < cur.length(); j++) {
                num = num * 10 + score[cur.charAt(j) - 'A'];
            }
            ans += num;
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}