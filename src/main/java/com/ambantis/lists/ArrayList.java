package com.ambantis.lists;

import java.lang.IllegalStateException;
import java.lang.Integer;
import java.lang.Object;
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

    // only objects of type <E> will go into array
    @SuppressWarnings("unchecked")
    private Itr() {
      itArray = (E[]) new Object[capacity];
      for (int i = 0; i < es.length; i++)
        itArray[i] = es[i];
      nextPos = 0;
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
      return itArray[nextPos++];
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
      System.out.println("call to previous(), itPos = " + nextPos);
      return itArray[--nextPos-1];
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
      throw new UnsupportedOperationException("This feature has not yet been implemented");
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
      throw new UnsupportedOperationException("This feature has not yet been implemented");
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
      throw new UnsupportedOperationException("This feature has not yet been implemented");
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
     * @throws UnsupportedOperationException if the {@code set} operation
     *         is not supported by this list iterator
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this list
     * @throws IllegalArgumentException if some aspect of the specified
     *         element prevents it from being added to this list
     * @throws IllegalStateException if neither {@code next} nor
     *         {@code previous} have been called, or {@code remove} or
     *         {@code add} have been called after the last call to
     *         {@code next} or {@code previous}
     */
    public void set(E e) {
      throw new UnsupportedOperationException("This feature has not yet been implemented");
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
      throw new UnsupportedOperationException("This feature has not yet been implemented");
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




}
