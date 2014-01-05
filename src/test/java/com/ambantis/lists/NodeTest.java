package com.ambantis.lists;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.lang.NullPointerException;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;

@RunWith(JUnit4.class)
public class NodeTest {
  Node<Integer> listIntOne;
  Node<Integer> listIntTwo;
  Node<Integer> listIntThree;
  Node<Long> listLongOne;
  Node<Long> listLongTwo;
  Node<Long> listLongThree;
  Node<String> listStringOne;
  Node<String> listStringTwo;
  Node<String> listStringThree;

  @Before
  public void setup() {
    listIntOne = new Node<Integer>(1, null);
    listIntTwo = new Node<Integer>(2, listIntOne);
    listIntThree = new Node<Integer>(3, listIntTwo);
    listLongOne = new Node<Long>(1L, null);
    listLongTwo = new Node<Long>(2L, listLongOne);
    listLongThree = new Node<Long>(3L, listLongTwo);
    listStringOne = new Node<String>("pie", null);
    listStringTwo = new Node<String>("cutie", listStringOne);
    listStringThree = new Node<String>("hello", listStringTwo);
  }



  @Test(expected = NullPointerException.class)
  public void testNullConstructor() {
    new Node<Integer>(null, null);
  }

  @Test
  public void testValidData() {
    assertTrue("Failure - Node(1, null) should have data() = 1 but instead was " +
        listIntOne.data(), listIntOne.data().equals(1));
  }

  @Test
  public void testValidNext() {
    assertTrue("Failure - Node(2, Node(1,null)) should have next() of Node(1,null) but instead was " +
        listIntTwo.next(), listIntTwo.next().equals(listIntOne));
  }

  @Test
  public void testHashCodeIsSame() {
    assertTrue("Failure - Node(3L,Node(2L,Node(1L,null))) should equal Node(3L,Node(2L,Node(1L,null)))",
        listIntThree.hashCode() == listLongThree.hashCode());
  }

  @Test
  public void testHashCodeNotSame() {
    assertFalse("Failure - Node<String> shouldn't return same hashcode as Node<Integer>",
        listIntThree.hashCode() == listStringThree.hashCode());
  }

}
