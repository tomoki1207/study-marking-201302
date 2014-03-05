package jp.ktsystem.studymarking.kadai201301.obara;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jp.ktsystem.studymarking.kadai201301.factory.VendorFactory;
import jp.ktsystem.studymarking.kadai201302.base.AbstractHuman;
import jp.ktsystem.studymarking.kadai201302.base.Bill;
import jp.ktsystem.studymarking.kadai201302.base.Coin;
import jp.ktsystem.studymarking.kadai201302.base.Money;
import jp.ktsystem.studymarking.kadai201302.base.RackBean;
import jp.ktsystem.studymarking.kadai201302.base.Reason;
import jp.ktsystem.studymarking.kadai201302.base.ResultFailed;
import jp.ktsystem.studymarking.kadai201302.base.ResultReport;
import jp.ktsystem.studymarking.kadai201302.base.ResultSuccess;
import jp.ktsystem.studymarking.kadai201302.base.Vendor;
import jp.ktsystem.studymarking.kadai201302.exception.CannotBeUsedException;
import jp.ktsystem.studymarking.kadai201302.exception.CannotBuyException;

/**
 * <h1>新人A.</h1>
 *
 * @author Ryoichi_Obara
 * @since  2013/01/12
 */
public class NewComerA extends AbstractHuman {

	@Override
	public ResultReport operate(String itemName, int qty, List<Money> tip) {

		// 自販機を探す
		Vendor vendor = VendorFactory.getInstance().create(itemName);
		List<Money> back = new ArrayList<Money>();

		// 自販機にお金を入れる
		for (Money money : tip) {

			if (money instanceof Coin) {
				try {
					vendor.addCoin(money);
				} catch (CannotBeUsedException e) {
					back.add(e.getInsertedMoney());
				}
			} else if (money instanceof Bill) {
				try {
					vendor.addBill(money);
				} catch (CannotBeUsedException e) {
					back.add(e.getInsertedMoney());
				}
			} else {
				// Unknown money　from Boss.
				back.add(money);
			}
		}

		// 購入
		Reason reason = null;
		try {
			int buttonId = obtainButtonId(vendor, itemName);

			// 買えるだけ買う
			for (int i = 0; i < qty; i++) {
				vendor.buy(buttonId);
			}

		} catch (CannotBuyException e) {
			reason = e.getReason();

		} finally {
			vendor.cancel();
		}

		// それぞれのトレイからお釣りを受け取る
		back.addAll(vendor.getChangesFromBill());
		back.addAll(vendor.getChangesFromCoinTray());

		if (null == reason) {

			return new ResultSuccess(vendor.getGoodsFromSlot(), back);
		} else {

			return new ResultFailed(vendor.getGoodsFromSlot(), back, reason);
		}
	}

	/**
	 * 探している商品のボタンIDを取得.<br>
	 *
	 * @param vendor　自販機
	 * @param itemName 商品名
	 * @return ボタンID
	 * @throws CannotBuyException その自販機からは買えない場合
	 */
	private int obtainButtonId(Vendor vendor, String itemName) throws CannotBuyException {

		Map<Integer, RackBean> items = vendor.getGoods();
		Iterator<Entry<Integer, RackBean>> i = items.entrySet().iterator();

		while (i.hasNext()) {
			Entry<Integer, RackBean> e = i.next();

			if (!e.getValue().isAvailable()) {
				throw new CannotBuyException(Reason.SOLD_OUT);
			}

			if (e.getValue().getItem().getItemName().equals(itemName)) {
				return e.getKey();
			}
		}

		throw new CannotBuyException(Reason.NOTHING);
	}

}
