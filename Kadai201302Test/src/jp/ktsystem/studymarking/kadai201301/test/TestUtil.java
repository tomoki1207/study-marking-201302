package jp.ktsystem.studymarking.kadai201301.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import jp.ktsystem.studymarking.kadai201302.base.Bill;
import jp.ktsystem.studymarking.kadai201302.base.Coin;
import jp.ktsystem.studymarking.kadai201302.base.ItemBean;
import jp.ktsystem.studymarking.kadai201302.base.Money;

/**
 * <h1>Utility for test class.</h1>
 *
 * @author Ryoichi_Obara
 * @since 2013/01/23
 */
public class TestUtil {

	/** 使用可能なお金. */
	private static final List<Money> AVAILABLE_MONEY;

	static {
		List<Money> availableMoney = Arrays.asList(new Coin(10), new Coin(50), new Coin(100), new Coin(500), new Bill(1000), new Bill(2000), new Bill(5000), new Bill(10000));

		// 数値からの変換処理で有利になるよう、逆順で格納する。
		Collections.reverse(availableMoney);
		AVAILABLE_MONEY = availableMoney;
	}

	// ----- constructor

	private TestUtil() {}

	// ----- public

	/**
	 * Convert int to List.<br>
	 * @param amount int
	 * @return List<Money>
	 */
	public static List<Money> createTip(int amount) {
		List<Money> tip = new ArrayList<Money>();

		int am = amount;
		for (Money m : AVAILABLE_MONEY) {
			while (Integer.valueOf(m.getAmount()) <= am) {
				am -= Integer.valueOf(m.getAmount());
				tip.add(m);
			}
		}
		return tip;
	}

	/**
	 * Convert List to int.<br>
	 * @param money List<Money>
	 * @return integer
	 */
	public static int calcAmount(List<Money> money) {
		int amount = 0;
		for (Money m : money) {
			amount += m.getAmount();
		}
		return amount;
	}

	/**
	 * 
	 * @param source
	 * @param dest
	 * @return
	 */
	public static boolean isSameGoods(List<ItemBean> source, List<ItemBean> dest) {

		if (null == source || null == dest) {
			return source == dest;
		}

		List<ItemBean> dst = new ArrayList<ItemBean>(dest);
		for (ItemBean s : source) {
			boolean found = false;
			for (ItemBean d : dst) {
				if (s.getItemName().equals(d.getItemName()) && s.getPrice() == d.getPrice()) {
					dst.remove(d);
					found = true;
					break;
				}
			}
			if (!found) return false;
		}

		return 0 == dst.size();
	}

	/**
	 * 
	 * @param source
	 * @param dest
	 * @return
	 */
	public static boolean isSameAmount(List<Money> source, List<Money> dest) {

		if (null == source || null == dest) {
			return source == dest;
		}

		List<Money> dst = new ArrayList<Money>(dest);
		for (Money s : source) {
			boolean found = false;
			for (Money d : dst) {
				if (s.getAmount() == d.getAmount()) {
					dst.remove(d);
					found = true;
					break;
				}
			}
			if (!found) return false;
		}

		return 0 == dst.size();
	}

}
