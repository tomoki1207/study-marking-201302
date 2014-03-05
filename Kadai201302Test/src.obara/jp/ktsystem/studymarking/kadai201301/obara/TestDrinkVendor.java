package jp.ktsystem.studymarking.kadai201301.obara;

import jp.ktsystem.studymarking.kadai201301.test.vendor.AbstractTestDrinkVendor;

/**
 * <h1>Test class for DrinkVendor.</h1>
 *
 * @author Ryoichi_Obara
 * @since 2013/01/22
 */
public class TestDrinkVendor extends AbstractTestDrinkVendor<DrinkVendor> {

	protected Class<DrinkVendor> getVendor() {
		return DrinkVendor.class;
	}

}
