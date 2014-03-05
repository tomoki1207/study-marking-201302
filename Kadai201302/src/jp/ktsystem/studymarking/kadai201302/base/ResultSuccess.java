package jp.ktsystem.studymarking.kadai201302.base;

import java.util.List;

/**
 * <h1>おつかい成功.</h1>
 *
 * @author Ryoichi_Obara
 * @since  2013/01/12
 */
public class ResultSuccess implements ResultReport {

	/** 購入品. */
	private final List<ItemBean> goods;

	/** お釣り. */
	private final List<Money> changes;

	public ResultSuccess(List<ItemBean> goods, List<Money> changes) {
		this.goods = goods;
		this.changes = changes;
	}

	// ----- Getter

	public List<ItemBean> getGoods() {
		return goods;
	}
	public List<Money> getChanges() {
		return changes;
	}

}
