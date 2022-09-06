import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {


	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		String[] arr =new String[N];
		
		for(int i=0;i<N;i++) {
			arr[i]=br.readLine();
		}
		
		Arrays.sort(arr ,new Comparator<String>() {
			public int compare(String s1, String s2) {
				if(s1.length()==s2.length()) {
					return s1.compareTo(s2);
				}else {
					return s1.length()-s2.length();
				}
			}
		});


		String first = arr[0]; // 두 문자열 비교해서 전체 나오게 하기위해 맨 앞수는 빼놨음
		System.out.println(first); // 맨 처음 문자열 부터
		for (int i = 1; i < N; i++) { // 같지 않은 문자열만 계속 출력
			if (arr[i - 1].equals(arr[i])) {
				continue;
			} else {
				System.out.println(arr[i]);
			}
		}

	}// main
}