package jp.ktsystem.studymarking.kadai201301.test.vendor;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import jp.ktsystem.studymarking.kadai201302.base.AbstractVendor;
import jp.ktsystem.studymarking.kadai201302.base.RackBean;
import jp.ktsystem.studymarking.kadai201302.exception.VendorException;
import jp.ktsystem.studymarking.kadai201302.item.Coffee;
import jp.ktsystem.studymarking.kadai201302.item.Coke;
import jp.ktsystem.studymarking.kadai201302.item.Water;
import junit.framework.TestCase;

/**
 * <h1>Vendor factory class for test.</h1>
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
	public <V extends AbstractVendor> V create(Class<V> vendor, String testCase) throws Exception {

		Map<Integer, RackBean> goods;

		if ("test_100".equals(testCase)) {        goods = createGoods_null();
		} else if ("test_101".equals(testCase)) { goods = createGoods_empty();
		} else if ("test_102".equals(testCase)) { goods = createGoods_containsNullRack();
		} else if ("test_103".equals(testCase)) { goods = createGoods_containsNullItem();
		} else if ("test_104".equals(testCase)) { goods = createGoods_soldout();
		} else if ("test_105".equals(testCase)) { goods = createGoods();
		} else if ("test_106".equals(testCase)) { goods = createGoods_containsRedbull();
		} else {
			TestCase.fail("Invalid testcase setting " + testCase); return null;
		}

		//Class<?>[] argType = { goods.getClass() };
		Class<?>[] argType = { Map.class };

		try {
			return vendor.getConstructor(argType).newInstance(goods);
		} catch (InvocationTargetException e) {
			if (e.getCause() instanceof VendorException) {
				throw (VendorException) e.getCause();
			} else {
				throw e;
			}
		}
	}

	// ----- private

	// for test_100
	private Map<Integer, RackBean> createGoods_null() {

		return null;
	}

	// for test_101
	private Map<Integer, RackBean> createGoods_empty() {

		return new HashMap<Integer, RackBean>();
	}

	// for test_102
	private Map<Integer, RackBean> createGoods_containsNullRack() {
		Map<Integer, RackBean> goods = new HashMap<Integer, RackBean>();

		goods.put(1, new RackBean(new Water(), 1));
		goods.put(2, null);

		return goods;
	}

	// for test_103
	private Map<Integer, RackBean> createGoods_containsNullItem() {
		Map<Integer, RackBean> goods = new HashMap<Integer, RackBean>();

		goods.put(1, new RackBean(new Water(), 1));
		goods.put(2, new RackBean(null, 1));

		return goods;
	}

	// for test_104
	private Map<Integer, RackBean> createGoods_soldout() {
		Map<Integer, RackBean> goods = new HashMap<Integer, RackBean>();

		goods.put(1, new RackBean(new Water(),  0));
		goods.put(2, new RackBean(new Coffee(), 0));
		goods.put(3, new RackBean(new Coke(),   0));

		return goods;
	}

	// for test_105
	private Map<Integer, RackBean> createGoods() {
		Map<Integer, RackBean> goods = new HashMap<Integer, RackBean>();

		goods.put(1, new RackBean(new Water(),  1));
		goods.put(2, new RackBean(new Coffee(), 1));
		goods.put(3, new RackBean(new Coke(),   1));

		return goods;
	}

	// for test_106
	private Map<Integer, RackBean> createGoods_containsRedbull() {
		Map<Integer, RackBean> goods = new HashMap<Integer, RackBean>();

		goods.put(1, new RackBean(new Water(),   1));
		goods.put(2, new RackBean(new Coffee(),  1));
		goods.put(3, new RackBean(new Coke(),    1));
		goods.put(4, new RackBean(new Redbull(), 2));

		return goods;
	}

}
