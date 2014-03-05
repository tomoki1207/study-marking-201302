package jp.ktsystem.studymarking.kadai201302.exception;

import jp.ktsystem.studymarking.kadai201302.base.Reason;

/**
 * <h1>購入できないボタンが自販機にて押下された場合の例外クラス.</h1>
 *
 * @author Ryoichi_Obara
 * @since  2013/01/21
 */
public class CannotBuyException extends Exception {

	private static final long serialVersionUID = -1886107122636192809L;

	private final Reason reason;

	// ----- Constructor

	/**
	 * Constructor.<br>
	 * @param reason Reason
	 */
	public CannotBuyException(Reason reason) {
		this.reason = reason;
	}

	// ----- Getter

	public Reason getReason() {
		return reason;
	}

}
