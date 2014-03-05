package jp.ktsystem.studymarking.kadai201302.base;

/**
 * <h1>コイン.</h1>
 *
 * @author Ryoichi_Obara
 * @since  2014/03/05
 */
public class Coin implements Money {

	/** 金額. */
	private final int amount;

	// ----- Constructor

	/**
	 * Constructor.<br>
	 * @param amount 金額
	 */
	public Coin(int amount) {
		super();
		this.amount = amount;
	}

	// ----- Getter

	public int getAmount() {
		return amount;
	}

}
