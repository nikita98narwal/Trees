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

    Node createTreeByLevelTree(Scanner sc) {

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
  
/* Complete the function checkMirrorTree given below
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

boolean checkMirrorTree(Node node1, Node node2) {
    //write your code here  
    if (node1 == null && node2 == null) 
            return true; 
        if (node1 == null || node2 == null)  
            return false; 
        return node1.value == node2.value
                && checkMirrorTree(node1.left, node2.right) 
                && checkMirrorTree(node1.right, node2.left); 
}



}
public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner sc = new Scanner(System.in);
        BinaryTree bt = new BinaryTree();
        bt.root = bt.createTreeByLevelTree(sc);
        bt.root = bt.replaceNegativeOne(bt.root);
        BinaryTree bt1 = new BinaryTree();
        bt1.root = bt1.createTreeByLevelTree(sc);
        bt1.root = bt1.replaceNegativeOne(bt1.root);
        if(bt.checkMirrorTree(bt.root, bt1.root))
            System.out.println("true");
        else
            System.out.println("false");
        bt.deleteTree(bt.root);
    }
}
