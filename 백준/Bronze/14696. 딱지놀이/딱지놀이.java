import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // 백준

	// 별 > 동그라미 > 네모 > 세모  , 모두 같으면 무승부  
	// 4    3       2     1   
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N =Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int tc=1;tc<=N;tc++) {
			
			String ans="";
			int[] A =new int[5]; // A 도형 점수 카운트  0 버리기 
			int[] B =new int[5]; // B 도형 점수 카운트  0 버리기
	
			st =new StringTokenizer(br.readLine()," ");
			 
			int numa=Integer.parseInt(st.nextToken());
			for(int i=0;i<numa;i++) {
				A[Integer.parseInt(st.nextToken())]++;
			}
			
			st=new StringTokenizer(br.readLine()," ");
			
			int numb=Integer.parseInt(st.nextToken());
			for(int i=0;i<numb;i++) {
				B[Integer.parseInt(st.nextToken())]++;
			}
			
			for(int i=4;i>=1;i--) {
				if(A[i]>B[i]) {
					ans="A";
					break;
				}else if(A[i]<B[i]) {
					ans="B";
					break;
				}else if(i==1 && A[i]==B[i]){
					ans="D";
				}else {
					continue;
				}
				
			}
			System.out.println(ans);
		}
		
	}// main
}