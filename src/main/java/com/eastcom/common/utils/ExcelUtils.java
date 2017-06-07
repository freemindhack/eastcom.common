package com.eastcom.common.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ExcelUtils {
	/**
	 * 往Excel中插入图片
	 * 
	 * @param dataSheet
	 *            待插入的工作表
	 * @param col
	 *            图片从该列开始
	 * @param row
	 *            图片从该行开始
	 * @param width
	 *            图片所占的列数
	 * @param height
	 *            图片所占的行数
	 * @param imgFile
	 *            要插入的图片文件
	 */
	public static void insertImg(WritableSheet dataSheet, int col, int row, int width, int height, File imgFile) {
		WritableImage img = new WritableImage(col, row, width, height, imgFile);
		dataSheet.addImage(img);
	}

	/**
	 * 导出Excel表
	 * 
	 * @param workSheet
	 *            输出的excel文件工作表名
	 * @param fileName
	 *            excel文件名
	 * @param title
	 *            excel工作表的标题
	 * @param width
	 *            数据所占大小
	 * @param list
	 *            数据
	 */
	public static void export(OutputStream os, String[] workSheet, String[] title, List<String[]> list,
			String imagePath) {
		WritableWorkbook workbook = null;
		try {
			workbook = Workbook.createWorkbook(os);
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = workbook.createSheet(workSheet[0], 0); // 添加第一个工作表
			jxl.write.Label label;
			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			// 以及单元格内容为workSheet
			// sheet.addCell(new Label(0, 0, workSheet[0], wcfF));//
			// //将定义好的单元格添加到工作表中
			for (int i = 0; i < title.length; i++) {
				// Label(列号,行号 ,内容 )
				label = new jxl.write.Label(i, 0, title[i]); // put the title
				sheet.addCell(label);
				// sheet.setColumnView(i, width[i]);
			}
			for (int i = 1; i < list.size(); i++) {
				String[] ss = list.get(i);
				for (int j = 0; j < ss.length; j++) {
					sheet.addCell(new Label(j, i, ss[j]));
				}
			}
			// 待插入的工作表
			WritableSheet imgSheet = workbook.createSheet(workSheet[1], 1);

			File imgFile = new File(imagePath);
			insertImg(imgSheet, 0, 1, 15, 15, imgFile);
			// 写入数据并关闭文件
			workbook.write();
			workbook.close();
			try {
				if (os != null) {
					os.flush();
					os.close();
					os = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void export(File file, String sheetName, String[][] title, List dataList) {
		try {
			OutputStream os = new FileOutputStream(file);
			export(os, sheetName, title, dataList);
			os.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void export(OutputStream os, String sheetName, String[][] title, List dataList) {
		try {
			String thisSheet = (sheetName == null || sheetName.trim().length() == 0) ? "Sheet1" : sheetName;

			WorkbookSettings settings = new WorkbookSettings();
			settings.setEncoding("gb2312");

			WritableWorkbook workbook = Workbook.createWorkbook(os, settings);
			WritableSheet worksheet = workbook.createSheet(thisSheet, 0);

			Label label = null;// 用于写入文本内容到工作表中去

			// 开始写入第一行，即标题栏
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false,
					UnderlineStyle.NO_UNDERLINE);
			WritableCellFormat wcft = new WritableCellFormat(wf);
			wcft.setBackground(Colour.BLUE_GREY);
			wcft.setBorder(Border.ALL, BorderLineStyle.THIN);
			for (int i = 0; i < title[1].length; i++) {
				label = new Label(i, 0, title[1][i], wcft);
				worksheet.addCell(label);

				worksheet.setColumnView(i, 20); // 设置列的宽度
			}

			// 开始写入内容
			WritableFont wf2 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false,
					UnderlineStyle.NO_UNDERLINE);
			WritableCellFormat wcft2 = new WritableCellFormat(wf2);
			wcft2.setBorder(Border.ALL, BorderLineStyle.THIN);
			if (dataList != null && dataList.size() > 0) {
				for (int i = 0; i < dataList.size(); i++) {
					Map map = (Map) dataList.get(i);
					for (int j = 0; j < title[0].length; j++) {
						String value = map.get(title[0][j]) == null ? "" : map.get(title[0][j]).toString();
						label = new Label(j, i + 1, value, wcft2);
						worksheet.addCell(label);
					}
				}
			}

			workbook.write();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void export(OutputStream os, String[] sheetName, String[][] title, List dataList, byte[] pngByte) {
		try {
			String dataSheetName = (sheetName[0] == null || sheetName[0].trim().length() == 0) ? "Sheet1"
					: sheetName[0];
			String imgSheetName = (sheetName[1] == null || sheetName[1].trim().length() == 0) ? "Sheet2" : sheetName[1];

			WorkbookSettings settings = new WorkbookSettings();
			settings.setEncoding("gb2312");

			WritableWorkbook workbook = Workbook.createWorkbook(os, settings);
			WritableSheet dataSheet = workbook.createSheet(dataSheetName, 0);
			WritableSheet imgSheet = workbook.createSheet(imgSheetName, 1);

			Label label = null;// 用于写入文本内容到工作表中去

			// 开始写入第一行，即标题栏
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false,
					UnderlineStyle.NO_UNDERLINE);
			WritableCellFormat wcft = new WritableCellFormat(wf);
			wcft.setBackground(Colour.BLUE_GREY);
			wcft.setBorder(Border.ALL, BorderLineStyle.THIN);
			for (int i = 0; i < title[1].length; i++) {
				label = new Label(i, 0, title[1][i], wcft);
				dataSheet.addCell(label);

				dataSheet.setColumnView(i, 20); // 设置列的宽度
			}

			// 开始写入内容
			WritableFont wf2 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false,
					UnderlineStyle.NO_UNDERLINE);
			WritableCellFormat wcft2 = new WritableCellFormat(wf2);
			wcft2.setBorder(Border.ALL, BorderLineStyle.THIN);
			if (dataList != null && dataList.size() > 0) {
				for (int i = 0; i < dataList.size(); i++) {
					Map map = (Map) dataList.get(i);
					for (int j = 0; j < title[0].length; j++) {
						String value = map.get(title[0][j]) == null ? "" : map.get(title[0][j]).toString();
						label = new Label(j, i + 1, value, wcft2);
						dataSheet.addCell(label);
					}
				}
			}

			imgSheet.addImage(new WritableImage(1, 1, 20, 30, pngByte));

			workbook.write();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void export(OutputStream os, String[] sheetName, String[][] title, List dataList, byte[] pngByte0,
			byte[] pngByte1) {
		try {
			String dataSheetName = (sheetName[0] == null || sheetName[0].trim().length() == 0) ? "Sheet1"
					: sheetName[0];
			String imgSheet1_name = (sheetName[1] == null || sheetName[1].trim().length() == 0) ? "Sheet2"
					: sheetName[1];
			String imgSheet2_name = (sheetName[2] == null || sheetName[2].trim().length() == 0) ? "Sheet3"
					: sheetName[2];

			WorkbookSettings settings = new WorkbookSettings();
			settings.setEncoding("gb2312");

			WritableWorkbook workbook = Workbook.createWorkbook(os, settings);
			WritableSheet dataSheet = workbook.createSheet(dataSheetName, 0);
			WritableSheet imgSheet1 = workbook.createSheet(imgSheet1_name, 1);
			WritableSheet imgSheet2 = workbook.createSheet(imgSheet2_name, 1);

			Label label = null;// 用于写入文本内容到工作表中去

			// 开始写入第一行，即标题栏
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false,
					UnderlineStyle.NO_UNDERLINE);
			WritableCellFormat wcft = new WritableCellFormat(wf);
			wcft.setBackground(Colour.BLUE_GREY);
			wcft.setBorder(Border.ALL, BorderLineStyle.THIN);
			for (int i = 0; i < title[1].length; i++) {
				label = new Label(i, 0, title[1][i], wcft);
				dataSheet.addCell(label);

				dataSheet.setColumnView(i, 20); // 设置列的宽度
			}

			// 开始写入内容
			WritableFont wf2 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false,
					UnderlineStyle.NO_UNDERLINE);
			WritableCellFormat wcft2 = new WritableCellFormat(wf2);
			wcft2.setBorder(Border.ALL, BorderLineStyle.THIN);
			if (dataList != null && dataList.size() > 0) {
				for (int i = 0; i < dataList.size(); i++) {
					Map map = (Map) dataList.get(i);
					for (int j = 0; j < title[0].length; j++) {
						String value = map.get(title[0][j]) == null ? "" : map.get(title[0][j]).toString();
						label = new Label(j, i + 1, value, wcft2);
						dataSheet.addCell(label);
					}
				}
			}

			imgSheet1.addImage(new WritableImage(1, 1, 20, 30, pngByte0));
			imgSheet2.addImage(new WritableImage(1, 1, 20, 30, pngByte1));

			workbook.write();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void export(OutputStream os, String[] sheetName, String[][] title, List dataList,
			List<byte[]> pngByte) {
		try {

			WorkbookSettings settings = new WorkbookSettings();
			settings.setEncoding("gb2312");

			WritableWorkbook workbook = Workbook.createWorkbook(os, settings);
			WritableSheet dataSheet = workbook.createSheet(sheetName[0], 0);
			List<WritableSheet> imgSheets = new ArrayList<WritableSheet>();
			for (int i = 1; i < sheetName.length; i++) {
				imgSheets.add(workbook.createSheet(sheetName[i], i));
			}

			Label label = null;// 用于写入文本内容到工作表中去

			// 开始写入第一行，即标题栏
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false,
					UnderlineStyle.NO_UNDERLINE);
			WritableCellFormat wcft = new WritableCellFormat(wf);
			wcft.setBackground(Colour.BLUE_GREY);
			wcft.setBorder(Border.ALL, BorderLineStyle.THIN);
			for (int i = 0; i < title[1].length; i++) {
				label = new Label(i, 0, title[1][i], wcft);
				dataSheet.addCell(label);

				dataSheet.setColumnView(i, 20); // 设置列的宽度
			}

			// 开始写入内容
			WritableFont wf2 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false,
					UnderlineStyle.NO_UNDERLINE);
			WritableCellFormat wcft2 = new WritableCellFormat(wf2);
			wcft2.setBorder(Border.ALL, BorderLineStyle.THIN);
			if (dataList != null && dataList.size() > 0) {
				for (int i = 0; i < dataList.size(); i++) {
					Map map = (Map) dataList.get(i);
					for (int j = 0; j < title[0].length; j++) {
						String value = map.get(title[0][j]) == null ? "" : map.get(title[0][j]).toString();
						label = new Label(j, i + 1, value, wcft2);
						dataSheet.addCell(label);
					}
				}
			}

			for (int i = 0; i < imgSheets.size(); i++)
				imgSheets.get(i).addImage(new WritableImage(1, 1, 20, 34, pngByte.get(i)));

			workbook.write();
			workbook.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void export(File file, String[] sheetNames, Map<String, String[][]> titles,
			Map<String, List<Map<String, Object>>> dataMap) {
		try {
			OutputStream os = new FileOutputStream(file);
			export(os, sheetNames, titles, dataMap);
			os.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void export(OutputStream os, String[] sheetNames, Map<String, String[][]> titles,
			Map<String, List<Map<String, Object>>> dataMap) {
		try {

			WorkbookSettings settings = new WorkbookSettings();
			settings.setEncoding("gb2312");

			WritableWorkbook workbook = Workbook.createWorkbook(os, settings);
			// 遍历Sheets
			for (String sheetName : sheetNames) {
				String thisSheet = (sheetName == null || sheetName.trim().length() == 0) ? "Sheet1" : sheetName;
				WritableSheet worksheet = workbook.createSheet(thisSheet, 0);

				Label label = null;// 用于写入文本内容到工作表中去

				// 开始写入第一行，即标题栏
				String[][] title = titles.get(sheetName);
				for (int i = 0; i < title[1].length; i++) {
					label = new Label(i, 0, title[1][i]);
					worksheet.addCell(label);
				}

				// 取出当前sheet的dataList
				List dataList = dataMap.get(sheetName);
				// 开始写入内容
				if (dataList != null && dataList.size() > 0) {
					for (int i = 0; i < dataList.size(); i++) {
						Map map = (Map) dataList.get(i);
						for (int j = 0; j < title[0].length; j++) {
							String value = map.get(title[0][j]) == null ? "" : map.get(title[0][j]).toString();
							label = new Label(j, i + 1, value);
							worksheet.addCell(label);
						}
					}
				}
			}

			workbook.write();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<Map> readExcel(InputStream is, String[] columnsArr) throws Exception {
		return readExcel(is, null, columnsArr, 1, -1);
	}

	public static List<Map> readExcel(InputStream is, String[] columnsArr, int startRow) throws Exception {
		return readExcel(is, null, columnsArr, startRow, -1);
	}

	public static List<Map> readExcel(InputStream is, String sheetName, String[] columnsArr) throws Exception {
		return readExcel(is, sheetName, columnsArr, 1, -1);
	}

	public static List<Map> readExcel(InputStream is, String sheetName, String[] columnsArr, int startRow)
			throws Exception {
		return readExcel(is, sheetName, columnsArr, startRow, -1);
	}

	public static List<Map> readExcel(InputStream is, String sheetName, String[] columnsArr, int startRow, int endRow)
			throws Exception {
		List<Map> rtList = new ArrayList<Map>();
		Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(is);
			Sheet sheet = null;
			if (sheetName == null || sheetName.trim().length() == 0) {
				sheet = workbook.getSheet(0);
			} else {
				sheet = workbook.getSheet(sheetName);
			}

			if (sheet.getColumns() < columnsArr.length) {
				throw new Exception("Invalid column number.");
			}

			if (endRow == -1) {
				endRow = sheet.getRows();
			}
			for (int i = startRow - 1; i < endRow; i++) {
				Map rtMap = new HashMap();
				for (int j = 0; j < columnsArr.length; j++) {
					Cell cell = sheet.getCell(j, i);
					if (sheet.getCell(j, i).getContents() != null
							|| !sheet.getCell(j, i).getContents().trim().equals("")) {
						if (cell instanceof DateCell) {
							rtMap.put(columnsArr[j], ((DateCell) sheet.getCell(j, i)).getDate());
						} else {
							rtMap.put(columnsArr[j], sheet.getCell(j, i).getContents().trim());
						}
					}
				}
				if (!rtMap.isEmpty()) {
					rtList.add(rtMap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			try {
				workbook.close();
			} catch (Exception ex) {
			}
		}
		return rtList;
	}
}