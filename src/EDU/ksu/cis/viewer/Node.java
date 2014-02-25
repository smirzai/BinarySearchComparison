/*
 * Node.java     03/18/98
 *
 * Copyright (c) 1998, Rod Howell, All Rights Reserved.
 *
 */
package EDU.ksu.cis.viewer;

import java.awt.*;

/**
 * The objects used as the nodes of trees.  The contents of a Node are represented
 * by a String.  In addition, each Node has an <tt>int</tt> tag and a color, which
 * is used in drawing the node.  A Node is immutable.
 * @author      Rod Howell
 * @see BinaryTree
 * @see NodeDrawing
 */
public final class Node implements Cloneable {

  private final static int PADDING = 2;
  public int contents;
  private int tag;
  private NodeDrawing drawing;

  private Node() {}

  /**
   * Constructs a black Node with the given contents and a tag of 0.
   * @param    s        the contents of the Node
   */
  public Node(int s) {
    this(s, 0, Color.black);
  }

  /**
   * Constructs a black Node with the given contents and tag.
   * @param    s        the contents of the Node
   * @param    tag      the tag
   */
  public Node(int s, int tag) {
    this(s, tag, Color.black);
  }

  /**
   * Constructs a Node of the given color with the given contents and a tag of 0.
   * @param    s        the contents of the Node
   * @param    col      the color used to draw the Node
   */
  public Node(int  s, Color col) {
    this(s, 0, col);
  }

  /**
   * Constructs a Node of the given color with the given contents and tag.
   * @param    s        the contents of the Node
   * @param    tag      the tag
   * @param    col      the color used to draw the Node
   */
  public Node(int s, int tag, Color col) {
    contents = s;
    drawing = new NodeDrawing(contents, PADDING, col);
    this.tag = tag;
  }

  /**
   * Returns the contents of the Node.
   * @return            the contents of the Node
   */
  public int getContents() {
    return contents;
  }

  /**
   * Returns the tag of the Node.
   * @return            the tag of the Node
   */
  public int getTag() {
    return tag;
  }

  /**
   * Returns an immutable drawing of the Node in a Backdrop.
   * @return            a drawing of the Node
   * @see Backdrop
   */
  public Canvas getDrawing() {
    return new Backdrop(drawing);
  }

  /**
   * Because this structure is immutable, this method simply returns the tree
   * itself.
   * @return             this Node
   */
  public Object clone() {
    return this;
  }
}
