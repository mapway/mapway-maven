package com.ksyzt.gwt.server.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 *  utilitty
 * @author Sea
 * @version 0.1 Created on 2005-9-13 10:55:05 �޶���ʷ��
 */
public class Utility {

	/** The Constant QUOTE_ENCODE. */
	// Constants used by escapeHTMLTags
	private static final char[] QUOTE_ENCODE = "&quot;".toCharArray();

	/** The Constant AMP_ENCODE. */
	private static final char[] AMP_ENCODE = "&amp;".toCharArray();

	/** The Constant LT_ENCODE. */
	private static final char[] LT_ENCODE = "&lt;".toCharArray();

	/** The Constant GT_ENCODE. */
	private static final char[] GT_ENCODE = "&gt;".toCharArray();

    /**
     * String 2 time.
     *
     * @param dateString the date string
     * @return the java.sql. timestamp
     * @throws ParseException the parse exception
     */
    public final static java.sql.Timestamp string2Time(String dateString)
			throws ParseException {
		DateFormat dateFormat;
		dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		// dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss.SSS",
		// Locale.ENGLISH);
		// �趨��ʽ
		// dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss",
		// Locale.ENGLISH);
		dateFormat.setLenient(false);
		java.util.Date timeDate = dateFormat.parse(dateString);// util����
		java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());
		// Timestamp����,timeDate.getTime()����һ��long��
		return dateTime;
	}

    /**
     * Check email.
     *
     * @param email the email
     * @return true, if successful
     */
    public static boolean checkEmail(String email) {
		String ps = "^([\\w-\\.]+)@[\\w-.]+(\\.?[a-zA-Z]{2,4}$)";
		Pattern p = Pattern.compile(ps);
		Matcher m = p.matcher(email);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}

	}

    /**
     * Replaces all instances of oldString with newString in line.
     *
     * @param line      the String to search to perform replacements on
     * @param oldString the String that should be replaced by newString
     * @param newString the String that will replace all instances of oldString
     * @return a String will all instances of oldString replaced by newString
     */
    public static final String replace(String line, String oldString,
			String newString) {
		if (line == null) {
			return null;
		}
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0) {
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = line.indexOf(oldString, i)) > 0) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;
	}

    /**
     * Replaces all instances of oldString with newString in line with the added
     * feature that matches of newString in oldString ignore case.
     *
     * @param line      the String to search to perform replacements on
     * @param oldString the String that should be replaced by newString
     * @param newString the String that will replace all instances of oldString
     * @return a String will all instances of oldString replaced by newString
     */
    public static final String replaceIgnoreCase(String line, String oldString,
			String newString) {
		if (line == null) {
			return null;
		}
		String lcLine = line.toLowerCase();
		String lcOldString = oldString.toLowerCase();
		int i = 0;
		if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = lcLine.indexOf(lcOldString, i)) > 0) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;
	}

    /**
     * Replaces all instances of oldString with newString in line with the added
     * feature that matches of newString in oldString ignore case. The count
     * paramater is set to the number of replaces performed.
     *
     * @param line      the String to search to perform replacements on
     * @param oldString the String that should be replaced by newString
     * @param newString the String that will replace all instances of oldString
     * @param count     a value that will be updated with the number of replaces            performed.
     * @return a String will all instances of oldString replaced by newString
     */
    public static final String replaceIgnoreCase(String line, String oldString,
			String newString, int[] count) {
		if (line == null) {
			return null;
		}
		String lcLine = line.toLowerCase();
		String lcOldString = oldString.toLowerCase();
		int i = 0;
		if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
			int counter = 0;
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = lcLine.indexOf(lcOldString, i)) > 0) {
				counter++;
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			count[0] = counter;
			return buf.toString();
		}
		return line;
	}

    /**
     * Replaces all instances of oldString with newString in line. The count
     * Integer is updated with number of replaces.
     *
     * @param line      the String to search to perform replacements on
     * @param oldString the String that should be replaced by newString
     * @param newString the String that will replace all instances of oldString
     * @param count     the count
     * @return a String will all instances of oldString replaced by newString
     */
    public static final String replace(String line, String oldString,
			String newString, int[] count) {
		if (line == null) {
			return null;
		}
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0) {
			int counter = 0;
			counter++;
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = line.indexOf(oldString, i)) > 0) {
				counter++;
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			count[0] = counter;
			return buf.toString();
		}
		return line;
	}

    /**
     * Escape HTML tags.
     *
     * @param in the text to be converted.
     * @return String string
     */
    public static final String escapeHTMLTags(String in) {
		if (in == null) {
			return null;
		}
		char ch;
		int i = 0;
		int last = 0;
		char[] input = in.toCharArray();
		int len = input.length;
		StringBuffer out = new StringBuffer((int) (len * 1.3));
		for (; i < len; i++) {
			ch = input[i];
			if (ch > '>') {
				continue;
			} else if (ch == '<') {
				if (i > last) {
					out.append(input, last, i - last);
				}
				last = i + 1;
				out.append(LT_ENCODE);
			} else if (ch == '>') {
				if (i > last) {
					out.append(input, last, i - last);
				}
				last = i + 1;
				out.append(GT_ENCODE);
			}
		}
		if (last == 0) {
			return in;
		}
		if (i > last) {
			out.append(input, last, i - last);
		}
		return out.toString();
	}

	/**
	 * Used by the hash method.
	 */
	private static MessageDigest digest = null;

    /**
     * Hashes a String using the Md5 algorithm and returns the result as a
     * String of hexadecimal numbers. This method is synchronized to avoid
     * excessive MessageDigest object creation. If calling this method becomes a
     * bottleneck in your code, you may wish to maintain a pool of MessageDigest
     * objects instead of using this method.
     * <p>
     * A hash is a one-way function -- that is, given an input, an output is
     * easily computed. However, given the output, the input is almost
     * impossible to compute. This is useful for passwords since we can store
     * the hash and a hacker will then have a very hard time determining the
     * original password.
     * <p>
     * In Jive, every time a user logs in, we simply take their plain text
     * password, compute the hash, and compare the generated hash to the stored
     * hash. Since it is almost impossible that two passwords will generate the
     * same hash, we know if the user gave us the correct password or not. The
     * only negative to this system is that password recovery is basically
     * impossible. Therefore, a reset password method is used instead.
     *
     * @param data the String to compute the hash of.
     * @return a hashed version of the passed-in String
     */
    public synchronized static final String hash(String data) {
		if (digest == null) {
			try {
				digest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException nsae) {
				System.err.println("Failed to load the MD5 MessageDigest. "
						+ "Jive will be unable to function normally.");
				nsae.printStackTrace();
			}
		}
		// Now, compute hash.
		digest.update(data.getBytes());
		return encodeHex(digest.digest());
	}

    /**
     * Turns an array of bytes into a String representing each byte as an
     * unsigned hex number.
     * <p>
     * Method by Santeri Paavolainen, Helsinki Finland 1996<br>
     * (c) Santeri Paavolainen, Helsinki Finland 1996<br>
     * Distributed under LGPL.
     *
     * @param bytes an array of bytes to convert to a hex-string
     * @return generated hex string
     */
    public static final String encodeHex(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		int i;

		for (i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString();
	}

    /**
     * Turns a hex encoded string into a byte array. It is specifically meant to
     * "reverse" the toHex(byte[]) method.
     *
     * @param hex a hex encoded String to transform into a byte array.
     * @return a byte array representing the hex String[
     */
    public static final byte[] decodeHex(String hex) {
		char[] chars = hex.toCharArray();
		byte[] bytes = new byte[chars.length / 2];
		int byteCount = 0;
		for (int i = 0; i < chars.length; i += 2) {
			byte newByte = 0x00;
			newByte |= hexCharToByte(chars[i]);
			newByte <<= 4;
			newByte |= hexCharToByte(chars[i + 1]);
			bytes[byteCount] = newByte;
			byteCount++;
		}
		return bytes;
	}

	/**
	 * Returns the the byte value of a hexadecmical char (0-f). It's assumed
	 * that the hexidecimal chars are lower case as appropriate.
	 * 
	 * @param ch
	 *            a hexedicmal character (0-f)
	 * @return the byte value of the character (0x00-0x0F)
	 */
	private static final byte hexCharToByte(char ch) {
		switch (ch) {
		case '0':
			return 0x00;
		case '1':
			return 0x01;
		case '2':
			return 0x02;
		case '3':
			return 0x03;
		case '4':
			return 0x04;
		case '5':
			return 0x05;
		case '6':
			return 0x06;
		case '7':
			return 0x07;
		case '8':
			return 0x08;
		case '9':
			return 0x09;
		case 'a':
			return 0x0A;
		case 'b':
			return 0x0B;
		case 'c':
			return 0x0C;
		case 'd':
			return 0x0D;
		case 'e':
			return 0x0E;
		case 'f':
			return 0x0F;
		}
		return 0x00;
	}

	

	/** The Constant fillchar. */
	private static final int fillchar = '=';

	/** The Constant cvt. */
	private static final String cvt = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
			+ "abcdefghijklmnopqrstuvwxyz" + "0123456789+/";

	

	/**
	 * Pseudo-random number generator object for use with randomString(). The
	 * Random class is not considered to be cryptographically secure, so only
	 * use these random Strings for low to medium security applications.
	 */
	private static Random randGen = new Random();

	/**
	 * Array of numbers and letters of mixed case. Numbers appear in the list
	 * twice so that there is a more equal chance that a number will be picked.
	 * We can use the array to get a random number or letter by picking a random
	 * array index.
	 */
	private static char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz"
			+ "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

    /**
     * Returns a random String of numbers and letters (lower and upper case) of
     * the specified length. The method uses the Random class that is built-in
     * to Java which is suitable for low to medium grade security uses. This
     * means that the output is only pseudo random, i.e., each number is
     * mathematically generated so is not truly random.
     * <p>
     * <p>
     * The specified length must be at least one. If not, the method will return
     * null.
     *
     * @param length the desired length of the random String to return.
     * @return a random String of numbers and letters of the specified length.
     */
    public static final String randomString(int length) {
		if (length < 1) {
			return null;
		}
		// Create a char buffer to put random letters and numbers in.
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}

    /**
     * Random digital.
     *
     * @param length the length
     * @return the string
     */
    public static final String randomDigital(int length) {
		if (length < 1) {
			return null;
		}
		// Create a char buffer to put random letters and numbers in.
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(10)];
		}
		return new String(randBuffer);
	}

    /**
     * Replace CRLF.
     *
     * @param p the p
     * @return the string
     */
    public static String replaceCRLF(String p) {
		p = escapeForXML(p);
		p = p.replace("\n", "<br>");
		return p;
	}

    /**
     * Replace AMP.
     *
     * @param p the p
     * @return the string
     */
    public static String replaceAMP(String p) {

		p = p.replace("&amp;", "&");
		return p;
	}

    /**
     * Replace CRLF 2.
     *
     * @param p the p
     * @return the string
     */
    public static String replaceCRLF2(String p) {
		p = escapeForXML(p);
		p = p.replace("\\r\\n", "<br>");
		return p;
	}

    /**
     * Intelligently chops a String at a word boundary (whitespace) that occurs
     * at the specified index in the argument or before. However, if there is a
     * newline character before <code>length</code>, the String will be chopped
     * there. If no newline or whitespace is found in <code>string</code> up to
     * the index <code>length</code>, the String will chopped at
     * <code>length</code>.
     * <p>
     * For example, chopAtWord("This is a nice String", 10) will return "This is
     * a" which is the first word boundary less than or equal to 10 characters
     * into the original String.
     *
     * @param string the String to chop.
     * @param length the index in <code>string</code> to start looking for a            whitespace boundary at.
     * @return a substring of <code>string</code> whose length is less than or         equal to <code>length</code>, and that is chopped at whitespace.
     */
    public static final String chopAtWord(String string, int length) {
		if (string == null) {
			return string;
		}

		char[] charArray = string.toCharArray();
		int sLength = string.length();
		if (length < sLength) {
			sLength = length;
		}

		// First check if there is a newline character before length; if so,
		// chop word there.
		for (int i = 0; i < sLength - 1; i++) {
			// Windows
			if (charArray[i] == '\r' && charArray[i + 1] == '\n') {
				return string.substring(0, i + 1);
			}
			// Unix
			else if (charArray[i] == '\n') {
				return string.substring(0, i);
			}
		}
		// Also check boundary case of Unix newline
		if (charArray[sLength - 1] == '\n') {
			return string.substring(0, sLength - 1);
		}

		// Done checking for newline, now see if the total string is less than
		// the specified chop point.
		if (string.length() < length) {
			return string;
		}

		// No newline, so chop at the first whitespace.
		for (int i = length - 1; i > 0; i--) {
			if (charArray[i] == ' ') {
				return string.substring(0, i).trim();
			}
		}

		// Did not find word boundary so return original String chopped at
		// specified length.
		return string.substring(0, length);
	}

	// Create a regular expression engine that is used by the highlightWords
	// method below.
	// private static Perl5Util perl5Util = new Perl5Util();

	/**
	 * Highlights words in a string. Words matching ignores case. The actual
	 * higlighting method is specified with the start and end higlight tags.
	 * Those might be beginning and ending HTML bold tags, or anything else.
	 * <p>
	 * 
	 * This method is under the Jive Open Source Software License and was
	 * written by Mark Imbriaco.
	 *
	 * @param string
	 *            the String to highlight words in.
	 * @return a new String with the specified words highlighted.
	 */
	/*
	 * public static final String highlightWords(String string, String[] words,
	 * String startHighlight, String endHighlight) { if (string == null || words
	 * == null || startHighlight == null || endHighlight == null) { return null;
	 * }
	 * 
	 * StringBuffer regexp = new StringBuffer(); // Iterate through each word
	 * and generate a word list for the regexp. for (int x=0; x<words.length;
	 * x++) { // Excape "|" and "/" to keep us out of trouble in our regexp.
	 * words[x] = perl5Util.substitute("s#([\\|\\/\\.])#\\\\$1#g", words[x]); if
	 * (regexp.length() > 0) { regexp.append("|"); } regexp.append(words[x]); }
	 * // Escape the regular expression delimiter ("/"). startHighlight =
	 * perl5Util.substitute("s#\\/#\\\\/#g", startHighlight); endHighlight =
	 * perl5Util.substitute("s#\\/#\\\\/#g", endHighlight); // Build the regular
	 * expression. insert() the first part. regexp.insert(0, "s/\\b("); // The
	 * word list is here already, so just append the rest.
	 * regexp.append(")\\b/"); regexp.append(startHighlight);
	 * regexp.append("$1"); regexp.append(endHighlight); regexp.append("/igm");
	 * // Do the actual substitution via a simple regular expression. return
	 * perl5Util.substitute(regexp.toString(), string); }
	 */

    /**
     * Escapes all necessary characters in the String so that it can be used in
     * an XML doc.
     *
     * @param string the string to escape.
     * @return the string with appropriate characters escaped.
     */
    public static final String escapeForXML(String string) {
		if (string == null) {
			return null;
		}
		char ch;
		int i = 0;
		int last = 0;
		char[] input = string.toCharArray();
		int len = input.length;
		StringBuffer out = new StringBuffer((int) (len * 1.3));
		for (; i < len; i++) {
			ch = input[i];
			if (ch > '>') {
				continue;
			} else if (ch == '<') {
				if (i > last) {
					out.append(input, last, i - last);
				}
				last = i + 1;
				out.append(LT_ENCODE);
			} else if (ch == '&') {
				if (i > last) {
					out.append(input, last, i - last);
				}
				last = i + 1;
				out.append(AMP_ENCODE);
			} else if (ch == '"') {
				if (i > last) {
					out.append(input, last, i - last);
				}
				last = i + 1;
				out.append(QUOTE_ENCODE);
			}
		}
		if (last == 0) {
			return string;
		}
		if (i > last) {
			out.append(input, last, i - last);
		}
		return out.toString();
	}

    /**
     * Unescapes the String by converting XML escape sequences back into normal
     * characters.
     *
     * @param string the string to unescape.
     * @return the string with appropriate characters unescaped.
     */
    public static final String unescapeFromXML(String string) {
		string = replace(string, "&lt;", "<");
		string = replace(string, "&gt;", ">");
		string = replace(string, "&quot;", "\"");
		return replace(string, "&amp;", "&");
	}

	/** The Constant zeroArray. */
	private static final char[] zeroArray = "0000000000000000".toCharArray();

    /**
     * Pads the supplied String with 0's to the specified length and returns the
     * result as a new String. For example, if the initial String is "9999" and
     * the desired length is 8, the result would be "00009999". This type of
     * padding is useful for creating numerical values that need to be stored
     * and sorted as character data. Note: the current implementation of this
     * method allows for a maximum <tt>length</tt> of 16.
     *
     * @param string the original String to pad.
     * @param length the desired length of the new padded String.
     * @return a new String padded with the required number of 0's.
     */
    public static final String zeroPadString(String string, int length) {
		if (string == null || string.length() > length) {
			return string;
		}
		StringBuffer buf = new StringBuffer(length);
		buf.append(zeroArray, 0, length - string.length()).append(string);
		return buf.toString();
	}

    /**
     * Formats a Date as a fifteen character long String made up of the Date's
     * padded millisecond value.
     *
     * @param date the date
     * @return a Date encoded as a String.
     */
    public static final String dateToMillis(Date date) {
		return zeroPadString(Long.toString(date.getTime()), 15);
	}

    /**
     * ����һ���ַ�ļ�����ʽ��?MD5 �㷨.
     *
     * @param originalStr the original str
     * @return the string
     * @author Sea
     */
    public static String encrypt(String originalStr) {
		return hash(originalStr);
	}

	// /**
	// * GBK ��Asciiת��
	// * @author Sea
	// */
	// public static String ChineseStringToAscii(String s)
	// {
	// try
	// {
	// // CharToByteConverter toByte = CharToByteConverter.getDefault();
	// CharToByteConverter toByte = CharToByteConverter.getConverter("GBK");
	// byte orig[] = toByte.convertAll(s.toCharArray());
	// char dest[] = new char[orig.length];
	// for(int i = 0; i < orig.length; i++)
	// dest[i] = (char)(orig[i] & 0xff);
	//
	// return new String(dest);
	// }
	// catch(Exception e)
	// {
	// e.printStackTrace();
	// }
	// return s;
	// }

    /**
     * GBK ��UTF-8ת����.
     *
     * @param s the s
     * @return the string
     * @author Sea
     */
    public static String ChineseStringToUTF(String s) {
		try {
			return new String(s.getBytes(), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

    /**
     * Gets the now long.
     *
     * @return the now long
     */
    public static long getNowLong() {
		long nCurrentTime = System.currentTimeMillis();
		return nCurrentTime;
	}

    /**
     * Gets the now date.
     *
     * @return the now date
     */
    public static java.util.Date getNowDate() {
		java.util.Date utilDate = new java.util.Date(getNowLong());
		return utilDate;
	}

    /**
     * Gets the now SQL date.
     *
     * @return the now SQL date
     */
    public static java.sql.Date getNowSQLDate() {
		// java.util.Date��java.sql.Date��ת��
		java.sql.Date sqlDate = new java.sql.Date(getNowLong());
		return sqlDate;
	}

    /**
     * Gets the now SQL time.
     *
     * @return the now SQL time
     */
    public static java.sql.Time getNowSQLTime() {
		java.sql.Time sqlTime = new java.sql.Time(getNowLong());
		return sqlTime;
	}

    /**
     * Gets the now SQL timestamp.
     *
     * @return the now SQL timestamp
     */
// ������������ݿ���������ں�ʱ����
	public static java.sql.Timestamp getNowSQLTimestamp() {
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(getNowLong());
		return sqlTimestamp;
	}

    /**
     * Translate tags.
     *
     * @param tags the tags
     * @return the string
     */
    public static String TranslateTags(String tags) {

		String[] tagss = tags.split(" ");

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tagss.length; i++) {
			sb.append("&nbsp;<a href=\"post_tagSearch.jsp?tag=" + tagss[i]
					+ "\">" + tagss[i] + "</a>");
		}
		return sb.toString();
	}

    /**
     * Gets the user link.
     *
     * @param id   the id
     * @param name the name
     * @return the user link
     */
    public static String getUserLink(BigDecimal id, String name) {
		return "<a href='person.jsp?uid=" + id.toString() + "'>" + name
				+ "</a>";
	}

    /**
     * Checks if is digital.
     *
     * @param s the s
     * @return true, if is digital
     */
    public static boolean isDigital(String s) {
		boolean b = true;
		if (s == null || s.equals(""))
			return false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= '0' && c <= '9') {

			} else {
				b = false;
				break;
			}
		}
		return b;
	}

    /**
     * Gets the date string.
     *
     * @param l the l
     * @return the date string
     */
    public static String getDateString(long l) {
		String v = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(l));
		return v;
	}

    /**
     * Gets the string.
     *
     * @param o the o
     * @return the string
     */
    public static String getString(Object o) {
		if (o == null)
			return "";
		return o.toString();
	}

    /**
     * Write to file.
     *
     * @param filename the filename
     * @param content  the content
     */
    public static void WriteToFile(String filename, byte[] content) {
		try {
			FileOutputStream fo = new FileOutputStream(filename);
			fo.write(content);
			fo.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    /**
     * Read from file.
     *
     * @param fileName the file name
     * @return the byte[]
     */
    public static byte[] ReadFromFile(String fileName) {

		File ff = new java.io.File(fileName);
		long filelength = ff.length();
		byte[] code = new byte[(int) filelength];

		InputStream inStream;
		try {
			inStream = new FileInputStream(fileName);
			inStream.read(code);
			inStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return code;
	}

    /**
     * To utf 8 string.
     *
     * @param s the s
     * @return the string
     */
    public static String toUtf8String(String s) {// String Buffer����
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					// System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

    /**
     * �������ڸ�ʽ����ʱ��.
     *
     * @param lon long��������ʱ�䣩
     * @param str String�����ڸ�ʽ��
     * @return String string
     */
    public static String longRstr(long lon, String str) {
		String rstr = "";
		if (lon < 1)
			return rstr;
		SimpleDateFormat df = new SimpleDateFormat(str);
		try {
			rstr = df.format(new Date(lon));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rstr;
	}
}
