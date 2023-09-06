import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static char[] arr = {'w', 'o', 'l', 'f'};
    static String s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean flag = true;
        s = br.readLine();

        Map<Character, Integer> map = new HashMap<>();

        init(map);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(i==0 && c!='w'){
                flag=false;
                break;
            }
            if (c == 'w') {
                Integer w = map.get('w');
                if (w < map.get('o') || w < map.get('l') || w < map.get('f') || map.get('o')!=map.get('l') || map.get('o')!=map.get('f')) {
                    flag = false;
                    break;
                }
                map.put(c, map.getOrDefault(c, 0) + 1);
            } else if (c == 'o') {
                Integer o = map.get('o');
                if (map.get('w') <= o || o < map.get('l') || o < map.get('f')) {
                    flag = false;
                    break;
                }
                map.put(c, map.getOrDefault(c, 0) + 1);
            } else if (c == 'l') {
                if (map.get('w') != map.get('o') || map.get('w') <= map.get('l') || map.get('o')<=map.get('l') || map.get('l') < map.get('f')) {
                    flag = false;
                    break;
                }
                map.put(c, map.getOrDefault(c, 0) + 1);
            } else if (c == 'f') {
                if (map.get('w') != map.get('o') || map.get('o') != map.get('l') || map.get('l') <= map.get('f')) {
                    flag = false;
                    break;
                }
                map.put(c, map.getOrDefault(c, 0) + 1);
            } else {
                flag = false;
                break;
            }
        }
        if(s.charAt(s.length()-1)!='f') flag=false;
        int cnt = map.get('w');
        if (map.get('o') != cnt || map.get('l') != cnt || map.get('f') != cnt) flag = false;

        if (!flag) System.out.println(0);
        else System.out.println(1);

    }

    private static void init(Map<Character, Integer> map) {
        map.put('w', 0);
        map.put('o', 0);
        map.put('l', 0);
        map.put('f', 0);
    }
}