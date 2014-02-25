/*
 * NodeDrawing.java     03/18/98
 *
 * Copyright (c) 1998, Rod Howell, All Rights Reserved.
 *
 */

package EDU.ksu.cis.viewer;

import java.awt.*;

/**
 * A drawing of a Node without a border.
 * @author      Rod Howell
 * @see Node
 * @see TreeDrawing
 */
public class NodeDrawing extends Canvas {
  private int contents;
  private Dimension size;
  private int padding;
  private static final Font FONT = new Font("Monospaced", Font.PLAIN, 12);
  private final FontMetrics METRICS = getFontMetrics(FONT);
  private Color theColor;

  private NodeDrawing() {}

  /**
   * Constructs a NodeDrawing with the given contents in the given color, and with
   * the given padding between the contents and the edges of the NodeDrawing.
   * @param     contents    the contents of the node
   * @param     padding     the padding in pixels between the contents and the
   *                        edges of the NodeDrawing
   * @param     col         the color to use when drawing
   */
  public NodeDrawing(int contents, int padding, Color col) {
    theColor = col;
    this.contents = contents;
    this.padding = padding;
    size = new Dimension(METRICS.stringWidth((new Integer(contents)).toString()) + 2 * padding, 
			 METRICS.getHeight() + 2 * padding);
  }

  /**
   * Returns the preferred size of the drawing.  This size will always be used.
   * @return                the preferred size of the drawing
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
   * Paints the NodeDrawing.  This method is called when the contents
   * of the NodeDrawing should be painted in response to the component
   * first being shown or damage needing repair.  The clip rectangle
   * in the Graphics parameter will be set to the area which needs
   * to be painted.
   * @param       g         The graphics context to use for painting
   */
  public void paint(Graphics g) {
    if (g != null && g.getClipBounds().intersects(new Rectangle(size))) {
      Font f = g.getFont();
      Color col = g.getColor();
      g.setFont(FONT);
      g.setColor(theColor);
      g.drawString((new Integer(contents)).toString(), padding, 
		   METRICS.getHeight() - METRICS.getDescent() + padding);
      g.setFont(f);
      g.setColor(col);
    }
  }
}
