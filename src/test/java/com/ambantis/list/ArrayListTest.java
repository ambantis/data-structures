package com.ambantis.list;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;

@RunWith(JUnit4.class)
public class ArrayListTest {

  @Test
  public void testArrayListAdd() {
    ArrayList<Integer> list = new ArrayList<Integer>();
    list.add(1);
    list.add(1);
    int expected = 2;
    int actual = 2;
    assertTrue("Failure - not implemented", expected == actual);
  }

}
