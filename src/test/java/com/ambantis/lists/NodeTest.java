package com.ambantis.lists;

import static org.junit.Assert.assertTrue;

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
}
