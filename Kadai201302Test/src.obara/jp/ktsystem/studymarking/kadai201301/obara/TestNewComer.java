package jp.ktsystem.studymarking.kadai201301.obara;

import java.util.ArrayList;
import java.util.List;

import jp.ktsystem.studymarking.kadai201301.obara.NewComerA;
import jp.ktsystem.studymarking.kadai201301.test.TestUtil;
import jp.ktsystem.studymarking.kadai201302.base.AbstractHuman;
import jp.ktsystem.studymarking.kadai201302.base.ItemBean;
import jp.ktsystem.studymarking.kadai201302.base.Money;
import jp.ktsystem.studymarking.kadai201302.base.Reason;
import jp.ktsystem.studymarking.kadai201302.base.ResultFailed;
import jp.ktsystem.studymarking.kadai201302.base.ResultReport;
import jp.ktsystem.studymarking.kadai201302.base.ResultSuccess;
import junit.framework.TestCase;

/**
 * <h1>Test class for NewComer.</h1>
 *
 * Notice:
 *     Copy jp.ktsystem.studymarking.kadai201301.test.human.VendorFactory
 *     to   jp.ktsystem.studymarking.kadai201301.factory.VendorFactory
 *     when you do test.
 *
 * @author Ryoichi_Obara
 * @since 2013/01/24
 */
public class TestNewComer extends TestCase {

	private static final int TIP = 100;

	// ----- public

	// 自販機オブジェクトの生成は他クラスでテスト済
	// 買えない　商品がない
	// 買えない　お金が足りない
	// 買えない　売り切れ

	public void test200() {
		AbstractHuman errandBoy = new NewComerA();

		ItemBean item = obtainItemFromTestCase("TC200");
		List<Money> tips = TestUtil.createTip(TIP);

		// 実行
		ResultReport result = errandBoy.operate(item.getItemName(), 1, tips);

		// 予想結果を生成
		List<ItemBean> goods = new ArrayList<ItemBean>();
		List<Money> changes = TestUtil.createTip(TestUtil.calcAmount(tips));

		// チェック
		checkResult(result, goods, changes, Reason.NOTHING);
	}

	public void test201() {
		AbstractHuman errandBoy = new NewComerA();

		ItemBean item = obtainItemFromTestCase("TC201");
		List<Money> tips = TestUtil.createTip(TIP);

		// 実行
		ResultReport result = errandBoy.operate(item.getItemName(), 1, tips);

		// 予想結果を生成
		List<ItemBean> goods = new ArrayList<ItemBean>();
		List<Money> changes = TestUtil.createTip(TestUtil.calcAmount(tips));

		// チェック
		checkResult(result, goods, changes, Reason.SOLD_OUT);
	}

	public void test202() {
		AbstractHuman errandBoy = new NewComerA();

		ItemBean item = obtainItemFromTestCase("TC202");
		List<Money> tips = TestUtil.createTip(0); // Depends on this case.

		// 実行
		ResultReport result = errandBoy.operate(item.getItemName(), 1, tips);

		// 予想結果を生成
		List<ItemBean> goods = new ArrayList<ItemBean>();
		List<Money> changes = TestUtil.createTip(TestUtil.calcAmount(tips));

		// チェック
		checkResult(result, goods, changes, Reason.POOR);
	}

	public void test203() {
		AbstractHuman errandBoy = new NewComerA();

		ItemBean item = obtainItemFromTestCase("TC203");
		List<Money> tips = TestUtil.createTip(TIP);

		// 実行
		ResultReport result = errandBoy.operate(item.getItemName(), 1, tips);

		// 予想結果を生成
		List<ItemBean> goods = new ArrayList<ItemBean>();
		goods.add(item);
		List<Money> changes = TestUtil.createTip(TestUtil.calcAmount(tips) - TIP);

		// チェック
		checkResult(result, goods, changes);
	}

	// ----- private

	private ItemBean obtainItemFromTestCase(String testCase) {
		ItemBean item = new ItemBean(testCase, 100);
		return item;
	}

	private void checkResult(ResultReport result, List<ItemBean> goods, List<Money> changes) {

		assertTrue(result instanceof ResultSuccess);
		assertTrue(TestUtil.isSameGoods( goods,   ((ResultSuccess) result).getGoods()));
		assertTrue(TestUtil.isSameAmount(changes, ((ResultSuccess) result).getChanges()));
	}

	private void checkResult(ResultReport result, List<ItemBean> goods, List<Money> changes, Reason reason) {

		assertTrue(result instanceof ResultFailed);
		assertTrue(TestUtil.isSameGoods( goods,   ((ResultFailed) result).getGoods()));
		assertTrue(TestUtil.isSameAmount(changes, ((ResultFailed) result).getChanges()));
	}

}
