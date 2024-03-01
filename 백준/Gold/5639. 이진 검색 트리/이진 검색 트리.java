import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static class Node {
        int v;
        Node leftChild, rightChild;

        public Node(int v, Node leftChild, Node rightChild) {
            this.v = v;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }

    static Node root;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        root = new Node(Integer.parseInt(br.readLine()), null, null);
        String str;
        while ((str = br.readLine())!=null && !str.isEmpty()) {
            int number = Integer.parseInt(str);
            setPos(root, number);
        }

        postOrder(root);

        System.out.println(sb.toString());
        br.close();
    }

    private static void setPos(Node curr, int number) {
        if (curr.v > number) {
            if (curr.leftChild==null) curr.leftChild = new Node(number, null, null);
            else setPos(curr.leftChild, number);
        } else {
            if (curr.rightChild==null) curr.rightChild = new Node(number, null, null);
            else setPos(curr.rightChild, number);
        }
    }

    private static void postOrder(Node curr) {

        if (curr.leftChild!=null) postOrder(curr.leftChild);
        if (curr.rightChild!=null) postOrder(curr.rightChild);

        sb.append(curr.v).append('\n');
    }
}