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
}
