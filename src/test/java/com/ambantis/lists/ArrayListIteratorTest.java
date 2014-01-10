package com.ambantis.lists;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.junit.rules.ExpectedException;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ArrayListIteratorTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

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
  }

  @Test
  public void testArrayListIteratorHasNext1() {
    it0 = listEmptyInts.iterator();
    assertFalse("Failure - List(1,2).iterator() should not have next after two calls to next()",
        it0.hasNext());
  }

  @Test
  public void testArrayListIteratorNext() {
    it2 = listTwoInts.iterator();
    it2.next();
    int expected = 2;
    int actual = it2.next();
    assertTrue("Failure - List(1,2).iterator() should have a value of `2` when called the second time",
        expected == actual);
  }

  @Test
  public void testArrayListIteratorNextThrowsException() {
    it2 = listTwoInts.iterator();
    it2.next();
    it2.next();
    it2.next();
    thrown.expect(NoSuchElementException.class);
    thrown.expectMessage("the collection has no next element");
  }

  @Test
  public void testArrayListIteratorHasPreviousTrue() {
    it2 = listTwoInts.iterator();
    it2.next();
    assertTrue("Failure - List(1,2).iterator().next().hasPrevious should be true", it2.hasPrevious());
  }

  @Test
  public void testArrayListIteratorHasPreviousFalse() {
    it0 = listEmptyInts.iterator();
    assertFalse("Failure - List().iterator().hasPrevious should be false", it0.hasPrevious());
  }

  @Test
  public void testArrayListIteratorNextIndex() {
    assertTrue("Failure - not implemented", false);
  }

  @Test
  public void testArrayListIteratorPreviousIndex() {
    assertTrue("Failure - not implemented", false);
  }

  @Test
  public void testArrayListIteratorRemove() {
    assertTrue("Failure - not implemented", false);
  }

  @Test
  public void testArrayListIteratorSet() {
    assertTrue("Failure - not implemented", false);
  }

  @Test
  public void testArrayListIteratorAdd() {
    assertTrue("Failure - not implemented", false);
  }

}
