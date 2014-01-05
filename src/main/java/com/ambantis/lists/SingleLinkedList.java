package com.ambantis.lists;

import java.lang.Iterable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SingleLinkedList<E> implements Collection<E> {

  private Node<E> firstNode = null;

  public SingleLinkedList() {}

  public SingleLinkedList(E... es) {
    if (es != null && es.length > 0) {
      Node[] nodes = new Node[es.length];
      for (int i = 0; i < es.length; i++)
        nodes[i] = new Node<E>(es[i], null);
      for (int i = nodes.length-2; i > -1; i--)
        nodes[i].setNext(nodes[i+1]);
      firstNode = nodes[0];
    }
  }

  @Override
  public int size() {
    int size = 0;
    for (E element : this) {
      size++;
    }
    return size;
  }

  @Override
  public boolean isEmpty() {
    return firstNode == null;
  }

  @Override
  public boolean contains(Object o) {
    if (o == null)
      throw new NullPointerException("This collection does not permit null elements");
    E that = (E) o;
    Iterator<E> it = this.iterator();
    while (it.hasNext()) {
      if (it.next().equals(that))
        return true;
    }
    return false;
  }

  @Override
  public Iterator<E> iterator() {
    Iterator<E> it = new Iterator<E>() {
      private boolean removable = false;
      private Node<E> prevNode;
      private Node<E> lastNode;
      private Node<E> nextNode = firstNode;

      @Override
      public boolean hasNext() {
        return nextNode != null;
      }

      @Override
      public E next() {
        if (!hasNext())
          throw new NoSuchElementException("the list has no more elements");
        E data = nextNode.data();
        removable = true;
        prevNode = lastNode;
        lastNode = nextNode;
        nextNode = nextNode.next();
        return data;
      }

      @Override
      public void remove() {
        if (removable) { // if lastNode is null, then user attempting to call remove twice
          if (prevNode == null) { // we are trying to remove the first element of the collection
            firstNode = nextNode; // so reset everything
            lastNode = null;
            prevNode = null;
          } else { //
            prevNode.setNext(nextNode);
          }
          removable = false;
        }
      }
    };
    return it;
  }

  @Override
  public Object[] toArray() {
    int size = this.size();
    Object[] result = new Object[size];
    int i = 0;
    for (Iterator<E> it = this.iterator(); it.hasNext(); i++) {
      result[i] = (Object) it.next();
    }
    return result;
  }

  @Override
  public <T> T[] toArray(T[] a) {
    if (a == null)
      throw new NullPointerException("the specified array is null");
    
  }

  @Override
  public boolean add(E e) {
    if (e == null)
      throw new NullPointerException("the specified array is null");
    int oldSize = this.size();
    firstNode = new Node(e, firstNode);
    if (oldSize == this.size() - 1)
      return true;
    else
      return false;
  }

  @Override
  public boolean remove(E e) {
    if (e == null)
      throw new NullPointerException("the specified array is null");
    Iterator<E> it = this.iterator();
    while (it.hasNext()) {
      if (it.next().equals(e)) {
        it.remove();
        return true;
      }
    }
    return false;
  }

  

}
