package com.zxl78585.velocity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * <pre>
 * Capital Group Finance Service
 * Capital Finance Service Site
 * 
 * Mixky Co., Ltd. 2015
 * &#64;author Zhaoxinle
 * </pre>
 */
public class Main {

	public static void main(String[] args) throws Exception {
//		VelocityUtil.createTemplate();
		for (int i = 0; i < 1000; i++) {
			Thread.sleep(1);
			System.out.println(getOrderIdByUUId().length());
		}
	}

	public static String getOrderIdByUUId() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = format.format(date);
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {// 有可能是负数
			hashCodeV = -hashCodeV;
		}
		// 0 代表前面补充0
		// 4 代表长度为4
		// d 代表参数为正数型
		return time + String.format("%011d", hashCodeV);
	}
}
