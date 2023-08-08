import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static PriorityQueue<Integer> ascPq, descPq;
    private static Map<Integer, Integer> hm;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            ascPq = new PriorityQueue<>();
            descPq = new PriorityQueue<>(Collections.reverseOrder());
            hm = new HashMap<>();

            int num = Integer.parseInt(br.readLine());
            cnt = 0;

            while (num-- > 0) {
                st = new StringTokenizer(br.readLine(), " ");
                char c = st.nextToken().charAt(0);
                int number = Integer.parseInt(st.nextToken());

                switch (c) {
                    case 'I':
                        cnt++;
                        insert(number);
                        break;
                    case 'D':
                        if (cnt == 0) {
                            clear();
                        } else {
                            cnt--;
                            if (number == 1) {
                                if(maxNum()){
                                    descPq.poll();
                                }
                            } else if(number == -1) {
                               if(minNum()){
                                   ascPq.poll();
                               }
                            }
                        }
                        break;
                }
            }

            if (cnt == 0 ) sb.append("EMPTY").append('\n');
            else {
               if(ascPq.isEmpty()){
                   sb.append(descGetMax()).append(" ").append(descGetMin()).append('\n');
               }else if(descPq.isEmpty()){
                    int minNum = ascGetMin();
                    sb.append(ascGetMax()).append(" ").append(minNum).append('\n');
               }else{
                   boolean descFlag = maxxxxxxxxxxxx() , ascFlag = mmmmmmmmmmmin() ;
                    int max ,min;
                    if(descFlag && ascFlag){
                        max = descGetMax();
                        min = ascGetMin();
                        sb.append(max).append(" ").append(min).append('\n');
                    }else if(!descFlag){
                        min = ascGetMin();
                        max = ascGetMax();
                        sb.append(max).append(" ").append(min).append('\n');
                    }else {
                        max = descGetMax();
                        min = descGetMin();
                        sb.append(max).append(" ").append(min).append('\n');
                    }
               }
            }
        }

        System.out.println(sb);

    }

    private static void insert(int number) {
        ascPq.offer(number);
        descPq.offer(number);
        hm.put(number, hm.getOrDefault(number, 0) + 1);
    }

    private static void clear() {
        ascPq.clear();
        descPq.clear();
        hm.clear();
    }


    private static boolean maxNum() {

        while (!descPq.isEmpty()) {
            int curr = descPq.peek();
            if (hm.get(curr) >= 1) {
                hm.put(curr, hm.get(curr) - 1);
                return true;
            } else {
                descPq.poll();
            }
        }
        return false;
    }

    private static boolean minNum() {

        while (!ascPq.isEmpty()) {
            int curr = ascPq.peek();
            if (hm.get(curr) >= 1) {
                hm.put(curr, hm.get(curr) - 1);
                return true;
            } else {
                ascPq.poll();
            }
        }
        return false;
    }

    private static boolean mmmmmmmmmmmin(){
        while (!ascPq.isEmpty()) {
            int curr = ascPq.peek();
            if (hm.get(curr) >= 1) {
                return true;
            } else {
                ascPq.poll();
            }
        }
        return false;
    }

    private static boolean maxxxxxxxxxxxx(){
        while (!descPq.isEmpty()) {
            int curr = descPq.peek();
            if (hm.get(curr) >= 1) {
                return true;
            } else {
                descPq.poll();
            }
        }
        return false;
    }


    private static Integer descGetMax(){
        int num = 0;
        while (!descPq.isEmpty()) {
            int curr = descPq.peek();
            if ( hm.get(curr) >= 1) {
                num = curr;
                return num;
            } else {
                descPq.poll();
            }
        }
        return num;
    }

    private static Integer ascGetMin() {
        int num = 0;
        while (!ascPq.isEmpty()) {
            int curr = ascPq.peek();
            if (hm.get(curr) >= 1) {
                num = curr;
                return num;
            } else {
                ascPq.poll();
            }
        }
        return num;
    }

    private static Integer ascGetMax(){
        int num = 0;
        while (!ascPq.isEmpty()) {
            int curr = ascPq.poll();
            if ( hm.get(curr) >= 1) {
                num = curr;
            } else {
                ascPq.poll();
            }
        }
        return num;
    }

    private static Integer descGetMin(){
        int num = 0;
        while (!descPq.isEmpty()) {
            int curr = descPq.poll();
            if ( hm.get(curr) >= 1) {
                num = curr;
            } else {
                descPq.poll();
            }
        }
        return num;
    }

}