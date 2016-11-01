package poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

public class TestPOIDemo {
	@Test
	public void HSSF() throws IOException{
		//1.创建一个工作簿excel文件
		Workbook wb = new HSSFWorkbook();
		//2.创建一个工作表sheet
		Sheet sheet = wb.createSheet();
		//3.创建行对象Row
		Row nRow = sheet.createRow(4);   //第5行 坐标从0 开始
		//4.创建单元格，指定列
		Cell nCell = nRow.createCell(3);   //第4列
		//5.单元格设置内容
		nCell.setCellValue("一生有你");
		//6.保存
		OutputStream os = new FileOutputStream(new File("c:\\fuqiang.xls"));
		wb.write(os);
		//7.关闭
		os.close();
	}
	//带样式
	@Test
	public void HSSFStyle() throws IOException{
		//1.创建一个工作簿excel文件
		Workbook wb = new HSSFWorkbook();
		//2.创建一个工作表sheet
		Sheet sheet = wb.createSheet();
		//3.创建行对象Row
		Row nRow = sheet.createRow(4);   //第5行 坐标从0 开始
		//4.创建单元格，指定列
		Cell nCell = nRow.createCell(3);   //第4列
		//5.单元格设置内容
		nCell.setCellValue("一生有你");
		//创建单元格样式
		CellStyle titleStyle = wb.createCellStyle();
		//创建字体对象
		Font titleFont = wb.createFont();
		titleFont.setFontName("华文行楷");           //设置字体
		titleFont.setFontHeightInPoints((short)20);   //字体大小
		
		titleStyle.setFont(titleFont);
		nCell.setCellStyle(titleStyle);
		//6.保存
		OutputStream os = new FileOutputStream(new File("c:\\fuqiang.xls"));
		wb.write(os);
		//7.关闭
		os.close();
	}
}
