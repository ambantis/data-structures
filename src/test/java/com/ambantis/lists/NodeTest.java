package com.ambantis.lists;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.lang.NullPointerException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;

@RunWith(JUnit4.class)
public class NodeTest {

  @Test
  public void testNodeConstructorValid() {
    String hello = "hello Node";
    Node<String> helloNode = new Node<String>(hello, null);
    assertTrue("Failure - node value should be `hello Node`", helloNode.data().equals(hello));
  }

  @Test(expected = NullPointerException.class)
  public void testNodeConstructorInvalid() {
    Node<String> thisShouldNotWork = new Node<String>(null, null);
  }

  @Test
  public void testNodeEqualsTrue1() {
    Node<Integer> node1 = new Node<Integer>(1, null);
    Node<Integer> node2 = new Node<Integer>(1, null);
    assertTrue("Failure - two nodes with null tail ts and data = 1 should be equal",
        node1.equals(node2));
  }

  @Test
  public void testNodeEqualsTrue2() {
    Node<String> node1 = new Node<String>("hello", new Node<String>("world", null));
    Node<String> node2 = new Node<String>("hello", new Node<String>("world", null));
    assertTrue("Failure - two nodes with `hello` `world` ts should be equal",
        node1.equals(node2));
  }

  @Test
  public void testNodeEqualsFalse1() {
    Node<String> node1 = new Node<String>("1", null);
    Node<Integer> node2 = new Node<Integer>(1, null);
    assertFalse("Failure - two nodes with unlike type should not be equal",
        node1.equals(node2));
  }

  @Test
  public void testNodeEqualsFalse2() {
    Node<String> node1 = new Node<String>("hello", new Node<String>("world", null));
    Node<String> node2 = new Node<String>("hello", null);
    assertFalse("Failure - two nodes with different `rest()` should not be equal",
        node1.equals(node2));
  }

  @Test
  public void testNodeEqualsFalse3() {
    Node<Integer> node4a = new Node<Integer>(4,null);
    Node<Integer> node3a = new Node<Integer>(3,node4a);
    Node<Integer> node2a = new Node<Integer>(2,node3a);
    Node<Integer> node1a = new Node<Integer>(1,node2a);
    Node<Double> node4b = new Node<Double>(4.0,null);
    Node<Double> node3b = new Node<Double>(3.0,node4b);
    Node<Double> node2b = new Node<Double>(2.0,node3b);
    Node<Double> node1b = new Node<Double>(1.0,node2b);
    assertFalse("Failure - two Nodes Int & Double should not be equal", node1a.equals(node1b));
  }

  @Test
  public void testNodeEqualsFalse4() {
    Node<Integer> node4a = new Node<Integer>(4,null);
    Node<Integer> node3a = new Node<Integer>(3,node4a);
    Node<Integer> node2a = new Node<Integer>(2,node3a);
    Node<Integer> node1a = new Node<Integer>(1,node2a);
    Node<Long> node4b = new Node<Long>(4L,null);
    Node<Long> node3b = new Node<Long>(3L,node4b);
    Node<Long> node2b = new Node<Long>(2L,node3b);
    Node<Long> node1b = new Node<Long>(1L,node2b);
    assertFalse("Failure - two Nodes Integer & Long should not be equal", node1a.equals(node1b));
  }


  public void testNodeSameHashTrue1() {
    Node<Integer> node1 = new Node<Integer>(1, null);
    Node<Integer> node2 = new Node<Integer>(1, null);
    assertTrue("Failure - two nodes with null tail ts and data = 1 should have same hashCode",
        node1.hashCode() == node2.hashCode());
  }

  @Test
  public void testNodeSameHashTrue2() {
    Node<String> node1 = new Node<String>("hello", new Node<String>("world", null));
    Node<String> node2 = new Node<String>("hello", new Node<String>("world", null));
    assertTrue("Failure - two nodes with `hello` `world` ts should have same hashCode",
        node1.hashCode() == node2.hashCode());
  }

  @Test
  public void testNodeToString() {
    String expected = "Node(1,Node(2,Node(3,Node(4,null))))";
    Node<Integer> node4 = new Node<Integer>(4,null);
    Node<Integer> node3 = new Node<Integer>(3,node4);
    Node<Integer> node2 = new Node<Integer>(2,node3);
    Node<Integer> node1 = new Node<Integer>(1,node2);
    assertTrue("Failure - node1 = " + node1.toString(), node1.toString().equals(expected));
  }

}
