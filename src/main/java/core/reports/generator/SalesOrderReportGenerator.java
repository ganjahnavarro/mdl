package core.reports.generator;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import core.model.Customer;
import core.model.Stock;
import core.model.transaction.SalesOrder;
import core.model.transaction.SalesOrderItem;
import core.reports.FontFactory;
import core.reports.TransactionReportGenerator;
import core.service.transaction.SalesOrderService;

@Component
@PropertySource(value = { "classpath:application.properties" })
public class SalesOrderReportGenerator extends TransactionReportGenerator {

	public static final String TYPE = "salesOrder"; 
	
	@Autowired private SalesOrderService salesOrderService;
	@Autowired private Environment environment;
	
	@Override
	protected void addBody(PdfWriter writer, Document document) throws DocumentException {
		String documentNo = (String) getValue("documentNo");
		SalesOrder salesOrder = salesOrderService.findByDocumentNo(documentNo);
		
		addTransactionDetails(document, salesOrder);
        addItems(document, salesOrder);
        addTotals(document, salesOrder);
        addSignatureSlots(document, salesOrder);
	}

	private void addTransactionDetails(Document document, SalesOrder salesOrder)
			throws DocumentException {
		Customer customer = salesOrder.getCustomer();
	
		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(90);
		
		PdfPCell emptyCell = createCell("");
		emptyCell.setColspan(3);
		emptyCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(emptyCell);
        table.addCell(createCell("DATE: " + DATE_FORMATTER.format(new Date())));

        PdfPCell soldTo = createCell("SOLD TO: " + nullSafe(customer.getDisplayString()));
        soldTo.setColspan(3);
        table.addCell(soldTo);
        
        PdfPCell terms = createCell("TERMS: " + nullSafe(customer.getTerms()));
        table.addCell(terms);
        
        PdfPCell address = createCell("ADDRESS: " + nullSafe(customer.getAddress()));
        address.setColspan(3);
        table.addCell(address);
        
        table.addCell(createCell("SO. NO.: " + nullSafe(salesOrder.getDocumentNo())));
        
        PdfPCell contactNo = createCell("TEL/CEL: " + nullSafe(customer.getContact()));
        contactNo.setColspan(2);
        table.addCell(contactNo);

        table.addCell(createCell("TIN: " + nullSafe(customer.getTin())));
        String agent = salesOrder.getAgent() != null ? salesOrder.getAgent().getDisplayString() : null;
        table.addCell(createCell("SALESMAN: " + nullSafe(agent)));
        
        document.add(table);
	}

	private void addItems(Document document, SalesOrder salesOrder) throws DocumentException {
		PdfPTable table = new PdfPTable(7);
		table.setWidthPercentage(90);
		
		table.setTotalWidth(new float[]{ 32.5f, 40, 230, 48, 60, 60, 65 });
		table.setLockedWidth(true);
		
		float itemsHeight = 570;
		
		table.addCell(createCellHeader("QTY"));
        table.addCell(createCellHeader("UNIT"));
        table.addCell(createCellHeader("DESCRIPTION"));
        table.addCell(createCellHeader("DISC"));
        table.addCell(createCellHeader("GROSS"));
        table.addCell(createCellHeader("NET"));
        table.addCell(createCellHeader("AMOUNT"));

        for (SalesOrderItem item : salesOrder.getItems()) {
        	Stock stock = item.getStock();
        	table.addCell(createItemCell(item.getQuantity()));
            table.addCell(createItemCell(stock.getUnit().getDisplayString()));
            
            table.addCell(createStockCell(stock));
            table.addCell(createDiscountCell(item.getDiscount1(), item.getDiscount2()));
            
            table.addCell(createItemCell(item.getGrossAmount()));
            table.addCell(createItemCell(item.getNetAmount()));
            table.addCell(createItemCell(item.getAmount()));
        }
        
        float remainingHeight = itemsHeight - table.getTotalHeight();
        PdfPCell dummy = createItemCell(null);
        dummy.setFixedHeight(remainingHeight);
        table.addCell(dummy);
        table.addCell(dummy);
        table.addCell(dummy);
        table.addCell(dummy);
        table.addCell(dummy);
        table.addCell(dummy);
        table.addCell(dummy);
        
        document.add(table);
	}
	
	private void addTotals(Document document, SalesOrder salesOrder) throws DocumentException {
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(90);
		
		table.setTotalWidth(new float[]{ 430.5f, 105 });
		table.setLockedWidth(true);
		
		PdfPCell remarks = createCell("REMARKS: " + nullSafe(salesOrder.getRemarks()));
		remarks.setRowspan(2);
        table.addCell(remarks);
        
        
        PdfPCell totalLabel = createCell("TOTAL: ", FontFactory.C8);
        totalLabel.setFixedHeight(18f);
        totalLabel.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
        totalLabel.setVerticalAlignment(Element.ALIGN_BOTTOM);
        table.addCell(totalLabel);
        
        
        PdfPCell totalAmount = createCell(salesOrder.getAmount(), FontFactory.C12B);
        totalAmount.setFixedHeight(27f);
        totalAmount.setVerticalAlignment(Element.ALIGN_TOP);
        totalAmount.setHorizontalAlignment(Element.ALIGN_CENTER);
        totalAmount.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
        table.addCell(totalAmount);
        
        document.add(table);
	}
	
	private void addSignatureSlots(Document document, SalesOrder salesOrder) throws DocumentException {
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(90);
		
		table.setTotalWidth(new float[]{ 94.5f, 94.5f, 115.5f, 115.5f, 115.5f });
		table.setLockedWidth(true);
		
		table.addCell(createFixedHeightCell("BOX:"));
		table.addCell(createFixedHeightCell("BUNDLE:"));
		table.addCell(createFixedHeightCell("PREPARED BY:"));
		table.addCell(createFixedHeightCell("CHECKED BY:"));
		table.addCell(createFixedHeightCell("RECEIVED BY:"));
		
        document.add(table);
	}
	
	protected PdfPCell createFixedHeightCell(String value) {
		PdfPCell cell = createCell(value);
		cell.setFixedHeight(45f);
		return cell;
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
