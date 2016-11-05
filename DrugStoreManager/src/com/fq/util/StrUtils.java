package com.fq.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class StrUtils {
	public static String getNewFileName(){
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		StringBuilder sb = new StringBuilder();
		sb.append(sdf.format(d));
		sb.append(new Random().nextInt(10));
		sb.append(new Random().nextInt(10));
		sb.append(new Random().nextInt(10));
		sb.append(new Random().nextInt(10));
		return sb.toString();
		
	}
}
