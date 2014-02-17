package com.ambantis.tree;

import java.lang.IllegalArgumentException;

class Branch<E> extends Node<E> {
  private final E e;

  private final Node<E> left;
  private final Node<E> right;

  Branch(E data, Node<E> left, Node<E> right) {
    if (data == null || left == null || right == null)
      throw new IllegalArgumentException("A node must contain non-null values");
    this.e = data;
    this.left = left;
    this.right = right;
  }

  @Override
  E value() {
    return e;
  }
}
