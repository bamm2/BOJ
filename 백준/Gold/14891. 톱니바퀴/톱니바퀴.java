import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<List<Integer>> gearList;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        gearList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            String s = br.readLine();
            List<Integer> tmp = new ArrayList<>();
            for (int j = 0; j < s.length(); j++) {
                tmp.add(s.charAt(j) - '0');
            }
            gearList.add(tmp);
        }

        int K = Integer.parseInt(br.readLine());
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            visited = new boolean[4];
            int gearNum = Integer.parseInt(st.nextToken())-1;
            int dir = Integer.parseInt(st.nextToken());
            visited[gearNum] = true;
            turn(gearNum, dir);
        }

        System.out.println(sum());

    }

    private static void turn(int gearNum, int dir) {
        Integer rightGear = gearList.get(gearNum).get(2);
        Integer leftGear = gearList.get(gearNum).get(6);

        if (!isOut(gearNum - 1) && !visited[gearNum - 1]
                && gearList.get(gearNum - 1).get(2) != leftGear) {
            visited[gearNum - 1] = true;
            turn(gearNum - 1, -dir);
        }
        if (!isOut(gearNum + 1) && !visited[gearNum + 1]
                && gearList.get(gearNum + 1).get(6) != rightGear) {
            visited[gearNum + 1] = true;
            turn(gearNum + 1, -dir);
        }

        List<Integer> turnedGear = move(gearNum, dir);
        gearList.remove(gearNum);
        gearList.add(gearNum,turnedGear);
    }

    private static List<Integer> move(int gearNum, int dir) {
        List<Integer> tmp = new ArrayList<>();
        List<Integer> list = gearList.get(gearNum);
        if (dir == 1) {
            for (int i = 0; i < list.size(); i++) {
                tmp.add(list.get((i + 7) % 8));
            }
        }else{
            for (int i = 0; i < list.size(); i++) {
                tmp.add(list.get((i + 1) % 8));
            }
        }
        return tmp;
    }

    private static int sum(){
        int sum = 0;
        for(int i=0;i< gearList.size();i++){
            Integer num = gearList.get(i).get(0);
            if(num==1) sum+=(int)Math.pow(2,i);
        }
        return sum;
    }


    private static boolean isOut(int num) {
        return num < 0 || num >= 4;
    }
}