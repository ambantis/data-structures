package com.ambantis.tree;

class Branch extends OldNode {
  private final Integer data;

  private final Node left;
  private final Node right;

  Branch(Integer data, Node left, Node right) {
    if (data == null || left == null || right == null)
      throw new IllegalArgumentException("A node must contain non-null values");
    this.data = data;
    this.left = left;
    this.right = right;
  }

  Integer value() {
    return data;
  }

  Node left() {
    return left;
  }

  Node right() {
    return right;
  }
}
