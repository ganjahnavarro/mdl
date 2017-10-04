package core.reports.generator;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.RectangleReadOnly;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import core.model.Customer;
import core.model.transaction.SalesOrder;
import core.reports.FontFactory;
import core.reports.ReportGenerator;
import core.service.CustomerService;
import core.service.transaction.SalesOrderService;

@Component
@PropertySource(value = { "classpath:application.properties" })
public class CounterReceiptReportGenerator extends ReportGenerator {
	
	public static final String TYPE = "counterReceipt"; 
	
	@Autowired private SalesOrderService salesOrderService;
	@Autowired private CustomerService customerService;
	@Autowired private Environment environment;
	
	@Override
	public Document newDocumentInstance() {
		Rectangle pageSize = new RectangleReadOnly(300, 400);
		return new Document(pageSize, 10, 10, 10, 10);
	}
	
	@Override
	protected void addBody(PdfWriter writer, Document document) throws DocumentException, ParseException {
		Customer customer = getCustomer();
		Date startDate = parseDate(getValue("startDate"));
		Date endDate = parseDate(getValue("endDate"));
		
		
		List<SalesOrder> salesOrders = salesOrderService.findFilteredItems(customer.getId(), startDate, endDate);

		if (salesOrders != null) {
			addCompanyDetails(writer, document);
	        addTransactionDetails(document, customer);
	        addItemsDetails(document, salesOrders);
		}
	}
	
	private void addCompanyDetails(PdfWriter writer, Document document) throws DocumentException {
		Paragraph preface = new Paragraph();
        preface.add(new Paragraph(getProperty("company.name").toUpperCase(), FontFactory.C11));
        preface.add(new Paragraph("Telefax: " + getProperty("company.fax.no"), FontFactory.C8));
        preface.add(new Paragraph("Cell.: " + getProperty("company.mobile.no"), FontFactory.C8));
        preface.add(new Paragraph(getProperty("company.address"), FontFactory.C8));
        
        addEmptyLine(preface, 1);
        
        preface.setAlignment(Element.ALIGN_CENTER);
        preface.setLeading(0, 1);
        document.add(preface);
	}
	

	private void addTransactionDetails(Document document, Customer customer) throws DocumentException {
		PdfPTable header = new PdfPTable(2);
		header.setWidthPercentage(95);
		header.setWidths(new float[] { 10, 4.5f } );
		
		header.addCell(createCell("TO: " + customer.getDisplayString().toUpperCase()));
		header.addCell(createCell("DATE: " + DATE_FORMATTER.format(new Date())));

		String address = nullSafe(customer.getAddress()).toUpperCase();
		header.addCell(createCell("ADDRESS: " + address));
		
		String terms = nullSafe(customer.getTerms()).toUpperCase();
		header.addCell(createCell("TERMS: " + terms));
		
		PdfPCell reportName = createCellHeader("COUNTER RECEIPT");
		reportName.setColspan(2);
		reportName.setFixedHeight(30);
		header.addCell(reportName);
		
		document.add(header);
	}
	
	private void addItemsDetails(Document document, List<SalesOrder> salesOrders) throws DocumentException {
		float itemsHeight = 160;
		
		PdfPTable items = new PdfPTable(3);
		items.setWidthPercentage(95);
		
		items.addCell(createCellHeader("DATE"));
		items.addCell(createCellHeader("INVOICE NO."));
		items.addCell(createCellHeader("AMOUNT"));
		
		
		BigDecimal totalAmount = BigDecimal.ZERO;
		
		for(SalesOrder salesOrder : salesOrders) {
			items.addCell(createItemCell(salesOrder.getDate()));
			items.addCell(createItemCell(salesOrder.getDocumentNo()));
			items.addCell(createItemCell(salesOrder.getAmount()));
			totalAmount = totalAmount.add(salesOrder.getAmount());
		}
		
        float remainingHeight = itemsHeight - items.getTotalHeight();
        PdfPCell dummy = createItemCell(null);
        dummy.setFixedHeight(remainingHeight);
        items.addCell(dummy);
        items.addCell(dummy);
        items.addCell(dummy);
		
		String totalAmountDisplay = "TOTAL: " + AMOUNT_FORMATTER.format(totalAmount); 
		PdfPCell totalAmountCell = createCell(totalAmountDisplay, FontFactory.C10B);
		totalAmountCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		totalAmountCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		totalAmountCell.setColspan(3);
		totalAmountCell.setFixedHeight(30);
		items.addCell(totalAmountCell);
		
		document.add(items);
	}
	
	private Customer getCustomer() {
		Long id = parseLong(getValue("customerId"));
		if (id == null) {
			throw new IllegalArgumentException("Customer is required.");
		}
		return (Customer) customerService.findById(id);
	}
	
	protected PdfPCell createItemCell(Object value) {
		PdfPCell cell = createCell(value);
		cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
		return cell;
	}
	
	
	@Override
	protected Environment getEnvironment() {
		return environment;
	}

}
