package com.ambantis.tree;

class Node {
  private final int data;
  private Node left;
  private Node right;

  Node(int data) {
    this.data = data;
    left = null;
    right = null;
  }

  Node(int data, Node left, Node right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }

  int value() {
    return data;
  }

  Node left() {
    return left;
  }

  Node right() {
    return right;
  }

  void setLeft(Node node) {
    left = node;
  }

  void setRight(Node node) {
    right = node;
  }
}
