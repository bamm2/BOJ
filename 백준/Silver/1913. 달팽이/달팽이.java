import java.io.*;


//1913 달팽이
public class Main {

    static int[][] dir ={{1,0},{0,1},{-1,0},{0,-1}};
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        int target = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];
        boolean[][] visited=new boolean[N][N];

        int size = N * N;
        int targetR = 0, targetC = 0;
        int r = 0, c = 0;
        int direct = 0;

        while(true){
            map[r][c]=size--;
            visited[r][c]=true;
            while(true){
                r+=dir[direct%4][0];
                c+=dir[direct%4][1];
                if(isOut(r,c) || visited[r][c]){
                    r-=dir[direct%4][0];
                    c-=dir[direct%4][1];
                    break;
                }
                map[r][c]=size--;
                visited[r][c]=true;
                if(size==0) break;
            }
            if(size==0) break;
            direct++;
            r+=dir[direct%4][0];
            c+=dir[direct%4][1];
        }

      for(int i=0;i<N;i++){
          for(int j=0;j<N;j++){
              bw.write(map[i][j]+" ");
              if(map[i][j]==target){
                  targetR=i+1;
                  targetC=j+1;
              }
          }
          bw.write('\n');
      }

      bw.write(targetR+" "+targetC+"\n");
      bw.flush();
      bw.close();
      br.close();
    }

    private static boolean isOut(int r,int c){
        return r<0 || c<0 || r>=N || c>=N ;
    }
}