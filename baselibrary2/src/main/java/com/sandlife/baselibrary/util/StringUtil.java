package com.sandlife.baselibrary.util;

import android.text.TextPaint;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;



public class StringUtil {
	private static String hexString = "1234567GHJKLMNBV";

	/**
	 * 字符串转换成十六进制值
	 * 
	 * @param bin
	 *            String 我们看到的要转换成十六进制的字符串
	 * @return
	 */
	public static String bin2hex(String bin) {
		char[] digital = "0123456789ABCDEF".toCharArray();
		StringBuffer sb = new StringBuffer("");
		byte[] bs = bin.getBytes();
		int bit;
		for (int i = 0; i < bs.length; i++) {
			bit = (bs[i] & 0x0f0) >> 4;
			sb.append(digital[bit]);
			bit = bs[i] & 0x0f;
			sb.append(digital[bit]);
		}
		return sb.toString();
	}

	/**
	 * 十六进制转换字符串
	 * 
	 * @param hex
	 *            String 十六进制
	 * @return String 转换后的字符串
	 */
	public static String hex2bin(String hex) {
		String digital = "0123456789ABCDEF";
		char[] hex2char = hex.toCharArray();
		byte[] bytes = new byte[hex.length() / 2];
		int temp;
		for (int i = 0; i < bytes.length; i++) {
			temp = digital.indexOf(hex2char[2 * i]) * 16;
			temp += digital.indexOf(hex2char[2 * i + 1]);
			bytes[i] = (byte) (temp & 0xff);
		}
		return new String(bytes);
	}

	public static String toStringVersion(String bytes) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(
				bytes.length() / 2);
		// 将每2位16进制整数组装成一个字节
		for (int i = 0; i < bytes.length(); i += 2)
			baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString
					.indexOf(bytes.charAt(i + 1))));

		return new String(baos.toByteArray());
	}

	public static boolean isBlank(String str) {
		int strLen;
		if ((str == null) || ((strLen = str.length()) == 0)
				|| str.equals("null")) {
			return true;
		}
		for (int i = 0; i < strLen; ++i) {
			if (!(Character.isWhitespace(str.charAt(i)))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotBlank(String str) {
		return (!(isBlank(str)));
	}
	public static String isString(String str) {
		if (str == null || str.equals("") || str.length() == 0
				|| str.equals("null")) {
			return "";
		} else {
			return str;
		}
	}
	/**
	 * 截取字符串
	 * 
	 * @param s
	 *            要被截取的字符串
	 * @param iLen
	 *            截取的长度
	 * @param hasDot
	 *            是否加...
	 * @return
	 */
	public static String getSubString(String s, int iLen, boolean hasDot) {
		if (StringUtil.isNotBlank(s)) {
			char c = ' ';
			String sAsc = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789.+-";
			String lsResult = " ";
			int iGetedLen = 0;
			boolean flag = false;
			for (int i = 0; i < s.length(); i++) {
				c = s.charAt(i);
				if (sAsc.indexOf((int) c) >= 0) {
					lsResult += c;
					iGetedLen += 1;
				} else {
					lsResult += c;
					iGetedLen += 2;
				}
				if (iGetedLen >= iLen) {
					if (i + 1 < s.length()) {
						flag = true;
					}
					break;
				}
			}
			if (iGetedLen <= iLen) {
				if (flag) {
					if (hasDot) {
						return lsResult.trim() + "...";
					} else {
						return lsResult.trim();
					}
				} else {
					return lsResult.trim();
				}
			}
			if (hasDot) {
				lsResult = lsResult + "...";
			}
			return (lsResult.trim());
		} else {
			return "";
		}
	}

	/**
	 * 给控件文字设置 粗体
	 * 
	 * @param view
	 *            需要设置 字体粗体 的控件
	 */
	public static void setFakeBoldText(TextView view) {
		TextPaint tp = view.getPaint();
		tp.setFakeBoldText(true);

	}

	/**
	 * 生成随机数
	 * 
	 * @return
	 */
	public static String getMath() {

		return TimeUtil.getDateTimeYMDHMS().toString()
				+ String.valueOf((int) (Math.random() * 100));
	}

	/**
	 * 金额不够12位补够12位
	 */
	public static String getAmt(String amt) {
		try {
			// amt =String.valueOf((int)(Double.valueOf(amt) * 100));
//			amt = String.valueOf((int) ((Double.valueOf(amt)) * 10) * 10);
//			amt = String.valueOf((int)(((Double.valueOf(amt)) * 10) * 10));
//			amt = String.valueOf((int)(Float.valueOf(amt)*100));
			amt=MoneyUtil.changeY2F(amt);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String amtString;
		switch (amt.length()) {
		case 1:
			amtString = "00000000000" + amt;
			break;
		case 2:
			amtString = "0000000000" + amt;
			break;
		case 3:
			amtString = "000000000" + amt;
			break;
		case 4:
			amtString = "00000000" + amt;
			break;
		case 5:
			amtString = "0000000" + amt;
			break;
		case 6:
			amtString = "000000" + amt;
			break;
		case 7:
			amtString = "00000" + amt;
			break;
		case 8:
			amtString = "0000" + amt;
			break;
		case 9:
			amtString = "000" + amt;
			break;
		case 10:
			amtString = "00" + amt;
			break;
		case 11:
			amtString = "0" + amt;
			break;
		case 12:
			amtString = amt;
			break;
		default:
			amtString = "0000000000";
			break;
		}
		return amtString;
	}
	public static String StringFilter(String str) throws PatternSyntaxException {
		// 清除掉所有特殊字符
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}
	public static List<String> removeDuplicateWithOrder(List<String> list) {
		Set set = new HashSet();
		List newList = new ArrayList();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (set.add(element))
				newList.add(element);
		}
		list.clear();
		list.addAll(newList);
		return list;
	}

	public static String[] getDistinct(String num[]) {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < num.length; i++) {
			if (!list.contains(num[i])) {// 如果list数组不包括num[i]中的值的话，就返回true。
				list.add(num[i]); // 在list数组中加入num[i]的值。已经过滤过。
			}
		}
		return list.toArray(new String[0]); // toArray（数组）方法返回数组。并要指定Integer类型。new
											// integer[o]的空间大小不用考虑。因为如果list中的长度大于0（你integer的长度），toArray方法会分配一个具有指定数组的运行时类型和此列表大小的新数组。
	}
	public static int SExchangeI(String money) {

		BigDecimal bg = new BigDecimal(money);
		int a = bg.intValue();
		return a;
	}
	public static boolean dateCompare(String d1, String d2) {
		Date dat1 = java.sql.Date.valueOf(d1);
		Date dat2 = java.sql.Date.valueOf(d2);
		boolean dateComPareFlag = true;
		if (dat2.compareTo(dat1) > 0) {
			dateComPareFlag = false;
		}
		return dateComPareFlag;
	}
	public static boolean isChinese(String str){
		String regEx = "[\u4e00-\u9fa5]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}
	/**
	 * 把小写字母转成大写字母
	 * @param str
	 * @return
	 */
	public static String lowerCaseToUpperCase(String str){
		char mChar = ' ';
		char endChar = ' ';
		String result = "";
		if(!"".equals(str) && str != null){
			int length =str.length();
			for (int i = 0; i < length; i++) {
				mChar = str.charAt(i);
				if(Character.isLowerCase(mChar)){
					endChar = Character.toUpperCase(mChar);
					result = result + endChar;
				}else{
					result = result + mChar;
				}
			}
		}
		return result;
	}

	/**
	 * 检查返回结果
	 * @param result
	 * @return
     */
	public static String checkResult(String result){
		String message = null;
		if (result == null){
			if (result.equals("成功")){
				message = "操作失败";
			}else {
				message = result;
			}
		}else{
			message = "操作失败";
		}
		return message;
	}
}