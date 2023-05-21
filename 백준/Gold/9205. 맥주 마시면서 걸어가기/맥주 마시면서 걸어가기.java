import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Conven {
        int r, c;
        Conven(int r,int c){
            this.r=r;
            this.c=c;
        }
    }

    static Conven[] conven;
    static int sangR,sangC,goalR,goalC,N;
    static String ans;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
       BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
       StringBuilder sb =new StringBuilder();
       StringTokenizer st;

        int T =Integer.parseInt(br.readLine()); // 테스트 케이스의 수

         while(T-->0){
            N=Integer.parseInt(br.readLine());
            conven=new Conven[N];
            visited=new boolean[N];
            ans="";

            st=new StringTokenizer(br.readLine()," ");
            sangR=Integer.parseInt(st.nextToken());
            sangC=Integer.parseInt(st.nextToken());

            for(int i=0;i<N;i++){
                st=new StringTokenizer(br.readLine()," ");
                conven[i]=new Conven(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            }

            st=new StringTokenizer(br.readLine()," ");
            goalR=Integer.parseInt(st.nextToken());
            goalC=Integer.parseInt(st.nextToken());

            solve(sangR,sangC);

            if(ans.equals("happy")) sb.append(ans).append('\n');
            else sb.append("sad").append('\n');
        }

        System.out.println(sb.toString().trim());
    }

    private static void solve(int r,int c) {
        Queue<Conven> q = new ArrayDeque<>();
        q.offer(new Conven(r,c));

        while(!q.isEmpty()){
            Conven curr =q.poll();

            if( Math.abs(goalR-curr.r)+ Math.abs(goalC-curr.c) <= 1000){
                ans="happy";
                break;
            }

            for(int i=0;i<N;i++){
                if(visited[i]) continue;

                Conven next = conven[i];

                if( Math.abs(next.r-curr.r) + Math.abs(next.c-curr.c) <=1000 ){
                    visited[i]=true;
                    q.offer(new Conven(next.r,next.c));
                }
            }
        }

    }
}