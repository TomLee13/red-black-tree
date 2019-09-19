package edu.cmu.andrew.mingyan2;

import java.math.BigInteger;
import java.util.Scanner;

public class RedBlackTree {
	private RedBlackNode tree;
	private RedBlackNode nil;
	private int numberOfInserts;
	// private int recentCompares;

	// Constructor
	// creates an empty RedBlackTree.
	public RedBlackTree() {
		// Creates the sentinel node.
		nil = new RedBlackNode("sentinel", null, RedBlackNode.BLACK, null, null, null);
		tree = nil;
		numberOfInserts = 0;
		// recentCompares = 0;
	}

	public int getSize() {
		return numberOfInserts;
	}

	public void inOrderTraversal() {
		inOrderTraversal(tree);
	}

	private void inOrderTraversal(RedBlackNode t) {
		if (t == nil) {
			return;
		}
		if (t.getLc() != nil) {
			inOrderTraversal(t.getLc());
		}
		if (t == tree) {
			System.out.println("This is the root.");
		}
		System.out.println("The key of this node is: " + t.getKey());
		System.out.println("The value of this node is: " + t.getData().toString());
		System.out.println("The color of this node is: " + t.getColor() + "\n");

		if (t.getRc() != nil) {
			inOrderTraversal(t.getRc());
		}
	}

	public void preOrderTraversal() {
		preOrderTraversal(tree);
	}

	private void preOrderTraversal(RedBlackNode t) {
		if (t == nil) {
			return;
		}

		if (t == tree) {
			System.out.println("This is the root.");
		}
		System.out.println("The key of this node is: " + t.getKey());
		System.out.println("The value of this node is: " + t.getData().toString());
		System.out.println("The color of this node is: " + t.getColor() + "\n");

		if (t.getLc() != nil) {
			preOrderTraversal(t.getLc());
		}
		if (t.getRc() != nil) {
			preOrderTraversal(t.getRc());
		}
	}

	public void insert(String key, String value) {
		BigInteger data = new BigInteger(value);
		RedBlackNode z = new RedBlackNode(key, data);
		RedBlackNode y = nil;
		RedBlackNode x = tree;

		// find the place for insertion
		while (x != nil) {
			y = x;
			int cmp = z.getKey().compareTo(x.getKey()); // compare keys
			if (cmp < 0) {
				x = x.getLc();
			} else if (cmp > 0) {
				x = x.getRc();
			} else {
				break;
			}
		}

		z.setP(y);
		if (y == nil) {
			tree = z;
			numberOfInserts++;
		} else {
			int cmp = z.getKey().compareTo(y.getKey()); // compare keys
			if (cmp < 0) {
				y.setLc(z);
				numberOfInserts++;
			} else if (cmp > 0) {
				y.setRc(z);
				numberOfInserts++;
			} else {
				y.setData(z.getData()); // for duplicate keys
			}
		}

		z.setLc(nil);
		z.setRc(nil);
		z.setColor(RedBlackNode.RED);
		RBInsertFixup(z);
		// numberOfInserts++;
	}

	public void leftRotate(RedBlackNode x) {
		// set a pointer y to the right child of x
		RedBlackNode y = x.getRc();
		// set y's left subtree to x's right subtree
		x.setRc(y.getLc());
		// set y's left subtree's parent to x
		y.getLc().setP(x);
		// let y's parent point to x's parent.
		y.setP(x.getP());

		if (x.getP() == nil) { // if x is the root
			tree = y; // set y to the root
		} else {
			if (x == x.getP().getLc()) { // if x is the left child of its' parent
				x.getP().setLc(y); // let y become x's parent's left child
			} else {
				x.getP().setRc(y); // let y become x's parent's left child
			}
		}

		y.setLc(x); // set y's left child to x
		x.setP(y); // x's parent now is y
	}

	public void rightRotate(RedBlackNode x) {
		// set a pointer y to the left child of x.
		RedBlackNode y = x.getLc();
		// set y's right subtree to x's left subtree.
		x.setLc(y.getRc());
		// set y's right subtree's parent to x.
		y.getRc().setP(x);
		// let y's parent point to x's parent
		y.setP(x.getP());

		if (x.getP() == nil) { // if x is the root
			tree = y; // set y to the root
		} else {
			if (x == x.getP().getLc()) { // if x is the left child of its parent
				x.getP().setLc(y); // let y become x's parent's left child
			} else {
				x.getP().setRc(y); // let y become x's parent's left child
			}
		}

		y.setRc(x); // set y's right child to x
		x.setP(y); // x's parent now is y
	}

	/*
	 * Do the fixup work after a node is inserted in order to maintain the red black
	 * properties.
	 * 
	 * @ parameter z z is the new node.
	 */
	public void RBInsertFixup(RedBlackNode z) {
		while (z.getP().getColor() == RedBlackNode.RED) { // if z's parent's color is red
			if (z.getP() == z.getP().getP().getLc()) { // if z's parent is the left child of its parent
				RedBlackNode y = z.getP().getP().getRc(); // let y point to z's grandparent's right child
				if (y.getColor() == RedBlackNode.RED) { // if z's parent and uncle are both red
					// flip color
					z.getP().setColor(RedBlackNode.BLACK);
					y.setColor(RedBlackNode.BLACK);
					z.getP().getP().setColor(RedBlackNode.RED);
					z = z.getP().getP();
				} else { // if z's uncle is black
					if (z == z.getP().getRc()) { // check if z is its parent's right child
						z = z.getP(); // let z point to its parent
						leftRotate(z);
					}
					z.getP().setColor(RedBlackNode.BLACK);
					z.getP().getP().setColor(RedBlackNode.RED);
					rightRotate(z.getP().getP());
				}
			} else { // if z's parent is the right child of its parent
				RedBlackNode y = z.getP().getP().getLc(); // let y point to z's grandparent's left child
				if (y.getColor() == RedBlackNode.RED) { // if z's parent and uncle are both red
					// flip color
					z.getP().setColor(RedBlackNode.BLACK);
					y.setColor(RedBlackNode.BLACK);
					z.getP().getP().setColor(RedBlackNode.RED);
					z = z.getP().getP();
				} else { // if z's uncle is black
					if (z == z.getP().getLc()) {
						z = z.getP();
						rightRotate(z);
					}
					z.getP().setColor(RedBlackNode.BLACK);
					z.getP().getP().setColor(RedBlackNode.RED);
					leftRotate(z.getP().getP());
				}
			}
		}
		tree.setColor(RedBlackNode.BLACK);
	}

	public BigInteger lookUp(String key) {
		RedBlackNode x = tree;
		while (x != nil) {
			int cmp = key.compareTo(x.getKey());
			if (cmp < 0) {
				x = x.getLc();
			} else if (cmp > 0) {
				x = x.getRc();
			} else {
				return x.getData();
			}
		}
		System.out.println("The key is not in the tree.");
		return null;
	}

	// Test routine
	public static void main(String[] args) {
		RedBlackTree rbt = new RedBlackTree();
		rbt.insert("10", "1");
		rbt.insert("85", "2");
		rbt.insert("15", "3");
		rbt.insert("70", "4");
		rbt.insert("20", "5");
		rbt.insert("60", "6");
		rbt.insert("30", "7");
		rbt.insert("50", "8");
		rbt.insert("65", "9");
		rbt.insert("80", "10");
		rbt.insert("90", "11");
		rbt.insert("40", "12");
		rbt.insert("55", "13");
		rbt.insert("1", "14");
		rbt.insert("99", "15");

		System.out.println("The size of the tree is: " + rbt.getSize() + "\n");

		System.out.println("Result of in order traversal: ");
		rbt.inOrderTraversal();

		System.out.println("Result of pre-order traversal: ");
		rbt.preOrderTraversal();

		System.out.println("Please enter a key for searching: ");
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		if (rbt.lookUp(input) != null) {
			System.out.println("The value in the node of the given key is: " + rbt.lookUp(input));
		}
		scan.close();

	}
}
