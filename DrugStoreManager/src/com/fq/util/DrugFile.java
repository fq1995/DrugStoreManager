package com.fq.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DrugFile {
	
	public static void print(String path,String json){
//		String path="C:\\Users\\fu\\git\\DrugStoreManager\\DrugStoreManager\\WebRoot";
		File file = new File(path, "drug.json");
		if(!file.exists()){
			file.mkdirs();
		}
		/*try {
			file.createNewFile(); // 创建文件
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		// 向文件写入内容(输出流)
//		String json = drugSaleServiceImpl.stats();
		byte bt[] = new byte[1024];
		bt = json.getBytes();
		try {
			FileOutputStream in = new FileOutputStream(file);
			try {
				in.write(bt, 0, bt.length);
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
