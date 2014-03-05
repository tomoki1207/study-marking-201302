package jp.ktsystem.studymarking.kadai201302.exception;

import jp.ktsystem.studymarking.kadai201302.base.Money;

/**
 * <h1>使用できないお金が自販機に投入された場合の例外クラス.</h1>
 *
 * @author Ryoichi_Obara
 * @since  2013/01/21
 */
public class CannotBeUsedException extends Exception {

	private static final long serialVersionUID = 6438149533830138594L;


	/** This coin/bill is not available in the vendor. */
	private final Money insertedMoney; 

	// ----- Constructor

	/**
	 * Constructor.<br>
	 * @param insertedMoney Money
	 */
	public CannotBeUsedException(Money insertedMoney) {
		this.insertedMoney = insertedMoney;
	}

	// ----- Getter

	public Money getInsertedMoney() {
		return insertedMoney;
	}

}
