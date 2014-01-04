package com.ambantis.lists;

import java.lang.NullPointerException;

class Node<E> {
  private E e;
  private Node<E> es;

  private Node() {}

  Node(E e, Node<E> es) {
    if (e == null)
      throw new NullPointerException("A list node data element cannot be null");
    this.e = e;
    this.es = es;
  }

  E data() {
    return e;
  }

  Node<E> next() {
    return es;
  }

  void setNext(Node<E> node) {
    es = node;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Node))
      return false;
    Node<E> that = (Node<E>) o;

    if (this == null && that == null)
      return true;
    else if (this == null && that != null || this != null && that == null)
      return false;
    else if (!that.data().equals(e))
      return false;
    else if (that.next() == null && es == null)
      return true;
    else if (that.next() == null && es != null || that.next() != null && es == null)
      return false;
    else if (!that.next().equals(es))
      return false;
    else
      return true;
  }

  @Override
  public int hashCode() {
    int result = 17;
    result += e.hashCode();
    Node<E> iter = es;
    while(iter != null) {
      result += iter.e.hashCode();
      iter = iter.es;
    }
    return result;
  }

  @Override
  public String toString() {
    if (this == null)
      return "Node()";
    else
      return "Node(" + e.toString() + "," + ((es == null) ? "null" : es.toString()) + ")";
  }
}
