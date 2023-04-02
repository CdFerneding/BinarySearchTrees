package kompPrakt.buc.bst.src;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Scanner;

public class Tree {
    public Node root;

    /**
     *
     * building tree in preorder (NLF)
     */
    public static void buildTree(Tree tree) {
        System.out.println("Enter the Tree you want to build (no whitespaces!)");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        System.out.println(input.length());

        if (!input.equals("@")) {
            tree.root = new Node(new NodeData(input.substring(0, 1)));
            Node freeRightChild = tree.root;
            input = input.substring((1));
            Stack<Node> freeRightChildren = new Stack<>();
            freeRightChildren.add(freeRightChild);
            if (!input.startsWith("@@")) {
                if (input.charAt(0) == '@') buildSubtree(input.substring(1), tree.root, false, freeRightChildren);
                else buildSubtree(input, tree.root, true, freeRightChildren);
                sc.close();
            } // else is here not needed because there a no more nodes if the root has no children
        }

    }

    /**
     *
     * @param input
     * @param node
     * @param isLeft
     * @param freeRightChildren: for remembering free RightChildren. When a "...@@..." comes we have to
     *                         continue with the lastes Child we pushed to our Stack (Stack are used, because
     *                         they can be simultaniously deleted
     */
    private static void buildSubtree(String input, Node node, boolean isLeft, Stack<Node> freeRightChildren) {
        if (!input.equals("@")) {
            Node subtreeNode = new Node(new NodeData(input.substring(0, 1)));
            if(isLeft) node.left = subtreeNode;
            else node.right = subtreeNode;
            // A child is added if in the upcoming String a right-Child will be skipped
            if(!input.substring(0,1).equals("@") && !input.substring(1,2).equals("@")) freeRightChildren.add(subtreeNode);
            input = input.substring((1));
            if (!input.startsWith("@@")) {
                if (input.charAt(0) == '@') buildSubtree(input.substring(1), subtreeNode, false, freeRightChildren);
                else buildSubtree(input, subtreeNode, true, freeRightChildren);
            } else {
                if(input.length() > 3) {
                    input = input.substring(2);
                    buildSubtree(input, freeRightChildren.pop(), false, freeRightChildren);
                }
            }
        }
    }

    /**
     * prints the tree on the console in preorder; meaning: Node --> Left --> Right
     */
    public void preOrderTraversal() {
        preOrderTraversal(this.root);
    }
    private void preOrderTraversal(Node node) {
        if(node != null) {
            node.data.visit();
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }

    /**
     * prints the tree on the console in preorder; meaning: Left --> Node --> Right
     */
    public void inOrderTraversal() {
        inOrderTraversal(this.root);
    }
    private void inOrderTraversal(Node node) {
        if(node != null) {
            preOrderTraversal(node.left);
            node.data.visit();
            preOrderTraversal(node.right);
        }
    }

    /**
     * prints the tree on the console in preorder; meaning: Left --> Right --> Node
     */
    public void postOrderTraversal() {
        inOrderTraversal(this.root);
    }
    private void postOrderTraversal(Node node) {
        if(node != null) {
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
            node.data.visit();
        }
    }

    public static void findOrInsert() {

    }
}
