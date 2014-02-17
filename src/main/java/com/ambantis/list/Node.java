package com.ambantis.list;

import java.lang.IllegalArgumentException;
import java.lang.NullPointerException;

class Node<T> {
  private T t;
  private Node<T> ts;

  private Node() {}

  Node(T t, Node<T> node) {
    if (t == null)
      throw new NullPointerException("nodes with null data are not permitted");
    this.t = t;
    throwIfRecursive(node);
    this.ts = node;
  }

  T data() {
    return t;
  }

  Node<T> next() {
    return ts;
  }

  void setNext(Node<T> node) throws IllegalArgumentException {
    throwIfRecursive(node);
    this.ts = node;
  }

  private void throwIfRecursive(Node<T> node) throws IllegalArgumentException {
    for (Node<T> i = node; i != null; i = i.next()) {
        if (this.equals(i))
        throw new IllegalArgumentException("error: setting new next() would result " +
            "in infinite recursion");
    }
  }

  @Override
  public int hashCode() {
    int result = 42;
    result += t.hashCode();
    for(Node<T> next = ts; next != null; next = next.next()) {
      result += next.data().hashCode();
    }
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null)
      return false;
    if (!(obj instanceof Node))
      return false;
    @SuppressWarnings("unchecked")
    Node<Object> that = (Node<Object>) obj;
    if (!t.equals(that.data()))
      return false;
    if (that.next() == null && ts == null)
      return true;
    if (that.next() == null && ts != null)
      return false;
    if (that.next() != null && ts == null)
      return true;
    return that.next().equals(ts);
  }

  @Override
  public String toString() {
    return "Node(" + t.toString() + "," + ((ts == null) ? "null" : ts.toString()) + ")";
  }
}
