package com.ambantis.lists;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.IllegalStateException;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.Iterator;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;

@RunWith(JUnit4.class)
public class MyLinkedListTest {


  MyLinkedList<Integer> emptyList;
  MyLinkedList<Integer> list;

  @Before
  public void setup() {
    emptyList = new MyLinkedList<Integer>();
    list = new MyLinkedList<Integer>(1,2,3,4,5);
  }


  @Test
  public void testSizeZero() {
    assertTrue("Failure, List() should have size = 0", emptyList.size() == 0);
  }

  @Test
  public void testSizeFive() {
    int expected = 5;
    int actual = list.size();
    assertTrue("Failure - List(1,2,3,4,5) should have size = 5 but instead was " + expected,
        expected == actual);
  }

  @Test
  public void testIsEmptyTrue() {
    assertTrue("Failure - List() should be empty", emptyList.isEmpty());
  }

  @Test
  public void testIsEmptyFalse() {
    assertFalse("Failure - List(1,2,3,4,5) should not be empty", list.isEmpty());
  }

  @Test(expected = NullPointerException.class)
  public void testContainsNull() {
    list.contains(null);
  }

  @Test
  public void testContains1() {
    assertTrue("Failure - List(1,2,3,4,5) should contain `3`", list.contains(3));
  }

  @Test
  public void testContains2() {
    assertFalse("Failure - List(1,2,3,4,5) should not contain `7`", list.contains(7));
  }

  @Test
  public void testContains3() {
    assertFalse("Failure - List(1,2,3,4,5) should not contain `hello`", list.contains("hello"));
  }

  @Test
  public void testAddOne() {
    list.add(6);
    int expected = 6;
    int actual = list.size();
    assertTrue("Failure - List(1,2,3,4,5).add(6) should have size = 6 but instead was " + actual,
        expected == actual);
  }

  @Test
  public void testIteratorHasNextNone() {
    Iterator<Integer> it = emptyList.iterator();
    assertFalse("Failure - iterator of List() should return false to hasNext()", it.hasNext());
  }

  @Test
  public void testIteratorHasNextSome() {
    Iterator<Integer> it = list.iterator();
    assertTrue("Failure - iterator of List(1) should return true to hasNext()", it.hasNext());
  }

  @Test
  public void testIteratorNextHasExpectedValue() {
    int expected = 3;
    Iterator<Integer> it = list.iterator();
    it.next();
    it.next();
    int actual = it.next();
    assertTrue("Failure - iterator of List(1,2,3,4,5) should return 3 on 3rd call to next() " +
        "but instead returned " + actual, expected == actual);
  }

  @Test(expected = IllegalStateException.class)
  public void testIteratorRemoveWithoutCallToNext() {
    Iterator<Integer> it = list.iterator();
    it.remove();
  }

  @Test(expected = IllegalStateException.class)
  public void testIteratorCallRemoveTwice() {
    Iterator<Integer> it = list.iterator();
    it.next();
    it.remove();
    it.remove();
  }

  @Test
  public void testToArray() {
    Object[] expected = {
      (Object) Integer.valueOf("1"),
      (Object) Integer.valueOf("2"),
      (Object) Integer.valueOf("3"),
      (Object) Integer.valueOf("4"),
      (Object) Integer.valueOf("5")
    };
  }

  @Test
  public void testToIntegerArray() {
    Integer[] expected = {1,2,3,4,5};
    Integer[] actual = new Integer[5];
    actual = list.toArray(actual);
    assertArrayEquals("Failure - List(1,2,3,4,5).toArray should be Array(1,2,3,4,5) instead of " +
        actual.toString(), expected, actual);
  }

  @Test
  public void testToIntegerArrayExtrasToNull() {
    Integer[] expected = {1,2,3,4,5, null, null};
    Integer[] actual = new Integer[7];
    actual = list.toArray(actual);
    assertArrayEquals("Failure - List(1,2,3,4,5).toArray should be Array(1,2,3,4,5) instead of " +
        actual.toString(), expected, actual);
  }

  @Test(expected = NullPointerException.class)
  public void testToNullArray() {
    list.toArray(null);
  }

  @Test(expected = ArrayStoreException.class)
  public void testIncompatiblToArray() {
    String[] strings = new String[7];
    list.toArray(strings);
  }

  @Test(expected = NullPointerException.class)
  public void testAddNullValue() {
    list.add(null);
  }

  @Test
  public void testAddOneValue() {
    list.add(10);
    Iterator<Integer> it = list.iterator();
    int expected = 10;
    int actual = it.next();
    assertTrue("Failure - adding 10 to List(1,2,3,4,5) should result in List(10,1,2,3,4,5) " +
        "instead resulted in head of " + actual, expected == actual);
  }

  @Test(expected = NullPointerException.class)
  public void testRemoveNull() {
    list.remove(null);
  }

  @Test
  public void testRemoveOneItHas() {
    boolean expected = true;
    boolean actual = list.remove(3);
    assertTrue("Failure - List(1,2,3,4,5).remove(3) should result in a list of size 4 with " +
        "return value of true", expected == actual && list.size() == 4);
  }

  @Test
  public void testRemoveOneItHasNot() {
    boolean expected = false;
    boolean actual = list.remove(42);
    assertTrue("Failure - List(1,2,3,4,5).remove(42) should result in list unchanged with a " +
        "return value of `false`", expected == actual && list.size() == 5);
  }

  @Test(expected = NullPointerException.class)
  public void testContainsAllNull() {
    list.containsAll(null);
  }

  @Test
  public void testContainsAllTrue() {
    ArrayList<Integer> other = new ArrayList<Integer>(5);
    other.add(1);
    other.add(2);
    other.add(3);
    other.add(4);
    other.add(5);
    assertTrue("Failure - List(1,2,3,4,5) should equal ArrayList(1,2,3,4,5)",
        list.containsAll(other));
  }

  @Test
  public void testDoesNotContainAll() {
    ArrayList<Integer> other = new ArrayList<Integer>(5);
    other.add(1);
    other.add(2);
    other.add(3);
    other.add(4);
    other.add(500);
    assertFalse("Failure - List(1,2,3,4,5) should not equal ArrayList(1,2,3,4,500)",
        list.containsAll(other));
  }

  @Test
  public void testContainsAllOtherType() {
    ArrayList<String> other = new ArrayList<String>(5);
    other.add("hello");
    other.add("java");
    other.add("and");
    other.add("junit");
    other.add("hamcrest");
    assertFalse("Failure - List(1,2,3,4,5) should not equal a ArrayList<String>",
        list.containsAll(other));
  }

  @Test(expected = NullPointerException.class)
  public void testAddAllNull() {
    list.addAll(null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testAddAllToItself() {
    list.addAll(list);
  }

  @Test
  public void testAddAllMoreIntegers() {
    ArrayList<Integer> other = new ArrayList<Integer>(5);
    other.add(10);
    other.add(20);
    other.add(30);
    other.add(40);
    other.add(50);
    boolean result = list.addAll(other);
    assertTrue("Failure - List(1,2,3,4,5).addAll(ArrayList(10,20,30,40,50) should have size " +
        "of 10", list.size() == 10 && result == true);
  }

  @Test(expected = NullPointerException.class)
  public void testRemoveAllNull() {
    list.removeAll(null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testRemoveAllFromItself() {
    list.removeAll(list);
  }

  @Test
  public void testRemoveAllValid() {
    MyLinkedList<Integer> expected = new MyLinkedList<Integer>(2,4);
    ArrayList<Integer> other = new ArrayList<Integer>();
    other.add(1);
    other.add(3);
    other.add(5);
    boolean result = list.removeAll(other);
    assertTrue("Failure - List(1,2,3,4,5).removeAll(new ArrayList(1,3,5) should have size of 3 " +
        "and be List(2,4) and have return value of `true`",
        result == true &&
        list.equals(expected));
  }

  @Test
  public void testRemoveAllNoOverlap() {
    MyLinkedList<Integer> expected = new MyLinkedList<Integer>(1,2,3,4,5);
    ArrayList<Integer> other = new ArrayList<Integer>();
    other.add(10);
    other.add(30);
    other.add(50);
    boolean result = list.removeAll(other);
    assertTrue("Failure - List(1,2,3,4,5).removeAll(new ArrayList(10,30,50) should have size of 5 " +
        "and be List(1,2,3,4,5) and have return value of `false`",
        result == false &&
        list.equals(expected));
  }

  @Test(expected = NullPointerException.class)
  public void testRetainAllNull() {
    list.retainAll(null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testRetainellFromItself() {
    list.retainAll(list);
  }

  @Test
  public void testRetainAllValid() {
    MyLinkedList<Integer> expected = new MyLinkedList<Integer>(1,3,5);
    ArrayList<Integer> other = new ArrayList<Integer>();
    other.add(1);
    other.add(3);
    other.add(5);
    boolean result = list.retainAll(other);
    assertTrue("Failure - List(1,2,3,4,5).retainAll(new ArrayList(1,3,5) should have size of 3 " +
        "and be List(1,3,5) and have return value of `true`",
        result == true &&
        list.equals(expected));
  }

  @Test
  public void testRetainAllNoOverlap() {
    ArrayList<Integer> other = new ArrayList<Integer>();
    other.add(10);
    other.add(30);
    other.add(50);
    boolean result = list.retainAll(other);
    assertTrue("Failure - List(1,2,3,4,5).retainAll(new ArrayList(10,30,50) should have size of 0 " +
        "and be List() and have return value of `false`",
        result == true &&
        list.isEmpty());
  }

  @Test
  public void testRetainAllWithAllOverlap() {
    ArrayList<Integer> other = new ArrayList<Integer>(5);
    other.add(1);
    other.add(2);
    other.add(3);
    other.add(4);
    other.add(5);
    boolean result = list.retainAll(other);
    assertTrue("Failure - List(1,2,3,4,5).retainAll(ArrayList(1,2,3,4,5) should remain unchanged " +
        "and have a return value of false",
        result == false &&
        list.size() == 5);
  }

  @Test
  public void testClear() {
    list.clear();
    assertTrue("Failure - List(1,2,3,4,5).clear() should result in list.isEmpty() as true",
        list.isEmpty());
  }

  @Test
  @SuppressWarnings("checked")
  public void testEqualsTrue() {
    Object same = (Object) new MyLinkedList<Integer>(1,2,3,4,5);
    assertTrue("Failure - itentical lists should be equal", list.equals(same));
  }

  @Test
  public void testEqualsFalse() {
    Object disordered = (Object) new MyLinkedList<Integer>(1,3,2,5,4);
    assertFalse("Failure - itentical lists with different orderings should not be equal",
        list.equals(disordered));
  }

  @Test
  public void testHashCodeSame() {
    MyLinkedList<Integer> same = new MyLinkedList<Integer>(1,2,3,4,5);
    assertTrue("Failure - two lists with the same elements should be equal",
        list.hashCode() == same.hashCode());
  }

  @Test
  public void testHashCodeDifferent() {
    Object disordered = new MyLinkedList<Integer>(1,3,2,5,4);
    assertFalse("Failure - itentical lists with different orderings should not be equal",
        list.hashCode() == disordered.hashCode());
  }

}
