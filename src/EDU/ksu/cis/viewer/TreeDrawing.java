/*
 * TreeDrawing.java     03/18/98
 *
 * Copyright (c) 1998, Rod Howell, All Rights Reserved.
 *
 */

package EDU.ksu.cis.viewer;

import java.awt.*;

/**
 * A drawing of a Tree.
 * @author      Rod Howell
 * @see BinaryTree
 * @see BinarySearchTree
 * @see AVLTree
 * @see SplayTree
 * @see NodeDrawing
 */
public class TreeDrawing extends Canvas {
  private Canvas root, children[];
  private Dimension rootSize, childrenSize, size;
  private int hspace, vspace;

  /**
   * 0 height and 0 width.
   */
  public static final Dimension ZERO_SIZE = new Dimension(0, 0);

  /**
   * Constructs a drawing of an empty tree with a height and with of 0.
   */
  public TreeDrawing() {
    this(null, null, 0, 0);
  }

  /**
   * Constructs a drawing of a tree with the given root and children and the
   * given horizontal spacing between the children and the given vertical spacing
   * between the root and its children.
   * @param     root        a drawing of the root without borders
   * @param     children    drawings of each of the children
   * @param     hspace      the number of pixels to insert between children
   * @param     vspace      the vertical pixel distance between the root and
   *                        its children
   */
  public TreeDrawing(Canvas root, Canvas children[], int hspace, int vspace) {
    this.root = root;
    this.children = children;
    this.hspace = hspace;
    this.vspace = vspace;
    computeSize();
  }

  private synchronized void computeSize() {
    rootSize = root == null ? new Dimension(0, 0) : root.getSize();
    childrenSize = new Dimension(0, 0);
    if (children != null && children.length > 0) {
      if (children[0] != null) {
	Dimension d = children[0].getSize();
	childrenSize.width += d.width;
	childrenSize.height = Math.max(childrenSize.height, d.height);
      }
      for (int i = 1; i < children.length; i++) {
	childrenSize.width += hspace;
	if (children[i] != null) {
	  Dimension d = children[i].getSize();
	  childrenSize.width += d.width;
	  childrenSize.height = Math.max(childrenSize.height, d.height);
	}
      }
    }
    size = new Dimension(Math.max(rootSize.width, childrenSize.width),
			 rootSize.height + vspace + childrenSize.height);
  }

  /**
   * Returns the preferred size of the drawing.  This size is always used.
   * @return               the preferred size of the drawing
   */
  public Dimension getPreferredSize() {
    return size;
  }

  /**
   * Returns the size of the drawing.  This is always the same as the preferred
   * size.
   * @return                the size of the drawing
   */
  public Dimension getSize() {
    return size;
  }

  /**
   * Updates this drawing on the screen.  This method is called in response
   * to a call to <tt>repaint</tt>.  This method does not clear the screen,
   * but immediately calls <tt>paint</tt>.
   * @param    g        the graphics context used in painting
   * @see #paint
   */
  public void update(Graphics g) {
    paint(g);
  }

  /** 
   * Paints the TreeDrawing.  This method is called when the contents
   * of the TreeDrawing should be painted in response to the component
   * first being shown or damage needing repair.  The clip rectangle
   * in the Graphics parameter will be set to the area which needs
   * to be painted.
   * @param       g         The graphics context to use for painting
   */
  public void paint(Graphics g) {
    if (g != null && g.getClipBounds().intersects(new Rectangle(size))) {
      Color col = g.getColor();
      g.setColor(getForeground());
      Point rootLoc = new Point((size.width - rootSize.width) / 2, 0);
      g.translate(rootLoc.x, rootLoc.y);
      root.paint(g);
      g.drawRect(0, 0, rootSize.width - 1, rootSize.height - 1);
      g.translate(-rootLoc.x, -rootLoc.y);
      Point edgeStart = new Point(rootLoc.x + rootSize.width / 2, rootSize.height);
      if (children != null) {
	Point loc = new Point((size.width - childrenSize.width) / 2,
			      rootSize.height + vspace);
	for (int i = 0; i < children.length; i++) {
	  if (children[i] != null 
	      && !children[i].getSize().equals(ZERO_SIZE)) {
	    g.translate(loc.x, loc.y);
	    children[i].paint(g);
	    g.translate(-loc.x, -loc.y);
	    int cw = children[i].getSize().width;
	    g.drawLine(edgeStart.x, edgeStart.y, loc.x + cw / 2, loc.y);
	    loc.translate(cw, 0);
	  }
	  loc.translate(hspace, 0);
	}
      }
      g.setColor(col);
    }
  }
}
