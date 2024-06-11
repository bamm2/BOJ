import java.io.*;
import java.util.*;

class Main {
	
	static int N,GSR,GSC,PSR,PSC;
	static boolean[][][] visited;
	static String[][] map;
	static int[][] members;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}}; //상 우 하 좌 
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N=Integer.parseInt(br.readLine());
		visited= new boolean[2][N][N];
		members = new int[2][3];
		map =new String[N][N];
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		members[0][0] = Integer.parseInt(st.nextToken())-1;
		members[0][1] = Integer.parseInt(st.nextToken())-1;
		members[0][2] = 0;
		GSR = members[0][0]; GSC = members[0][1];
		st = new StringTokenizer(br.readLine()," ");
		members[1][0] = Integer.parseInt(st.nextToken())-1;
		members[1][1] = Integer.parseInt(st.nextToken())-1;
		members[1][2] = 0;
		PSR = members[1][0]; PSC = members[1][1];
		
		for(int i=0; i<N;i++){
			map[i] = br.readLine().split(" ");
		}
		
		while(go(0,members[0][0],members[0][1]));
		while(go(1,members[1][0],members[1][1]));
		
		if(!visited[0][GSR][GSC]) ++members[0][2];
		if(!visited[1][PSR][PSC]) ++members[1][2];
		
		if(members[0][2] > members[1][2]) {
			System.out.println("goorm "+ members[0][2]);
		}else{
			System.out.println("player "+ members[1][2]);
		}
	}
	
	private static boolean go(int memberIdx,int r,int c){
		int idx = map[r][c].length()-1; // 방향 문자 찾기 위해 문자의 idx  
		int moveCnt = Integer.parseInt(map[r][c].substring(0,idx));
		int d = getDirIdx(map[r][c].charAt(idx));
		
		do{
			--moveCnt;
			r = (r + dir[d][0]+N) % N;
			c = (c + dir[d][1]+N) % N;
			if(visited[memberIdx][r][c]) return false;
			++members[memberIdx][2];
			visited[memberIdx][r][c]=true;
			
		}while(moveCnt!=0);

		members[memberIdx][0] = r;
		members[memberIdx][1] = c;
		
		return true;
	}
	
	private static int getDirIdx(char c){
		switch(c){
			case 'U':
					return 0;
			case 'R':
				return 1;
			case 'D':
				return 2;
			case 'L':
				return 3;
		}
		return -1;
	}
	
}