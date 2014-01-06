package com.ambantis.lists;

import static org.junit.Assert.assertTrue;


import java.lang.IllegalArgumentException;
import java.lang.NullPointerException;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;

@RunWith(JUnit4.class)
public class NodeTest {

  Node<Integer> node1;
  Node<Integer> node2;
  Node<Integer> node3;

  @Before
  public void setup() {
    node1 = new Node<Integer>(1, null);
    node2 = new Node<Integer>(2, node1);
    node3 = new Node<Integer>(3, node2);
  }

  @Test(expected = NullPointerException.class)
  public void testNodeConstructorNullData() {
    new Node<Integer>(null, null);
  }

  @Test
  public void testNodeData() {
    int expected = 2;
    int actual = node2.data();
    assertTrue("Failure - Node(2,Node(1,null).data() should equal 2 but instead was " + actual,
        expected == actual);
  }

  @Test
  public void testNodeNext() {
    Node<Integer> result = node2.next();
    assertTrue("Failure - Node(2,Node(1,null).next() should equal Node(1,null) but instead was " +
        result, result.equals(node1));
  }

  @Test
  public void testNodeSetNext() {
    node3.setNext(node1);
    Node<Integer> result = node3.next();
    assertTrue("Failure - Node3(3,Node(2,Node(1,null))).setNext(node1) should result in a value " +
        "of Node(1,null) as new Node but instead was " + result, node1 == result);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRecursiveSetNext() {
    node3.setNext(new Node<Integer>(100,new Node<Integer>(101,node3)));
  }

  @Test
  public void testEquals() {
    Node<Integer> otherNode2 = new Node<Integer>(2, new Node<Integer>(1,null));
    assertTrue("Failure - Node(2,Node(1,null)) should equal itself", otherNode2.equals(node2));
  }

  @Test
  public void testSameHashCode() {
    Node<Integer> otherNode2 = new Node<Integer>(2, new Node<Integer>(1,null));
    assertTrue("Failure - Node(2,Node(1,null)) should have same hashCode as itself",
        otherNode2.hashCode() ==  node2.hashCode());
  }

  @Test
  public void testToString() {
    String expected = "Node(3,Node(2,Node(1,null)))";
    assertTrue("Failure - Node3(3,Node(2,Node(1,null))) does not have expected toString() " +
        "representation but instead was: " + node3.toString(), expected.equals(node3.toString()));
  }
}
