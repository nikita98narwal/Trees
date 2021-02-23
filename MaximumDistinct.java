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

/* Complete the function findDistinctCount given below
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
static int printt(Node node, HashMap<Long,Integer> m) 
{ 
    if (node == null) 
        return m.size(); 
    if(m.containsKey(node.value)) 
    { 
        m.put((node.value), m.get(node.value) + 1); 
    } 
    else
    { 
        m.put(node.value, 1); 
    } 
    int max_path = Math.max(printt(node.left, m), 
                            printt(node.right, m)); 
    if(m.containsKey(node.value)) 
    { 
        m.put(node.value, m.get(node.value) - 1); 
    } 
    if (m.get(node.value) == 0) 
        m.remove(node.value); 
    return max_path; 
} 

int findDistinctCount(Node node) {
    //write your code here
    HashMap<Long,Integer> hash = new HashMap<Long, 
                                        Integer>(); 
    return printt(node,hash);
}


}
public class Main {

    public static void main(String[] args) {
        // write your code here
            BinaryTree bt = new BinaryTree();
            bt.root = bt.createTreeByLevelTree();
            bt.root = bt.replaceNegativeOne(bt.root);
            System.out.println(bt.findDistinctCount(bt.root));
            bt.deleteTree(bt.root);
    }
}
