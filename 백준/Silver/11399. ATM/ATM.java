import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int T =Integer.parseInt(br.readLine()); // 사람 수 
		
		int[] arr =new int[T];
		StringTokenizer st =new StringTokenizer(br.readLine()," ");
		for(int i=0;i<T;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int sum=0; 
		for(int i=0;i<T;i++) {
			for(int j=0;j<=i;j++) {
				sum+=arr[j];
			}
		}
		
		System.out.println(sum);
		
		
	}// main
}
