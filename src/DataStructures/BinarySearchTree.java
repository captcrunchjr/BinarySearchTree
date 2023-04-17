package DataStructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ADTs.BinarySearchTreeADT;
import Exceptions.EmptyTreeException;
import Exceptions.NodeNotFoundException;

/**Binary Search Tree
 * @author Joshua Eckard
 * @author Professor
 */
public class BinarySearchTree<E extends Comparable<E>> implements BinarySearchTreeADT<E> {
  /**
   * root set to null
   */
  TreeNode<E> root = null;

  /**Method to see if the tree is empty or not
   * 
   */
  public boolean isEmpty() {
    return root == null;
  }

  /**Method to track size
   * 
   */
  public int size() {
    if(isEmpty()){
      return 0;
    }
    
    return size(root);
  }

  /**private helper for size recursion
   * 
   * @param node current node
   * @return works recursively
   */
  private int size(TreeNode<E> node){
    if(node == null){
      return 0;
    }

    return size(node.getLeft()) + size(node.getRight()) + 1;
  }

  /**Method to insert a new element
   * 
   */
  public void insert(E element) {
    TreeNode<E> newNode = new TreeNode<>(element);

    if (root == null){
      root = newNode;
    }
    else{
      insert(root, newNode);
    }
  }

  /**private helper for insert recursion
   * 
   * @param node current node
   * @param newNode new node created to insert
   */
  private void insert(TreeNode<E> node, TreeNode<E> newNode){
    int compare = newNode.getElement().compareTo(node.getElement());

    if(compare < 0){
      if(node.getLeft() == null){
        node.setLeft(newNode);
      }
      else{
        insert(node.getLeft(), newNode);
      }
    }
    else{
      if(node.getRight() == null){
        node.setRight(newNode);
      }
      else{
        insert(node.getRight(), newNode);
      }
    }
  }

  /** Method to see if an element exists in the tree
   *  @return operates recursively
   */
  public boolean contains(E element) throws EmptyTreeException {
    if(isEmpty()){
      throw new EmptyTreeException();
    }
    return contains(root, element);
  }

  /** Private helper method for recursion
   * 
   * @param node current node
   * @param element element checking for
   * @return operates recursively
   */
  private boolean contains(TreeNode<E> node, E element){
    if(node == null){
      return false;
    }
    int result = node.getElement().compareTo(element);
    if(result == 0){
      return true;
    }
    else if(result > 0){
      return contains(node.getLeft(), element);
    }
    else{
      return contains(node.getRight(), element);
    }

  }

  /**Search for an element in the tree
   * @return operates recursively
   */
  public E search(E element) throws NodeNotFoundException, EmptyTreeException {
    if(isEmpty()){
      throw new EmptyTreeException();
    }

    return search(root, element);
  }

  /** Private helper method to search recursively
   * 
   * @param node current node
   * @param element target element
   * @return the element at the node found
   * @throws NodeNotFoundException if element isnt found
   */
  private E search(TreeNode<E> node, E element) throws NodeNotFoundException{
    if(node == null){
      throw new NodeNotFoundException();
    }

    int compare = element.compareTo(node.getElement());

    if(compare < 0 ){
        return search(node.getLeft(), element);
    }
    else if(compare > 0){
      return search(node.getRight(), element);
    }
    else{
      return node.getElement();
    }
  }

  /** Method to return contents in parent -> childen order
   *  @return list with contents
   */
  public String preOrder() {
    List<E> list = new ArrayList<>();

    preOrder(root, list);

    return Arrays.toString(list.toArray());

  }
  
  /** Private helper method to return contents in parent -> children order
   * 
   * @param node current node
   * @param list list of contents
   */
  private void preOrder(TreeNode<E> node, List<E> list){
    if(node == null){
      return;
    }

    list.add(node.getElement());
    preOrder(node.getLeft(), list);
    preOrder(node.getRight(), list);
  }

  /** Method to return contents in left to right order
   * @return list of contents
   */
  public String postOrder() {
    List<E> list = new ArrayList<>();

    postOrder(root, list);

    return Arrays.toString(list.toArray());
  }

  /** Private helper method to return contents of tree in left to right order
   * 
   * @param node current node being viewed
   * @param list list of contents
   */
  private void postOrder(TreeNode<E> node, List<E> list){
    if(node == null){
      return;
    }

    postOrder(node.getLeft(), list);
    postOrder(node.getRight(), list);
    list.add(node.getElement());
  }

  /** Method to return contents in numerical order
   *  @return list with contents
   */
  public String inOrder() {
    List<E> list = new ArrayList<>();

    inOrder(root, list);

    return Arrays.toString(list.toArray());
  }

  /** Private helper method to return contents in numerical order.
   * @param node current node being checked
   * @param list arraylist of contents to add to.
   */ 
   private void inOrder(TreeNode<E> node, List<E> list){
    if(node == null){
      return;
    }

    inOrder(node.getLeft(), list);
    list.add(node.getElement());
    inOrder(node.getRight(), list);
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

  /** 
   * A private helper method to delete an element from the tree recursively
   * @author Professor
   * @param element target for deletion
   * @param node current node
   * @return works recursively
   */
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

  /**
   * A private helper method to find the smallest node in a subtree
   * @author Professor
   * @param node current node
   * @return works recursively
   */
  private TreeNode<E> findMin(TreeNode<E> node) {
    // Base case: if the node has no left child, return it
    if (node.getLeft() == null) {
      return node;
    }

    // Recursive case: go to the left subtree
    return findMin(node.getLeft());
  }

}
