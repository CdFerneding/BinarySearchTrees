package kompPrakt.buc.bst.src;
import java.util.*;

public class Tree {
    public Node root;

    /**
     *
     * building tree in pre-order (NLF)
     * enter Letters or Number seperated by commas (enter for ending the input)
     */
    public static void buildTree(Tree tree) {
        System.out.println("Enter the Tree you want to build seperated by commas");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        input.replaceAll(" ", "");
        String[] arr = input.split(",");
        LinkedList<String> list = new LinkedList<>(Arrays.asList(arr));

        tree.root = new Node(new NodeData(list.get(0)));
        Node freeRightChild = tree.root;
        list.removeFirst();
        Stack<Node> freeRightChildren = new Stack<>();
        freeRightChildren.add(freeRightChild);
        if (!(list.get(0) + list.get(1)).equals("@@")) {
            if (Objects.equals(list.get(0), "@")) {
                list.removeFirst();
                buildSubtree(list, tree.root, false, freeRightChildren);
            }
            else buildSubtree(list, tree.root, true, freeRightChildren);
            sc.close();
        } // else is here not needed because there a no more nodes if the root has no children

    }

    /**
     *
     * @param list BST as a String. final ends marked by '@'s
     * @param node current Node which child is being created
     * @param isLeft indicates whether the next Node has to be placed left or right
     * @param freeRightChildren for remembering free RightChildren. When a "...@@..." comes we have to
     *                         continue with the latest Child we pushed to our Stack (Stack are used, because
     *                         they can be simultaneously deleted
     */
    private static void buildSubtree(LinkedList<String> list, Node node, boolean isLeft, Stack<Node> freeRightChildren) {
        if (list != null) {
            Node subtreeNode = new Node(new NodeData(list.get(0)));
            if(isLeft) node.left = subtreeNode;
            else node.right = subtreeNode;
            // A child is added if a right-Child will be skipped in the upcoming String
            if(!Objects.equals(list.get(0), "@") && !Objects.equals(list.get(1), "@")) freeRightChildren.add(subtreeNode);
            list.removeFirst();
            if (!(list.get(0) + list.get(1)).equals("@@")) {
                if (Objects.equals(list.get(0), "@")) {
                    list.removeFirst();
                    buildSubtree(list, subtreeNode, false, freeRightChildren);
                }
                else buildSubtree(list, subtreeNode, true, freeRightChildren);
            } else {
                if(list.size() > 3) {
                    list.removeFirst();
                    list.removeFirst();
                    buildSubtree(list, freeRightChildren.pop(), false, freeRightChildren);
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
            inOrderTraversal(node.left);
            node.data.visit();
            inOrderTraversal(node.right);
        }
    }

    /**
     * prints the tree on the console in preorder; meaning: Left --> Right --> Node
     */
    public void postOrderTraversal() {
        postOrderTraversal(this.root);
    }
    private void postOrderTraversal(Node node) {
        if(node != null) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            node.data.visit();
        }
    }

    /**
     * searches for the Date d; If it is  found: returns the node,
     * else: inserts a new Node at the right place (so that the BST is still ordered,
     * meaning: Ones own Date is larger than that of every left child and larger than that of every right child)
     * @param tree  tree that is searched
     * @param d NodeData that is searched for
     */
    public static Node findOrInsert(Tree tree, NodeData d) {
        if(tree.root.data.w.compareTo(d.w) < 0) return findOrInsert(tree.root.right, d);
        else if(tree.root.data.w.compareTo(d.w) > 0) return findOrInsert(tree.root.left, d);
        else return tree.root;
    }
    private static Node findOrInsert(Node node, NodeData d) {
        if(node.data.w.compareTo(d.w) < 0) {
            if(node.right != null) {
                return findOrInsert(node.right, d);
            } else {
                Node insert = new Node(d);
                node.right = insert;
                return insert;
            }
        }
        else if(node.data.w.compareTo(d.w) > 0) {
            if(node.left != null) {
                return findOrInsert(node.left, d);
            } else {
                Node insert = new Node(d);
                node.left = insert;
                return insert;
            }
        }
        else return node;
    }


}
