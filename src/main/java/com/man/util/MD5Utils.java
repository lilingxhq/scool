package com.man.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5 加密技术工具类
 */
public class MD5Utils {
	private MD5Utils(){
		throw new AssertionError();
	}

	private static final char[] hexDigits  = "0123456789abcdef".toCharArray();
	private static final String MD5 = "MD5";

	private static String byteArrayToHexString(byte[] b) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < b.length; i++){
			sb.append(byteToHexString(b[i]));
		}


		return sb.toString();
	}

	/**
	 * byte字节获取16进制字符串
	 * @param b
	 * @return
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0){
			n += 256;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return String.valueOf(hexDigits[d1] + hexDigits[d2]);
	}

	/**
	 * md5加密
	 * @param origin
	 * @param charset
	 * @return
	 */
	public static String md5Encry(String origin, String charset) {
		try {
			MessageDigest md = MessageDigest.getInstance(MD5);
			if (null == charset || "".equals(charset)){
				origin = byteArrayToHexString(md.digest(origin.getBytes()));
			}else{
				origin = byteArrayToHexString(md.digest(origin.getBytes(charset)));
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return origin;
	}
	/**
	 * MD5加密
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String md5Encry(String str){
		MessageDigest md;
		try {
			md = MessageDigest.getInstance(MD5);
			return byteArrayToHexString(md.digest(str.getBytes())).toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

}