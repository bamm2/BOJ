import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int A=Integer.parseInt(st.nextToken());
		int B=Integer.parseInt(st.nextToken());
		int C=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		
		int ans =0 ; // 하루에 최대 많은 일 한 경우 
		int tired =0; // 피로도 
		
		for(int i=0;i<24;i++) { // 하루는 24시간 
			if(tired+A>M) { // 피로도 기준을 넘을 경우 
				tired-=C; // 한시간 쉬면서 피로도 빼주고 ans는 그대로 
				if(tired<0) { // 피로도는 음수가 없으므로 음수일 경우 0으로 
					tired=0;
				}
			}else { // 피로도 기준을 넘지 않을 경우  
				tired+=A; // 피로도 증가 
				ans+=B; // 일의 양 추가 
			}
		}
		System.out.println(ans);
	}// main
}