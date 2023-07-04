import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String str = br.readLine();

            if (isPalindrome(str)) {
                sb.append(0).append('\n');
            } else if (isPalindromeMinusOneCharTypeOne(str) || isPalindromeMinusOneCharTypeTwo(str)  ) {
                sb.append(1).append('\n');
            } else {
                sb.append(2).append('\n');
            }

        }

        System.out.println(sb);

    }

    private static boolean isPalindrome(String s) { // 팰린드롬
        int length = s.length();
        int chkIdx = length / 2;
        for (int i = 0; i < chkIdx; i++) {
            if (s.charAt(i) != s.charAt(length - 1 - i)) return false;
        }
        return true;
    }

    private static boolean isPalindromeMinusOneCharTypeOne(String s) { // 왼쪽 한문자 제거 버전
        int left = 0;
        int right = s.length()-1;
        boolean flag= false;
        while (left <= right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                if (isSame(s.charAt(left + 1), s.charAt(right))) {
                    if (flag) return false;
                    left++;
                    flag=true;
                }else{
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isPalindromeMinusOneCharTypeTwo(String s){ // 오른쪽 한문자 제거 버전
        int left = 0;
        int right = s.length()-1;
        boolean flag= false;
        while (left <= right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                if (isSame(s.charAt(left), s.charAt(right-1))) {
                    if (flag) return false;
                    right--;
                    flag=true;
                }else{
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isSame(char a, char b) {
        return a == b;
    }

}