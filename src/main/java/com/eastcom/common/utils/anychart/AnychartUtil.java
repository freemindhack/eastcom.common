package com.eastcom.common.utils.anychart;

import java.io.InputStream;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Anychart操作工具类，主要是对常用属性提供方法进行设置，如标题、是否启用滚动条、添加Series等<br>
 */
public class AnychartUtil {
	private static Logger logger = LoggerFactory.getLogger(AnychartUtil.class);

	private static final String docPath = "/anychart/charts/chart/";

	private String filePath = "template/";

	// 模板路径
	public String COLUMN_SPLINE_XML = filePath + "columnSpline.xml";
	public String COLUMN_XML = filePath + "column.xml";
	public String SPLINE_XML = filePath + "spline.xml";
	public String DOUGHNUT_XML = filePath + "doughnut.xml";

	// 鼠标移到图上显示的信息格式化
	public static final String FORMAT = "{%SeriesName}\n{%Name}\n{%Value}";// 原值呈现
	public static final String FORMAT_DEC_0 = "{%SeriesName}\n{%Name}\n{%Value}{numDecimals:0}";// 无小数
	public static final String FORMAT_DEC_2 = "{%SeriesName}\n{%Name}\n{%Value}{numDecimals:2}";// 2位小数
	public static final String FORMAT_DEC_3 = "{%SeriesName}\n{%Name}\n{%Value}{numDecimals:3}";// 3位小数
	public static final String FORMAT_DEC_4 = "{%SeriesName}\n{%Name}\n{%Value}{numDecimals:4}";// 4位小数

	// X/Y轴Label模式
	public static final String MODEL_STAGER = "Stager";// 上下间隔模式
	public static final String MODEL_ROTATED = "Rotated";// 倾斜模式
	
	
	public AnychartUtil() {
		super();
	}

	public AnychartUtil(String filePath) {
		super();
		this.filePath = filePath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * 读取模板文件
	 * 
	 * @param path
	 * @return
	 */
	public static Document getChartDoc(String path) {
		InputStream in = null;
		try {
			SAXReader reader = new SAXReader();
			in = AnychartUtil.class.getResourceAsStream(path);
			return reader.read(in);
		} catch (DocumentException e) {
			logger.error("读取AnyChart模板文件错误:" + e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * 设置结点属性启用或禁用
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param nodePath
	 *            结点路径
	 * @param enabled
	 *            true:启用,false:禁用
	 */
	private static void setEnabled(Document doc, String nodePath, boolean enabled) {
		Element node = (Element) doc.selectSingleNode(nodePath);
		if (node == null)
			node = doc.addElement(nodePath);
		Attribute attr = node.attribute("enabled");
		if (attr == null)
			node.addAttribute("enabled", enabled ? "true" : "false");
		else
			attr.setText(enabled ? "true" : "false");
	}

	/**
	 * 获取结点，如果没有则新增结点
	 * 
	 * @param doc
	 * @param name
	 *            节点名称
	 * @param nodePath
	 *            节点URI
	 * @return
	 */
	private static Element getNode(Document doc, String name, String nodePath) {
		Element node = (Element) doc.selectSingleNode(nodePath);
		if (node == null)
			node = doc.addElement(name, nodePath);

		return node;
	}

	/**
	 * 设置属性值，如果没有则新增属性
	 * 
	 * @param node
	 *            结点Element对象
	 * @param name
	 *            属性名称
	 * @param value
	 *            属性值
	 */
	private static void setAttribute(Element node, String name, Object value) {
		Attribute attr = node.attribute(name);
		String text = "";
		if (value instanceof Boolean) {
			text = (Boolean) value ? "true" : "false";
		} else {
			text = value + "";
		}
		if (attr == null)
			node.addAttribute(name, text);
		else
			attr.setText(text);
	}

	/**
	 * 设置标题（默认无标题）
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param title
	 *            图表标题
	 * @param nodePath
	 *            结点路径
	 * @return
	 */
	private static void setTitle(Document doc, String title, String nodePath) {
		// 启用标题
		setEnabled(doc, nodePath, true);

		Element node = getNode(doc, "title", nodePath);
		Element text = node.element("text");
		if (text == null)
			text = node.addElement("text");
		text.setText(title);
	}

	/**
	 * 设置图表标题（默认无标题）
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param title
	 *            图表标题
	 * @return
	 */
	public static void setTitle(Document doc, String title) {
		setTitle(doc, title, docPath + "chart_settings/title");
	}

	/**
	 * 设置X轴标题（默认无标题）
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param title
	 *            X标题
	 * @return
	 */
	public static void setXAxisTitle(Document doc, String title) {
		setTitle(doc, title, docPath + "chart_settings/axes/x_axis/title");
	}

	/**
	 * 设置Y轴标题（默认无标题）
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param title
	 *            X标题
	 * @return
	 */
	public static void setYAxisTitle(Document doc, String title) {
		setTitle(doc, title, docPath + "chart_settings/axes/y_axis/title");
	}

	/**
	 * 设置Y轴标题（默认无标题）
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param title
	 *            Y标题
	 * @param isExtra
	 *            true:设置右侧Y轴,false:设置左边Y轴,默认为false
	 * @return
	 */
	public static void setYAxisTitle(Document doc, String title, boolean isExtra) {
		if (isExtra)
			setTitle(doc, title, docPath + "chart_settings/axes/y_axis/title");
		else
			setTitle(doc, title, docPath + "chart_settings/axes/extra/y_axis/title");
	}

	/**
	 * 设置X轴Label显示模式为倾斜模式
	 * 
	 * @param doc
	 *            图表Document对象
	 * @return
	 */
	public static void setXAxisLablesRotat(Document doc, int value) {
		if (value == 0)
			return;
		Element node = getNode(doc, "labels", docPath + "chart_settings/axes/x_axis");
		Element labels = node.element("labels");
		if (labels == null)
			labels = node.addElement("labels");
		// 设置rotation属性值
		setAttribute(labels, "rotation", value);
		// 模式设置为Rotated
		setAttribute(labels, "display_mode", "Rotated");
	}

	/**
	 * 设置X轴Label显示模式为换行模式
	 * 
	 * @param doc
	 *            图表Document对象
	 * @return
	 */
	public static void setXAxisLables(Document doc, Integer width) {
		Element node = (Element) doc.selectSingleNode(docPath + "chart_settings/axes/x_axis");
		Element labels = node.element("labels");
		if (labels == null)
			labels = node.addElement("labels");
		setAttribute(labels, "width", width);
	}

	/**
	 * 设置X轴Label显示模式为上下间隔模式
	 * 
	 * @param doc
	 *            图表Document对象
	 * @return
	 */
	public static void setXAxisLablesStager(Document doc) {
		setXAxisLablesStager(doc, null);
	}

	/**
	 * 设置X轴Label显示模式为上下间隔模式
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param width
	 *            X轴Label宽度
	 * @return
	 */
	public static void setXAxisLablesStager(Document doc, Integer width) {
		Element node = getNode(doc, "labels", docPath + "chart_settings/axes/x_axis/labels");
		if (width != null) {
			setAttribute(node, "width", width);
		}
		setAttribute(node, "display_mode", "Stager");
	}

	/**
	 * 设置Y轴值小数点位数（默认无小数点）
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param decimals
	 *            小数点
	 * @return
	 */
	public static void setYAxisDecimal(Document doc, int decimals) {
		setYAxisDecimal(doc, decimals, null);
	}

	/**
	 * 设置Y轴值小数点位数（默认无小数点、无符号）
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param decimals
	 *            小数点
	 * @param mark
	 *            符号
	 * @return
	 */
	public static void setYAxisDecimal(Document doc, int decimals, String mark) {
		setYAxisDecimal(doc, decimals, mark, false);
	}

	/**
	 * 设置Y轴小数点位数和单位符号（默认无小数点、无符号）
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param decimals
	 *            小数点
	 * @param mark
	 *            单位符号
	 * @param isExtra
	 *            true:设置右侧Y轴,false:设置左边Y轴,默认为false
	 * @return
	 */
	public static void setYAxisDecimal(Document doc, int decimals, String mark, boolean isExtra) {
		String path = docPath + "chart_settings/axes/y_axis/labels/format";
		if (isExtra)
			path = docPath + "chart_settings/axes/extra/y_axis/labels/format";
		Element node = getNode(doc, "format", path);
		mark = mark == null ? "" : mark;
		node.setText("{%Value}{numDecimals:" + decimals + "}" + mark);
	}

	/**
	 * 是否启用图例(默认不显示)
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param enabled
	 *            ture:显示，false:不显示
	 * @return
	 */
	public static void setLegend(Document doc, boolean enabled) {
		setLegend(doc, enabled, null);
	}

	/**
	 * 是否启用图例，并设置显示位置(默认不启用)
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param enabled
	 *            ture:显示，false:不显示
	 * @param position
	 *            图例位置:Left/Right/Top/Buttom
	 * @return
	 */
	public static void setLegend(Document doc, boolean enabled, String position) {
		String path = docPath + "chart_settings/legend";
		// 启用图例
		setEnabled(doc, path, enabled);

		Element node = getNode(doc, "legend", path);
		position = position == null ? "Bottom" : position;
		setAttribute(node, "position", position);
	}

	/**
	 * 数据值是否显示（默认不显示）
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param path
	 *            结点路径
	 * @param enabled
	 *            ture:显示，false:不显示
	 * @param decimals
	 *            小数点
	 * @param mark
	 *            符号
	 */
	private static void setLabel(Document doc, String path, boolean enabled, int decimals,
			String mark) {
		// 启用提示信息
		setEnabled(doc, path, enabled);

		Element node = getNode(doc, "format", path + "/format");
		if (mark == null)
			node.setText("{%Value}{numDecimals:" + decimals + "}");
		else
			node.setText("{%Value}{numDecimals:" + decimals + "}" + mark);
	}

	/**
	 * 曲线数据值是否显示(默认不显示)
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param enabled
	 *            ture:显示，false:不显示
	 */
	public static void setLineLabel(Document doc, boolean enabled) {
		setLineLabel(doc, enabled, 0, null);
	}

	/**
	 * 曲线数据值是否显示(默认不显示)
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param enabled
	 *            ture:显示，false:不显示
	 * @param mark
	 *            符号
	 */
	public static void setLineLabel(Document doc, boolean enabled, String mark) {
		setLineLabel(doc, enabled, 0, mark);
	}

	/**
	 * 曲线数据值是否显示(默认不显示)
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param enabled
	 *            ture:显示，false:不显示
	 */
	public static void setLineLabel(Document doc, boolean enabled, int decimals) {
		setLineLabel(doc, enabled, decimals, null);
	}

	/**
	 * 曲线数据值是否显示(默认不显示)
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param enabled
	 *            ture:显示，false:不显示
	 * @param decimals
	 *            小数点
	 * @param mark
	 *            符号
	 */
	public static void setLineLabel(Document doc, boolean enabled, int decimals, String mark) {
		setLabel(doc, docPath + "data_plot_settings/line_series/label_settings", enabled, decimals,
				mark);
	}

	/**
	 * 柱子数据值是否显示(默认不显示)
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param enabled
	 *            ture:显示，false:不显示
	 */
	public static void setBarLabel(Document doc, boolean enabled) {
		setBarLabel(doc, enabled, 0, null);
	}

	/**
	 * 柱子数据值是否显示(默认不显示)
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param enabled
	 *            ture:显示，false:不显示
	 * @param mark
	 *            符号
	 */
	public static void setBarLabel(Document doc, boolean enabled, String mark) {
		setBarLabel(doc, enabled, 0, mark);
	}

	/**
	 * 柱子数据值是否显示(默认不显示)
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param enabled
	 *            ture:显示，false:不显示
	 * @param decimals
	 *            小数点
	 */
	public static void setBarLabel(Document doc, boolean enabled, int decimals) {
		setBarLabel(doc, enabled, decimals, null);
	}

	/**
	 * 柱子数据值是否显示(默认不显示)
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param enabled
	 *            ture:显示，false:不显示
	 * @param decimals
	 *            小数点
	 * @param mark
	 *            符号
	 */
	public static void setBarLabel(Document doc, boolean enabled, int decimals, String mark) {
		setLabel(doc, docPath + "data_plot_settings/bar_series/label_settings", enabled, decimals,
				mark);
	}

	/**
	 * 鼠标停留在图上时是否显示提示信息（默认不启用）
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param path
	 *            结点路径
	 * @param enabled
	 *            ture:显示，false:不显示
	 * @param format
	 *            提示信息格式
	 * @return
	 */
	private static void setTooltip(Document doc, String path, boolean enabled, String format,
			String mark, String info) {
		// 启用提示信息
		setEnabled(doc, path, enabled);
		if (format == null || format.trim().equals(""))
			format = FORMAT;

		Element node = getNode(doc, "format", path + "/format");
		mark = mark == null ? "" : mark;
		info = info == null ? "" : "\n" + "{%" + info + "}";
		node.clearContent();
		node.addCDATA(format + mark + info);
	}

	/**
	 * 鼠标停留在曲线上时是否显示提示信息（默认不启用）
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param enabled
	 *            ture:显示，false:不显示
	 * @return
	 */
	public static void setLineTooltip(Document doc, boolean enabled) {
		setLineTooltip(doc, enabled, null);
	}

	/**
	 * 鼠标停留在曲线上时是否显示提示信息（默认不启用）
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param enabled
	 *            ture:显示，false:不显示
	 * @param format
	 *            提示信息格式
	 * @return
	 */
	public static void setLineTooltip(Document doc, boolean enabled, String format) {
		setLineTooltip(doc, enabled, format, null);
	}

	/**
	 * 鼠标停留在曲线上时是否显示提示信息（默认不启用）
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param enabled
	 *            ture:显示，false:不显示
	 * @param format
	 *            提示信息格式
	 * @param mark
	 *            符号
	 * @return
	 */
	public static void setLineTooltip(Document doc, boolean enabled, String format, String mark) {
		setTooltip(doc, docPath + "data_plot_settings/line_series/tooltip_settings", enabled,
				format, mark, null);
	}

	/**
	 * 鼠标停留在曲线上时是否显示提示信息（默认不启用）
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param enabled
	 *            ture:显示，false:不显示
	 * @param format
	 *            提示信息格式
	 * @param mark
	 *            符号
	 * @param info
	 *            额外信息
	 * @return
	 */
	public static void setLineTooltip(Document doc, boolean enabled, String format, String mark,
			String info) {
		setTooltip(doc, docPath + "data_plot_settings/line_series/tooltip_settings", enabled,
				format, mark, info);
	}

	/**
	 * 鼠标停留在柱子上时是否显示提示信息（默认不启用）
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param enabled
	 *            ture:显示，false:不显示
	 * @return
	 */
	public static void setBarTooltip(Document doc, boolean enabled) {
		setBarTooltip(doc, enabled, null);
	}

	/**
	 * 鼠标停留在柱子上时是否显示提示信息（默认不启用）
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param enabled
	 *            ture:显示，false:不显示
	 * @param format
	 *            提示信息格式
	 * @return
	 */
	public static void setBarTooltip(Document doc, boolean enabled, String format) {
		setBarTooltip(doc, enabled, format, null);
	}

	/**
	 * 鼠标停留在柱子上时是否显示提示信息（默认不显示）
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param enabled
	 *            ture:显示，false:不显示
	 * @param format
	 *            提示信息格式
	 * @param mark
	 *            符号
	 * @return
	 */
	public static void setBarTooltip(Document doc, boolean enabled, String format, String mark) {
		setTooltip(doc, docPath + "data_plot_settings/bar_series/tooltip_settings", enabled,
				format, mark, null);
	}

	/**
	 * 鼠标停留在柱子上时是否显示提示信息（默认不显示）
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param enabled
	 *            ture:显示，false:不显示
	 * @param format
	 *            提示信息格式
	 * @param mark
	 *            符号
	 * @param info
	 *            额外信息
	 * @return
	 */
	public static void setBarTooltip(Document doc, boolean enabled, String format, String mark,
			String info) {
		setTooltip(doc, docPath + "data_plot_settings/bar_series/tooltip_settings", enabled,
				format, mark, info);
	}

	/**
	 * 鼠标停留在饼图上时是否显示提示信息（默认不显示）
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param enabled
	 *            ture:显示，false:不显示
	 * @param format
	 *            提示信息格式
	 * @param mark
	 *            符号
	 * @param info
	 *            额外信息
	 * @return
	 */
	public static void setPieTooltip(Document doc, boolean enabled, String format, String mark,
			String info) {
		setTooltip(doc, docPath + "data_plot_settings/pie_series/tooltip_settings", enabled,
				format, mark, info);
	}

	/**
	 * 是否启用X轴滚动条（默认不启用）
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param enabled
	 *            ture:启用，false:不启用
	 * @return
	 */
	public static void setXZoom(Document doc, boolean enabled) {
		setXZoom(doc, enabled, 7);
	}

	/**
	 * 是否启用X轴滚动条（默认不启用）
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param enabled
	 *            ture:启用，false:不启用
	 * @param range
	 *            第几个值开始滚动
	 * @return
	 */
	public static void setXZoom(Document doc, boolean enabled, int range) {
		String path = docPath + "chart_settings/axes/x_axis/zoom";
		// 启用图例
		setEnabled(doc, path, enabled);

		Element node = getNode(doc, "zoom", path);
		setAttribute(node, "visible_range", range);
	}

	/**
	 * 创建series元素
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param name
	 *            名称
	 * @return
	 */
	public static Element createSeries(Document doc, String name) {
		return createSeries(doc, null, name, null, null);
	}

	/**
	 * 创建series元素
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param name
	 *            名称
	 * @return
	 */
	public static Element createSeriesById(Document doc, String id, String name) {
		return createSeries(doc, id, name, null, null);
	}

	/**
	 * 创建series元素
	 * 
	 * @param doc
	 * @param name
	 * @param type
	 * @return
	 */
	public static Element createSeries(Document doc, String name, String type) {
		return createSeries(doc, null, name, type, null);
	}

	/**
	 * 创建series元素
	 * 
	 * @param doc
	 * @param id
	 * @param name
	 * @param type
	 * @return
	 */
	public static Element createSeriesById(Document doc, String id, String name, String type) {
		return createSeries(doc, id, name, type, null);
	}

	/**
	 * 创建series元素
	 * 
	 * @param doc
	 * @param name
	 * @param type
	 * @param yAxis
	 * @return
	 */
	public static Element createSeries(Document doc, String name, String type, String yAxis) {
		return createSeries(doc, null, name, type, yAxis);
	}

	/**
	 * 创建series元素
	 * 
	 * @param doc
	 * @param name
	 * @return
	 */
	public static Element createSeries(Document doc, String id, String name, String type,
			String yAxis) {
		Element data = getNode(doc, "data", docPath + "data");
		Element series = data.addElement("series");
		setAttribute(series, "name", name);
		if (id != null && !id.trim().equals(""))
			setAttribute(series, "id", id);
		if (type != null && !type.trim().equals(""))
			setAttribute(series, "type", type);
		if (yAxis != null && !yAxis.trim().equals(""))
			setAttribute(series, "y_axis", yAxis);
		return series;
	}

	/**
	 * 添加Point
	 * 
	 * @param series
	 *            series元素Element
	 * @param name
	 *            名称
	 * @param value
	 *            值
	 */
	public static void addPoint(Element series, String name, Object value) {
		Element point = series.addElement("point");
		setAttribute(point, "name", name);
		setAttribute(point, "y", value);
	}

	/**
	 * 添加attributes、attribute属性
	 * 
	 * @param series
	 *            series元素Element
	 * @param name
	 *            名称
	 * @param info
	 *            额外信息
	 * @param attributeName
	 *            attribute属性中name对应的值 <attribute name=attributeName>
	 * @param value
	 *            值
	 */
	public static void addPoint(Element series, String name, Object value, String info,
			String attributeName) {
		Element point = series.addElement("point");
		Element attributes = point.addElement("attributes");
		Element attribute = attributes.addElement("attribute");
		setAttribute(point, "name", name);
		setAttribute(point, "y", value);
		setAttribute(attribute, "name", attributeName);
		attribute.addCDATA(info);
	}

	/**
	 * 设置Series图表数据
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param series
	 * @return
	 */
	public static void removeSeries(Document doc, Element series) {
		Element data = getNode(doc, "data", docPath + "data");
		data.remove(series);
	}

	/**
	 * 设置单位及单位位置（默认显示在y轴上方） 设置Series图表数据
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param unit
	 *            要显示的单位
	 * @param position
	 *            label属性的中position的值 position : "top" 、"bottom"、"left"、"right"
	 * @param align
	 *            label属性的中align的值 align : "Near","Far"
	 * @return
	 */
	public static void setUnit(Document doc, String unit, String position, String align) {
		String path = docPath + "chart_settings/controls";
		if ((position == null || position.equals("")) && (align.equals("") || align == null)
				|| position.equalsIgnoreCase("Top") && align.equalsIgnoreCase("Near")) {
			path = docPath + "chart_settings/controls/label";
			Element data = getNode(doc, "label", path);
			Element bold = data.addElement("font");
			setAttribute(bold, "bold", false);// 直接设为不加粗了。
			data = data.addElement("text");
			data.addCDATA(unit);
		} else {
			Element data = getNode(doc, "controls", path);
			Element label = data.addElement("label");
			setAttribute(label, "position", position);
			setAttribute(label, "align", align);
			if (position.equalsIgnoreCase("Top") && align.equalsIgnoreCase("Far")) {
				Element margin = label.addElement("margin");
				setAttribute(margin, "right", -40);
				setAttribute(margin, "bottom", -3);
			}
			Element bold = label.addElement("font");
			setAttribute(bold, "bold", false);// 直接设为不加粗了。
			label = label.addElement("text");
			label.addCDATA(unit);
		}
	}

	/**
	 * 饼图数据显示在外面（字体默认为黑色、取消粗体）
	 * 
	 * @param doc
	 *            图表Document对象
	 */
	public static void setPieLineLabel(Document doc, boolean rightOrNot) {
		String path = docPath + "data_plot_settings/pie_series/label_settings";
		Element node = getNode(doc, "label_settings", path);
		setAttribute(node, "mode", "Outside");
		setAttribute(node, "multi_line_align", "Center");

		// 设置为黑色字体 、取消粗体
		Element font = getNode(doc, "font", path + "/font");
		setAttribute(font, "color", "Black");
		setAttribute(font, "bold", false);

		// legend在Name右边显示、提示信息加上name属性
		Element format = getNode(doc, "format", path + "/format");
		format.clearContent();
		format.addText("{%Name}:{%YPercentOfSeries}{numDecimals:2}%");
		String path2 = docPath + "chart_settings/legend";
		Element legend = getNode(doc, "legend", path2);
		if (rightOrNot)
			setAttribute(legend, "position", "right");
	}

	/**
	 * 饼图数据显示在外面（字体默认为黑色、取消粗体）+添加额外提示信息
	 * 
	 * @param doc
	 *            图表Document对象
	 */
	public static void setPieLineLabel2(Document doc, String info) {
		String path = docPath + "data_plot_settings/pie_series/label_settings";
		Element node = getNode(doc, "label_settings", path);
		setAttribute(node, "mode", "Outside");
		setAttribute(node, "multi_line_align", "Center");

		// 设置为黑色字体 、取消粗体
		Element font = getNode(doc, "font", path + "/font");
		setAttribute(font, "color", "Black");
		setAttribute(font, "bold", false);

		// legend在Name右边显示、提示信息加上name属性
		Element format = getNode(doc, "format", path + "/format");
		format.clearContent();
		format.addText("{%" + info + "}\n{%Name}:{%YPercentOfSeries}{numDecimals:0}%");
		String path2 = docPath + "chart_settings/legend";
		Element legend = getNode(doc, "legend", path2);
		setAttribute(legend, "position", "right");
	}

	/**
	 * 设置Yscale mode为"Stacked"模式,默认常规模式。
	 * 
	 * @param doc
	 *            图表Document对象
	 * @param isExtra
	 *            true:设置右侧Y轴,false:设置左边Y轴
	 * @return
	 */
	public static void setYscaleMode(Document doc, boolean isExtra, String mode) {
		String path = docPath + "chart_settings/axes/y_axis";
		if (isExtra)
			path = docPath + "chart_settings/axes/extra/y_axis";
		Element node = getNode(doc, "y_axis", path);
		Element scale = node.addElement("scale");
		setAttribute(scale, "mode", mode);
	}

	/**
	 * 设置enable_3d_mode为true,默认false。
	 * 
	 * @param doc
	 *            图表Document对象
	 * @return
	 */
	public static void setTdmode(Document doc, boolean trOfFs) {
		String path = docPath + "data_plot_settings";
		Element node = getNode(doc, "data_plot_settings", path);
		setAttribute(node, "enable_3d_mode", trOfFs);
	}

	/**
	 * 设置柱状图上数值显示位置，默认为顶部。
	 * 
	 * @param doc
	 *            图表Document对象
	 * @return
	 */
	public static void setBarLabelPosition(Document doc, String valignPosition,
			String anchorPosition, String halignPosition) {
		String path = docPath + "data_plot_settings/bar_series/label_settings";
		Element node = getNode(doc, "label_settings", path);
		Element positionEl = node.addElement("position");
		setAttribute(positionEl, "valign", valignPosition);
		setAttribute(positionEl, "anchor", anchorPosition);
		setAttribute(positionEl, "halign", halignPosition);
	}

	// <position anchor="" valign="" halign="" />

	public static void main(String[] args) {
		AnychartUtil a = new AnychartUtil();
		System.out.println(a.getFilePath()+a.COLUMN_SPLINE_XML);
	}
}
