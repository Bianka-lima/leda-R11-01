package adt.bst.extended;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Note que esta classe estende sua implementacao de BST (BSTImpl).
 * Dependendo do design que voce use, sua BSTImpl precisa ter apenas funcionando
 * corretamente o metodo insert para que voce consiga testar esta classe.
 */
public class FloorCeilBSTImpl extends BSTImpl<Integer> implements FloorCeilBST {

	@Override
	public Integer floor(Integer[] array, double numero) {
		Integer floor = null;
		for (Integer element:array) {
			this.insert(element);
		}
		if (!(this.root.isEmpty())) {
			floor = recursiveFloor(root, numero);
		}
		return floor;
	}

	private Integer recursiveFloor(BSTNode<Integer> node, double element) {
		Integer floor = null;
		if (node != null && !(node.isEmpty())) {
			if (element < node.getData()) {
				floor = recursiveFloor((BSTNode<Integer>)node.getLeft(), element);
			} else if (element > node.getData()) {
				Integer floorRight = recursiveFloor((BSTNode<Integer>)node.getRight(), element);
				if (floorRight != null) {
					floor = floorRight;
				} else {
					floor = node.getData();
				}
			} else {
				floor = node.getData();
			}
		}
		return floor;
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		Integer ceil = null;
		for (Integer element:array) {
			this.insert(element);
		}
		if (this.root != null && !(this.root.isEmpty())) {
			ceil = recursiveCeil(root, numero);
		}
		return ceil;
	}

	private Integer recursiveCeil(BSTNode<Integer> node, double element) {
		Integer ceil = null;
		if (node != null && !(node.isEmpty())) {
			if (element < node.getData()) {
				ceil = recursiveCeil((BSTNode<Integer>)node.getRight(), element);
			} else if (element > node.getData()) {
				Integer ceilLeft = recursiveCeil((BSTNode<Integer>)node.getLeft(), element);
				if (ceilLeft != null) {
					ceil = ceilLeft;
				} else {
					ceil = node.getData();
				}
			} else {
				ceil = node.getData();
			}
		}
		return ceil;
	}

}
