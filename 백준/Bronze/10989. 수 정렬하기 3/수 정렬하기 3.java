
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {  
	public static void main(String[] args) throws IOException{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
	
		int T=Integer.parseInt(br.readLine());
		
		int[] arr= new int[10001];
		
		for(int tc=0;tc<T;tc++) {
			arr[Integer.parseInt(br.readLine())]++;
		}
			br.close();
		
		StringBuilder sb =new StringBuilder();
		for(int i=1;i<10001;i++) {
			while(arr[i]>0) {
				sb.append(i).append('\n');
				arr[i]--;
			}
		}
		System.out.println(sb);
		
		
		
		
	}//main
	
	
}
