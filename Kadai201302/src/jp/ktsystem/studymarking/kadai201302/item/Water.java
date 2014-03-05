package jp.ktsystem.studymarking.kadai201302.item;

import jp.ktsystem.studymarking.kadai201302.base.ItemBean;

/**
 * <h1>Water.</h1>
 *
 * @author Ryoichi_Obara
 * @since  2013/01/21
 */
public class Water extends ItemBean {

	private static final long serialVersionUID = 715936282746788950L;

	public static final String ITEM_NAME = "Water";
	public static final int PRICE = 100;

	// --- Constructor

	public Water() {
		super(ITEM_NAME, PRICE);
	}

}
