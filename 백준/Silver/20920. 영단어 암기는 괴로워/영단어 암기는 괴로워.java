import javafx.geometry.Pos;

import java.io.*;
import java.util.*;

public class Main {



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            if (s.length() >= M) {
                if (!hm.containsKey(s)) {
                    hm.put(s, 1);
                } else {
                    int keyNum = hm.get(s) + 1;
                    hm.remove(s);
                    hm.put(s, keyNum);
                }
            }
        }

        List<String> list = new ArrayList<>(hm.keySet());

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(hm.get(o1)==hm.get(o2)){ // value가 동일하면서
                    if(o1.length()==o2.length()) { // 길이가 동일하면
                        return o1.compareTo(o2); //사전 순으로 정렬
                    } // value는 동일하지만 길이가 동일하지 않으면
                    return o2.length()-o1.length(); // 길이 내림차순
                } // value가 동일하지 않으면 내림차순
                return hm.get(o2)-hm.get(o1);
            }
        });

        for(int i=0,size=list.size();i<size;i++){
            sb.append(list.get(i)).append('\n');
        }
        System.out.println(sb);


    }
}