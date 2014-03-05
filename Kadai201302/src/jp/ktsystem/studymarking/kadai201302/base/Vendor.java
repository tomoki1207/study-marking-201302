package jp.ktsystem.studymarking.kadai201302.base;

import java.util.List;
import java.util.Map;

import jp.ktsystem.studymarking.kadai201302.exception.CannotBeUsedException;
import jp.ktsystem.studymarking.kadai201302.exception.CannotBuyException;

/**
 * <h1>自販機インターフェイス.</h1>
 *
 * @author Ryoichi_Obara
 * @since  2013/01/21
 */
public interface Vendor {

	/**
	 * コイン投入口へコインを投入.<br>
	 * @param coin コイン
	 * @throws CannotBeUsedException 使用不可なコインが投入された
	 */
	public void addCoin(Money coin) throws CannotBeUsedException;

	/**
	 * 紙幣口へ紙幣を投入.<br>
	 * @param bill 紙幣
	 * @throws CannotBeUsedException 使用不可な紙幣が投入された
	 */
	public void addBill(Money bill) throws CannotBeUsedException;

	/**
	 * 現在の投入金額を表示.<br>
	 * @return 現在の投入金額
	 */
	public int obtainCurrentAmount();

	/**
	 * キャンセルボタンの押下.<br>
	 */
	public void cancel();

	/**
	 * 購入ボタンの押下.<br>
	 * @param buttonId 購入時に押下されたボタン
	 * @throws CannotBuyException 購入不可な商品のボタンが押下された
	 */
	public void buy(int buttonId) throws CannotBuyException;

	/**
	 * 商品取り出し口より商品を取得.<br>
	 * @return 購入後の商品
	 */
	public List<ItemBean> getGoodsFromSlot();

	/**
	 * 釣銭返却トレイよりコインを取得.<br>
	 * @return 釣銭返却トレイにあるコイン
	 */
	public List<Money> getChangesFromCoinTray();

	/**
	 * 紙幣口より紙幣を取得.<br>
	 * @return 紙幣口にある紙幣
	 */
	public List<Money> getChangesFromBill();

	// ----- Getter

	/**
	 * 自販機が持っている商品郡を取得.<br>
	 * @return 商品郡<ボタンID,商品棚>
	 */
	public Map<Integer, RackBean> getGoods();

}
