package com.sandlife.baselibrary.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPattern {
	private static final String URL = "[a-zA-z]+://[^\\s]*";
	private static final String EMAIL = "^([a-z0-9A-Z]+[-_\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	private static final String LOGINNAME = "^(?![.]+$)(?![_]+$)[a-z0-9A-Z_.]{3,50}$";
	private static final String PASSWORD = "^[a-zA-Z0-9_]{6,31}$";
	private static final String CHARS = "[`~!@#$%^&*()+ =|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
	private static final String TEL = "\\d{3}-\\d{8}|\\d{4}-\\d{8}|\\d{4}-\\d{7}|\\d{3}-\\d{7}|\\d{3}\\d{8}|\\d{4}\\d{8}|\\d{3}\\d{7}|\\d{4}\\d{7}";
	private static final String ZIP_CODE = "[1-9]\\d{5}(?!\\d)";
	private static final String ID_CARD_NO ="^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";
	private static final String PHONE = "^1[2|3|4|5|6|7|8|9][0-9]\\d{8}$";
	private static final String NUMBER="^[0-9]+$";
    private static final String PASSID_CARD_NO ="^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
	private static final String PASSID_CARD_NO1 ="^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
	private static final String USER_PWD = "^(?![\\d]+$)(?![\\D]+$)[\\S]{8,20}$"; //非全数字、非全字母、不含空白 8-20位字符串

	private static final String MONEY = "^([1-9][\\d]{0,100}|0)(\\.[\\d]{0,2})?$";

	public static boolean isNumber(String value){
		return value.matches(NUMBER);
	}
	/**
	 * 匹配手机号码
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isPhone(String value) {
		return value.matches(PHONE);
	}
	/**
	 * 匹配身份证
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isIdCardNo(String value) {
		return value.matches(ID_CARD_NO);
	}
	public static boolean isPASSIdCardNo(String value) {
		return value.matches(PASSID_CARD_NO);
	}
	public static boolean isPASSIdCardNo1(String value) {
		return value.matches(PASSID_CARD_NO1);
	}
	/**
	 * 匹配密码是否合法(密码长度4-10字母和数字)
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isPassWord(String value) {
		return value.matches(PASSWORD);
	}
	/**
	 * 匹配帐号是否合法(字母开头，允许4-10字节，允许字母数字下划线)
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isLoginName(String value) {
		return value.matches(LOGINNAME);
	}
	/**
	 * 匹配中国邮政编码
	 */
	public static boolean isZipCode(String value) {
		return value.matches(ZIP_CODE);
	}
	/**
	 * 匹配国内电话号码
	 */
	public static boolean isTel(String value) {
		if (value.trim().equals("")) {
			return true;
		}
		return value.matches(TEL);
	}
	/**
	 * 检测非法字符
	 */
	public static boolean isChars(String str) {
		Pattern p = Pattern.compile(CHARS);
		Matcher m = p.matcher(str);
		return m.find();
	}
	/**
	 * 检测Email
	 */
	public static boolean isEmail(String email) {
		return Pattern.matches(EMAIL, email);
	}
	public static boolean isUrl(String url) {
		return Pattern.matches(URL, url);
	}
	public static String encode(String regex) {
		if (regex.indexOf("[") != -1)
			regex = regex.replace("[", "\\[");
		if (regex.indexOf("]") != -1)
			regex = regex.replace("]", "\\]");
		if (regex.indexOf("(") != -1)
			regex = regex.replace("(", "\\(");
		if (regex.indexOf(")") != -1)
			regex = regex.replace(")", "\\)");
		return regex;
	}
		public  static  void  fillTelSpeace(final  EditText mAddCardNumEdt){
	  	  mAddCardNumEdt.addTextChangedListener(new TextWatcher() {
					public void onTextChanged(CharSequence s, int start, int before,int count) {
						if (count == 1) {
							if (s.length() == 3) {
								mAddCardNumEdt.setText(s + "-");
								mAddCardNumEdt.setSelection(4);
							}
							if (s.length() == 8) {
								mAddCardNumEdt.setText(s + "-");
								mAddCardNumEdt.setSelection(9);
							}
						} else if (count == 0) {
							if (s.length() == 3) {
								mAddCardNumEdt.setText(s.subSequence(0,s.length() - 1));
								mAddCardNumEdt.setSelection(2);
							}
							if (s.length() == 8) {
								mAddCardNumEdt.setText(s.subSequence(0,s.length() - 1));
								mAddCardNumEdt.setSelection(7);
							}
						}
					}
					public void beforeTextChanged(CharSequence s, int start, int count,
							int after) {
					}
					public void afterTextChanged(Editable s) {
					}
				});
		}
		/**
		 * 银行卡补齐 空格
		 * 
		 */
		public  static  void  fillBankNumSpeace(final  EditText mAddCardNumEdt){
		  	  mAddCardNumEdt.addTextChangedListener(new TextWatcher() {
						public void onTextChanged(CharSequence s, int start, int before,
								int count) {
							if (count == 1) {
								if (s.length() == 4) {
									mAddCardNumEdt.setText(s + "-");
									mAddCardNumEdt.setSelection(5);
								}
								if (s.length() == 9) {
									mAddCardNumEdt.setText(s + "-");
									mAddCardNumEdt.setSelection(10);
								}
								if (s.length() == 14) {
									mAddCardNumEdt.setText(s + "-");
									mAddCardNumEdt.setSelection(15);
								}
								if (s.length() == 19) {
									mAddCardNumEdt.setText(s + "-");
									mAddCardNumEdt.setSelection(20);
								}
							} else if (count == 0) {
								if (s.length() == 4) {
									mAddCardNumEdt.setText(s.subSequence(0,s.length() - 1));
									mAddCardNumEdt.setSelection(3);
								}
								if (s.length() == 9) {
									mAddCardNumEdt.setText(s.subSequence(0,s.length() - 1));
									mAddCardNumEdt.setSelection(8);
								}
								if (s.length() == 14) {
									mAddCardNumEdt.setText(s.subSequence(0,s.length() - 1));
									mAddCardNumEdt.setSelection(13);
								}
								if (s.length() == 19) {
									mAddCardNumEdt.setText(s.subSequence(0,s.length() - 1));
									mAddCardNumEdt.setSelection(18);
								}
							}
						}
						public void beforeTextChanged(CharSequence s, int start, int count,
								int after) {
						}
						public void afterTextChanged(Editable s) {
						}
					});
		    }

	/**
	 * 检测用户密码是否是8～20位数字和字母组合
	 * @param userPwd
	 * @return
     */
	public static boolean isUserPwd (String userPwd){
		return userPwd.matches(USER_PWD);
	}

	/**
	 * 输入金额控制小数点后两位
	 * @param money
	 * @return
	 */
	public static boolean isMoney(String money){
		return money.matches(MONEY);
	}
}
