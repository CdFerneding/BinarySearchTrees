package kompPrakt.buc.bst.src;


public class TestDriver {

    public static void testTraversal(Tree tree) {
        System.out.println("preOrderTraversal: ");
        tree.preOrderTraversal(); // out:   C E F H B G A N J K
        System.out.println("\ninOrderTraversal: ");
        tree.inOrderTraversal(); // out:    F H E B C A G J N K
        System.out.println("\npostOrderTraversal: ");
        tree.postOrderTraversal(); // out:  H F B E A J K N G C
    }
}
