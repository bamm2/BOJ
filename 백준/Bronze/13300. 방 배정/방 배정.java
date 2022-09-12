import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // 백준
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine()," ");
		int N =Integer.parseInt(st.nextToken()); // 참가하는 학생 수
		int K= Integer.parseInt(st.nextToken()); // 한 방에 최대 인원 수 
		
		int[][] student =new int[2][7]; // 남녀 0,1  학년  1~6
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine()," ");
			int S =Integer.parseInt(st.nextToken());
			int Y =Integer.parseInt(st.nextToken());
			if(S ==0 ) student[0][Y]++;
			else student[1][Y]++; // 학년과 남녀에 해당하는 index ++
		}
	
		int cnt =0; // 방 개수 체크 
		
		for(int i=0;i<2;i++) {
			for(int j=1;j<=6;j++) {
				if(student[i][j]==0) {
					continue;
				}
				cnt +=student[i][j] / K;
				if(student[i][j]%K!=0) {
					cnt+=1;
				}
			}
		}
		System.out.println(cnt);
		
	}// main
}