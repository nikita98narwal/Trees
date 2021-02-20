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

    Node createTreeByLevelTree(Scanner sc ) {

        long n, m;
        LinkedList<Node> queue = new LinkedList<>();
        Node t;
        root = null;
        while (sc.hasNext()) {
            n = sc.nextLong();
            if (queue.isEmpty()) {
                root = createNode(n);
                queue.add(root);
                continue;
            }
            m = sc.nextLong();
            t = queue.peekFirst();
            queue.pop();
            t.left = createNode(n);
            t.right = createNode(m);
            if (t.left.value != -1)
                queue.add(t.left);
            if (t.right.value != -1)
                queue.add(t.right);
            if (queue.isEmpty())
                break;
        }
          return root;
    }

    void deleteTree(Node node) {
        node = null;
    }

/* Complete the function findLowestAncestor given below
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

Node findLowestAncestor(Node node, int v1, int v2) {
    //write your code here
    if (node == null) 
            return null; 
   
        // If both n1 and n2 are smaller than root, then LCA lies in left 
        if (node.value > v1 && node.value > v2) 
            return findLowestAncestor(node.left, v1, v2); 
   
        // If both n1 and n2 are greater than root, then LCA lies in right 
        if (node.value < v1 && node.value < v2)  
            return findLowestAncestor(node.right, v1, v2); 
   
        return node; 
}


}
public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner sc = new Scanner(System.in);
        BinaryTree bt = new BinaryTree();
        bt.root = bt.createTreeByLevelTree(sc);
        bt.root = bt.replaceNegativeOne(bt.root);
        int n1 = sc.nextInt();
        int n2 = sc.nextInt();
        Node val = bt.findLowestAncestor(bt.root, n1,n2);
        System.out.println(val.value);
        bt.deleteTree(bt.root);
    }
}
