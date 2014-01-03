package com.ambantis.lists;

import java.lang.NullPointerException;

class Node<T> {
  private T t;
  private Node<T> ts;

  private Node() {}

  Node(T t, Node<T> ts) {
    if (t == null)
      throw new NullPointerException("A list node data element cannot be null");
    this.t = t;
    this.ts = ts;
  }

  T data() {
    return t;
  }

  Node<T> next() {
    return ts;
  }

  void setNext(Node<T> ts) {
    this.ts = ts;
  }
}
