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

    // Count the number of "twigs" in the tree
    // Count the number of "twigs" based on dynamic constraints
    public int countTwigs(List<Predicate<Node>> constraints) {
        return countTwigs(root, constraints);
    }

    private int countTwigs(Node current, List<Predicate<Node>> constraints) {
        if (current == null) {
            return 0;
        }

        // Check if all constraints are satisfied
        boolean satisfiesAll = constraints.stream().allMatch(predicate -> predicate.test(current));

        if (satisfiesAll) {
            System.out.println("Twig node: " + current.value);
        }

        // Count the node if it satisfies all constraints
        int twigCount = satisfiesAll ? 1 : 0;

        // Recursively count twigs in left and right subtrees
        return twigCount + countTwigs(current.left, constraints) + countTwigs(current.right, constraints);
    }

    private boolean hasTwoLeafChildren(Node node) {
        if (node == null || node.left == null || node.right == null) {
            return false;
        }
        return isLeaf(node.left) && isLeaf(node.right);
    }

    private boolean hasNoSiblings(Node node) {
        if (node == null || node.parent == null) {
            return false;
        }
        Node parent = node.parent;
        return (parent.left == node && parent.right == null) || (parent.right == node && parent.left == null);
    }

    private boolean parentHasNoSiblings(Node node) {
        if (node == null || node.parent == null) {
            return false;
        }
        Node parent = node.parent;
        Node grandparent = parent.parent;
        if (grandparent == null) {
            return true;
        }
        return (grandparent.left == parent && grandparent.right == null) ||
                (grandparent.right == parent && grandparent.left == null);
    }

    private boolean isLeaf(Node node) {
        return node != null && node.left == null && node.right == null;
    }

    private boolean hasOnlyOneChild(Node node) {
        if (node == null) return false;
        int childCount = (node.left != null ? 1 : 0) + (node.right != null ? 1 : 0);
        return childCount == 1;
    }

    private boolean hasChildren(Node node) {
        return node != null && (node.left != null || node.right != null);
    }

    private boolean childHasLeaf(Node node) {
        if (node == null) return false;
        if (node.left != null && (isLeaf(node.left.left) || isLeaf(node.left.right))) return true;
        if (node.right != null && (isLeaf(node.right.left) || isLeaf(node.right.right))) return true;
        return false;
    }

    private boolean hasGrandchildren(Node node) {
        if (node == null) return false;
        if (node.left != null && (node.left.left != null || node.left.right != null)) return true;
        if (node.right != null && (node.right.left != null || node.right.right != null)) return true;
        return false;
    }

    private boolean isChildALeaf(Node node) {
        if (node == null) return false;
        if (node.left != null && isLeaf(node.left)) return true;
        if (node.right != null && isLeaf(node.right)) return true;
        return false;
    }

    private boolean parentHasOnlyOneChild(Node node) {
        if (node == null || node.parent == null) return false;
        return (node.parent.left != null && node.parent.right == null) ||
                (node.parent.right != null && node.parent.left == null);
    }

    private boolean isRoot(Node node) {
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
        tree.insert(22);
        tree.insert(10);
        tree.insert(36);
        tree.insert(8);
        tree.insert(15);
        tree.insert(26);
        tree.insert(40);
        tree.insert(6);
        tree.insert(11);
        tree.insert(24);
        tree.insert(28);
        tree.insert(45);
        tree.insert(2);
        tree.insert(7);
        tree.insert(13);
        tree.insert(27);
        tree.insert(30);
        tree.insert(48);
        tree.insert(12);
        tree.insert(14);
        tree.insert(29);
        tree.insert(32);
        tree.insert(46);
        tree.insert(50);

        // Define constraints using Predicates
        Predicate<BinarySearchTree.Node> hasTwoLeafChildren = tree::hasTwoLeafChildren;
        Predicate<BinarySearchTree.Node> hasNoSiblings = tree::hasNoSiblings;
        Predicate<BinarySearchTree.Node> parentHasNoSiblings = tree::parentHasNoSiblings;
        Predicate<BinarySearchTree.Node> isLeaf = tree::isLeaf;
        Predicate<BinarySearchTree.Node> hasOnlyOneChild = tree::hasOnlyOneChild;
        Predicate<BinarySearchTree.Node> hasChildren = tree::hasChildren;
        Predicate<BinarySearchTree.Node> childHasLeaf = tree::childHasLeaf;
        Predicate<BinarySearchTree.Node> hasGrandchildren = tree::hasGrandchildren;
        Predicate<BinarySearchTree.Node> isChildALeaf = tree::isChildALeaf;
        Predicate<BinarySearchTree.Node> parentHasOnlyOneChild = tree::parentHasOnlyOneChild;
        Predicate<BinarySearchTree.Node> isRoot = tree::isRoot;

        // Combine constraints into a list
        List<Predicate<BinarySearchTree.Node>> constraints = new ArrayList<>();
        constraints.add(hasTwoLeafChildren);
        constraints.add(hasNoSiblings);
        constraints.add(parentHasNoSiblings);

        // Count the number of twigs that satisfy the constraints
        int twigCount = tree.countTwigs(constraints);
        int height = tree.height();
        int pathLength = tree.internalPathLength();

        // Print the result
        System.out.println("Number of twigs: " + twigCount);
        System.out.println("Height of tree: " + height);
        System.out.println("Internal path length: " + pathLength);
    }
}
