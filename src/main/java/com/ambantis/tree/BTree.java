package com.ambantis.tree;

/**
 * Binary search trees
 */
public class BTree implements Tree {

  private Node root;
  private int size = 0;

  public BTree() {
    root = null;
  }

  public BTree(int data) {
    root = new Node(data);
  }

  public boolean contains(int data) {
    return (root == null) ? false : lookup(root, data);
  }

  public boolean insert(int data) {
    boolean result = false;
    if (root == null) {
      root = new Node(data);
      size++;
      result = true;
    } else {
      result = findRightSpot(root, data);
      if (result == true)
        size++;
    }
    return result;
  }

  public int size() {
    return count(root);
  }

  private int count(Node node) {
    if (node == null) {
      return 0;
    } else if (node.left() == null && node.right() == null) {
      return 1;
    } else if (node.left() != null && node.right() == null) {
      return 1 + count(node.left());
    } else if (node.left() == null && node.right() != null) {
      return 1 + count(node.right());
    } else { // (node.left() != null && node.right() != null)
      return 1 + count(node.left()) + count(node.right());
    }
  }



  private Boolean lookup(Node node, int data) {
    boolean result = false;
    if (data == node.value()) {
      result = true;
    } else if (data < node.value() && node.left() != null) {
      result = lookup(node.left(), data);
    } else if (data > node.value() && node.right() != null) {
      result = lookup(node.right(), data);
    }
    return result;
  }

  private boolean findRightSpot(Node node, int data) {
    boolean result = false;
    if (data < node.value() && node.left() == null) {
      node.setLeft(new Node(data));
      result = true;
    } else if (data < node.value() && node.left() != null) {
      result = findRightSpot(node.left(), data);
    } else if (data > node.value() && node.right() == null) {
      node.setRight(new Node(data));
      result = true;
    } else if(data > node.value() && node.right() != null) {
      result = findRightSpot(node.right(), data);
    } else {// data == node.value()
      throw new IllegalArgumentException("duplicates are not allowed");
    }
    return result;
  }
}
