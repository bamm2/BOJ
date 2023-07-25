import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Number {
        int cnt,firstIdx;

        Number(int cnt, int firstIdx){
            this.cnt=cnt;
            this.firstIdx=firstIdx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        Map<Integer, Number> hm  = new HashMap<>();

        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<N;i++){
            int num =Integer.parseInt(st.nextToken());
            if(!hm.containsKey(num)){
                hm.put(num,new Number(1,i));
            }else{
                Number number = hm.get(num);
                number.cnt++;
                hm.put(num,number);
            }
        }

        List<Integer> list =new ArrayList<>(hm.keySet());

        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(hm.get(o2).cnt == hm.get(o1).cnt){
                    return hm.get(o1).firstIdx - hm.get(o2).firstIdx;
                }else{
                    return hm.get(o2).cnt-hm.get(o1).cnt;
                }
            }
        });

        StringBuilder sb =new StringBuilder();
        for(int key : list){
            int value = hm.get(key).cnt;
            while(value-->0){
                sb.append(key).append(" ");
            }
        }

        System.out.println(sb);

    }
}