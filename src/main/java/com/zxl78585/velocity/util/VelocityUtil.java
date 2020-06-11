package com.zxl78585.velocity.util;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.generic.NumberTool;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.pdf.BaseFont;

/**
 * <pre>
 * Capital Group Finance Service
 * Capital Finance Service Site
 * 
 * Mixky Co., Ltd. 2015
 * @author Zhaoxinle
 * </pre>
 */
public class VelocityUtil {
	private static String fileName = "zhucexieyi.vm";
	
	public static void createTemplate() throws Exception{
		// 初始化并取得Velocity引擎
		VelocityEngine ve = new VelocityEngine();
		ve.init();
		
		//设置编码
		ve.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
		ve.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
		// 取得velocity的模版

		Template t = ve.getTemplate(fileName,"utf-8");
		
		// 取得velocity的上下文context
		VelocityContext context = new VelocityContext();
		context.put("name", "张三");
		NumberTool number = new NumberTool();
		context.put("number", number);
		// 把数据填入上下文
		StringWriter writer = new StringWriter();
		t.merge(context, writer);
		
		System.out.println(writer.toString());
		createPdf(writer.toString());
//		testPdf(writer.toString());
	}

	public static void createPdf(String htmlCode) throws Exception {
		String outputFile = "D:/test.pdf";
		OutputStream os = new FileOutputStream(outputFile);
		ITextRenderer renderer = new ITextRenderer();
		
		ITextFontResolver fontResolver = renderer.getFontResolver();
		fontResolver.addFont("font/simsun.ttf", BaseFont.IDENTITY_H,
				 BaseFont.NOT_EMBEDDED);
		renderer.setDocumentFromString(htmlCode);
		renderer.layout();
		renderer.createPDF(os);
		os.close();
	}
	public static void testPdf(String htmlCode) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ITextRenderer render = new ITextRenderer();
		ITextFontResolver fontResolver = render.getFontResolver();
		fontResolver.addFont("font/simsun.ttf", BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
		render.setDocumentFromString(htmlCode);
		render.layout();
		render.createPDF(baos);
		baos.close();
	}
}
