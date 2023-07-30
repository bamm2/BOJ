import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        Comparator<String> comparator = (o1, o2) -> {

            if (o1.length() > o2.length()) {
                if (o2.equals(o1.substring(0, o2.length()))) {
                    return 1;
                }
            } else {
                if (o1.equals(o2.substring(0, o1.length()))) {
                    return -1;
                }
            }

            List<String> compOneList = makeList(o1);
            List<String> compTwoList = makeList(o2);

            int size = Math.min(compOneList.size(), compTwoList.size());

            for (int i = 0; i < size; i++) {
                int compOneStrLen = compOneList.get(i).length();
                int compTwoStrLen = compTwoList.get(i).length();

                if (compOneList.get(i).equals(compTwoList.get(i))) continue;

                if (compOneStrLen == 1 && compTwoStrLen == 1) { // 길이가 1인 경우
                    return compCharOneByOne(compOneList.get(i), compTwoList.get(i));
                } else { // 둘 중 하나 또는 둘다 숫자인 경우
                    return compStrAnyByAny(compOneList.get(i), compTwoList.get(i));
                }
            }
            return 0;
        };

        List<String> result =new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            result.add(str);
        }

        Collections.sort(result,comparator);

        for (String s : result) {
            bw.write(s+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int compStrAnyByAny(String s1, String s2) {
        char c1 = s1.charAt(0);
        char c2 = s2.charAt(0);

        if (isNum(c1) && !isNum(c2)) {  // 둘 중 하나 숫자인 경우
            return -1;
        } else if (!isNum(c1) && isNum(c2)) { // 둘 중 하나 숫자인 경우
            return 1;
        } else { // 둘다 숫자인 경우
            return compNum(s1, s2);
        }
    }

    private static int compNum(String s1, String s2) {
        int s1NumStartIdx = getMinIdx(s1);
        int s2NumStartIdx = getMinIdx(s2);

        int lenOne = 0 ;
        int lenTwo = 0;
        String subStrS1="";
        String subStrS2="";

        if(s1NumStartIdx!=100) { // 0이 아닌 수가 있는 경우
            lenOne = s1.substring(s1NumStartIdx).length();  // 앞의 0을 제외한 수의 길이
            subStrS1 = s1.substring(s1NumStartIdx);
        }
        if(s1NumStartIdx==100) { // 전부 0 인 경우
            lenOne = s1.length();
            subStrS1=s1;
        }
        if(s2NumStartIdx!=100){ // 0이 아닌 수가 있는 경우
            lenTwo = s2.substring(s2NumStartIdx).length(); // 앞의 0을 제외한 수의 길이
            subStrS2 = s2.substring(s2NumStartIdx);
        }
        if(s2NumStartIdx==100){ // 전부 0 인경우
            lenTwo= s2.length();
            subStrS1=s2;
        }

        if(s1NumStartIdx==100 && s2NumStartIdx == 100 ){ // 0 있는 경우 체크
            return Integer.compare(s1.length(),s2.length());
        }else if( s1NumStartIdx==100 && s2NumStartIdx != 100){
            return -1;
        }else if( s1NumStartIdx!=100 && s2NumStartIdx == 100){
            return 1;
        }else { // 0 이 없는 경우
            if (lenOne > lenTwo) { // s2가 더 작은 수
                return 1;
            } else if (lenOne < lenTwo) { // s1이 더 작은 수
                return -1;
            } else {  // 0을 제외해도 같은 길이 라면
                if (subStrS1.equals(subStrS2)) return s1.length() > s2.length() ? 1 : -1; // 0을 제외한 수가 같다면 길이 짧은 순으로 정렬
                return sameLenNumComp(subStrS1, subStrS2);
            }
        }
    }

    private static int sameLenNumComp(String s1, String s2) {

        for (int i = 0, size = s1.length(); i < size; i++) {
            int s1Num = s1.charAt(i) - '0';
            int s2Num = s2.charAt(i) - '0';
            if (s1Num == s2Num) continue;
            else return Integer.compare(s1Num, s2Num);
        }
        return 0;
    }

    private static int getMinIdx(String s) {

        int minIdx = 100; // 문자열의 최대 길이 100 ,idx 최대 99 , 100반환한 경우 0 만 있는 경우
        for (int i = 1; i <= 9; i++) {
            if (s.indexOf(String.valueOf(i)) == -1) continue;
            minIdx = Math.min(minIdx, s.indexOf(String.valueOf(i)));
        }
        return minIdx;
    }

    private static int compCharOneByOne(String s1, String s2) {
        char c1 = s1.charAt(0);
        char c2 = s2.charAt(0);

        if (isNum(c1) && !isNum(c2)) { // 둘 중 하나 숫자인 경우
            return -1;
        } else if (!isNum(c1) && isNum(c2)) { // 둘중 하나 숫자인 경우
            return 1;
        } else if (isNum(c1) && isNum(c2)) { // 둘 다 숫자인 경우
            return Integer.compare(c1,c2);
        } else { // 둘다 문자일 경우 , 대소구분
            if (isBig(c1) && !isBig(c2)) {
                return c1 + 32 <= c2 ? -1 : 1;
            } else if (!isBig(c1) && isBig(c2)) {
                return c2 + 32 <= c1 ? 1 : -1;
            }
//           else if(isBig(c1) && isBig(c2)){ // 둘다 대문자 인 경우
//               return c1-c2;
//           }
            else { // 둘다 소문자인 경우
                return c1 - c2;
            }
        }
    }

    private static List<String> makeList(String s) {
        List<String> tmp = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (isNum(curr)) {
                int plusIdx = 0;
                String addStr = "";
                while (i+plusIdx<s.length() && isNum(s.charAt(i + plusIdx))) {
                    addStr += s.charAt(i + plusIdx);
                    plusIdx++;
                }
                plusIdx--;
                tmp.add(addStr);
                i += plusIdx;
            } else {
                tmp.add(String.valueOf(curr));
            }
        }
        return tmp;
    }

    private static boolean isNum(char c) {
        return '0' <= c && c <= '9';
    }

    private static boolean isBig(char c) {
        return 'A' <= c && c <= 'Z';
    }
}