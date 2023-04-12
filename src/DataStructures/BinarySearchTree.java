package DataStructures;

import ADTs.BinarySearchTreeADT;
import Exceptions.EmptyTreeException;
import Exceptions.NodeNotFoundException;

public class BinarySearchTree<E extends Comparable<E>> implements BinarySearchTreeADT<E> {
  TreeNode<E> root = null;

  public boolean isEmpty() {
    throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
  }

  public int size() {
    throw new UnsupportedOperationException("Unimplemented method 'size'");
  }

  public void insert(E element) {
    throw new UnsupportedOperationException("Unimplemented method 'insert'");
  }

  public boolean contains(E element) throws EmptyTreeException {
    throw new UnsupportedOperationException("Unimplemented method 'contains'");
  }

  public E search(E element) throws NodeNotFoundException, EmptyTreeException {
    throw new UnsupportedOperationException("Unimplemented method 'search'");
  }

  public String preOrder() {
    throw new UnsupportedOperationException("Unimplemented method 'preOrder'");
  }

  public String postOrder() {
    throw new UnsupportedOperationException("Unimplemented method 'postOrder'");
  }

  public String inOrder() {
    throw new UnsupportedOperationException("Unimplemented method 'inOrder'");
  }

  /**
   * Deletes an element from the tree.
   * 
   * @param element the element to be deleted.
   * @throws EmptyTreeException if the tree is empty and no elements can be
   *                            deleted.
   */
  public void delete(E element) throws EmptyTreeException {
    // Check if the tree is empty
    if (root == null) {
      throw new EmptyTreeException();
    }

    // Call the recursive helper method starting from the root
    root = delete(root, element);
  }

  // A private helper method to delete an element from the tree recursively
  private TreeNode<E> delete(TreeNode<E> node, E element) {
    // Base case: if the node is null, return null
    if (node == null) {
      return null;
    }

    // Compare the element with the node's element
    int comparison = element.compareTo(node.getElement());

    // If the element is smaller than the node's element, go to the left subtree
    if (comparison < 0) {
      node.setLeft(delete(node.getLeft(), element));
    }
    // If the element is greater than the node's element, go to the right subtree
    else if (comparison > 0) {
      node.setRight(delete(node.getRight(), element));
    }
    // If the element is equal to the node's element, delete the node
    else {
      // Case 1: the node has no children, return null
      if (node.getLeft() == null && node.getRight() == null) {
        return null;
      }
      // Case 2: the node has only one child, return that child
      else if (node.getLeft() == null) {
        return node.getRight();
      } else if (node.getRight() == null) {
        return node.getLeft();
      }
      // Case 3: the node has two children, replace it with its in-order successor
      else {
        // Find the smallest node in the right subtree
        TreeNode<E> successor = findMin(node.getRight());

        // Copy its element to the current node
        node.setElement(successor.getElement());

        // Delete the successor from the right subtree
        node.setRight(delete(node.getRight(), successor.getElement()));
      }
    }

    // Return the updated node
    return node;
  }

  // A private helper method to find the smallest node in a subtree
  private TreeNode<E> findMin(TreeNode<E> node) {
    // Base case: if the node has no left child, return it
    if (node.getLeft() == null) {
      return node;
    }

    // Recursive case: go to the left subtree
    return findMin(node.getLeft());
  }

}
