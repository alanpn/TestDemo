package com.sandlife.baselibrary.util;


public class EventUtil {
	
	private static long lastTime = 0;
	
	/**
	 * 防止单击多次响应多次点击事件
	 * @param interval 是设置单击时间间隔（毫秒）
	 * @return
	 */
	public static  boolean eventCount(long interval){
		long eventTime = System.currentTimeMillis();
		long time = eventTime - lastTime;
		if(0 < time && time <= interval ){
			return true;
		}else{
			lastTime = eventTime;
			return false;
		}
	}

	
}
