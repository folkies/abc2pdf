package com.github.folkies.abc2pdf;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/print")
public class PrintApi {

	@Inject
	Abc2PdfService abc2pdfService;

	@POST
	@Consumes("*/*")
	@Produces("application/pdf")
	public Response print(InputStream is) throws IOException, InterruptedException {
		java.nio.file.Path tempFile = Files.createTempFile("tmp", ".abc");
		Files.copy(is, tempFile, StandardCopyOption.REPLACE_EXISTING);
		File pdfFile = abc2pdfService.convertToPdf(tempFile.toFile());
		return Response.ok(pdfFile, "application/pdf").build();
	}
}
