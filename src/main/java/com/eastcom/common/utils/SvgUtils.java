package com.eastcom.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

/**
 * 
 * Title: eastcom-common <br>
 * Description: <br>
 * Copyright: eastcom Copyright (C) 2009 <br>
 * 
 * @author <a href="mailto:hujian@eastcom-sw.com">jaredhu</a><br>
 * @e-mail: hujian@eastcom-sw.com <br>
 * @version 1.0 <br>
 * @creatdate 2015年9月6日 下午6:02:42 <br>
 * 
 */
public class SvgUtils {

	/**
	 * 将svg字符串转成byte字节数组
	 * 
	 * @param svgCode
	 *            svg代码
	 * @return
	 * @throws IOException
	 * @throws TranscoderException
	 */
	public static byte[] convertToPng(String svgCode) throws IOException, TranscoderException {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			convertToPng(svgCode, outputStream);
			return outputStream.toByteArray();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 将svg字符串转换为png
	 * 
	 * @param svgCode
	 * @param pngFilePath
	 * @throws IOException
	 * @throws TranscoderException
	 */
	public static void convertToPng(String svgCode, String pngFilePath) throws IOException, TranscoderException {

		File file = new File(pngFilePath);

		FileOutputStream outputStream = null;
		try {
			file.createNewFile();
			outputStream = new FileOutputStream(file);
			convertToPng(svgCode, outputStream);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @Description: 将svgCode转换成png文件，直接输出到流中
	 * @param svgCode
	 *            svg代码
	 * @param outputStream
	 *            输出流
	 * @throws TranscoderException
	 *             异常
	 * @throws IOException
	 *             io异常
	 */
	public static void convertToPng(String svgCode, OutputStream outputStream) throws TranscoderException, IOException {
		try {
			byte[] bytes = svgCode.getBytes("UTF-8");
			PNGTranscoder t = new PNGTranscoder();
			TranscoderInput input = new TranscoderInput(new ByteArrayInputStream(bytes));
			TranscoderOutput output = new TranscoderOutput(outputStream);
			t.transcode(input, output);
			outputStream.flush();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException, TranscoderException{
	//	String svgcode = "<svg xmlns:xlink=\"http://www.w3.org/1999/xlink\" version=\"1.1\" style=\"font-family:'lucida grande', 'lucida sans unicode', arial, helvetica, sans-serif;font-size:12px;\" xmlns=\"http://www.w3.org/2000/svg\" width=\"600\" height=\"400\"><desc>Created with Highcharts 4.1.3</desc><defs><clipPath id=\"highcharts-8\"><rect x=\"0\" y=\"0\" width=\"514\" height=\"265\"></rect></clipPath></defs><rect x=\"0\" y=\"0\" width=\"600\" height=\"400\" strokeWidth=\"0\" fill=\"#FFFFFF\" class=\" highcharts-background\"></rect><g class=\"highcharts-grid\" ></g><g class=\"highcharts-grid\" ><path fill=\"none\" d=\"M 76 318.5 L 590 318.5\" stroke=\"#D8D8D8\" stroke-width=\"1\"  opacity=\"1\"></path><path fill=\"none\" d=\"M 76 265.5 L 590 265.5\" stroke=\"#D8D8D8\" stroke-width=\"1\"  opacity=\"1\"></path><path fill=\"none\" d=\"M 76 212.5 L 590 212.5\" stroke=\"#D8D8D8\" stroke-width=\"1\"  opacity=\"1\"></path><path fill=\"none\" d=\"M 76 159.5 L 590 159.5\" stroke=\"#D8D8D8\" stroke-width=\"1\"  opacity=\"1\"></path><path fill=\"none\" d=\"M 76 106.5 L 590 106.5\" stroke=\"#D8D8D8\" stroke-width=\"1\"  opacity=\"1\"></path><path fill=\"none\" d=\"M 76 52.5 L 590 52.5\" stroke=\"#D8D8D8\" stroke-width=\"1\"  opacity=\"1\"></path></g><g class=\"highcharts-axis\" ><path fill=\"none\" d=\"M 118.5 318 L 118.5 328\" stroke=\"#C0D0E0\" stroke-width=\"1\" opacity=\"1\"></path><path fill=\"none\" d=\"M 161.5 318 L 161.5 328\" stroke=\"#C0D0E0\" stroke-width=\"1\" opacity=\"1\"></path><path fill=\"none\" d=\"M 204.5 318 L 204.5 328\" stroke=\"#C0D0E0\" stroke-width=\"1\" opacity=\"1\"></path><path fill=\"none\" d=\"M 246.5 318 L 246.5 328\" stroke=\"#C0D0E0\" stroke-width=\"1\" opacity=\"1\"></path><path fill=\"none\" d=\"M 289.5 318 L 289.5 328\" stroke=\"#C0D0E0\" stroke-width=\"1\" opacity=\"1\"></path><path fill=\"none\" d=\"M 332.5 318 L 332.5 328\" stroke=\"#C0D0E0\" stroke-width=\"1\" opacity=\"1\"></path><path fill=\"none\" d=\"M 375.5 318 L 375.5 328\" stroke=\"#C0D0E0\" stroke-width=\"1\" opacity=\"1\"></path><path fill=\"none\" d=\"M 418.5 318 L 418.5 328\" stroke=\"#C0D0E0\" stroke-width=\"1\" opacity=\"1\"></path><path fill=\"none\" d=\"M 461.5 318 L 461.5 328\" stroke=\"#C0D0E0\" stroke-width=\"1\" opacity=\"1\"></path><path fill=\"none\" d=\"M 503.5 318 L 503.5 328\" stroke=\"#C0D0E0\" stroke-width=\"1\" opacity=\"1\"></path><path fill=\"none\" d=\"M 546.5 318 L 546.5 328\" stroke=\"#C0D0E0\" stroke-width=\"1\" opacity=\"1\"></path><path fill=\"none\" d=\"M 590.5 318 L 590.5 328\" stroke=\"#C0D0E0\" stroke-width=\"1\" opacity=\"1\"></path><path fill=\"none\" d=\"M 75.5 318 L 75.5 328\" stroke=\"#C0D0E0\" stroke-width=\"1\" opacity=\"1\"></path><path fill=\"none\" d=\"M 76 318.5 L 590 318.5\" stroke=\"#C0D0E0\" stroke-width=\"1\"  visibility=\"visible\"></path></g><g class=\"highcharts-axis\" ><text x=\"28.234375\"  text-anchor=\"middle\" transform=\"translate(0,0) rotate(270 28.234375 185.5)\" class=\" highcharts-yaxis-title\" style=\"color:#707070;fill:#707070;\" visibility=\"visible\" y=\"185.5\">Values</text></g><g class=\"highcharts-series-group\" ><g class=\"highcharts-series\" visibility=\"visible\"  transform=\"translate(76,53) scale(1 1)\" clip-path=\"url(#highcharts-8)\"><path fill=\"none\" d=\"M 21.416666666666668 233.306 L 64.25 189.20999999999998 L 107.08333333333334 152.216 L 149.91666666666666 128.048 L 192.75 112.35999999999999 L 235.58333333333334 78.44 L 278.4166666666667 121.26400000000001 L 321.25000000000006 107.59 L 364.08333333333337 35.615999999999985 L 406.9166666666667 59.25399999999999 L 449.75000000000006 163.664 L 492.58333333333337 207.336\" stroke=\"#7cb5ec\" stroke-width=\"2\"  stroke-linejoin=\"round\" stroke-linecap=\"round\"></path></g><g class=\"highcharts-markers\" visibility=\"visible\"  transform=\"translate(76,53) scale(1 1)\" clip-path=\"none\"><path fill=\"#7cb5ec\" d=\"M 492 203.336 C 497.328 203.336 497.328 211.336 492 211.336 C 486.672 211.336 486.672 203.336 492 203.336 Z\"></path><path fill=\"#7cb5ec\" d=\"M 449 159.664 C 454.328 159.664 454.328 167.664 449 167.664 C 443.672 167.664 443.672 159.664 449 159.664 Z\"></path><path fill=\"#7cb5ec\" d=\"M 406 55.25399999999999 C 411.328 55.25399999999999 411.328 63.25399999999999 406 63.25399999999999 C 400.672 63.25399999999999 400.672 55.25399999999999 406 55.25399999999999 Z\"></path><path fill=\"#7cb5ec\" d=\"M 364 31.615999999999985 C 369.328 31.615999999999985 369.328 39.615999999999985 364 39.615999999999985 C 358.672 39.615999999999985 358.672 31.615999999999985 364 31.615999999999985 Z\"></path><path fill=\"#7cb5ec\" d=\"M 321 103.59 C 326.328 103.59 326.328 111.59 321 111.59 C 315.672 111.59 315.672 103.59 321 103.59 Z\"></path><path fill=\"#7cb5ec\" d=\"M 278 117.26400000000001 C 283.328 117.26400000000001 283.328 125.26400000000001 278 125.26400000000001 C 272.672 125.26400000000001 272.672 117.26400000000001 278 117.26400000000001 Z\"></path><path fill=\"#7cb5ec\" d=\"M 235 74.44 C 240.328 74.44 240.328 82.44 235 82.44 C 229.672 82.44 229.672 74.44 235 74.44 Z\"></path><path fill=\"#7cb5ec\" d=\"M 192 108.35999999999999 C 197.328 108.35999999999999 197.328 116.35999999999999 192 116.35999999999999 C 186.672 116.35999999999999 186.672 108.35999999999999 192 108.35999999999999 Z\"></path><path fill=\"#7cb5ec\" d=\"M 149 124.048 C 154.328 124.048 154.328 132.048 149 132.048 C 143.672 132.048 143.672 124.048 149 124.048 Z\"></path><path fill=\"#7cb5ec\" d=\"M 107 148.216 C 112.328 148.216 112.328 156.216 107 156.216 C 101.672 156.216 101.672 148.216 107 148.216 Z\"></path><path fill=\"#7cb5ec\" d=\"M 64 185.20999999999998 C 69.328 185.20999999999998 69.328 193.20999999999998 64 193.20999999999998 C 58.672 193.20999999999998 58.672 185.20999999999998 64 185.20999999999998 Z\"></path><path fill=\"#7cb5ec\" d=\"M 21 229.306 C 26.328 229.306 26.328 237.306 21 237.306 C 15.672 237.306 15.672 229.306 21 229.306 Z\"></path></g></g><text x=\"300\" text-anchor=\"middle\" class=\"highcharts-title\"  style=\"color:#333333;font-size:18px;fill:#333333;width:536px;\" y=\"24\"><tspan>Chart title</tspan></text><g class=\"highcharts-legend\"  transform=\"translate(258,356)\"><g ><g><g class=\"highcharts-legend-item\"  transform=\"translate(8,3)\"><path fill=\"none\" d=\"M 0 11 L 16 11\" stroke=\"#7cb5ec\" stroke-width=\"2\"></path><path fill=\"#7cb5ec\" d=\"M 8 7 C 13.328 7 13.328 15 8 15 C 2.6719999999999997 15 2.6719999999999997 7 8 7 Z\"></path><text x=\"21\" style=\"color:#333333;font-size:12px;font-weight:bold;cursor:pointer;fill:#333333;\" text-anchor=\"start\"  y=\"15\"><tspan>Series 1</tspan></text></g></g></g></g><g class=\"highcharts-axis-labels highcharts-xaxis-labels\" ><text x=\"97.41666666666667\" style=\"color:#606060;cursor:default;font-size:11px;fill:#606060;\" text-anchor=\"middle\" transform=\"translate(0,0)\" y=\"338\" opacity=\"1\">Jan</text><text x=\"140.25000000000003\" style=\"color:#606060;cursor:default;font-size:11px;fill:#606060;\" text-anchor=\"middle\" transform=\"translate(0,0)\" y=\"338\" opacity=\"1\">Feb</text><text x=\"183.08333333333334\" style=\"color:#606060;cursor:default;font-size:11px;fill:#606060;\" text-anchor=\"middle\" transform=\"translate(0,0)\" y=\"338\" opacity=\"1\">Mar</text><text x=\"225.91666666666669\" style=\"color:#606060;cursor:default;font-size:11px;fill:#606060;\" text-anchor=\"middle\" transform=\"translate(0,0)\" y=\"338\" opacity=\"1\">Apr</text><text x=\"268.74999999999994\" style=\"color:#606060;cursor:default;font-size:11px;fill:#606060;\" text-anchor=\"middle\" transform=\"translate(0,0)\" y=\"338\" opacity=\"1\">May</text><text x=\"311.5833333333333\" style=\"color:#606060;cursor:default;font-size:11px;fill:#606060;\" text-anchor=\"middle\" transform=\"translate(0,0)\" y=\"338\" opacity=\"1\">Jun</text><text x=\"354.4166666666667\" style=\"color:#606060;cursor:default;font-size:11px;fill:#606060;\" text-anchor=\"middle\" transform=\"translate(0,0)\" y=\"338\" opacity=\"1\">Jul</text><text x=\"397.25\" style=\"color:#606060;cursor:default;font-size:11px;fill:#606060;\" text-anchor=\"middle\" transform=\"translate(0,0)\" y=\"338\" opacity=\"1\">Aug</text><text x=\"440.08333333333337\" style=\"color:#606060;cursor:default;font-size:11px;fill:#606060;\" text-anchor=\"middle\" transform=\"translate(0,0)\" y=\"338\" opacity=\"1\">Sep</text><text x=\"482.9166666666667\" style=\"color:#606060;cursor:default;font-size:11px;fill:#606060;\" text-anchor=\"middle\" transform=\"translate(0,0)\" y=\"338\" opacity=\"1\">Oct</text><text x=\"525.7500000000001\" style=\"color:#606060;cursor:default;font-size:11px;fill:#606060;\" text-anchor=\"middle\" transform=\"translate(0,0)\" y=\"338\" opacity=\"1\">Nov</text><text x=\"568.5833333333334\" style=\"color:#606060;cursor:default;font-size:11px;fill:#606060;\" text-anchor=\"middle\" transform=\"translate(0,0)\" y=\"338\" opacity=\"1\">Dec</text></g><g class=\"highcharts-axis-labels highcharts-yaxis-labels\" ><text x=\"61\" style=\"color:#606060;cursor:default;font-size:11px;fill:#606060;width:188px;text-overflow:clip;\" text-anchor=\"end\" transform=\"translate(0,0)\" y=\"321\" opacity=\"1\">0</text><text x=\"61\" style=\"color:#606060;cursor:default;font-size:11px;fill:#606060;width:188px;text-overflow:clip;\" text-anchor=\"end\" transform=\"translate(0,0)\" y=\"268\" opacity=\"1\">50</text><text x=\"61\" style=\"color:#606060;cursor:default;font-size:11px;fill:#606060;width:188px;text-overflow:clip;\" text-anchor=\"end\" transform=\"translate(0,0)\" y=\"215\" opacity=\"1\">100</text><text x=\"61\" style=\"color:#606060;cursor:default;font-size:11px;fill:#606060;width:188px;text-overflow:clip;\" text-anchor=\"end\" transform=\"translate(0,0)\" y=\"162\" opacity=\"1\">150</text><text x=\"61\" style=\"color:#606060;cursor:default;font-size:11px;fill:#606060;width:188px;text-overflow:clip;\" text-anchor=\"end\" transform=\"translate(0,0)\" y=\"109\" opacity=\"1\">200</text><text x=\"61\" style=\"color:#606060;cursor:default;font-size:11px;fill:#606060;width:188px;text-overflow:clip;\" text-anchor=\"end\" transform=\"translate(0,0)\" y=\"56\" opacity=\"1\">250</text></g><g class=\"highcharts-tooltip\"  style=\"cursor:default;padding:0;white-space:nowrap;\" transform=\"translate(0,-9999)\"><path fill=\"none\" d=\"M 3 0 L 13 0 C 16 0 16 0 16 3 L 16 13 C 16 16 16 16 13 16 L 3 16 C 0 16 0 16 0 13 L 0 3 C 0 0 0 0 3 0\"  stroke=\"black\" stroke-opacity=\"0.049999999999999996\" stroke-width=\"5\" transform=\"translate(1, 1)\"></path><path fill=\"none\" d=\"M 3 0 L 13 0 C 16 0 16 0 16 3 L 16 13 C 16 16 16 16 13 16 L 3 16 C 0 16 0 16 0 13 L 0 3 C 0 0 0 0 3 0\"  stroke=\"black\" stroke-opacity=\"0.09999999999999999\" stroke-width=\"3\" transform=\"translate(1, 1)\"></path><path fill=\"none\" d=\"M 3 0 L 13 0 C 16 0 16 0 16 3 L 16 13 C 16 16 16 16 13 16 L 3 16 C 0 16 0 16 0 13 L 0 3 C 0 0 0 0 3 0\"  stroke=\"black\" stroke-opacity=\"0.15\" stroke-width=\"1\" transform=\"translate(1, 1)\"></path><path fill=\"rgb(249, 249, 249)\" fill-opacity=\" .85\" d=\"M 3 0 L 13 0 C 16 0 16 0 16 3 L 16 13 C 16 16 16 16 13 16 L 3 16 C 0 16 0 16 0 13 L 0 3 C 0 0 0 0 3 0\"></path><text x=\"8\"  style=\"font-size:12px;color:#333333;fill:#333333;\" y=\"20\"></text></g></svg>";
		
	//	System.out.println(Arrays.toString(SvgUtils.convertToPng(svgcode)));
			
	}
}
