package jp.ktsystem.studymarking.kadai201301.factory;

import jp.ktsystem.studymarking.kadai201302.base.Vendor;

/**
 * <h1>自販機を生成.</h1>
 *
 * @author Ryoichi_Obara
 * @since  2013/01/12
 */
public class VendorFactory {

	private static VendorFactory instance;

	// ----- constructor
	private VendorFactory() {
		// Save from instantiate.
	}

	// ----- public

	public static VendorFactory getInstance() {
		if (null == instance) {
			instance = new VendorFactory();
		}
		return instance;
	}

	/**
	 * 自販機インスタンス生成.<br>
	 * @return 生成したVendor
	 */
	public Vendor create(String itemName) {

		//TODO new DrinkVendor();
		return null;
	}

}
