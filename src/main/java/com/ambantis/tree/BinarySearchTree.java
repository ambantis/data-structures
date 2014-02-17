package com.ambantis.tree;

/**
 * Binary search trees
 */
public class BinarySearchTree implements Tree {

  private Node root;

  public BinarySearchTree(int data) {
    root = new Node(data);
  }

  public boolean contains(int data) {
    return lookup(root, data);
  }

  public void insert(int data) {}

  private Boolean lookup(Node node, int data) {
    if (data == node.value()) {
      return true;
    } else if (data < node.value() && node.left() != null) {
        lookup(node.left(), data);
    } else if (data > node.value() && node.right() != null) {
        lookup(node.right(), data);
    }
    return false;
  }

  private void findRightSpot(Node node, int data) {
    if (data < node.value() && node.left() == null) {
      node.setLeft(new Node(data));
    } else if (data < node.value() && node.left() != null) {
      findRightSpot(node.left(), data);
    } else if (data > node.value() && node.right() == null) {
      node.setRight(new Node(data));
    } else if(data > node.value() && node.right() != null) {
      findRightSpot(node.right(), data);
    } else // data == node.value()
      throw new RuntimeException("This should never happen");
  }
}
