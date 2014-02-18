package com.ambantis.list;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.junit.Test;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ArrayListIteratorTest {

  ArrayList<Integer> listEmptyInts;
  ArrayList<Integer> listTwoInts;
  ArrayList<Integer> listFiveInts;

  ListIterator<Integer> it0;
  ListIterator<Integer> it2;
  ListIterator<Integer> it5;

  @Before
  public void setup() {
    listEmptyInts = new ArrayList<Integer>();
    listTwoInts = new ArrayList<Integer>();
    listFiveInts = new ArrayList<Integer>();

    listTwoInts.add(1);
    listTwoInts.add(2);
    listFiveInts.add(1);
    listFiveInts.add(2);
    listFiveInts.add(3);
    listFiveInts.add(4);
    listFiveInts.add(5);

    it0 = listEmptyInts.iterator();
    it2 = listTwoInts.iterator();
    it5 = listFiveInts.iterator();
  }

  @Test
  public void testArrayListIteratorHasNext1() {
    assertFalse("Failure - List(1,2).iterator() should not have next after two calls to next()",
        it0.hasNext());
  }

  @Test
  public void testArrayListIteratorNext() {
    it2.next();
    int expected = 2;
    int actual = it2.next();
    assertTrue("Failure - List(1,2).iterator() should have a value of `2` when called the second time",
        expected == actual);
  }

  @Test(expected = NoSuchElementException.class)
  public void testArrayListIteratorNextThrowsException() {
    it2.next();
    it2.next();
    it2.next();
  }

  @Test
  public void testArrayListIteratorHasPreviousTrue() {
    it2.next();
    it2.next();
    boolean expected = it2.hasPrevious();
    assertTrue("Failure - List(1,2).iterator().next()next().hasPrevious() should be true", expected);
  }

  @Test
  public void testArrayListIteratorHasPreviousFalse() {
    assertFalse("Failure - List().iterator().hasPrevious should be false", it0.hasPrevious());
  }

  @Test
  public void testArrayListIteratorPreviousTrue() {
    it2.next();
    it2.next();
    int expected = 1;
    int actual = it2.previous();
    assertTrue("Failure - List(1,2).iterator().next().next().previous() should be 1 " +
        "but instead was " + actual, expected == actual);
  }

  @Test(expected = NoSuchElementException.class)
  public void testArrayListIteratorPreviousFalse() {
    it2.next();
    it2.next();
    it2.previous();
    it2.previous();
  }

  @Test
  public void testArrayListIteratorNextIndex() {
    it2.next();
    int expected = 1;
    int actual = it2.nextIndex();
    assertTrue("Failure - List(1,2).iterator().next().nextIndex() should yield 1 " +
        "but instead was " + actual, expected == actual);
  }

  @Test
  public void testArrayListIteratorPreviousIndex() {
    it2.next();
    it2.next();
    int expected = 0;
    int actual = it2.previousIndex();
    assertTrue("Failure - List(1,2).iterator().next().next().previousIndex() should " +
        "yield 0 but instead was " + actual, expected == actual);
  }

  @Ignore
  public void testArrayListIteratorRemove() {
    int expected = 12;
    it5.next(); // 1
    it5.next(); // 2
    it5.next(); // 3
    it5.remove();
    System.out.println("listFiveInts = " + listFiveInts);
    int actual = 0;
    for (Integer i : listFiveInts) {
      actual += i;
    }
    //assertTrue("Failure - call to remove should have removed `3`", expected == actual);
  }

// @Test
// public void testArrayListIteratorSet() {
//   assertTrue("Failure - not implemented", false);
// }
//
// @Test
// public void testArrayListIteratorAdd() {
//   assertTrue("Failure - not implemented", false);
// }

}
