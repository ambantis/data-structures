package com.ambantis.lists;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.NullPointerException;
import java.util.ArrayList;
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
    Object[] actual = list5.toArray();
    assertArrayEquals("Failure - List(1,2,3,4,5).toArray() should be Object(1,2,3,4,5)",
        expected, list5.toArray());
  }

  @Test(expected = NullPointerException.class)
  public void testToAnotherArrayNull() {
    list5.toArray(null);
  }

  @Test(expected = ArrayStoreException.class)
  public void testToAnotherArrayInvalidType() {
    String[] strings = {"Doc", "Grumpy", "Happy", "Sleepy", "Bashful", "Sneezy", "Dopey"};
    list5.toArray(strings);
  }

  @Test
  public void testToAnotherArrayIntegerIntoNumber() {
    Number[] expected = new Number[1];
    expected[0] = (Number) 1;
    Number[] actual = new Number[1];
    list1.toArray(actual);
    assertArrayEquals("Failure - List<Integer>(1).toArray() of type Number should " +
        "be the same as the list cast to type Number[]", expected, actual);
  }

  @Test
  public void testToAnotherArraySameTypeAndSize() {
    Integer[] expected = {1,2,3,4,5};
    Integer[] actual = new Integer[5];
    list5.toArray(actual);
    assertArrayEquals("Failure - List(1,2,3,4,5).toArray() should be the same as " +
        "List(1,2,3,4,5).toArray(new Integer[5])", expected, actual);
  }

  @Test(expected = NullPointerException.class)
  public void testAddNull() {
    list5.add(null);
  }

  @Test
  public void testAddOne() {
    MyLinkedList<Integer> actual = new MyLinkedList<Integer>(2,3,4,5);
    actual.add(1);
    assertTrue("Failure - List(2,3,4,5).add(1) should equal List(1,2,3,4,5) " +
        "but instead was " + actual.toString(), list5.equals(actual));
  }

  @Test
  public void TestEqualsTrue() {
    MyLinkedList<Integer> duplicateOfList5 = new MyLinkedList<Integer>(1,2,3,4,5);
    assertTrue("Failure - List(1,2,3,4,5) and List(1,2,3,4,5) should be equal",
        list5.equals(duplicateOfList5));
  }


}
