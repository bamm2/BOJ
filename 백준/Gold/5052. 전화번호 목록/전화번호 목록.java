import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static class Node {
         Map<Character, Node> childNode = new HashMap<>();
         boolean isLast;
    }

    static class Trie {

        Node rootNode = new Node();

        boolean contains(String word) {
            Node node = rootNode;

            for (int i = 0; i < word.length(); i++) {
                char curr = word.charAt(i);
                if (node.childNode.get(curr) == null) {
                    node.childNode.put(curr, new Node());
                }
                node = node.childNode.get(curr);
                if (node.isLast) return false;
            }

            if (node.childNode.size() != 0) return false;

            node.isLast = true;
            return true;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            Trie trie=new Trie();
            int N = Integer.parseInt(br.readLine());
            boolean flag =false;
            for (int i = 0; i < N; i++) {
                String str = br.readLine();
               if(!trie.contains(str)){
                   flag=true;
                }
            }

            if(flag) sb.append("NO").append('\n');
            else sb.append("YES").append('\n');

        }

        System.out.println(sb);

    }
}