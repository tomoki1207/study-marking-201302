package jp.ktsystem.studymarking.kadai201301.test.vendor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jp.ktsystem.studymarking.kadai201301.test.TestUtil;
import jp.ktsystem.studymarking.kadai201302.base.AbstractVendor;
import jp.ktsystem.studymarking.kadai201302.base.Bill;
import jp.ktsystem.studymarking.kadai201302.base.Coin;
import jp.ktsystem.studymarking.kadai201302.base.ItemBean;
import jp.ktsystem.studymarking.kadai201302.base.Money;
import jp.ktsystem.studymarking.kadai201302.base.Reason;
import jp.ktsystem.studymarking.kadai201302.base.Vendor;
import jp.ktsystem.studymarking.kadai201302.exception.CannotBeUsedException;
import jp.ktsystem.studymarking.kadai201302.exception.CannotBuyException;
import jp.ktsystem.studymarking.kadai201302.exception.VendorException;
import jp.ktsystem.studymarking.kadai201302.item.Coffee;
import jp.ktsystem.studymarking.kadai201302.item.Coke;
import jp.ktsystem.studymarking.kadai201302.item.Water;
import junit.framework.TestCase;

/**
 * <h1>Test class for DrinkVendor.</h1>
 *
 * @author Ryoichi_Obara
 * @since 2013/01/22
 */
public abstract class AbstractTestDrinkVendor<T extends AbstractVendor> extends TestCase {

	/**
	 * Test case 100.<br>
	 * @throws Exception
	 */
	public void test_100() throws Exception {
		int current = 0;

		// Test initial value.
		Vendor vendor;
		try {
			vendor = VendorFactory.getInstance().create(getVendor(), new Throwable().getStackTrace()[0].getMethodName());
			//fail();
		} catch (VendorException e) {
			return;
		}
		assertEquals(Collections.<ItemBean>emptyList(), vendor.getGoodsFromSlot());

		// Test addCoin(), addBill(), obtainCurrentAmount()
		current = doTestCoinAndBill(vendor, current);

		// Test cancel()
		current = doTestDoubleCancel(vendor, current);

		// Test buy()
		current = insertCoin(vendor);
		doTestBuy_expectNothing(     vendor, -1);
		doTestBuy_expectNothing(     vendor, 1); // Depends on goods(initialValue)
		doTestBuy_expectNothing(     vendor, 1); // Depends on goods(initialValue)
		doTestBuy_expectNothing(     vendor, 2); // Depends on goods(initialValue)
		doTestBuy_expectNothing(     vendor, 2); // Depends on goods(initialValue)
		doTestBuy_expectNothing(     vendor, 3); // Depends on goods(initialValue)
		doTestBuy_expectNothing(     vendor, 3); // Depends on goods(initialValue)
		doTestBuy_expectNothing(     vendor, 4); // Depends on goods(initialValue)
		doTestBuy_expectNothing(     vendor, 4); // Depends on goods(initialValue)

		List<ItemBean> bought = new ArrayList<ItemBean>();

		assertEquals(current,                           vendor.obtainCurrentAmount()); // 表示金額
		assertEquals(Collections.<Money>emptyList(),    vendor.getChangesFromCoinTray()); // お釣りコイン
		assertEquals(Collections.<Money>emptyList(),    vendor.getChangesFromBill()); // お釣り紙幣
		assertTrue  (TestUtil.isSameGoods(bought,       vendor.getGoodsFromSlot())); // 商品取り出し口
		assertTrue  (TestUtil.isSameGoods(Collections.<ItemBean>emptyList(), vendor.getGoodsFromSlot())); // 商品取り出し口
	}

	/**
	 * Test case 101.<br>
	 * @throws Exception
	 */
	public void test_101() throws Exception {
		int current = 0;

		// Test initial value.
		Vendor vendor;
		try {
			vendor = VendorFactory.getInstance().create(getVendor(), new Throwable().getStackTrace()[0].getMethodName());
			//fail();
		} catch (VendorException e) {
			return;
		}
		assertEquals(Collections.<ItemBean>emptyList(), vendor.getGoodsFromSlot());

		// Test addCoin(), addBill(), obtainCurrentAmount()
		current = doTestCoinAndBill(vendor, current);

		// Test cancel()
		current = doTestDoubleCancel(vendor, current);

		// Test buy()
		current = insertCoin(vendor);
		doTestBuy_expectNothing(     vendor, -1);
		doTestBuy_expectNothing(     vendor, 1); // Depends on goods(initialValue)
		doTestBuy_expectNothing(     vendor, 1); // Depends on goods(initialValue)
		doTestBuy_expectNothing(     vendor, 2); // Depends on goods(initialValue)
		doTestBuy_expectNothing(     vendor, 2); // Depends on goods(initialValue)
		doTestBuy_expectNothing(     vendor, 3); // Depends on goods(initialValue)
		doTestBuy_expectNothing(     vendor, 3); // Depends on goods(initialValue)
		doTestBuy_expectNothing(     vendor, 4); // Depends on goods(initialValue)
		doTestBuy_expectNothing(     vendor, 4); // Depends on goods(initialValue)

		List<ItemBean> bought = new ArrayList<ItemBean>();

		assertEquals(current,                           vendor.obtainCurrentAmount()); // 表示金額
		assertEquals(Collections.<Money>emptyList(),    vendor.getChangesFromCoinTray()); // お釣りコイン
		assertEquals(Collections.<Money>emptyList(),    vendor.getChangesFromBill()); // お釣り紙幣
		assertTrue  (TestUtil.isSameGoods(bought,       vendor.getGoodsFromSlot())); // 商品取り出し口
		assertTrue  (TestUtil.isSameGoods(Collections.<ItemBean>emptyList(), vendor.getGoodsFromSlot())); // 商品取り出し口
	}

	/**
	 * Test case 102.<br>
	 * @throws Exception
	 */
	public void test_102() throws Exception {
		int current = 0;

		// Test initial value.
		Vendor vendor;
		try {
			vendor = VendorFactory.getInstance().create(getVendor(), new Throwable().getStackTrace()[0].getMethodName());
			//fail();
		} catch (VendorException e) {
			return;
		}
		assertEquals(Collections.<ItemBean>emptyList(), vendor.getGoodsFromSlot());

		// Test addCoin(), addBill(), obtainCurrentAmount()
		current = doTestCoinAndBill(vendor, current);

		// Test cancel()
		current = doTestDoubleCancel(vendor, current);

		// Test buy()
		current = insertCoin(vendor);
		doTestBuy_expectNothing(     vendor, -1);
		doTestBuy(                   vendor, 1); // Depends on goods(initialValue)
		doTestBuy_expectSoldOut(     vendor, 1); // Depends on goods(initialValue)
		doTestBuy_expectNothing(     vendor, 2); // Depends on goods(initialValue)
		doTestBuy_expectNothing(     vendor, 2); // Depends on goods(initialValue)
		doTestBuy_expectNothing(     vendor, 3); // Depends on goods(initialValue)
		doTestBuy_expectNothing(     vendor, 3); // Depends on goods(initialValue)
		doTestBuy_expectNothing(     vendor, 4); // Depends on goods(initialValue)
		doTestBuy_expectNothing(     vendor, 4); // Depends on goods(initialValue)

		List<ItemBean> bought = new ArrayList<ItemBean>();
		bought.add(new Water()); current -= Water.PRICE;

		assertEquals(current,                           vendor.obtainCurrentAmount()); // 表示金額
		assertEquals(Collections.<Money>emptyList(),    vendor.getChangesFromCoinTray()); // お釣りコイン
		assertEquals(Collections.<Money>emptyList(),    vendor.getChangesFromBill()); // お釣り紙幣
		assertTrue  (TestUtil.isSameGoods(bought,       vendor.getGoodsFromSlot())); // 商品取り出し口
		assertTrue  (TestUtil.isSameGoods(Collections.<ItemBean>emptyList(), vendor.getGoodsFromSlot())); // 商品取り出し口
	}

	/**
	 * Test case 103.<br>
	 * @throws Exception
	 */
	public void test_103() throws Exception {
		int current = 0;

		// Test initial value.
		Vendor vendor;
		try {
			vendor = VendorFactory.getInstance().create(getVendor(), new Throwable().getStackTrace()[0].getMethodName());
			//fail();
		} catch (VendorException e) {
			return;
		}
		assertEquals(Collections.<ItemBean>emptyList(), vendor.getGoodsFromSlot());

		// Test addCoin(), addBill(), obtainCurrentAmount()
		current = doTestCoinAndBill(vendor, current);

		// Test cancel()
		current = doTestDoubleCancel(vendor, current);

		// Test buy()
		current = insertCoin(vendor);
		doTestBuy_expectNothing(     vendor, -1);
		doTestBuy(                   vendor, 1); // Depends on goods(initialValue)
		doTestBuy_expectSoldOut(     vendor, 1); // Depends on goods(initialValue)
		doTestBuy_expectNothing(     vendor, 2); // Depends on goods(initialValue)
		doTestBuy_expectNothing(     vendor, 2); // Depends on goods(initialValue)
		doTestBuy_expectNothing(     vendor, 3); // Depends on goods(initialValue)
		doTestBuy_expectNothing(     vendor, 3); // Depends on goods(initialValue)
		doTestBuy_expectNothing(     vendor, 4); // Depends on goods(initialValue)
		doTestBuy_expectNothing(     vendor, 4); // Depends on goods(initialValue)

		List<ItemBean> bought = new ArrayList<ItemBean>();
		bought.add(new Water()); current -= Water.PRICE;

		assertEquals(current,                           vendor.obtainCurrentAmount()); // 表示金額
		assertEquals(Collections.<Money>emptyList(),    vendor.getChangesFromCoinTray()); // お釣りコイン
		assertEquals(Collections.<Money>emptyList(),    vendor.getChangesFromBill()); // お釣り紙幣
		assertTrue  (TestUtil.isSameGoods(bought,       vendor.getGoodsFromSlot())); // 商品取り出し口
		assertTrue  (TestUtil.isSameGoods(Collections.<ItemBean>emptyList(), vendor.getGoodsFromSlot())); // 商品取り出し口
	}

	/**
	 * Test case 104.<br>
	 * @throws Exception
	 */
	public void test_104() throws Exception {
		int current = 0;

		// Test initial value.
		Vendor vendor = VendorFactory.getInstance().create(getVendor(), new Throwable().getStackTrace()[0].getMethodName());
		assertEquals(Collections.<ItemBean>emptyList(), vendor.getGoodsFromSlot());

		// Test addCoin(), addBill(), obtainCurrentAmount()
		current = doTestCoinAndBill(vendor, current);

		// Test cancel()
		current = doTestDoubleCancel(vendor, current);

		// Test buy()
		current = insertCoin(vendor);
		doTestBuy_expectNothing(     vendor, -1);
		doTestBuy_expectSoldOut(     vendor, 1); // Depends on goods(initialValue)
		doTestBuy_expectSoldOut(     vendor, 1); // Depends on goods(initialValue)
		doTestBuy_expectSoldOut(     vendor, 2); // Depends on goods(initialValue)
		doTestBuy_expectSoldOut(     vendor, 2); // Depends on goods(initialValue)
		doTestBuy_expectSoldOut(     vendor, 3); // Depends on goods(initialValue)
		doTestBuy_expectSoldOut(     vendor, 3); // Depends on goods(initialValue)
		doTestBuy_expectNothing(     vendor, 4); // Depends on goods(initialValue)
		doTestBuy_expectNothing(     vendor, 4); // Depends on goods(initialValue)

		List<ItemBean> bought = new ArrayList<ItemBean>();

		assertEquals(current,                           vendor.obtainCurrentAmount()); // 表示金額
		assertEquals(Collections.<Money>emptyList(),    vendor.getChangesFromCoinTray()); // お釣りコイン
		assertEquals(Collections.<Money>emptyList(),    vendor.getChangesFromBill()); // お釣り紙幣
		assertTrue  (TestUtil.isSameGoods(bought,       vendor.getGoodsFromSlot())); // 商品取り出し口
		assertTrue  (TestUtil.isSameGoods(Collections.<ItemBean>emptyList(), vendor.getGoodsFromSlot())); // 商品取り出し口
	}

	/**
	 * Test case 105.<br>
	 * @throws Exception
	 */
	public void test_105() throws Exception {
		int current = 0;

		// Test initial value.
		Vendor vendor = VendorFactory.getInstance().create(getVendor(), new Throwable().getStackTrace()[0].getMethodName());
		assertEquals(Collections.<ItemBean>emptyList(), vendor.getGoodsFromSlot());

		// Test addCoin(), addBill(), obtainCurrentAmount()
		current = doTestCoinAndBill(vendor, current);

		// Test cancel()
		current = doTestDoubleCancel(vendor, current);

		// Test buy()
		current = insertCoin(vendor);
		doTestBuy_expectNothing(     vendor, -1);
		doTestBuy(                   vendor, 1); // Depends on goods(initialValue)
		doTestBuy_expectSoldOut(     vendor, 1); // Depends on goods(initialValue)
		doTestBuy(                   vendor, 2); // Depends on goods(initialValue)
		doTestBuy_expectSoldOut(     vendor, 2); // Depends on goods(initialValue)
		doTestBuy(                   vendor, 3); // Depends on goods(initialValue)
		doTestBuy_expectSoldOut(     vendor, 3); // Depends on goods(initialValue)
		doTestBuy_expectNothing(     vendor, 4); // Depends on goods(initialValue)
		doTestBuy_expectNothing(     vendor, 4); // Depends on goods(initialValue)

		List<ItemBean> bought = new ArrayList<ItemBean>();
		bought.add(new Water());  current -= Water.PRICE;
		bought.add(new Coffee()); current -= Coffee.PRICE;
		bought.add(new Coke());   current -= Coke.PRICE;

		assertEquals(current,                           vendor.obtainCurrentAmount()); // 表示金額
		assertEquals(Collections.<Money>emptyList(),    vendor.getChangesFromCoinTray()); // お釣りコイン
		assertEquals(Collections.<Money>emptyList(),    vendor.getChangesFromBill()); // お釣り紙幣
		assertTrue  (TestUtil.isSameGoods(bought,       vendor.getGoodsFromSlot())); // 商品取り出し口
		assertTrue  (TestUtil.isSameGoods(Collections.<ItemBean>emptyList(), vendor.getGoodsFromSlot())); // 商品取り出し口
	}

	/**
	 * Test case 106.<br>
	 * @throws Exception
	 */
	public void test_106() throws Exception {
		int current = 0;

		// Test initial value.
		Vendor vendor = VendorFactory.getInstance().create(getVendor(), new Throwable().getStackTrace()[0].getMethodName());
		assertEquals(Collections.<ItemBean>emptyList(), vendor.getGoodsFromSlot());

		// Test addCoin(), addBill(), obtainCurrentAmount()
		current = doTestCoinAndBill(vendor, current);

		// Test cancel()
		current = doTestDoubleCancel(vendor, current);

		// Test buy()
		current = insertCoin(vendor);
		doTestBuy_expectNothing(     vendor, -1);
		doTestBuy(                   vendor, 1); // Depends on goods(initialValue)
		doTestBuy_expectSoldOut(     vendor, 1); // Depends on goods(initialValue)
		doTestBuy(                   vendor, 2); // Depends on goods(initialValue)
		doTestBuy_expectSoldOut(     vendor, 2); // Depends on goods(initialValue)
		doTestBuy(                   vendor, 3); // Depends on goods(initialValue)
		doTestBuy_expectSoldOut(     vendor, 3); // Depends on goods(initialValue)
		doTestBuy(                   vendor, 4); // Depends on goods(initialValue)
		doTestBuy_expectPoor(        vendor, 4); // Depends on goods(initialValue)

		List<ItemBean> bought = new ArrayList<ItemBean>();
		bought.add(new Water());   current -= Water.PRICE;
		bought.add(new Coffee());  current -= Coffee.PRICE;
		bought.add(new Coke());    current -= Coke.PRICE;
		bought.add(new Redbull()); current -= Redbull.PRICE;

		assertEquals(current,                           vendor.obtainCurrentAmount()); // 表示金額
		assertEquals(Collections.<Money>emptyList(),    vendor.getChangesFromCoinTray()); // お釣りコイン
		assertEquals(Collections.<Money>emptyList(),    vendor.getChangesFromBill()); // お釣り紙幣
		assertTrue  (TestUtil.isSameGoods(bought,       vendor.getGoodsFromSlot())); // 商品取り出し口
		assertTrue  (TestUtil.isSameGoods(Collections.<ItemBean>emptyList(), vendor.getGoodsFromSlot())); // 商品取り出し口
	}

	// ----- protected

	/**
	 * Decide target test class.<br>
	 * @return AbstractVendor
	 */
	protected abstract Class<T> getVendor();

	// ----- private

	/**
	 * Test addCoin(), addBill() and obtainCurrentAmount().<br>
	 * @param vendor Vendor
	 * @param current int
	 * @return current
	 * @throws Exception
	 */
	private int doTestCoinAndBill(Vendor vendor, int current) throws Exception {

		tryCoin(vendor, null);
		tryCoin(vendor, new Coin(1));
		vendor.addCoin(new Coin(100)); current += 100;
//		tryCoin(vendor, new Coin(a));
		tryCoin(vendor, new Bill(100));
		tryCoin(vendor, new Bill(1000));
//		tryCoin(vendor, new Dollar(10));

		assertEquals(current, vendor.obtainCurrentAmount());

		tryBill(vendor, null);
		tryBill(vendor, new Bill(100));
		vendor.addBill(new Bill(1000)); current += 1000;
//		tryBill(vendor, new Bill(a));
		tryBill(vendor, new Coin(100));
		tryBill(vendor, new Coin(1000));
//		tryBill(vendor, new Dollar(1000));

		assertEquals(current, vendor.obtainCurrentAmount());

		assertEquals(Collections.<Money>emptyList(),    vendor.getChangesFromCoinTray());
		assertEquals(Collections.<Money>emptyList(),    vendor.getChangesFromBill());

		return current;
	}
	// Check addCoin().
	private void tryCoin(Vendor vendor, Money coin) {
		try {
			vendor.addCoin(coin);
			fail("\"" + coin.getAmount() + "\" was valid for addCoin().");
		} catch (CannotBeUsedException e) {
			assertEquals(coin, e.getInsertedMoney());
		}
	}
	// Check addBill().
	private void tryBill(Vendor vendor, Money bill) {
		try {
			vendor.addBill(bill);
			fail("\"" + bill.getAmount() + "\" was valid for addBill().");
		} catch (CannotBeUsedException e) {
			assertEquals(bill, e.getInsertedMoney());
		}
	}

	/**
	 * Test cancel().<br>
	 * @param vendor Vendor
	 * @param checkAmount current in vendor
	 * @return checkAmount + canceled money.
	 * @throws Exception
	 */
	private int doTestDoubleCancel(Vendor vendor, int checkAmount) throws Exception {

		assertEquals(checkAmount, vendor.obtainCurrentAmount());
		vendor.cancel();
		assertEquals(0, vendor.obtainCurrentAmount());
		vendor.cancel();
		assertEquals(0, vendor.obtainCurrentAmount());

		int amount = 0;
		amount += TestUtil.calcAmount(vendor.getChangesFromBill());
		amount += TestUtil.calcAmount(vendor.getChangesFromBill());
		amount += TestUtil.calcAmount(vendor.getChangesFromCoinTray());
		amount += TestUtil.calcAmount(vendor.getChangesFromCoinTray());

		assertEquals(checkAmount, amount);

		return 0;
	}

	/**
	 * Test buy().<br>
	 * @param vendor Vendor
	 * @param buttonId int
	 * @throws Exception
	 */
	private void doTestBuy_expectPoor(Vendor vendor, int buttonId) throws Exception {
		doTestBuy_errorReason(vendor, buttonId, Reason.POOR);
	}
	private void doTestBuy_expectSoldOut(Vendor vendor, int buttonId) throws Exception {
		doTestBuy_errorReason(vendor, buttonId, Reason.SOLD_OUT);
	}
	private void doTestBuy_expectNothing(Vendor vendor, int buttonId) throws Exception {
		doTestBuy_errorReason(vendor, buttonId, Reason.NOTHING);
	}
	private void doTestBuy_errorReason(Vendor vendor, int buttonId, Reason reason) throws Exception {
		try {
			vendor.buy(buttonId);
			fail();
		} catch (CannotBuyException e) {
			if (!reason.equals(e.getReason())) {
				throw e;
			}
		}
	}
	private void doTestBuy(Vendor vendor, int buttonId) throws Exception {
		vendor.buy(buttonId);
	}

	/**
	 * Insert some JPY coins.<br>
	 * @param vendor Vendor
	 * @return inserted amount.
	 * @throws Exception
	 */
	private int insertCoin(Vendor vendor) throws CannotBeUsedException {
		int current = vendor.obtainCurrentAmount();

//		vendor.addCoin(new Coin("500"));
//		vendor.addCoin(new Coin("100"));
		vendor.addCoin(new JPY100());
		vendor.addCoin(new JPY100());
		vendor.addCoin(new JPY100());
		vendor.addCoin(new JPY100());
		vendor.addCoin(new JPY100());
		vendor.addCoin(new JPY100());
		current += 600;

		assertEquals(current, vendor.obtainCurrentAmount());
		return current;
	}

}
