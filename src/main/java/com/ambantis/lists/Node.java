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

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Node))
      return false;
    Node<Object> that = (Node) o;
    if (!that.data().equals(this.data()))
      return false;
    if (that.next() == null && ts != null || that.next() != null && ts == null)
      return false;
    if (that.data().equals(t) &&
        (that.next() == null && ts == null) || (that.next().equals(ts)))
      return true;
    else
      return false;
  }

  @Override
  public int hashCode() {
    int result = 17;
    result += t.hashCode();
    Node<T> iter = ts;
    while(iter.ts != null) {
      result += iter.t.hashCode();
      iter = iter.ts;
    }
    return result;
  }

  @Override
  public String toString() {
    return "Node(" + t.toString() + "," + ((ts == null) ? "null" : ts.toString()) + ")";
  }
}
