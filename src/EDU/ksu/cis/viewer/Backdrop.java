/*
 * Backdrop.java     03/18/98
 *
 * Copyright (c) 1998, Rod Howell, All Rights Reserved.
 *
 */

package EDU.ksu.cis.viewer;

import java.awt.*;

/**
 * A lightweight container class to hold a single Canvas at a specified location.
 * The Backdrop may be manipulated as any Canvas, but the contained Canvas will
 * be drawn on top of it.  The Backdrop also serves as a layer of protection for
 * the contained Canvas, as it neither changes the contained Canvas nor provides
 * access to it through any methods.
 * @author      Rod Howell
 */
public class Backdrop extends Canvas {
  private Canvas theCanvas;
  private Point canvasLoc;

  /**
   * Constructs a Backdrop containing a new Canvas whose upper left-hand
   * corner coincides with the upper left-hand corner of the Backdrop.
   * The size of the Backdrop is initialized to the size of the Canvas.
   */
  public Backdrop() {
    this(new Canvas());
  }

  /**
   * Constructs a Backdrop containing the given Canvas.  The upper left-hand corner
   * of the given Canvas is placed at the upper left-hand corner of the Backdrop,
   * and the size of the Backdrop is initialized to the size of the Canvas.
   * @param      canv     the Canvas to be placed in the Backdrop
   */
  public Backdrop(Canvas canv) {
    this(canv, new Point(0, 0), canv.getSize());
  }

  /**
   * Constructs a Backdrop containing the given Canvas at the given location.
   * The location gives the pixel coordinates of the upper left-hand corner of 
   * the Canvas relative to the upper left-hand corner of the Backdrop.  The
   * size of the Backdrop is initialized to the smallest size that will encompass
   * the entire Canvas.
   * @param      canv     the Canvas to be placed in the Backdrop
   * @param      loc      the location of the upper left-hand corner of the
   *                      Canvas relative to the upper left-hand corner of the
   *                      Backdrop
   */
  public Backdrop(Canvas canv, Point loc) {
    this(canv, loc,  new Dimension(canv.getSize().width + loc.x,
                                   canv.getSize().height + loc.y));
  }

  /**
   * Constructs a Backdrop of the given size containing the given Canvas at its
   * upper left-hand corner.
   * @param       canv     the Canvas to be placed in the Backdrop
   * @param       size     the initial size of the Backdrop
   */
  public Backdrop(Canvas canv, Dimension size) {
    this(canv, new Point(0, 0), size);
  }

  /**
   * Constructs a Backdrop of the given size containing the given Canvas at the
   * given location.
   * @param       canv     the Canvas to be placed in the Backdrop
   * @param       loc      the location of the upper left-hand corner of the
   *                       Canvas relative to the upper left-hand corner of the
   *                       Backdrop
   * @param       size     the initial size of the Backdrop
   */
  public Backdrop(Canvas canv, Point loc, Dimension size) {
    theCanvas = canv;
    canvasLoc = loc;
    setSize(size);
  }

  /** 
   * Paints the Backdrop.  This method is called when the contents
   * of the Backdrop should be painted in response to the component
   * first being shown or damage needing repair.  The clip rectangle
   * in the Graphics parameter will be set to the area which needs
   * to be painted.
   * @param       g         The graphics context to use for painting
   */
  public void paint(Graphics g) {
    if (g != null) {
      g.translate(canvasLoc.x, canvasLoc.y);
      theCanvas.update(g);
      g.translate(-canvasLoc.x, -canvasLoc.y);
    }
  }
}
