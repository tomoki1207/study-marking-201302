package jp.ktsystem.studymarking.kadai201301.factory;

import jp.ktsystem.studymarking.kadai201302.base.AbstractHuman;

/**
 * <h1>中間管理職.</h1>
 *
 * @author Ryoichi_Obara
 * @since  2013/01/21
 */
public class MiddleManagerFactory {

	private static MiddleManagerFactory instance;

	// ----- constructor
	private MiddleManagerFactory() {
		// Save from instantiate.
	}

	// ----- public

	public static MiddleManagerFactory getInstance() {
		if (null == instance) {
			instance = new MiddleManagerFactory();
		}
		return instance;
	}

	/**
	 * おつかいに行かせる新人をつかまえてくる.<br>
	 * @return 見つかった新人
	 */
	public AbstractHuman create() {

		//TODO return new NewComer();
		return null;
	}

}
