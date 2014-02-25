/*
 * BinarySearchTree.java     03/18/98
 *
 * Copyright (c) 1998, Rod Howell, All Rights Reserved.
 *
 */

package EDU.ksu.cis.viewer;

import java.util.*;
import java.awt.*;

/**
 * An immutable binary search tree that can draw itself.
 * 
 * @author Rod Howell
 */
public final class BinarySearchTree implements BSTInterface {
	private BinaryTree theTree;

	/**
	 * Constructs an empty BinarySearchTree.
	 */
	public BinarySearchTree() {
		theTree = new BinaryTree();
	}

	private BinarySearchTree(BinaryTree t) {
		theTree = t;
	}

	/**
	 * Returns the tree resulting from the insert of the given key. If the key
	 * is already in the tree, an identical tree is returned.
	 * 
	 * @param key
	 *            the key to be inserted
	 * @return the tree resulting from the insertion
	 * @exception NullPointerException
	 *                If key is <tt>null</tt>
	 */
	public BSTInterface put(int key) throws NullPointerException {
		return put(key, 0);
	}

	public BSTInterface put(int key, int tag) throws NullPointerException {

		return new BinarySearchTree(put(key, theTree, tag));
	}

	private static BinaryTree put(int key, BinaryTree t, int tag) {
		if (t.empty) {
			return new BinaryTree(new Node(key, tag), null, null);
		} else {
			

			if (key == t.root.contents) {
				return t;
			} else if (key < t.root.contents) {
				return new BinaryTree(t.root, put(key, t.left, tag), t.right);
			} else {
				return new BinaryTree(t.root, t.right, put(key, t.left, tag));
			}
		}
	}

	/**
	 * Returns the tree resulting from the removal of the given key. If the key
	 * is not in the tree, an identical tree is returned.
	 * 
	 * @param key
	 *            the key to be removed
	 * @return the tree resulting from the deletion
	 * @exception NullPointerException
	 *                If key is <tt>null</tt>
	 */
	public BSTInterface remove(int key) throws NullPointerException {

		return new BinarySearchTree(remove(key, theTree));
	}

	private static BinaryTree remove(int s, BinaryTree t) {
		if (t.isEmpty()) {
			return t;
		} else {
			Node root = t.getRoot();

			if (s == root.getContents()) {
				if (t.getLeftChild().isEmpty()) {
					return t.getRightChild();
				} else if (t.getRightChild().isEmpty()) {
					return t.getLeftChild();
				} else {
					BinaryTree newTree = minToRoot(t.getRightChild());
					return new BinaryTree(newTree.getRoot(), t.getLeftChild(),
							newTree.getRightChild());
				}
			} else if (s < root.getContents()) {
				return new BinaryTree(root, remove(s, t.getLeftChild()),
						t.getRightChild());
			} else {
				return new BinaryTree(root, t.getLeftChild(), remove(s,
						t.getRightChild()));
			}
		}
	}

	private static BinaryTree minToRoot(BinaryTree t) {
		if (t.getLeftChild().isEmpty()) {
			return t;
		} else {
			BinaryTree temp = minToRoot(t.getLeftChild());
			return new BinaryTree(temp.getRoot(), null, new BinaryTree(
					t.getRoot(), temp.getRightChild(), t.getRightChild()));
		}
	}

	/**
	 * Returns an immutable drawing of the tree in a Backdrop.
	 * 
	 * @return the drawing of the tree.
	 * @see Backdrop
	 */
	public Canvas getDrawing() {
		return theTree.getDrawing();
	}

	/**
	 * Gets a StackInterface appropriate for recording instances of
	 * BinarySearchTree.
	 * 
	 * @return an empty instance of StackInterface
	 */
	public StackInterface getStack() {
		return new BSTStack();
	}

	/**
	 * Because this structure is immutable, this method simply returns the tree
	 * itself.
	 * 
	 * @return this BinarySearchTree
	 */
	public Object clone() {
		return this;
	}

	@Override
	public Integer getTag(int key) {
		BinaryTree t = theTree;

		while (true)
			if (t.isEmpty()) {
				return null;
			} else {
				Node root = t.getRoot();

				if (key == root.getContents()) {
					return root.getTag();
				} else if (key < root.getContents()) {
					t = t.getLeftChild();
				} else {
					t = t.getRightChild();
				}
			}

	}
}

final class BSTStack implements StackInterface {
	private ConsList theStack;

	BSTStack() {
		theStack = new ConsList();
	}

	public void push(Object obj) throws ClassCastException {
		theStack = new ConsList((BinarySearchTree) obj, theStack);
	}

	public Object pop() throws EmptyStackException {
		if (theStack.isEmpty()) {
			throw new EmptyStackException();
		}
		Object top = theStack.getHead();
		theStack = theStack.getTail();
		return top;
	}

	public boolean empty() {
		return theStack.isEmpty();
	}

	public Object clone() {
		try {
			BSTStack s = (BSTStack) super.clone();
			s.theStack = theStack;
			return s;
		} catch (CloneNotSupportedException e) { // This shouldn't happen.
			System.err.println("Clone not supported.");
			return null;
		}
	}
}
