package core.controller.reports;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import core.reports.CounterReceiptReportGenerator;
import core.reports.PurchaseOrderReportGenerator;
import core.reports.ReportData;
import core.reports.ReportGenerator;
import core.reports.SalesOrderReportGenerator;

@CrossOrigin
@RestController
@RequestMapping("/")
public class ReportController {

	private static Map<String, ReportGenerator> registry;

	@Autowired private PurchaseOrderReportGenerator purchaseOrderReportGenerator;
	@Autowired private SalesOrderReportGenerator salesOrderReportGenerator;
	@Autowired private CounterReceiptReportGenerator counterReceiptReportGenerator;

	@PostConstruct
	public void initialize() {
		registry = new ConcurrentHashMap<String, ReportGenerator>();
		registry.put(PurchaseOrderReportGenerator.TYPE, purchaseOrderReportGenerator);
		registry.put(SalesOrderReportGenerator.TYPE, salesOrderReportGenerator);
		registry.put(CounterReceiptReportGenerator.TYPE, counterReceiptReportGenerator);
	}

	@RequestMapping(value = "reports/{type}", method = RequestMethod.POST)
	public String generateReport(@PathVariable String type, @RequestBody ReportData[] values,
			HttpServletRequest request) throws DocumentException, ParseException, FileNotFoundException {
		ReportGenerator generator = registry.get(type);
		if (generator != null) {
			Document document = generator.newDocumentInstance();
			String fileName = getFileName(request);
			generateDocument(values, generator, document, fileName);
			return fileName;
		}
		return null;
	}

	private void generateDocument(ReportData[] values, ReportGenerator generator, Document document, String fileName)
			throws DocumentException, ParseException, FileNotFoundException {
		PdfWriter writer = getPDFWriter(document, fileName);

		document.open();
		generator.setValues(values);
		generator.addContent(writer, document);
		document.close();
	}

	private PdfWriter getPDFWriter(Document document, String fileName) throws DocumentException, FileNotFoundException {
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(getFilePath(fileName)));
		return writer;
	}

	private static String getFileName(HttpServletRequest request) {
		String remoteAddress = request.getRemoteAddr();
		return remoteAddress.replaceAll("\\.", "") + ".pdf";
	}

	private static String getFilePath(String fileName) {
		String root = System.getProperty("jboss.home.dir");
		String subFolder = "/standalone/deployments/web/pdfs/";
		return root + subFolder + fileName;
	}

}
