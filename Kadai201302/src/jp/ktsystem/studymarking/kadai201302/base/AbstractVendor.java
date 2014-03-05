package jp.ktsystem.studymarking.kadai201302.base;

import java.util.Map;

/**
 * <h1>自販機共通処理.</h1>
 *
 * @author Ryoichi_Obara
 * @since  2013/01/21
 */
public abstract class AbstractVendor implements Vendor {

	/** 商品郡<ボタンID,商品棚> */
	protected final Map<Integer, RackBean> goods;

	// ----- Constructor

	/**
	 * Constructor.<br>
	 * @param goods 格納する商品
	 */
	public AbstractVendor(Map<Integer, RackBean> goods) {
		this.goods = goods;
	}

	// ----- Getter

	public Map<Integer, RackBean> getGoods() {
		return goods;
	}

}
