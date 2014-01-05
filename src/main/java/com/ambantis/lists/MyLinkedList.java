package com.ambantis.lists;

import java.lang.ArrayStoreException;
import java.lang.IllegalStateException;
import java.lang.Integer;
import java.lang.Iterable;
import java.lang.NullPointerException;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements Collection<E> {
  private Node<E> first;

  public MyLinkedList() {}
  public MyLinkedList(E e) { add(e); }

  @SuppressWarnings("unchecked")
  public MyLinkedList(E e, E... rest) throws NullPointerException {
    for (int i = rest.length-1; i > -1; i--)
      add(rest[i]);
    add(e);
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
    for (E elem : this) {
      size++;
      if (size == Integer.MAX_VALUE)
        break;
    }
    return size;
  }

  /**
   * Returns <tt>true</tt> if this collection contains no elements.
   *
   * @return <tt>true</tt> if this collection contains no elements
   */
  public boolean isEmpty() { return first == null; }


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
    for (E elem : this) {
      if (elem.equals(o))
        return true;
    }
    return false;
  }

  public Iterator<E> iterator() {
    return new Itr();
  }

  private class Itr implements Iterator<E> {
    private boolean removable = false;
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
        throw new NoSuchElementException("the iteration has no more elements");
      E data = next.data();
      prev = last;
      last = next;
      next = next.next();
      removable = true;
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
      if (!removable)
        throw new IllegalStateException("can only remove element once per call to `next()`");
      if (prev == null) {
        first = next;
        last = null;
      } else {
        prev.setNext(next);
      }
      removable = false;
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
    int len = this.size();
    int i = 0;
    Object[] result = new Object[len];
    for (E elem : this) {
      result[i++] = (Object) elem;
    }
    return result;
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
      throw new NullPointerException("the specified array is null");
    int len = this.size();
    if (a.length < len)
      a = (T[]) java.lang.reflect.Array.newInstance(
          a.getClass().getComponentType(), len);

    Iterator<E> it = this.iterator();
    for (int i = 0; i < a.length; i++) {
      a[i] = (it.hasNext()) ? (T) it.next() : null;
    }
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
  public boolean add(E e) {
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
  public boolean remove(Object o) {
    if (o == null)
      throw new NullPointerException("the specified element is null and this collection " +
          "does not permit null elements");
    Iterator<E> it = this.iterator();
    E current;
    while(it.hasNext()) {
      current = it.next();
      if (current.equals(o)) {
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
  public boolean containsAll(Collection<?> c) {
    if (c == null)
      throw new NullPointerException("the specified element is null and this collection " +
          "does not permit null elements");
    for (E elem : this) {
      if (!c.contains(elem)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Adds all of the elements in the specified collection to this collection
   * (optional operation).  The behavior of this operation is undefined if
   * the specified collection is modified while the operation is in progress.
   * (This implies that the behavior of this call is undefined if the
   * specified collection is this collection, and this collection is
   * nonempty.)
   *
   * @param c collection containing elements to be added to this collection
   * @return <tt>true</tt> if this collection changed as a result of the call
   * @throws NullPointerException if the specified collection contains a
   *         null element and this collection does not permit null elements,
   *         or if the specified collection is null
   * @see #add(Object)
   */
  public boolean addAll(Collection<? extends E> c) {
    if (c == null)
      throw new NullPointerException("the specified element is null and this collection " +
          "does not permit null elements");
    if (this.equals(c))
      throw new UnsupportedOperationException("This collection cannot be added to itself");

    for (E elem : c) {
      this.add(elem);
    }
    return true;
  }

  /**
   * Removes all of this collection's elements that are also contained in the
   * specified collection (optional operation).  After this call returns,
   * this collection will contain no elements in common with the specified
   * collection.
   *
   * @param c collection containing elements to be removed from this collection
   * @return <tt>true</tt> if this collection changed as a result of the
   *         call
   * @throws UnsupportedOperationException if the <tt>removeAll</tt> method
   *         is not supported by this collection
   * @throws ClassCastException if the types of one or more elements
   *         in this collection are incompatible with the specified
   *         collection
   *         (<a href="#optional-restrictions">optional</a>)
   * @throws NullPointerException if this collection contains one or more
   *         null elements and the specified collection does not support
   *         null elements
   *         (<a href="#optional-restrictions">optional</a>),
   *         or if the specified collection is null
   * @see #remove(Object)
   * @see #contains(Object)
   */
  public boolean removeAll(Collection<?> c) {
    boolean result = false;
    if (c == null)
      throw new NullPointerException("the specified element is null and this collection " +
          "does not permit null elements");
    if (this.equals(c))
      throw new UnsupportedOperationException("This collection cannot be removed from itself");

    Iterator<E> it = this.iterator();
    E current;
    while (it.hasNext()) {
      current = it.next();
      if (c.contains(current)) {
        it.remove();
        result = true;
      }
    }
    return result;
  }


  /**
   * Retains only the elements in this collection that are contained in the
   * specified collection (optional operation).  In other words, removes from
   * this collection all of its elements that are not contained in the
   * specified collection.
   *
   * @param c collection containing elements to be retained in this collection
   * @return <tt>true</tt> if this collection changed as a result of the call
   * @throws UnsupportedOperationException if the <tt>retainAll</tt> operation
   *         is not supported by this collection
   * @throws ClassCastException if the types of one or more elements
   *         in this collection are incompatible with the specified
   *         collection
   *         (<a href="#optional-restrictions">optional</a>)
   * @throws NullPointerException if this collection contains one or more
   *         null elements and the specified collection does not permit null
   *         elements
   *         (<a href="#optional-restrictions">optional</a>),
   *         or if the specified collection is null
   * @see #remove(Object)
   * @see #contains(Object)
   */
  public boolean retainAll(Collection<?> c) {
    boolean result = false;
    if (c == null)
      throw new NullPointerException("the specified element is null and this collection " +
          "does not permit null elements");
    if (this.equals(c))
      throw new UnsupportedOperationException("This collection cannot be retained from itself");

    Iterator<E> it = this.iterator();
    E current;
    while (it.hasNext()) {
      current = it.next();
      if (!c.contains(current)) {
        it.remove();
        result = true;
      }
    }
    return result;
  }


  /**
   * Removes all of the elements from this collection (optional operation).
   * The collection will be empty after this method returns.
   */
  public void clear() {
    first = null;
  }


  // Comparison and hashing

  /**
   * Compares the specified object with this collection for equality. <p>
   *
   * While the <tt>Collection</tt> interface adds no stipulations to the
   * general contract for the <tt>Object.equals</tt>, programmers who
   * implement the <tt>Collection</tt> interface "directly" (in other words,
   * create a class that is a <tt>Collection</tt> but is not a <tt>Set</tt>
   * or a <tt>List</tt>) must exercise care if they choose to override the
   * <tt>Object.equals</tt>.  It is not necessary to do so, and the simplest
   * course of action is to rely on <tt>Object</tt>'s implementation, but
   * the implementor may wish to implement a "value comparison" in place of
   * the default "reference comparison."  (The <tt>List</tt> and
   * <tt>Set</tt> interfaces mandate such value comparisons.)<p>
   *
   * The general contract for the <tt>Object.equals</tt> method states that
   * equals must be symmetric (in other words, <tt>a.equals(b)</tt> if and
   * only if <tt>b.equals(a)</tt>).  The contracts for <tt>List.equals</tt>
   * and <tt>Set.equals</tt> state that lists are only equal to other lists,
   * and sets to other sets.  Thus, a custom <tt>equals</tt> method for a
   * collection class that implements neither the <tt>List</tt> nor
   * <tt>Set</tt> interface must return <tt>false</tt> when this collection
   * is compared to any list or set.  (By the same logic, it is not possible
   * to write a class that correctly implements both the <tt>Set</tt> and
   * <tt>List</tt> interfaces.)
   *
   * @param o object to be compared for equality with this collection
   * @return <tt>true</tt> if the specified object is equal to this
   * collection
   *
   * @see Object#equals(Object)
   * @see Set#equals(Object)
   * @see List#equals(Object)
   */
  @Override
  @SuppressWarnings("unchecked")
  public boolean equals(Object obj) {
    if (obj == null)
      return false;
    if (!(obj instanceof MyLinkedList))
      return false;
    MyLinkedList<E> that = (MyLinkedList<E>) obj;
    if (this.size() != that.size())
      return false;
    Iterator<E> iThis = this.iterator();
    Iterator<E> iThat = that.iterator();
    while (iThis.hasNext()) {
      if (!iThis.next().equals(iThat.next()))
        return false;
    }
    return true;
  }

  /**
   * Returns the hash code value for this collection.  While the
   * <tt>Collection</tt> interface adds no stipulations to the general
   * contract for the <tt>Object.hashCode</tt> method, programmers should
   * take note that any class that overrides the <tt>Object.equals</tt>
   * method must also override the <tt>Object.hashCode</tt> method in order
   * to satisfy the general contract for the <tt>Object.hashCode</tt> method.
   * In particular, <tt>c1.equals(c2)</tt> implies that
   * <tt>c1.hashCode()==c2.hashCode()</tt>.
   *
   * @return the hash code value for this collection
   *
   * @see Object#hashCode()
   * @see Object#equals(Object)
   */
  @Override
  public int hashCode() {
    int result = 42;
    Integer i = 17;
    for (E elem : this) {
      result += elem.hashCode() * i.hashCode();
      i++;
    }
    return result;
  }

}
