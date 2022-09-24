import java.io.*;
import java.util.*;

public class Main {
    static int N,cnt ;
    static ArrayList<Integer> list =new ArrayList<>();
    static int[][] map;
    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        cnt=1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {
                    map[i][j]=0;
                    dfs(i, j);
                    list.add(cnt);
                    cnt=1;
                }
            }
        }

        Collections.sort(list);

        bw.write(list.size()+"\n");
        for(int i=0;i<list.size();i++){
            bw.write(list.get(i)+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int r, int c) {
        for(int d=0;d<4;d++){
            int nr = r +dir[d][0];
            int nc = c+ dir[d][1];
            if(nr<0 || nc <0 || nr>=N || nc >= N) continue;
            if(map[nr][nc]!=1) continue;
            map[nr][nc]=0;
            cnt++;
            dfs(nr,nc);
        }
    }
}
