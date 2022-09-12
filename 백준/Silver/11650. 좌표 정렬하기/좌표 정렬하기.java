import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
	
		int N=Integer.parseInt(br.readLine());
		
		int[][] map=new int[N][2];
		
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine()," ");
			map[i][0]=Integer.parseInt(st.nextToken());
			map[i][1]=Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(map, (num1, num2)->{
			if(num1[0]==num2[0]) {
				return num1[1]-num2[1];
			}else {
				return num1[0]-num2[0];
			}
		});
		StringBuilder sb =new StringBuilder();
		for(int i=0;i<N;i++) {
			sb.append(map[i][0]).append(" ").append(map[i][1]).append('\n');
		}
	
		System.out.println(sb);
	}//main
}