package com.ambantis.tree;

class Leaf extends OldNode {
  private final Integer data;

  Leaf(Integer data) {
    if (data == null)
      throw new IllegalArgumentException("a node cannot contain null values");
    this.data = data;
  }

  Integer value() {
    return data;
  }
}
