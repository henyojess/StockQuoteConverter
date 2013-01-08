package com.henyo.pse;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class ConverterTest {


	Converter converter = new Converter();
	String fileName = "sample.pdf";

	@Test
	public void testPDFtoTextConversion() {		
		File inputFile = new File(fileName);
		File tempFile = converter.convertPDFtoText(inputFile);
		assertTrue(tempFile.exists());
		tempFile.renameTo(new File(Converter.replaceSuffix(fileName, ".txt")));
	}

	@Test
	public void testTextToCSVConversion() throws Exception {		
		File inputFile = new File(Converter.replaceSuffix(fileName, ".txt"));
		File tempFile = converter.convertTextToCSV(inputFile);
		assertTrue(tempFile.exists());
		tempFile.renameTo(new File(Converter.replaceSuffix(fileName, ".csv")));
	}

	@Test
	public void testReplaceSuffix() throws Exception {		
		String result = Converter.replaceSuffix(fileName, ".txt");
		assertTrue(result.endsWith(".txt"));
		assertFalse(result.contains("pdf"));
	}
	
	@Test
	public void testConvertLineToCSV() throws Exception {
		String line = "ASIATRUST ASIA - - - - - - - - -";
		String actual = converter.convertLineToCSV(line);
		String expected ="ASIA|-|-|-|-|-|-|-|-|-";
		assertEquals(expected, actual);		
	}

	@Test
	public void testIsQuoteLine() throws Exception {
		String line;
		line = "ASIA|-|-|-|-|-|-|-|-|-";
		assertTrue("should be valid", Converter.isQuoteLine(line));
		
		line = "CHIB|478|479|480|480|478|478|2010|962798|-";
		assertTrue("should be valid", Converter.isQuoteLine(line));
		
		line = "BDO|61|61.05|61.35|61.55|61|61.05|3354440|204989672|(135122528)";
		assertTrue("should be valid", Converter.isQuoteLine(line));
		
		line = "2GO|1.5|1.89|-|-|-|-|-|-|-";
		assertTrue("should be valid", Converter.isQuoteLine(line));
		
	}
}
