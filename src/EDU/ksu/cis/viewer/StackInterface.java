/*
 * StackInterface.java     03/18/98
 *
 * Copyright (c) 1998, Rod Howell, All Rights Reserved.
 *
 */

package EDU.ksu.cis.viewer;

import java.util.*;

/**
 * This interface is implemented by each of the stack structures that are used
 * to record the histories for each of the data structures.
 * @author      Rod Howell
 */
public interface StackInterface extends Cloneable {

  /**
   * Pushes an Object on the stack.
   * @param     t     the Object to be pushed
   */
  public void push(Object t);

  /**
   * Removes the Object from the top of the stack and returns it.
   * @return          the Object on the top of the stack
   * @exception       EmptyStackException
   *                  If the stack is empty
   */
  public Object pop() throws EmptyStackException;

  /**
   * Returns <tt>true</tt> if the stack is empty.
   * @return          whether the stack is empty
   */
  public boolean empty();

  /**
   * Returns a clone of this StackInterface object.
   * @return          a clone of this StackInterface object
   */
  public Object clone();
}

