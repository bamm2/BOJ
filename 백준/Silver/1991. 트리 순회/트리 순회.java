import java.util.*;
import java.io.*;

public class Main {

    static class Node{
        char nodeChar;
        Node leftChild ,rightChild;
        Node(char nodeChar,Node leftChild,Node rightChild){
            this.nodeChar= nodeChar;
            this.leftChild=leftChild;
            this.rightChild=rightChild;
        }
    }
    static Node root;
    static StringBuilder sb =new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine(), " ");

        char parent = st.nextToken().charAt(0);
        char leftChild = st.nextToken().charAt(0);
        char rightChild = st.nextToken().charAt(0);
        Node left = leftChild == '.' ? null : new Node(leftChild,null,null);
        Node right = rightChild == '.' ? null : new Node(rightChild,null,null);
        root=new Node(parent,left,right);

        for(int i=0;i<N-1;i++) {
            st = new StringTokenizer(br.readLine(), " ");

            parent = st.nextToken().charAt(0);
            leftChild = st.nextToken().charAt(0);
            rightChild = st.nextToken().charAt(0);

            searchNode(root,parent,leftChild,rightChild);
        }

        preOrder(root); sb.append('\n');
        InOrder(root); sb.append('\n');
        postOrder(root);

        System.out.println(sb);
    }

    private static void searchNode(Node node, char parent, char leftChild, char rightChild) {
        if(node == null) return;

        if(node.nodeChar == parent){
            node.leftChild = leftChild == '.' ? null : new Node(leftChild,null,null);
            node.rightChild = rightChild == '.' ? null : new Node(rightChild,null,null);
            return;
        }

        searchNode(node.leftChild,parent,leftChild,rightChild);
        searchNode(node.rightChild,parent,leftChild,rightChild);
    }

    private static void preOrder(Node node) {
        if(node==null) return;

        sb.append(node.nodeChar);
        if(node.leftChild!=null) preOrder(node.leftChild);
        if(node.rightChild!=null) preOrder(node.rightChild);
    }

    private static void InOrder(Node node) {
        if(node==null) return;

        if(node.leftChild!=null) InOrder(node.leftChild);
        sb.append(node.nodeChar);
        if(node.rightChild!=null) InOrder(node.rightChild);
    }

    private static void postOrder(Node node) {
        if(node==null) return;

        if(node.leftChild!=null) postOrder(node.leftChild);
        if(node.rightChild!=null) postOrder(node.rightChild);
        sb.append(node.nodeChar);
    }
}