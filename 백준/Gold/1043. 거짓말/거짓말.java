import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int V,E,ans;
    static boolean[] visited;
    static List<Integer>[] list;
    static List<List<Integer>> question;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        V=Integer.parseInt(st.nextToken());
        E=Integer.parseInt(st.nextToken());

        st=new StringTokenizer(br.readLine()," ");
        int num =Integer.parseInt(st.nextToken());
        visited=new boolean[V+1];
        for(int i=0;i<num;i++){
            int number = Integer.parseInt(st.nextToken());
            visited[number]=true;
        }

        list=new ArrayList[V+1];
        for(int i=1;i<=V;i++){
            list[i]=new ArrayList<>();
        }

        question=new ArrayList<>(new ArrayList<>());

        for(int i=0;i<E;i++){
            st=new StringTokenizer(br.readLine()," ");
            int nums = Integer.parseInt(st.nextToken());
            List<Integer> tmp =new ArrayList<>();
            for(int j=0;j<nums;j++){
                int number =Integer.parseInt(st.nextToken());
                tmp.add(number);
            }
            makeGraph(tmp);
            question.add(tmp);
        }

        Queue<Integer> q =new ArrayDeque<>();
        for(int i=1;i<=V;i++){
            if(!visited[i]) continue;
            q.offer(i);
        }
        while (!q.isEmpty()){
            Integer curr =q.poll();
            for(Integer next : list[curr]){
                if(visited[next]) continue;
                q.offer(next);
                visited[next]=true;
            }
        }


        for(int i=0;i< question.size();i++){
            List<Integer> test = question.get(i);
            boolean flag =false;
            for(int j=0;j<test.size();j++){
                Integer curr = test.get(j);
                if(visited[curr]){
                    flag=true;
                    break;
                }
            }
            if(!flag) ans++;
        }

        System.out.println(ans);
    }

    private static void makeGraph(List<Integer> tmp){
        for (int i = 0; i < tmp.size()-1; i++) {
            int from = tmp.get(i);
            for(int j=i+1;j<tmp.size();j++){
                int to = tmp.get(j);
                list[from].add(to);
                list[to].add(from);
            }
        }
    }

}