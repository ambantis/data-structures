package com.ambantis.tree;

class Leaf<E> extends Node<E> {
  private final E e;

  Leaf(E data) {
    if (data == null)
      throw new IllegalArgumentException("a node cannot contain null values");
    this.e = data;
  }

  @Override
  E value() {
    return e;
  }
}
