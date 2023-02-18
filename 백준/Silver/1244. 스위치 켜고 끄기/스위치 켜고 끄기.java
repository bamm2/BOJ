import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // 백준 2108
//	1은 켜져있음
//	0은 꺼져있음

//	남 - 스위치 번호가 자기 받은 수의 배수의 스위치 상태 바꾸기
//	여 - 자기가 받은 수의 스위치 번호를 중심으로 좌우 대칭이면서 가장 많은 스위치 포함하는 구간을 모두 바꿈  
//
//	입력으로 스위치 처음 상태 주어짐 
//
//	마지막 스위치 상태 출력 
//
//	스위치는 100개 이하의 양의 정수 
//
//	1열 스위치 개수
//	2열 스위치 상태
//	3열 학생 수
//	4열 ~ N열 ( 학생 수만큼 )   남자는 1 , 여자는 2  /  받은 스위치 번호  
//
//	20번째 스위치까지 한 줄에 
//	21번부터는 다음 줄 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int switchcnt = Integer.parseInt(br.readLine());
		int[] switch_arr = new int[switchcnt + 1];// 1번부터 시작하기 위해서

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int tc = 1; tc <= switchcnt; tc++) { // 1번부터 값 받아오기
			switch_arr[tc] = Integer.parseInt(st.nextToken());
		}

		int studentnum = Integer.parseInt(br.readLine()); // 학생 수

		for (int tc = 0; tc < studentnum; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int MOW = Integer.parseInt(st.nextToken());// 남 ? 녀 ?
			int switchnum = Integer.parseInt(st.nextToken());// 스위치 번호

			int i = 1;
			if (MOW == 1) { // 남자 선택
				while (true) {
					if (switch_arr[switchnum * i] == 0) {
						switch_arr[switchnum * i] = 1;
					} else {
						switch_arr[switchnum * i] = 0;
					}
					i++;
					if (switchnum * i > switch_arr.length - 1)
						break;
				}
			}
			int j = 1;
			if (MOW == 2) { // 여자 선택
				if(switch_arr[switchnum]==0) {
					switch_arr[switchnum]=1;
				}else {
					switch_arr[switchnum]=0;
				}
				while (true) {
					if (switch_arr.length > switchnum + j && switchnum - j > 0) {
						if (switch_arr[switchnum - j] == switch_arr[switchnum + j]) {
							if (switch_arr[switchnum + j] == 1) {
								switch_arr[switchnum - j] = 0;
								switch_arr[switchnum + j] = 0;
							} else {
								switch_arr[switchnum - j] = 1;
								switch_arr[switchnum + j] = 1;
							}
//							if (j % 2 == 0) {
//								if (switch_arr[switchnum] == 0) {
//									switch_arr[switchnum] = 1;
//								} else
//									switch_arr[switchnum] = 0;
//							}
							j++;
						} else // 같지 않으면 종료
							break;

					} else // 길이가 끝까지 갔으면 종료
						break;
				}
			}
		}
//		int[] ans= new int[switchcnt]; // 0번 인덱스 버리기 
//		for(int i=1;i<switch_arr.length;i++) {
//			ans[i]=switch_arr[i-1];
//		}
//		if(ans.length/20==0) {
//			System.out.println();
//		}

		for (int j = 1; j < switch_arr.length; j++) {
			System.out.print(switch_arr[j] + " ");
			if( j%20==0) {
				System.out.println();
			}
		}
		
		

	}// main

}
