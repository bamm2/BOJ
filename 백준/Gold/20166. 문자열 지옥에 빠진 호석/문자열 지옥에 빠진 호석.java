import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int R,C,goalLen;
    static char[][] map;
    static boolean[][] visited;
    static int[][] dir = { {-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
    static Map<String,Integer> hm =new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());
        int K =Integer.parseInt(st.nextToken());

        map=new char[R][C];
        visited=new boolean[R][C];

        for(int i=0;i<R;i++){
            String str = br.readLine();
            for(int j=0;j<C;j++){
                map[i][j]=str.charAt(j);
            }
        }

        for(int i=0;i<K;i++){
            String likeStr = br.readLine();
            goalLen=likeStr.length();
            hm.put(likeStr,0);
        }

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                solve(i,j,map[i][j]+"");
            }
        }

        for(Map.Entry<String,Integer> ety : hm.entrySet()){
            System.out.println(ety.getValue());
        }

    }

    private static void solve(int r, int c, String findStr) {

        if(goalLen==findStr.length()){
            if(hm.containsKey(findStr)){
                hm.put(findStr,hm.getOrDefault(findStr,0)+1);
            }
            return;
        }

        for(int d=0;d<dir.length;d++){
            int nr =(r+dir[d][0]+R)%R;
            int nc =(c+dir[d][1]+C)%C;
            if(visited[nr][nc]) continue;
            visited[nr][nc]=true;
            solve(nr,nc,findStr+map[nr][nc]);
            visited[nr][nc]=false;
        }
    }
}