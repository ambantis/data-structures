package com.ambantis.lists;

import java.lang.IllegalStateException;
import java.lang.Iterable;
import java.lang.NullPointerException;
import java.lang.StringBuilder;
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


  // Query Operations

  /**
   * Returns the number of elements in this collection.  If this collection
   * contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
   * <tt>Integer.MAX_VALUE</tt>.
   *
   * @return the number of elements in this collection
   */
  public int size() {
    int size = 0;
    for (E e : this)
      size++;
    return size;
  }


  /**
   * Returns <tt>true</tt> if this collection contains no elements.
   *
   * @return <tt>true</tt> if this collection contains no elements
   */
  public boolean isEmpty() {
    return first == null;
  }


  /**
   * Returns <tt>true</tt> if this collection contains the specified element.
   * More formally, returns <tt>true</tt> if and only if this collection
   * contains at least one element <tt>e</tt> such that
   * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
   *
   * @param o element whose presence in this collection is to be tested
   * @return <tt>true</tt> if this collection contains the specified
   *         element
   * @throws NullPointerException if the specified element is null and this
   *         collection does not permit null elements
   *         (<a href="#optional-restrictions">optional</a>)
   */
  public boolean contains(Object o) {
    if (o == null)
      throw new NullPointerException("this collection does not permit null elements");
    for (E e : this)
      if (e.equals(o))
        return true;
    return false;
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

  /**
   * Returns an array containing all of the elements in this collection.
   * If this collection makes any guarantees as to what order its elements
   * are returned by its iterator, this method must return the elements in
   * the same order.
   *
   * <p>The returned array will be "safe" in that no references to it are
   * maintained by this collection.  (In other words, this method must
   * allocate a new array even if this collection is backed by an array).
   * The caller is thus free to modify the returned array.
   *
   * <p>This method acts as bridge between array-based and collection-based
   * APIs.
   *
   * @return an array containing all of the elements in this collection
   */
  Object[] toArray() {
    if (this.isEmpty())
      return new Object[0];
    else {
      int len = this.size();
      Object[] es = new Object[this.size()];
      {
        int i = 0;
        E e;
        for (Iterator<E> it = this.iterator(); it.hasNext(); ) {
          e = it.next();
          es[i++] = (Object) e;
        }
      }
      return es;
    }
  }

  @Override
  public String toString() {
    String string = "List()";
    if (!this.isEmpty()) {
      Iterator<E> it = this.iterator();
      E e = null;
      StringBuilder sb = new StringBuilder(this.size());
      sb.append("List(");
      for (int i = 0, len = this.size(); i < len; i++) {
        e = it.next();
        sb.append(e.toString());
        if (i < len-1)
          sb.append(",");
      }
      sb.append(")");
      string = sb.toString();
    }
    return string;
  }

}
