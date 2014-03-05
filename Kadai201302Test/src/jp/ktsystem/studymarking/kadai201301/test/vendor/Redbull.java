package jp.ktsystem.studymarking.kadai201301.test.vendor;

import jp.ktsystem.studymarking.kadai201302.base.ItemBean;

/**
 * <h1>Red bull.</h1>
 *
 * @author Ryoichi_Obara
 * @since  2013/01/21
 */
public class Redbull extends ItemBean {

	private static final long serialVersionUID = 715936282746788950L;


	public static final String ITEM_NAME = "Redbull";
	public static final int PRICE = 250;

	// --- Constructor

	public Redbull() {
		super(ITEM_NAME, PRICE);
	}

}
