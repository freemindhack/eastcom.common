package com.eastcom.common.bean.anychart;

public class AnyChartSeries {

	private StringBuffer points = new StringBuffer();
	private String name;
	private String type;
	private String y_axis;
	
	public AnyChartSeries(String name, String type, String yAxis) {
		this.name = name;
		this.type = type;
		y_axis = yAxis;
	}

	public AnyChartSeries(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public AnyChartSeries(String name) {
		this.name = name;
	}



	public AnyChartSeries addPoint(String name,Object y){
		points.append("<point name=\""+name+"\" y=\""+y+"\"/>");
		return this;
	}
	
	@Override
	public String toString() {
		StringBuffer series = new StringBuffer("<series name=\""+name+"\" ");
		if(type != null){
			series.append("type=\""+type+"\" ");
		}
		if(y_axis != null){
			series.append("y_axis=\""+y_axis+"\" ");
		}
		series.append(">");
		series.append(points.toString());
		series.append("</series>");
		return series.toString();
	}
}
