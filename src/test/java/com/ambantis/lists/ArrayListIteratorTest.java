package com.ambantis.lists;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;

@RunWith(JUnit4.class)
public class ArrayListIteratorTest {

  ArrayList<Integer> listEmptyInts;
  ArrayList<Integer> listTwoInts;
  ArrayList<Integer> listFiveInts;

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
    Iterator<Integer> it = listEmptyInts.iterator();
    assertFalse("Failure - List(1,2).iterator() should not have next after two calls to next()",
        it.hasNext());
  }

  @Test
  public void testArrayListIteratorNext() {
    assertTrue("Failure - not implemented", false);
  }

  @Test
  public void testArrayListIteratorHasPrevious() {
    assertTrue("Failure - not implemented", false);
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
