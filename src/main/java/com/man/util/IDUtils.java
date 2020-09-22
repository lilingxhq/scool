package com.man.util;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @date 2020-05-23 23:46
 */
public class IDUtils {
	private IDUtils(){
		throw new AssertionError();
	}

	/**
	 * 获取uuid
	 * <p>
	 *     使用默认长度为32位
	 * </p>
	 * @return 获取到的uuid值
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-","");
	}

	public static String getUUID(int bit){
		return null;
	}

	/**
	 * 生成数字随机码
	 * @param type
	 * @return
	 */
	public static String generateNumbersRandomCode(String type){
		StringBuilder sb = new StringBuilder(type);
		return  sb.append(getDateyyMMddHHmmssSSrrrr())
				.append(VerificationUtils.getVerificationDigit(2)).toString();


	}

	/**
	 *  生成格式YYYYMMDD
	 */
	public static  String getDateYYYYMMDD(){
		LocalDateTime ldt = LocalDateTime.now();
		return  ldt.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	}


	public static String getDateyyMMddHHmmssSSrrrr(){
		LocalDateTime ldt = LocalDateTime.now();
		return ldt.format(DateTimeFormatter.ofPattern("yyMMddHHmmssSS"));
	}
}
