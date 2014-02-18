package com.ambantis.tree;

/**
 * Binary search trees
 */
public class BTree implements Tree {

  private Node root = null;
  private int size = 0;

  public BTree() {}

  public BTree(int elem, int... elems) {
    root = new Node(elem);
    for (int i = 0; i < elems.length; i++)
      insert(elems[i]);
  }

  public boolean contains(int data) {
    return (root == null) ? false : recursiveLookup(root, data);
  }

  public boolean insert(int data) {
    boolean result = false;
    if (root == null) {
      root = new Node(data);
      size++;
      result = true;
    } else {
      result = recursiveInsert(root, data);
      if (result == true)
        size++;
    }
    return result;
  }

  public int size() {
    return recursiveCount(root);
  }

  public int maxDepth() {
    return (root == null) ? 0 : recursiveDepth(1, root);

  }

  private Boolean recursiveLookup(Node node, int data) {
    boolean result = false;
    if (data == node.value()) {
      result = true;
    } else if (data < node.value() && node.left() != null) {
      result = recursiveLookup(node.left(), data);
    } else if (data > node.value() && node.right() != null) {
      result = recursiveLookup(node.right(), data);
    }
    return result;
  }

  private int recursiveCount(Node node) {
    if (node == null) {
      return 0;
    } else if (node.left() == null && node.right() == null) {
      return 1;
    } else if (node.left() != null && node.right() == null) {
      return 1 + recursiveCount(node.left());
    } else if (node.left() == null && node.right() != null) {
      return 1 + recursiveCount(node.right());
    } else { // (node.left() != null && node.right() != null)
      return 1 + recursiveCount(node.left()) + recursiveCount(node.right());
    }
  }

  private boolean recursiveInsert(Node node, int data) {
    boolean result = false;
    if (data < node.value() && node.left() == null) {
      node.setLeft(new Node(data));
      result = true;
    } else if (data < node.value() && node.left() != null) {
      result = recursiveInsert(node.left(), data);
    } else if (data > node.value() && node.right() == null) {
      node.setRight(new Node(data));
      result = true;
    } else if(data > node.value() && node.right() != null) {
      result = recursiveInsert(node.right(), data);
    } else {// data == node.value()
      throw new IllegalArgumentException("duplicates are not allowed");
    }
    return result;
  }

  private int recursiveDepth(int depth, Node node) {
    int result;
    if (node.left() == null && node.right() == null) {
      result = depth;
    } else if (node.left() != null && node.right() == null) {
      result = recursiveDepth(depth+1, node.left());
    } else if (node.left() == null && node.right() != null) {
      result = recursiveDepth(depth+1, node.right());
    } else { // node.left() != null && node.right() != null
      result = Math.max(
          recursiveDepth(depth+1, node.left()),
          recursiveDepth(depth+1, node.right()));
    }
    return result;
  }
}
