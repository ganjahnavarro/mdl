package core.reports.generator;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import core.model.Customer;
import core.model.Supplier;
import core.model.transaction.PurchaseOrder;
import core.model.transaction.SalesOrder;
import core.reports.FontFactory;
import core.reports.TransactionReportGenerator;
import core.service.CustomerService;
import core.service.SupplierService;
import core.service.transaction.PurchaseOrderService;
import core.service.transaction.SalesOrderService;

@Component
@PropertySource(value = { "classpath:application.properties" })
public class TransactionListReportGenerator extends TransactionReportGenerator {
	
	public static final String TYPE = "transactionList"; 
	
	@Autowired private SalesOrderService salesOrderService;
	@Autowired private PurchaseOrderService purchaseOrderService;
	@Autowired private CustomerService customerService;
	@Autowired private SupplierService supplierService;
	
	@Autowired private Environment environment;

	@Override
	protected void addBody(PdfWriter writer, Document document) throws DocumentException, ParseException {
		String subType = (String) getValue("subType");
		Date startDate = parseDate(getValue("startDate"));
		Date endDate = parseDate(getValue("endDate"));
		
		if (subType.equals("purchaseOrder")) {
			addPurchaseOrders(document, startDate, endDate);
		} else {
			addSalesOrders(document, startDate, endDate);
		}
	}
	
	private void addPurchaseOrders(Document document, Date startDate, Date endDate) throws DocumentException {
		Paragraph reportName = new Paragraph("Purchase Orders", FontFactory.C14B);
		reportName.setSpacingAfter(20);
		reportName.setAlignment(Element.ALIGN_CENTER);
		document.add(reportName);
		
		Supplier supplier = getSupplier();
		
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(90);
		
		table.setTotalWidth(new float[] { 60, 300, 65.5f, 45, 65 });
		
		Long supplierId = supplier != null ? supplier.getId() : null;
		List<PurchaseOrder> purchaseOrders = purchaseOrderService.findFilteredItems(supplierId, startDate, endDate);
		
		List<String> headers = Arrays.asList("DOC. NO.", "SUPPLIER", "DATE", "DISC", "AMOUNT");
		
		if (purchaseOrders != null) {
			for (String header : headers) {
				PdfPCell cell = createCellHeader(header);
				cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);
			}
			
			for (PurchaseOrder item : purchaseOrders) {
				table.addCell(createItemCell(item.getDocumentNo()));
				
				String supplierName = item.getSupplier().getDisplayString();
				table.addCell(createItemCell(supplierName));
				
				table.addCell(createItemCell(item.getDate()));
				
				Object discount = item.getDiscount1() != null ? item.getDiscount1() : "NET";
	            PdfPCell discountCell = createItemCell(discount);
	            discountCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	            table.addCell(discountCell);
				
				table.addCell(createItemCell(item.getAmount()));
			}
		}
		document.add(table);
	}
	
	private void addSalesOrders(Document document, Date startDate, Date endDate) throws DocumentException {
		Paragraph reportName = new Paragraph("Sales Orders", FontFactory.C14B);
		reportName.setSpacingAfter(20);
		reportName.setAlignment(Element.ALIGN_CENTER);
		document.add(reportName);
		
		Customer customer = getCustomer();
		
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(90);
		
		table.setTotalWidth(new float[] { 60, 300, 65.5f, 45, 65 });
		
		Long customerId = customer != null ? customer.getId() : null;
		List<SalesOrder> salesOrders = salesOrderService.findFilteredItems(customerId, startDate, endDate);
		
		List<String> headers = Arrays.asList("DOC. NO.", "CUSTOMER / SALESMAN", "DATE", "DISC", "AMOUNT");
		
		if (salesOrders != null) {
			for (String header : headers) {
				PdfPCell cell = createCellHeader(header);
				cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);
			}
			
			for (SalesOrder item : salesOrders) {
				table.addCell(createItemCell(item.getDocumentNo()));
				
				String customerName = item.getCustomer().getDisplayString();
				String agentName = item.getAgent().getDisplayString();
				table.addCell(createItemCell(customerName + " / " + agentName));
				
				table.addCell(createItemCell(item.getDate()));
				
				Object discount = item.getDiscount1() != null ? item.getDiscount1() : "NET";
	            PdfPCell discountCell = createItemCell(discount);
	            discountCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	            table.addCell(discountCell);
				
				table.addCell(createItemCell(item.getAmount()));
			}
		}
		document.add(table);
	}
	
	private Customer getCustomer() {
		Long id = parseLong(getValue("customerId"));
		if (id != null) {
			return (Customer) customerService.findById(id);
		}
		return null;
	}
	
	private Supplier getSupplier() {
		Long id = parseLong(getValue("supplierId"));
		if (id != null) {
			return (Supplier) supplierService.findById(id);
		}
		return null;
	}
	
	private PdfPCell createItemCell(Object value) {
		PdfPCell cell = createCell(value);
		cell.setBorder(Rectangle.BOTTOM);
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		return cell;
	}

	@Override
	protected Environment getEnvironment() {
		return environment;
	}

}
