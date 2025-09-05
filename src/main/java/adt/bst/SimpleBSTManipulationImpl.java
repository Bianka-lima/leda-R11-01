package adt.bst;

/**
 * - Esta eh a unica classe que pode ser modificada 
 * @author adalbertocajueiro
 *
 * @param <T>
 */
public class SimpleBSTManipulationImpl<T extends Comparable<T>> implements SimpleBSTManipulation<T> {

	@Override
	public boolean equals(BST<T> tree1, BST<T> tree2) {
		boolean equals = false;
		if (tree1 == null && tree2 == null) {
			equals = true;
		} else if(tree1 == null || tree2 == null) {
			equals = false;
		} else {
			equals = areEquals((BSTNode<T>)tree1.getRoot(), (BSTNode<T>)tree2.getRoot());
		}
		return equals;
	}

	private boolean areEquals(BSTNode<T> node1, BSTNode<T>node2) {
		boolean areEquals = false;
		if (node1 == null && node2 == null) {
			areEquals = true;
		} else if (node1 == null || node2 == null)  {
			areEquals = false;
		} else if (!(node1.getData().equals(node2.getData()))) {
			areEquals = false;
		} else {
			areEquals = areEquals((BSTNode<T>)node1.getLeft(), (BSTNode<T>)node2.getLeft()) &&
			areEquals((BSTNode<T>)node1.getRight(), (BSTNode<T>)node2.getRight());
		}
		return areEquals;
	}

	@Override
	public boolean isSimilar(BST<T> tree1, BST<T> tree2) {
		boolean issim = false;
		if ((tree1 == null && tree2 == null)) {
			issim = true;
		} else if (tree1 == null || tree2 == null) {
			issim = false;
		} else {
			issim = (areSimilar((BSTNode<T>)tree1.getRoot(), (BSTNode<T>)tree2.getRoot()));
		}
		return issim;
	}

	private boolean areSimilar(BSTNode<T> node1, BSTNode<T> node2) {
		boolean aresim = false;
		if (node1 == null && node2 == null) {
			aresim = true;
		} else if (node1 == null || node2 == null) {
			aresim = false;
		} else {
			aresim = areSimilar((BSTNode<T>)node1.getLeft(), (BSTNode<T>)node2.getLeft()) && 
			areSimilar((BSTNode<T>)node1.getRight(), (BSTNode<T>)node2.getRight());
		}
		return aresim;
	}

	@Override
    public T orderStatistic(BST<T> tree, int k) {
        T element = null;
		if (!(tree.isEmpty())) {
			BSTNode<T> root = (BSTNode<T>)tree.getRoot();
			element = recursiveStatistic(root, k);
		}
		return element;
    }

	private T recursiveStatistic(BSTNode<T> node, int k) {
		T element = null;
		if (node != null && !(node.isEmpty())) {
			int sizeLeft = size((BSTNode<T>)node.getLeft());
			if (k == sizeLeft + 1) {
				element = node.getData();
			} else if (k < sizeLeft){
				element = recursiveStatistic((BSTNode<T>)node.getLeft(), k);
			} else {
				element = recursiveStatistic((BSTNode<T>)node.getRight(), k - (sizeLeft+1));
			}
		}
		return element;
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
