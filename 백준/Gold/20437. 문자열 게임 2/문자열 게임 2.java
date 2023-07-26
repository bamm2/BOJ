import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static class Number{
        int startIdx,endIdx,cnt;
        Number(int startIdx,int endIdx,int cnt){ // 시작 , 끝 , 개수
            this.startIdx=startIdx;
            this.endIdx=endIdx;
            this.cnt=cnt;
        }

        public int getLength(int endIdx, int startIdx){ // 길이 반환 메서드
            return endIdx-startIdx+1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        Number[] numbers;
        while (T-- > 0) {
            String str = br.readLine();
            int num = Integer.parseInt(br.readLine());

            numbers=new Number[26]; // 문자에 해당하는 cnt 체크용 크기 26 객체 배열

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for(int i=0;i<str.length();i++){
                int idx = str.charAt(i)-'a'; // 문자에 해당하는 idx
                if(numbers[idx] == null) { // 아직 값이 없는 경우
                    numbers[idx]= new Number(i,i,1); // 시작, 끝 ,카운트
                } else { // 값이 들어 있는 경우
                    int startIdx =numbers[idx].startIdx; // 시작 위치
                    int cnt = numbers[idx].cnt; // 카운트

                    if(cnt + 1 > num ){ // 이미 조건 충족
                        int nextFirstIdx = str.indexOf(str.charAt(i),startIdx+1); // 맨앞에 위치한 idx 이후 동일 문자찾기
                        numbers[idx]=new Number(nextFirstIdx,i,num);  // 새로 찾은 첫 문자위치 , 마지막 위치, 찾아야하는 문자길이로 유지
                        int getLen = numbers[idx].getLength(i,nextFirstIdx); // 길이찾기 메서드
                        min = min > getLen ? getLen : min; // 최소
                        max = max < getLen ? getLen : max; // 최대

                    }else if (cnt + 1 == num){ // 조건 충족 완료
                        numbers[idx]=new Number(startIdx,i,++cnt); // +1 하면 조건 충족완료
                        int getLen =  numbers[idx].getLength(i,startIdx); // 길이 찾기
                        min = min > getLen ? getLen : min; // 최소
                        max = max < getLen ? getLen : max; // 최대
                    }else{ // 조건 미달
                        numbers[idx]=new Number(startIdx,i,++cnt); // 아직 조건에 부합하지 않아서 마지막 위치 변경, cnt+1 해주기
                    }
                }
            }

            if(min==Integer.MAX_VALUE && num!=1) sb.append(-1).append('\n'); // 조건에 맞는 결과 못찾았을 경우 -1
            else if(num==1) sb.append(1).append(" ").append(1).append('\n');
            else sb.append(min).append(" ").append(max).append('\n'); // 찾았을 경우 min , max 찾기

        }

        System.out.println(sb.toString().trim());

    }
}