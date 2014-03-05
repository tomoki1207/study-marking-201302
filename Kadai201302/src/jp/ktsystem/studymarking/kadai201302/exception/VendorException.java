package jp.ktsystem.studymarking.kadai201302.exception;

/**
 * <h1>自販機でエラーになった場合の例外クラス.</h1>
 *
 * @author Ryoichi_Obara
 * @since 2014/03/05
 */
public class VendorException extends RuntimeException {

	private static final long serialVersionUID = 7213210366469489253L;

	// ----- Constructor

	public VendorException() {
		super();
	}

	public VendorException(String message) {
		super(message);
	}

	public VendorException(String message, Throwable cause) {
		super(message, cause);
	}

	public VendorException(Throwable cause) {
		super(cause);
	}

}
