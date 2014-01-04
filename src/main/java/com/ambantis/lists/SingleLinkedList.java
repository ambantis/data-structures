package com.ambantis.lists;

import java.lang.Iterable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SingleLinkedList<T> implements Iterable<T> {

  private Node<T> firstNode = null;

  public SingleLinkedList() {}

  public SingleLinkedList(T... ts) {
    if (ts != null && ts.length > 0) {
      Node[] nodes = new Node[ts.length];
      for (int i = 0; i < ts.length; i++)
        nodes[i] = new Node<T>(ts[i], null);
      for (int i = nodes.length-2; i > -1; i--)
        nodes[i].setNext(nodes[i+1]);
      firstNode = (Node<T>) nodes[0];
    }
  }

  public Iterator<T> iterator() {
    Iterator<T> it = new Iterator<T>() {
      private boolean removable = false;
      private Node<T> prevNode;
      private Node<T> lastNode;
      private Node<T> nextNode = firstNode;

      @Override
      public boolean hasNext() {
        return nextNode == null;
      }

      @Override
      public T next() {
        if (!hasNext())
          throw new NoSuchElementException("the list has no more elements");
        T data = nextNode.data();
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
            firstNode = firstNode.next();
          } else { //
            prevNode.setNext(nextNode);
          }
          removable = false;
        }
      }
    };
    return it;
  }

}
