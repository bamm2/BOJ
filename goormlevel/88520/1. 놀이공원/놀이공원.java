import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
	
	public static void testCase(int caseIndex) throws IOException {
		StringTokenizer st =new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken()); // 지도의 크기 
		int K = Integer.parseInt(st.nextToken()); // 놀이공원의 크기
		
		int[][] map = new int[N+1][N+1]; // 각 칸의 쓰레기 존재 여부 
		for (int r = 1; r <= N; ++r) {
			st =new StringTokenizer(br.readLine()," ");
			for (int c = 1; c <= N; ++c) {
				map[r][c] = Integer.parseInt(st.nextToken()) + map[r][c-1];
			}
		}
	
		int	answer = Integer.MAX_VALUE;
		
		loop:
		for(int r=1,len = N-K+1; r <= len; ++r){
			for(int c=K; c<=N; ++c){
				int sum  = 0;
				for(int plus = 0; plus < K; ++plus){
					sum+= (map[r+plus][c] - map[r+plus][c-K]);
				}
				answer = Math.min(answer,sum);
				if(answer==0) break loop;
			}
		}
		
		System.out.println(answer);
	}
	
	public static void main(String[] args) throws Exception {
		int caseSize = Integer.parseInt(br.readLine());
		
		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}
		
	}
	
}