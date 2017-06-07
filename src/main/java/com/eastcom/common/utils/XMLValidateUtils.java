package com.eastcom.common.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXValidator;
import org.dom4j.io.XMLWriter;
import org.dom4j.util.XMLErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

/**
 * 
 * Title: eastcom.common <br>
 * Description: <br>
 * @author <a href="mailto:13507615840@163.com">lukw</a><br>
 * @email:13507615840@163.com	<br>
 * @version 1.0 <br>
 * @creatdate 2017年6月7日 下午9:38:52 <br>
 *
 */
public class XMLValidateUtils {
	private static Logger logger = LoggerFactory
			.getLogger(XMLValidateUtils.class);

	/**
	 * 利用xsd文件验证xml文件的结构合法性
	 * 
	 * @param xmlFileName
	 * @param xsdFileName
	 * @return
	 */
	public static boolean validateXMLByXSD(String xmlFileName,
			String xsdFileName) {

		if (xmlFileName == null || xmlFileName.length() < 1) {
			return false;
		}

		if (xsdFileName == null || xsdFileName.length() < 1) {
			return false;
		}
		try {
			XMLErrorHandler errorHandler = new XMLErrorHandler();
			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setValidating(true);// 注意要设置有效，否则后面验证无用
			// factory.setNamespaceAware(true);//XSD中有命名空间设置
			SAXParser parser = factory.newSAXParser();
			SAXReader xmlReader = new SAXReader();
			org.dom4j.Document documentObject = (org.dom4j.Document) xmlReader
					.read(new File(xmlFileName));// filename是对应符合XSD模式的具体xml文件绝对路径名

			// String xsdpathfile =
			// "e:\\schemaexample\\example.xsd";//xsdPathfile是绝对路径名
			parser.setProperty(
					"http://java.sun.com/xml/jaxp/properties/schemaLanguage",
					"http://www.w3.org/2001/XMLSchema");
			parser.setProperty(
					"http://java.sun.com/xml/jaxp/properties/schemaSource",
					"file:" + xsdFileName);
			SAXValidator validator = new SAXValidator(parser.getXMLReader());
			validator.setErrorHandler(errorHandler);
			validator.validate(documentObject);
			XMLWriter writer = new XMLWriter(OutputFormat.createPrettyPrint());
			if (errorHandler.getErrors().hasContent()) {
				writer.write(errorHandler.getErrors());
				return false;
			} else {
				logger.info("xml validate success by xsd.");
				return true;
			}
		} catch (Exception ex) {
			logger.error("xml file: " + xmlFileName
					+ " validate failed by xsd file:" + xsdFileName
					+ " because " + ex.getMessage());
			return false;
		}
	}

	/**
	 * 利用xsd文件验证xml文件的结构合法性
	 * 
	 * @param xmlFileName
	 * @param xsdFileName
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 */
	public static boolean validateXML(String xmlString, String schemalFilePath)
			throws SAXException, IOException {
		SchemaFactory schemaFactory = SchemaFactory
				.newInstance("http://www.w3.org/2001/XMLSchema");
		Schema schema = schemaFactory.newSchema(new File(schemalFilePath));
		Validator validator = schema.newValidator();
		InputStream inputStream = new ByteArrayInputStream(
				xmlString.getBytes("UTF-8"));

		Source source = new StreamSource(inputStream);

		validator.validate(source);

		return true;
	}

	public static void main(String[] args) {

		String xmlFile = "C:\\myxml.xml";
		String xsdFile = "C:\\myxsd.xsd";
		XMLValidateUtils.validateXMLByXSD(xmlFile, xsdFile);

	}
}
