import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T =Integer.parseInt(br.readLine());

        for(int tc=0;tc<T;tc++) {
            List<String[]> list = new ArrayList<>();
            list.add(br.readLine().split(" "));

            Map<String, String> map = new HashMap<>();;

            String str;
            while (!(str = br.readLine()).equals("what does the fox say?")) {

                String[] tmp = str.split(" ");
                map.put(tmp[0], tmp[2]);
            }



            for (int i = 0; i < list.size(); i++) {
                for (int k = 0; k < list.get(i).length; k++) {
                    for (Map.Entry<String, String> tmp : map.entrySet()) {
                        if (list.get(i)[k].equals(tmp.getValue())) {
                            list.get(i)[k] = "";
                        }
                    }
                }
                for (int k = 0; k < list.get(i).length; k++) {
                    if (list.get(i)[k] == "") continue;
                    else sb.append(list.get(i)[k]).append(" ");
                }
                sb.append('\n');
            }
        }

        System.out.println(sb.toString().trim());

    }
}
