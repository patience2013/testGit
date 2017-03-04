package xiong.com.mvptest.util;

import android.annotation.SuppressLint;
import android.text.TextUtils;


import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import xiong.com.mvptest.App;

public class StringUtil {

	/**
	 * 判断字符串是否为空
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isNullOrEmpty(String string) {
		if (string == null || string.length() == 0) {
			return true;
		}
		return false;
	}

	@SuppressLint("SimpleDateFormat")
	public static String formatDate(Date date, String format) {
		SimpleDateFormat dateformat = new SimpleDateFormat(format);
		return dateformat.format(date);
	}

	public static String formateDate(long mills, String format) {
		return formatDate(new Date(mills), format);
	}

	public static String formatCustomDate(String dateString, String format) {
		int b = dateString.indexOf("(") + 1;
		int e = dateString.indexOf("+");
		String str = dateString.substring(b, e);
		Date date = new Date(Long.valueOf(str));
		return formatDate(date, format);
	}

	// 转md5
	public static String md5(String sourceStr) {
		byte[] source = sourceStr.getBytes();
		String s = null;
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest();
			char[] str = new char[16 * 2];
			int k = 0;
			for (int i = 0; i < 16; i++) {
				byte byte0 = tmp[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			s = new String(str);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return s;
	}

	public static boolean isMobile(String mobile) {
		/*
		 * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		 * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
		 * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		 */
		String telRegex = "[1][3578]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
		if (TextUtils.isEmpty(mobile))
			return false;
		else
			return mobile.matches(telRegex);
	}
	
	public static String imgUrl(String url) {
		if (TextUtils.isEmpty(url)) return "";
		
		if (url.startsWith("http")) {
			return url;
		} else {
			return App.IMG_HOST + url;

		}
	}
	//判断是否是正确的邮编
	public static boolean sZipNO(String zipString){
		String str = "^[1-9][0-9]{5}$";
		return Pattern.compile(str).matcher(zipString).matches();
	}
}
