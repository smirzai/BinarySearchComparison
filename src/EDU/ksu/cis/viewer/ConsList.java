/*
 * ConsList.java     03/18/98
 *
 * Copyright (c) 1998, Rod Howell, All Rights Reserved.
 *
 */

package EDU.ksu.cis.viewer;

/**
 * An immutable linear recursive structure with a head, which is an Object, and
 * a tail, which is a ConsList.
 * @author      Rod Howell
 */
public final class ConsList {
  private boolean empty;
  private Object head;
  private ConsList tail;

  /**
   * Constructs an empty ConsList.
   */
  public ConsList() {
    empty = true;
  }

  /**
   * Constructs a ConsList with the given head and tail.  If the given tail is
   * <tt>null</tt>, an empty ConsList is used in its place.
   * @param    h        the head of the list
   * @param    lst      the tail of the list
   */
  public ConsList(Object h, ConsList lst) {
    empty = false;
    head = h;
    tail = lst == null ? new ConsList() : lst;
  }

  /**
   * Returns <tt>true</tt> if the list is empty.
   * @return            whether the list is empty
   */
  public boolean isEmpty() {
    return empty;
  }

  /**
   * Returns the head of the list.
   * @return            the head of the list
   * @exception         EmptyListException
   *                    If the list is empty.
   */
  public Object getHead() throws EmptyListException {
    if (empty) {
      throw new EmptyListException();
    }
    return head;
  }

  /**
   * Returns the tail of the list.
   * @return            the tail of the list
   * @exception         EmptyListException
   *                    If the list is empty.
   */
  public ConsList getTail() throws EmptyListException {
    if (empty) {
      throw new EmptyListException();
    }
    return tail;
  }
}
