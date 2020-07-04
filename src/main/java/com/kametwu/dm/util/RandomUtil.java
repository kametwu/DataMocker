package com.kametwu.dm.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import org.joda.time.DateTime;

import com.kametwu.dm.bo.Code;
import com.kametwu.dm.cache.CacheManager;

public class RandomUtil {
	private static Random random = new Random();
	private static String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static double getDouble(int min, int max, int scale) {
		double value = min + random.nextDouble() * (max - min + 1);
		return new BigDecimal(value).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static double getDouble(double min, double max, int scale) {
		double value = min + random.nextDouble() * (max - min + 1);
		return new BigDecimal(value).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static int getInteger(int min, int max) {
		int value = min + random.nextInt(max - min + 1);
		return value;
	}

	public static int getInteger(int max) {
		return getInteger(0, max);
	}

	public static long getLong(long min, long max) {
		return (long) (min + random.nextDouble() * (max - min + 1));
	}

	public static String getString(long minLen, long maxLen) {
		long len = getLong(minLen, maxLen);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			sb.append(CHARS.charAt(random.nextInt(62)));
		}
		return sb.toString();
	}

	public static String getString(int minLen, int maxLen) {
		return getString(Long.parseLong(String.valueOf(minLen)), Long.parseLong(String.valueOf(maxLen)));
	}

	public static String getString(int len) {
		return getString(len, len);
	}

	/**
	 * 
	 * @param minDate 开始日期，格式：yyyy-MM-dd
	 * @param maxDate 结束日期，格式：yyyy-MM-dd
	 * @return 返回一个介于开始日期和结束日期之间的随机日期，格式：yyyy-MM-dd
	 */
	public static String getDate(String minDate, String maxDate) {
		return getDate(new DateTime(minDate), new DateTime(maxDate)).toString("yyyy-MM-dd");
	}

	public static String getDate() {
		return getDate(new DateTime("1970-01-01"), new DateTime()).toString("yyyy-MM-dd");
	}

	public static String getDateTime(String minDate, String maxDate) {
		return getDate(new DateTime(minDate), new DateTime(maxDate)).toString("yyyy-MM-dd HH:mm:ss");
	}

	public static String getDateTime() {
		return getDate(new DateTime("1970-01-01"), new DateTime()).toString("yyyy-MM-dd HH:mm:ss");
	}

	private static DateTime getDate(DateTime minDate, DateTime maxDate) {
		DateTime bDate = new DateTime(minDate);
		DateTime eDate = new DateTime(maxDate);
		long diffMillis = eDate.getMillis() - bDate.getMillis();
		long randomDiff = getLong(0, diffMillis);
		DateTime newDate = bDate.plus(randomDiff);
		return newDate;
	}

	public static Code selectOneCode(List<Code> codeList) {
		return codeList.get(getInteger(codeList.size() - 1));
	}

	public static Code selectOneCode(String typeCode) {
		List<Code> codeList = CacheManager.findCodeListByType(typeCode);
		return selectOneCode(codeList);
	}

	public static String getYesOrNo() {
		return getInteger(1) == 1 ? "Y" : "N";
	}

	public static String getChineseChar() {
		try {
			byte[] b = new byte[2];
			b[0] = (new Integer(176 + Math.abs(random.nextInt(39))).byteValue());
			b[1] = (new Integer(161 + Math.abs(random.nextInt(93))).byteValue());
			return new String(b, "GBK"); // 转成中文
		} catch (UnsupportedEncodingException e) {
			return "*";
		}
//		return (char) (0x8140 + (int) (Math.random() * (0x9fa5 - 0x8140 + 1)));
	}

	public static void main(String[] args) { // 65-122
		for (int i = 0; i < 100; i++) {
			System.out.println(getChineseChar());
		}
	}

}
