package jp.ktsystem.studymarking.kadai201301.test.human;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.ktsystem.studymarking.kadai201302.base.AbstractVendor;
import jp.ktsystem.studymarking.kadai201302.base.Bill;
import jp.ktsystem.studymarking.kadai201302.base.Coin;
import jp.ktsystem.studymarking.kadai201302.base.ItemBean;
import jp.ktsystem.studymarking.kadai201302.base.Money;
import jp.ktsystem.studymarking.kadai201302.base.RackBean;
import jp.ktsystem.studymarking.kadai201302.base.Reason;
import jp.ktsystem.studymarking.kadai201302.base.Vendor;
import jp.ktsystem.studymarking.kadai201302.exception.CannotBeUsedException;
import jp.ktsystem.studymarking.kadai201302.exception.CannotBuyException;
import jp.ktsystem.studymarking.kadai201302.exception.VendorException;
import jp.ktsystem.studymarking.kadai201302.item.Coffee;
import jp.ktsystem.studymarking.kadai201302.item.Coke;
import jp.ktsystem.studymarking.kadai201302.item.Water;

/**
 * <h1>
 *  This class is for test.
 * </h1>
 *
 * Usage:
 *     Copy jp.ktsystem.studymarking.kadai201301.factory when you test human class.
 *
 * @author Ryoichi_Obara
 * @since 2013/01/23
 */
public class VendorFactory {

	// ----- design pattern; singleton

	private static VendorFactory instance = new VendorFactory();
	private VendorFactory() {}
	public static final VendorFactory getInstance() { return instance; }

	// ----- public

	/**
	 * Factory method.<br>
	 *
	 * @param packagename "jp.ktsystem.studymarking.kadai201301.*****"
	 * @param testCase "test_100" - "test_106"
	 * @return Vendor
	 * @throws Exception
	 *     fail: Invalid testCase.
	 *     VendorException: Exception at constructor of vendor.
	 *     InvocationTargetException: others.
	 */
	public Vendor create(String testCase) {

		Map<Integer, RackBean> goods;

		if ("TC200".equals(testCase)) {        goods = createGoods_empty(testCase);
		} else if ("TC201".equals(testCase)) { goods = createGoods_soldout(testCase);
		} else {                               goods = createGoods(testCase);
		}

		return new VendorForTestNewComer(goods);
	}

	// ----- private

	// for TC200
	private Map<Integer, RackBean> createGoods_empty(String itemName) {

		return new HashMap<Integer, RackBean>();
	}

	// for TC201
	private Map<Integer, RackBean> createGoods_soldout(String itemName) {
		Map<Integer, RackBean> goods = new HashMap<Integer, RackBean>();

		goods.put(1, new RackBean(new Water(),  1));
		goods.put(2, new RackBean(new Coffee(), 1));
		goods.put(3, new RackBean(new Coke(),   1));
		goods.put(4, new RackBean(new ItemBean(itemName, 100),  0));

		return goods;
	}

	// for TC202 and other
	private Map<Integer, RackBean> createGoods(String itemName) {
		Map<Integer, RackBean> goods = new HashMap<Integer, RackBean>();

		goods.put(1, new RackBean(new Water(),  1));
		goods.put(2, new RackBean(new Coffee(), 1));
		goods.put(3, new RackBean(new Coke(),   1));
		goods.put(4, new RackBean(new ItemBean(itemName, 100),  1));

		return goods;
	}

	// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

	/** この自販機で使用可能な硬貨. */
	private static final List<String> AVAILABLE_COIN;

	/** この自販機で使用可能な紙幣. */
	private static final List<String> AVAILABLE_BILL;

	static {
		List<String> availableCoins = Arrays.asList("10", "50", "100", "500");
		List<String> availableBills = Arrays.asList("1000", "2000", "5000", "10000");

		// 返却処理で処理が有利になるよう、逆順で格納する。
		Collections.reverse(availableCoins);
		Collections.reverse(availableBills);

		AVAILABLE_COIN = availableCoins;
		AVAILABLE_BILL = availableBills;
	}

	class VendorForTestNewComer extends AbstractVendor {

		/** 現在の投入金額 */
		private int amount = 0;

		/** 商品取り出し口の商品 */
		private List<ItemBean> goodsFromSlot = new ArrayList<ItemBean>();

		/** お釣りトレイ(硬貨) */
		private List<Money> changesCoin = new ArrayList<Money>();

		/** お釣りトレイ(紙幣) */
		private List<Money> changesBill = new ArrayList<Money>(); 

		// ----- Constructor

		public VendorForTestNewComer(Map<Integer, RackBean> goods) {
			super(goods);
			if (null == goods) {
				throw new VendorException("goods id null");
			}
		}

		// ----- Public

		@Override
		public void addCoin(Money insertedMoney) throws CannotBeUsedException {

			validateMoney(AVAILABLE_COIN, Coin.class, insertedMoney);
			amount += Integer.valueOf(insertedMoney.getAmount());
		}

		@Override
		public void addBill(Money insertedMoney) throws CannotBeUsedException {

			validateMoney(AVAILABLE_BILL, Bill.class, insertedMoney);
			amount += Integer.valueOf(insertedMoney.getAmount());
		}

		@Override
		public int obtainCurrentAmount() {
			return amount;
		}

		@Override
		public void cancel() {

			// 紙幣の返却
			for (String s : AVAILABLE_BILL) {
				int m = Integer.valueOf(s);
				while (m <= amount) {
					amount -= m;
					changesBill.add(new Bill(m));
				}
			}

			// 硬貨の返却
			for (String s : AVAILABLE_COIN) {
				int m = Integer.valueOf(s);
				while (m <= amount) {
					amount -= m;
					changesCoin.add(new Coin(m));
				}
			}
		}

		@Override
		public void buy(int buttonId) throws CannotBuyException {

			// ボタンIDから商品を 取得
			if (!goods.containsKey(buttonId)) {
				throw new CannotBuyException(Reason.NOTHING);
			}
			RackBean targetRack = goods.get(buttonId);

			// 在庫の確認
			if (!targetRack.isAvailable()) {
				throw new CannotBuyException(Reason.SOLD_OUT);
			}

			// 入金額を商品の値段を比較 ⇒ 買えない場合はエラー
			if (amount < targetRack.getItem().getPrice()) {
				throw new CannotBuyException(Reason.POOR);
			}

			// 購入

			targetRack.decreaseQty(); // 在庫減
			goodsFromSlot.add(targetRack.getItem()); // 商品を取り出し口へ
			amount -= targetRack.getItem().getPrice(); // 残額更新
		}

		@Override
		public List<ItemBean> getGoodsFromSlot() {
			List<ItemBean> newInstance = new ArrayList<ItemBean>(goodsFromSlot);
			goodsFromSlot.clear();
			return newInstance;
		}

		@Override
		public List<Money> getChangesFromCoinTray() {
			List<Money> newInstance = new ArrayList<Money>(changesCoin);
			changesCoin.clear();
			return newInstance;
		}

		@Override
		public List<Money> getChangesFromBill() {
			List<Money> newInstance = new ArrayList<Money>(changesBill);
			changesBill.clear();
			return newInstance;
		}

		// ----- Private

		/**
		 * 投入されたお金のチェック.<br>
		 *
		 * @param availables 有効なお金
		 * @param check チェックする型(クラス)
		 * @param money チェックすお金
		 * @throws CannotBeUsedException 有効でない
		 */
		private <T extends Money> void validateMoney(List<String> availables, Class<T> check, Money money) throws CannotBeUsedException {

			if (null == money) throw new CannotBeUsedException(money);

			if (!money.getClass().isAssignableFrom(check)) {
				throw new CannotBeUsedException(money);
			}

			if (availables.contains(money.getAmount())) {
				try {
					new Integer(money.getAmount());
				} catch (NumberFormatException e) {
					throw new CannotBeUsedException(money);
				}
			} else {
				throw new CannotBeUsedException(money);
			}
		}
	}

}
