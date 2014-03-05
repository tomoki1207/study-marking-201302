package jp.ktsystem.studymarking.kadai201302.base;

import java.util.List;

/**
 * <h1>おつかい失敗.</h1>
 *
 * @author Ryoichi_Obara
 * @since  2013/01/12
 */
public class ResultFailed implements ResultReport {

	/** 購入品. */
	private final List<ItemBean> goods;

	/** お釣り. */
	private final List<Money> changes;

	/** 原因 */
	private final Reason reason;

	// ----- constructor

	public ResultFailed(List<ItemBean> goods, List<Money> changes, Reason reason) {
		this.goods = goods;
		this.changes = changes;
		this.reason = reason;
	}

	// ----- Getter

	public List<ItemBean> getGoods() {
		return goods;
	}
	public List<Money> getChanges() {
		return changes;
	}
	public Reason getReason() {
		return reason;
	}

}
