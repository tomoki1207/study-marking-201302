package jp.ktsystem.studymarking.kadai201302.base;

import java.util.List;

/**
 * <h1>おつかい人間共通処理.</h1>
 *
 * @author Ryoichi_Obara
 * @since  2013/01/21
 */
public abstract class AbstractHuman {

	/**
	 * おつかい命令.<br>
	 *
	 * @param itemName 商品名
	 * @param qty 個数
	 * @param tip 代金
	 * @return 購入品
	 */
	public abstract ResultReport operate(String itemName, int qty, List<Money> tip);

}
