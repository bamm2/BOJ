import java.io.*;
import java.util.*;

class Main {
	
	static int N,ans;
	static int[][] map;
	static HashSet<Integer> atSigns = new HashSet<>();
	static int[][] dir = {{0,0},{-1,0},{1,0},{0,1},{0,-1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st=new StringTokenizer(br.readLine()," ");
		N=Integer.parseInt(st.nextToken());
		int K =Integer.parseInt(st.nextToken());
		
		map=new int[N][N];
		
		for(int i = 0; i < N; ++i){
			st=new StringTokenizer(br.readLine()," ");
			for(int j = 0; j < N; ++j){
					char curr = st.nextToken().charAt(0);
				if(curr == '@') { // 2 증가 해야 하는 위치 
					atSigns.add(i*N+j);
				}else if(curr == '#'){ // 증가할 수 없는 위치 
					map[i][j] = -1;
				}
			}
		}
		
		ans = 0;
		
		for(int i=0; i < K ; ++i){
			st=new StringTokenizer(br.readLine()," ");
			int r= Integer.parseInt(st.nextToken())-1;
			int c= Integer.parseInt(st.nextToken())-1;
			setBomb(r,c);
		}
		
		System.out.println(ans);
	}
	
	private static void setBomb(int r,int c){
		for(int d=0;d<5; ++d){
			int nr = r+dir[d][0];
			int nc = c+dir[d][1];
			if(isOut(nr,nc) || map[nr][nc] == -1) continue;
			if(atSigns.contains(nr*N+nc)) {
				map[nr][nc]+=2;
			}else{
				++map[nr][nc];
			}
			ans = Math.max(ans,map[nr][nc]);
		} 
	}
	
	private static boolean isOut(int r,int c){
		return r<0 || c<0 || r>=N || c>=N;
	}
}