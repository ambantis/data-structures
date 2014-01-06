package com.ambantis.lists;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.NullPointerException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;

public class MyLinkedListTest {

  MyLinkedList<Integer> list0;
  MyLinkedList<Integer> list1;
  MyLinkedList<Integer> list5;

  @Before
  public void setup() {
    list0 = new MyLinkedList<Integer>();
    list1 = new MyLinkedList<Integer>(1);
    list5 = new MyLinkedList<Integer>(1,2,3,4,5);
  }

  @Test
  public void testSizeEmpty() {
    int expected = 0;
    int actual = list0.size();
    assertTrue("Failure, List() should have size = 0 but instead was " + actual, expected == actual);
  }

  @Test
  public void testSizeFive() {
    int expected = 5;
    int actual = list5.size();
    assertTrue("Failure, List(1,2,3,4,5) should have size = 5 but instead was " + actual,
        expected == actual);
  }

  @Test
  public void testEmptyTrue() {
    assertTrue("Failure - List() should be empty", list0.isEmpty());
  }

  @Test
  public void testEmptyFalse() {
    assertFalse("Failure - List(1,2,3,4,5) should not be empty", list5.isEmpty());
  }

  @Test(expected = NullPointerException.class)
  public void testContainsNull() {
    list5.contains(null);
  }

  @Test
  public void testContainsTrue() {
    assertTrue("Failure - List(1,2,3,4,5) should contain `3`", list5.contains(3));
  }

  @Test
  public void testContainsFalse() {
    assertFalse("Failure - List(1,2,3,4,5) should not contain `13`", list5.contains(13));
  }

  @Test(expected = NoSuchElementException.class)
  public void testIteratorNextOnEmpty() {
    Iterator<Integer> it = list0.iterator();
    it.next();
  }

  @Test(expected = IllegalStateException.class)
  public void testIteratorRemoveTwice() {
    Iterator<Integer> it = list5.iterator();
    it.next();
    it.remove();
    it.remove();
  }

  @Test
  public void testToArray() {
    Object[] expected = {1,2,3,4,5};
    System.out.println("list5 = " + list5.toString());
    Object[] actual = list5.toArray();
    System.out.print("actual elements were: ");
    for (Object o : actual) {
      Integer element = (Integer) o;
      System.out.print(element + " ");
    }
    System.out.println("");

    assertArrayEquals("Failure - List(1,2,3,4,5).toArray() should be Object(1,2,3,4,5) " +
        "but instead was " + actual.toString(), expected, list5.toArray());
  }
}
