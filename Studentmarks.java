import java.util.LinkedList;
import java.util.*;
import java.util.Scanner;
import java.io.*;

class Node
{
    long value;
    Node left, right;

    public Node(long item)
    {
        value = item;
        left = right = null;
    }
}
class BinaryTree {
    Node root;

    BinaryTree() {
        root = null;
    }

    Node createNode(long value) {
        Node t = new Node(value);
        return t;
    }

    Node replaceNegativeOne(Node root) {
        if (root == null || (root.value == -1 && root.left == null && root.right == null)) {
            return null;
        }
        root.left = replaceNegativeOne(root.left);
        root.right = replaceNegativeOne(root.right);
        return root;
    }

    Node createTreeByLevelTree() {
        Scanner sc = new Scanner(System.in);
        long n, m;
        Queue<Node> queue = new LinkedList<>();
        Node t;
        root = null;
        while (sc.hasNext()) {
            n = sc.nextLong();
            if (queue.isEmpty()) {
                root = createNode(n);
                ((LinkedList<Node>) queue).add(root);
                continue;
            }
            m = sc.nextLong();
            t = ((LinkedList<Node>) queue).peekFirst();
            ((LinkedList<Node>) queue).pop();
            t.left = createNode(n);
            t.right = createNode(m);
            if (t.left.value != -1)
                ((LinkedList<Node>) queue).add(t.left);
            if (t.right.value != -1)
                ((LinkedList<Node>) queue).add(t.right);
            if (queue.isEmpty())
                break;
        }
        return root;
    }

    void deleteTree(Node node) {
        node = null;
    }

/*
Complete the function findAverageLevel given below.
For your reference:
class Node
{
    long value;
    Node left, right;
    public Node(long item)
    {
        value = item;
        left = right = null;
    }
}
*/

void findAverageLevel(Node node) {
    //write your code here
    Queue<Node> q = new LinkedList<Node> ();  
    q.add(root);  
    double sum = 0, count  = 0; 
  
    while (!q.isEmpty()) {  
        sum = 0; 
        count = 0;  
        Queue<Node> temp = new LinkedList<Node> ();  
        while (!q.isEmpty()) {  
            Node n = q.peek();  
            q.remove();  
            sum += n.value;  
            count++;  
            if (n.left != null)  
                temp.add(n.left);  
            if (n.right != null)  
                temp.add(n.right);  
        }  
        q = temp;  
        double f = (sum * 1.0 / count);
        String s = String.format("%,.2f", f);
        System.out.print(s +" ");  
    }  
}


}
public class Main {

    public static void main(String[] args) {
        // write your code here
            BinaryTree bt = new BinaryTree();
            bt.root = bt.createTreeByLevelTree();
            bt.root = bt.replaceNegativeOne(bt.root);
            bt.findAverageLevel(bt.root);
            bt.deleteTree(bt.root);
    }
}
