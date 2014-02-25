/*
 * BinaryTree.java     03/18/98
 *
 * Copyright (c) 1998, Rod Howell, All Rights Reserved.
 *
 */

package EDU.ksu.cis.viewer;

import java.awt.*;

/**
 * An immutable binary tree that can draw itself.
 * @author      Rod Howell
 */
public final class BinaryTree implements Cloneable {
  private final static int VERTICAL_PADDING = 20;
  private final static int HORIZONTAL_PADDING = 8;
  public boolean empty;
  public Node root;
  public BinaryTree left, right;
  private TreeDrawing drawing;

  /**
   * Constructs an empty BinaryTree.
   */
  public BinaryTree() {
    empty = true;
    drawing = new TreeDrawing();
  }

  /**
   * Constructs a BinaryTree with the given root and children.  If either
   * BinaryTree parameter is <tt>null</tt>, an empty BinaryTree will be used
   * for that child.
   * @param    root     the root of the tree
   * @param    left     the left child
   * @param    right    the right child
   */
  public BinaryTree(Node root, BinaryTree left, BinaryTree right) {
    empty = false;
    this.root = root;
    if (left == null) {
      this.left = new BinaryTree();
    }
    else {
      this.left = left;
    }
    if (right == null) {
      this.right = new BinaryTree();
    }
    else {
      this.right = right;
    }
    /*
    
    Canvas children[] = new Canvas[2];
    children[0] = this.left.getDrawing();
    children[1] = this.right.getDrawing();
    drawing = new TreeDrawing(root.getDrawing(), children, 
			      HORIZONTAL_PADDING, VERTICAL_PADDING);
		*/	      
  }

  /**
   * Returns <tt>true</tt> if the tree is empty.
   * @return            whether the tree is empty.
   */
  public boolean isEmpty() {
    return empty;
  }

  /**
   * Returns the root of the tree.
   * @return            the root of the tree.
   * @exception         EmptyTreeException
   *                    If the tree is empty.
   */
  public Node getRoot() throws EmptyTreeException {
    if (empty) {
      throw new EmptyTreeException();
    }
    else {
      return root;
    }
  }

  /**
   * Returns the left child of the tree.
   * @return             The left child
   * @exception          EmptyTreeException
   *                     If the tree is empty.
   */
  public BinaryTree getLeftChild() throws EmptyTreeException {
    if (empty) {
      throw new EmptyTreeException();
    }
    else {
      return left;
    }
  }

  /**
   * Returns the right child of the tree.
   * @return             The right child
   * @exception          EmptyTreeException
   *                     If the tree is empty.
   */
  public BinaryTree getRightChild() throws EmptyTreeException {
    if (empty) {
      throw new EmptyTreeException();
    }
    else {
      return right;
    }
  }

  /**
   * Returns an immutable drawing of the tree in a Backdrop.
   * @return            the drawing of the tree.
   * @see Backdrop
   */
  public Canvas getDrawing() {
    return new Backdrop(drawing);
  }

  /** 
   * Because this structure is immutable, this method simply returns the tree
   * itself.
   * @return             this BinaryTree
   */
  public Object clone() {
    return this;
  }
}
