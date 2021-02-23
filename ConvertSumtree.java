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
    public void printLevelOrder(Node root)
    {
        if(root==null)
        {
            return;
        }
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        while(!nodes.isEmpty())
        {
            Node node = nodes.remove();
            System.out.print(node.value+" ");
            if(node.left!=null)
            {
                nodes.add(node.left);
            }
            if(node.right!=null)
            {
                nodes.add(node.right);
            }
        }
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

/* Complete the function convertToSumTree given below
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

long convertToSumTree(Node node) {
    //write your code here
    if (node == null) 
            return 0; 
   
        // Store the old value 
        long old_val = node.value; 
   
        // Recursively call for left and right subtrees and store the sum 
        // as new value of this node 
        node.value = convertToSumTree(node.left) + convertToSumTree(node.right); 
   
        // Return the sum of values of nodes in left and right subtrees 
        // and old_value of this node 
        return node.value + old_val; 
}


}
public class Main {

    public static void main(String[] args) {
        // write your code here
        BinaryTree bt = new BinaryTree();
        bt.root = bt.createTreeByLevelTree();
        bt.root = bt.replaceNegativeOne(bt.root);
        bt.convertToSumTree(bt.root);
        bt.printLevelOrder(bt.root);
        bt.deleteTree(bt.root);
    }
}
