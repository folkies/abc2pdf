package com.github.folkies.abc2pdf;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

public class Abc2PdfServiceTest {

	@Test
	public void shouldConvert() throws IOException, InterruptedException {
		Abc2PdfService abc2Pdf = new Abc2PdfService();
		File pdfFile = abc2Pdf.convertToPdf(new File("/Users/hwellmann/git/abc/Rover.abc"));
		System.out.println(pdfFile);
	}

}
