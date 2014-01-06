package com.ambantis.lists;

import java.lang.IllegalStateException;
import java.lang.Iterable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements Iterable<E> {
  private Node<E> first;

  public MyLinkedList() {}

  // only type E can be fed into the Nodes that will be put into nodes[]
  @SuppressWarnings("unchecked")
  public MyLinkedList(E... es) {
    for (E e : es) {
      if (e == null)
        throw new NullPointerException("error: this collection does not accept " +
            "null values");
    }
    Node<E>[] nodes = (Node<E>[]) new Node<?>[es.length];
    nodes[es.length-1] = new Node<E>(es[es.length-1], null);
    for (int i = es.length-2; i > -1; i--) {
      nodes[i] = new Node<E>(es[i], nodes[i+1]);
    }
    first = nodes[0];
  }

  public Iterator<E> iterator() {
    return new Itr();
  }

  private class Itr implements Iterator<E> {
    private boolean removeable = false;
    private Node<E> prev;
    private Node<E> last;
    private Node<E> next = first;
    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    public boolean hasNext() {
      return next != null;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    public E next() {
      if (!hasNext())
        throw new NoSuchElementException("this collection has no more elements");
      E data = next.data();
      removeable = true;
      prev = last;
      last = next;
      next = next.next();
      return data;
    }

    /**
     * Removes from the underlying collection the last element returned
     * by this iterator (optional operation).  This method can be called
     * only once per call to {@link #next}.  The behavior of an iterator
     * is unspecified if the underlying collection is modified while the
     * iteration is in progress in any way other than by calling this
     * method.
     *
     * @throws IllegalStateException if the {@code next} method has not
     *         yet been called, or the {@code remove} method has already
     *         been called after the last call to the {@code next}
     *         method
     */
    public void remove() {
      if (!removeable)
        throw new IllegalStateException("this method can be called only once, " +
            "per call to next()");
      if (prev == null) {
        first = next;
        last = null;
      } else {
        prev.setNext(next);
        last = prev;
      }
      removeable = false;
    }
  }
}
