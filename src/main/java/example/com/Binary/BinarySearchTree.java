package example.com.Binary;

// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    private Node root;

    private static class Node {
        int value;
        Node left, right, parent; // Parent reference added

        Node(int value) {
            this.value = value;
            left = right = parent = null;
        }
    }

    // Insert a value into the tree
    public void insert(int value) {
        root = insert(root, null, value); // Root has no parent
    }

    private Node insert(Node current, Node parent, int value) {
        if (current == null) {
            Node newNode = new Node(value);
            newNode.parent = parent; // Set the parent reference
            return newNode;
        }
        if (value < current.value) {
            current.left = insert(current.left, current, value);
        } else if (value > current.value) {
            current.right = insert(current.right, current, value);
        }
        return current;
    }

    // Check if the tree contains a specific value
    public boolean contains(int value) {
        return contains(root, value);
    }

    private boolean contains(Node current, int value) {
        if (current == null) {
            return false;
        }
        if (value < current.value) {
            return contains(current.left, value);
        } else if (value > current.value) {
            return contains(current.right, value);
        } else {
            return true; // Match found
        }
    }

    // Find the maximum value in the tree
    public int findMax() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        return findMax(root).value;
    }

    private Node findMax(Node current) {
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }

    // Find the minimum value in the tree
    public int findMin() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        return findMin(root).value;
    }

    private Node findMin(Node current) {
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // Calculate the height of the tree
    public int height() {
        return height(root);
    }

    private int height(Node current) {
        if (current == null) {
            return -1; // Height of an empty tree is -1
        }
        return 1 + Math.max(height(current.left), height(current.right));
    }

    // Calculate the depth from root
    public int depth() {
        return depth(root, 0); // Start fra roden med dybde 0
    }

    // Recursive helper-method for calculation the depth
    private int depth(Node current, int currentDepth) {
        if (current == null) {
            return currentDepth; // Returnér dybden, når vi når en tom node
        }
        // Beregn dybden for venstre og højre subtræ og vælg den maksimale
        return Math.max(
                depth(current.left, currentDepth + 1),
                depth(current.right, currentDepth + 1)
        );
    }

    // Print the tree in sorted order (in-order traversal)
    public void printTree() {
        printTree(root);
        System.out.println();
    }

    private void printTree(Node current) {
        if (current != null) {
            printTree(current.left);
            System.out.print(current.value + " ");
            printTree(current.right);
        }
    }

    // Method to count the number of "twigs" in the tree based on dynamic constraints
    // A "twig" is defined as a node that satisfies all the given constraints
    public int countTwigs(List<Predicate<Node>> constraints) {
        // Delegate the counting process to a private recursive method
        // Starts from the root of the tree
        return countTwigs(root, constraints);
    }

    // Private recursive method to count twigs in the tree
    private int countTwigs(Node current, List<Predicate<Node>> constraints) {
        // Base case: If the current node is null, return 0 (no twigs to count here)
        if (current == null) {
            return 0;
        }

        // Check if the current node satisfies all the provided constraints
        // A node satisfies all constraints if every Predicate in the list returns true for the node
        boolean satisfiesAll = constraints.stream().allMatch(predicate -> predicate.test(current));

        // Log the node value if it satisfies all constraints (for debugging or analysis)
        if (satisfiesAll) {
            System.out.println("Twig node: " + current.value);
        }

        // If the node satisfies all constraints, count it as a twig (1)
        // Otherwise, count it as 0
        int twigCount = satisfiesAll ? 1 : 0;

        // Recursively count twigs in the left and right subtrees
        // Add their counts to the current node's twig count
        return twigCount + countTwigs(current.left, constraints) + countTwigs(current.right, constraints);
    }

    // Check if a node has exactly two children that are leaf nodes
    private boolean hasTwoLeafChildren(Node node) {
        // A node must have two non-null children to qualify
        if (node == null || node.left == null || node.right == null) {
            return false;
        }
        // Both children must be leaf nodes (no children of their own)
        return isLeaf(node.left) && isLeaf(node.right);
    }

    // Check if a node has no siblings
    private boolean hasNoSiblings(Node node) {
        // A node must have a parent to evaluate sibling relationships
        if (node == null || node.parent == null) {
            return false;
        }
        Node parent = node.parent;
        // Check if the node is the only child of its parent
        return (parent.left == node && parent.right == null) || (parent.right == node && parent.left == null);
    }

    // Check if the current node has a child with no siblings
    private boolean childHasNoSiblings(Node node) {
        // Ensure the node and its parent are non-null
        if (node == null || node.parent == null) {
            return false;
        }

        // Check if one of the node's children exists without a sibling
        return (node.left != null && node.right == null || node.right != null && node.left == null);
    }

    // Check if the parent of the node has no siblings
    private boolean parentHasNoSiblings(Node node) {
        // Ensure the node and its parent are non-null
        if (node == null || node.parent == null) {
            return false;
        }
        Node parent = node.parent;
        Node grandparent = parent.parent;

        // If the parent has no grandparent, it has no siblings
        if (grandparent == null) {
            return true;
        }

        // Check if the parent is the only child of the grandparent
        return (grandparent.left == parent && grandparent.right == null) ||
                (grandparent.right == parent && grandparent.left == null);
    }

    // Check if a node is a leaf node (has no children)
    private boolean isLeaf(Node node) {
        // A node is a leaf if it exists and both left and right children are null
        return node != null && node.left == null && node.right == null;
    }

    // Check if a node has exactly one child
    private boolean hasOnlyOneChild(Node node) {
        if (node == null) return false;
        // Count the number of non-null children (should equal 1)
        int childCount = (node.left != null ? 1 : 0) + (node.right != null ? 1 : 0);
        return childCount == 1;
    }

    // Check if a node has at least one child
    private boolean hasChildren(Node node) {
        // A node has children if either the left or right child is non-null
        return node != null && (node.left != null || node.right != null);
    }

    // Check if a node's child has a leaf child (grandchild of the current node)
    private boolean childHasLeaf(Node node) {
        if (node == null) return false;
        // Check if any child has a leaf as one of its children
        if (node.left != null && (isLeaf(node.left.left) || isLeaf(node.left.right))) return true;
        if (node.right != null && (isLeaf(node.right.left) || isLeaf(node.right.right))) return true;
        return false;
    }

    // Check if a node has grandchildren
    private boolean hasGrandchildren(Node node) {
        if (node == null) return false;
        // Check if either child has children of their own
        if (node.left != null && (node.left.left != null || node.left.right != null)) return true;
        if (node.right != null && (node.right.left != null || node.right.right != null)) return true;
        return false;
    }

    // Check if any of the node's children is a leaf
    private boolean isChildALeaf(Node node) {
        if (node == null) return false;
        // Check if either child is a leaf
        if (node.left != null && isLeaf(node.left)) return true;
        if (node.right != null && isLeaf(node.right)) return true;
        return false;
    }

    // Check if the node has exactly one child, which is a leaf
    private boolean childHasExactlyOneChildThatIsLeaf(Node node) {
        if (node == null) return false;
        // Check if only one child exists and it is a leaf
        if (node.left != null && isLeaf(node.left) && node.right == null) return true;
        if (node.right != null && isLeaf(node.right) && node.left == null) return true;
        return false;
    }

    // Check if the parent of the node has only one child
    private boolean parentHasOnlyOneChild(Node node) {
        if (node == null || node.parent == null) return false;
        // Check if the node's parent has only one child
        return (node.parent.left != null && node.parent.right == null) ||
                (node.parent.right != null && node.parent.left == null);
    }

    // Check if a node is the root of the tree
    private boolean isRoot(Node node) {
        // A node is the root if it is equal to the root reference
        return node == root;
    }

    // Get the parent of a node (used for other tree operations if needed)
    public Node findParent(int value) {
        Node target = findNode(value);
        return target != null ? target.parent : null;
    }

    // Find a node by its value
    public Node findNode(int value) {
        return findNode(root, value);
    }

    private Node findNode(Node current, int value) {
        if (current == null) {
            return null; // Not found
        }
        if (value == current.value) {
            return current; // Match found
        }
        if (value < current.value) {
            return findNode(current.left, value); // Search left subtree
        } else {
            return findNode(current.right, value); // Search right subtree
        }
    }

    // Public method to calculate the internal path length
    public int internalPathLength() {
        return calculateIPL(root, 0); // Start with depth 0
    }

    // Helper method to calculate IPL recursively
    private int calculateIPL(Node current, int depth) {
        if (current == null) {
            return 0; // No contribution from an empty subtree
        }
        // Current node's depth + IPL of left subtree + IPL of right subtree
        return depth + calculateIPL(current.left, depth + 1) + calculateIPL(current.right, depth + 1);
    }

    // Test program
    public static void main( String [ ] args )
    {
        BinarySearchTree tree = new BinarySearchTree();

        // Insert nodes into the tree
        tree.insert(7);
        tree.insert(4);
        tree.insert(28);
        tree.insert(3);
        tree.insert(55);
        tree.insert(2);
        tree.insert(51);
        tree.insert(60);
        tree.insert(1);
        tree.insert(48);
        tree.insert(58);
        tree.insert(69);
        tree.insert(40);
        tree.insert(57);
        tree.insert(35);

        // Define constraints using Predicates
        Predicate<BinarySearchTree.Node> hasTwoLeafChildren = tree::hasTwoLeafChildren;
        Predicate<BinarySearchTree.Node> hasNoSiblings = tree::hasNoSiblings;
        Predicate<BinarySearchTree.Node> parentHasNoSiblings = tree::parentHasNoSiblings;
        Predicate<BinarySearchTree.Node> isLeaf = tree::isLeaf;
        Predicate<BinarySearchTree.Node> hasOnlyOneChild = tree::hasOnlyOneChild;
        Predicate<BinarySearchTree.Node> hasChildren = tree::hasChildren;
        Predicate<BinarySearchTree.Node> childHasLeaf = tree::childHasLeaf;
        Predicate<BinarySearchTree.Node> childHasNoSiblings = tree::childHasNoSiblings;
        Predicate<BinarySearchTree.Node> hasGrandchildren = tree::hasGrandchildren;
        Predicate<BinarySearchTree.Node> isChildALeaf = tree::isChildALeaf;
        Predicate<BinarySearchTree.Node> childHasExactlyOneChildThatIsLeaf = tree::childHasExactlyOneChildThatIsLeaf;
        Predicate<BinarySearchTree.Node> parentHasOnlyOneChild = tree::parentHasOnlyOneChild;
        Predicate<BinarySearchTree.Node> isRoot = tree::isRoot;

        // Combine constraints into a list
        List<Predicate<BinarySearchTree.Node>> constraints = new ArrayList<>();
        constraints.add(hasOnlyOneChild);
        constraints.add(hasNoSiblings);
        constraints.add(childHasNoSiblings);
        constraints.add(childHasExactlyOneChildThatIsLeaf);

        // Count the number of twigs that satisfy the constraint
        int twigCount = tree.countTwigs(constraints);
        int height = tree.height();
        int depth = tree.depth();
        int min = tree.findMin();
        int max = tree.findMax();
        int pathLength = tree.internalPathLength();

        // Print the result
        System.out.println("Number of twigs: " + twigCount);
        System.out.println("Height of tree: " + height);
        System.out.println("Depth of tree: " + depth);
        System.out.println("Min/Max of tree: " + min + "/" + max);
        System.out.println("Internal path length: " + pathLength);
    }
}
