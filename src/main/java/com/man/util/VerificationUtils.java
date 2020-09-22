package com.man.util;

import java.util.Random;

/**
 * @date 2020-07-08 17:01
 */
public class VerificationUtils {
	private VerificationUtils(){
		throw new AssertionError();
	}
	private final static String  VERIFICATION ="0123456789";
	/**
	 * 根据指定位数生成随机位数的验证码
	 * @param count 随机位数
	 * @return
	 */
	public static String getVerificationDigit(int count){
		StringBuffer sb = new StringBuffer();
		String str = VERIFICATION;
		Random r = new Random();
		for(int i=0;i<count;i++){
			int num = r.nextInt(str.length());
			sb.append(str.charAt(num));
			str = str.replace((str.charAt(num)+""), "");
		}
		return sb.toString();
	}

	/**
	 * 获取随机字符串
	 *
	 * @param length
	 * @return
	 */
	public static String getStringRandom(int length) {
		String val = "";
		Random random = new Random();
		//参数length，表示生成几位随机数
		for(int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			//输出字母还是数字
			if( "char".equalsIgnoreCase(charOrNum) ) {
				//输出是大写字母还是小写字母
				int temp = random.nextInt(2)%2 == 0 ? 65 : 97;
				val += (char)(random.nextInt(26) + temp);
			} else if( "num".equalsIgnoreCase(charOrNum) ) {
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}
}
