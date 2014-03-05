package jp.ktsystem.studymarking.kadai201302.base;

import java.io.Serializable;

/**
 * <h1>商品棚.</h1>
 *
 * @author Ryoichi_Obara
 * @since  2013/01/21
 */
public class RackBean implements Serializable {

	private static final long serialVersionUID = 3481362753124863579L;

	/** 商品. */
	private final ItemBean item;

	/** 在庫数. */
	private int qty;

	// ----- Constructor

	/**
	 * Constructor.<br>
	 * @param item 商品
	 * @param qty 個数
	 */
	public RackBean(ItemBean item, int qty) {
		super();
		this.item = item;
		this.qty = qty;
	}

	// ----- Public

	/**
	 * 在庫を一個減らす.<br>
	 */
	public void decreaseQty() {
		this.qty--;
	}

	/**
	 * 購入可能かどうか.<br>
	 * @return true:購入可能
	 */
	public boolean isAvailable() {
		return qty > 0;
	}

	// ----- Getter

	public ItemBean getItem() {
		return item;
	}

}
