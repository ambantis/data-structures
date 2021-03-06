package com.ambantis.tree;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;

@RunWith(JUnit4.class)
public class BSTreeTest {

  BSTree tree123;
  BSTree treeEleven;
  BSTree treeEmpty;

  @Before
  public void setup() {
    tree123 = new BSTree(2,1,3);
    treeEleven = new BSTree(5,3,7,2,4,6,10,9,11,1,8);
    treeEmpty = new BSTree();
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
    assertTrue("Failure - Tree(5,3,7,2,4,6,10,9,11,1,8 should = 11",
        treeEleven.size() == 11);
  }

  @Test
  public void testMaxDepth123() {
    int expected = 2;
    int actual = tree123.maxDepth();
    assertTrue("Failure - BSTree(2,1,3) should have maxDepth = 2 but instead was " +
        actual, expected == actual);
  }

  @Test
  public void testMaxDepthEleven() {
    int expected = 5;
    int actual = treeEleven.maxDepth();
    assertTrue("Failure - Tree(5,3,7,2,4,6,10,9,11,1,8) should equal 5 but instead was " +
        actual, expected == actual);
  }

  @Test(expected = NullPointerException.class)
  public void testMinValueEmptyTree() {
    treeEmpty.minValue();
  }

  @Test
  public void testMinValue123() {
    int expected = 1;
    int actual = tree123.minValue();
    assertTrue("Failure - Tree(2,1,3) should have minValue = 1 but instead was " +
        actual, expected == actual);
  }

  @Test
  public void testMinValueEleven() {
    int expected = 1;
    int actual = tree123.minValue();
    assertTrue("Failure - Tree(5,3,7,2,4,6,10,9,11,1,8) should have minValue = 11 " +
        "but instead was " + actual, expected == actual);
  }

  @Test
  public void testprintEmptyTree() {
    String expected = "()";
    String actual = treeEmpty.printTree();
    assertTrue("Failure - Tree().printTree() should be `()` but instead was " + actual,
        expected.equals(actual));
  }

  @Test
  public void testPrintTree123() {
    String expected = "(1 2 3)";
    String actual = tree123.printTree();
    assertTrue("Failure - Tree(2,1,3).printTree() should be `(1 2 3)'` but instead was " +
        actual + " with a length of " + actual.length(), expected.equals(actual));
  }

  @Test
  public void testPrintTreeEleven() {
    String expected = "(1 2 3 4 5 6 7 8 9 10 11)";
    String actual = treeEleven.printTree();
    assertTrue("Failure - Tree(5,3,7,2,4,6,10,9,11,1,8).printTree() should be " +
        "`(1 2 3 4 5 6 7 8 9 10 11)` but instead was " + actual, expected.equals(actual));
  }
}
