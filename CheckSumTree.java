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

/* Complete the function checkSumTree given below
For your reference

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

boolean checkSumTree(Node node) {
    //write your code here
             long ls,rs;
             if((node==null)||(node.left==null&&node.right==null))
             return true;
             ls=sum(node.left);
             rs=sum(node.right);
              if ((node.value == ls + rs) && (checkSumTree(node.left) !=false) 
                && (checkSumTree(node.right)) != false) 
            return true; 
   
        return false; 
             
	}
	 
  long sum(Node node)  
    { 
        if (node == null) 
            return 0; 
        return sum(node.left) + node.value + sum(node.right); 
    } 



}
public class Main {

    public static void main(String[] args) {
        // write your code here
            BinaryTree bt = new BinaryTree();
            bt.root = bt.createTreeByLevelTree();
            bt.root = bt.replaceNegativeOne(bt.root);
            if(bt.checkSumTree(bt.root)==true)
                System.out.println("true");
            else
                System.out.println("false");
            bt.deleteTree(bt.root);
    }
}
