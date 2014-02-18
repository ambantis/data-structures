package com.ambantis.tree;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;

@RunWith(JUnit4.class)
public class BTreeTest {

  BTree tree123;

  @Before
  public void setup() {
    tree123 = new BTree();
    tree123.insert(2);
    tree123.insert(1);
    tree123.insert(3);
  }

  @Test
  public void testBuild123() {
    assertTrue("Failure - true should be true", true);
    assertTrue("Failure - BinarySearchTree(1,2,3) should contain 1",
        tree123.contains(1));
    assertTrue("Failure - BinarySearchTree(1,2,3) should contain 2",
        tree123.contains(2));
    assertTrue("Failure - BinarySearchTree(1,2,3) should contain 3",
        tree123.contains(3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddDuplicateToTree() {
    tree123.insert(1);
  }

  @Test
  public void testSize123() {
    assertTrue("Failure - BinarySearchTree(1,2,3) should have size == 3",
        tree123.size() == 3);
  }

  @Test
  public void testSizeBigger() {
    BTree tree = new BTree();
    tree.insert(5);
    tree.insert(3);
    tree.insert(7);
    tree.insert(2);
    tree.insert(4);
    tree.insert(6);
    tree.insert(10);
    tree.insert(9);
    tree.insert(11);
    tree.insert(1);
    tree.insert(8);
    assertTrue("Failure - Tree(5,3,7,2,4,6,10,9,11,1,8 should = 11",
        tree.size() == 11);
  }

}
