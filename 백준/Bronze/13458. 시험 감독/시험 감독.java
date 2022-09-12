import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] people = new int[N];
		for (int i = 0; i < N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine(), " ");
		int main = Integer.parseInt(st.nextToken());
		int sub = Integer.parseInt(st.nextToken());

		long ans=0;
		
		for(int i=0;i<N;i++) {
			people[i]-=main;
			ans++;
		}
		
		for(int i=0;i<N;i++) {
			if(people[i]>0) {
				if(people[i]%sub>0) {
					ans+=people[i]/sub+1;
				}else if(people[i]%sub==0) {
					ans+=people[i]/sub;
				}
			}
		}
		System.out.println(ans);
	}// main
}