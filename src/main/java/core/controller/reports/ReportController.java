package core.controller.reports;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import core.reports.PurchaseOrderReportGenerator;
import core.reports.ReportData;
import core.reports.ReportGenerator;
import core.reports.SalesOrderReportGenerator;

@CrossOrigin
@RestController
@RequestMapping("/")
public class ReportController {

	private static Map<String, ReportGenerator> registry;

	@Autowired PurchaseOrderReportGenerator purchaseOrderReportGenerator;
	@Autowired SalesOrderReportGenerator salesOrderReportGenerator;

	@PostConstruct
	public void initialize() {
		registry = new ConcurrentHashMap<String, ReportGenerator>();
		registry.put(PurchaseOrderReportGenerator.TYPE, purchaseOrderReportGenerator);
		registry.put(SalesOrderReportGenerator.TYPE, salesOrderReportGenerator);
	}

	@RequestMapping(value = "reports/{type}", method = RequestMethod.POST)
	public String generateReport(@PathVariable String type, @RequestBody ReportData[] values, HttpServletRequest request)
			throws FileNotFoundException, DocumentException {
		ReportGenerator generator = type != null ? registry.get(type) : null;

		if (generator != null) {
			Document document = new Document(PageSize.A4, 0, 0, 20, 20);
			
			String fileName = getFileName(request);
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(getFilePath(fileName)));

			document.open();
			generator.setValues(values);
			generator.addContent(writer, document);
			document.close();

			return fileName;
		}
		return null;
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
