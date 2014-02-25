/*
 * BSTInterface.java     03/18/98
 *
 * Copyright (c) 1998, Rod Howell, All Rights Reserved.
 *
 */

package EDU.ksu.cis.viewer;

import java.awt.*;

/** 
 * This interface is implemented by each of the data structures whose functionality
 * is equivalent to a binary search tree.  Functionality includes the ability to
 * insert and delete String keys, to clone itself, and to draw itself.
 * @author      Rod Howell
 * @see         AVLTree
 * @see         BinarySearchTree
 * @see         SplayTree
 */
public interface BSTInterface extends Cloneable {

  /** 
   * Inserts a key into the tree and returns the result.  If the key
   * is already in the tree, an identical tree is returned.
   * @param     key     the key to be inserted
   * @return            the tree resulting from the insertion
   * @exception         NullPointerException
   *                    If key is <tt>null</tt>
   */
  public BSTInterface put(int  key) throws NullPointerException;
  public BSTInterface put(int  key, int tag) throws NullPointerException;
  
  public Integer getTag(int  key);

  /**
   * Removes a key from the tree and returns the result.  If the key
   * is not in the tree, an identical tree is returned.
   * @param     key     the key to be removed
   * @return            the tree resulting from the deletion
   * @exception         NullPointerException
   *                    If key is <tt>null</tt>
   */
  public BSTInterface remove(int  key) throws NullPointerException;

  /**
   * Gets a Canvas containing a drawing of the tree.
   * @return            the drawing of the tree.
   */
  public Canvas getDrawing();

  /**
   * Gets a StackInterface appropriate for recording instances of this tree.
   * @return            an empty instance of StackInterface
   */
  public StackInterface getStack();

  /**
   * Returns a clone of this tree.
   * @return            a clone of this tree
   */
  public Object clone();
}
