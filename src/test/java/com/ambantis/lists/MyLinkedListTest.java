package com.ambantis.lists;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.NullPointerException;
import java.lang.IllegalArgumentException;
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
  MyLinkedList<Integer> list4;
  MyLinkedList<Integer> list5;
  MyLinkedList<Integer> duplicateOfList5;
  ArrayList<String> dwarves;
  ArrayList<Integer> arrayList4;

  @Before
  public void setup() {
    list0 = new MyLinkedList<Integer>();
    list1 = new MyLinkedList<Integer>(5);
    list4 = new MyLinkedList<Integer>(1,2,3,4);
    list5 = new MyLinkedList<Integer>(1,2,3,4,5);
    duplicateOfList5 = new MyLinkedList<Integer>(1,2,3,4,5);
    dwarves = new ArrayList<String>(5);
    dwarves.add("Doc");
    dwarves.add("Grumpy");
    dwarves.add("Happy");
    dwarves.add("Sleepy");
    dwarves.add("Bashful");
    dwarves.add("Sneezy");
    dwarves.add("Dopey");
    arrayList4 = new ArrayList<Integer>(4);
    arrayList4.add(1);
    arrayList4.add(2);
    arrayList4.add(3);
    arrayList4.add(4);
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
    expected[0] = (Number) 5;
    Number[] actual = new Number[1];
    list1.toArray(actual);
    assertArrayEquals("Failure - List<Integer>(5).toArray() of type Number should " +
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

  @Test(expected = NullPointerException.class)
  public void testRemoveNull() {
    list5.remove(null);
  }

  @Test
  public void testRemoveElementNotFound() {
    boolean actual = list5.remove(101);
    assertTrue("Failure - List(1,2,3,4,5).remove(101) should return false",
        actual == false && list5.equals(duplicateOfList5));
  }

  @Test
  public void testRemoveElementFound() {
    boolean actual = list5.remove(5);
    assertTrue("Failure - List(1,2,3,4,5).remove(5) should return true and with a " +
        "value of List(1,2,3,4), but instead was " + list5.toString(),
        actual == true && list5.equals(list4));
  }

  @Test(expected = NullPointerException.class)
  public void testContainsAll() {
    list5.containsAll(null);
  }

  @Test
  public void testContainsAllStrings() {
    assertFalse("Failure - List(1,2,3,4,5) should not contain dwarves",
        list5.containsAll(dwarves));
  }

  @Test
  public void testContainsAllTrue() {
    assertTrue("Failure - List(1,2,3,4,5) should contain all of List(1,2,3,4)",
        list5.containsAll(arrayList4));
  }

  @Test(expected = NullPointerException.class)
  public void testAddAllNull() {
    list5.addAll(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddAllToItself() {
    list5.addAll(list5);
  }

  @Test
  public void testAddAllEmpty() {
    boolean actual = list5.addAll(list0);
    assertFalse("Failure - List(1,2,3,4,5).addAll(List()) should result in unchanged " +
        "list", actual);
  }

  @Test
  public void testAddAllValid() {
    boolean actual = list1.addAll(arrayList4);
    boolean hasSameElements = list5.containsAll(list1);
    boolean isSameSize = list5.size() == list1.size();
    assertTrue("Failure - List(5).addAll(List(1,2,3,4) should be the same elements and " +
        "size as list5", actual == true && hasSameElements && isSameSize);
  }

  @Test
  public void testRemoveAllNull() {
    boolean result = list5.removeAll(null);
    assertTrue("Failure - List(1,2,3,4,5).removeAll(null)) should return false and an " +
        "unchanged list", result == false && list5.equals(duplicateOfList5));
  }

  @Test
  public void testRemoveAllInvalid() {
    MyLinkedList<String> dwarvesList = new MyLinkedList<String>();
    dwarvesList.addAll(dwarves);
    boolean result = list5.removeAll(dwarvesList);
    assertTrue("Failure - List(1,2,3,4,5).removeAll(dwarvesList)) should return false and an " +
        "unchanged list", result == false && list5.equals(duplicateOfList5));
  }

  @Test
  public void testRemoveAllValid() {
    boolean result = list5.removeAll(list4);
    assertTrue("Failure - List(1,2,3,4,5).removeAll(List(1,2,3,4)) should return true " +
        "and it should now be List(5)", result == true && list5.equals(list1));
  }

  @Test(expected = NullPointerException.class)
  public void testRetainAllNull() {
    list5.retainAll(null);
  }

  @Test
  public void testRetainAllDifferentCollectionType() {
    MyLinkedList<String> dwarvesList = new MyLinkedList<String>();
    dwarvesList.addAll(dwarves);
    boolean result = list5.retainAll(dwarvesList);
    assertTrue("Failure - List(1,2,3,4,5).retainAll(dwarvesList) should return true " +
        "and it should be an empty list", result == true && list5.isEmpty());
  }

  @Test
  public void testRetainAllSimilarCollection() {
    boolean result = list5.retainAll(list4);
    assertTrue("Failure - List(1,2,3,4,5).retainAll(List(1,2,3,4)) should have a return " +
        "value of `true` and it should equal List(1,2,3,4",
        result == true && list5.equals(list4));
  }


  @Test
  public void testEqualsTrue() {
    assertTrue("Failure - List(1,2,3,4,5) and List(1,2,3,4,5) should be equal",
        list5.equals(duplicateOfList5));
  }



}
