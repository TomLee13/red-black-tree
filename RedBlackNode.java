package edu.cmu.andrew.mingyan2;

import java.math.BigInteger;

public class RedBlackNode {
	public static final int BLACK = 0;
	public static final int RED = 1;

	private String key;
	private BigInteger data;
	private int color;
	private RedBlackNode p, lc, rc;

	// constructor 1: it takes 2 parameters - key, value.
	public RedBlackNode(String key, BigInteger data) {
		this.key = key;
		this.data = data;
		color = RED;
		p = null;
		lc = null;
		rc = null;
	}

	// constructor 2: it takes all parameters needed for a RedBlackNode.
	public RedBlackNode(String key, BigInteger data, int color, RedBlackNode p, RedBlackNode lc, RedBlackNode rc) {
		this.key = key;
		this.data = data;
		this.color = color;
		this.p = p;
		this.lc = lc;
		this.rc = rc;
	}

	public String toString() {
		return data.toString();
	}

	public String getKey() {
		return key;
	}

	public int getColor() {
		return color;
	}

	public BigInteger getData() {
		return data;
	}

	public RedBlackNode getLc() {
		return lc;
	}

	public RedBlackNode getP() {
		return p;
	}

	public RedBlackNode getRc() {
		return rc;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public void setData(BigInteger data) {
		this.data = data;
	}

	public void setLc(RedBlackNode lc) {
		this.lc = lc;
	}

	public void setP(RedBlackNode p) {
		this.p = p;
	}

	public void setRc(RedBlackNode rc) {
		this.rc = rc;
	}

}
