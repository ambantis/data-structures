package com.ambantis.list;

import java.lang.IllegalStateException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.StringBuilder;
import java.lang.UnsupportedOperationException;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ArrayList<E> implements Iterable<E> {

  private E[] es;
  private static final int DEFAULT_CAPACITY = 20;
  private int capacity;
  private int size;

  // Cast needed to initialize array as type <E>
  @SuppressWarnings("unchecked")
  public ArrayList(int initialCapacity) {
    es = (E[]) new Object[initialCapacity];
    capacity = initialCapacity;
    size = 0;
  }

  public ArrayList() {
    this(DEFAULT_CAPACITY);
  }

  /**
   * Returns an iterator over the elements contained in this collection.
   *
   * @return an iterator over the elements contained in this collection
   */
  public ListIterator<E> iterator() {
    return new Itr();
  }

  private class Itr implements ListIterator<E> {

    private E[] itArray;
    private int nextPos;
    private int lastPos;

    // only objects of type <E> will go into array
    @SuppressWarnings("unchecked")
    private Itr() {
      itArray = (E[]) new Object[capacity];
      for (int i = 0; i < es.length; i++)
        itArray[i] = es[i];
      nextPos = 0;
      lastPos = -1;
    }

    // Iterator Query Operations

    /**
     * Returns {@code true} if this list iterator has more elements when
     * traversing the list in the forward direction. (In other words,
     * returns {@code true} if {@link #next} would return an element rather
     * than throwing an exception.)
     *
     * @return {@code true} if the list iterator has more elements when
     *         traversing the list in the forward direction
     */
    public boolean hasNext() {
      return nextPos < size;
    }

    /**
     * Returns the next element in the list and advances the cursor position.
     * This method may be called repeatedly to iterate through the list,
     * or intermixed with calls to {@link #previous} to go back and forth.
     * (Note that alternating calls to {@code next} and {@code previous}
     * will return the same element repeatedly.)
     *
     * @return the next element in the list
     * @throws NoSuchElementException if the iteration has no next element
     */
    public E next() {
      if (!hasNext())
        throw new NoSuchElementException("the collection has no next element");
      lastPos = nextPos;
      nextPos++;
      return itArray[lastPos];
    }

    /**
     * Returns {@code true} if this list iterator has more elements when
     * traversing the list in the reverse direction.  (In other words,
     * returns {@code true} if {@link #previous} would return an element
     * rather than throwing an exception.)
     *
     * @return {@code true} if the list iterator has more elements when
     *         traversing the list in the reverse direction
     */
    public boolean hasPrevious() {
      return (nextPos-1) > 0;
    }

    /**
     * Returns the previous element in the list and moves the cursor
     * position backwards.  This method may be called repeatedly to
     * iterate through the list backwards, or intermixed with calls to
     * {@link #next} to go back and forth.  (Note that alternating calls
     * to {@code next} and {@code previous} will return the same
     * element repeatedly.)
     *
     * @return the previous element in the list
     * @throws NoSuchElementException if the iteration has no previous
     *         element
     */
    public E previous() {
      if (!hasPrevious())
        throw new NoSuchElementException("the collection has no previous element");
      lastPos = nextPos-2;
      nextPos--;
      return itArray[lastPos];
    }

    /**
     * Returns the index of the element that would be returned by a
     * subsequent call to {@link #next}. (Returns list size if the list
     * iterator is at the end of the list.)
     *
     * @return the index of the element that would be returned by a
     *         subsequent call to {@code next}, or list size if the list
     *         iterator is at the end of the list
     */
    public int nextIndex() {
      return (size < nextPos) ? size : nextPos;
    }

    /**
     * Returns the index of the element that would be returned by a
     * subsequent call to {@link #previous}. (Returns -1 if the list
     * iterator is at the beginning of the list.)
     *
     * @return the index of the element that would be returned by a
     *         subsequent call to {@code previous}, or -1 if the list
     *         iterator is at the beginning of the list
     */
    public int previousIndex() {
      return (nextPos < 2) ? -1 : nextPos - 2;
    }

    // Modification Operations

    /**
     * Removes from the list the last element that was returned by {@link
     * #next} or {@link #previous} (optional operation).  This call can
     * only be made once per call to {@code next} or {@code previous}.
     * It can be made only if {@link #add} has not been
     * called after the last call to {@code next} or {@code previous}.
     *
     * @throws UnsupportedOperationException if the {@code remove}
     *         operation is not supported by this list iterator
     * @throws IllegalStateException if neither {@code next} nor
     *         {@code previous} have been called, or {@code remove} or
     *         {@code add} have been called after the last call to
     *         {@code next} or {@code previous}
     */
    public void remove() {
      if (lastPos < 0)
        throw new IllegalStateException("remove can only be called once per call to next()");
      for (int i = lastPos; i < size; i++) {
        itArray[i] = itArray[i+1];
      }
      nextPos--;
      lastPos = -1;
    }

    /**
     * Replaces the last element returned by {@link #next} or
     * {@link #previous} with the specified element (optional operation).
     * This call can be made only if neither {@link #remove} nor {@link
     * #add} have been called after the last call to {@code next} or
     * {@code previous}.
     *
     * @param e the element with which to replace the last element returned by
     *          {@code next} or {@code previous}
     * @throws IllegalStateException if neither {@code next} nor
     *         {@code previous} have been called, or {@code remove} or
     *         {@code add} have been called after the last call to
     *         {@code next} or {@code previous}
     */
    public void set(E e) {
      if (lastPos < 0)
        throw new IllegalStateException("set last element cannot be called if neither `next()` " +
            "nor `previous()` have been called, or `add()` have been called after last call to " +
            "`next()` or `previous()`");
      itArray[lastPos] = e;
      lastPos = -1;
    }

    /**
     * Inserts the specified element into the list (optional operation).
     * The element is inserted immediately before the element that
     * would be returned by {@link #next}, if any, and after the element
     * that would be returned by {@link #previous}, if any.  (If the
     * list contains no elements, the new element becomes the sole element
     * on the list.)  The new element is inserted before the implicit
     * cursor: a subsequent call to {@code next} would be unaffected, and a
     * subsequent call to {@code previous} would return the new element.
     * (This call increases by one the value that would be returned by a
     * call to {@code nextIndex} or {@code previousIndex}.)
     *
     * @param e the element to insert
     * @throws UnsupportedOperationException if the {@code add} method is
     *         not supported by this list iterator
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this list
     * @throws IllegalArgumentException if some aspect of this element
     *         prevents it from being added to this list
     */
    public void add(E e) {
      for (int i = size; i > nextPos; i--) {
        itArray[i+1] = itArray[i];
      }
      itArray[nextPos] = e;
      lastPos = nextPos;
      nextPos++;
    }

  }

  public int size() {
    return size;
  }


  // Modification Operations

  /**
   * {@inheritDoc}
   *
   * <p>This implementation always throws an
   * <tt>UnsupportedOperationException</tt>.
   *
   * @throws ClassCastException            {@inheritDoc}
   * @throws IllegalStateException         {@inheritDoc}
   */
  public boolean add(E e) {
    if (size == Integer.MAX_VALUE)
      throw new IllegalStateException("Error - this collection is already at maximum capacity");
    es[size++] = e;
    return true;
  }

  @Override
  public int hashCode() {
    int result = "ArrayList".hashCode();
    Integer i = 17;
    for (E e : this) {
      result += (e.hashCode() * i.hashCode());
      i++;
    }
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null)
      return false;
    if (!(obj instanceof ArrayList<?>))
      return false;
    @SuppressWarnings("unchecked")
    ArrayList<Object> that = (ArrayList<Object>) obj;
    if (this.size() != that.size())
      return false;
    {
      ListIterator<E> itThis = this.iterator();
      ListIterator<Object> itThat = that.iterator();
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
    String string = "ArrayList()";
    if (size > 0) {
      ListIterator<E> it = this.iterator();
      E e = null;
      StringBuilder sb = new StringBuilder(size);
      sb.append("ArrayList(");
      for (int i = 0, len = size; i < len; i++) {
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
