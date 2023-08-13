import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            left.push(str.charAt(i));
        }

        int N = Integer.parseInt(br.readLine());


        while (N-- > 0) {
            String[] s = br.readLine().split(" ");
            switch (s[0]) {
                case "L":
                    if (!left.isEmpty()) right.push(left.pop());
                    break;
                case "D":
                    if (!right.isEmpty()) left.push(right.pop());
                    break;
                case "B":
                    if (!left.isEmpty()) left.pop();
                    break;
                case "P":
                    char addChar = s[1].charAt(0);
                    left.push(addChar);
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!left.isEmpty()) {
            sb.append(left.pop());
        }
        sb.reverse();
        while (!right.isEmpty()) {
            sb.append(right.pop());
        }
        System.out.println(sb);

    }
}
