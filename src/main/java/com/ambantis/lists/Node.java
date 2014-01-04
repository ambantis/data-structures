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

  void setNext(Node<T> node) {
    ts = node;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Node))
      return false;
    Node<T> that = (Node<T>) o;

    if (this == null && that == null)
      return true;
    else if (this == null && that != null || this != null && that == null)
      return false;
    else if (!that.data().equals(t))
      return false;
    else if (that.next() == null && ts == null)
      return true;
    else if (that.next() == null && ts != null || that.next() != null && ts == null)
      return false;
    else if (!that.next().equals(ts))
      return false;
    else
      return true;
  }

  @Override
  public int hashCode() {
    int result = 17;
    result += t.hashCode();
    Node<T> iter = ts;
    while(iter != null) {
      result += iter.t.hashCode();
      iter = iter.ts;
    }
    return result;
  }

  @Override
  public String toString() {
    if (this == null)
      return "Node()";
    else
      return "Node(" + t.toString() + "," + ((ts == null) ? "null" : ts.toString()) + ")";
  }
}
