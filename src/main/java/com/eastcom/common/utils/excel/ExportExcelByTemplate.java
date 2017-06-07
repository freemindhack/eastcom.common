package com.eastcom.common.utils.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * DESC: 读取模板导出Excel报表<BR>
 * AUTHOR: GuoMM ( Goma ) oma1989@yeah.net<BR>
 * VERSION: 1.0<BR>
 * DATE:2011-11-7 14:23:51<BR>
 */
public class ExportExcelByTemplate {
	private String fileUrl;

	/**
	 * @param fileUrl 模板路径<br>  
     * DESC:创建实例，并初始化模板<br>  
     */ 
	public ExportExcelByTemplate(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	/**
	 * 
	 * @param rows 模板内配置所占行数<br>
	 * @param cols 模板内配置所占列数<br>
	 * @param result 要导出报表的数据集<br>
	 * @return excel报表<br>
	 * 
	 */
	@SuppressWarnings("resource")
	public InputStream export(int rows, int cols, List<Map<String,Object>> result) {
		try {
			File file = new File(fileUrl);
			FileInputStream fint = new FileInputStream(file);
			HSSFWorkbook wb = new HSSFWorkbook(fint);
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow namesRow = sheet.getRow(rows-1);
			for (int i=0;i<result.size();i++) {
				HSSFRow row = sheet.getRow(rows + i);
				if (row == null) {
					row = sheet.createRow(rows + i);
				}
				for (int j = 0; j < cols; j++) {
					HSSFCell cell = row.getCell(j);
					if (cell == null) {
						cell = row.createCell(j);
					}
				
					// 获得方法名
					HSSFCell nameCell = namesRow.getCell(j);
					String name = nameCell.getRichStringCellValue().getString();
					String val = result.get(i).get(name)==null ? "0" : result.get(i).get(name).toString();
					cell.setCellValue(new HSSFRichTextString(val));
				}
			}

			sheet.shiftRows(rows, result.size() + rows, -1);
			System.out.println("WRITE EXCEL REPORT IS OK..");
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			wb.write(os);
			byte[] content = os.toByteArray();
			InputStream is = new ByteArrayInputStream(content);
			
			fint.close();
			
			return is;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
}
