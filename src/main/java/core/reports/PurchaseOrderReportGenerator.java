package core.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import core.model.Stock;
import core.model.Supplier;
import core.model.transaction.PurchaseOrder;
import core.model.transaction.PurchaseOrderItem;
import core.service.transaction.PurchaseOrderService;

@Component
@PropertySource(value = { "classpath:application.properties" })
public class PurchaseOrderReportGenerator extends TransactionReportGenerator {

	public static final String TYPE = "purchaseOrder"; 
	
	@Autowired private PurchaseOrderService purchaseOrderService;
	@Autowired private Environment environment;
	
	@Override
	protected void addBody(PdfWriter writer, Document document) throws DocumentException {
		String documentNo = (String) getValue("documentNo");
		PurchaseOrder purchaseOrder = purchaseOrderService.findByDocumentNo(documentNo);
		
		addTransactionDetails(document, purchaseOrder);
        addItems(document, purchaseOrder);
        addTotals(document, purchaseOrder);
        addSignatureSlots(document, purchaseOrder);
	}

	private void addTransactionDetails(Document document, PurchaseOrder purchaseOrder)
			throws DocumentException {
		Supplier supplier = purchaseOrder.getSupplier();
	
		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(90);

        PdfPCell soldTo = createCell("SUPPLIER: " + nullSafe(supplier.getDisplayString()));
        soldTo.setColspan(3);
        table.addCell(soldTo);
        
        PdfPCell terms = createCell("TERMS: " + nullSafe(supplier.getTerms()));
        table.addCell(terms);
        
        PdfPCell address = createCell("ADDRESS: " + nullSafe(supplier.getAddress()));
        address.setColspan(3);
        table.addCell(address);
        
        table.addCell(createCell("PO. NO.: " + nullSafe(purchaseOrder.getDocumentNo())));
        
        PdfPCell contactNo = createCell("TEL/CEL: " + nullSafe(supplier.getContact()));
        contactNo.setColspan(2);
        table.addCell(contactNo);

        PdfPCell tinNo = createCell("TIN: " + nullSafe(supplier.getTin()));
        tinNo.setColspan(2);
        table.addCell(tinNo);
        
        document.add(table);
	}

	private void addItems(Document document, PurchaseOrder purchaseOrder) throws DocumentException {
		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(90);
		
		table.setTotalWidth(new float[]{ 30, 45, 320.5f, 35, 50, 55 });
		table.setLockedWidth(true);
		
		float itemsHeight = 570;
		
		table.addCell(createCellHeader("Qty"));
        table.addCell(createCellHeader("Unit"));
        table.addCell(createCellHeader("Description"));
        table.addCell(createCellHeader("Disc"));
        table.addCell(createCellHeader("Unit Price"));
        table.addCell(createCellHeader("Amount"));

        for (PurchaseOrderItem item : purchaseOrder.getItems()) {
        	Stock stock = item.getStock();
        	table.addCell(createItemCell(item.getQuantity()));
            table.addCell(createItemCell(stock.getUnit().getDisplayString()));
            table.addCell(createItemCell(stock.getDisplayString()));
            
            Object discount = item.getDiscount1() != null ? item.getDiscount1() : "NET";
            PdfPCell discountCell = createItemCell(discount);
            discountCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(discountCell);
            
            table.addCell(createItemCell(item.getPrice()));
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
        
        document.add(table);
	}
	
	private void addTotals(Document document, PurchaseOrder purchaseOrder) throws DocumentException {
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(90);
		
		table.setTotalWidth(new float[]{ 430.5f, 105 });
		table.setLockedWidth(true);
		
		PdfPCell remarks = createCell("REMARKS: " + nullSafe(purchaseOrder.getRemarks()));
		remarks.setRowspan(2);
        table.addCell(remarks);
        
        
        PdfPCell totalLabel = createCell("TOTAL: ", TR8);
        totalLabel.setFixedHeight(18f);
        totalLabel.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
        totalLabel.setVerticalAlignment(Element.ALIGN_BOTTOM);
        table.addCell(totalLabel);
        
        
        Font TR11B = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        PdfPCell totalAmount = createCell(purchaseOrder.getAmount(), TR11B);
        totalAmount.setFixedHeight(27f);
        totalAmount.setVerticalAlignment(Element.ALIGN_TOP);
        totalAmount.setHorizontalAlignment(Element.ALIGN_CENTER);
        totalAmount.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
        table.addCell(totalAmount);
        
        document.add(table);
	}
	
	private void addSignatureSlots(Document document, PurchaseOrder purchaseOrder) throws DocumentException {
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
