package jp.ktsystem.studymarking.kadai201302.base;

import java.io.Serializable;

/**
 * <h1>商品.</h1>
 *
 * @author Ryoichi_Obara
 * @since  2013/01/21
 */
public class ItemBean implements Serializable {

	private static final long serialVersionUID = 4190844828080661331L;

	/** 商品名. */
	private final String itemName;

	/** 価格. */
	private final int price;

	// ----- Constructor

	/**
	 * Constructor.<br>
	 * @param itemName 商品名
	 * @param price 価格
	 */
	public ItemBean(String itemName, int price) {
		super();
		this.itemName = itemName;
		this.price = price;
	}

	// ----- Getter

	public String getItemName() {
		return itemName;
	}
	public int getPrice() {
		return price;
	}

}
