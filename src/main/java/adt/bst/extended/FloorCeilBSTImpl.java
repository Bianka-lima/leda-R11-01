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
		return floor;
	}

	private Integer floor(BSTNode<Integer> node, Integer element) {
		Integer floor = null;
		return floor;
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		Integer ceil = null;
		for (Integer element:array) {
			this.insert(element);
		}
		return ceil;
	}

	private Integer recursiveCeil(BSTNode<Integer> node, Integer element) {
		Integer ceil = null;
		return ceil;
	}

}
