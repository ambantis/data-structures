package com.ambantis.lists;

import java.lang.NullPointerException;

class Node<T> {
  private T t;
  private Node<T> ts;

  private Node() {}
  Node(T t, Node<T> ts) {
    if (t == null)
      throw new NullPointerException("nodes must contain a non-null value");
    this.t = t;
    this.ts = ts;
  }

  T data() { return t; }

  Node<T> next() { return ts; }

  void setNext(Node<T> ts) { this.ts = ts; }

  @Override
  public int hashCode() {
    int result = 42;
    result += t.hashCode();
    Node<T> next = ts;
    while (next != null) {
      result += next.data().hashCode();
      next = next.next();
    }
    return result;
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean equals(Object obj) {
    if (obj == null)
      return false;
    if (!(obj instanceof Node))
      return false;
    Node<Object> that = (Node<Object>) obj;
    if (!t.equals(that.data()))
      return false;
    if (that.next() == null && ts == null)
      return true;
    if ((that.next() == null && ts != null) || (that.next() != null && ts == null))
      return false;
    return this.next().equals(that.next());
  }

  @Override
  public String toString() {
    return "Node(" + t.toString() + "," + ((ts == null) ? "null" : ts.toString()) + ")";
  }



}
