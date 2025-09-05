package adt.bst;

import java.util.ArrayList;
import java.util.Arrays;

import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		int height = -1;
		if (!(isEmpty())) {
			height = recursiveHeight(this.root);
		}
		return height;
	}

	private int recursiveHeight(BSTNode<T> node) {
		int height = -1;
		if (node != null && !(node.isEmpty())) {
			height = 1 + Math.max(recursiveHeight((BSTNode<T>)node.getLeft()), recursiveHeight((BSTNode<T>)node.getRight()));
		}
		return height;
	}

	@Override
	public BSTNode<T> search(T element) {
		return recursiveSearch(this.root, element);
	}

	private BSTNode<T> recursiveSearch(BSTNode<T> node, T element) {
		BSTNode<T> resp = new BSTNode<T>();
		if (node != null && !(node.isEmpty())) {
			if (node.getData().equals(element)) {
				resp = node;
			} else if (element.compareTo(node.getData()) < 0) {
				resp = recursiveSearch((BSTNode<T>)node.getLeft(), element);
			} else {
				resp = recursiveSearch((BSTNode<T>)node.getRight(), element);
			}
		}
		return resp;
	}

	@Override
	public void insert(T element) {
		if (isEmpty() && element != null) {
			BSTNode<T> newNode = new BSTNode<>();
			newNode.setData(element);
			newNode.setLeft(new BSTNode<T>());
			newNode.setRight(new BSTNode<T>());
			this.root = newNode;
		} else {
			recursiveInsert(this.root, element);
		}
	}

	private void recursiveInsert(BSTNode<T> node, T element) {
		if (element.compareTo(node.getData()) < 0) {
			if (node.getLeft().isEmpty()) {
				BSTNode<T> newNode = new BSTNode<>();
				newNode.setData(element);
				newNode.setLeft(new BSTNode<>());
				newNode.setRight(new BSTNode<>());
				newNode.setParent(node);
				node.setLeft(newNode);
			} else {
				recursiveInsert((BSTNode<T>)node.getLeft(), element);
			}
		} else {
			if (node.getRight().isEmpty()) {
				BSTNode<T> newNode = new BSTNode<>();
				newNode.setData(element);
				newNode.setLeft(new BSTNode<>());
				newNode.setRight(new BSTNode<>());
				newNode.setParent(node);
				node.setRight(newNode);
			} else {
				recursiveInsert((BSTNode<T>)node.getRight(), element);
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		BSTNode<T> max = null;
		if (!(isEmpty())) {
			max = recursiveMaximum(this.root);
		}
		return max;
	}

	private BSTNode<T> recursiveMaximum(BSTNode<T> node) {
		BSTNode<T> max = node;
		if (node != null && node.getRight() != null && !(node.getRight().isEmpty())) {
			max = recursiveMaximum((BSTNode<T>)node.getRight());
		}
		return max;
	}

	@Override
	public BSTNode<T> minimum() {
		BSTNode<T> min = null;
		if (!(isEmpty())) {
			min = recursiveMinimum(this.root);
		}
		return min;
	}

	private BSTNode<T> recursiveMinimum(BSTNode<T> node) {
		BSTNode<T> min = node;
		if (node != null && node.getLeft() != null && !(node.getLeft().isEmpty())) {
			min = recursiveMinimum((BSTNode<T>)node.getLeft());
		}
		return min;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> suc = null;
		BSTNode<T> node = search(element);
		if (node != null && !(node.isEmpty())) {
			if (!(node.getRight().isEmpty())) {
				suc = recursiveMinimum((BSTNode<T>)node.getRight());
			} else {
				BSTNode<T> aux = (BSTNode<T>)node.getParent();
				while (aux != null && !(aux.isEmpty()) && (aux.getData().compareTo(element) < 0)) {
					aux = (BSTNode<T>)aux.getParent();
				}
				suc = aux;
			}
		}
		return suc;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> pred = null;
		BSTNode<T> node = search(element);
		if (node != null && ! (node.isEmpty())) {
			if (node.getLeft() != null && !(node.getLeft().isEmpty())) {
				pred = recursiveMaximum((BSTNode<T>)node.getLeft());
			} else {
				BSTNode<T> aux = (BSTNode<T>)node.getParent();
				while (aux != null && !(aux.isEmpty()) && (element.compareTo(aux.getData()) < 0)) {
					aux = (BSTNode<T>)aux.getParent();
				}
				pred  = aux;
			}
		}
		return pred;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> toRemove = search(element);
		if (toRemove != null && !(toRemove.isEmpty())) {
			if (toRemove.getRight().isEmpty() && toRemove.getLeft().isEmpty()) {
				if (toRemove.equals(this.root)) {
					this.root = null;
				} else {
					if (toRemove.getData().compareTo(toRemove.getParent().getData()) < 0) {
						toRemove.getParent().setLeft(new BSTNode<>());
					} else {
						toRemove.getParent().setRight(new BSTNode<>());
					}
				}
			} else if (toRemove.getRight().isEmpty() && !(toRemove.getLeft().isEmpty())) {
				if (toRemove.equals(this.root)) {
					this.root = (BSTNode<T>)toRemove.getLeft();
					this.root.setParent(null);
				} else {
					toRemove.getLeft().setParent(toRemove.getParent());
					if (toRemove.getData().compareTo(toRemove.getParent().getData()) < 0) {
						toRemove.getParent().setLeft(toRemove.getLeft());
					} else {
						toRemove.getParent().setRight(toRemove.getLeft());
					}
				}
			} else if (toRemove.getLeft().isEmpty() && !(toRemove.getRight().isEmpty())) {
				if (toRemove.equals(this.root)) {
					this.root = (BSTNode<T>)toRemove.getRight();
					this.root.setParent(null);
				} else {
					toRemove.getRight().setParent(toRemove.getParent());
					if (toRemove.getData().compareTo(toRemove.getParent().getData()) < 0) {
						toRemove.getParent().setLeft(toRemove.getRight());
					} else {
						toRemove.getParent().setRight(toRemove.getRight());
					}
				}
			} else {
				BSTNode<T> sucessor = sucessor(toRemove.getData());
				T temp = toRemove.getData();
				toRemove.setData(sucessor.getData());
				sucessor.setData(temp);
				remove(sucessor.getData());
			}
		}
	}

	@Override
	public T[] preOrder() {
		T[] preOrder = (T[]) new Comparable[0];
		if (!(isEmpty())) {
			preOrder = recursivePreOrder(this.root);
		}
		return preOrder;
	}

	private T[] recursivePreOrder(BSTNode<T> node) {
		ArrayList<T> lista = new ArrayList<>();
		if (node != null && !(node.isEmpty())) {
			lista.add(node.getData());
			lista.addAll(Arrays.asList(recursivePreOrder((BSTNode<T>) node.getLeft())));
			lista.addAll(Arrays.asList(recursivePreOrder((BSTNode<T>) node.getRight())));
		}
		return (T[]) lista.toArray((T[]) new Comparable[lista.size()]);
	}

	/*@Override
	public T[] order() {
		ArrayList<T> orderList = new ArrayList<>();
		
		if (!isEmpty()) {
			recursiveOrder(this.root, orderList);
		}
		
		return orderList.toArray((T[]) new Comparable[orderList.size()]);
	}

	private void recursiveOrder(BSTNode<T> node, ArrayList<T> list) {
		if (node == null) {
			return;
		}
		
		recursiveOrder((BSTNode<T>)node.getLeft(), list);
		list.add(node.getData());
		recursiveOrder((BSTNode<T>)node.getRight(), list);
	}*/

	@Override
	public T[] order() {
		T[] order = (T[]) new Comparable[0];
		if (!(isEmpty())) {
			order = recursiveOrder(this.root);
		}
		return order;
	}

	private T[] recursiveOrder(BSTNode<T> node) {
		ArrayList<T> lista = new ArrayList<>();
		if (node != null && !(isEmpty())) {
			lista.addAll(Arrays.asList(recursiveOrder((BSTNode<T>) node.getLeft())));
			lista.add(node.getData());
			lista.addAll(Arrays.asList(recursiveOrder((BSTNode<T>) node.getRight())));
		}
		return (T[]) lista.toArray((T[]) new Comparable[lista.size()]);
	}


	@Override
	public T[] postOrder() {
		T[] posOrder = (T[]) new Comparable[0];
		if (!(isEmpty())) {
			posOrder = recursivePosOrder(this.root);
		}
		return posOrder;
	}

	private T[] recursivePosOrder(BSTNode<T> node) {
		ArrayList<T> lista = new ArrayList<>();
		if (node != null && !(isEmpty())) {
			lista.addAll(Arrays.asList(recursivePosOrder((BSTNode<T>) node.getLeft())));
			lista.addAll(Arrays.asList(recursivePosOrder((BSTNode<T>) node.getRight())));
			lista.add(node.getData());
		}
		return (T[]) lista.toArray((T[]) new Comparable[lista.size()]);
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
