package com.ambantis.lists;

import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;

@RunWith(JUnit4.class)
public class SingleLinkedListTest {

  @Test
  public void testSize() {
    SingleLinkedList<Integer> list = new SingleLinkedList<Integer>(1,2,3);
    int expected = 3;
    int actual = list.size();
    assertTrue("Failure - list(1,2,3) should have 3 but instead had " + actual + " items",
        expected == actual);
  }

  @Test
  public void testIteratorRemove() {
    SingleLinkedList<Integer> list = new SingleLinkedList<Integer>(1,2,3);
    Iterator<Integer> it = list.iterator();
    while (it.hasNext()) {
      it.next();
      it.remove();
    }
    int expected = 0;
    int actual = list.size();
    assertTrue("Failure - calling remove() three times on list(1,2,3) should result in " +
        "an empty list, instead it had a size of " + actual, expected == actual);
  }

}

