import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Satellite {

    public Satellite() {}

    public Tree treeFromTraversals(List<Character> preorder, List<Character> inorder) {
        if (preorder.size() != inorder.size()) {
            throw new IllegalArgumentException("traversals must have the same length");
        }

        if (!preorder.containsAll(inorder)) {
            throw new IllegalArgumentException("traversals must have the same elements");
        }

        if (!hasUniqueItems(preorder)) {
            throw new IllegalArgumentException("traversals must contain unique items");
        }

        return new Tree(buildTree(
            new ArrayList<>(preorder),
            inorder,
            0,
            preorder.size()
        ));
    }

    private boolean hasUniqueItems(List<Character> traversal) {
        HashSet<Character> set = new HashSet<>();
        for (Character c : traversal) {
            if (!set.add(c)) {
                return false;
            }
        }
        return true;
    }

    private Node buildTree(List<Character> pre, List<Character> in, int inStart, int inEnd) {
        // Base case: preorder has no elements
        // Base case: 'in' sublist has no elements
        if (pre.size() == 0 || inEnd < inStart) {
            return null;
        }

        Node root = new Node(pre.remove(0));

        // Base case: 'in' sublist has only one element
        if (inEnd - inStart == 0) {
            return root;
        }

        int mid = in.indexOf(root.value);
        root.left = buildTree(pre, in, inStart, mid - 1);
        root.right = buildTree(pre, in, mid + 1, inEnd);

        return root;
    }
}
