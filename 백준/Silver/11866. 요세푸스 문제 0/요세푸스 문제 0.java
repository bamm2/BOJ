import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Queue<Integer> q=new LinkedList<>();
		
		for(int i=1;i<=N;i++) {
			q.add(i);
		}
		
		int j=0;
		int[] arr =new int[N];
		while(!q.isEmpty()) {
			for(int i=0;i<K-1;i++) {
			q.add(q.poll());
				}
			arr[j]=q.poll();
			j++;
		}
		System.out.print("<");
		for(int i=0;i<N;i++) {
			if(i==N-1) {
				System.out.print(arr[i]);
			}else
				System.out.print(arr[i]+", ");
		}
		System.out.println(">");
			
		
		
	}// main
}