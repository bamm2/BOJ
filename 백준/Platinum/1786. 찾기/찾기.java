import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int[] table;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();

        String text = br.readLine();
        String findStr = br.readLine();
        makeTable(findStr);

        int count = 0;
        List<Integer> position = new ArrayList<>();

        int idx = 0;
        for (int i = 0; i < text.length(); i++) {

            while (idx > 0 && findStr.charAt(idx)!=text.charAt(i)) {
                idx = table[idx - 1];
            }

            if (findStr.charAt(idx) == text.charAt(i)){
                if(idx==findStr.length()-1){
                    count++;
                    position.add(i-idx+1);
                    idx = table[idx];
                }else{
                    idx++;
                }
            }
        }

        sb.append(count).append('\n');
        for(Integer pos : position){
            sb.append(pos).append('\n');
        }

        System.out.println(sb);

    }

    private static void makeTable(String pattern) {
        table = new int[pattern.length()];

        int idx = 0;
        for (int i = 1; i < pattern.length(); i++) {

            while (idx > 0 && pattern.charAt(i)!=pattern.charAt(idx)) {
                idx = table[idx - 1];
            }

            if (pattern.charAt(i)==pattern.charAt(idx)) {
                table[i] = ++idx;
            }

        }
    }

}