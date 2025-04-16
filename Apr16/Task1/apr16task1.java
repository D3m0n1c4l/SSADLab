import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

class TreeNode {
    private String value;
    private List<TreeNode> neighbors;

    public TreeNode(String value) {
        this.value = value;
        this.neighbors = new ArrayList<>();
    }

    public String getValue() {
        return value;
    }

    public List<TreeNode> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(TreeNode neighbor) {
        neighbors.add(neighbor);
    }
}

class DirectedTree {
    private TreeNode root;

    public DirectedTree(TreeNode root) {
        this.root = root;
    }

    public TreeNode getRoot() {
        return root;
    }
    
    public Iterator<TreeNode> iterator() {
        return new DFSIterator(root);
    }
    
    private class DFSIterator implements Iterator<TreeNode> {
        private Stack<TreeNode> stack;

        public DFSIterator(TreeNode root) {
            stack = new Stack<>();
            if (root != null) {
                stack.push(root);
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public TreeNode next() {
            if (!hasNext()) {
                throw new IllegalStateException("No more elements to iterate.");
            }
            TreeNode currentNode = stack.pop();
            for (int i = currentNode.getNeighbors().size() - 1; i >= 0; i--) {
                stack.push(currentNode.getNeighbors().get(i));
            }
            return currentNode;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode("A");
        TreeNode nodeB = new TreeNode("B");
        TreeNode nodeC = new TreeNode("C");
        TreeNode nodeD = new TreeNode("D");
        TreeNode nodeE = new TreeNode("E");
        
        root.addNeighbor(nodeB);
        root.addNeighbor(nodeC);
        nodeB.addNeighbor(nodeD);
        nodeB.addNeighbor(nodeE);
        
        DirectedTree tree = new DirectedTree(root);
        
        Iterator<TreeNode> iterator = tree.iterator();
        while (iterator.hasNext()) {
            TreeNode node = iterator.next();
            System.out.println(node.getValue());
        }
    }
}
