package com.ambantis.lists;

import java.lang.Class;
import java.lang.ClassCastException;
import java.lang.IllegalStateException;
import java.lang.Iterable;
import java.lang.NullPointerException;
import java.lang.StringBuilder;
import java.util.Collection;
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
  public Object[] toArray() {
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

  /**
   * Returns an array containing all of the elements in this collection;
   * the runtime type of the returned array is that of the specified array.
   * If the collection fits in the specified array, it is returned therein.
   * Otherwise, a new array is allocated with the runtime type of the
   * specified array and the size of this collection.
   *
   * <p>If this collection fits in the specified array with room to spare
   * (i.e., the array has more elements than this collection), the element
   * in the array immediately following the end of the collection is set to
   * <tt>null</tt>.  (This is useful in determining the length of this
   * collection <i>only</i> if the caller knows that this collection does
   * not contain any <tt>null</tt> elements.)
   *
   * <p>If this collection makes any guarantees as to what order its elements
   * are returned by its iterator, this method must return the elements in
   * the same order.
   *
   * <p>Like the {@link #toArray()} method, this method acts as bridge between
   * array-based and collection-based APIs.  Further, this method allows
   * precise control over the runtime type of the output array, and may,
   * under certain circumstances, be used to save allocation costs.
   *
   * <p>Suppose <tt>x</tt> is a collection known to contain only strings.
   * The following code can be used to dump the collection into a newly
   * allocated array of <tt>String</tt>:
   *
   * <pre>
   *     String[] y = x.toArray(new String[0]);</pre>
   *
   * Note that <tt>toArray(new Object[0])</tt> is identical in function to
   * <tt>toArray()</tt>.
   *
   * @param a the array into which the elements of this collection are to be
   *        stored, if it is big enough; otherwise, a new array of the same
   *        runtime type is allocated for this purpose.
   * @return an array containing all of the elements in this collection
   * @throws ArrayStoreException if the runtime type of the specified array
   *         is not a supertype of the runtime type of every element in
   *         this collection
   * @throws NullPointerException if the specified array is null
   */
  @SuppressWarnings("unchecked")
  public <T> T[] toArray(T[] a) {
    if (a == null)
      throw new NullPointerException("argument array is null");
    int i = 0;
    if (!this.isEmpty()) {
      int len = this.size();
      if (a.length < len)
        a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), len);
      for (Iterator<E> it = this.iterator(); it.hasNext(); ) {
        E e = it.next();
        a[i++] = (T) e;
      }
    }
    for (; i < a.length; i++)
      a[i] = null;
    return a;
  }


  // Modification Operations

  /**
   * Ensures that this collection contains the specified element (optional
   * operation).  Returns <tt>true</tt> if this collection changed as a
   * result of the call.  (Returns <tt>false</tt> if this collection does
   * not permit duplicates and already contains the specified element.)<p>
   *
   * Collections that support this operation may place limitations on what
   * elements may be added to this collection.  In particular, some
   * collections will refuse to add <tt>null</tt> elements, and others will
   * impose restrictions on the type of elements that may be added.
   * Collection classes should clearly specify in their documentation any
   * restrictions on what elements may be added.<p>
   *
   * If a collection refuses to add a particular element for any reason
   * other than that it already contains the element, it <i>must</i> throw
   * an exception (rather than returning <tt>false</tt>).  This preserves
   * the invariant that a collection always contains the specified element
   * after this call returns.
   *
   * @param e element whose presence in this collection is to be ensured
   * @return <tt>true</tt> if this collection changed as a result of the
   *         call
   * @throws NullPointerException if the specified element is null and this
   *         collection does not permit null elements
   */
  boolean add(E e) {
    if (e == null)
      throw new NullPointerException("this collection does not permit null elements");
    first = new Node<E>(e, first);
    return true;
  }


  /**
   * Removes a single instance of the specified element from this
   * collection, if it is present (optional operation).  More formally,
   * removes an element <tt>e</tt> such that
   * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>, if
   * this collection contains one or more such elements.  Returns
   * <tt>true</tt> if this collection contained the specified element (or
   * equivalently, if this collection changed as a result of the call).
   *
   * @param o element to be removed from this collection, if present
   * @return <tt>true</tt> if an element was removed as a result of this call
   * @throws NullPointerException if the specified element is null and this
   *         collection does not permit null elements
   *         (<a href="#optional-restrictions">optional</a>)
   */
  boolean remove(Object o) {
    if (o == null)
      throw new NullPointerException("this collection does not permit null elements");
    E e;
    for (Iterator<E> it = this.iterator(); it.hasNext(); ) {
      e = it.next();
      if (e.equals(o)) {
        it.remove();
        return true;
      }
    }
    return false;
  }


  // Bulk Operations

  /**
   * Returns <tt>true</tt> if this collection contains all of the elements
   * in the specified collection.
   *
   * @param  c collection to be checked for containment in this collection
   * @return <tt>true</tt> if this collection contains all of the elements
   *         in the specified collection
   * @throws NullPointerException if the specified collection contains one
   *         or more null elements and this collection does not permit null
   *         elements
   *         (<a href="#optional-restrictions">optional</a>),
   *         or if the specified collection is null.
   * @see    #contains(Object)
   */
  boolean containsAll(Collection<?> c) {
    if (c == null)
      throw new NullPointerException("this collection does not permit null elements");
    for (Iterator<?> it = c.iterator(); it.hasNext(); ) {
      Object elem = (Object) it.next();
      if (!this.contains(elem))
        return false;
    }
    return true;
  } 

  @Override
  public boolean equals(Object obj) {
    if (obj == null)
      return false;
    if (!(obj instanceof MyLinkedList<?>))
      return false;
    // `that` must be of type MyLinkedList if we reach this point in the code
    @SuppressWarnings("unchecked")
    MyLinkedList<Object> that = (MyLinkedList<Object>) obj;
    if (this.size() != that.size())
      return false;
    {
      Iterator<E> itThis = this.iterator();
      Iterator<Object> itThat = that.iterator();
      E thing1;
      Object thing2;
      while (itThis.hasNext()) {
        thing1 = itThis.next();
        thing2 = itThat.next();
        if (!thing1.equals(thing2))
          return false;
      }
    }
    return true;
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
