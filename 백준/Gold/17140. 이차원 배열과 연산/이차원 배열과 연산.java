import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int findR,findC,findNum;
    static int[][] map;
    static class Number implements Comparable<Number>{
        int num, cnt;
        Number(int num,int cnt){
            this.num=num;
            this.cnt=cnt;
        }
        @Override
        public int compareTo(Number o) {
            if(this.cnt==o.cnt){
                return Integer.compare(this.num,o.num);
            }else{
                return Integer.compare(this.cnt,o.cnt);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st=new StringTokenizer(br.readLine()," ");
        findR=Integer.parseInt(st.nextToken())-1;
        findC=Integer.parseInt(st.nextToken())-1;
        findNum=Integer.parseInt(st.nextToken());

        map=new int[3][3];

        for(int i=0;i<3;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<3;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        int time =0;
        while(true) {
            if(findR<map.length && findC<map[0].length && map[findR][findC]==findNum) break;
            if(time==101) break;
            time++;
            task();
        }
        if(time==101) time =-1;

        System.out.println(time);
    }

    private static void task() {
        int rLen = map.length;
        int cLen = map[0].length;
        if(rLen>=cLen){
            rTask();
        }else{
            cTask();
        }

    }

    private static void rTask() {
        List<Number>[] list =new ArrayList[map.length];
        for(int i=0;i<map.length;i++){
            list[i]=new ArrayList<>();
        }

        int c = map[0].length;

        int max = 0;
        Map<Integer,Integer> hm ;
        for(int i=0;i<map.length;i++){
            hm =new HashMap<>();
            int cnt = 0;
            for(int j=0;j<c;j++){
                if(map[i][j]==0) continue;
                hm.put(map[i][j],hm.getOrDefault(map[i][j],0)+1);
            }
            for(Map.Entry<Integer,Integer> entry : hm.entrySet()){
                list[i].add(new Number(entry.getKey(),entry.getValue()));
                cnt++;
            }
            if(max<cnt) max=cnt;
            Collections.sort(list[i]);
        }

        settingC(max*2,list);

    }

    private static void settingC(int max, List<Number>[] list) {
        int[][] tmp =new int[map.length][max];

        for(int i=0;i<map.length;i++){
            int idx = 0;
            for(int j=0;j<list[i].size();j++){
                tmp[i][idx]=list[i].get(j).num;
                tmp[i][idx+1]=list[i].get(j).cnt;
                idx+=2;
                if(idx==100) break;
            }
            if(i==100) break;
        }
        map=tmp;
    }

    private static void cTask() {
        List<Number>[] list =new ArrayList[map[0].length];
        for(int i=0;i<list.length;i++){
            list[i]=new ArrayList<>();
        }

        int c = map[0].length;

        int max = 0;
        Map<Integer,Integer> hm ;
        for(int i=0;i<c;i++){
            hm =new HashMap<>();
            int cnt = 0;
            for(int j=0;j<map.length;j++){
                if(map[j][i]==0) continue;
                hm.put(map[j][i],hm.getOrDefault(map[j][i],0)+1);
            }
            for(Map.Entry<Integer,Integer> entry : hm.entrySet()){
                list[i].add(new Number(entry.getKey(),entry.getValue()));
                cnt++;
            }
            if(max<cnt) max=cnt;
            Collections.sort(list[i]);
        }

        settingR(max*2,list);

    }

    private static void settingR(int max, List<Number>[] list) {
        int[][] tmp =new int[max][map[0].length];

        for(int i=0;i<map[0].length;i++){
            int idx = 0;
            for(int j=0;j<list[i].size();j++){
                tmp[idx][i]=list[i].get(j).num;
                tmp[idx+1][i]=list[i].get(j).cnt;
                idx+=2;
                if(idx==100) break;
            }
            if(i==100) break;
        }
        map=tmp;
    }

}
