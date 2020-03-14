package com.github.folkies.abc2pdf;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Abc2PdfService {

	public File convertToPdf(File abcFile) throws IOException, InterruptedException {
		ProcessBuilder processBuilder = new ProcessBuilder();
		Path pdfFile = Files.createTempFile("tmp", ".pdf");
		processBuilder.command("sh", "/deployments/convert.sh", abcFile.toString(), pdfFile.toString());
		processBuilder.inheritIO();
		Process process = processBuilder.start();
		int exit = process.waitFor();
		System.out.println(exit);
		return pdfFile.toFile();
	}

}
