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
		// TODO Implement this method
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public boolean isSimilar(BST<T> tree1, BST<T> tree2) {
		// TODO Implement this method
		throw new UnsupportedOperationException("Not implemented yet!");
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
				element = recursiveStatistic((BSTNode<T>)node.getRight(), k -sizeLeft-1);
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
