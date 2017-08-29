package com.ksyzt.gwt.shared.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminLoginException.
 */
public class AdminLoginException extends Exception  implements java.io.Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant AE_MSG. */
	public static final int AE_MSG = 2;
	
	/** The exception type. */
	private int exceptionType = 0;
	
	/** The Constant AE_FILEERROR. */
	public static final int AE_FILEERROR = 0;
	
	/** The Constant AE_NOFILE. */
	public static final int AE_NOFILE = 1;

	/**
	 * Instantiates a new admin login exception.
	 */
	public AdminLoginException()
	{
		
	}
	
	/**
	 * Instantiates a new admin login exception.
	 *
	 * @param msg the msg
	 */
	public AdminLoginException(String msg) {
		super(msg);
		exceptionType = AE_FILEERROR;
	}

	/**
	 * Instantiates a new admin login exception.
	 *
	 * @param type the type
	 * @param msg the msg
	 */
	public AdminLoginException(int type, String msg) {
		super(msg);
		exceptionType = type;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public int getType() {
		return exceptionType;
	}
}
