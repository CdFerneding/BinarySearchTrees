package kompPrakt.buc.bst.src;

public class NodeData {
    public String w;

    public NodeData(String w) {
        this.w = w;
    }

    public void visit() {
        System.out.print(w + " ");
    }
}
