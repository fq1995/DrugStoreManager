package com.fq.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class DrugFile {
	
	public static void print(String path,String json){
		/*File file = new File(path, "drug.json");
		if(!file.exists()){
			file.mkdirs();
		}
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
		}*/
		OutputStreamWriter pw = null;//定义一个流
		try {
			pw = new OutputStreamWriter(new FileOutputStream(path+"/drug.json"),"UTF-8");
			pw.write(json);//将要写入文件的内容
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				pw.close();//关闭流
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
